//===================================================================
// ListTapes.java
//      Description:
//          This class handles commands associated with list tapes.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.command;

import com.spectralogic.blackpearl.nacre.api.BpConnector;
import com.spectralogic.blackpearl.nacre.model.Ds3Bucket;
import com.spectralogic.blackpearl.nacre.model.Tape;
import com.spectralogic.blackpearl.nacre.util.map.MapBuckets;

import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ListTapes {
    private static final Logger log = LoggerFactory.getLogger(ListTapes.class);

    public static ArrayList<Tape> all(BpConnector pearl) {
        log.info("Listing all tapes in the BlackPearl.");

        ArrayList<Tape> tape_list = new ArrayList<Tape>();

        try {
            tape_list = pearl.listTapes();

            log.info("Found (" + tape_list.size() + ") tapes.");
        } catch(Exception e) {
            log.error(e.getMessage());
            log.error("Failed to list tapes.");
        }

        return tape_list;
    }

    public static void byBucket(BpConnector pearl) {
        log.info("Grouping tapes by bucket...");

        ArrayList<Ds3Bucket> bucket_list = ListBuckets.all(pearl);
        HashMap<String, String> id_name_map = MapBuckets.createIdNameMap(bucket_list);
        HashMap<String, Integer> bucket_tapes_map = new HashMap<String, Integer>();

        int tape_count;

        try {
            ArrayList<Tape> tape_list = all(pearl);
            
            for(Tape tape : tape_list) {
                if(bucket_tapes_map.get(tape.getBucketId()) != null) {
                    tape_count = bucket_tapes_map.get(tape.getBucketId());
                    tape_count++;
                    bucket_tapes_map.put(tape.getBucketId(), tape_count);
                } else {
                    tape_count = 1;
                    bucket_tapes_map.put(tape.getBucketId(), tape_count);
                }                
            }

            for(String key : bucket_tapes_map.keySet()) {
                System.out.println(id_name_map.get(key) + ": " + bucket_tapes_map.get(key));
            }
            
        } catch(Exception e) {
            log.error(e.getMessage());
            log.error("Failed to sum tapes by bucket...");
        }
    }

    public static ArrayList<Tape> byState(String state, BpConnector pearl) {
        ArrayList<Tape> all_tapes = all(pearl);
        ArrayList<Tape> filtered_list = new ArrayList<Tape>();

        log.info("Filtering list of tapes based on tape state [" + state + "]");
        
        for(Tape tape : all_tapes) {
            if(tape.getState().equals(state)) {
                filtered_list.add(tape);
            }
        }

        log.info("Found (" + filtered_list.size() + ") " + state + " tapes");

        for(Tape tape : filtered_list) {
            System.err.println("[" + tape.getBarCode() + "]: " + tape.getState());
        }

        return filtered_list;
    }
}
