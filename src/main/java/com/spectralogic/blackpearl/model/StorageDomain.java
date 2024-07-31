//===================================================================
// StorageDomain.java
//      Description:
//          A model class for the storage domain object.
// 
// Created by Sean Snyder
// Copyright Spectra Logic Corporation 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.model;

import com.google.gson.annotations.SerializedName;

public class StorageDomain {
    private String name;
    @SerializedName("write_optimization")
    private String write_optimization;
    @SerializedName("secure_media_allocation")
    private boolean secure_media_allocation;
    @SerializedName("media_ejection_allowed")
    private boolean media_ejection_allowed;
    @SerializedName("auto_eject_upon_cron")
    private String auto_eject_upon_cron;
    @SerializedName("auto_eject_upon_job_completion")
    private boolean auto_eject_upon_job_completion;
    @SerializedName("auto_eject_upon_job_cancellation")
    private boolean auto_eject_upon_job_cancellation;
    @SerializedName("auto_eject_upon_media_full")
    private boolean auto_eject_upon_media_full;
    @SerializedName("auto_eject_media_full_threshold")
    private Integer auto_eject_media_full_threshold;
    @SerializedName("maximum_auto_verification_frequency_in_days")
    private Integer maximum_auto_verification_frequency_in_days;
    @SerializedName("ltfs_file_naming")
    private String ltfs_file_naming;
    @SerializedName("verify_prior_to_auto_eject")
    private String verify_prior_to_auto_eject;
    @SerializedName("created_at")
    private String created_at;
    @SerializedName("updated_at")
    private String updated_at;
    private String id;

    //==========================================
    // Constructors
    //==========================================
    public StorageDomain() {} // blank to allow for copy constructor

    // Copy Constructor
    public StorageDomain(StorageDomain domain) {
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
    // Gettors
    //===========================================
    public String getName() { return name; }
    public String getWriteOptimization() { return write_optimization; }
    public boolean isSecureMediaAllocation() { return secure_media_allocation; }
    public boolean isMediaEjectionAllowed() { return media_ejection_allowed; }
    public String getAutoEjectUponCron() { return auto_eject_upon_cron; }
    public boolean isAutoEjectUponJobCompletion() { return auto_eject_upon_job_completion; }
    public boolean isAutoEjectUponJobCancellation() { return auto_eject_upon_job_cancellation; }
    public boolean isAutoEjectUponMediaFull() { return auto_eject_upon_media_full; }
    public Integer getAutoEjectMediaFullThreshold() { return auto_eject_media_full_threshold; }
    public Integer getMaximumAutoVerificationFrequencyInDays() { return maximum_auto_verification_frequency_in_days; }
    public String getLtfsFileNaming() { return ltfs_file_naming; }
    public String getVerifyPriorToAutoEject() { return verify_prior_to_auto_eject; }
    public String getCreatedAt() { return created_at; }
    public String getUpdatedAt() { return updated_at; }
    public String getId() { return id; }
    
    //===========================================
    // Settors
    //===========================================
    public void setName(String name) { this.name = name; }
    public void setWriteOptimization(String write_optimization) { this.write_optimization = write_optimization; }
    public void setSecureMediaAllocation(boolean secure_media_allocation) { this.secure_media_allocation = secure_media_allocation; }
    public void setMediaEjectionAllowed(boolean media_ejection_allowed) { this.media_ejection_allowed = media_ejection_allowed; }
    public void setAutoEjectUponCron(String auto_eject_upon_cron) { this.auto_eject_upon_cron = auto_eject_upon_cron; }
    public void setAutoEjectUponJobCompletion(boolean auto_eject_upon_job_completion) { this.auto_eject_upon_job_completion = auto_eject_upon_job_completion; }
    public void setAutoEjectUponJobCancellation(boolean auto_eject_upon_job_cancellation) { this.auto_eject_upon_job_cancellation = auto_eject_upon_job_cancellation; }
    public void setAutoEjectUponMediaFull(boolean auto_eject_upon_media_full) { this.auto_eject_upon_media_full = auto_eject_upon_media_full; }
    public void setAutoEjectMediaFullThreshold(Integer auto_eject_media_full_threshold) { this.auto_eject_media_full_threshold = auto_eject_media_full_threshold; }
    public void setMaximumAutoVerificationFrequencyInDays(Integer maximum_auto_verification_frequency_in_days) { this.maximum_auto_verification_frequency_in_days = maximum_auto_verification_frequency_in_days; }
    public void setLtfsFileNaming(String ltfs_file_naming) { this.ltfs_file_naming = ltfs_file_naming; }
    public void setVerifyPriorToAutoEject(String verify_prior_to_auto_eject) { this.verify_prior_to_auto_eject = verify_prior_to_auto_eject; }
    public void setCreatedAt(String created_at) { this.created_at = created_at; }
    public void setUpdatedAt(String updated_at) { this.updated_at = updated_at; }
    public void setId(String id) { this.id = id; }
}
