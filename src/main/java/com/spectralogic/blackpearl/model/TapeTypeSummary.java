//===================================================================
// TapeTypeSummary.java
//      Description:
//          A model class to hold tape type summaries for the tape
//      partition model.
// 
// Created by Sean Snyder
// Copyright Spectra Logic Corporation 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.model;

import com.google.gson.annotations.SerializedName;

public class TapeTypeSummary {
    @SerializedName("available_storage_capacity")
    private long available_storage_capacity;
    private int count;
    @SerializedName("total_storage_capacity")
    private long total_storage_capacity;
    private String type;
    @SerializedName("used_storage_capacity")
    private long used_storage_capacity;
    
    //===========================================
    // Gettors
    //===========================================
    public long getAvailableStorageCapacity() { return available_storage_capacity; }
    public int getCount() { return count; }
    public long getTotalStorageCapacity() { return total_storage_capacity; }
    public String getType() { return type; }
    public long getUsedStorageCapacity() { return used_storage_capacity; }
    
    //===========================================
    // Settors
    //===========================================
    public void setAvailableStorageCapacity(long available_storage_capacity) { this.available_storage_capacity = available_storage_capacity; }
    public void setCount(int count) { this.count = count; }
    public void setTotalStorageCapacity(long total_storage_capacity) { this.total_storage_capacity = total_storage_capacity; }
    public void setType(String type) { this.type = type; }
    public void setUsedStorageCapacity(long used_storage_capacity) { this.used_storage_capacity = used_storage_capacity; }

}
