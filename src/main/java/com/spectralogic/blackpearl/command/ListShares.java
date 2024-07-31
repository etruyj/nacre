//===================================================================
// ListShares.java
//      Description:
//          This command provides a list of all the  Shares in a
//          BlackPearl.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.command;

import com.spectralogic.blackpearl.nacre.api.BpConnector;
import com.spectralogic.blackpearl.nacre.model.Share;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ListShares {
    private static final Logger log = LoggerFactory.getLogger(ListShares.class);

    public static ArrayList<Share> all(BpConnector pearl) {
        log.info("Listing all shares in the BlackPearl.");

        ArrayList<Share> share_list = new ArrayList<Share>();
        try {
            share_list = pearl.listShares();

            log.info("Found (" + share_list.size() + ") shares.");
        } catch(Exception e) {
            log.error(e.getMessage());
            log.error("Failed to list shares.");
        }

        return share_list;
    }
}
