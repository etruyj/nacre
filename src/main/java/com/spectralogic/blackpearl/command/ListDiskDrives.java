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
import com.spectralogic.blackpearl.nacre.model.DriveTypeSummary;

import java.util.ArrayList;
import java.util.HashMap;

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

            if(all_drives != null) {
                for(DiskDrive drive : all_drives) {
                    // check to see if the drive exists (product != null)
                    // if the drive is not a bezel (bezel == false)
                    // and the drive isn't assigned to a pool.
                    if(drive.getPoolId() == null && drive.getProduct() != null && drive.getBezel() == false) {
                        drive_list.add(drive);
                    }
                }
            }
            log.info("Found (" + drive_list.size() + ") available data drives.");
        } catch(Exception e) {
            log.error(e.getMessage());
            log.error("Failed to list disk drives.");
        }

        return drive_list;
    }

    public static ArrayList<DiskDrive> availableData(BpConnector pearl) {
        log.info("Listing unassigned disk drives in the BlackPearl.");

        ArrayList<DiskDrive> all_drives;
        ArrayList<DiskDrive> drive_list = null;
        HashMap<String, ArrayList<DiskDrive>> drive_type_map = new HashMap<String, ArrayList<DiskDrive>>();
        String map_key = null;

        try {
            all_drives = all(pearl);
            
            if(all_drives != null) {
               for(DiskDrive drive : all_drives) {
                    // check to see if the drive exists (product != null)
                    // if the drive is not a bezel (bezel == false)
                    // and the drive isn't assigned to a pool.
                    if(drive.getPoolId() == null && drive.getProduct() != null && drive.getBezel() == false) {
                        map_key = drive.getInterface() + ":" + drive.getSpeed();

                        if(drive_type_map.get(map_key) != null) {
                            // Get the corresponding array list and add a drive
                            // to the referenced value without assignment. Java is fun.
                            drive_type_map.get(map_key).add(drive);
                        } else {
                            drive_list = new ArrayList<DiskDrive>();
                            drive_list.add(drive);
                            drive_type_map.put(map_key, drive_list);
                        }
                    }
                }
            }

            log.info("Found (" + drive_type_map.size() + ") different drive types.");
            log.info("Returning drive type with the largest number of drives.");
            drive_list = null;

            if(drive_type_map.size() > 0) {
                for(String key : drive_type_map.keySet()) {
                    // If the list is null (first iteration or the list is smaller than the map(key) list
                    // the new key is stored as the potential return value. 
                    if(drive_list == null || drive_list.size() < drive_type_map.get(key).size()) {
                        drive_list = drive_type_map.get(key);
                        map_key = key; // store this for logging.
                    }
                }
            
                log.info("Returning (" + drive_list.size() + ") " + map_key + " disk drives.");
            } else {
                log.warn("No data drive types are available.");
                return new ArrayList<DiskDrive>();
            }
        } catch(Exception e) {
            log.error(e.getMessage());
            log.error("Failed to list disk drives.");
        }

        return drive_list;
    }

    public static HashMap<String, DriveTypeSummary> summarizeAvailable(BpConnector pearl) {
        ArrayList<DiskDrive> drive_list = available(pearl);

        DriveTypeSummary info;

        HashMap<String, DriveTypeSummary> drive_count_map = new HashMap<String, DriveTypeSummary>();

        for(DiskDrive drive : drive_list) {
            if(drive_count_map.get(drive.getProduct()) == null) {
                info = new DriveTypeSummary();
                
                info.setSize(drive.getSize());
                info.setModel(drive.getProduct());
                info.setSpeed(drive.getSpeed());
                info.setInterfaceType(drive.getInterface());
                info.setCount(1);

                drive_count_map.put(info.getModel(), info);
            } else {
                info = drive_count_map.get(drive.getProduct());
                info.incrementCount();

                drive_count_map.put(info.getModel(), info);
            }
        }
    
        for(String key : drive_count_map.keySet()) {
            System.out.println(drive_count_map.get(key).getDriveType() + ":\t" + drive_count_map.get(key).getCount());
        }

        return drive_count_map;
    }
}
