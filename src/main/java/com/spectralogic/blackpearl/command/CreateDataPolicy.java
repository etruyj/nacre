//===================================================================
// CreateDataPolicy.java
//      Description:
//          This class creates a data policy in the BlackPearl.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.command;

import com.spectralogic.blackpearl.nacre.api.BpConnector;
import com.spectralogic.blackpearl.nacre.model.DataPolicy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateDataPolicy {
    private static final Logger log = LoggerFactory.getLogger(CreateDataPolicy.class);

    public static DataPolicy fromObject(DataPolicy policy, BpConnector pearl) {
        log.info("Creating data policy [" + policy.getName() + "]");
        DataPolicy result_policy = null;

        try {
            result_policy = pearl.createDataPolicy(policy);
            log.info("Successfully created a data policy.");
        } catch(Exception e) {
            log.error(e.getMessage());
            log.error("Failed to create the data policy [" + policy.getName() + "]");
        }

        return result_policy;
    }
}
