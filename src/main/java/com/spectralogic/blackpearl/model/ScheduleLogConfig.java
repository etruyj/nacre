//===================================================================
// ScheduleLogConfig.java
//      Description:
//          This model class holds config information for log set
//          schedules.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.model;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class ScheduleLogConfig {
    private String time;
    private List<String> days;

    //===========================================
    // Constructors
    //===========================================
    public ScheduleLogConfig() {} // empty constructor to allow for copy constructor

    public ScheduleLogConfig(ScheduleLogConfig schedule) {
        this.setTime(schedule.getTime());

        if(schedule.getDays() != null) {
            this.days = new ArrayList<String>();

            for(String day : schedule.getDays()) {
                this.days.add(day);
            }
        } else {
            this.days = null;
        }
    }

    //===========================================
    // Getters
    //===========================================
    public String getTime() { return time; }
    public List<String> getDays() { return days; }
    public String getSchedule() {
        String schedule = "";

        for(String day : days) {
            schedule += day + ", ";
        }

        schedule = schedule.substring(0, schedule.length()-2);

        return schedule;
    }

    //===========================================
    // Setters
    //===========================================
    public void setTime(String time) { this.time = time; }
    public void setDays(List<String> days) { this.days = days; }
}
