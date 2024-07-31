//===================================================================
// DefaultsConfig.java
//      Description:
//          This class holds all the default information for 
//          different objects in the script.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.model;

public class DefaultsConfig {
    private StorageDomainMember storageDomainMember;
    private StorageDomain storageDomain;
    private DataPersistenceRule dataPersistenceRule;
    private DataPolicy dataPolicy;

    public DefaultsConfig() {
        storageDomainMember = new StorageDomainMember();
        storageDomain = new StorageDomain();
        dataPersistenceRule = new DataPersistenceRule();
        dataPolicy = new DataPolicy();
    }

    //===========================================
    // Getters
    //===========================================
    public StorageDomainMember getStorageDomainMember() { return storageDomainMember; }
    public StorageDomain getStorageDomain() { return storageDomain; }
    public DataPersistenceRule getDataPersistenceRule() { return dataPersistenceRule; }
    public DataPolicy getDataPolicy() { return dataPolicy; }

    //===========================================
    // Setters
    //===========================================
    public void setStorageDomainMember(StorageDomainMember storageDomainMember) { this.storageDomainMember = storageDomainMember; }
    public void setStorageDomain(StorageDomain domain) { this.storageDomain = domain; }
    public void setDataPersistenceRule(DataPersistenceRule rule) { this.dataPersistenceRule = rule; }
    public void setDataPolicy(DataPolicy policy) { this.dataPolicy = policy; }
}
