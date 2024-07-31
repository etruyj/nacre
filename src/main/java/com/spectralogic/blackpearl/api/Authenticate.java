//===================================================================
// Authenticate.java
//      Description:
//          This class handles the login API calls to the BlackPearl.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.api;

import com.spectralogic.blackpearl.nacre.model.Token;
import com.spectralogic.vail.vapir.util.http.RestClient;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Authenticate {
    private static final Logger log = LoggerFactory.getLogger(Authenticate.class);

    public static String getToken(String user, String password, String ip_address, RestClient rest_client) throws IOException, JsonParseException {
        Gson gson = new Gson();

        String url = getUrl(ip_address);

        log.debug("API URL: POST " + url);
        
        String payload = "{\"username\":\"" + user + "\", "
                    + "\"password\": \"" + password + "\"}";

        String response = rest_client.post(url, payload);

        log.debug("API Response: " + response);

        return gson.fromJson(response, Token.class).getToken();
    }

    //===========================================
    // Private Functions
    //===========================================

    private static String getUrl(String address) {
        return address + "/api/tokens.json";
    }
}
