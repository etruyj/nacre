//===================================================================
// MapDataPolicies.java
//      Description:
//          This class creates maps of information related to the
//          data policy.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.util.map;

import com.spectralogic.blackpearl.nacre.model.DataPolicy;
import java.util.ArrayList;
import java.util.HashMap;

public class MapDataPolicies {
    public static HashMap<String, String> createNameIdMap(ArrayList<DataPolicy> policy_list) {
        HashMap<String, String> policy_map = new HashMap<String, String>();

        for(DataPolicy policy : policy_list) {
            policy_map.put(policy.getName(), policy.getId());
        }

        return policy_map;
    }
}
