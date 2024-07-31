//===================================================================
// ListServices.java
//      Description:
//          This command pulls a list of services from the BlackPearl.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.command;

import com.spectralogic.blackpearl.nacre.api.BpConnector;
import com.spectralogic.blackpearl.nacre.model.Service;
import com.spectralogic.blackpearl.nacre.model.ServiceS3;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ListServices {
    private static final Logger log = LoggerFactory.getLogger(ListServices.class);

    public static ArrayList<Service> all(BpConnector pearl) {
        log.info("Listing all BlackPearl services.");

        ArrayList<Service> service_list = new ArrayList<Service>();

        try {
            service_list = pearl.listServices();
            log.info("Found (" + service_list.size() + ") services.");
        } catch(Exception e) {
            log.error(e.getMessage());
            log.error("Failed to list services.");
        }

        return service_list;
    }
}
