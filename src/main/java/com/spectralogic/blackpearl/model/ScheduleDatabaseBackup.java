//===================================================================
// ScheduleDatabaseBackup.java
//      Description:
//          This model holds the information for scheduling regular
//          database backups.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.model;

import com.google.gson.annotations.SerializedName;

public class ScheduleDatabaseBackup {
    @SerializedName("num_to_save")
    private int num_to_save;
    @SerializedName("cron_string")
    private String cron_string;
    @SerializedName("created_at")
    private String created_at;
    @SerializedName("updated_at")
    private String updated_at;
    private String id;
    @SerializedName("repeat_schedule_daily_value")
    private String repeat_schedule_daily_value;
    @SerializedName("repeat_schedule_hourly_value")
    private String repeat_schedule_hourly_value;
    @SerializedName("repeat_schedule_minute_value")
    private String repeat_schedule_minute_value;
    @SerializedName("repeat_schedule_unit")
    private String repeat_schedule_unit;
    @SerializedName("repeat_schedule_weekly_days")
    private Boolean repeat_schedule_weekly_days;
    
    //===========================================
    // Constructors
    //===========================================
    public ScheduleDatabaseBackup() {} // blank to allow for copy constructor

    public ScheduleDatabaseBackup(ScheduleDatabaseBackup schedule) {
        this.setNumToSave(schedule.getNumToSave());
        this.setCronString(schedule.getCronString());
        this.setCreatedAt(schedule.getCreatedAt());
        this.setUpdatedAt(schedule.getUpdatedAt());
        this.setId(schedule.getId());
        this.setRepeatScheduleDailyValue(schedule.getRepeatScheduleDailyValue());
        this.setRepeatScheduleHourlyValue(schedule.getRepeatScheduleHourlyValue());
        this.setRepeatScheduleMinuteValue(schedule.getRepeatScheduleMinuteValue());
        this.setRepeatScheduleUnit(schedule.getRepeatScheduleUnit());
        this.setRepeatScheduleWeeklyDays(schedule.getRepeatScheduleWeeklyDays());
    }
    
    //===========================================
    // Getters
    //===========================================
    public int getNumToSave() { return num_to_save; }
    public String getCronString() { return cron_string; }
    public String getCreatedAt() { return created_at; }
    public String getUpdatedAt() { return updated_at; }
    public String getId() { return id; }
    public String getRepeatScheduleDailyValue() { return repeat_schedule_daily_value; }
    public String getRepeatScheduleHourlyValue() { return repeat_schedule_hourly_value; }
    public String getRepeatScheduleMinuteValue() { return repeat_schedule_minute_value; }
    public String getRepeatScheduleUnit() { return repeat_schedule_unit; }
    public Boolean getRepeatScheduleWeeklyDays() { return repeat_schedule_weekly_days; }

    //===========================================
    // Settors
    //===========================================
    public void setNumToSave(int num) { this.num_to_save = num; }
    public void setCronString(String cron_string) { this.cron_string = cron_string; }
    public void setCreatedAt(String date) { this.created_at = date; }
    public void setUpdatedAt(String date) { this.updated_at = date; }
    public void setId(String id) { this.id = id; }
    public void setRepeatScheduleDailyValue(String value) { this.repeat_schedule_daily_value = value; }
    public void setRepeatScheduleHourlyValue(String value) { this.repeat_schedule_hourly_value = value; }
    public void setRepeatScheduleMinuteValue(String value) { this.repeat_schedule_minute_value = value; }
    public void setRepeatScheduleUnit(String value) { this.repeat_schedule_unit = value; }
    public void setRepeatScheduleWeeklyDays(Boolean value) { this.repeat_schedule_weekly_days = value; }
}
