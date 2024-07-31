//======================================================================
//  DiskPartition
//      Description:  
//          This class represents the structure of a pool JSON 
//          object with various attributes such as name, poolIds, 
//          and type.
//          
// Created by Sean Snyder 
// Copyright Spectra Logic Corporation.
//======================================================================

package com.spectralogic.blackpearl.nacre.model;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class DiskPartition {
    private String name;
    @SerializedName("pool_ids")
    private ArrayList<String> poolIds;
    private String type;

    //===========================================
    // Getters
    //===========================================
    public String getName() { return name; }
    public ArrayList<String> getPoolIds() { return poolIds; }
    public String getType() { return type; }

    //===========================================
    // Setters
    //===========================================
    public void setName(String name) { this.name = name; }
    public void setPoolIds(ArrayList<String> poolIds) { this.poolIds = poolIds; }
    public void setType(String type) { this.type = type; }
}

