//======================================================================
//  NtpSettings
//      Description: 
//          This class represents NTP settings including addresses, 
//          synchronization status, and timestamps.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//======================================================================

package com.spectralogic.blackpearl.nacre.model;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class NtpSettings {
    private ArrayList<String> addresses;
    @SerializedName("current_time")
    private String currentTime;
    @SerializedName("ntp_time")
    private boolean ntpTime;
    @SerializedName("synchronized")
    private boolean synced;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("updated_at")
    private String updatedAt;
    private int id;
    @SerializedName("ntp_server_2")
    private String ntpServer2;
    @SerializedName("ntp_server_1")
    private String ntpServer1;

    //===========================================
    // Constructors
    //===========================================
    public NtpSettings() {
        addresses = new ArrayList<String>();
    }

    public NtpSettings(NtpSettings settings) {
        this.setCurrentTime(settings.getCurrentTime());
        this.setNtpTime(settings.isNtpTime());
        this.setSynchronized(settings.isSynchronized());
        this.setCreatedAt(settings.getCreatedAt());
        this.setUpdatedAt(settings.getUpdatedAt());
        this.setId(settings.getId());
        this.setNtpServer1(settings.getNtpServer1());
        this.setNtpServer2(settings.getNtpServer2());

        this.addresses = new ArrayList<String>();

        for(String address : settings.getAddresses()) {
            addresses.add(address);
        }
    }
    
    //===========================================
    // Getters
    //===========================================
    public ArrayList<String> getAddresses() { return addresses; }
    public String getCurrentTime() { return currentTime; }
    public boolean isNtpTime() { return ntpTime; }
    public boolean isSynchronized() { return synced; }
    public String getCreatedAt() { return createdAt; }
    public String getUpdatedAt() { return updatedAt; }
    public int getId() { return id; }
    public String getNtpServer2() { return ntpServer2; }
    public String getNtpServer1() { return ntpServer1; }

    //===========================================
    // Setters
    //===========================================
    public void setAddresses(ArrayList<String> addresses) { this.addresses = addresses; }
    public void setCurrentTime(String currentTime) { this.currentTime = currentTime; }
    public void setNtpTime(boolean ntpTime) { this.ntpTime = ntpTime; }
    public void setSynchronized(boolean synced) { this.synced = synced; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
    public void setUpdatedAt(String updatedAt) { this.updatedAt = updatedAt; }
    public void setId(int id) { this.id = id; }
    public void setNtpServer2(String ntpServer2) { this.ntpServer2 = ntpServer2; }
    public void setNtpServer1(String ntpServer1) { this.ntpServer1 = ntpServer1; }
}

