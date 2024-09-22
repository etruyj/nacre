//===================================================================
// CreateAggregateInterface.java
//      Description:
//          The purpose of this class is to create a new aggregate
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

public class CreateAggregateInterface {
    private static final Logger log = LoggerFactory.getLogger(CreateAggregateInterface.class);

    public static NetworkInterface fromObject(NetworkInterfaceSend interf, BpConnector pearl) {
        log.info("Creating new aggregate network interface.");

        try {
            NetworkInterface new_inter = pearl.createNetworkInterface(interf);

            if(new_inter != null) {
                log.info("Successfully created new aggregate interface.");
                return new_inter;
            } else {
                log.error("Unable to create new aggregate interface.");
                return null;
            }
        } catch(Exception e) {
            log.error(e.getMessage());
            log.error("Failed to create aggregate interface.");
            return null;
        }
    }
}
