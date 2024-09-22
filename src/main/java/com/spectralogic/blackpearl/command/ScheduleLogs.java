//===================================================================
// ScheduleLogSet.java
//      Description:
//          This command updates the BlackPearl's database backup
//          schedule.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.command;

import com.spectralogic.blackpearl.nacre.api.BpConnector;
import com.spectralogic.blackpearl.nacre.model.ScheduleLogSet;
import com.spectralogic.blackpearl.nacre.model.ScheduleLogConfig;
import com.spectralogic.blackpearl.nacre.util.generator.CronGenerator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScheduleLogs {
    private static final Logger log = LoggerFactory.getLogger(ScheduleLogSet.class);

    public static ScheduleLogSet fromConfig(ScheduleLogConfig config, BpConnector pearl) {
        log.info("Configuring BlackPearl log schedule to [" + config.getSchedule() + "] at " + config.getTime());
        
        ScheduleLogSet schedule = new ScheduleLogSet();

        try {
            String cron_string = CronGenerator.createBasicString("weekly", config.getTime(), config.getDays()); 

            schedule.setCronString(cron_string);
            schedule.setName("weekly");
            schedule.setRepeatScheduleUnit("weekly");
            // The daily, hourly, minute values seem to be
            // required but static. Not noticing any changes
            // despite what's configured.
            schedule.setRepeatScheduleDailyValue("1");
            schedule.setRepeatScheduleHourlyValue("1");
            schedule.setRepeatScheduleMinuteValue("0");

            if(config.getDays().size() == 7) {
                schedule.setRepeatScheduleWeeklyDays(true);
            } else {
                schedule.setRepeatScheduleWeeklyDays(false);
            }
        
            return fromObject(schedule, pearl);
        } catch(Exception e) {
            log.error(e.getMessage());
            log.error("Failed to configure log set schedule.");
            return null;
        }

    }

    public static ScheduleLogSet fromObject(ScheduleLogSet schedule, BpConnector pearl) {
        log.info("Sending log set schedule object to Blackpearl.");

        try {
            ScheduleLogSet new_schedule = pearl.setLogSchedule(schedule);
            log.info("Successfully configured log schedule " + new_schedule.getCronString());

            return new_schedule;
        } catch(Exception e) {
            log.error(e.getMessage());
            log.error("Failed to configure log schedule.");
            return null;
        }
    }

    /* Holding off on this section
     * not needed for the current implementation of the code, but it may be 
     * useful in the future.
    public static String withParameters(String schedule, String time, BpConnector pearl) {
        log.info("Setting BlackPearl database backup schedule to [" + schedule + "] at " + time);
        
        String message = "Failed to update log set schedule.";

        try {
            ScheduleLogSet db_schedule = new ScheduleLogSet();

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
    */
}
