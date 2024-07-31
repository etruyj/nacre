//===================================================================
// BpConnector.java
//      Description:
//          This is the central access for all the commands.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.command;

import com.spectralogic.blackpearl.nacre.api.BpConnector;
import com.spectralogic.blackpearl.nacre.model.BpConfig;
import com.spectralogic.blackpearl.nacre.model.DataPolicy;
import com.spectralogic.blackpearl.nacre.model.DefaultsConfig;
import com.spectralogic.blackpearl.nacre.model.DiskDrive;
import com.spectralogic.blackpearl.nacre.model.DiskPartition;
import com.spectralogic.blackpearl.nacre.model.Ds3Bucket;
import com.spectralogic.blackpearl.nacre.model.Ds3User;
import com.spectralogic.blackpearl.nacre.model.Pool;
import com.spectralogic.blackpearl.nacre.model.Service;
import com.spectralogic.blackpearl.nacre.model.Share;
import com.spectralogic.blackpearl.nacre.model.StorageDomain;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.yaml.snakeyaml.Yaml;

public class BpController {
    private static final Logger log = LoggerFactory.getLogger(BpController.class);
    private BpConnector pearl;
    private DefaultsConfig defaults;

    public BpController(String domain_name, boolean ignore_ssl) {
        pearl = new BpConnector(domain_name, ignore_ssl);
        this.defaults = new DefaultsConfig();
    }

    public BpController(String domain_name, boolean ignore_ssl, String file_path) {
        pearl = new BpConnector(domain_name, ignore_ssl);
        loadConfig(file_path);
    }

    public BpController(String domain_name, boolean ignore_ssl, DefaultsConfig defaults) {
        pearl = new BpConnector(domain_name, ignore_ssl);
        this.defaults = defaults;
    }

    //===========================================
    // Commands
    //===========================================

    public void backupDatabase() {
        CreateDatabaseBackup.newBackup(pearl);
    }

    public ArrayList<String> configureFromFile(String file_name) {
        return ConfigureBlackPearl.fromFile(file_name, defaults, pearl);
    }

    public ArrayList<String> configureFromObject(BpConfig bpconfig) {
        return ConfigureBlackPearl.fromObject(bpconfig, defaults, pearl);  
    }

    public ArrayList<Ds3Bucket> listBuckets() {
        return ListBuckets.all(pearl);
    }

    public ArrayList<DataPolicy> listDataPolicies() {
        return ListDataPolicies.all(pearl);
    }

    public ArrayList<DiskDrive> listDiskDrives() {
        return ListDiskDrives.all(pearl);
    }

    public ArrayList<DiskDrive> listDiskDrivesAvailable() {
        return ListDiskDrives.available(pearl);
    }

    public ArrayList<DiskPartition> listDs3DiskPartitions() {
        return ListDs3DiskPartitions.all(pearl);
    }

    public ArrayList<Pool> listPools() {
        return ListPools.all(pearl);
    }

    public List<Service> listServices() {
        return ListServices.all(pearl);
    }

    public ArrayList<Share> listShares() { 
        return ListShares.all(pearl);
    }

    public ArrayList<StorageDomain> listStorageDomains() {
        return ListStorageDomains.all(pearl);
    }

    public ArrayList<Ds3User> listUsers() {
        return ListUsers.all(pearl);
    }

    public boolean login(String username, String password) {
        log.info("Attempting to log into the BlackPearl with username [" + username + "]");

        try {
            if(pearl.login(username, password)) {
                log.info("Log in successful.");
                return true;
            } else {
                log.error("Failed to login to the BlackPearl with username [" + username + "]");
                return false;
            }
        } catch(Exception e) {
            log.error(e.getMessage());
            log.error("Failed to connect to BlackPearl.");
            return false;
        }
    }

    public ArrayList<String> setBackupSchedule(String schedule, String time, int copies_to_keep) {
        ArrayList<String> response = new ArrayList<String>();
        response.add(ScheduleBackup.withParameters(schedule, time, copies_to_keep, pearl));

        return response;
    }

    //===========================================
    // Private Function
    //===========================================

    public void loadConfig(String file_path) {
        Yaml yaml = new Yaml();
        Path path = Paths.get(file_path);

        log.info("Loading configuration defaults from file: " + file_path);
        try(InputStream inputStream = Files.newInputStream(path)) {
            if(inputStream == null) {
                throw new IllegalArgumentException("File not found: " + file_path);
            }

            this.defaults = yaml.loadAs(inputStream, DefaultsConfig.class);
        } catch(Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load configuration file", e);
        }
    }
}

