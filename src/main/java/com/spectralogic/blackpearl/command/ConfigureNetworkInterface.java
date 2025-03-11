//===================================================================
// ConfigureNetworkInterface.java
//      Description:
//          The purpose of this class is to handle configuring the
//          network interfaces on a BlackPearl.
//
// Created by Sean Snyder
//===================================================================

package com.spectralogic.blackpearl.nacre.command;

import com.spectralogic.blackpearl.nacre.api.BpConnector;
import com.spectralogic.blackpearl.nacre.model.NetworkInterface;
import com.spectralogic.blackpearl.nacre.model.NetworkInterfaceConfig;
import com.spectralogic.blackpearl.nacre.model.NetworkInterfaceSend;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigureNetworkInterface {
    private static final Logger log = LoggerFactory.getLogger(ConfigureNetworkInterface.class);

    public static String ipFromShell(String ip, String prefix, boolean aggregate, String type, BpConnector pearl) {
        log.info("Manually configuring " + type + " interface from the shell.");

        String response = "none";

        try {
            NetworkInterfaceConfig iface = new NetworkInterfaceConfig();

            iface.setAddress(ip);
            iface.setPrefix(prefix);
            iface.setAggregate(aggregate);

            NetworkInterface new_iface = triageType(iface, type, pearl);

            response = "Successfully configured " + type + " interface.";
        } catch(Exception e) {
            log.error(e.getMessage());
            log.error("Failed to configure [" + type + "] interface.");
        }
    
        return response;
    }

    public static String interfacesFromMap(Map<String, NetworkInterfaceConfig> iface_map, BpConnector pearl) {
        // This is for automated configurations where a script is provided.
        log.info("Configuring network interfaces from provided configuration");

        ArrayList<String> response = new ArrayList<String>();
        String response_text = "";
        int successes = 0;

        // Configured the data path first.
        // This will allow us to make all changes up to this point before having to reset the 
        // management connection.
        // Each piece goes into its own try catch block as a failure should allow the configuration
        // to proceed.
        try {
            if(iface_map.get("data") != null) {
                System.out.println("Configuring DATA interface");

                NetworkInterface new_iface = triageType(iface_map.get("data"), "data", pearl);

                if(new_iface != null) {
                    response_text = "DATA interface set to " + new_iface.getAddressString();
                    successes++;
                } else {
                    throw new Exception("Failed to set IP address on data interface.");
                }
            } else {
                log.info("No configuration information is present for the BlackPearl data interface.");
            }
        } catch(Exception e) {
            log.error(e.getMessage());
            response_text = "Failed to configure DATA interface.";
        }

        System.out.println(response_text);

        // Configured the mgmt path second.
        // if the script loses connection to the BP, the configuration should be complete.
        // Each piece goes into its own try catch block as a failure should allow the configuration
        // to proceed.
        try {
            if(iface_map.get("management") != null) {
                System.out.println("Configuring MGMT interface");

                NetworkInterface new_iface = triageType(iface_map.get("management"), "management", pearl);

                if(new_iface != null) {
                    response_text = "MGMT interface set to " + new_iface.getAddressString();
                    successes++;
                } else {
                    throw new Exception("Failed to set IP address on MGMT interface.");
                }
            } else {
                log.info("No configuration information is present for the BlackPearl management interface.");
            }
        } catch(Exception e) {
            log.error(e.getMessage());
            response_text = "Failed to configure MGMT interface.";
        }

        System.out.println(response_text);

        return "Successfully configured (" + successes + "/" + iface_map.size() + ") network interfacces.";
    }

    public static NetworkInterface triageType(NetworkInterfaceConfig iface, String type, BpConnector pearl) throws Exception {
        // The purpose of this function is to triage how to handle the request. 
        // If the requested interface already exists, setting the information
        // is a PUT request. If the interface doesn't exist already (e.g. aggregate)
        // the interface has to be created, which is a POST.

        List<NetworkInterface> iface_list = ListNetworkInterfaces.connectedByType(type, pearl);
        List<NetworkInterface> lagg_list = ListNetworkInterfaces.activeByType("lagg", pearl);

        NetworkInterface new_iface = null;
        if((iface.isAggregate() && lagg_list.size() == 0)) { // Physical interfaces should be configured and can't be created.
            // The interface needs to be created.
            new_iface = createNew(iface, type, iface_list, pearl);
        } else if(iface.isAggregate()) {
            // The interface needs to be updated.
            new_iface = updateLaggInterface(iface, type, iface_list, lagg_list, pearl);
        } else {
            new_iface = updateSingleInterface(iface, type, iface_list, pearl);
        }
        
        return new_iface;        
    }

    public static NetworkInterface createNew(NetworkInterfaceConfig iface, String type, List<NetworkInterface> iface_list, BpConnector pearl) throws Exception {
        // This creates a new network interface from the list. 
        NetworkInterfaceSend configured_iface = new NetworkInterfaceSend();

        if(iface_list == null || iface_list.size() == 0) {
            log.error("Active interface list is empty.");
            throw new Exception("Unable to configure IP address. No active interfaces available.");
        }

        if(iface.isAggregate()) {
            log.info("Creating new aggregate " + type + " network interface with address " + iface.getAddress());
            configured_iface.setDhcp(false);
            configured_iface.setType("lagg");
            configured_iface.setUp(true);
            configured_iface.setMtu("1500");
            configured_iface.setLaggPortOptions(true);
            configured_iface.setAddress(iface.getAddress() + "/" + iface.getPrefix());
           
            // Just grab the peer name off of the first interface.
            configured_iface.setPeername(iface_list.get(0).getPeername());
            
            for(NetworkInterface aif : iface_list) {
                configured_iface.addLaggPort(aif.getIfname());
            }
        } else {
            log.info("Creating new " + type + " network interface with address " + iface.getAddress());
            throw new Exception("Missing functionality to support creating a new address.");
        }

        NetworkInterface new_iface = pearl.createNetworkInterface(configured_iface);

        log.info("Successfully configured network interface.");

        return new_iface;
    }

    public static NetworkInterface updateLaggInterface(NetworkInterfaceConfig iface, String type, List<NetworkInterface> iface_list, List<NetworkInterface> lagg_list, BpConnector pearl) throws Exception {
        // this function updates the aggregate interface. It assumes there is only one aggregate interface
        // of the desired type. (I actually assume the only lagg interface will be data, but I still check.
        log.info("Updating aggregate " + type + " interface to have new address " + iface.getAddress());

        NetworkInterfaceSend configured_iface = null;
        NetworkInterface new_iface = null;

        for(NetworkInterface lagg : lagg_list) {
            if(lagg.getGroup().equals(type)) {
                log.debug("Interface " + lagg.getName() + " is a lagg associated with " + type);
                configured_iface = new NetworkInterfaceSend(lagg);
            }
        }
        
        if(configured_iface == null) {
            log.warn("None of the existing LAgg interfaces belong to group: " + type);    
            new_iface = createNew(iface, type, iface_list, pearl);
        } else {
            log.info("Updating network interface: " + configured_iface.getName());

            configured_iface.setAddress(iface.getAddress() + "/" + iface.getPrefix());
            configured_iface.setDefaultGateway(iface.getGateway());

            new_iface = pearl.updateNetworkInterface(configured_iface);
        }

        return new_iface;
    }

    public static NetworkInterface updateSingleInterface(NetworkInterfaceConfig iface, String type, List<NetworkInterface> iface_list, BpConnector pearl) throws Exception {
        // This one will search the list of active interfaces. If there is an interface without an IP
        // we'll use that IP. If there isn't one with an available IP, we'll update the the interface.
        log.info("Updating " + type + " network interface with address " + iface.getAddress());

        NetworkInterfaceSend configured_iface = null;
        NetworkInterface new_iface = null;

        if(iface_list == null || iface_list.size() == 0) {
            log.error("Active interface list is empty.");
            throw new Exception("Unable to configure IP address. No active interfaces available.");
        }

        // Search for an unassigned active interface
        NetworkInterface open_interface = null;
        
        for(NetworkInterface aif : iface_list) {
            if(open_interface == null) {
                open_interface = aif;
            }
        }

        if(open_interface == null) { // all active interfaces are already assigend.
            configured_iface = new NetworkInterfaceSend(iface_list.get(0)); // use the first one in the list
        } else { // use the available ip address
            configured_iface = new NetworkInterfaceSend(open_interface);
        }

        configured_iface.setAddress(iface.getAddress() + "/" + iface.getPrefix());
        configured_iface.setDefaultGateway(iface.getGateway());

        new_iface = pearl.updateNetworkInterface(configured_iface);

        return new_iface;
    }
}
