//===================================================================
// EnableSsh.java
//      Description:
//          This function enables SSH on a BlackPearl by adding the
//          activation key, then updating the Administrator user to
//          allow SSH access to the hardware.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.command;

import com.spectralogic.blackpearl.nacre.api.BpConnector;
import com.spectralogic.blackpearl.nacre.model.ActivationKey;
import com.spectralogic.blackpearl.nacre.model.Ds3User;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnableSsh {
    private static final Logger log = LoggerFactory.getLogger(EnableSsh.class);

    public static boolean withActivationKey(ActivationKey key, BpConnector pearl) {
        log.info("Enabling SSH with activation key: " + key.getRawKey());

        try {
            log.info("Adding remote support activation key to BlackPearl.");
            pearl.addActivationKey(key);
            
            List<Ds3User> user_list = ListUsers.all(pearl);
           
            // Search for Admin user.
            Ds3User admin = null;

            for(Ds3User user : user_list) {
                if(user.getUsername().equals("Administrator")) {
                    admin = user;
                }
            } 

            if(admin != null) {
                log.info("Enabling remote support functionality.");
                admin.setRemoteSupportEnabled(true);
                admin = pearl.updateUser(admin);

                System.err.println("Remote support: " + admin.getId());

                if(admin == null) {
                    throw new Exception("Failed to set remote support enabled on admin user.");
                } else if(!admin.isRemoteSupportEnabled()) {
                    log.warn("Failed to apply remote support to user: Administrator");
                    throw new Exception("Failed to activate remote support on admin user.");
                } else {
                    log.warn("Remote support enabled.");
                }
            }
            
            return true;
        } catch(Exception e) {
            log.error(e.getMessage());
            log.error("Failed to enable SSH on BlackPearl.");
            return false;
        }
    }
}
