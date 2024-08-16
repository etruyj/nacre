//===================================================================
// AddActivationKey.java
//      Description:
//          The purpose of this class is to add activation keys to
//          the BlackPearl system. There are two types of activation
//          keys, those that can just be applied and those that
//          require a server reset. The key type must be parsed
//          and this command has to wait until the system is back
//          online before adding the next key.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.command;

import com.spectralogic.blackpearl.nacre.api.BpConnector;
import com.spectralogic.blackpearl.nacre.model.ActivationKey;
import com.spectralogic.blackpearl.nacre.model.ActivationKeyConfig;
import com.spectralogic.blackpearl.nacre.model.DefaultsConfig;
import com.spectralogic.blackpearl.nacre.util.http.ServerStatus;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddActivationKey {
    private static final Logger log = LoggerFactory.getLogger(AddActivationKey.class);

    public static ActivationKey fromUserInput(String key, String key_name, DefaultsConfig defaults, BpConnector pearl) {
        log.info("Adding activation key [" + key + "] to BlackPearl.");

        ActivationKeyConfig new_key = new ActivationKeyConfig();

        new_key.setKey(key);
        new_key.setName(key_name);

        return fromObject(new_key, defaults, pearl);
    }

    public static ActivationKey fromObject(ActivationKeyConfig key, DefaultsConfig defaults, BpConnector pearl) {
        log.info("Adding activation key [" + key.getKey() + "] to BlackPearl.");
        boolean restart_required = false;

        ActivationKey raw_key = new ActivationKey();
        raw_key.setRawKey(key.getKey());

        try {
            raw_key = pearl.addActivationKey(raw_key);
            
            if(raw_key != null && defaults.getServer().getKeySettings().get(key.getName()) != null) {
                if(defaults.getServer()
                        .getKeySettings()
                        .get(key.getName())
                        .isRestartRequired()) {
                    log.warn("Key [" + key.getName() + "] requires system restart.");
                    restart_required = true;
                }
            } else if(raw_key == null) {
                log.error("Failed to add activation key [" + key.getKey() + "] to BlackPearl.");
            }
        
            if(restart_required) { // reboot required
                log.warn("Waiting for system to power down.");
//              waitForState("offline", pearl.getDomainName(), 30, 3000);
                log.warn("System has powered down.");
                waitForState("online", pearl.getDomainName(), 30, 3000);
                log.warn("Waiting for system to power on.");
            }
        } catch(Exception e) {
            log.error(e.getMessage());
            log.error("Failed to add activation key [" + key.getKey() + "] to BlackPearl.");
        }

        return raw_key;
    }

    private static void waitForState(String state, String url, int interval_seconds, int timeout_milliseconds) {
        log.info("Waiting for BlackPearl to be " + state);

        boolean desired_state = false;

        if(state.equals("offline")) {
            desired_state = false;
        } else { 
            desired_state = true;
        }

        try {
            while(ServerStatus.isOnline(url, timeout_milliseconds) != desired_state) {
                // Wait the desired interval and try again.
                log.debug("Waiting " + interval_seconds + " before next try");
                TimeUnit.SECONDS.sleep(interval_seconds);
            }
        } catch(Exception e) {
            log.error(e.getMessage());
        }
    }
}
