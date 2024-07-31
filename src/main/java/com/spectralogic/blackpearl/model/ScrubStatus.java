// ======================================================================
// ScrubStatus.java
//      Description:
//          This class represents the structure of a scan task JSON 
//          object with various attributes such as state, startTime, 
//          minutesLeft, and more.
//          This object is part of the pool class.
//
// Created by Sean Snyder 
// Copyright Spectra Logic 2024
// ======================================================================

package com.spectralogic.blackpearl.nacre.model;

import com.google.gson.annotations.SerializedName;

public class ScrubStatus {
    private String state;
    @SerializedName("start_time")
    private String startTime;
    @SerializedName("minutes_left")
    private int minutesLeft;
    @SerializedName("minutes_taken")
    private int minutesTaken;
    @SerializedName("bytes_to_scan")
    private long bytesToScan;
    @SerializedName("bytes_issued")
    private long bytesIssued;
    private int errors;
    @SerializedName("end_time")
    private String endTime;
    @SerializedName("percent_complete")
    private int percentComplete;
    private String eta;

    //===========================================
    // Getters
    //===========================================
    public String getState() { return state; }
    public String getStartTime() { return startTime; }
    public int getMinutesLeft() { return minutesLeft; }
    public int getMinutesTaken() { return minutesTaken; }
    public long getBytesToScan() { return bytesToScan; }
    public long getBytesIssued() { return bytesIssued; }
    public int getErrors() { return errors; }
    public String getEndTime() { return endTime; }
    public int getPercentComplete() { return percentComplete; }
    public String getEta() { return eta; }

    //===========================================
    // Setters
    //===========================================
    public void setState(String state) { this.state = state; }
    public void setStartTime(String startTime) { this.startTime = startTime; }
    public void setMinutesLeft(int minutesLeft) { this.minutesLeft = minutesLeft; }
    public void setMinutesTaken(int minutesTaken) { this.minutesTaken = minutesTaken; }
    public void setBytesToScan(long bytesToScan) { this.bytesToScan = bytesToScan; }
    public void setBytesIssued(long bytesIssued) { this.bytesIssued = bytesIssued; }
    public void setErrors(int errors) { this.errors = errors; }
    public void setEndTime(String endTime) { this.endTime = endTime; }
    public void setPercentComplete(int percentComplete) { this.percentComplete = percentComplete; }
    public void setEta(String eta) { this.eta = eta; }
}

