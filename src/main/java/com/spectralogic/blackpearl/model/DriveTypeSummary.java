//===================================================================
// DriveTypeSummary.java
//      Description:
//          This class models key information for disk drives to
//          translate the information into something useful.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.model;

import com.spectralogic.blackpearl.nacre.util.convert.StorageConversion;

public class DriveTypeSummary {
    private int count;
    private String interfaceType;
    private String model; // product
    private Long size;
    private String speed; // if omitted it's an SSD.

    //===========================================
    // Getters
    //===========================================
    public String getDriveType() {
        String type;

        if(speed != null && speed != "") {
            type = speed + "RPM";
        } else {
            type = "SSD";
        }
        
        return StorageConversion.bytesToHumanReadable(size) + " "
                + type + " "
                + interfaceType;
    }
    public int getCount() { return count; }
    public String getInterfaceType() { return interfaceType; }
    public String getModel() { return model; }
    public long getSize() { return size; }
    public String getSpeed() { return speed; }

    //===========================================
    // Setters
    //===========================================
    public void incrementCount() { this.count++; }
    public void setCount(int count) { this.count = count; }
    public void setInterfaceType(String type) { this.interfaceType = type; }
    public void setModel(String model) { this.model = model; }
    public void setSize(long size) { this.size = size; }
    public void setSpeed(String rpm) { this.speed = rpm; }
    
    //===========================================
    // Functions
    //===========================================
    @Override
    public String toString() {
        return getDriveType();
    }
}
