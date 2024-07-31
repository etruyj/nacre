//===================================================================
// AddStorageDomainMember.java
//      Description:
//          This class handles commands for adding storage domain
//          members to a storage domain in the BlackPearl
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.command;

import com.spectralogic.blackpearl.nacre.api.BpConnector;
import com.spectralogic.blackpearl.nacre.model.StorageDomainMember;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddStorageDomainMember {
    private static final Logger log = LoggerFactory.getLogger(AddStorageDomainMember.class);

    public static StorageDomainMember fromObject(StorageDomainMember member, BpConnector pearl) {
        log.info("Adding [" + member.getTapeType() + "] from partition [" + member.getTapePartitionId() + "] to storage domain [" + member.getStorageDomainId());

        StorageDomainMember new_member = null;
        try {
            new_member = pearl.addStorageDomainMember(member);
        } catch(Exception e) {
            log.error(e.getMessage());
            log.error("Failed to add storage domain member to storage domain.");
        }

        return new_member;
    }
}
