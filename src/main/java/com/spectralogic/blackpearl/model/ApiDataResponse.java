//===================================================================
// ApiDataResponse.java
//      Description:
//          This class holds pagenated data responses from the API.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.model;

import java.util.ArrayList;

public class ApiDataResponse<T> {
    private ArrayList<T> data;
    private int count;

    //===========================================
    // Getters
    //===========================================
    public ArrayList<T> getData() { return data; }
    public int getCount() { return count; }

    //===========================================
    // Setters
    //===========================================
    public void setData(ArrayList<T> data) { this.data = data; }
    public void setCount(int count) { this.count = count; }
}
