//===================================================================
// VolumeConfig.java
//      Description:
//          This model holds simplified configuration information
//          in the config file to make it easier users to configure
//          the BlackPearl.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.model;

import com.google.gson.annotations.SerializedName;

public class VolumeConfig extends Volume {
    private String pool;
    @SerializedName("max_size")
    private String max_size;
    @SerializedName("min_size")
    private String min_size;

    //===========================================
    // Constructors
    //===========================================
    public VolumeConfig() {} // blank constructor to allow for a copy constructor

    public VolumeConfig(VolumeConfig vol) {
        super(vol);
    
        this.setPool(vol.getPool());
        this.setMaxSize(vol.getMaxSize());
        this.setMinSize(vol.getMinSize());
    }

    public VolumeConfig(Volume vol) {
        super(vol);
    }
    
    //===========================================
    // Getters
    //===========================================
    public String getPool() { return pool; }
    public String getMaxSize() { return max_size; }
    public String getMinSize() { return min_size; }

    //===========================================
    // Setters
    //===========================================
    public void setPool(String pool) { this.pool = pool; }
    public void setMaxSize(String max) { this.max_size = max; }
    public void setMinSize(String min) { this.min_size = min; }

}
