//===================================================================
// GetDs3Pool.java
//      Description:
//          This class handles commands associated with retrieving a
//          ds3 pool.
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

public class GetDs3Pool {
    private static final Logger log = LoggerFactory.getLogger(GetDs3Pool.class);

    public static Pool byId(String pool_id, BpConnector pearl) {
        log.info("Retrieving pool [" + pool_id + "] from the BlackPearl.");

        Pool pool = null;
        try {
            pool = pearl.getDs3Pool(pool_id);

            log.info("Found " + pool.getName());
        } catch(Exception e) {
            log.error(e.getMessage());
            log.error("Failed to retrieve pool " + pool_id + ".");
        }

        return pool;
    }
}
