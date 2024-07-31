//===================================================================
// ShareConfig.java
//      Description:
//          This model holds the configuration information for a
//          BlackPearl NAS Share.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.model;

import com.google.gson.annotations.SerializedName;

public class ShareConfig extends Share {
    private String volume;
    @SerializedName("min_size")
    private Long min_size;
    @SerializedName("max_size")
    private Long max_size;
    @SerializedName("already_exists")
    private boolean already_exists;

    public ShareConfig() {} // blank to allow for a copy constructor

    public ShareConfig(ShareConfig share) {
        super(share);

        this.setVolume(share.getVolume());
        this.setMinSize(share.getMinSize());
        this.setMaxSize(share.getMaxSize());
        this.setAlreadyExists(share.isAlreadyExists());
    }

    //===========================================
    // Getters
    //===========================================
    public String getVolume() { return volume; }
    public Long getMinSize() { return min_size; }
    public Long getMaxSize() { return max_size; }
    public boolean isAlreadyExists() { return already_exists; }
    
    //===========================================
    // Setters
    //===========================================
    public void setVolume(String vol) { this.volume = vol; }
    public void setMinSize(Long min) { this.min_size = min; }
    public void setMaxSize(Long max) { this.max_size = max; }
    public void setAlreadyExists(boolean exists) { this.already_exists = exists; }
}
