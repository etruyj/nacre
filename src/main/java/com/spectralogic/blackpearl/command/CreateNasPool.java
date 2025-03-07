//===================================================================
// CreateNasPool.java
//      Description:
//          This class handles the command necessary to create the
//          a new NAS Pool.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.command;

import com.spectralogic.blackpearl.nacre.api.BpConnector;
import com.spectralogic.blackpearl.nacre.model.DiskDrive;
import com.spectralogic.blackpearl.nacre.model.Pool;
import com.spectralogic.blackpearl.nacre.model.PoolConfig;
import com.spectralogic.blackpearl.nacre.model.Stripe;

import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateNasPool {
    private static final Logger log = LoggerFactory.getLogger(CreateNasPool.class);

    public static Pool fromPoolConfig(PoolConfig pool, Pool defaults, BpConnector pearl) {
        log.info("Building pool from configuration.");
        Pool new_pool = null;

        System.err.println("Drive count: " + pool.getDriveCount());

        ArrayList<DiskDrive> available_drives = ListDiskDrives.available(pearl);
        Stripe stripe = null;
        int drives_assigned = 0; // how many drives were assigned
        int drive_iterator = 0; // what drive is currently being assigned.
        int stripe_size = 0; // the number of drives in a stripe
        int stripe_counter = 0; // the number of drives assigned to a stripe.
        long pool_available = 0; // available space in bytes for the pool
        long pool_overhead = 0; // overhead space in bytes for the pool
        long pool_total = 0; // total (raw) size of the pool

        try {   
            // Load the default parameters
            // Boolean values can't be defaults as
            // omitting those fields will automatically set them as false.
            new_pool = new Pool(defaults);

            // Poplate the configuration fields
            new_pool.setName(pool.getName());
            new_pool.setStripes(pool.getStripes());
            new_pool.setProtection(pool.getProtectionLevel());
            
            // Check for potential options
            if(pool.getType() != null) {
                new_pool.setType(pool.getType());
            }


            if(pool.getPowerSavingMode() != null) {
                new_pool.setPowerSavingMode(pool.getPowerSavingMode());
            }


            if(pool.getHighWaterMark() != null) {
                new_pool.setHighWaterMark(pool.getHighWaterMark());
            }


            if(pool.getEncryptionState() != null) {
                new_pool.setEncryptionState(pool.getEncryptionState());
            }

            // Assign Drives to Pool
            // reset to 0
            drives_assigned = 0;
            pool_available = 0;
            pool_overhead = 0;
            pool_total = 0;
            stripe_size = pool.getDriveCount() / pool.getStripes();
            stripe_counter = 0;

            log.debug("Assigning drives for pool [" + new_pool.getName() + "]");
            log.debug("Stripe size: " + stripe_size);
    
            while(drives_assigned < pool.getDriveCount() && drive_iterator < available_drives.size()) {
                log.info("Searching for drive " + drives_assigned + " which belongs to stripe slot " + stripe_counter);
                new_pool.addDiskId(available_drives.get(drive_iterator).getId());
                pool_total += available_drives.get(drive_iterator).getSize();

                //==== Build each stripe ====
                // Create a new one when the stripe counter is reset to 0
                if(stripe_counter == 0) {
                    stripe = new Stripe();
                    stripe.setType(pool.getProtectionLevel());
                }

                stripe.addDiskDrive(available_drives.get(drive_iterator));

                stripe_counter++;

                // Close out the stripe
                if(stripe_counter >= stripe_size) {
                    new_pool.addStripe(stripe);
                    stripe_counter = 0;
                    
                    log.debug("Adding stripe to pool. Current stripe count: " + new_pool.getTopology().size());
                }
                //==== END STRIPE ====
    
                // Increment Iterator
                drive_iterator++;
                drives_assigned++;
            }

            // Calculate capacities
            // use the first disk in the topology as the reference size
            log.debug("Calculating available capacity and pool overhead with protect level " + pool.getProtectionLevel());
            log.debug("Stripe count: " + new_pool.getTopology().size());
            log.debug("Stripe 0 disk drive count: " + new_pool.getTopology().get(0).getChildren().size());

            switch(new_pool.getProtection()) {
                case "none":
                    pool_available = pool_total;
                    pool_overhead = 0;
                    break;
                case "mirror":
                    pool_available = pool_total / 2;
                    pool_overhead = pool_available;
                    break;
                case "single":
                    pool_overhead = (new_pool.getTopology().size() * new_pool.getTopology()
                            .get(0)
                            .getChildren()
                            .get(0)
                            .getSize()) * 1;

                    pool_available = pool_total - pool_overhead;
                    break;
                case "double":
                    pool_overhead = (new_pool.getTopology().size() * new_pool.getTopology()
                            .get(0)
                            .getChildren()
                            .get(0)
                            .getSize()) * 2;
                    
                    pool_available = pool_total - pool_overhead;
                    break;
                case "triple":
                    pool_overhead = (new_pool.getTopology().size() * new_pool.getTopology()
                            .get(0)
                            .getChildren()
                            .get(0)
                            .getSize()) * 3;
                    
                    pool_available = pool_total - pool_overhead;
                    break;
            }

            new_pool.setAvailable(pool_available);
            new_pool.setOverhead(pool_overhead);
            new_pool.setUsed(0L); // set used capacity to 0 but a long version of 0.

            log.debug("Pool [" + new_pool.getName() + "] will have " + pool_available + " bytes available.");
        
            // Check to make sure all the required drives were assigned.
            if(drives_assigned == pool.getDriveCount()) {
                new_pool = fromObject(new_pool, pearl);

            } else {
                throw new Exception("Unable to create pool. Not enough drives available.");
            }
        } catch(Exception e) {
            log.error(e.getMessage());
            log.error("Failed to create pool [" + pool.getName() + "]");
        }

        return new_pool;
    }

    @Deprecated // moved to CreatePool.class
    public static Pool fromObject(Pool pool, BpConnector pearl) {
        log.info("Creating NAS pool [" + pool.getName() + "]");

        Pool new_pool = null;

        try {
            new_pool = pearl.createPool(pool);

        } catch(Exception e) {
            log.error(e.getMessage());
            log.error("Failed to create pool [" + pool.getName() + "]");
        }

        return pool;
    }
}
