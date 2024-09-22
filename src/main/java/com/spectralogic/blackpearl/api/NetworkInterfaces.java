//===================================================================
// NetworkInterfaces.java
//      Description:
//          This class handles API calls to the BlackPearl related to
//          the NetworkInterfaces.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.api;

import com.spectralogic.vail.vapir.util.http.RestClient;
import com.spectralogic.blackpearl.nacre.model.ApiDataResponse;
import com.spectralogic.blackpearl.nacre.model.NetworkInterface;
import com.spectralogic.blackpearl.nacre.model.NetworkInterfaceSend;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NetworkInterfaces {
    private static final Logger log = LoggerFactory.getLogger(NetworkInterfaces.class);

    public static NetworkInterface create(NetworkInterfaceSend interf, String ip_address, String token, RestClient rest_client) throws IOException, JsonParseException {
        Gson gson = new Gson();

        String url = getUrl(ip_address);
        String payload = gson.toJson(interf);

        log.debug("API URL: POST " + url);
        log.debug("API Payload: " + payload);

        String response = rest_client.post(url, token, payload);

        log.debug("API Response: " + response);

        NetworkInterface new_interf =  gson.fromJson(response, NetworkInterface.class);

        if(new_interf.getName() != null) {
            return new_interf;
        } else {
            return null;
        }
    }

    public static ArrayList<NetworkInterface> list(String ip_address, String token, RestClient rest_client) throws IOException, JsonParseException {
        Gson gson = new Gson();

        String url = getUrl(ip_address);

        log.debug("API URL: GET " + url);

        String response = rest_client.get(url, token);

        log.debug("API Response: " + response);

        ApiDataResponse data = gson.fromJson(response, new TypeToken<ApiDataResponse<NetworkInterface>>() {}.getType());

        return data.getData();
    }

    public static NetworkInterface put(NetworkInterfaceSend interf, String ip_address, String token, RestClient rest_client) throws IOException, JsonParseException {
        Gson gson = new Gson();

        String url = getUrl(ip_address) + "/" + interf.getId();
        String payload = gson.toJson(interf);

        log.debug("API URL: PUT " + url);
        log.debug("API Payload: " + payload);

        String response = rest_client.put(url, token, payload);

        log.debug("API Response: " + response);

        NetworkInterface new_interf =  gson.fromJson(response, NetworkInterface.class);

        if(new_interf.getName() != null) {
            return new_interf;
        } else {
            return null;
        }
    }

    //===========================================
    // Private Functions
    //===========================================
    private static String getUrl(String ip_address) {
        return ip_address + "/api/network_interfaces";
    }
}
