//===================================================================
// LoadActivationKeys.java
//      Description:
//          The purpose of this class is to read in a file of
//          activation keys and return them as a list to be added to
//          the configuration.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.command;

import com.spectralogic.blackpearl.nacre.model.ActivationKeyConfig;
import com.spectralogic.blackpearl.nacre.util.io.LoadFile;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoadActivationKeys {
    private static final Logger log = LoggerFactory.getLogger(LoadActivationKeys.class);

    public static ArrayList<ActivationKeyConfig> fromFile(String file_path) {
        log.info("Loading BlackPearl activation keys from file " + file_path);

        ArrayList<ActivationKeyConfig> key_list = new ArrayList<ActivationKeyConfig>();
        ActivationKeyConfig key = null;

        String[] key_parts;
        try {
            ArrayList<String> file_data = LoadFile.toList(file_path);

            log.debug("Read (" + file_data.size() + ") lines from the file.");

            for(String line : file_data) {
                key_parts = line.split(":");

                // Check to see if we loaded a key.
                if(key_parts.length == 2) {
                    key = new ActivationKeyConfig();

                    key.setName(key_parts[0].trim());
                    key.setKey(key_parts[1].trim());

                    key_list.add(key);
                } else {
                    log.debug("Line: [" + line + "] does not contain an activation key.");
                }
            }

        } catch(Exception e) {
            log.error(e.getMessage());
            log.error("Failed to load activation keys from file.");
        }

        log.info("Loaded (" + key_list.size() + ") activation keys.");

        return key_list;
    }
}
