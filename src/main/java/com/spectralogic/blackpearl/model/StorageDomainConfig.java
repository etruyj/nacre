//===================================================================
// StorageDomainConfig.java
//      Description:
//          This class holds storage domain configuration object when
//          configuring the blackpearl. This allows simplified inputs
//          when loading the system.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.model;

import java.util.ArrayList;

public class StorageDomainConfig extends StorageDomain {
    private ArrayList<StorageDomainMemberConfig> members;

    //===========================================
    // Constructor
    //===========================================
    public StorageDomainConfig() {} // blank constructor to allow for copy constructor
    
    public StorageDomainConfig(StorageDomain domain) {
        this.setId(domain.getId());
        this.setCreatedAt(domain.getCreatedAt());
        this.setUpdatedAt(domain.getUpdatedAt());
        this.setName(domain.getName());
        this.setWriteOptimization(domain.getWriteOptimization());
        this.setLtfsFileNaming(domain.getLtfsFileNaming());
        this.setAutoEjectUponCron(domain.getAutoEjectUponCron());
        this.setSecureMediaAllocation(domain.isSecureMediaAllocation());
        this.setMediaEjectionAllowed(domain.isMediaEjectionAllowed());
        this.setAutoEjectUponJobCompletion(domain.isAutoEjectUponJobCompletion());
        this.setAutoEjectUponJobCancellation(domain.isAutoEjectUponJobCancellation());
        this.setAutoEjectUponMediaFull(domain.isAutoEjectUponMediaFull());
        this.setAutoEjectMediaFullThreshold(domain.getAutoEjectMediaFullThreshold());
        this.setMaximumAutoVerificationFrequencyInDays(domain.getMaximumAutoVerificationFrequencyInDays());
    }

    //===========================================
    // Getters
    //===========================================
    public ArrayList<StorageDomainMemberConfig> getMembers() { return members; }

    //===========================================
    // Setters
    //===========================================
    public void setMembers(ArrayList<StorageDomainMemberConfig> members) { this.members = members; }
}
