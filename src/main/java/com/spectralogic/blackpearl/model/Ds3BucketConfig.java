//===================================================================
// Ds3BucketConfig.java
//      Description:
//          This class holds the configuration information for 
//          BlackPearl NearlineGateway buckets.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.model;

import com.google.gson.annotations.SerializedName;

public class Ds3BucketConfig extends Ds3Bucket{
    private String owner;
    @SerializedName("data_policy")
    private String data_policy;
    @SerializedName("tape_copies")
    private int tape_copies;
    @SerializedName("osd_copies")
    private int osd_copies;
    @SerializedName("osd_duration")
    private int osd_duration;
    @SerializedName("bucket_isolated")
    private boolean bucket_isolated;
    @SerializedName("already_exists")
    private boolean already_exists;

    //===========================================
    // Getters
    //===========================================
    public String getDataPolicy() { return data_policy; }
    public String getOwner() { return owner; }
    public int getTapeCopies() { return tape_copies; }
    public int getOsdCopies() { return osd_copies; }
    public int getOsdDuration() { return osd_duration; }
    public boolean isBucketIsolated() { return bucket_isolated; }
    public boolean isAlreadyExists() { return already_exists; }

    //===========================================
    // Setters
    //===========================================
    public void setDataPolicy(String policy) { this.data_policy = policy; }
    public void setOwner(String owner) { this.owner = owner; }
    public void setTapeCopies(int copies) { this.tape_copies = copies; }
    public void setOsdCopies(int copies) { this.osd_copies = copies; }
    public void setOsdDuration(int days) { this.osd_duration = days; }
    public void setBucketIsolated(boolean isolated) { this.bucket_isolated = isolated; }
    public void setAlreadyExists(boolean exists) { this.already_exists = exists; }
}
