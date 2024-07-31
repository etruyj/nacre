//===================================================================
// DatabaseBackups.java
//      Description:
//          This class handles API calls to the BlackPearl related to
//          database backups.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.api;

import com.spectralogic.blackpearl.nacre.model.ScheduleDatabaseBackup;
import com.spectralogic.vail.vapir.util.http.RestClient;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseBackups {
    private static final Logger log = LoggerFactory.getLogger(DatabaseBackups.class);

    public static ScheduleDatabaseBackup getBackupSchedule(String ip_address, String token, RestClient rest_client) throws IOException, JsonParseException {
        Gson gson = new Gson();

        String url = getUrl(ip_address);

        log.debug("API URL: PUT " + url);

        String response = rest_client.get(url, token);

        log.debug("API Response: " + response);

        return gson.fromJson(response, ScheduleDatabaseBackup.class);
    }

    public static ScheduleDatabaseBackup updateSchedule(ScheduleDatabaseBackup schedule, String ip_address, String token, RestClient rest_client) throws IOException, JsonParseException {
        Gson gson = new Gson();

        String url = putUrl(ip_address);
        String payload = gson.toJson(schedule);

        log.debug("API URL: PUT " + url);
        log.debug("API Payload: " + payload);

        String response = rest_client.put(url, token, payload);

        log.debug("API Response: " + response);

        return gson.fromJson(response, ScheduleDatabaseBackup.class);
    }

    //===========================================
    // Private Functions
    //===========================================

    private static String getUrl(String ip_address) {
        return ip_address + "/api/database_backup_schedules";
    }

    private static String putUrl(String ip_address) {
        return ip_address + "/api/database_backup_schedules/database_archiver_schedule";
    }
}
