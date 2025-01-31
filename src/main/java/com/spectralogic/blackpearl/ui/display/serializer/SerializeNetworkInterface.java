//===================================================================
// SerializeNetworkInterface.java
//      Description:
//          This class serializes the network interface response for
//          output by converting the fields to a key-value pair.
//
// Created by Sean Snyder
//===================================================================

package com.spectralogic.blackpearl.nacre.ui.display.serializer;

import com.spectralogic.blackpearl.nacre.model.NetworkInterface;
import com.spectralogic.blackpearl.nacre.model.Output;

import java.util.ArrayList;

public class SerializeNetworkInterface {
    public static ArrayList<Output> forOutput(ArrayList<NetworkInterface> int_list) {
        ArrayList<Output> output = new ArrayList<Output>();
        Output pair = null;

        if(int_list != null) {
            for(NetworkInterface iface : int_list) {
                pair = new Output("name", iface.getName());
                output.add(pair);
                
                pair = new Output("type", iface.getType());
                output.add(pair);
                
                pair = new Output("link_speed", String.valueOf(iface.getLinkSpeed()));
                output.add(pair);
                
                pair = new Output("new-line", "");
                output.add(pair);
            }
        }

        return output;
    }
}
