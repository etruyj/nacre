//===================================================================
// ArgParser.java
//      Description:
//          The purpose of this class is to process the String[] 
//          args that are passed when the shell command is started.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.ui;

import java.util.HashMap;

public class ArgParser {
    private HashMap<String, String> arg_map;
    private String command;

    public ArgParser() {
        arg_map = new HashMap<String, String>();
        command = "";
    }

    public void parse(String[] args) {
        int i = 0;

        while(i < args.length) {
            if(args[i].substring(0, 2).equals("--")) { // see if the argument starts with --, denoting a flag.
                if((i+1) < args.length) { // make sure there is another argument before checking the value.
                    if(args[i+1].substring(0, 1).equals("-")) { // the next arg is also a flag, so this one is a boolean.
                        arg_map.put(args[i].substring(2, args[i].length()), "true"); // if there isn't a value assigned with the flag, assume it's a bool.
                    } else {
                        arg_map.put(args[i].substring(2, args[i].length()), args[i+1]); // Store the value with the flag.
                        i++; // incrememt an extra time as we need to skip the flag and then the value.
                    }
                } else {
                    arg_map.put(args[i].substring(2, args[i].length()), "true"); // if there isn't a value assigned with the flag, assume it's a bool.
                }
                
                i++; // increment past the flag.
            }
        }
    }

    public boolean getBoolean(String flag) {
        if(arg_map.get(flag) == null) {
            return false;
        } else {
            return true;
        }
    }

    public String getCommand() {
        return command;
    }

    public String getRequiredValue(String flag) throws Exception {
        if(arg_map.get(flag) == null) {
            throw new Exception("Required parameter [" + flag + "] is missing. Please use --" + flag + " to specify value.");
        } else {
            return arg_map.get(flag);
        }
    }

    public String getValue(String flag) {
        return arg_map.get(flag);
    }
}
