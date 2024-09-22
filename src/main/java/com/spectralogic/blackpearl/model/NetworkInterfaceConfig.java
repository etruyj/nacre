//===================================================================
// NetworkInterfaceConfig.java
//      Description:
//          The purpose of this class is to hold the network interface
//          configuration information.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.model;

public class NetworkInterfaceConfig {
    private String address;
    private String prefix;
    private String gateway;
    private boolean aggregate;

    //=========================================== 
    // Getters
    //=========================================== 
    public String getAddress() { return address; }
    public String getPrefix() { return prefix; }
    public String getGateway() { return gateway; }
    public boolean isAggregate() { return aggregate; }
    public String getDefaultGateway() {
        // The goal is to provide a default gateway
        // for the configuration if it isn't specified.
        if(gateway != null) {
            return gateway;
        } else { // Assuming we're using IPv4 addressing
            // Also assuming a /24 prefix for MVP Will sort
            // this out in later generations.
            String[] ip_octets = address.split("\\.");
            return ip_octets[0] + "." + ip_octets[1]
                + "." + ip_octets[2] + ".1";
        }
    }

    //=========================================== 
    // Setters
    //=========================================== 
    public void setAddress(String address) { this.address = address; }
    public void setPrefix(String prefix) { this.prefix = prefix; }
    public void setGateway(String gateway) { this.gateway = gateway; }
    public void setAggregate(boolean aggregate) { this.aggregate = aggregate; }
}
