//===================================================================
// Stripe.java
//      Description:
//          This models a stripe of disk drives.
//
// Created by Sean Snyder
// Copyright Spectra Logic Corporation 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.model;

import java.util.ArrayList;

public class Stripe {
    private ArrayList<DiskDrive> children;
    private String type;

    //===========================================
    // Gettors
    //===========================================
    public ArrayList<DiskDrive> getChildren() { return children; }
    public DiskDrive getChild(int i) { return children.get(i); }
    public String getType() { return type; }

    //===========================================
    // Settors
    //===========================================

    public void addDiskDrive(DiskDrive drive) { this.children.add(drive); }
    public void setChildren(ArrayList<DiskDrive> children) { this.children = children; }
    public void setType(String type) { this.type = type; }
}
