//===================================================================
// NacreShell.java
//      Description:
//          This class provides shell commands for accessing the
//          BlackPearl's management UI.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.ui;

import com.spectralogic.blackpearl.nacre.command.BpController;
import com.spectralogic.blackpearl.nacre.ui.ArgParser;

public class NacreShell {
    public static void main(String[] args) {
        ArgParser aparser = new ArgParser();

        aparser.parse(args);

        try {
            BpController conn = new BpController(aparser.getRequiredValue("endpoint"), aparser.getBoolean("ignore-ssl"), "../nacre.yaml");    
            
            if(conn.login(aparser.getRequiredValue("username"), aparser.getRequiredValue("password"))) {
                processCommand(aparser, conn);
            } else {
                System.err.println("Failed to connect to BlackPearl as user [" + aparser.getValue("username") + "]");
            }
        } catch(Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void processCommand(ArgParser aparser, BpController conn) {
        try {
            switch(aparser.getRequiredValue("command")) {
                case "backup-database":
                    conn.backupDatabase();
                    break;
                case "configure":
                    conn.configureFromFile(aparser.getRequiredValue("file"));
                    break;
                case "list-data-policies":
                    conn.listDataPolicies();
                    break;
                case "list-services":
                    conn.listServices();
                    break;
                case "set-backup-schedule":
                    conn.setBackupSchedule(aparser.getRequiredValue("schedule"), 
                                            aparser.getRequiredValue("time"),
                                            Integer.valueOf(aparser.getRequiredValue("keep-copies")));
                    break;
                case "test":
                    conn.listPools();
                    break;
                default:
                    System.err.println("Invalid command [" + aparser.getRequiredValue("command") + "] selected.");
            }
        } catch(Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
