//===================================================================
// ActivationKeyConfig.java
//      Description:
//          This class holds the config parameters for activation keys
//          namely whether the key requires a system reboot and
//          whether it must be done in a specific order.
//          
//          This class doesn't inherit the activation key class like
//          the other config classes inherit their parents.
//
// Create by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.model;

public class ActivationKeyConfig {
    private String key;
    private String name;
    private boolean restart_required;
    private int load_order;

    //===========================================
    // Getters
    //===========================================
    public String getKey() { return key; }
    public String getName() { return name; }
    public boolean isRestartRequired() { return restart_required; }
    public int getLoadOrder() { return load_order; }

    //===========================================
    // Getters
    //===========================================
    public void setKey(String key) { this.key = key; }
    public void setName(String name) { this.name = name; }
    public void setRestartRequired(boolean restart) { this.restart_required = restart; }
    public void setLoadOrder(int order) { this.load_order = order; }
}
