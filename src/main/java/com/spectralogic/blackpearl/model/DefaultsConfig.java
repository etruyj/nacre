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
        cifsShare = new Share();
        nfsShare = new Share();
        vailShare = new Share();
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
    public Share getCifsShare() { return cifsShare; }
    public Share getNfsShare() { return nfsShare; }
    public Share getVailShare() { return vailShare; }
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
    public void setCifsShare(Share share) { this.cifsShare = share; }
    public void setNfsShare(Share share) { this.nfsShare = share; }
    public void setVailShare(Share share) { this.vailShare = share; }
    public void setServer(ServerConfig server) { this.server = server; }
}
