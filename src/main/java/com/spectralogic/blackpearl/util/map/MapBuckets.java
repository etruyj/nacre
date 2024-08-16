//===================================================================
// MapBuckets.java
//      Description:
//          This class creates maps related to tape buckettitions.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.util.map;

import com.spectralogic.blackpearl.nacre.model.Ds3Bucket;

import java.util.ArrayList;
import java.util.HashMap;

public class MapBuckets {
    public static HashMap<String, String> createIdNameMap(ArrayList<Ds3Bucket> bucket_list) {
        HashMap<String, String> bucket_map = new HashMap<String, String>();

        for(Ds3Bucket bucket : bucket_list) {
            bucket_map.put(bucket.getId(), bucket.getName());
        }

        return bucket_map;
    }
    
    public static HashMap<String, Ds3Bucket> createNameObjectMap(ArrayList<Ds3Bucket> bucket_list) {
        HashMap<String, Ds3Bucket> bucket_map = new HashMap<String, Ds3Bucket>();

        for(Ds3Bucket bucket : bucket_list) {
            bucket_map.put(bucket.getName(), bucket);
        }

        return bucket_map;
    }
}
