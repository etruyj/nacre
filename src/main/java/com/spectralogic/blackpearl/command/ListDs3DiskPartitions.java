//===================================================================
// ListDs3DiskPartitions.java
//      Description:
//          This command provides a list of all the Ds3 Ds3DiskPartitions in a
//          BlackPearl.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.command;

import com.spectralogic.blackpearl.nacre.api.BpConnector;
import com.spectralogic.blackpearl.nacre.model.DiskPartition;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ListDs3DiskPartitions {
    private static final Logger log = LoggerFactory.getLogger(ListDs3DiskPartitions.class);

    public static ArrayList<DiskPartition> all(BpConnector pearl) {
        log.info("Listing all DS3 pool partitions in the BlackPearl.");

        ArrayList<DiskPartition> pool_list = new ArrayList<DiskPartition>();
        try {
            pool_list = pearl.listDiskPartitions();

            log.info("Found (" + pool_list.size() + ") pool partitions.");
        } catch(Exception e) {
            log.error(e.getMessage());
            log.error("Failed to list pool partitions.");
        }

        return pool_list;
    }
}
