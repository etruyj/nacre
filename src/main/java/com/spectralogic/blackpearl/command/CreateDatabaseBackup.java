//===================================================================
// CreateDatabaseBackup.java
//      Description:
//          This class handles the commands required to create a
//          database backup.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.command;

import com.spectralogic.blackpearl.nacre.api.BpConnector;
import com.spectralogic.blackpearl.nacre.model.Service;
import com.spectralogic.blackpearl.nacre.model.ServiceS3;
import com.spectralogic.blackpearl.nacre.util.map.MapServices;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateDatabaseBackup {
    private static final Logger log = LoggerFactory.getLogger(CreateDatabaseBackup.class);

    public static String newBackup(BpConnector pearl) {
        log.info("Creating BlackPearl database backup.");

        try {
            // Find S3 service information.
            // This is passed in the body of PUT Database backup command
            // and the ID is used in the url path.
            ArrayList<Service> service_list = ListServices.all(pearl);
            HashMap<String, Service> service_map = MapServices.createNameServiceMap(service_list);

            if(service_map.get("S3") != null && service_map.get("S3") instanceof ServiceS3) {
                ServiceS3 s3_service = (ServiceS3) service_map.get("S3");
                
                s3_service.setTriggerBackup(true);

                s3_service = pearl.createDatabaseBackup(s3_service);

                if(s3_service != null) {
                    log.info("Successfully created database backup");
                    return "Successfully created database backup.";
                } else {
                    log.error("Failed to create database backup.");
                }
            } else {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();

                System.out.println(gson.toJson(service_list));
            }
        } catch(Exception e) {
            log.error(e.getMessage());
            log.error("Failed to backup BlackPearl database.");
        }
                    
        return "Failed to create database backup.";
    }
}
