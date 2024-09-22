//===================================================================
// UpdateNtpServers.java
//      Description:
//          This class updates the Ntp Server settings in the BlackPearl
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

public class UpdateNtpServers {
    private static final Logger log = LoggerFactory.getLogger(UpdateNtpServers.class);

    public static NtpSettings setServers(String server1, String server2, BpConnector pearl) {
        log.info("Configuring Ntp Servers. Setting addresses server 1 [" + server1 + "] and server 2 [" + server2 + "]");
    
        NtpSettings settings = ListNtpServers.getSettings(pearl);
        ArrayList<String> servers = new ArrayList<String>();        


        // If server1 input is set, use this value to set the new address.
        // Else use the existing value.
        // If server values equal clear or none, clear the field.
        if(server1 != null) {
            if(!(server1.equals("clear") || server1.equals("none"))) {
                log.info("Setting NTP server 1 to " + server1);
                servers.add(server1);
            } else {
                log.info("Clearing NTP server 1 configuration.");
            }
        } else if(settings.getAddresses().size() > 0) {
            log.info("Server 1 is not set. Using existing value " + settings.getAddresses().get(0));
            servers.add(settings.getAddresses().get(0)); // add the original server 1
        }

        // If server2 input is set, use this value to set the new address.
        // Else use the existing value.
        // If server values equal clear or none, clear the field.
        if(server2 != null) {
            if(!(server2.equals("clear") || server2.equals("none"))) {
                log.info("Setting NTP server 2 to " + server2);
                servers.add(server2);
            } else {
                log.info("Clearing NTP server 2 configuration.");
            }
        } else if(settings.getAddresses().size() > 1) {
            log.info("Server 2 is not set. Using existing value " + settings.getAddresses().get(1));
            servers.add(settings.getAddresses().get(1)); // add the original server 2
        }

        // Update the servers if necessary.
        try {
            if(servers.size() > 0) {
                settings.setAddresses(servers);

                if(servers.size() >= 1) {
                    settings.setNtpServer1(server1);
                }

                if(servers.size() == 2) {
                    settings.setNtpServer2(server2);
                }

                settings.setNtpTime(true);
                settings.setSynchronized(true);

                settings = pearl.updateNtpServers(settings);

                log.info("Successfully updated NTP settings.");
            
                return settings;
            } else if(servers.size() == 0 && settings.getAddresses().size() > 0) {
                // clear the NTP settings.
                throw new Exception("Cannot clear NTP settings.");
            }
        } catch(Exception e) {
            log.error(e.getMessage());
            log.error("Failed to update NTP server settings.");
        }

        return null;
    }
}
