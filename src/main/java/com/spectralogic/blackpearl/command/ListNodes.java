//===================================================================
// ListNodes.java
//      Description:
//          This class handles commands associated with list nodes.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.command;

import com.spectralogic.blackpearl.nacre.api.BpConnector;
import com.spectralogic.blackpearl.nacre.model.BlackPearlNode;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ListNodes {
    private static final Logger log = LoggerFactory.getLogger(ListNodes.class);

    public static BlackPearlNode active(BpConnector pearl) {
        log.info("Searching for the active node in the BlackPearl.");

        ArrayList<BlackPearlNode> node_list = new ArrayList<BlackPearlNode>();

        try {
            node_list = pearl.listNodes();

            log.info("Found (" + node_list.size() + ") nodes.");
        
            if(node_list.size() == 1) {
                return node_list.get(0);
            } else {
                for(BlackPearlNode node : node_list) {
                    if(node.getHotpairStatus().getState().equals("active")) {
                        return node;
                    }
                }
            }
        } catch(Exception e) {
            log.error(e.getMessage());
            log.error("Failed to list nodes.");
        }

        log.warn("Unable to find active node.");

        return null;
    }
    public static ArrayList<BlackPearlNode> all(BpConnector pearl) {
        log.info("Listing all nodes in the BlackPearl.");

        ArrayList<BlackPearlNode> node_list = new ArrayList<BlackPearlNode>();

        try {
            node_list = pearl.listNodes();

            log.info("Found (" + node_list.size() + ") nodes.");
        } catch(Exception e) {
            log.error(e.getMessage());
            log.error("Failed to list nodes.");
        }

        return node_list;
    }
}
