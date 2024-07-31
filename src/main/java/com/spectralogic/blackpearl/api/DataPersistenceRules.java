//===================================================================
// DataPersistenceRules.java
//      Description:
//          This class handles API calls related to the BlackPearl's
//          data persistence rules.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.api;

import com.spectralogic.blackpearl.nacre.model.DataPersistenceRule;
import com.spectralogic.vail.vapir.util.http.RestClient;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataPersistenceRules {
    private static final Logger log = LoggerFactory.getLogger(DataPersistenceRules.class);

    public static DataPersistenceRule add(DataPersistenceRule rule, String ip_address, String token, RestClient rest_client) throws IOException, JsonParseException {
        Gson gson = new Gson();

        String url = getUrl(ip_address);
        String payload = gson.toJson(rule);

        log.debug("API URL: POST " + url);
        log.debug("API Payload: " + payload);

        String response = rest_client.post(url, token, payload); 

        log.debug("API Response: " + response);

        return gson.fromJson(response, DataPersistenceRule.class);
    }

    //===========================================
    // Private Functions
    //===========================================

    private static String getUrl(String ip_address) {
        return ip_address + "/api/ds3/data_persistence_rules";
    }
}
