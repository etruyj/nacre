//===================================================================
// ListVolumes.java
//      Description:
//          This class handles commands associated with list volumes.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.command;

import com.spectralogic.blackpearl.nacre.api.BpConnector;
import com.spectralogic.blackpearl.nacre.model.Volume;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ListVolumes {
    private static final Logger log = LoggerFactory.getLogger(ListVolumes.class);

    public static ArrayList<Volume> all(BpConnector pearl) {
        log.info("Listing all volumes in the BlackPearl.");

        ArrayList<Volume> volume_list = new ArrayList<Volume>();

        try {
            volume_list = pearl.listVolumes();

            log.info("Found (" + volume_list.size() + ") volumes.");
        } catch(Exception e) {
            log.error(e.getMessage());
            log.error("Failed to list volumes.");
        }

        return volume_list;
    }
}
