//===================================================================
// Ds3DiskPartitions.java
//      Description:
//          This command handles the API calls associated with
//          the DS3 disk partitions (aka Pool Partitions).
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.api;

import com.spectralogic.vail.vapir.util.http.RestClient;
import com.spectralogic.blackpearl.nacre.model.ApiDataResponse;
import com.spectralogic.blackpearl.nacre.model.DiskPartition;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Ds3DiskPartitions {
    private static final Logger log = LoggerFactory.getLogger(Ds3DiskPartitions.class);

    public static ArrayList<DiskPartition> list(String ip_address, String token, RestClient rest_client) throws IOException, JsonParseException {
        Gson gson = new Gson();

        String url = getUrl(ip_address);

        log.debug("API URL: GET " + url);

        String response = rest_client.get(url, token);

        log.debug("API Response: " + response);

        ApiDataResponse data = gson.fromJson(response, new TypeToken<ApiDataResponse<DiskPartition>>() {}.getType());

        return data.getData();
    }

    //===========================================
    // Private Functions
    //===========================================
    private static String getUrl(String ip_address) {
        return ip_address + "/api/ds3/pool_partitions";
    }
}
