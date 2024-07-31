//===================================================================
// StorageDomains.java
//      Description:
//          This class holds the API calls associated with storage
//          domains.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.api;

import com.spectralogic.blackpearl.nacre.model.ApiDataResponse;
import com.spectralogic.blackpearl.nacre.model.StorageDomain;
import com.spectralogic.vail.vapir.util.http.RestClient;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StorageDomains {
    private static final Logger log = LoggerFactory.getLogger(StorageDomains.class);

    public static StorageDomain create(StorageDomain domain, String ip_address, String token, RestClient rest_client) throws IOException, JsonParseException {
        Gson gson = new Gson();

        String url = getUrl(ip_address);
        String payload = gson.toJson(domain);

        log.debug("API URL: POST " + url);
        log.debug("API Payload: " + payload);

        String response = rest_client.post(url, token, payload);
        
        log.debug("API Response: " + response);

        return convertResponseToStorageDomain(response);
    }

    public static ArrayList<StorageDomain> list(String ip_address, String token, RestClient rest_client) throws IOException, JsonParseException {
        String url = getUrl(ip_address);

        log.debug("API URL: GET " + url);

        String response = rest_client.get(url, token);

        log.debug("API Response: " + response);

        return convertResponseToApiDataResponse(response).getData();
    }

    //===========================================
    // Private Functions
    //===========================================
    private static ApiDataResponse<StorageDomain> convertResponseToApiDataResponse(String response) throws JsonParseException {
        Gson gson = new Gson();

        ApiDataResponse<StorageDomain> data = gson.fromJson(response, new TypeToken<ApiDataResponse<StorageDomain>>() {}.getType());

        if(data == null || data.getData() == null) {
            throw new JsonParseException("Failed to convert response to ApiDataResponse object.");
        }

        return data;
    }
    
    private static StorageDomain convertResponseToStorageDomain(String response) throws JsonParseException {
        Gson gson = new Gson();

        StorageDomain domain = gson.fromJson(response, StorageDomain.class);

        if(domain == null || domain.getName() == null || domain.getName().length()==0) {
            throw new JsonParseException("Failed to convert response to StorageDomain object.");
        }

        return domain;
    }

    private static String getUrl(String ip_address) {
        return ip_address + "/api/ds3/storage_domains";
    }
}
