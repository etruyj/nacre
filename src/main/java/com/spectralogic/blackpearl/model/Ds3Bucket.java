//===================================================================
// Ds3Bucket.java
//      Description:
//          A model for the BlackPearl bucket.
// 
// Created by Sean Snyder
// Copyright Spectra Logic Corporation 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.model;

import com.google.gson.annotations.SerializedName;

public class Ds3Bucket {
    private String name;
    private Boolean empty;
    @SerializedName("logical_used_capacity")
    private Long logical_used_capacity;
    @SerializedName("user_id")
    private String user_id;
    @SerializedName("data_policy_id")
    private String data_policy_id;
    @SerializedName("created_at")
    private String created_at;
    @SerializedName("updated_at")
    private String updated_at;
    private String id;
    @SerializedName("user_username")
    private String user_username;
    private String operation;

    //===========================================
    // Constructors
    //===========================================
    public Ds3Bucket() {} // empty constructor to allow for a copy constructor

    public Ds3Bucket(Ds3Bucket bucket) {
        this.setName(bucket.getName());
        this.setEmpty(bucket.isEmpty());
        this.setLogicalUsedCapacity(bucket.getLogicalUsedCapacity());
        this.setUserId(bucket.getUserId());
        this.setDataPolicyId(bucket.getDataPolicyId());
        this.setCreatedAt(bucket.getCreatedAt());
        this.setUpdatedAt(bucket.getUpdatedAt());
        this.setId(bucket.getId());
        this.setUserUsername(bucket.getUserUsername());
        this.setOperation(bucket.getOperation());
    }
    
    //===========================================
    // Gettors
    //===========================================
    public String getName() { return name; }
    public Boolean isEmpty() { return empty; }
    public Long getLogicalUsedCapacity() { return logical_used_capacity; }
    public String getUserId() { return user_id; }
    public String getDataPolicyId() { return data_policy_id; }
    public String getCreatedAt() { return created_at; }
    public String getUpdatedAt() { return updated_at; }
    public String getId() { return id; }
    public String getUserUsername() { return user_username; }
    public String getOperation() { return operation; }

    //===========================================
    // Settors
    //===========================================
    public void setName(String name) { this.name = name; }
    public void setEmpty(Boolean empty) { this.empty = empty; }
    public void setLogicalUsedCapacity(Long logical_used_capacity) { this.logical_used_capacity = logical_used_capacity; }
    public void setUserId(String user_id) { this.user_id = user_id; }
    public void setDataPolicyId(String data_policy_id) { this.data_policy_id = data_policy_id; }
    public void setCreatedAt(String created_at) { this.created_at = created_at; }
    public void setUpdatedAt(String updated_at) { this.updated_at = updated_at; }
    public void setId(String id) { this.id = id; }
    public void setUserUsername(String user_username) { this.user_username = user_username; }
    public void setOperation(String operation) { this.operation = operation; }
}
