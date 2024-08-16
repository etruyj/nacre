//===================================================================
// CreateDs3Bucket.java
//      Description:
//          This class handles the command necessary to create the
//          a new Ds3Bucket.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.command;

import com.spectralogic.blackpearl.nacre.api.BpConnector;
import com.spectralogic.blackpearl.nacre.model.Ds3Bucket;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateDs3Bucket {
    private static final Logger log = LoggerFactory.getLogger(CreateDs3Bucket.class);

    public static Ds3Bucket fromObject(Ds3Bucket bucket, BpConnector pearl) {
        log.info("Creating DS3 bucket [" + bucket.getName() + "]");

        Ds3Bucket new_bucket = null;

        try {
            new_bucket = pearl.createDs3Bucket(bucket);
        } catch(Exception e) {
            log.error(e.getMessage());
            log.error("Failed to create DS3 bucket [" + bucket.getName() + "]");
        }

        return new_bucket;
    }
}
