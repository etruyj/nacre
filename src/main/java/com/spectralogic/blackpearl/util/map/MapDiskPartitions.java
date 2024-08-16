//===================================================================
// MapDiskPartitions.java
//      Description:
//          This class creates maps related to tape disktitions.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.util.map;

import com.spectralogic.blackpearl.nacre.model.DiskPartition;

import java.util.ArrayList;
import java.util.HashMap;

public class MapDiskPartitions {
    public static HashMap<String, DiskPartition> createNameObjectMap(ArrayList<DiskPartition> disk_list) {
        HashMap<String, DiskPartition> disk_map = new HashMap<String, DiskPartition>();

        for(DiskPartition disk : disk_list) {
            disk_map.put(disk.getName(), disk);
        }

        return disk_map;
    }
}
