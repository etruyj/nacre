//===================================================================
// AddDataPersistenceRule.java
//      Description:
//          This class handles commands to add data persistence rules
//          to the BlackPearl.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.command;

import com.spectralogic.blackpearl.nacre.api.BpConnector;
import com.spectralogic.blackpearl.nacre.model.DataPersistenceRule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddDataPersistenceRule {
    private static final Logger log = LoggerFactory.getLogger(AddDataPersistenceRule.class);

    public static DataPersistenceRule fromObject(DataPersistenceRule rule, BpConnector pearl) {
        log.info("Adding data persistence rule for data policy [" + rule.getDataPolicyId() + "] and storage domain [" + rule.getStorageDomainId() + "] with isolation level: " + rule.getIsolationLevel());

        DataPersistenceRule new_rule = null;
        try {
            new_rule = pearl.addDataPersistenceRule(rule);
        } catch(Exception e) {
            log.error(e.getMessage());
            log.error("Failed to add data persistence rule to data policy");
        }

        return new_rule;
    }
}
