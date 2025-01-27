//===================================================================
// GetHostname.java
//      Description:
//          The purpose of this class is to get the BlackPearl's
//          hostname.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.command;

import com.spectralogic.blackpearl.nacre.api.BpConnector;
import com.spectralogic.blackpearl.nacre.model.BlackPearlNode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetHostname {
    private static final Logger log = LoggerFactory.getLogger(SetHostname.class);

    public static String fromNode(BpConnector pearl) {
        log.info("Querying BlackPearl for the system hostname.");

        BlackPearlNode node = ListNodes.active(pearl);

        if(node != null) {
            try {
                log.info("BlackPearl hostname is " + node.getName());
                return node.getName();
            } catch(Exception e) {
                log.error(e.getMessage());
                log.error("Failed to get hostname.");
                return "blackpearl";
            }
        } else {
            log.error("Unable to identify the active node.");
            return "blackpearl";
        }
    }
}
