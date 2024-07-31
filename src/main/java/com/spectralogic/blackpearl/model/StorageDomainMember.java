//===================================================================
// StorageDomainMember.java
//      Description:
//          This is a model class for the Storage Domain Member object.
// 
// Created by Sean Snyder
// Copyright Spectra Logic Corporation 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.model;

import com.google.gson.annotations.SerializedName;

public class StorageDomainMember {
    @SerializedName("pool_partition_id")
    private String pool_partition_id;
    @SerializedName("tape_partition_id")
    private String tape_partition_id;
    @SerializedName("storage_domain_id")
    private String storage_domain_id;
    @SerializedName("write_preference")
    private String write_preference;
    @SerializedName("tape_type")
    private String tape_type;
    private String state;
    @SerializedName("auto_compaction_threshold")
    public Integer auto_compaction_threshold;
    @SerializedName("created_at")
    private String created_at;
    @SerializedName("updated_at")
    private String updated_at;
    private String id;
    
    //===========================================
    // Constructors
    //===========================================
    public StorageDomainMember() {} // empty constructor to allow for a copy constructor

    // Copy Constructor
    public StorageDomainMember(StorageDomainMember member) {
        this.setPoolPartitionId(member.getPoolPartitionId());
        this.setTapePartitionId(member.getTapePartitionId());
        this.setStorageDomainId(member.getStorageDomainId());
        this.setWritePreference(member.getWritePreference());
        this.setTapeType(member.getTapeType()); 
        this.setState(member.getState());
        this.setAutoCompactionThreshold(member.getAutoCompactionThreshold());
        this.setCreatedAt(member.getCreatedAt());
        this.setUpdatedAt(member.getUpdatedAt()); 
        this.setId(member.getId()); 
    }

    //===========================================
    // Gettors
    //===========================================
    public String getPoolPartitionId() { return pool_partition_id; }
    public String getTapePartitionId() { return tape_partition_id; }
    public String getStorageDomainId() { return storage_domain_id; }
    public String getWritePreference() { return write_preference; }
    public String getTapeType() { return tape_type; }
    public String getState() { return state; }
    public Integer getAutoCompactionThreshold() { return auto_compaction_threshold; }
    public String getCreatedAt() { return created_at; }
    public String getUpdatedAt() { return updated_at; }
    public String getId() { return id; }
    
    //===========================================
    // Settors
    //===========================================
    public void setPoolPartitionId(String pool_partition_id) { this.pool_partition_id = pool_partition_id; }
    public void setTapePartitionId(String tape_partition_id) { this.tape_partition_id = tape_partition_id; }
    public void setStorageDomainId(String storage_domain_id) { this.storage_domain_id = storage_domain_id; }
    public void setWritePreference(String write_preference) { this.write_preference = write_preference; }
    public void setTapeType(String tape_type) { this.tape_type = tape_type; }
    public void setState(String state) { this.state = state; }
    public void setAutoCompactionThreshold(Integer auto_compaction_threshold) { this.auto_compaction_threshold = auto_compaction_threshold; }
    public void setCreatedAt(String created_at) { this.created_at = created_at; }
    public void setUpdatedAt(String updated_at) { this.updated_at = updated_at; }
    public void setId(String id) { this.id = id; }
}
