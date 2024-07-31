//===================================================================
// ListDataPolicies.java
//      Description:
//          This class handles commands related to listing data
//          policies.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.command;

import com.spectralogic.blackpearl.nacre.api.BpConnector;
import com.spectralogic.blackpearl.nacre.model.DataPolicy;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ListDataPolicies {
    private static final Logger log = LoggerFactory.getLogger(ListDataPolicies.class);

    public static ArrayList<DataPolicy> all(BpConnector pearl) {
        ArrayList<DataPolicy> policy_list = new ArrayList<DataPolicy>();

        log.info("Requesting a list of all data policies in the BlackPearl.");
        try {
            policy_list = pearl.listDataPolicies();

            log.info("Found (" + policy_list.size() + ") data policies.");
        } catch(Exception e) {
            log.error(e.getMessage());
            log.error("Failed to list data policies.");
        }

        return policy_list;
    }
}
