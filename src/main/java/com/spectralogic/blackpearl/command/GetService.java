//===================================================================
// GetService.java
//      Description:
//          The purpose of this function is to retrieve a specific 
//          service from the BlackPearl. It performs a list of all
//          services and returns the desired one.
//
// Created by Sean Snyder
//===================================================================

package com.spectralogic.blackpearl.nacre.command;

import com.spectralogic.blackpearl.nacre.api.BpConnector;
import com.spectralogic.blackpearl.nacre.model.Service;
import com.spectralogic.blackpearl.nacre.model.ServiceS3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class GetService {
    private static final Logger log = LoggerFactory.getLogger(GetService.class);

    public static ServiceS3 s3(BpConnector pearl) {
        log.info("Retrieving S3 service information.");

        List<Service> service_list = ListServices.all(pearl);

        for(Service service : service_list) {
            if(service.getType().equals("DS3")) {
                log.info("Found S3 service.");
                return (ServiceS3) service;
            }
        }

        log.info("Failed to find S3 service.");
        return null;
    }
}
