//===================================================================
// DiskDrive.java
//      Description:
//          This is the model for a BlackPearl disk drive.
//
// Created by Sean Snyder
// Copyright Spectra Logic Corporation 2024
//===================================================================

package com.spectralogic.vail.configurator.model.blackpearl.disk;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class DiskDrive {
    private ArrayList<String> children;
    @SerializedName("disk_id")
    private String disk_id;
    private long size;
    private String type;

    //===========================================
    // Gettors
    //===========================================
    public ArrayList<String> getChildren() { return children; }
    public String getDiskId() { return disk_id; }
    public long getSize() { return size; }
    public String getType() { return type; }

    //===========================================
    // Settors
    //===========================================

    public void setChildren(ArrayList<String> children) { this.children = children; }
    public void setDiskId(String disk_id) { this.disk_id = disk_id; }
    public void setSize(long size) { this.size = size; }
    public void setType(String type) { this.type = type; }
}
