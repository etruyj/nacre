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
                
                if(iface.getAddresses() != null && iface.getAddresses().size() > 0) {
                    String ips = "";
                    for(NetworkInterface.IpAddress address : iface.getAddresses()) {
                        ips += address.getAddress() + ", ";
                    }

                    ips = ips.substring(0, ips.length()-2);
                    
                    pair = new Output("address", ips);
                    output.add(pair);
                } else {
                    pair = new Output("address", " ");
                    output.add(pair);
                }
                
                pair = new Output("fib", String.valueOf(iface.getFib()));
                output.add(pair);
                
                pair = new Output("mtu", String.valueOf(iface.getMtu()));
                output.add(pair);
                
                pair = new Output("link_speed", String.valueOf(iface.getLinkSpeed()));
                output.add(pair);
                
                pair = new Output("link_status", iface.getLinkStatus());
                output.add(pair);

                if(iface.isUp()) {
                    pair = new Output("link_state", "up");
                    output.add(pair);
                } else {
                    pair = new Output("link_state", "down");
                    output.add(pair);
                }

                pair = new Output("new-line", "");
                output.add(pair);
            }
        }

        return output;
    }
}
