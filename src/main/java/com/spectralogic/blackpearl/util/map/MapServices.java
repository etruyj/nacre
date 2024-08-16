//===================================================================
// MapServices.java
//      Description:
//          This class creates maps related to the services object
//          and sub-class.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.util.map;

import com.spectralogic.blackpearl.nacre.model.Service;

import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MapServices {
    private static final Logger log = LoggerFactory.getLogger(MapServices.class);

    public static HashMap<String, Service> createNameServiceMap(ArrayList<Service> service_list) {
        HashMap<String, Service> service_map = new HashMap<String, Service>();
        String debug_output = "Mapped Services: ";

        for(Service service : service_list) {
            service_map.put(service.getName(), service);

            debug_output = debug_output + service.getName() + " ";
        }

        log.debug(debug_output);

        return service_map;
    }
}
