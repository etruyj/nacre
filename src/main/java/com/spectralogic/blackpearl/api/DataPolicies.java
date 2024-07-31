//===================================================================
// DataPolicies.java
//      Description:
//          This class handles API calls related to data policies.
//===================================================================

package com.spectralogic.blackpearl.nacre.api;

import com.spectralogic.blackpearl.nacre.model.ApiDataResponse;
import com.spectralogic.blackpearl.nacre.model.DataPolicy;
import com.spectralogic.vail.vapir.util.http.RestClient;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataPolicies {
    private static final Logger log = LoggerFactory.getLogger(DataPolicies.class);

    public static DataPolicy create(DataPolicy policy, String ip_address, String token, RestClient rest_client) throws IOException, JsonParseException {
        Gson gson = new Gson();

        String url = getUrl(ip_address);
        String payload = gson.toJson(policy);

        log.debug("API URL: POST " + url);
        log.debug("API Payload: " + payload);

        String response = rest_client.post(url, token, payload);

        log.debug("API Response: " + response);

        return convertDataPolicyFromJson(response);
    }

    public static ArrayList<DataPolicy> list(String ip_address, String token, RestClient rest_client) throws IOException, JsonParseException {
        Gson gson = new Gson();

        String url = getUrl(ip_address);

        log.debug("API URL: GET " + url);

        String response = rest_client.get(url, token);

        log.debug("API Response: " + response);

        ApiDataResponse<DataPolicy> results = gson.fromJson(response, new TypeToken<ApiDataResponse<DataPolicy>>() {}.getType());
        return results.getData();
        //return gson.fromJson(response, new TypeToken<Ap<DataPolicy>>() {}.getType());
    }

    //===========================================
    // Private Functions
    //===========================================

    private static String getUrl(String ip_address) {
        return ip_address + "/api/ds3/data_policies";
    }

    private static DataPolicy convertDataPolicyFromJson(String response) throws JsonParseException {
        Gson gson = new Gson();

        DataPolicy policy = gson.fromJson(response, DataPolicy.class);

        if(policy == null || policy.getName() == null) {
            throw new JsonParseException("Failed to convert response to data policy object.");
        }

        return policy;
    }
}
