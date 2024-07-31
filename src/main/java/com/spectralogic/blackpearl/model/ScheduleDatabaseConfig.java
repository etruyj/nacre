//===================================================================
// ScheduleDatabaseConfig.java
//      Description:
//          This model class holds config information for database
//          backup schedules.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ScheduleDatabaseConfig {
    private String schedule;
    private String time;
    @SerializedName("copies_to_keep")
    private int copies_to_keep;
    private List<String> days;
    @SerializedName("data_policy")
    private String data_policy;

    //===========================================
    // Getters
    //===========================================
    public String getSchedule() { return schedule; }
    public String getTime() { return time; }
    public int getCopiesToKeep() { return copies_to_keep; }
    public List<String> getDays() { return days; }
    public String getDataPolicy() { return data_policy; }

    //===========================================
    // Setters
    //===========================================
    public void setSchedule(String schedule) { this.schedule = schedule; }
    public void setTime(String time) { this.time = time; }
    public void setCopiesToKeep(int copies) { this.copies_to_keep = copies; }
    public void setDays(List<String> days) { this.days = days; }
    public void setDataPolicy(String policy) { this.data_policy = policy; }
}
