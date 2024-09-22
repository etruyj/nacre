//===================================================================
// NtpServers.java
//      Description:
//          This class handles API calls to the BlackPearl related to
//          the NtpServers.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.api;

import com.spectralogic.vail.vapir.util.http.RestClient;
import com.spectralogic.blackpearl.nacre.model.ApiDataResponse;
import com.spectralogic.blackpearl.nacre.model.NtpSettings;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NtpServers {
    private static final Logger log = LoggerFactory.getLogger(NtpServers.class);

    public static NtpSettings update(NtpSettings settings, String ip_address, String token, RestClient rest_client) throws IOException, JsonParseException {
        Gson gson = new Gson();

        String url = getUrl(ip_address) + "/1"; // the /1 is a constant for PUT requests.
        String payload = gson.toJson(settings);

        log.debug("API URL: PUT " + url);
        log.debug("API Payload: " + payload);

        String response = rest_client.put(url, token, payload);

        log.debug("API Response: " + response);

        NtpSettings new_settings =  gson.fromJson(response, NtpSettings.class);

        if(new_settings.getCurrentTime() != null) {
            return new_settings;
        } else {
            return null;
        }
    }

    public static ArrayList<NtpSettings> list(String ip_address, String token, RestClient rest_client) throws IOException, JsonParseException {
        Gson gson = new Gson();

        String url = getUrl(ip_address);

        log.debug("API URL: GET " + url);

        String response = rest_client.get(url, token);

        log.debug("API Response: " + response);

        ApiDataResponse data = gson.fromJson(response, new TypeToken<ApiDataResponse<NtpSettings>>() {}.getType());

        return data.getData();
    }

    //===========================================
    // Private Functions
    //===========================================
    private static String getUrl(String ip_address) {
        return ip_address + "/api/ntp_servers";
    }
}
