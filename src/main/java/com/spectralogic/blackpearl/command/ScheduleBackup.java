//===================================================================
// ScheduleDatabaseBackup.java
//      Description:
//          This command updates the BlackPearl's database backup
//          schedule.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.command;

import com.spectralogic.blackpearl.nacre.api.BpConnector;
import com.spectralogic.blackpearl.nacre.model.ScheduleDatabaseBackup;
import com.spectralogic.blackpearl.nacre.model.ScheduleDatabaseConfig;
import com.spectralogic.blackpearl.nacre.util.generator.CronGenerator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScheduleBackup {
    private static final Logger log = LoggerFactory.getLogger(ScheduleDatabaseBackup.class);

    public static ScheduleDatabaseBackup fromConfig(ScheduleDatabaseConfig config, BpConnector pearl) {
        log.info("Configuring BlackPearl database backup schedule to [" + config.getSchedule() + "] at " + config.getTime() + " and to keep " + config.getCopiesToKeep());
        
        ScheduleDatabaseBackup schedule = new ScheduleDatabaseBackup();

        try {
            String cron_string = CronGenerator.createBasicString(config.getSchedule(), config.getTime(), config.getDays()); 

            schedule.setCronString(cron_string);
            schedule.setId("database_archiver_schedule");
            schedule.setNumToSave(config.getCopiesToKeep());
            schedule.setRepeatScheduleUnit(config.getSchedule());
            schedule.setRepeatScheduleWeeklyDays(false);
        
            return fromObject(schedule, pearl);
        } catch(Exception e) {
            log.error(e.getMessage());
            log.error("Failed to configure database backup schedule.");
            return null;
        }

    }

    public static ScheduleDatabaseBackup fromObject(ScheduleDatabaseBackup schedule, BpConnector pearl) {
        log.info("Sending database backup object to Blackpearl.");

        try {
            return pearl.updateBackupSchedule(schedule);
        } catch(Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }
    
    public static String withParameters(String schedule, String time, int num_to_keep, BpConnector pearl) {
        log.info("Setting BlackPearl database backup schedule to [" + schedule + "] at " + time + " and to keep " + num_to_keep);
        
        String message = "Failed to update database backup schedule.";

        try {
            ScheduleDatabaseBackup db_schedule = new ScheduleDatabaseBackup();

            String cron_string = CronGenerator.createBasicString(schedule, time, null); 

            db_schedule.setCronString(cron_string);
            db_schedule.setId("database_archiver_schedule");
            db_schedule.setNumToSave(num_to_keep);
            db_schedule.setRepeatScheduleUnit(schedule);
            db_schedule.setRepeatScheduleWeeklyDays(false);
            
            db_schedule = fromObject(db_schedule, pearl);

            if(schedule != null) {
                message = "Successfully updated database backup schedule";
            }
        } catch(Exception e) {
            log.error(e.getMessage());
            log.error("Failed to update database backup schedule");
        }

        return message;
    }

}
