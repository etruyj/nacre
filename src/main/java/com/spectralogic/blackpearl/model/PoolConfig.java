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
    @SerializedName("preferred_stripe_size")
    private Integer preferred_stripe_size;
    @SerializedName("protection_level")
    private String protection_level;
    @SerializedName("pool_allocation")
    private String pool_allocation; 
    @SerializedName("stripe_increment")
    private Integer stripe_increment;
    @SerializedName("max_spare_drives")
    private Integer max_spare_drives;
    @SerializedName("write_performance_drives")
    private Boolean write_performance_drives;
    @SerializedName("metadata_drives")
    private Boolean metadata_drives;
    @SerializedName("drive_count")
    private Integer drive_count;
    @SerializedName("stripe_name")
    private String stripe_name;

    //===========================================
    // Constructors
    //===========================================
    public PoolConfig() {} // blank constructor to allow for a copy constructor.

    public PoolConfig(PoolConfig pool) {
        super(pool);
        this.setName(pool.getName());
        this.setPreferredStripeSize(pool.getPreferredStripeSize());
        this.setProtectionLevel(pool.getProtectionLevel());
        this.setPoolAllocation(pool.getPoolAllocation());
        this.setStripeIncrement(pool.getStripeIncrement());
        this.setMaxSpareDrives(pool.getMaxSpareDrives());
        this.setWritePerformanceDrives(pool.isWritePerformanceDrives());
        this.setMetadataDrives(pool.isMetadataDrives());
        this.setDriveCount(pool.getDriveCount());
        this.setStripeName(pool.getStripeName());
    }

    //===========================================
    // Getters
    //===========================================
    public Integer getPreferredStripeSize() { return preferred_stripe_size; }
    public String getProtectionLevel() { return protection_level; }
    public String getPoolAllocation() { return pool_allocation; }
    public Integer getStripeIncrement() { return stripe_increment; }
    public Integer getMaxSpareDrives() { return max_spare_drives; }
    public Boolean isWritePerformanceDrives() { return write_performance_drives; }
    public Boolean isMetadataDrives() { return metadata_drives; }
    public Integer getDriveCount() { return drive_count; }
    public String getStripeName() { return stripe_name; }

    //===========================================
    // Setters
    //===========================================
    public void setPreferredStripeSize(Integer stripe_size) { this.preferred_stripe_size = stripe_size; }
    public void setProtectionLevel(String level) { this.protection_level = level; }
    public void setPoolAllocation(String allocation) { this.pool_allocation = allocation; }
    public void setStripeIncrement(Integer increment) { this.stripe_increment = increment; }
    public void setMaxSpareDrives(Integer max) { this.max_spare_drives = max; }
    public void setWritePerformanceDrives(Boolean has_drives) { this.write_performance_drives = has_drives; }
    public void setMetadataDrives(Boolean has_drives) { this.metadata_drives = has_drives; }
    public void setDriveCount(Integer drives) { this.drive_count = drives; }
    public void setStripeName(String name) { this.stripe_name = name; }
}

