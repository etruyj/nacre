//===================================================================
// DataPolicy.java
//      Description:
//          This is a model class for a data policy object.
// 
// Created by Sean Snyder
// Copyright Spectra Logic Corporation 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.model;

import com.google.gson.annotations.SerializedName;

public class DataPolicy {
    private String name;
    @SerializedName("always_force_put_job_creation")
    private Boolean always_force_put_job_creation;
    @SerializedName("always_minimize_spanning_across_media")
    private Boolean always_minimize_spanning_across_media;
    @SerializedName("blobbing_enabled")
    private Boolean blobbing_enabled;
    @SerializedName("checksum_type")
    private String checksum_type;
    @SerializedName("default_blob_size")
    private Integer default_blob_size;
    @SerializedName("default_get_job_priority")
    private String default_get_job_priority;
    @SerializedName("default_put_job_priority")
    private String default_put_job_priority;
    @SerializedName("default_verify_job_priority")
    private String default_verify_job_priority;
    @SerializedName("default_verify_after_write")
    private Boolean default_verify_after_write;
    @SerializedName("end_to_end_crc_required")
    private Boolean end_to_end_crc_required;
    @SerializedName("rebuild_priority")
    private String rebuild_priority;
    @SerializedName("versioning")
    private String versioning;
    @SerializedName("max_versions_to_keep")
    private Integer max_versions_to_keep;
    @SerializedName("created_at")
    private String created_at;
    @SerializedName("updated_at")
    private String updated_at;
    private String id;
    
    //===========================================
    // Constructors
    //===========================================
    public DataPolicy() {} // blank constructor to allow for copy constructor.

    public DataPolicy(DataPolicy policy) {
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
    // Gettors
    //===========================================
    public String getName() { return name; }
    public Boolean isAlwaysForcePutJobCreation() { return always_force_put_job_creation; }
    public Boolean isAlwaysMinimizeSpanningAcrossMedia() { return always_minimize_spanning_across_media; }
    public Boolean isBlobbingEnabled() { return blobbing_enabled; }
    public String getChecksumType() { return checksum_type; }
    public Integer getDefaultBlobSize() { return default_blob_size; }
    public String getDefaultGetJobPriority() { return default_get_job_priority; }
    public String getDefaultPutJobPriority() { return default_put_job_priority; }
    public String getDefaultVerifyJobPriority() { return default_verify_job_priority; }
    public Boolean isDefaultVerifyAfterWrite() { return default_verify_after_write; }
    public Boolean isEndToEndCrcRequired() { return end_to_end_crc_required; }
    public String getRebuildPriority() { return rebuild_priority; }
    public String getVersioning() { return versioning; }
    public Integer getMaxVersionsToKeep() { return max_versions_to_keep; }
    public String getCreatedAt() { return created_at; }
    public String getUpdatedAt() { return updated_at; }
    public String getId() { return id; }
    
    //===========================================
    // Settors
    //===========================================
    public void setName(String name) { this.name = name; }
    public void setAlwaysForcePutJobCreation(Boolean always_force_put_job_creation) { this.always_force_put_job_creation = always_force_put_job_creation; }
    public void setAlwaysMinimizeSpanningAcrossMedia(Boolean always_minimize_spanning_across_media) { this.always_minimize_spanning_across_media = always_minimize_spanning_across_media; }
    public void setBlobbingEnabled(Boolean blobbing_enabled) { this.blobbing_enabled = blobbing_enabled; }
    public void setChecksumType(String checksum_type) { this.checksum_type = checksum_type; }
    public void setDefaultBlobSize(Integer default_blob_size) { this.default_blob_size = default_blob_size; }
    public void setDefaultGetJobPriority(String default_get_job_priority) { this.default_get_job_priority = default_get_job_priority; }
    public void setDefaultPutJobPriority(String default_put_job_priority) { this.default_put_job_priority = default_put_job_priority; }
    public void setDefaultVerifyJobPriority(String default_verify_job_priority) { this.default_verify_job_priority = default_verify_job_priority; }
    public void setDefaultVerifyAfterWrite(Boolean default_verify_after_write) { this.default_verify_after_write = default_verify_after_write; }
    public void setEndToEndCrcRequired(Boolean end_to_end_crc_required) { this.end_to_end_crc_required = end_to_end_crc_required; }
    public void setRebuildPriority(String rebuild_priority) { this.rebuild_priority = rebuild_priority; }
    public void setVersioning(String versioning) { this.versioning = versioning; }
    public void setMaxVersionsToKeep(Integer max_versions_to_keep) { this.max_versions_to_keep = max_versions_to_keep; }
    public void setCreatedAt(String created_at) { this.created_at = created_at; }
    public void setUpdatedAt(String updated_at) { this.updated_at = updated_at; }
    public void setId(String id) { this.id = id; }
}
