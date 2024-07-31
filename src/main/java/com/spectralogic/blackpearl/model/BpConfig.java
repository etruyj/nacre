//===================================================================
// BpConfig.java
//      Description:
//          This model holds the BlackPearl configuration information.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.model;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class BpConfig {
    @SerializedName("disk_pools")
    private ArrayList<PoolConfig> disk_pools;
    @SerializedName("storage_domains")
    private ArrayList<StorageDomainConfig> storage_domains;
    @SerializedName("data_policies")
    private ArrayList<DataPolicyConfig> data_policies;
    @SerializedName("buckets")
    private ArrayList<Ds3BucketConfig> buckets;
    @SerializedName("nas_pools")
    private ArrayList<PoolConfig> nas_pools;
    private ArrayList<VolumeConfig> volumes;
    @SerializedName("cifs_shares")
    private ArrayList<ShareConfig> cifs_shares;
    @SerializedName("nfs_shares")
    private ArrayList<ShareConfig> nfs_shares;
    @SerializedName("database_backup")
    private ScheduleDatabaseConfig database_backup;

    //===========================================
    // Getters
    //===========================================
    public ArrayList<PoolConfig> getDiskPools() { return disk_pools; }
    public ArrayList<StorageDomainConfig> getStorageDomains() { return storage_domains; }
    public ArrayList<DataPolicyConfig> getDataPolicies() { return data_policies; }
    public ArrayList<Ds3BucketConfig> getBuckets() { return buckets; }
    public ArrayList<PoolConfig> getNasPools() { return nas_pools; }
    public ArrayList<VolumeConfig> getVolumes() { return volumes; }
    public ArrayList<ShareConfig> getCifsShares() { return cifs_shares; }
    public ArrayList<ShareConfig> getNfsShares() { return nfs_shares; }
    public ScheduleDatabaseConfig getDatabaseBackup() { return database_backup; }

    //===========================================
    // Setters
    //===========================================
    public void setDiskPools(ArrayList<PoolConfig> pools) { this.disk_pools = pools; }
    public void setStorageDomains(ArrayList<StorageDomainConfig> domains) { this.storage_domains = domains; }
    public void setDataPolicies(ArrayList<DataPolicyConfig> policies) { this.data_policies = policies; }
    public void setBuckets(ArrayList<Ds3BucketConfig> buckets) { this.buckets = buckets; }
    public void setNasPools(ArrayList<PoolConfig> pools) { this.nas_pools = pools; }
    public void setVolumes(ArrayList<VolumeConfig> vols) { this.volumes = vols; }
    public void setCifsShares(ArrayList<ShareConfig> shares) { this.cifs_shares = shares; }
    public void setNfsShares(ArrayList<ShareConfig> shares) { this.nfs_shares = shares; }
    public void setDatabaseBackup(ScheduleDatabaseConfig backup) { this.database_backup = backup; }
}
