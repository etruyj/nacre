//===================================================================
// ScheduleLogSet.java
//      Description:
//          This class holds the log set schedule information.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.model;

import com.google.gson.annotations.SerializedName;

public class ScheduleLogSet {
    private String name;
    @SerializedName("cron_string")
    private String cronString;
    @SerializedName("repeat_schedule_weekly_days")
    private boolean repeatScheduleWeeklyDays;
    @SerializedName("repeat_schedule_unit")
    private String repeatScheduleUnit;
    @SerializedName("repeat_schedule_hourly_value")
    private String repeatScheduleHourlyValue;
    @SerializedName("repeat_schedule_minute_value")
    private String repeatScheduleMinuteValue;
    @SerializedName("repeat_schedule_daily_value")
    private String repeatScheduleDailyValue;
    
    //===========================================
    // Constructors
    //===========================================
    public ScheduleLogSet() {} // blank constructor to allow for copy constructor.
    
    // Copy Constructor
    public ScheduleLogSet(ScheduleLogSet other) {
        this.name = other.name;
        this.cronString = other.cronString;
        this.repeatScheduleWeeklyDays = other.repeatScheduleWeeklyDays;
        this.repeatScheduleUnit = other.repeatScheduleUnit;
        this.repeatScheduleHourlyValue = other.repeatScheduleHourlyValue;
        this.repeatScheduleMinuteValue = other.repeatScheduleMinuteValue;
        this.repeatScheduleDailyValue = other.repeatScheduleDailyValue;
    }

    //===========================================
    // Getters
    //===========================================
    public String getName() { return name; }
    public String getCronString() { return cronString; }
    public boolean isRepeatScheduleWeeklyDays() { return repeatScheduleWeeklyDays; }
    public String getRepeatScheduleUnit() { return repeatScheduleUnit; }
    public String getRepeatScheduleHourlyValue() { return repeatScheduleHourlyValue; }
    public String getRepeatScheduleMinuteValue() { return repeatScheduleMinuteValue; }
    public String getRepeatScheduleDailyValue() { return repeatScheduleDailyValue; }

    //===========================================
    // Setters
    //===========================================
    public void setName(String name) { this.name = name; }
    public void setCronString(String cronString) { this.cronString = cronString; }
    public void setRepeatScheduleWeeklyDays(boolean repeatScheduleWeeklyDays) { this.repeatScheduleWeeklyDays = repeatScheduleWeeklyDays; }
    public void setRepeatScheduleUnit(String repeatScheduleUnit) { this.repeatScheduleUnit = repeatScheduleUnit; }
    public void setRepeatScheduleHourlyValue(String repeatScheduleHourlyValue) { this.repeatScheduleHourlyValue = repeatScheduleHourlyValue; }
    public void setRepeatScheduleMinuteValue(String repeatScheduleMinuteValue) { this.repeatScheduleMinuteValue = repeatScheduleMinuteValue; }
    public void setRepeatScheduleDailyValue(String repeatScheduleDailyValue) { this.repeatScheduleDailyValue = repeatScheduleDailyValue; }
}
