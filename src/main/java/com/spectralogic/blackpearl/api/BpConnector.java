//===================================================================
// BpConnector.java
//      Description:
//          This code is the central access point for all the API 
//          calls to the BlackPearl's management interface.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.api;

import com.spectralogic.blackpearl.nacre.model.ActivationKey;
import com.spectralogic.blackpearl.nacre.model.BlackPearlNode;
import com.spectralogic.blackpearl.nacre.model.Ds3Bucket;
import com.spectralogic.blackpearl.nacre.model.Ds3User;
import com.spectralogic.blackpearl.nacre.model.DataPersistenceRule;
import com.spectralogic.blackpearl.nacre.model.DataPolicy;
import com.spectralogic.blackpearl.nacre.model.DiskDrive;
import com.spectralogic.blackpearl.nacre.model.DiskPartition;
import com.spectralogic.blackpearl.nacre.model.NetworkInterface;
import com.spectralogic.blackpearl.nacre.model.NetworkInterfaceSend;
import com.spectralogic.blackpearl.nacre.model.NtpSettings;
import com.spectralogic.blackpearl.nacre.model.Pool;
import com.spectralogic.blackpearl.nacre.model.ScheduleDatabaseBackup;
import com.spectralogic.blackpearl.nacre.model.ScheduleLogSet;
import com.spectralogic.blackpearl.nacre.model.Service;
import com.spectralogic.blackpearl.nacre.model.ServiceS3;
import com.spectralogic.blackpearl.nacre.model.Share;
import com.spectralogic.blackpearl.nacre.model.SmtpSettings;
import com.spectralogic.blackpearl.nacre.model.StorageDomain;
import com.spectralogic.blackpearl.nacre.model.StorageDomainMember;
import com.spectralogic.blackpearl.nacre.model.Tape;
import com.spectralogic.blackpearl.nacre.model.TapePartition;
import com.spectralogic.blackpearl.nacre.model.Volume;
import com.spectralogic.vail.vapir.util.http.RestClient;

import com.google.gson.JsonParseException;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BpConnector {
    private String token;
    private String domain_name;
    private RestClient rest_client;
    private static final Logger log = LoggerFactory.getLogger(BpConnector.class);

    public BpConnector(String domain_name, boolean ignore_ssl) {
        this.domain_name = "https://" + domain_name;
        this.rest_client = new RestClient(ignore_ssl);
        
        if(ignore_ssl) {
            log.info("Initializing connection to blackpearl at " + this.domain_name + " with ignore_ssl=true"); 
        } else {
            log.info("Initializing connection to blackpearl at " + this.domain_name + " with ignore_ssl=false"); 
        }
    }

    //===========================================
    // Getters
    //===========================================
    public boolean getConnectionStatus() { 
        if(token != null && token.length() > 0) {
            return true;
        } else {
            return false;
        }
    }
    public String getDomainName() { return domain_name; }

    //===========================================
    // API Calls
    //===========================================

    public ActivationKey addActivationKey(ActivationKey key) throws IOException, JsonParseException {
        return ActivationKeys.add(key, domain_name, token, rest_client);
    }

    public DataPersistenceRule addDataPersistenceRule(DataPersistenceRule rule) throws IOException, JsonParseException {
        return DataPersistenceRules.add(rule, domain_name, token, rest_client);
    }

    public StorageDomainMember addStorageDomainMember(StorageDomainMember member) throws IOException, JsonParseException {
        return StorageDomainMembers.add(member, domain_name, token, rest_client);
    }

    public Ds3Bucket createDs3Bucket(Ds3Bucket bucket) throws IOException, JsonParseException {
        return Buckets.create(bucket, domain_name, token, rest_client);
    }

    public DiskPartition createDs3DiskPartition(DiskPartition par) throws IOException, JsonParseException {
        return Ds3DiskPartitions.create(par, domain_name, token, rest_client);
    }

    public NetworkInterface createNetworkInterface(NetworkInterfaceSend interf) throws IOException, JsonParseException {
        return NetworkInterfaces.create(interf, domain_name, token, rest_client);
    }

    public Pool createDs3Pool(Pool pool) throws IOException, JsonParseException {
        return Ds3Pools.create(pool, domain_name, token, rest_client);
    }

    public ServiceS3 createDatabaseBackup(ServiceS3 s3_service) throws IOException, JsonParseException {
        return Services.putDatabaseBackup(s3_service, domain_name, token, rest_client);
    }

    public DataPolicy createDataPolicy(DataPolicy policy) throws IOException, JsonParseException {
        return DataPolicies.create(policy, domain_name, token, rest_client);
    }

    public Pool createPool(Pool pool) throws IOException, JsonParseException {
        return Pools.create(pool, domain_name, token, rest_client);
    }

    public Share createShare(Share share) throws IOException, JsonParseException {
        return Shares.create(share, domain_name, token, rest_client);
    }

    public StorageDomain createStorageDomain(StorageDomain domain) throws IOException, JsonParseException {
        return StorageDomains.create(domain, domain_name, token, rest_client);
    }

    public Volume createVolume(Volume volume) throws IOException, JsonParseException {
        return Volumes.create(volume, domain_name, token, rest_client);
    }

    public ScheduleDatabaseBackup getDatabaseBackupSchedule() throws IOException, JsonParseException {
        return DatabaseBackups.getBackupSchedule(domain_name, token, rest_client);
    }

    public Pool getDs3Pool(String pool_id) throws IOException, JsonParseException {
        return Ds3Pools.get(pool_id, domain_name, token, rest_client);
    }

    public SmtpSettings getSmtpSettings() throws IOException, JsonParseException {
        return SmtpServer.get(domain_name, token, rest_client);
    }

    public ArrayList<ActivationKey> listActivationKeys() throws IOException, JsonParseException {
        return ActivationKeys.list(domain_name, token, rest_client);
    }

    public ArrayList<Ds3Bucket> listBuckets() throws IOException, JsonParseException {
        return Buckets.list(domain_name, token, rest_client);
    }

    public ArrayList<Pool> listDs3Pools() throws IOException, JsonParseException {
        return Ds3Pools.list(domain_name, token, rest_client);
    }

    public ArrayList<DataPolicy> listDataPolicies() throws IOException, JsonParseException {
        return DataPolicies.list(domain_name, token, rest_client);
    }

    public ArrayList<DiskDrive> listDataDisks() throws IOException, JsonParseException {
        return DataDisks.list(domain_name, token, rest_client);
    }

    public ArrayList<DiskPartition> listDiskPartitions() throws IOException, JsonParseException {
        return Ds3DiskPartitions.list(domain_name, token, rest_client);
    }

    public ArrayList<NetworkInterface> listNetworkInterfaces() throws IOException, JsonParseException {
        return NetworkInterfaces.list(domain_name, token, rest_client);
    }

    public ArrayList<BlackPearlNode> listNodes() throws IOException, JsonParseException {
        return Nodes.list(domain_name, token, rest_client);
    }

    public ArrayList<NtpSettings> listNtpServers() throws IOException, JsonParseException {
        return NtpServers.list(domain_name, token, rest_client);
    }
    
    public ArrayList<Pool> listPools() throws IOException, JsonParseException {
        return Pools.list(domain_name, token, rest_client);
    }

    public ArrayList<Service> listServices() throws IOException, JsonParseException {
        return Services.list(domain_name, token, rest_client);
    }

    public ArrayList<Share> listShares() throws IOException, JsonParseException {
        return Shares.list(domain_name, token, rest_client);
    }
    
    public ArrayList<StorageDomain> listStorageDomains() throws IOException, JsonParseException {
        return StorageDomains.list(domain_name, token, rest_client);
    }

    public ArrayList<Tape> listTapes() throws IOException, JsonParseException {
        return Tapes.list(domain_name, token, rest_client);
    }

    public ArrayList<TapePartition> listTapePartitions() throws IOException, JsonParseException {
        return TapePartitions.list(domain_name, token, rest_client);
    }

    public ArrayList<Ds3User> listUsers() throws IOException, JsonParseException {
        return Users.list(domain_name, token, rest_client);
    }

    public ArrayList<Volume> listVolumes() throws IOException, JsonParseException {
        return Volumes.list(domain_name, token, rest_client);
    }

    public boolean login(String username, String password) throws IOException, JsonParseException {
        token = "Bearer " + Authenticate.getToken(username, password, domain_name, rest_client);

        if(token != null && token.length() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public ScheduleLogSet setLogSchedule(ScheduleLogSet schedule) throws IOException, JsonParseException {
        return LogSchedules.put(schedule, domain_name, token, rest_client);
    }

    public ScheduleDatabaseBackup updateBackupSchedule(ScheduleDatabaseBackup schedule) throws IOException, JsonParseException {
        return DatabaseBackups.updateSchedule(schedule, domain_name, token, rest_client);
    }

    public NetworkInterface updateNetworkInterface(NetworkInterfaceSend interf) throws IOException, JsonParseException {
        return NetworkInterfaces.put(interf, domain_name, token, rest_client);
    }

    public BlackPearlNode updateNode(BlackPearlNode node) throws IOException, JsonParseException {
        return Nodes.update(node, domain_name, token, rest_client);
    }

    public NtpSettings updateNtpServers(NtpSettings settings) throws IOException, JsonParseException {
        return NtpServers.update(settings, domain_name, token, rest_client);
    }

    public SmtpSettings updateSmtpSettings(SmtpSettings settings) throws IOException, JsonParseException {
        return SmtpServer.update(settings, domain_name, token, rest_client);
    }
}
