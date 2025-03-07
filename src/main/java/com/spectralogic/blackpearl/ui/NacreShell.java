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
import com.spectralogic.blackpearl.nacre.model.ActivationKey;
import com.spectralogic.blackpearl.nacre.ui.ArgParser;
import com.spectralogic.blackpearl.nacre.ui.display.Display;

import java.util.ArrayList;

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
        ArrayList output = null;

        try {
            switch(aparser.getRequiredValue("command")) {
                case "add-key":
                    conn.addActivationKeyUserInput(aparser.getRequiredValue("key"), aparser.getValue("name"));
                    break;
                case "backup-database":
                    conn.backupDatabase();
                    break;
                case "delete-tape":
                    conn.deleteTapeByBarcode(aparser.getRequiredValue("barcode"));
                    break;
                case "delete-ejected-tapes":
                    conn.deleteTapeLostOrEjected();
                    break;
                case "configure":
                    conn.configureFromFile(aparser.getRequiredValue("file"));
                    break;
                case "enable-ssh":
                    conn.enableSsh(new ActivationKey(aparser.getRequiredValue("key")));
                    break;
                case "list-available-drives":
                    conn.listDiskDrivesAvailableShell();
                    break;
                case "list-data-policies":
                    conn.listDataPolicies();
                    break;
                case "list-network-interfaces":
                    if(aparser.getBoolean("active")) {
                        output = conn.listNetworkInterfacesActive();
                    } else if(aparser.getValue("type") != null) {
                        output = conn.listNetworkInterfacesActiveByType(aparser.getValue("type"));
                    } else {
                        output = conn.listNetworkInterfacesAll();
                    }
                    break;
                case "list-nodes":
                    conn.listNodes();
                    break;
                case "list-services":
                    conn.listServices();
                    break;
                case "list-tapes":
                    conn.listTapes(aparser.getValue("state"));
                    break;
                case "load-keys":
                    conn.loadActivationKeys(aparser.getRequiredValue("file"));
                    break;
                case "set-backup-schedule":
                    conn.setBackupSchedule(aparser.getRequiredValue("schedule"), 
                                            aparser.getRequiredValue("time"),
                                            Integer.valueOf(aparser.getRequiredValue("keep-copies")));
                    break;
                case "set-hostname":
                    conn.setHostname(aparser.getRequiredValue("name"));
                    break;
                case "set-ip":
                    conn.createNetworkInterface(aparser.getRequiredValue("address"),
                                                aparser.getRequiredValue("prefix"),
                                                aparser.getBoolean("aggregate"),
                                                aparser.getRequiredValue("type"));
                    break;
                case "set-ntp":
                case "set-ntp-servers":
                    conn.setNtpServers(aparser.getValue("server1"), aparser.getValue("server2"));
                    break;
                case "test":
                    conn.listTapesByBucket();
                    break;
                default:
                    System.err.println("Invalid command [" + aparser.getRequiredValue("command") + "] selected.");
            }

            if(output != null) {
                Display.print(output);
            }
        } catch(Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
