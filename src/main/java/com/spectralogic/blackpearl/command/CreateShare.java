//===================================================================
// CreateShare.java
//      Description:
//          This class handles the commands associated with creating
//          a share.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.command;

import com.spectralogic.blackpearl.nacre.api.BpConnector;
import com.spectralogic.blackpearl.nacre.model.Share;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateShare {
    private static final Logger log = LoggerFactory.getLogger(CreateShare.class);

    public static Share fromObject(Share share, BpConnector pearl) {
        log.info("Creating share [" + share.getName() + "]");
        Share new_share = null;

        try {
            new_share = pearl.createShare(share);
           
        } catch(Exception e) {
            log.error(e.getMessage());
            log.error("Failed to create share.");
        }

        return new_share;
    }
}
