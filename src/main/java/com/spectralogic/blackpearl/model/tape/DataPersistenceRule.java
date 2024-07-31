//===================================================================
// DataPersistenceRule.java
//      Description:
//          This is the container class for the data persistence rule
//          object which attaches storage domains to data policies.
// 
// Created by Sean Snyder
// Copyright Spectra Logic Corporation 2024
//===================================================================

package com.spectralogic.vail.configurator.model.blackpearl.tape;

import com.google.gson.annotations.SerializedName;
import java.time.ZonedDateTime;

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
    private ZonedDateTime created_at;
    @SerializedName("updated_at")
    private ZonedDateTime updated_at;
    private String id;
    
    //===========================================
    // Gettors
    //===========================================
    public String getDataPolicyId() { return data_policy_id; }
    public String getIsolationLevel() { return isolation_level; }
    public String getStorageDomainId() { return storage_domain_id; }
    public String getType() { return type; }
    public Integer getMinimumDaysToRetain() { return minimum_days_to_retain; }
    public String getState() { return state; }
    public ZonedDateTime getCreatedAt() { return created_at; }
    public ZonedDateTime getUpdatedAt() { return updated_at; }
    public String getId() { return id; }
    
    //===========================================
    // Settors
    //===========================================
    public void setDataPolicyId(String data_policy_id) { this.data_policy_id = data_policy_id; }
    public void setIsolationLevel(String isolation_level) { this.isolation_level = isolation_level; }
    public void setStorageDomainId(String storage_domain_id) { this.storage_domain_id = storage_domain_id; }
    public void setType(String type) { this.type = type; }
    public void setMinimumDaysToRetain(Integer minimum_days_to_retain) { this.minimum_days_to_retain = minimum_days_to_retain; }
    public void setState(String state) { this.state = state; }
    public void setCreatedAt(ZonedDateTime created_at) { this.created_at = created_at; }
    public void setUpdatedAt(ZonedDateTime updated_at) { this.updated_at = updated_at; }
    public void setId(String id) { this.id = id; }
}
