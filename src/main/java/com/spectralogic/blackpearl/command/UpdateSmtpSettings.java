//===================================================================
// UpdateSmtpSettings.java
//      Description:
//          The purpose of this class is to update the Smtp settings
//          in the BlackPearl.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.command;

import com.spectralogic.blackpearl.nacre.api.BpConnector;
import com.spectralogic.blackpearl.nacre.model.SmtpSettings;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UpdateSmtpSettings {
    private static final Logger log = LoggerFactory.getLogger(UpdateSmtpSettings.class);

    public static SmtpSettings fromObject(SmtpSettings settings, BpConnector pearl) {
        log.info("Updating SMTP settings from object.");

        SmtpSettings new_settings = null;
        try {
            new_settings = pearl.updateSmtpSettings(settings);

            if(new_settings != null) {
                log.info("Successfully updated SMTP server settings.");
            } else {
                log.info("Failed to update SMTP settings. No response from server.");
            }
        } catch(Exception e) {
            log.error(e.getMessage());
            log.error("Failed to update SMTP settings.");
        }

        return new_settings;
    }
}
