//===================================================================
// UpdateNetworkInterface.java
//      Description:
//          The purpose of this class is to update and existing
//          network interface in the BlackPearl.
// 
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.command;

import com.spectralogic.blackpearl.nacre.api.BpConnector;
import com.spectralogic.blackpearl.nacre.model.NetworkInterface;
import com.spectralogic.blackpearl.nacre.model.NetworkInterfaceSend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UpdateNetworkInterface {
    private static final Logger log = LoggerFactory.getLogger(CreateAggregateInterface.class);

    public static NetworkInterface fromObject(NetworkInterfaceSend interf, BpConnector pearl) {
        log.info("Updating network interface " + interf.getName());

        try {
            NetworkInterface new_inter = pearl.createNetworkInterface(interf);

            if(new_inter != null) {
                log.info("Successfully updated interface " + new_inter.getName() + " [" + new_inter.getId() + "]");
                return new_inter;
            } else {
                log.error("Unable to update interface " + interf.getName());
                return null;
            }
        } catch(Exception e) {
            log.error(e.getMessage());
            log.error("Failed to update network interface " + interf.getName());
            return null;
        }
    }
}
