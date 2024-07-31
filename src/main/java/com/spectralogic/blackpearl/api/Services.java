//===================================================================
// Services.java
//      Description:
//          This class handles API calls to the BlackPearl regarding
//          the different services.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.api;

import com.spectralogic.blackpearl.nacre.model.ApiDataResponse;
import com.spectralogic.blackpearl.nacre.model.Service;
import com.spectralogic.blackpearl.nacre.model.ServiceS3;
import com.spectralogic.blackpearl.nacre.util.json.ApiDataResponseServiceDeserializer;
import com.spectralogic.blackpearl.nacre.util.json.ServiceDeserializer;
import com.spectralogic.vail.vapir.util.http.RestClient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Services {
    private static final Logger log = LoggerFactory.getLogger(Services.class);

    public static ArrayList<Service> list(String ip_address, String token, RestClient rest_client) throws IOException, JsonParseException {
        GsonBuilder gson_builder = new GsonBuilder();
        
        // Register the serializer for the service class.
        gson_builder.registerTypeAdapter(Service.class, new ServiceDeserializer());
            
        // Register the serializer for the ApiDataResponse class.
        Type apiDataResponseType = TypeToken.getParameterized(ApiDataResponse.class, Service.class).getType();
        gson_builder.registerTypeAdapter(apiDataResponseType, new ApiDataResponseServiceDeserializer<>(Service.class));

        Gson gson = gson_builder.create();

        String url = getUrl(ip_address);

        log.debug("API URL: GET " + url);

        String response = rest_client.get(url, token);

        log.debug("API Response: " + response);

        ApiDataResponse data = gson.fromJson(response, apiDataResponseType);
        
        return data.getData();
    }

    public static ServiceS3 putDatabaseBackup(ServiceS3 s3_service, String ip_address, String token, RestClient rest_client) throws IOException, JsonParseException {
        GsonBuilder gson_builder = new GsonBuilder();
        
        // Register the serializer for the service class.
        gson_builder.registerTypeAdapter(Service.class, new ServiceDeserializer());
            
        // Register the serializer for the ApiDataResponse class.
        Type apiDataResponseType = TypeToken.getParameterized(ApiDataResponse.class, Service.class).getType();
        gson_builder.registerTypeAdapter(apiDataResponseType, new ApiDataResponseServiceDeserializer<>(Service.class));

        Gson gson = gson_builder.create();

        String url = getUrl(ip_address) + "/" + s3_service.getId();
        String payload = gson.toJson(s3_service);

        log.debug("API URL: PUT " + url);
        log.debug("API Payload: " + payload);

        String response = rest_client.put(url, token, payload);

        log.debug("API Response: " + response);

        ApiDataResponse<Service> data = gson.fromJson(response, apiDataResponseType);

        return (ServiceS3) data.getData().get(0);
    }

    //===========================================
    // Private Functions
    //===========================================
    private static String getUrl(String ip_address) {
        return ip_address + "/api/services";
    }
}
