//===================================================================
// MapStorageDomains.java
//      Description:
//          This class creates maps of information related to the
//          storage domain.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.util.map;

import com.spectralogic.blackpearl.nacre.model.StorageDomain;
import java.util.ArrayList;
import java.util.HashMap;

public class MapStorageDomains {
    public static HashMap<String, String> createNameIdMap(ArrayList<StorageDomain> domain_list) {
        HashMap<String, String> sd_map = new HashMap<String, String>();

        for(StorageDomain domain : domain_list) {
            sd_map.put(domain.getName(), domain.getId());
        }

        return sd_map;
    }
}
