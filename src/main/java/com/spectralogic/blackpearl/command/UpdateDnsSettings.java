//===================================================================
// UpdateDnsSettings.java
//      Description:
//          The purpose of this class is to set/update the DNS server
//          settings on the BlackPearl.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.command;

import com.spectralogic.blackpearl.nacre.api.BpConnector;
import com.spectralogic.blackpearl.nacre.model.NetworkInterface;
import com.spectralogic.blackpearl.nacre.model.NetworkInterfaceSend;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UpdateDnsSettings {
    private static final Logger log = LoggerFactory.getLogger(UpdateDnsSettings.class);

    public static NetworkInterface fromList(List<String> servers, BpConnector pearl) {
        log.info("Setting DNS to (" + servers.size() + ") servers.");

        NetworkInterface ret_inter = null; // returned interface
        ArrayList<String> address_list;

        try {
            ArrayList<NetworkInterface> inter_list = ListNetworkInterfaces.management(pearl);
        
            for(NetworkInterface interf : inter_list) {
                log.info("Setting DNS on interface [" + interf.getId() + "]");
                
                NetworkInterfaceSend send_interf = new NetworkInterfaceSend(interf); // DUMB DUMB DUMB. The API IS DUMB
                
                send_interf.setNameServers(servers);

                ret_inter = pearl.updateNetworkInterface(send_interf);

                if(ret_inter != null) {
                    log.info("Successfully updated DNS on interface [" + interf.getId() + "]");
                } else {
                    log.warn("Failed to update DNS on interface [" + interf.getId() + "]");
                }
            }
        } catch(Exception e) {
            log.error(e.getMessage());
            log.error("Failed to set DNS servers.");
        }

        return ret_inter;
    }
}
