//===================================================================
// CreateStorageDomain.java
//      Description:
//          This class handles the commands associated with creating
//          a storage domain.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.command;

import com.spectralogic.blackpearl.nacre.api.BpConnector;
import com.spectralogic.blackpearl.nacre.model.StorageDomain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateStorageDomain {
    private static final Logger log = LoggerFactory.getLogger(CreateStorageDomain.class);

    public static StorageDomain fromObject(StorageDomain domain, BpConnector pearl) {
        log.info("Creating storage domain [" + domain.getName() + "]");
        StorageDomain new_domain = null;

        try {
            new_domain = pearl.createStorageDomain(domain);
           
        } catch(Exception e) {
            log.error(e.getMessage());
            log.error("Failed to create storage domain.");
        }

        return new_domain;
    }
}
