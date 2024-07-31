//===================================================================
// DataPersistenceRule.java
//      Description:
//          This model holds information on the data persistence rule.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.model;

import com.google.gson.annotations.SerializedName;

public class DataPersistenceRule {
    @SerializedName("data_policy_id")
    private String data_policy_id;
    @SerializedName("isolation_level")
    private String isolation_level;
    @SerializedName("storage_domain_id")
    private String storage_domain_id;
    private String type;
    @SerializedName("minimum_days_to_retain")
    private Integer minimum_days_to_retain;
    private String state;
    @SerializedName("created_at")
    private String created_at;
    @SerializedName("updated_at")
    private String updated_at;
    private String id;

    //===========================================
    // Constructors
    //===========================================
    public DataPersistenceRule() {} // empty constructor to allow for copy constructor.
    
    public DataPersistenceRule(DataPersistenceRule rule) {
        this.setDataPolicyId(rule.getDataPolicyId());
        this.setIsolationLevel(rule.getIsolationLevel());
        this.setStorageDomainId(rule.getStorageDomainId());
        this.setType(rule.getType());
        this.setMinimumDaysToRetain(rule.getMinimumDaysToRetain());
        this.setState(rule.getState());
        this.setCreatedAt(rule.getCreatedAt());
        this.setUpdatedAt(rule.getUpdatedAt());
        this.setId(rule.getId());
    }


    //===========================================
    // Getters
    //===========================================
    public String getDataPolicyId() { return data_policy_id; }
    public String getIsolationLevel() { return isolation_level; }
    public String getStorageDomainId() { return storage_domain_id; }
    public String getType() { return type; }
    public Integer getMinimumDaysToRetain() { return minimum_days_to_retain; }
    public String getState() { return state; }
    public String getCreatedAt() { return created_at; }
    public String getUpdatedAt() { return updated_at; }
    public String getId() { return id; }

    //===========================================
    // Setters
    //===========================================
    public void setDataPolicyId(String policy_id) { this.data_policy_id = policy_id; }
    public void setIsolationLevel(String level) { this.isolation_level = level; }
    public void setStorageDomainId(String domain_id) { this.storage_domain_id = domain_id; }
    public void setType(String type) { this.type = type; }
    public void setMinimumDaysToRetain(Integer days) { this.minimum_days_to_retain = days; }
    public void setState(String state) { this.state = state; }
    public void setCreatedAt(String date) { this.created_at = created_at; }
    public void setUpdatedAt(String date) { this.updated_at = updated_at; }
    public void setId(String id) { this.id = id; }
}
