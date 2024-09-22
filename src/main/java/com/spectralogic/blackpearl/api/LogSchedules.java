//===================================================================
// LogSchedules.java
//      Description:
//          This class handles API calls to the BlackPearl related to
//          the LogSchedules.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.api;

import com.spectralogic.vail.vapir.util.http.RestClient;
import com.spectralogic.blackpearl.nacre.model.ApiDataResponse;
import com.spectralogic.blackpearl.nacre.model.ScheduleLogSet;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogSchedules {
    private static final Logger log = LoggerFactory.getLogger(LogSchedules.class);

    public static ArrayList<ScheduleLogSet> list(String ip_address, String token, RestClient rest_client) throws IOException, JsonParseException {
        Gson gson = new Gson();

        String url = getUrl(ip_address);

        log.debug("API URL: GET " + url);

        String response = rest_client.get(url, token);

        log.debug("API Response: " + response);

        ApiDataResponse data = gson.fromJson(response, new TypeToken<ApiDataResponse<ScheduleLogSet>>() {}.getType());

        return data.getData();
    }

    public static ScheduleLogSet put(ScheduleLogSet schedule, String ip_address, String token, RestClient rest_client) throws IOException, JsonParseException {
        Gson gson = new Gson();

        String url = getUrl(ip_address);
        String payload = gson.toJson(schedule);

        log.debug("API URL: PUT " + url);
        log.debug("API Payload: " + payload);

        String response = rest_client.post(url, token, payload);

        log.debug("API Response: " + response);

        ScheduleLogSet sch =  gson.fromJson(response, ScheduleLogSet.class);

        if(sch.getCronString() != null) {
            return sch;
        } else {
            return null;
        }
    }

    //===========================================
    // Private Functions
    //===========================================
    private static String getUrl(String ip_address) {
        return ip_address + "/api/log_schedules";
    }
}
