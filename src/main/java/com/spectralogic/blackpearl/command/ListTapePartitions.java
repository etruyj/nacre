//===================================================================
// ListTapePartitions.java
//      Description:
//          This class handles any functions associated with listing
//          tape partitions.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.command;

import com.spectralogic.blackpearl.nacre.api.BpConnector;
import com.spectralogic.blackpearl.nacre.model.TapePartition;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ListTapePartitions {
    private static final Logger log = LoggerFactory.getLogger(ListTapePartitions.class);

    public static ArrayList<TapePartition> all(BpConnector pearl) {
        log.info("Retrieving a list of all tape partitions attached to the BlackPearl.");

        ArrayList<TapePartition> partition_list = new ArrayList<TapePartition>();
        try {
            partition_list = pearl.listTapePartitions();

            log.info("Found (" + partition_list.size() + ") tape partitions.");
        } catch(Exception e) {
            log.error(e.getMessage());
            log.error("Failed to fetch tape partitions.");
        }

        return partition_list;
    }
}
