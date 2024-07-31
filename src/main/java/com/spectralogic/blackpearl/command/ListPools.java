//===================================================================
// ListPools.java
//      Description:
//          This class handles commands associated with list pools.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.command;

import com.spectralogic.blackpearl.nacre.api.BpConnector;
import com.spectralogic.blackpearl.nacre.model.Pool;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ListPools {
    private static final Logger log = LoggerFactory.getLogger(ListPools.class);

    public static ArrayList<Pool> all(BpConnector pearl) {
        log.info("Listing all pools in the BlackPearl.");

        ArrayList<Pool> pool_list = new ArrayList<Pool>();

        try {
            pool_list = pearl.listPools();

            log.info("Found (" + pool_list.size() + ") pools.");
        } catch(Exception e) {
            log.error(e.getMessage());
            log.error("Failed to list pools.");
        }

        return pool_list;
    }
}
