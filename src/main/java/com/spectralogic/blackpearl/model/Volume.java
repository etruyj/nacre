//===================================================================
// Volume.java
//      Description:
//          This is the model class for a NAS volume.
// 
// Created by Sean Snyder
// Copyright Spectra Logic Corporation 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.model;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class Volume {
    private String id;
    private boolean atime;
    private Long available;
    private boolean compression;
    @SerializedName("dataset_id")
    private String dataset_id;
    private boolean deduplication;
    @SerializedName("mount_point")
    private String mount_point;
    private String name;
    @SerializedName("pool_id")
    private String pool_id;
    private Long quota;
    @SerializedName("read_only")
    private boolean read_only;
    private Long reservation;
    private String type;
    private Long used;
    @SerializedName("used_by_snapshots")
    private Long used_by_snapshots;
    @SerializedName("created_at")
    private String created_at;
    @SerializedName("updated_at")
    private String updated_at;
    private String bpownerid;
    private boolean replicated;
    private boolean caseinsensitive;
    @SerializedName("snapshot_change_threshold")
    private String snapshot_change_threshold;
    @SerializedName("used_for_share")
    private boolean used_for_share;
    @SerializedName("database_backup_target")
    private boolean database_backup_target;
    private ArrayList<String> transactions;
    @SerializedName("zfs_name")
    private String zfs_name;
    @SerializedName("nfi_volume_policy_enabled")
    private boolean nfi_volume_policy_enabled;
    @SerializedName("nfi_volume_policy_nfi_system_id")
    private String nfi_volume_policy_nfi_system_id;
    @SerializedName("nfi_volume_policy_bucket_id")
    private String nfi_volume_policy_bucket_id;
    @SerializedName("nfi_volume_policy_policy")
    private String nfi_volume_policy_policy;
    @SerializedName("nfi_volume_policy_days_to_keep")
    private int nfi_volume_policy_days_to_keep;
    @SerializedName("nfi_volume_policy_cron_string")
    private String nfi_volume_policy_cron_string;
    @SerializedName("nfi_volume_policy_start_when_in_cache")
    private boolean nfi_volume_policy_start_when_in_cache;
    @SerializedName("nfi_volume_policy_ds3_job_ids")
    private ArrayList<String> nfi_volume_policy_ds3_job_ids;
    @SerializedName("replication_volume_policy_enabled")
    private boolean replication_volume_policy_enabled;
    @SerializedName("replication_volume_policy_replication_system_id")
    private String replication_volume_policy_replication_system_id;
    @SerializedName("replication_volume_policy_destination_pool_name")
    private String replication_volume_policy_destination_pool_name;
    @SerializedName("replication_volume_policy_destination_volume_name")
    private String replication_volume_policy_destination_volume_name;
    @SerializedName("replication_volume_policy_cron_string")
    private String replication_volume_policy_cron_string;
    @SerializedName("replication_volume_policy_initial_validation")
    private String replication_volume_policy_initial_validation;
    @SerializedName("replication_volume_policy_error_message_id")
    private String replication_volume_policy_error_message_id;
    @SerializedName("replication_volume_policy_status")
    private String replication_volume_policy_status;

    //===========================================
    // Constructors
    //===========================================
    public Volume() {} // blank constructor to allow for a copy constructor.
    
    public Volume(Volume other) {
        this.setId(other.getId());
        this.setAtime(other.isAtime());
        this.setAvailable(other.getAvailable());
        this.setCompression(other.isCompression());
        this.setDatasetId(other.getDatasetId());
        this.setDeduplication(other.isDeduplication());
        this.setMountPoint(other.getMountPoint());
        this.setName(other.getName());
        this.setPoolId(other.getPoolId());
        this.setQuota(other.getQuota());
        this.setReadOnly(other.isReadOnly());
        this.setReservation(other.getReservation());
        this.setType(other.getType());
        this.setUsed(other.getUsed());
        this.setUsedBySnapshots(other.getUsedBySnapshots());
        this.setCreatedAt(other.getCreatedAt());
        this.setUpdatedAt(other.getUpdatedAt());
        this.setBpownerid(other.getBpownerid());
        this.setReplicated(other.isReplicated());
        this.setCaseinsensitive(other.isCaseinsensitive());
        this.setSnapshotChangeThreshold(other.getSnapshotChangeThreshold());
        this.setUsedForShare(other.isUsedForShare());
        this.setDatabaseBackupTarget(other.isDatabaseBackupTarget());
        this.setTransactions(other.getTransactions());
        this.setZfsName(other.getZfsName());
        this.setNfiVolumePolicyEnabled(other.isNfiVolumePolicyEnabled());
        this.setNfiVolumePolicyNfiSystemId(other.getNfiVolumePolicyNfiSystemId());
        this.setNfiVolumePolicyBucketId(other.getNfiVolumePolicyBucketId());
        this.setNfiVolumePolicyPolicy(other.getNfiVolumePolicyPolicy());
        this.setNfiVolumePolicyDaysToKeep(other.getNfiVolumePolicyDaysToKeep());
        this.setNfiVolumePolicyCronString(other.getNfiVolumePolicyCronString());
        this.setNfiVolumePolicyStartWhenInCache(other.isNfiVolumePolicyStartWhenInCache());
        this.setNfiVolumePolicyDs3JobIds(other.getNfiVolumePolicyDs3JobIds());
        this.setReplicationVolumePolicyEnabled(other.isReplicationVolumePolicyEnabled());
        this.setReplicationVolumePolicyReplicationSystemId(other.getReplicationVolumePolicyReplicationSystemId());
        this.setReplicationVolumePolicyDestinationPoolName(other.getReplicationVolumePolicyDestinationPoolName());
        this.setReplication_volume_policy_cron_string(other.getReplicationVolumePolicyCronString());
        this.setReplicationVolumePolicyDestinationVolumeName(other.getReplicationVolumePolicyDestinationVolumeName());
        this.setReplicationVolumePolicyInitialValidation(other.getReplicationVolumePolicyInitialValidation());
        this.setReplicationVolumePolicyErrorMessageId(other.getReplicationVolumePolicyErrorMessageId());
        this.setReplicationVolumePolicyStatus(other.getReplicationVolumePolicyStatus());
    }

    //===========================================
    // Gettors
    //===========================================
    public String getId() { return id; }
    public boolean isAtime() { return atime; }
    public Long getAvailable() { return available; }
    public boolean isCompression() { return compression; }
    public String getDatasetId() { return dataset_id; }
    public boolean isDeduplication() { return deduplication; }
    public String getMountPoint() { return mount_point; }
    public String getName() { return name; }
    public String getPoolId() { return pool_id; }
    public Long getQuota() { return quota; }
    public boolean isReadOnly() { return read_only; }
    public Long getReservation() { return reservation; }
    public String getType() { return type; }
    public Long getUsed() { return used; }
    public Long getUsedBySnapshots() { return used_by_snapshots; }
    public String getCreatedAt() { return created_at; }
    public String getUpdatedAt() { return updated_at; }
    public String getBpownerid() { return bpownerid; }
    public boolean isReplicated() { return replicated; }
    public boolean isCaseinsensitive() { return caseinsensitive; }
    public String getSnapshotChangeThreshold() { return snapshot_change_threshold; }
    public boolean isUsedForShare() { return used_for_share; }
    public boolean isDatabaseBackupTarget() { return database_backup_target; }
    public ArrayList<String> getTransactions() { return transactions; }
    public String getZfsName() { return zfs_name; }
    public boolean isNfiVolumePolicyEnabled() { return nfi_volume_policy_enabled; }
    public String getNfiVolumePolicyNfiSystemId() { return nfi_volume_policy_nfi_system_id; }
    public String getNfiVolumePolicyBucketId() { return nfi_volume_policy_bucket_id; }
    public String getNfiVolumePolicyPolicy() { return nfi_volume_policy_policy; }
    public int getNfiVolumePolicyDaysToKeep() { return nfi_volume_policy_days_to_keep; }
    public String getNfiVolumePolicyCronString() { return nfi_volume_policy_cron_string; }
    public boolean isNfiVolumePolicyStartWhenInCache() { return nfi_volume_policy_start_when_in_cache; }
    public ArrayList<String> getNfiVolumePolicyDs3JobIds() { return nfi_volume_policy_ds3_job_ids; }
    public boolean isReplicationVolumePolicyEnabled() { return replication_volume_policy_enabled; }
    public String getReplicationVolumePolicyReplicationSystemId() { return replication_volume_policy_replication_system_id; }
    public String getReplicationVolumePolicyDestinationPoolName() { return replication_volume_policy_destination_pool_name; }
    public String getReplicationVolumePolicyDestinationVolumeName() { return replication_volume_policy_destination_volume_name; }
    public String getReplicationVolumePolicyCronString() { return replication_volume_policy_cron_string; }
    public String getReplicationVolumePolicyInitialValidation() { return replication_volume_policy_initial_validation; }
    public String getReplicationVolumePolicyErrorMessageId() { return replication_volume_policy_error_message_id; }
    public String getReplicationVolumePolicyStatus() { return replication_volume_policy_status; }
    
    //===========================================
    // Settors
    //===========================================
    public void setId(String id) { this.id = id; }
    public void setAtime(boolean atime) { this.atime = atime; }
    public void setAvailable(Long available) { this.available = available; }
    public void setCompression(boolean compression) { this.compression = compression; }
    public void setDatasetId(String dataset_id) { this.dataset_id = dataset_id; }
    public void setDeduplication(boolean deduplication) { this.deduplication = deduplication; }
    public void setMountPoint(String mount_point) { this.mount_point = mount_point; }
    public void setName(String name) { this.name = name; }
    public void setPoolId(String pool_id) { this.pool_id = pool_id; }
    public void setQuota(Long quota) { this.quota = quota; }
    public void setReadOnly(boolean read_only) { this.read_only = read_only; }
    public void setReservation(Long reservation) { this.reservation = reservation; }
    public void setType(String type) { this.type = type; }
    public void setUsed(Long used) { this.used = used; }
    public void setUsedBySnapshots(Long used_by_snapshots) { this.used_by_snapshots = used_by_snapshots; }
    public void setCreatedAt(String created_at) { this.created_at = created_at; }
    public void setUpdatedAt(String updated_at) { this.updated_at = updated_at; }
    public void setBpownerid(String bpownerid) { this.bpownerid = bpownerid; }
    public void setReplicated(boolean replicated) { this.replicated = replicated; }
    public void setCaseinsensitive(boolean caseinsensitive) { this.caseinsensitive = caseinsensitive; }
    public void setSnapshotChangeThreshold(String snapshot_change_threshold) { this.snapshot_change_threshold = snapshot_change_threshold; }
    public void setUsedForShare(boolean used_for_share) { this.used_for_share = used_for_share; }
    public void setDatabaseBackupTarget(boolean database_backup_target) { this.database_backup_target = database_backup_target; }
    public void setTransactions(ArrayList<String> transactions) { this.transactions = transactions; }
    public void setZfsName(String zfs_name) { this.zfs_name = zfs_name; }
    public void setNfiVolumePolicyEnabled(boolean nfi_volume_policy_enabled) { this.nfi_volume_policy_enabled = nfi_volume_policy_enabled; }
    public void setNfiVolumePolicyNfiSystemId(String nfi_volume_policy_nfi_system_id) { this.nfi_volume_policy_nfi_system_id = nfi_volume_policy_nfi_system_id; }
    public void setNfiVolumePolicyBucketId(String nfi_volume_policy_bucket_id) { this.nfi_volume_policy_bucket_id = nfi_volume_policy_bucket_id; }
    public void setNfiVolumePolicyPolicy(String nfi_volume_policy_policy) { this.nfi_volume_policy_policy = nfi_volume_policy_policy; }
    public void setNfiVolumePolicyDaysToKeep(int nfi_volume_policy_days_to_keep) { this.nfi_volume_policy_days_to_keep = nfi_volume_policy_days_to_keep; }
    public void setNfiVolumePolicyCronString(String nfi_volume_policy_cron_string) { this.nfi_volume_policy_cron_string = nfi_volume_policy_cron_string; }
    public void setNfiVolumePolicyStartWhenInCache(boolean nfi_volume_policy_start_when_in_cache) { this.nfi_volume_policy_start_when_in_cache = nfi_volume_policy_start_when_in_cache; }
    public void setNfiVolumePolicyDs3JobIds(ArrayList<String> nfi_volume_policy_ds3_job_ids) { this.nfi_volume_policy_ds3_job_ids = nfi_volume_policy_ds3_job_ids; }
    public void setReplicationVolumePolicyEnabled(boolean replication_volume_policy_enabled) { this.replication_volume_policy_enabled = replication_volume_policy_enabled; }
    public void setReplicationVolumePolicyReplicationSystemId(String replication_volume_policy_replication_system_id) { this.replication_volume_policy_replication_system_id = replication_volume_policy_replication_system_id; }
    public void setReplicationVolumePolicyDestinationPoolName(String replication_volume_policy_destination_pool_name) { this.replication_volume_policy_destination_pool_name = replication_volume_policy_destination_pool_name; }
    public void setReplication_volume_policy_cron_string(String replication_volume_policy_cron_string) { this.replication_volume_policy_cron_string = replication_volume_policy_cron_string; }
    public void setReplicationVolumePolicyDestinationVolumeName(String replication_volume_policy_destination_volume_name) { this.replication_volume_policy_destination_volume_name = replication_volume_policy_destination_volume_name; }
    public void setReplicationVolumePolicyInitialValidation(String replication_volume_policy_initial_validation) { this.replication_volume_policy_initial_validation = replication_volume_policy_initial_validation; }
    public void setReplicationVolumePolicyErrorMessageId(String replication_volume_policy_error_message_id) { this.replication_volume_policy_error_message_id = replication_volume_policy_error_message_id; }
    public void setReplicationVolumePolicyStatus(String replication_volume_policy_status) { this.replication_volume_policy_status = replication_volume_policy_status; }
    
}
