//===================================================================
// ListBuckets.java
//      Description:
//          This command provides a list of all the Ds3 Buckets in a
//          BlackPearl.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.command;

import com.spectralogic.blackpearl.nacre.api.BpConnector;
import com.spectralogic.blackpearl.nacre.model.Ds3Bucket;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ListBuckets {
    private static final Logger log = LoggerFactory.getLogger(ListBuckets.class);

    public static ArrayList<Ds3Bucket> all(BpConnector pearl) {
        log.info("Listing all buckets in the BlackPearl.");

        ArrayList<Ds3Bucket> bucket_list = new ArrayList<Ds3Bucket>();
        try {
            bucket_list = pearl.listBuckets();

            log.info("Found (" + bucket_list.size() + ") buckets.");
        } catch(Exception e) {
            log.error(e.getMessage());
            log.error("Failed to list buckets.");
        }

        return bucket_list;
    }
}
