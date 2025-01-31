//===================================================================
// Serializer.java
//      Description:
//          This class takes unformed output and converts it to 
//          a format that can be output to the shell.
//
// Created by Sean Snyder
//===================================================================

package com.spectralogic.blackpearl.nacre.ui.display.serializer;

import com.spectralogic.blackpearl.nacre.model.NetworkInterface;
import com.spectralogic.blackpearl.nacre.model.Output;

import java.util.ArrayList;

public class Serializer {
    public static ArrayList<Output> serializeOutput(ArrayList array) {
        ArrayList<Output> output_list = new ArrayList<Output>();

        if(array != null && array.size() > 0) {
            if(array.get(0) instanceof NetworkInterface) {
                output_list = SerializeNetworkInterface.forOutput(array);
            }
        }

        return output_list;
    }
}
