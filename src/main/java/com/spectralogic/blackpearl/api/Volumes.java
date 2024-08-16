//===================================================================
// Volumes.java
//      Description:
//          This class handles API calls to the BlackPearl related to
//          the Volumes.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.api;

import com.spectralogic.vail.vapir.util.http.RestClient;
import com.spectralogic.blackpearl.nacre.model.ApiDataResponse;
import com.spectralogic.blackpearl.nacre.model.Volume;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Volumes {
    private static final Logger log = LoggerFactory.getLogger(Volumes.class);

    public static Volume create(Volume volume, String ip_address, String token, RestClient rest_client) throws IOException, JsonParseException {
        Gson gson = new Gson();

        String url = getUrl(ip_address);
        String payload = gson.toJson(volume);

        log.debug("API URL: POST " + url);
        log.debug("API Payload: " + payload);

        String response = rest_client.post(url, token, payload);

        log.debug("API Response: " + response);

        Volume vol =  gson.fromJson(response, Volume.class);

        if(vol.getName() != null) {
            return vol;
        } else {
            return null;
        }
    }

    public static ArrayList<Volume> list(String ip_address, String token, RestClient rest_client) throws IOException, JsonParseException {
        Gson gson = new Gson();

        String url = getUrl(ip_address);

        log.debug("API URL: GET " + url);

        String response = rest_client.get(url, token);

        log.debug("API Response: " + response);

        ApiDataResponse data = gson.fromJson(response, new TypeToken<ApiDataResponse<Volume>>() {}.getType());

        return data.getData();
    }

    //===========================================
    // Private Functions
    //===========================================
    private static String getUrl(String ip_address) {
        return ip_address + "/api/volumes";
    }
}
