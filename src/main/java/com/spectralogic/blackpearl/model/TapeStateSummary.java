//===================================================================
// TapeStateSummary.java
//      Description:
//          A model class to hold the TapeStateSummary inner class for
//      the tape partition model.
// 
// Created by Sean Snyder
// Copyright Spectra Logic Corporation 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.model;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class TapeStateSummary {
    private int count;
    @SerializedName("full_of_data")
    private int full_of_data;
    @SerializedName("tape_state")
    private String tape_state;
    @SerializedName("type_counts")
    private ArrayList<TapeTypeCount> type_counts;
    
    //===========================================
    // Gettors
    //===========================================
    public int getCount() { return count; }
    public int getFullOfData() { return full_of_data; }
    public String getTapeState() { return tape_state; }
    public ArrayList<TapeTypeCount> getTypeCounts() { return type_counts; }
    
    //===========================================
    // Settors
    //===========================================
    public void setCount(int count) { this.count = count; }
    public void setFullOfData(int full_of_data) { this.full_of_data = full_of_data; }
    public void setTapeState(String tape_state) { this.tape_state = tape_state; }
    public void setTypeCounts(ArrayList<TapeTypeCount> type_counts) { this.type_counts = type_counts; }

}
