//===================================================================
// Service.java
//      Description:
//          A super class that defines core values inherent to
//          all servies.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.model;

public class Service {
    private String name;
    private String id;

    //===========================================
    // Getters
    //===========================================
    public String getName() { return name; }
    public String getId() { return id; }

    //===========================================
    // Setters
    //===========================================
    public void setName(String name) { this.name = name; }
    public void setId(String id) { this.id = id; }
}
