//===================================================================
// CronGenerator.java
//      Description:
//          This code is used for translating Cron Strings.
//      
//      Cron Fields: <minute> <hour> <day-of-month> <month> <day-of-week>
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.util.generator;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CronGenerator {
    public static String createBasicString(String schedule, String time, List<String> days) throws Exception {
        String cron_string = parseTimeToCron(time);
        
        switch (schedule) {
            case "hourly":
                // overwrite the hour field of from the time specification
                // and set the string to happen every hour, day of month, 
                // month, and day of week.
                String[] parts = cron_string.split(" ");
                cron_string = parts[0] + " * * * *";
                break;
            case "daily":
                // Set the day of month, month, and day of week fields to any.
                cron_string = cron_string + " * * *";
                break;
            case "weekly":
                // Determine which days of the week to execute on.
                // day of month and month set to any.
                cron_string = cron_string + " * * " + convertDaysToCron(days);
                break;
            default:
                throw new Exception("Invalid backup schedule selected [" + schedule + "]. Must be hourly, daily, or weekly.");
        }
        return cron_string;
    }

    //===========================================
    // Private Functions
    //===========================================
    private static String parseTimeToCron(String time) throws Exception {
        if(time == null) {
            // No time specified. Set to midnight.
            time = convertTimeToGmt("00:00");
            return convertTimeToCron(time);
        } else {
            time = convertTimeToGmt(time);
            return convertTimeToCron(time);
        }
    }

    private static String convertDaysToCron(List<String> days) throws Exception {
        String cdays = "?";

        if(days == null || days.size() == 0) {
            // no days specified. Execute on Sundays
            cdays = "1";
        } else if(days.size() == 7) {
            // Assume all valid inputs and 7 days selected.
            cdays = "*";
        } else if(days.size() < 7 && days.size() > 0) {
            for(String day : days) {
                switch(day.toLowerCase()) {
                    case "saturday":
                    case "sat":
                    case "sa":
                        cdays += "0";
                        break;
                    case "sunday":
                    case "sun":
                    case "su":
                        cdays += "1";
                        break;
                    case "monday":
                    case "mon":
                    case "m":
                        cdays += "2";
                        break;
                     case "tuesday":
                     case "tues":
                     case "tu":
                        cdays += "3";
                        break;
                     case "wednesday":
                     case "weds":
                     case "w":
                        cdays += "4";
                        break;
                     case "thursday":
                     case "thurs":
                     case "th":
                        cdays += "5";
                        break;
                     case "friday":
                     case "fri":
                     case "f":
                        cdays += "6";
                        break;
                     default:
                        throw new Exception("Cannot parse day [" + day + "].");
                }

                cdays += ",";
            }

        } else {
            throw new Exception("Invalid number of days selected.");
        }
 
        if(cdays.length() > 1) {       
            if(cdays.substring(cdays.length()-2, cdays.length()-1).equals(",")) {
                cdays = cdays.substring(0, cdays.length()-2);
            }
        }

        return cdays;
    }
    
    private static String convertTimeToCron(String time) throws Exception {
        String cron_string = "";
        String working_time = "";
        int clock_conversion = 0;
        
        working_time = time.toLowerCase();
        // Search for AM or PM
        if(working_time.contains("a")) {
            // drop the am from the list.
            working_time = working_time.substring(0, working_time.indexOf("a")).trim();
        } else if(time.contains("p")) {
            clock_conversion = 12; // twelve hours have to be added to the clock.
            working_time = working_time.substring(0, working_time.indexOf("p")).trim();
        }

        String[] parts = working_time.split(":");

        if(parts.length != 2) {
            throw new Exception("Unable to parse time " + time);
        } else {
            // Minutes go first
            if(Integer.valueOf(parts[1]) < 59) {
                if(parts[1].equals("00")) {
                    cron_string = "0 ";
                } else {
                    cron_string = parts[1] + " ";
                }
            } else {
                throw new Exception("Unable to parse time " + time + ". Invalid number of minutes specified.");
            }

            clock_conversion += Integer.valueOf(parts[0]); // determine the time when am or PM is included.
            if(clock_conversion < 24) {
                if(Integer.valueOf(parts[0]) < 10) {
                    cron_string = cron_string + parts[0].substring(1, 2);
                } else {
                    cron_string = cron_string + parts[0];
                }
            } else {
                throw new Exception("Unable to parse time " + time + ". Invalid hour specified.");
            }
        }

        return cron_string;
    }

    private static String convertTimeToGmt(String time) {
        LocalTime local_time = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));
        // Convert to zoned date time.
        ZonedDateTime zlocal_time = local_time.atDate(java.time.LocalDate.now())
                                              .atZone(ZoneId.systemDefault());
        // Convert to GMT
        ZonedDateTime zGmtTime = zlocal_time.withZoneSameInstant(ZoneId.of("GMT"));
        return zGmtTime.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

}
