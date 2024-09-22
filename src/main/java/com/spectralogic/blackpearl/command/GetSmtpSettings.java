//===================================================================
// ListSmtpSettings.java
//      Description:
//          This class lists the Ntp Server settings in the BlackPearl
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.command;

import com.spectralogic.blackpearl.nacre.api.BpConnector;
import com.spectralogic.blackpearl.nacre.model.SmtpSettings;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetSmtpSettings {
    private static final Logger log = LoggerFactory.getLogger(GetSmtpSettings.class);

    public static SmtpSettings getSettings(BpConnector pearl) {
        log.info("Fetching SMTP server information.");

        try {
            SmtpSettings settings = pearl.getSmtpSettings();

            if(settings != null) {
                log.info("Retrieved SMTP server info.");
                return settings;
            } else {
                log.error("Couldn't find SMTP information.");
                return null;
            }
        } catch(Exception e) {
            log.error(e.getMessage());
            log.error("Failed to retrieve SMTP information.");
            return null;
        }
    }
}
