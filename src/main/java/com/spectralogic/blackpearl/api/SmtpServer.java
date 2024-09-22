//===================================================================
// SmtpServer.java
//      Description:
//          This class handles API calls to the BlackPearl related to
//          the SmtpServer.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.api;

import com.spectralogic.vail.vapir.util.http.RestClient;
import com.spectralogic.blackpearl.nacre.model.ApiObjectResponse;
import com.spectralogic.blackpearl.nacre.model.SmtpSettings;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SmtpServer {
    private static final Logger log = LoggerFactory.getLogger(SmtpServer.class);

    public static SmtpSettings update(SmtpSettings settings, String ip_address, String token, RestClient rest_client) throws IOException, JsonParseException {
        Gson gson = new Gson();

        String url = getUrl(ip_address);
        String payload = gson.toJson(settings);

        log.debug("API URL: PUT " + url);
        log.debug("API Payload: " + payload);

        String response = rest_client.put(url, token, payload);

        log.debug("API Response: " + response);

        SmtpSettings new_settings =  gson.fromJson(response, SmtpSettings.class);

        if(new_settings.getAddress() != null) {
            return new_settings;
        } else {
            return null;
        }
    }

    public static SmtpSettings get(String ip_address, String token, RestClient rest_client) throws IOException, JsonParseException {
        Gson gson = new Gson();

        String url = getUrl(ip_address);

        log.debug("API URL: GET " + url);

        String response = rest_client.get(url, token);

        log.debug("API Response: " + response);

        ApiObjectResponse data = gson.fromJson(response, new TypeToken<ApiObjectResponse<SmtpSettings>>() {}.getType());

        return (SmtpSettings) data.getData();
    }

    //===========================================
    // Private Functions
    //===========================================
    private static String getUrl(String ip_address) {
        return ip_address + "/api/smtp_server";
    }
}
