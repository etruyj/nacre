//===================================================================
// MapVolumes.java
//      Description:
//          This class creates maps of information related to the
//          volume.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.util.map;

import com.spectralogic.blackpearl.nacre.model.Volume;
import java.util.ArrayList;
import java.util.HashMap;

public class MapVolumes {
    public static HashMap<String, String> createNameIdMap(ArrayList<Volume> volume_list) {
        HashMap<String, String> vol_map = new HashMap<String, String>();

        for(Volume volume : volume_list) {
            vol_map.put(volume.getName(), volume.getId());
        }

        return vol_map;
    }
}
