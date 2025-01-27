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
    private String hostname;
    @SerializedName("activation_keys")
    private ArrayList<ActivationKeyConfig> activation_keys;
    @SerializedName("disk_partitions")
    private ArrayList<PoolConfig> disk_partitions;
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
    @SerializedName("vail_shares")
    private ArrayList<ShareConfig> vail_shares;
    @SerializedName("smtp_settings")
    private SmtpSettings smtp_settings;
    @SerializedName("database_backup")
    private ScheduleDatabaseConfig database_backup;
    @SerializedName("log_schedule")
    private ScheduleLogConfig log_schedule;
    @SerializedName("ntp_servers")
    private ArrayList<String> ntp_servers; 
    @SerializedName("dns_servers")
    private ArrayList<String> dns_servers;
    @SerializedName("management_interface")
    private NetworkInterfaceConfig mgmt_interface;
    @SerializedName("data_interface")
    private NetworkInterfaceConfig data_interface;

    //===========================================
    // Getters
    //===========================================
    public String getHostname() { return hostname; }
    public ArrayList<ActivationKeyConfig> getActivationKeys() { return activation_keys; }
    public ArrayList<PoolConfig> getDiskPartitions() { return disk_partitions; }
    public ArrayList<StorageDomainConfig> getStorageDomains() { return storage_domains; }
    public ArrayList<DataPolicyConfig> getDataPolicies() { return data_policies; }
    public ArrayList<Ds3BucketConfig> getBuckets() { return buckets; }
    public ArrayList<PoolConfig> getNasPools() { return nas_pools; }
    public ArrayList<VolumeConfig> getVolumes() { return volumes; }
    public ArrayList<ShareConfig> getCifsShares() { return cifs_shares; }
    public ArrayList<ShareConfig> getNfsShares() { return nfs_shares; }
    public ArrayList<ShareConfig> getVailShares() { return vail_shares; }
    public SmtpSettings getSmtpSettings() { return smtp_settings; }
    public ScheduleDatabaseConfig getDatabaseBackup() { return database_backup; }
    public ScheduleLogConfig getLogSchedule() { return log_schedule; }
    public ArrayList<String> getNtpServers() { return ntp_servers; }
    public ArrayList<String> getDnsServers() { return dns_servers; }
    public NetworkInterfaceConfig getManagementInterface() { return mgmt_interface; }
    public NetworkInterfaceConfig getDataInterface() { return mgmt_interface; }

    //===========================================
    // Setters
    //===========================================
    public void addNtpServer(String server) { this.ntp_servers.add(server); }
    public void setHostname(String hostname) { this.hostname = hostname; }
    public void setActivationKeys(ArrayList<ActivationKeyConfig> keys) { this.activation_keys = keys; }
    public void setDiskPartitions(ArrayList<PoolConfig> pools) { this.disk_partitions = pools; }
    public void setStorageDomains(ArrayList<StorageDomainConfig> domains) { this.storage_domains = domains; }
    public void setDataPolicies(ArrayList<DataPolicyConfig> policies) { this.data_policies = policies; }
    public void setBuckets(ArrayList<Ds3BucketConfig> buckets) { this.buckets = buckets; }
    public void setNasPools(ArrayList<PoolConfig> pools) { this.nas_pools = pools; }
    public void setVolumes(ArrayList<VolumeConfig> vols) { this.volumes = vols; }
    public void setCifsShares(ArrayList<ShareConfig> shares) { this.cifs_shares = shares; }
    public void setNfsShares(ArrayList<ShareConfig> shares) { this.nfs_shares = shares; }
    public void setVailShares(ArrayList<ShareConfig> shares) { this.vail_shares = shares; }
    public void setSmtpSettings(SmtpSettings settings) { this.smtp_settings = settings; }
    public void setDatabaseBackup(ScheduleDatabaseConfig backup) { this.database_backup = backup; }
    public void setLogSchedule(ScheduleLogConfig schedule) { this.log_schedule = schedule; }
    public void setNtpServers(ArrayList<String> servers) { this.ntp_servers = servers; }
    public void setDnsServers(ArrayList<String> servers) { this.dns_servers = servers; }
    public void setManagementInterface(NetworkInterfaceConfig mgmt) { this.mgmt_interface = mgmt; }
    public void setDataInterface(NetworkInterfaceConfig data) { this.data_interface = data; }

    //===========================================
    // Functions
    //===========================================
    public void merge(BpConfig config) {
        // This class merges two BlackPearl Configurations.
        // The main purpose of this method is to combine the default settings configured
        // for the BlackPearl with the settings configured in Vail.
        
        if(config.getHostname() != null) {
            if(hostname == null) { hostname = config.getHostname(); }
        }
    
        if(config.getActivationKeys() != null) {
            if(activation_keys == null) { 
                hostname = config.getHostname(); 
            } else {
                activation_keys.addAll(config.getActivationKeys());
            }
        }
    
        if(config.getDiskPartitions() != null) {
            if(disk_partitions == null) { 
                disk_partitions = config.getDiskPartitions(); 
            } else {
                disk_partitions.addAll(config.getDiskPartitions());
            }
        }
    
        if(config.getStorageDomains() != null) {
            if(storage_domains == null) { 
                storage_domains = config.getStorageDomains(); 
            } else {
                storage_domains.addAll(config.getStorageDomains());
            }
        }

        if(config.getDataPolicies() != null) {
            if(data_policies == null) { 
                data_policies = config.getDataPolicies(); 
            } else {
                data_policies.addAll(config.getDataPolicies());
            }
        }
    
        if(config.getBuckets() != null) {
            if(buckets == null) {
                buckets = config.getBuckets();
            } else {
                buckets.addAll(config.getBuckets());
            }
        } 
        
        if(config.getNasPools() != null) {
            if(nas_pools == null) { 
                nas_pools = config.getNasPools(); 
            } else {
                nas_pools.addAll(config.getNasPools());
            }
        }
    
        if(config.getVolumes() != null) {
            if(volumes == null) { 
                volumes = config.getVolumes(); 
            } else {
                volumes.addAll(config.getVolumes());
            }
        }
    
        if(config.getDataPolicies() != null) {
            if(data_policies == null) { 
                data_policies = config.getDataPolicies(); 
            } else {
                data_policies.addAll(config.getDataPolicies());
            }
        }
    
        if(config.getCifsShares() != null) {
            if(cifs_shares == null) { 
                cifs_shares = config.getCifsShares(); 
            } else {
                cifs_shares.addAll(config.getCifsShares());
            }
        }
    
        if(config.getNfsShares() != null) {
            if(nfs_shares == null) { 
                nfs_shares = config.getNfsShares(); 
            } else {
                nfs_shares.addAll(config.getNfsShares());
            }
        }
    
        if(config.getVailShares() != null) {
            if(vail_shares == null) { 
                vail_shares = config.getVailShares(); 
            } else {
                vail_shares.addAll(config.getVailShares());
            }
        }
    
        if(config.getSmtpSettings() != null) {
            if(smtp_settings == null) { smtp_settings = config.getSmtpSettings(); }
        }
    
        if(config.getDatabaseBackup() != null) {
            if(database_backup == null) { database_backup = config.getDatabaseBackup(); }
        }
    
        if(config.getLogSchedule() != null) {
            if(log_schedule == null) { log_schedule = config.getLogSchedule(); }
        }
    
        if(config.getNtpServers() != null) {
            if(ntp_servers == null) { 
                ntp_servers = config.getNtpServers(); 
            } else {
                ntp_servers.addAll(config.getNtpServers());
            }
        }
    
        if(config.getDnsServers() != null) {
            if(dns_servers == null) { 
                dns_servers = config.getDnsServers(); 
            } else {
                dns_servers.addAll(config.getDnsServers());
            }
        }
    
        if(config.getManagementInterface() != null) {
            if(mgmt_interface == null) { mgmt_interface = config.getManagementInterface(); }
        }
    
        if(config.getDataInterface() != null) {
            if(data_interface == null) { data_interface = config.getDataInterface(); }
        }
    }
}
