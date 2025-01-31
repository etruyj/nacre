//===================================================================
// Output.java
//      Description:
//          This class models output for the shell UI.
//
// Created by Sean Snyder
//===================================================================

package com.spectralogic.blackpearl.nacre.model;

public class Output {
    private String key;
    private String value;

    //===========================================
    // Constructors
    //===========================================
    public Output() {} // blank constructor 
    public Output(String key, String value) {
        this.key = key;
        this.value = value;
    }

    //===========================================
    // Getters
    //===========================================
    public String getKey() { return key; }
    public String getValue() { return value; }

    //===========================================
    // Setters
    //===========================================
    public void setKey(String key) { this.key = key; }
    public void setValue(String value) { this.value = value; }
}
