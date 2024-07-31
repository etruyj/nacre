//===================================================================
// ListDiskDrives.java
//      Description:
//          This command provides lists of data disks from the Black
//          BlackPearl.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.command;

import com.spectralogic.blackpearl.nacre.api.BpConnector;
import com.spectralogic.blackpearl.nacre.model.DiskDrive;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ListDiskDrives {
    private static final Logger log = LoggerFactory.getLogger(ListDiskDrives.class);

    public static ArrayList<DiskDrive> all(BpConnector pearl) {
        log.info("Listing disk drives in the BlackPearl.");
        ArrayList<DiskDrive> drive_list = new ArrayList<DiskDrive>();

        try {
            drive_list = pearl.listDataDisks();

            log.info("Found (" + drive_list.size() + ") disk drives.");
        } catch(Exception e) {
            log.error(e.getMessage());
            log.error("Failed to list disk drives.");
        }

        return drive_list;
    }

    public static ArrayList<DiskDrive> available(BpConnector pearl) {
        log.info("Listing unassigned disk drives in the BlackPearl.");

        ArrayList<DiskDrive> all_drives;
        ArrayList<DiskDrive> drive_list = new ArrayList<DiskDrive>();

        try {
            all_drives = pearl.listDataDisks();

            for(DiskDrive drive : all_drives) {
                // check to see if the drive exists (product != null)
                // if the drive is not a bezel (bezel == false)
                // and the drive isn't assigned to a pool.
                if(drive.getPoolId() == null && drive.getProduct() != null && drive.getBezel() == false) {
                    drive_list.add(drive);
                }
            }

            log.info("Found (" + drive_list.size() + ") available data drives.");
        } catch(Exception e) {
            log.error(e.getMessage());
            log.error("Failed to list disk drives.");
        }

        return drive_list;
    }
}
