//===================================================================
// ActivationKey.java
//      Description:
//          This model holds the information related to BlackPearl
//          activation keys.
//
// Created by Sean Snyder
// Copyright Spectra Logic
//===================================================================

package com.spectralogic.blackpearl.nacre.model;

import com.google.gson.annotations.SerializedName;

public class ActivationKey {
    @SerializedName("raw_key")
    private String raw_key;

    //===========================================
    // Getters
    //===========================================
    public String getRawKey() { return raw_key; }
    
    //===========================================
    // Setters
    //===========================================
    public void setRawKey(String key) { this.raw_key = key; }
}
