//===================================================================
// MapNasPools.java
//      Description:
//          This class creates maps of information related to the
//          NAS pool.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.util.map;

import com.spectralogic.blackpearl.nacre.model.Pool;
import java.util.ArrayList;
import java.util.HashMap;

public class MapNasPools {
    public static HashMap<String, String> createNameIdMap(ArrayList<Pool> pool_list) {
        HashMap<String, String> pool_map = new HashMap<String, String>();

        for(Pool pool : pool_list) {
            pool_map.put(pool.getName(), pool.getId());
        }

        return pool_map;
    }
}
