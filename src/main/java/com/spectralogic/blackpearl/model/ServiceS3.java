//======================================================================
// ServiceS3.java
//      Description:
//          This model contains the S3 service settings for the BlackPearl.
//
// Created by Sean Snyder
// Copyright Spectra Logic Corporation
//======================================================================

package com.spectralogic.blackpearl.nacre.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ServiceS3 extends Service {
    @SerializedName("arctic_blue_pool_config")
    private String arctic_blue_pool_config;
    @SerializedName("scheduled_iom")
    private String scheduled_iom;
    @SerializedName("dependency_attrs")
    private List<String> dependency_attrs;
    private String type;
    @SerializedName("sub_type")
    private String sub_type;
    private String state;
    private boolean enabled;
    @SerializedName("created_at")
    private String created_at;
    @SerializedName("updated_at")
    private String updated_at;
    private String status;
    private int port;
    @SerializedName("iom_enable_cron_string")
    private String iom_enable_cron_string;
    @SerializedName("iom_disable_cron_string")
    private String iom_disable_cron_string;;
    private boolean activated;
    @SerializedName("allow_new_job_requests")
    private boolean allow_new_job_requests;
    @SerializedName("auto_activate_timeout_in_mins")
    private Integer auto_activate_timeout_in_mins;
    @SerializedName("auto_inspect")
    private String auto_inspect;
    @SerializedName("default_verify_data_prior_to_import")
    private boolean default_verify_data_prior_to_import;
    @SerializedName("default_verify_data_after_import")
    private String default_verify_data_after_import;
    @SerializedName("iom_enabled")
    private boolean iom_enabled;
    @SerializedName("partially_verify_last_percent_of_tapes")
    private String partially_verify_last_percent_of_tapes;
    @SerializedName("unavailable_tape_partition_max_job_retry_in_mins")
    private int unavailable_tape_partition_max_job_retry_in_mins;
    @SerializedName("unavailable_pool_max_job_retry_in_mins")
    private int unavailable_pool_max_job_retry_in_mins;
    @SerializedName("unavailable_media_policy")
    private String unavailable_media_policy;
    @SerializedName("verify_checkpoint_before_read")
    private boolean verify_checkpoint_before_read;
    @SerializedName("trigger_backup")
    private Boolean trigger_backup; // Trigger a database backup

    //===========================================
    // Getters
    //===========================================
    public String getArcticBluePoolConfig() { return arctic_blue_pool_config; }
    public String getScheduledIom() { return scheduled_iom; }
    public List<String> getDependencyAttrs() { return dependency_attrs; }
    public String getType() { return type; }
    public String getSubType() { return sub_type; }
    public String getState() { return state; }
    public boolean isEnabled() { return enabled; }
    public String getCreatedAt() { return created_at; }
    public String getUpdatedAt() { return updated_at; }
    public String getStatus() { return status; }
    public int getPort() { return port; }
    public String getIomEnableCronString() { return iom_enable_cron_string; }
    public String getIomDisableCronString() { return iom_disable_cron_string; }
    public boolean isActivated() { return activated; }
    public boolean isAllowNewJobRequests() { return allow_new_job_requests; }
    public Integer getAutoActivateTimeoutInMins() { return auto_activate_timeout_in_mins; }
    public String getAutoInspect() { return auto_inspect; }
    public boolean isDefaultVerifyDataPriorToImport() { return default_verify_data_prior_to_import; }
    public String getDefaultVerifyDataAfterImport() { return default_verify_data_after_import; }
    public boolean isIomEnabled() { return iom_enabled; }
    public String getPartiallyVerifyLastPercentOfTapes() { return partially_verify_last_percent_of_tapes; }
    public int getUnavailableTapePartitionMaxJobRetryInMins() { return unavailable_tape_partition_max_job_retry_in_mins; }
    public int getUnavailablePoolMaxJobRetryInMins() { return unavailable_pool_max_job_retry_in_mins; }
    public String getUnavailableMediaPolicy() { return unavailable_media_policy; }
    public boolean isVerifyCheckpointBeforeRead() { return verify_checkpoint_before_read; }
    public Boolean isTriggerBackup() { return trigger_backup; }

    //===========================================
    // Setters
    //===========================================
    public void setArcticBluePoolConfig(String arctic_blue_pool_config) { this.arctic_blue_pool_config = arctic_blue_pool_config; }
    public void setScheduledIom(String scheduled_iom) { this.scheduled_iom = scheduled_iom; }
    public void setDependencyAttrs(List<String> dependency_attrs) { this.dependency_attrs = dependency_attrs; }
    public void setType(String type) { this.type = type; }
    public void setSubType(String sub_type) { this.sub_type = sub_type; }
    public void setState(String state) { this.state = state; }
    public void setEnabled(boolean enabled) { this.enabled = enabled; }
    public void setCreatedAt(String created_at) { this.created_at = created_at; }
    public void setUpdatedAt(String updated_at) { this.updated_at = updated_at; }
    public void setStatus(String status) { this.status = status; }
    public void setPort(int port) { this.port = port; }
    public void setIomEnableCronString(String iom_enable_cron_string) { this.iom_enable_cron_string = iom_enable_cron_string; }
    public void setIomDisableCronString(String iom_disable_cron_string) { this.iom_disable_cron_string = iom_disable_cron_string; }
    public void setActivated(boolean activated) { this.activated = activated; }
    public void setAllowNewJobRequests(boolean allow_new_job_requests) { this.allow_new_job_requests = allow_new_job_requests; }
    public void setAutoActivateTimeoutInMins(Integer auto_activate_timeout_in_mins) { this.auto_activate_timeout_in_mins = auto_activate_timeout_in_mins; }
    public void setAutoInspect(String auto_inspect) { this.auto_inspect = auto_inspect; }
    public void setDefaultVerifyDataPriorToImport(boolean default_verify_data_prior_to_import) { this.default_verify_data_prior_to_import = default_verify_data_prior_to_import; }
    public void setDefaultVerifyDataAfterImport(String default_verify_data_after_import) { this.default_verify_data_after_import = default_verify_data_after_import; }
    public void setIomEnabled(boolean iom_enabled) { this.iom_enabled = iom_enabled; }
    public void setPartiallyVerifyLastPercentOfTapes(String partially_verify_last_percent_of_tapes) { this.partially_verify_last_percent_of_tapes = partially_verify_last_percent_of_tapes; }
    public void setUnavailableTapePartitionMaxJobRetryInMins(int unavailable_tape_partition_max_job_retry_in_mins) { this.unavailable_tape_partition_max_job_retry_in_mins = unavailable_tape_partition_max_job_retry_in_mins; }
    public void setUnavailablePoolMaxJobRetryInMins(int unavailable_pool_max_job_retry_in_mins) { this.unavailable_pool_max_job_retry_in_mins = unavailable_pool_max_job_retry_in_mins; }
    public void setUnavailableMediaPolicy(String unavailable_media_policy) { this.unavailable_media_policy = unavailable_media_policy; }
    public void setVerifyCheckpointBeforeRead(boolean verify_checkpoint_before_read) { this.verify_checkpoint_before_read = verify_checkpoint_before_read; }
    public void setTriggerBackup(Boolean trigger) { this. trigger_backup = trigger; }
}

