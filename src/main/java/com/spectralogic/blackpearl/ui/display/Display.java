//===================================================================
// Display.java
//      Description:
//          This purpose of this class is to handle output for the
//          the script.
//
// Created by Sean Snyder
//===================================================================

package com.spectralogic.blackpearl.nacre.ui.display;

import com.spectralogic.blackpearl.nacre.ui.display.serializer.Serializer;
import com.spectralogic.blackpearl.nacre.model.Output;

import java.util.ArrayList;

public class Display {
    private static String output_format = "table";

    public static void print(ArrayList array) {
        // Convert Array to format
        ArrayList<Output> output = Serializer.serializeOutput(array);

        switch(output_format) {
            case "csv":
                break;
            case "shell":
                break;
            case "table":
                Table.print(output);
                break;
        }
    }

    public static void print(ArrayList array, String format) {
        output_format = format;

        print(array);
    }
}
