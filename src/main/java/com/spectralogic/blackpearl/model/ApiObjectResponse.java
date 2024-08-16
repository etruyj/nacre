//===================================================================
// ApiObjectResponse.java
//      Description:
//          This class holds pagenated data responses from the API
//          for when the API only returns a single object such as get
//          object request.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.model;

import java.util.ArrayList;

public class ApiObjectResponse<T> {
    private T data;
    private int count;

    //===========================================
    // Getters
    //===========================================
    public T getData() { return data; }
    public int getCount() { return count; }

    //===========================================
    // Setters
    //===========================================
    public void setData(T obj) { this.data = obj; }
    public void setCount(int count) { this.count = count; }
}
