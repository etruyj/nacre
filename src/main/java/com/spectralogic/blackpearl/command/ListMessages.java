//===================================================================
// ListMessages.java
//      Description:
//          This command provides a list of all the Ds3 Messages in a
//          BlackPearl.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.command;

import com.spectralogic.blackpearl.nacre.api.BpConnector;
import com.spectralogic.blackpearl.nacre.model.Message;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ListMessages {
    private static final Logger log = LoggerFactory.getLogger(ListMessages.class);

    public static ArrayList<Message> all(BpConnector pearl) {
        log.info("Listing all messages in the BlackPearl.");

        ArrayList<Message> message_list = new ArrayList<Message>();
        try {
            message_list = pearl.listMessages();

            log.info("Found (" + message_list.size() + ") messages.");
        } catch(Exception e) {
            log.error(e.getMessage());
            log.error("Failed to list messages.");
        }

        return message_list;
    }
}
