//===================================================================
// Messages.java
//      Description:
//          This class handles API calls to the BlackPearl related to
//          ds3 users.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.api;

import com.spectralogic.blackpearl.nacre.model.ApiDataResponse;
import com.spectralogic.blackpearl.nacre.model.Message;
import com.spectralogic.vail.vapir.util.http.RestClient;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Messages {
    private static final Logger log = LoggerFactory.getLogger(Messages.class);

    public static ArrayList<Message> list(String ip_address, String token, RestClient rest_client) throws IOException, JsonParseException {
        Gson gson = new Gson();

        String url = getUrl(ip_address);

        log.debug("API URL: GET " + url);

        String response = rest_client.get(url, token);

        log.debug("API Response: " + response);

        ApiDataResponse results = gson.fromJson(response, new TypeToken<ApiDataResponse<Message>>() {}.getType());

        return results.getData();
    }

    //===========================================
    // Private Functions
    //===========================================

    private static String getUrl(String ip_address) {
        return ip_address + "/api/messages";
    }
}
