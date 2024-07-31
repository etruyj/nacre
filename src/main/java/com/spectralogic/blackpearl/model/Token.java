//===================================================================
// Token.java
//      Description:
//          This model holds the token response for the api authenticate
//          call.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.model;

public class Token {
    private String token;

    //===========================================
    // Getters
    //===========================================
    public String getToken() { return token; }

    //===========================================
    // Setters
    //===========================================
    public void setToken(String token) { this.token = token; }
}
