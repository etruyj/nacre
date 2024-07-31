//===================================================================
// DataPolicyConfig.java
//      Description:
//          This model holds the configuration information for 
//          data policies.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DataPolicyConfig extends DataPolicy {
    @SerializedName("data_persistence_rules")
    private ArrayList<DataPersistenceRuleConfig> data_persistence_rules;

    //===========================================
    // Constructors
    //===========================================
    public DataPolicyConfig() {} // blank constructor to allow for copy constructor

    public DataPolicyConfig(DataPolicy policy) {
        this.setName(policy.getName());
        this.setChecksumType(policy.getChecksumType());
        this.setDefaultGetJobPriority(policy.getDefaultGetJobPriority());
        this.setDefaultPutJobPriority(policy.getDefaultPutJobPriority());
        this.setDefaultVerifyJobPriority(policy.getDefaultVerifyJobPriority());
        this.setRebuildPriority(policy.getRebuildPriority());
        this.setVersioning(policy.getVersioning());
        this.setCreatedAt(policy.getCreatedAt());
        this.setUpdatedAt(policy.getUpdatedAt());
        this.setId(policy.getId());
        this.setAlwaysForcePutJobCreation(policy.isAlwaysForcePutJobCreation());
        this.setAlwaysMinimizeSpanningAcrossMedia(policy.isAlwaysMinimizeSpanningAcrossMedia());
        this.setBlobbingEnabled(policy.isBlobbingEnabled());
        this.setDefaultBlobSize(policy.getDefaultBlobSize());
        this.setDefaultVerifyAfterWrite(policy.isDefaultVerifyAfterWrite());
        this.setEndToEndCrcRequired(policy.isEndToEndCrcRequired());
        this.setMaxVersionsToKeep(policy.getMaxVersionsToKeep());
    }
    
    public DataPolicyConfig(DataPolicyConfig policy) {
        this.setName(policy.getName());
        this.setChecksumType(policy.getChecksumType());
        this.setDefaultGetJobPriority(policy.getDefaultGetJobPriority());
        this.setDefaultPutJobPriority(policy.getDefaultPutJobPriority());
        this.setDefaultVerifyJobPriority(policy.getDefaultVerifyJobPriority());
        this.setRebuildPriority(policy.getRebuildPriority());
        this.setVersioning(policy.getVersioning());
        this.setCreatedAt(policy.getCreatedAt());
        this.setUpdatedAt(policy.getUpdatedAt());
        this.setId(policy.getId());
        this.setAlwaysForcePutJobCreation(policy.isAlwaysForcePutJobCreation());
        this.setAlwaysMinimizeSpanningAcrossMedia(policy.isAlwaysMinimizeSpanningAcrossMedia());
        this.setBlobbingEnabled(policy.isBlobbingEnabled());
        this.setDefaultBlobSize(policy.getDefaultBlobSize());
        this.setDefaultVerifyAfterWrite(policy.isDefaultVerifyAfterWrite());
        this.setEndToEndCrcRequired(policy.isEndToEndCrcRequired());
        this.setMaxVersionsToKeep(policy.getMaxVersionsToKeep());
    }
    
    //===========================================
    // Getters
    //===========================================
    public ArrayList<DataPersistenceRuleConfig> getDataPersistenceRules() { return data_persistence_rules; }

    //===========================================
    // Setters
    //===========================================
    public void setDataPersistenceRules(ArrayList<DataPersistenceRuleConfig> rules) { this.data_persistence_rules = rules; }
}
