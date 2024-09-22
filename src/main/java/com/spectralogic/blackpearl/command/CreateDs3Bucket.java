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
import com.spectralogic.blackpearl.nacre.model.DataPolicy;
import com.spectralogic.blackpearl.nacre.util.map.MapDataPolicies;

import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateDs3Bucket {
    private static final Logger log = LoggerFactory.getLogger(CreateDs3Bucket.class);

    public static Ds3Bucket forDatabase(String data_policy, BpConnector pearl) {
        log.info("Creating DS3 bucket for database backups.");

        Ds3Bucket new_bucket = null;

        try {
            log.info("Searching for data policy id for " + data_policy);
            ArrayList<DataPolicy> policy_list = ListDataPolicies.all(pearl);
            HashMap<String, String> policy_map = MapDataPolicies.createNameIdMap(policy_list);
            
            String data_policy_id = "";

            if(policy_map.get(data_policy) != null) {
                data_policy_id = policy_map.get(data_policy);
            } else {
                throw new Exception("Unable to find data policy [" + data_policy + "]");
            }

            new_bucket = new Ds3Bucket();

            new_bucket.setDataPolicyId(data_policy_id);
            new_bucket.setOperation("create_db_backup_bucket");

            new_bucket = pearl.createDs3Bucket(new_bucket);

            log.info("Created database backup bucket [" + new_bucket.getName() + "]");
        } catch(Exception e) {
            log.error(e.getMessage());
            log.error("Failed to create database backup bucket.");
        }

        return new_bucket;
    }

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
