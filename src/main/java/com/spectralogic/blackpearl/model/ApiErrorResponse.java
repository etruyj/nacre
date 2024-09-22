//===================================================================
// ApiErrorResponse.java
//      Description:
//          This class models error messages sent by the system.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.model;

import java.io.IOException;
import java.util.ArrayList;

public class ApiErrorResponse {
    private Error errors;

    //===========================================
    // Getter
    //===========================================
    public Error getErrors() { return errors; }
    public void throwErrors() throws IOException {
        String msg = "";

        for(String line : errors.getBase()) {
            msg += line + " ";
        }

        if(msg.length() > 0) {
            throw new IOException(msg);
        }
    }


    //===========================================
    // Setter
    //===========================================
    public void setErrors(Error errors) { this.errors = errors; }

    //===========================================
    // Inner Classes
    //===========================================
    public static class Error {
        private ArrayList<String> base;
    
        //=======================================
        // Getters
        //=======================================
        public ArrayList<String> getBase() { return base; }
        
        //=======================================
        // Setters
        //=======================================
        public void setBase(ArrayList<String> base) { this.base = base; }
    }
}
