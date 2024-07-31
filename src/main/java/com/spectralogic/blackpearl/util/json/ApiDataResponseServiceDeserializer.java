//===================================================================
// ApiDataResponseServiceDeserializer.java
//      Description:
//          This class handles deserialization of the ApiDataResponse
//          model when referencing the service super-class.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.util.json;

import com.spectralogic.blackpearl.nacre.model.ApiDataResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ApiDataResponseServiceDeserializer<T> implements JsonDeserializer<ApiDataResponse<T>> {
    private final Class<T> clazz;

    public ApiDataResponseServiceDeserializer(Class<T> clazz) {
        this.clazz = clazz;
    }
    
    @Override
    public ApiDataResponse<T> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject json_object = json.getAsJsonObject();
        ApiDataResponse<T> response = new ApiDataResponse<>();
        ArrayList<T> data = null;

        if(json_object.has("data")) {
            JsonElement data_object = json_object.get("data");

            if(data_object.isJsonArray()) {
                JsonArray data_array = json_object.getAsJsonArray("data");

                Type listType = TypeToken.getParameterized(ArrayList.class, clazz).getType();
                data = context.deserialize(data_array, listType);

                response.setData(data);
            } else if(data_object.isJsonObject()) {
                T json_data = context.deserialize(data_object.getAsJsonObject(), clazz);
                data = new ArrayList<>();
                data.add(json_data);

                response.setData(data);
            } else {
                throw new JsonParseException("Cannot determine if returned data is an object or an array.");
            }
        } else {
            throw new JsonParseException("Failed to convert JSON object to ApiDataResponse<Service>.class");
        }
        
        return response;
    }
}
