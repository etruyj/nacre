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

    public static NetworkInterface triageType(NetworkInterfaceConfig iface, String type, BpConnector pearl) throws Exception {
        // The purpose of this function is to triage how to handle the request. 
        // If the requested interface already exists, setting the information
        // is a PUT request. If the interface doesn't exist already (e.g. aggregate)
        // the interface has to be created, which is a POST.

        List<NetworkInterface> iface_list = ListNetworkInterfaces.activeByType(type, pearl);
        List<NetworkInterface> lagg_list = ListNetworkInterfaces.activeByType("lagg", pearl);

        NetworkInterface new_iface = null;
        if((iface.isAggregate() && lagg_list.size() == 0) || iface_list.size() == 0) {
            // The interface needs to be created.
            new_iface = createNew(iface, type, iface_list, pearl);
        } else {
            // The interface needs to be updated.
        
        }
        
        return new_iface;        
    }

    public static NetworkInterface createNew(NetworkInterfaceConfig iface, String type, List<NetworkInterface> iface_list, BpConnector pearl) {
        if(iface.isAggregate()) {
            log.info("Creating new aggregate " + type + " network interface with address " + iface.getAddress());
        } else {
            log.info("Creating new " + type + " network interface with address " + iface.getAddress());
        }

        return null;
    }
}
