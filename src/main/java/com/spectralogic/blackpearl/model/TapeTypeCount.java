//===================================================================
// TapeTypeCount.java
//      Description:
//          A model to hold the inner class tape type count for the
//      tape partition model.
// 
// Created by Sean Snyder
// Copyright Spectra Logic Corporation 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.model;

import com.google.gson.annotations.SerializedName;

public class TapeTypeCount {
    private int count;
    @SerializedName("full_of_data")
    private int full_of_data;
    private String type;
    
    //===========================================
    // Gettors
    //===========================================
    public int getCount() { return count; }
    public int getFullOfData() { return full_of_data; }
    public String getType() { return type; }
    
    //===========================================
    // Settors
    //===========================================
    public void setCount(int count) { this.count = count; }
    public void setFullOfData(int full_of_data) { this.full_of_data = full_of_data; }
    public void setType(String type) { this.type = type; }

}
