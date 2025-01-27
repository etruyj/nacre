//===================================================================
// ListNtpServers.java
//      Description:
//          This class lists the Ntp Server settings in the BlackPearl
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.command;

import com.spectralogic.blackpearl.nacre.api.BpConnector;
import com.spectralogic.blackpearl.nacre.model.NtpSettings;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ListNtpServers {
    private static final Logger log = LoggerFactory.getLogger(ListNtpServers.class);

    public static ArrayList<String> getServers(BpConnector pearl) {
        NtpSettings settings = getSettings(pearl);

        log.info("System has " + settings.getAddresses().size() + " servers configured.");
        return settings.getAddresses();
    }

    public static NtpSettings getSettings(BpConnector pearl) {
        log.info("Fetching NTP server information.");
        NtpSettings settings = null;

        try {
            ArrayList<NtpSettings> settings_list = pearl.listNtpServers();
            
            // A list of 1 object is always returned.
            // Check to see if there is an object and grab the first (only) one.
            if(settings_list.size() > 0) {
                settings = settings_list.get(0);
            }

            if(settings != null) {
                log.info("Retrieved NTP server info.");
                return settings;
            } else {
                log.error("Couldn't find NTP information.");
                return null;
            }
        } catch(Exception e) {
            log.error(e.getMessage());
            log.error("Failed to retrieve NTP information.");
            return null;
        }
    }
}
