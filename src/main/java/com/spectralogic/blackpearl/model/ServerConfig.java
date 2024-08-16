//===================================================================
// ServerConfig.java
//      Description:
//          This config class is a subclass of the BlackPearl
//          DefaultsConfig to determine some of the sub-processes
//          such as time outs and information on activation keys.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.model;

import java.util.HashMap;

public class ServerConfig {
    private int pingInterval;
    private int timeoutMilliseconds;
    private HashMap<String, ActivationKeyConfig> keySettings;

    //=========================================== 
    // Constructors
    //=========================================== 
    public ServerConfig() {
        keySettings = new HashMap<String, ActivationKeyConfig>();
    }

    //=========================================== 
    // Getters
    //===========================================
    public int getPingInterval() { return pingInterval; }
    public int getTimeoutMilliseconds() { return timeoutMilliseconds; }
    public HashMap<String, ActivationKeyConfig> getKeySettings() { return keySettings; }

    //=========================================== 
    // Setters
    //=========================================== 
    public void setPingInterval(int interval) { this.pingInterval = interval; }
    public void setTimeoutMilliseconds(int timeout) { this.timeoutMilliseconds = timeout; }
    public void setKeySettings(HashMap<String, ActivationKeyConfig> settings) { this.keySettings = settings; }
}
