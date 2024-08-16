//===================================================================
// StringOrArrayDeserializer.java
//      Description:
//          This customer deserializer can process whether the input
//          value is a string or an array and return the result
//          as an array.
//
// Create by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.util.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class StringOrArrayDeserializer implements JsonDeserializer<List<String>> {

    @Override
    public List<String> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        List<String> result = new ArrayList<>();

        if (json.isJsonArray()) {
            JsonArray array = json.getAsJsonArray();
            for (JsonElement element : array) {
                result.add(element.getAsString());
            }
        } else if (json.isJsonPrimitive()) {
            result.add(json.getAsString());
        } else {
            throw new JsonParseException("Unexpected JSON type: " + json.getClass());
        }

        return result;
    }
}

