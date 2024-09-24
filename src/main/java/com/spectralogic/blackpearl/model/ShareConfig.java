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

import java.lang.StringBuilder;
import java.util.ArrayList;

public class ShareConfig extends Share {
    private String volume;
    @SerializedName("min_size")
    private Long min_size;
    @SerializedName("max_size")
    private Long max_size;
    @SerializedName("already_exists")
    private boolean already_exists;
    private ArrayList<String> permissions;

    public ShareConfig() {
        permissions = new ArrayList<String>();
    } // blank to allow for a copy constructor

    public ShareConfig(ShareConfig share) {
        super(share);

        this.setVolume(share.getVolume());
        this.setMinSize(share.getMinSize());
        this.setMaxSize(share.getMaxSize());
        this.setAlreadyExists(share.isAlreadyExists());
        this.setPermissions(share.getPermissions());
    }

    public ShareConfig(Share share) {
        super(share);
    }

    //===========================================
    // Getters
    //===========================================
    public String getVolume() { return volume; }
    public Long getMinSize() { return min_size; }
    public Long getMaxSize() { return max_size; }
    public boolean isAlreadyExists() { return already_exists; }
    public ArrayList<String> getPermissions() { return permissions; }
    public String getPermissionsString() {
        StringBuilder control = new StringBuilder();

        for(String line : permissions) {
            control.append(line);
            control.append("\n");
        }

        return control.toString().substring(0, control.length()-1); // return all but the last endline character.
    }

    //===========================================
    // Setters
    //===========================================
    public void setVolume(String vol) { this.volume = vol; }
    public void setMinSize(Long min) { this.min_size = min; }
    public void setMaxSize(Long max) { this.max_size = max; }
    public void setAlreadyExists(boolean exists) { this.already_exists = exists; }
    public void setPermissions(ArrayList<String> permissions) { this.permissions = permissions; }
}
