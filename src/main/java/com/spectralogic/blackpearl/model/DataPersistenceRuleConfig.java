//===================================================================
// DataPersistenceRuleConfig.java
//      Description:
//          This model builds on the data persitence rule class to 
//          offer a user friendly config model.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.model;

public class DataPersistenceRuleConfig extends DataPersistenceRule {
    private String name; 

    //===========================================
    // Constructors
    //===========================================
    public DataPersistenceRuleConfig() {} // blank constructor to allow for copy constructor
    
    public DataPersistenceRuleConfig(DataPersistenceRule rule) {
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
    
    public DataPersistenceRuleConfig(DataPersistenceRuleConfig rule) {
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
    public String getName() { return name; }

    //===========================================
    // Setters
    //===========================================
    public void setName(String name) { this.name = name; }
}
