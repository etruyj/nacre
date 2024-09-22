//===================================================================
// StorageConversion.java
//      Description:
//          This class handles storage unit conversions to and from
//          human-readable values to bytes.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.util.convert;

public class StorageConversion {
    public static long humanReadableToBytes(String value) throws Exception {
        // break the units from the numbers
        String[] parts = value.split(" ");

        String units = "B";

        if(parts.length == 2) {
            long number = Long.valueOf(parts[0]);
            units = parts[1];

            while(!units.equals("B")) {
                switch(units) {
                    case "PB":
                        number *= 1000;
                        units = "TB";
                        break;
                    case "TB":
                        number *= 1000;
                        units = "GB";
                        break;
                    case "GB":
                        number *= 1000;
                        units = "MB";
                        break;
                    case "MB":
                        number *= 1000;
                        units = "KB";
                        break;
                    case "KB":
                        number *= 1000;
                        units = "B";
                        break;
                    case "PiB":
                        number *= 1024;
                        units = "TiB";
                        break;
                    case "TiB":
                        number *= 1024;
                        units = "GiB";
                        break;
                    case "GiB":
                        number *= 1024;
                        units = "MiB";
                        break;
                    case "MiB":
                        number *= 1024;
                        units = "KiB";
                        break;
                    case "KiB":
                        number *= 1024;
                        units = "B";
                        break;
                    default:
                        throw new Exception("Unable to parse unit " + units);
                }
            }
            return number;
        } else {
            try {
                return Long.valueOf(value);
            } catch(Exception e) {
                throw new Exception("Unable to convert " + value + " to bytes.");
            }
        }
    }
}
