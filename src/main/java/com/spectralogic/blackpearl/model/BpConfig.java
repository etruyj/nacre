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
import java.util.HashMap;
import java.util.Map;

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
    @SerializedName("network_interfaces")
    private Map<String, NetworkInterfaceConfig> network_interfaces;

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
    public Map<String, NetworkInterfaceConfig> getNetworkInterfaces() { return network_interfaces; }
    public NetworkInterfaceConfig getNetworkInterface(String type) { return network_interfaces.get(type); }

    //===========================================
    // Setters
    //===========================================
    public void addDs3Bucket(Ds3BucketConfig bucket) {
        if(buckets == null) {
            buckets = new ArrayList<Ds3BucketConfig>();
        }

        buckets.add(bucket);
    }
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
    public void setNetworkInterfaces(Map<String, NetworkInterfaceConfig> iface_map) { this.network_interfaces = iface_map; }
    public void setDataInterface(NetworkInterfaceConfig iface) {
        if(network_interfaces == null) {
            network_interfaces = new HashMap<String, NetworkInterfaceConfig>();
        }

        network_interfaces.put("data", iface);
    }
    public void setManagementInterface(NetworkInterfaceConfig iface) {
        if(network_interfaces == null) {
            network_interfaces = new HashMap<String, NetworkInterfaceConfig>();
        }

        network_interfaces.put("management", iface);
    }
}
