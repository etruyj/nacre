//===================================================================
// SetHostname.java
//      Description:
//          The purpose of this class is to set the BlackPearl's
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

public class SetHostname {
    private static final Logger log = LoggerFactory.getLogger(SetHostname.class);

    public static boolean fromString(String hostname, BpConnector pearl) {
        log.info("Setting BlackPearl hostname to " + hostname);

        BlackPearlNode node = ListNodes.active(pearl);

        if(node != null) {
            try {
                node.setName(hostname);

                node = pearl.updateNode(node);

                if(node.getName().equals(hostname)) {
                    log.info("BlackPearl hostname is now " + hostname);
                    return true;
                } else {
                    log.info("Failed to update BlackPearl hostname.");
                    return false;
                }
            } catch(Exception e) {
                log.error(e.getMessage());
                log.error("Failed to set hostname.");
                return false;
            }
        } else {
            log.error("Unable to identify the active node.");
            return false;
        }
    }
}
