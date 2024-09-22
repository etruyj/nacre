//======================================================================
//  SmtpSettings
//      Description: 
//          This class represents email settings including the 
//          server address, port, TLS status, sender's email, 
//          creation and update timestamps, authentication type, 
//          and credentials.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//======================================================================

package com.spectralogic.blackpearl.nacre.model;

import com.google.gson.annotations.SerializedName;

public class SmtpSettings {
    private int id;
    private String address;
    private String port;
    private Boolean tls;
    private String from;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("updated_at")
    private String updatedAt;
    @SerializedName("authentication_type")
    private String authenticationType;
    private String username;
    private String password;
    
    //===========================================
    // Constructors
    //===========================================
    public SmtpSettings() {} // blank to allow for a copy constructor

    // Copy Constructor
    public SmtpSettings(SmtpSettings other) {
        this.id = other.id;
        this.address = other.address;
        this.port = other.port;
        this.tls = other.tls;
        this.from = other.from;
        this.createdAt = other.createdAt;
        this.updatedAt = other.updatedAt;
        this.authenticationType = other.authenticationType;
        this.username = other.username;
        this.password = other.password;
    }

    //===========================================
    // Getters
    //===========================================
    public int getId() { return id; }
    public String getAddress() { return address; }
    public String getPort() { return port; }
    public Boolean isTls() { return tls; }
    public String getFrom() { return from; }
    public String getCreatedAt() { return createdAt; }
    public String getUpdatedAt() { return updatedAt; }
    public String getAuthenticationType() { return authenticationType; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }

    //===========================================
    // Setters
    //===========================================
    public void setId(int id) { this.id = id; }
    public void setAddress(String address) { this.address = address; }
    public void setPort(String port) { this.port = port; }
    public void setTls(Boolean tls) { this.tls = tls; }
    public void setFrom(String from) { this.from = from; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
    public void setUpdatedAt(String updatedAt) { this.updatedAt = updatedAt; }
    public void setAuthenticationType(String authenticationType) { this.authenticationType = authenticationType; }
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
}

