//===================================================================
// ServerStatus.java
//      Description:
//          The purpose of this class is to verify the server is 
//          online.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.util.http;

import com.spectralogic.vail.vapir.util.http.AddressResolver;
import java.net.InetAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerStatus {
    private static final Logger log = LoggerFactory.getLogger(ServerStatus.class);

    public static boolean isOnline(String endpoint, int timeout_milliseconds) throws Exception {
        log.debug("Verifying server at " + endpoint + " is online. Timeout is set to " + timeout_milliseconds + " milliseconds.");
        String ip_address = AddressResolver.resolveDomainNameToIP(endpoint);

        log.debug("Endpoint [" + endpoint + "] is located at IP address " + ip_address);
    
        return ping(ip_address, timeout_milliseconds);
    }

    public static boolean ping(String ip_address, int timeout_milliseconds) throws Exception {
        InetAddress ip_addr = InetAddress.getByName(ip_address);

        log.debug("Pinging [" + ip_address + "]....");

        if(ip_addr.isReachable(timeout_milliseconds)) {
            log.debug("Server is reachable.");
            return true;
        } else {
            log.debug("Server is not reachable");
            return false;
        }
    }
}
