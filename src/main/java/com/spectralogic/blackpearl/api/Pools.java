//===================================================================
// Pools.java
//      Description:
//          This class handles API calls to the BlackPearl related to
//          the Pools.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.api;

import com.spectralogic.vail.vapir.util.http.RestClient;
import com.spectralogic.blackpearl.nacre.model.ApiDataResponse;
import com.spectralogic.blackpearl.nacre.model.Pool;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Pools {
    private static final Logger log = LoggerFactory.getLogger(Pools.class);

    public static Pool create(Pool pool, String ip_address, String token, RestClient rest_client) throws IOException, JsonParseException {
        Gson gson = new Gson();

        String url = getUrl(ip_address);
        String payload = gson.toJson(pool);

        log.debug("API URL: POST " + url);
        log.debug("API Payload: " + payload);

        String response = rest_client.post(url, token, payload);

        log.debug("API Response: " + response);

        Pool new_pool =  gson.fromJson(response, Pool.class);
    
        // Check if pool was really created or just a null reference
        if(new_pool.getName() != null) {
            return new_pool;
        } else {
            return null;
        }
    }

    public static ArrayList<Pool> list(String ip_address, String token, RestClient rest_client) throws IOException, JsonParseException {
        Gson gson = new Gson();

        String url = getUrl(ip_address);

        log.debug("API URL: GET " + url);

        String response = rest_client.get(url, token);

        log.debug("API Response: " + response);

        ApiDataResponse data = gson.fromJson(response, new TypeToken<ApiDataResponse<Pool>>() {}.getType());

        return data.getData();
    }

    //===========================================
    // Private Functions
    //===========================================
    private static String getUrl(String ip_address) {
        return ip_address + "/api/pools";
    }
}
