//===================================================================
// ListNetworkInterfaces.java
//      Description:
//          This class handles commands associated with list interfaces.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.command;

import com.spectralogic.blackpearl.nacre.api.BpConnector;
import com.spectralogic.blackpearl.nacre.model.NetworkInterface;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ListNetworkInterfaces {
    private static final Logger log = LoggerFactory.getLogger(ListNetworkInterfaces.class);

    public static ArrayList<NetworkInterface> active(BpConnector pearl) {
        log.info("Listing active interfaces in the BlackPearl.");

        ArrayList<NetworkInterface> active_interfaces = new ArrayList<NetworkInterface>();

        ArrayList<NetworkInterface> interface_list = all(pearl);

        for(NetworkInterface iface : interface_list) {
            if(iface.isUp() && iface.getLinkStatus().equals("active") 
                    && iface.getAddresses() != null && iface.getAddresses().size() > 0) {
                active_interfaces.add(iface);
            }
        }

        log.info("Found (" + active_interfaces.size() + ") active interfaces.");

        return active_interfaces;
    }

    public static ArrayList<NetworkInterface> activeByType(String type, BpConnector pearl) {
        log.info("Listing active interfaces of type [" + type + "] in the BlackPearl.");

        ArrayList<NetworkInterface> data_list = new ArrayList<NetworkInterface>();

        ArrayList<NetworkInterface> interface_list = all(pearl);

        for(NetworkInterface interf : interface_list) {
            if(interf.getType().equals(type) && interf.getLinkStatus().equals("active")) {
                data_list.add(interf);
            }
        }

        log.info("Found (" + data_list.size() + ") active [" + type + "] interfaces.");

        return data_list;
    }

    public static ArrayList<NetworkInterface> all(BpConnector pearl) {
        log.info("Listing all interfaces in the BlackPearl.");

        ArrayList<NetworkInterface> interface_list = new ArrayList<NetworkInterface>();

        try {
            interface_list = pearl.listNetworkInterfaces();

            log.info("Found (" + interface_list.size() + ") interfaces.");
        } catch(Exception e) {
            log.error(e.getMessage());
            log.error("Failed to list interfaces.");
        }

        return interface_list;
    }

    public static ArrayList<NetworkInterface> dataActive(BpConnector pearl) {
        log.info("Listing active data interfaces in the BlackPearl.");

        ArrayList<NetworkInterface> data_list = new ArrayList<NetworkInterface>();

        ArrayList<NetworkInterface> interface_list = all(pearl);

        for(NetworkInterface interf : interface_list) {
            if(interf.getType().equals("data") && interf.getLinkStatus().equals("active")) {
                data_list.add(interf);
            }
        }

        log.info("Found (" + data_list.size() + ") active data interfaces.");

        return data_list;
    }

    public static ArrayList<NetworkInterface> management(BpConnector pearl) {
        log.info("Listing management interfaces in the BlackPearl.");

        ArrayList<NetworkInterface> management_list = new ArrayList<NetworkInterface>();

        ArrayList<NetworkInterface> interface_list = all(pearl);

        for(NetworkInterface interf : interface_list) {
            if(interf.getType().equals("management")) {
                management_list.add(interf);
            }
        }

        log.info("Found (" + management_list.size() + ") management interfaces.");

        return management_list;
    }
}
