//===================================================================
// ListStorageDomains.java
//      Description:
//          This command pulls a list of Storage Domains from the
//          BlackPearl.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.command;

import com.spectralogic.blackpearl.nacre.api.BpConnector;
import com.spectralogic.blackpearl.nacre.model.StorageDomain;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ListStorageDomains {
    private static final Logger log = LoggerFactory.getLogger(ListStorageDomains.class);

    public static ArrayList<StorageDomain> all(BpConnector pearl) {
        ArrayList<StorageDomain> domain_list = new ArrayList<StorageDomain>();

        log.info("Retrieving a list of storage domains from BlackPeal.");

        try {
            domain_list = pearl.listStorageDomains();

            log.info("Found (" + domain_list.size() + ") storage domains.");
        } catch(Exception e) {
            log.error(e.getMessage());
            log.error("Failed to retrieve storage domains.");
        }

        return domain_list;
    }
}
