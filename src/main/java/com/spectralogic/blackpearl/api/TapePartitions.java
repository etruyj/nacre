//===================================================================
// TapePartitions.java
//      Description:
//          This class handles API calls associated with tape 
//          partitions.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.api;

import com.spectralogic.blackpearl.nacre.model.ApiDataResponse;
import com.spectralogic.blackpearl.nacre.model.TapePartition;
import com.spectralogic.vail.vapir.util.http.RestClient;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TapePartitions {
    private static final Logger log = LoggerFactory.getLogger(TapePartitions.class);

    public static ArrayList<TapePartition> list(String ip_address, String token, RestClient rest_client) throws IOException, JsonParseException {
        String url = getUrl(ip_address);

        log.debug("API URL: GET " + url);

        String response = rest_client.get(url, token);

        log.debug("API Response: " + response);

        return convertResponseToApiDataResponse(response).getData();
    }

    //===========================================
    // Private Functions
    //===========================================
    private static ApiDataResponse<TapePartition> convertResponseToApiDataResponse(String response) throws JsonParseException {
        Gson gson = new Gson();

        ApiDataResponse<TapePartition> data = gson.fromJson(response, new TypeToken<ApiDataResponse<TapePartition>>() {}.getType());
        
        if(data == null || data.getData() == null || data.getData().size() == 0) {
            throw new JsonParseException("Failed to convert response to ApiDataResponse");
        }

        return data;
    }


    private static String getUrl(String ip_address) {
        return ip_address + "/api/tape_library_partitions";
    }
}
