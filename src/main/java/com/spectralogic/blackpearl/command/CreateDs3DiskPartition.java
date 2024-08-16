//===================================================================
// CreateDs3DiskPartition.java
//      Description:
//          This class handles the command necessary to create the
//          a new Ds3DiskPartition.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.command;

import com.spectralogic.blackpearl.nacre.api.BpConnector;
import com.spectralogic.blackpearl.nacre.model.DiskPartition;
import com.spectralogic.blackpearl.nacre.model.Pool;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateDs3DiskPartition {
    private static final Logger log = LoggerFactory.getLogger(CreateDs3DiskPartition.class);

    public static DiskPartition fromObject(DiskPartition par, BpConnector pearl) {
        log.info("Creating DS3 disk partition [" + par.getName() + "]");

        DiskPartition new_par = null;
        boolean waiting = false;
        Pool pool = null;

        try {
            log.info("Verifying DS3 pools are online.");

            for(int i=0; i<par.getPoolIds().size(); i++) {
                waiting = true;
                
                while(waiting) {
                    pool = GetDs3Pool.byId(par.getPoolIds().get(i), pearl);
                
                    if(pool == null) {
                        throw new Exception("Cannot find pool " + par.getPoolIds().get(i));
                    } else if(pool.getDs3PoolHealth() != null && pool.getDs3PoolHealth().equals("ok")) {
                        // Pool has been imported.
                        log.info("Pool [" + pool.getName() + "] has imported successfully.");
                        waiting = false;
                    } else {
                        // Pool hasn't been imported.
                        // Wait a few seconds and retry.
                        log.info("Waiting on pool [" + pool.getName() + "] to import.");
                        TimeUnit.SECONDS.sleep(10);
                    }
                }
            }

            new_par = pearl.createDs3DiskPartition(par);
        } catch(Exception e) {
            log.error(e.getMessage());
            log.error("Failed to create DS3 disk partition [" + par.getName() + "]");
        }

        return new_par;
    }
}
