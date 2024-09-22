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
    private Volume volume;
    private Share cifsShare;
    private Share nfsShare;
    private Share vailShare;
    private StorageDomainMember storageDomainMember;
    private StorageDomain storageDomain;
    private DataPersistenceRule dataPersistenceRule;
    private DataPolicy dataPolicy;
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
        volume = new Volume();
        storageDomainMember = new StorageDomainMember();
        storageDomain = new StorageDomain();
        dataPersistenceRule = new DataPersistenceRule();
        dataPolicy = new DataPolicy();
        ds3Bucket = new Ds3Bucket();
        cifsShare = new Share();
        nfsShare = new Share();
        vailShare = new Share();
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
    public Volume getVolume() { return volume; }
    public StorageDomainMember getStorageDomainMember() { return storageDomainMember; }
    public StorageDomain getStorageDomain() { return storageDomain; }
    public DataPersistenceRule getDataPersistenceRule() { return dataPersistenceRule; }
    public DataPolicy getDataPolicy() { return dataPolicy; }
    public Ds3Bucket getDs3Bucket() { return ds3Bucket; }
    public Share getCifsShare() { return cifsShare; }
    public Share getNfsShare() { return nfsShare; }
    public Share getVailShare() { return vailShare; }
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
    public void setVolume(Volume vol) { this.volume = vol; }
    public void setStorageDomainMember(StorageDomainMember storageDomainMember) { this.storageDomainMember = storageDomainMember; }
    public void setStorageDomain(StorageDomain domain) { this.storageDomain = domain; }
    public void setDataPersistenceRule(DataPersistenceRule rule) { this.dataPersistenceRule = rule; }
    public void setDataPolicy(DataPolicy policy) { this.dataPolicy = policy; }
    public void setDs3Bucket(Ds3Bucket bucket) { this.ds3Bucket = bucket; }
    public void setCifsShare(Share share) { this.cifsShare = share; }
    public void setNfsShare(Share share) { this.nfsShare = share; }
    public void setVailShare(Share share) { this.vailShare = share; }
    public void setDnsServers(List<String> servers) { this.dnsServers = servers; }
    public void setNtpServers(List<String> servers) { this.ntpServers = servers; }
    public void setSmtpSettings(SmtpSettings settings) { this.smtpSettings = settings; }
    public void setDatabaseBackup(ScheduleDatabaseConfig backup_schedule) { this.databaseBackup = backup_schedule; }
    public void setLogSchedule(ScheduleLogConfig schedule) { this.logSchedule = schedule; }
    public void setServer(ServerConfig server) { this.server = server; }
}
