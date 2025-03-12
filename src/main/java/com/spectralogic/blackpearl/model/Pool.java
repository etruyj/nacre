//===================================================================
// Pool.java
//      Description:
//          This class models a disk pool.
// 
// Created by Sean Snyder
// Copyright Spectra Logic Corporation 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.model;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.Arrays;

public class Pool {
    private String id;
    private String name;
    private String protection;
    private int stripes;
    private String health;
    @SerializedName("raw_size")
    private Long raw_size;
    @SerializedName("rebuild_status")
    private String rebuild_status;
    @SerializedName("scrub_status")
    private ScrubStatus scrub_status;
    private String status;
    private Long available;
    private Long overhead;
    private Long used;
    @SerializedName("zfs_status")
    private String zfs_status;
    @SerializedName("high_water_mark")
    private Integer high_water_mark;
    @SerializedName("last_import_time")
    private String last_import_time;
    @SerializedName("created_at")
    private String created_at;
    @SerializedName("updated_at")
    private String updated_at;
    private Boolean globalhotspare;
    private Boolean autoreplace;
    @SerializedName("power_saving_mode")
    private String power_saving_mode;
    @SerializedName("special_available")
    private Long special_available;
    @SerializedName("special_used")
    private Long special_used;
    @SerializedName("disk_ids")
    private ArrayList<String> disk_ids;
    private ArrayList<Stripe> topology;
    private ArrayList<String> log;
    @SerializedName("zil_drives")
    private ArrayList<String> zil_drives;
    private ArrayList<String> special;
    @SerializedName("special_drives")
    private ArrayList<String> special_drives;
    @SerializedName("special_disk_drives")
    private ArrayList<String> special_disk_ids;
    private String type;
    @SerializedName("encryption_state")
    private String encryption_state;
    @SerializedName("ds3_pool_health")
    private String ds3_pool_health;
    @SerializedName("auto_trim")
    private Boolean auto_trim;
    @SerializedName("protection_select")
    private String protection_select;


    //===========================================
    // Constructors
    //===========================================
    public Pool() {
        this.encryption_state="Disabled";

    } // blank constructor to allow for copy constructor. 

    // Copy constructor
    public Pool(Pool other) {
        this.setId(other.getId());
        this.setName(other.getName());
        this.setProtection(other.getProtection());
        this.setStripes(other.getStripes());
        this.setHealth(other.getHealth());
        this.setRawSize(other.getRawSize());
        this.setRebuildStatus(other.getRebuildStatus());
        this.setScrubStatus(other.getScrubStatus());
        this.setStatus(other.getStatus());
        this.setAvailable(other.getAvailable());
        this.setOverhead(other.getOverhead());
        this.setUsed(other.getUsed());
        this.setZfsStatus(other.getZfsStatus());
        this.setHighWaterMark(other.getHighWaterMark());
        this.setLastImportTime(other.getLastImportTime());
        this.setCreatedAt(other.getCreatedAt());
        this.setUpdatedAt(other.getUpdatedAt());
        this.setGlobalhotspare(other.isGlobalhotspare());
        this.setAutoreplace(other.isAutoreplace());
        this.setPowerSavingMode(other.getPowerSavingMode());
        this.setSpecialAvailable(other.getSpecialAvailable());
        this.setSpecialUsed(other.getSpecialUsed());
        this.setType(other.getType());
        this.setEncryptionState(other.getEncryptionState());
        this.setDs3PoolHealth(other.getDs3PoolHealth());
        this.setProtectionSelect(other.getProtectionSelect());
        this.setAutoTrim(other.getAutoTrim());

        //=== Copy disk_ids ===
        this.disk_ids = other.getDiskIds() != null ? 
                            new ArrayList<String>(other.getDiskIds()) :
                            new ArrayList<String>();
        //=== Copy Topology ===
        this.topology = other.getTopology() != null ?
                            new ArrayList<Stripe>(other.getTopology()) :
                            new ArrayList<Stripe>();

        //=== Copy Log ===
        this.log = other.getLog() != null ?
                    new ArrayList<String>(other.getLog()) :
                    new ArrayList<String>();

        //=== Copy Zil Drives ===
        this.zil_drives = other.getZilDrives() != null ?
                            new ArrayList<String>(other.getZilDrives()) :
                            new ArrayList<String>();

        //=== Copy special ===
        this.special = other.getSpecial() != null ?
                        new ArrayList<String>(other.getSpecial()) :
                        new ArrayList<String>();

        //=== Copy special_drives ===
        this.special_drives = other.getSpecial() != null ?
                                new ArrayList<String>(other.getSpecial()) :
                                new ArrayList<String>();

        //=== Copy special_disk_ids ===
        this.special_disk_ids = other.getSpecial() != null ?
                                    new ArrayList<String>(other.getSpecialDiskIds()) :
                                    new ArrayList<String>();
    }
    
    //===========================================
    // Gettors
    //===========================================
    public String getId() { return id; }
    public String getName() { return name; }
    public String getProtection() { return protection; }
    public int getStripes() { return stripes; }
    public String getHealth() { return health; }
    public Long getRawSize() { return raw_size; }
    public String getRebuildStatus() { return rebuild_status; }
    public ScrubStatus getScrubStatus() { return scrub_status; }
    public String getStatus() { return status; }
    public Long getAvailable() { return available; }
    public Long getOverhead() { return overhead; }
    public Long getUsed() { return used; }
    public String getZfsStatus() { return zfs_status; }
    public Integer getHighWaterMark() { return high_water_mark; }
    public String getLastImportTime() { return last_import_time; }
    public String getCreatedAt() { return created_at; }
    public String getUpdatedAt() { return updated_at; }
    public Boolean isGlobalhotspare() { return globalhotspare; }
    public Boolean isAutoreplace() { return autoreplace; }
    public String getPowerSavingMode() { return power_saving_mode; }
    public Long getSpecialAvailable() { return special_available; }
    public Long getSpecialUsed() { return special_used; }
    public ArrayList<String> getDiskIds() { return disk_ids; }
    public ArrayList<Stripe> getTopology() { return topology; }
    public ArrayList<String> getLog() { return log; }
    public ArrayList<String> getZilDrives() { return zil_drives; }
    public ArrayList<String> getSpecial() { return special; }
    public ArrayList<String> getSpecialDrives() { return special_drives; }
    public ArrayList<String> getSpecialDiskIds() { return special_disk_ids; }
    public String getType() { return type; }
    public String getEncryptionState() { return encryption_state; }
    public String getDs3PoolHealth() { return ds3_pool_health; }
    public Boolean getAutoTrim() { return auto_trim; }
    public String getProtectionSelect() { return protection_select; }

    //===========================================
    // Settors
    //===========================================
    public void addDiskId(String id) { 
        if(disk_ids == null) {
            disk_ids = new ArrayList<String>();
        }

        disk_ids.add(id); 
    }
    public void addStripe(Stripe stripe) { 
        if(topology == null) {
            topology = new ArrayList<Stripe>();
        }

        topology.add(stripe); 
    }

    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setProtection(String protection) { this.protection = protection; }
    public void setStripes(int stripes) { this.stripes = stripes; }
    public void setHealth(String health) { this.health = health; }
    public void setRawSize(Long raw_size) { this.raw_size = raw_size; }
    public void setRebuildStatus(String rebuild_status) { this.rebuild_status = rebuild_status; }
    public void setScrubStatus(ScrubStatus scrub_status) { this.scrub_status = scrub_status; }
    public void setStatus(String status) { this.status = status; }
    public void setAvailable(Long available) { this.available = available; }
    public void setOverhead(Long overhead) { this.overhead = overhead; }
    public void setUsed(Long used) { this.used = used; }
    public void setZfsStatus(String zfs_status) { this.zfs_status = zfs_status; }
    public void setHighWaterMark(Integer high_water_mark) { this.high_water_mark = high_water_mark; }
    public void setLastImportTime(String last_import_time) { this.last_import_time = last_import_time; }
    public void setCreatedAt(String created_at) { this.created_at = created_at; }
    public void setUpdatedAt(String updated_at) { this.updated_at = updated_at; }
    public void setGlobalhotspare(Boolean globalhotspare) { this.globalhotspare = globalhotspare; }
    public void setAutoreplace(Boolean autoreplace) { this.autoreplace = autoreplace; }
    public void setPowerSavingMode(String power_saving_mode) { this.power_saving_mode = power_saving_mode; }
    public void setSpecialAvailable(Long special_available) { this.special_available = special_available; }
    public void setSpecialUsed(Long special_used) { this.special_used = special_used; }
    public void setDiskIds(ArrayList<String> disk_ids) { this.disk_ids = disk_ids; }
    public void setTopology(ArrayList<Stripe> topology) { this.topology = topology; }
    public void setLog(ArrayList<String> log) { this.log = log; }
    public void setZilDrives(ArrayList<String> zil_drives) { this.zil_drives = zil_drives; }
    public void setSpecial(ArrayList<String> special) { this.special = special; }
    public void setSpecialDrives(ArrayList<String> special_drives) { this.special_drives = special_drives; }
    public void setSpecialDiskIds(ArrayList<String> special_disk_ids) { this.special_disk_ids = special_disk_ids; }
    public void setType(String type) { this.type = type; }
    public void setEncryptionState(String state) { this.encryption_state = state; }
    public void setDs3PoolHealth(String state) { this.ds3_pool_health = state; }
    public void setAutoTrim(Boolean auto_trim) { this.auto_trim = auto_trim; }
    public void setProtectionSelect(String protection_select) { this.protection_select = protection_select; }
}
