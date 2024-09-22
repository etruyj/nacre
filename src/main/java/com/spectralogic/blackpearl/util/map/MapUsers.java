//===================================================================
// MapUsers.java
//      Description:
//          This class creates maps of information related to the
//          user.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.util.map;

import com.spectralogic.blackpearl.nacre.model.Ds3User;
import java.util.ArrayList;
import java.util.HashMap;

public class MapUsers {
    public static HashMap<String, String> createNameIdMap(ArrayList<Ds3User> user_list) {
        HashMap<String, String> user_map = new HashMap<String, String>();

        for(Ds3User user : user_list) {
            user_map.put(user.getName(), String.valueOf(user.getId()));
        }

        return user_map;
    }
    
    public static HashMap<String, String> createUsernameIdMap(ArrayList<Ds3User> user_list) {
        HashMap<String, String> user_map = new HashMap<String, String>();

        for(Ds3User user : user_list) {
            user_map.put(user.getUsername(), String.valueOf(user.getId()));
        }

        return user_map;
    }
}
