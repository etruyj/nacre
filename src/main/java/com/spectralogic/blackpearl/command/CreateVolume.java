//===================================================================
// CreateVolume.java
//      Description:
//          This class handles the command necessary to create the
//          a new Volume.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.command;

import com.spectralogic.blackpearl.nacre.api.BpConnector;
import com.spectralogic.blackpearl.nacre.model.Volume;
import com.spectralogic.blackpearl.nacre.model.VolumeConfig;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateVolume {
    private static final Logger log = LoggerFactory.getLogger(CreateVolume.class);

    public static Volume fromObject(Volume volume, BpConnector pearl) {
        log.info("Creating volume [" + volume.getName() + "]");

        Volume new_volume = null;

        try {
            new_volume = pearl.createVolume(volume);
        } catch(Exception e) {
            log.error(e.getMessage());
            log.error("Failed to create volume [" + volume.getName() + "]");
        }

        return new_volume;
    }
}
