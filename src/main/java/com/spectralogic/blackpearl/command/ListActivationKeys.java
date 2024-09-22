//===================================================================
// ListActivationKeys.java
//      Description:
//          This command provides a list of all the activation keys in a
//          BlackPearl.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.command;

import com.spectralogic.blackpearl.nacre.api.BpConnector;
import com.spectralogic.blackpearl.nacre.model.ActivationKey;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ListActivationKeys {
    private static final Logger log = LoggerFactory.getLogger(ListActivationKeys.class);

    public static ArrayList<ActivationKey> all(BpConnector pearl) {
        log.info("Listing all activation keys in the BlackPearl.");

        ArrayList<ActivationKey> key_list = new ArrayList<ActivationKey>();
        try {
            key_list = pearl.listActivationKeys();

            log.info("Found (" + key_list.size() + ") activation keys.");
        } catch(Exception e) {
            log.error(e.getMessage());
            log.error("Failed to list activation keys.");
        }

        return key_list;
    }
}
