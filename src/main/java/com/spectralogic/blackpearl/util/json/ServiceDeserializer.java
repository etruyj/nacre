//===================================================================
// ServiceDeserializer.java
//      Description:
//          This is a customer deserializer for Google Gson to allow
//          the deserialization of the different service objects
//          returned by the GET services API call.
//          
//          Each of the Service classes need to be imported into this
//          class and specific fields that are consistently present 
//          are used to determine which class the object belongs to.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.util.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import com.spectralogic.blackpearl.nacre.model.Service;
import com.spectralogic.blackpearl.nacre.model.ServiceCifs;
import com.spectralogic.blackpearl.nacre.model.ServiceNfs;
import com.spectralogic.blackpearl.nacre.model.ServiceS3;

import java.lang.reflect.Type;

public class ServiceDeserializer implements JsonDeserializer<Service> {
    @Override
    public Service deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        if (jsonObject.has("iom_enabled")) {
            return context.deserialize(jsonObject, ServiceS3.class);
        } else if(jsonObject.has("local_status")) {
            return context.deserialize(jsonObject, ServiceCifs.class);
        } else if(jsonObject.has("threads")) {
            return context.deserialize(jsonObject, ServiceNfs.class);
        } else {
            return new Service();
        }
    }
}
/*
public class ServiceDeserializer implements JsonDeserializer<Service> {
    @Override
    public Service deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        if(jsonObject.has("iom_enabled")) {
            return context.deserialize(jsonObject, ServiceS3.class);
        } else {
            return context.deserialize(jsonObject, Service.class);
        }
    }
} */
