//===================================================================
// MapTapePartitions.java
//      Description:
//          This class creates maps related to tape partitions.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.util.map;

import com.spectralogic.blackpearl.nacre.model.TapePartition;

import java.util.ArrayList;
import java.util.HashMap;

public class MapTapePartitions {
    public static HashMap<String, TapePartition> createNameObjectMap(ArrayList<TapePartition> par_list) {
        HashMap<String, TapePartition> par_map = new HashMap<String, TapePartition>();

        for(TapePartition par : par_list) {
            par_map.put(par.getName(), par);
        }

        return par_map;
    }
}
