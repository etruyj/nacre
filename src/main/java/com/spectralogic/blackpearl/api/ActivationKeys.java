//===================================================================
// ActivationKeys.java
//      Description:
//          This class handles the API calls related to activation
//          keys.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.api;

import com.spectralogic.blackpearl.nacre.model.ActivationKey;
import com.spectralogic.vail.vapir.util.http.RestClient;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActivationKeys {
    private static final Logger log = LoggerFactory.getLogger(ActivationKeys.class);

    public static ActivationKey add(ActivationKey key, String ip_address, String token, RestClient rest_client) throws IOException, JsonParseException {
        Gson gson = new Gson();

        String url = getUrl(ip_address);
        String payload = gson.toJson(key);
        
        log.debug("API URL: POST " + url);
        log.debug("API Payload: " + payload);

        String response = rest_client.post(url, token, payload);

        log.debug("API Response: " + response);

        return gson.fromJson(response, ActivationKey.class);
    }

    public static ArrayList<ActivationKey> list(String ip_address, String token, RestClient rest_client) throws IOException, JsonParseException {
        Gson gson = new Gson();

        String url = getUrl(ip_address);

        log.debug("API URL: GET " + url);

        String response = rest_client.get(url, token);

        log.debug("API Response: " + response);

        return gson.fromJson(response, new TypeToken<ArrayList<ActivationKey>>() {}.getType());
    }

    //===========================================
    // Private Functions
    //===========================================

    private static String getUrl(String ip_address) {
        return ip_address + "/api/activation_keys";
    }
}
