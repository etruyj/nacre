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

import java.util.ArrayList;
import java.util.List;

public class DefaultsConfig {
    private Pool nasPool;
    private Pool ds3Pool;
    private DiskPartition ds3DiskPartition;
    private VolumeConfig volume;
    private ShareConfig cifsShare;
    private ShareConfig nfsShare;
    private ShareConfig vailShare;
    private StorageDomainMemberConfig storageDomainMember;
    private StorageDomainConfig storageDomain;
    private DataPersistenceRule dataPersistenceRule;
    private DataPolicyConfig dataPolicy;
    private Ds3Bucket ds3Bucket;
    private List<String> dnsServers;
    private List<String> ntpServers;
    private SmtpSettings smtpSettings;
    private ScheduleDatabaseConfig databaseBackup;
    private ScheduleLogConfig logSchedule;
    private ServerConfig server;

    public DefaultsConfig() {
        nasPool = new Pool();
        ds3Pool = new Pool();
        ds3DiskPartition = new DiskPartition();
        volume = new VolumeConfig();
        storageDomainMember = new StorageDomainMemberConfig();
        storageDomain = new StorageDomainConfig();
        dataPersistenceRule = new DataPersistenceRule();
        dataPolicy = new DataPolicyConfig();
        ds3Bucket = new Ds3Bucket();
        cifsShare = new ShareConfig();
        nfsShare = new ShareConfig();
        vailShare = new ShareConfig();
        dnsServers = new ArrayList<String>();
        ntpServers = new ArrayList<String>();
        smtpSettings = new SmtpSettings();
        databaseBackup = new ScheduleDatabaseConfig();
        logSchedule = new ScheduleLogConfig();
        server = new ServerConfig();
    }

    //===========================================
    // Getters
    //===========================================
    public Pool getNasPool() { return nasPool; }
    public Pool getDs3Pool() { return ds3Pool; }
    public DiskPartition getDs3DiskPartition() { return ds3DiskPartition; }
    public VolumeConfig getVolume() { return volume; }
    public StorageDomainMemberConfig getStorageDomainMember() { return storageDomainMember; }
    public StorageDomainConfig getStorageDomain() { return storageDomain; }
    public DataPersistenceRule getDataPersistenceRule() { return dataPersistenceRule; }
    public DataPolicyConfig getDataPolicy() { return dataPolicy; }
    public Ds3Bucket getDs3Bucket() { return ds3Bucket; }
    public ShareConfig getCifsShare() { return cifsShare; }
    public ShareConfig getNfsShare() { return nfsShare; }
    public ShareConfig getVailShare() { return vailShare; }
    public List<String> getDnsServers() { return dnsServers; }
    public List<String> getNtpServers() { return ntpServers; }
    public SmtpSettings getSmtpSettings() { return smtpSettings; }
    public ScheduleDatabaseConfig getDatabaseBackup() { return databaseBackup; }
    public ScheduleLogConfig getLogSchedule() { return logSchedule; }
    public ServerConfig getServer() { return server; }

    //===========================================
    // Setters
    //===========================================
    public void setNasPool(Pool pool) { this.nasPool = pool; }
    public void setDs3Pool(Pool pool) { this.ds3Pool = pool; }
    public void setDs3DiskPartition(DiskPartition par) { this.ds3DiskPartition = par; } 
    public void setVolume(VolumeConfig vol) { this.volume = vol; }
    public void setStorageDomainMember(StorageDomainMemberConfig storageDomainMember) { this.storageDomainMember = storageDomainMember; }
    public void setStorageDomain(StorageDomainConfig domain) { this.storageDomain = domain; }
    public void setDataPersistenceRule(DataPersistenceRule rule) { this.dataPersistenceRule = rule; }
    public void setDataPolicy(DataPolicyConfig policy) { this.dataPolicy = policy; }
    public void setDs3Bucket(Ds3Bucket bucket) { this.ds3Bucket = bucket; }
    public void setCifsShare(ShareConfig share) { this.cifsShare = share; }
    public void setNfsShare(ShareConfig share) { this.nfsShare = share; }
    public void setVailShare(ShareConfig share) { this.vailShare = share; }
    public void setDnsServers(List<String> servers) { this.dnsServers = servers; }
    public void setNtpServers(List<String> servers) { this.ntpServers = servers; }
    public void setSmtpSettings(SmtpSettings settings) { this.smtpSettings = settings; }
    public void setDatabaseBackup(ScheduleDatabaseConfig backup_schedule) { this.databaseBackup = backup_schedule; }
    public void setLogSchedule(ScheduleLogConfig schedule) { this.logSchedule = schedule; }
    public void setServer(ServerConfig server) { this.server = server; }
}
