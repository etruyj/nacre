//===================================================================
// ListUsers.java
//      Description:
//          This command provides a list of all the Ds3 Users in a
//          BlackPearl.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.command;

import com.spectralogic.blackpearl.nacre.api.BpConnector;
import com.spectralogic.blackpearl.nacre.model.Ds3User;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ListUsers {
    private static final Logger log = LoggerFactory.getLogger(ListUsers.class);

    public static ArrayList<Ds3User> all(BpConnector pearl) {
        log.info("Listing all users in the BlackPearl.");

        ArrayList<Ds3User> user_list = new ArrayList<Ds3User>();
        try {
            user_list = pearl.listUsers();

            log.info("Found (" + user_list.size() + ") users.");
        } catch(Exception e) {
            log.error(e.getMessage());
            log.error("Failed to list users.");
        }

        return user_list;
    }
}
