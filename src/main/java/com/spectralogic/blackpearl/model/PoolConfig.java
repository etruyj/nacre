//===================================================================
// PoolConfig.java
//      Description:
//          This model holds the default settings for a BlackPearl
//          pool. These settings extend the regular pool settings to
//          allow for the logic to create pool configurations.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.model;

import com.google.gson.annotations.SerializedName;

public class PoolConfig extends Pool {
    @SerializedName("drive_type")
    private String drive_type;
    @SerializedName("drive_count")
    private Integer drive_count;
    @SerializedName("stripe_name")
    private String stripe_name; // this is used for OSD pools.

    //===========================================
    // Constructors
    //===========================================
    public PoolConfig() {} // blank constructor to allow for a copy constructor.

    public PoolConfig(PoolConfig pool) {
        super(pool);
        this.setName(pool.getName());
        this.setDriveCount(pool.getDriveCount());
        this.setStripeName(pool.getStripeName());
    }

    //===========================================
    // Getters
    //===========================================
    public String getDriveType() { return drive_type; }
    public Integer getDriveCount() { return drive_count; }
    // This get method was included in the original code and persisted
    // here to simplify the code change.
    public String getProtectionLevel() { return this.getProtection(); }
    public String getStripeName() { return stripe_name; }

    //===========================================
    // Setters
    //===========================================
    public void setDriveType(String type) { this.drive_type = type; }
    public void setDriveCount(Integer drives) { this.drive_count = drives; }
    public void setStripeName(String name) { this.stripe_name = name; }
}

