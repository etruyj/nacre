//======================================================================
//  NetworkInterface.java
//      Description:
//          This class represents a network interface configuration, 
//          including ID, link status, DHCP settings, media options, 
//          MAC address, IP addresses, and related network details.
//          
// Created by Sean Snyder
// Copyright Spectra Logic 2024
// ======================================================================

package com.spectralogic.blackpearl.nacre.model;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class NetworkInterface {
    private String id;
    @SerializedName("link_status")
    private String linkStatus;
    private boolean dhcp;
    private boolean exists;
    private int fib;
    private String fqdn;
    private String group;
    @SerializedName("lagg_id")
    private String laggId;
    @SerializedName("link_speed")
    private long linkSpeed;
    private int mtu;
    @SerializedName("port_type")
    private String portType;
    @SerializedName("search_domains")
    private List<String> searchDomains;
    private int slot;
    @SerializedName("supported_media")
    private List<String> supportedMedia;
    private String ifname;
    private String peername;
    @SerializedName("manual_dns")
    private boolean manualDns;
    private long options;
    private String mac;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("updated_at")
    private String updatedAt;
    private boolean up;
    private String type;
    private String name;
    @SerializedName("default_gateway")
    private String defaultGateway;
    @SerializedName("ipv6_default_gateway")
    private String ipv6DefaultGateway;
    private List<IpAddress> addresses;
    @SerializedName("hotpair_addresses")
    private List<String> hotpairAddresses;
    @SerializedName("lagg_ports")
    private List<String> laggPorts;
    @SerializedName("slots")
    private List<String> slots;
    @SerializedName("name_servers")
    private List<String> nameServers;
    
    //===========================================
    // Getters
    //===========================================
    public String getId() { return id; }
    public String getLinkStatus() { return linkStatus; }
    public boolean isDhcp() { return dhcp; }
    public boolean isExists() { return exists; }
    public int getFib() { return fib; }
    public String getFqdn() { return fqdn; }
    public String getGroup() { return group; }
    public String getLaggId() { return laggId; }
    public long getLinkSpeed() { return linkSpeed; }
    public int getMtu() { return mtu; }
    public String getPortType() { return portType; }
    public List<String> getSearchDomains() { return searchDomains; }
    public int getSlot() { return slot; }
    public List<String> getSupportedMedia() { return supportedMedia; }
    public String getIfname() { return ifname; }
    public String getPeername() { return peername; }
    public boolean isManualDns() { return manualDns; }
    public long getOptions() { return options; }
    public String getMac() { return mac; }
    public String getCreatedAt() { return createdAt; }
    public String getUpdatedAt() { return updatedAt; }
    public boolean isUp() { return up; }
    public String getType() { return type; }
    public String getName() { return name; }
    public String getDefaultGateway() { return defaultGateway; }
    public String getIpv6DefaultGateway() { return ipv6DefaultGateway; }
    public List<IpAddress> getAddresses() { return addresses; }
    public List<String> getHotpairAddresses() { return hotpairAddresses; }
    public List<String> getLaggPorts() { return laggPorts; }
    public List<String> getSlots() { return slots; }
    public List<String> getNameServers() { return nameServers; }

    //===========================================
    // Setters
    //===========================================
    public void setId(String id) { this.id = id; }
    public void setLinkStatus(String linkStatus) { this.linkStatus = linkStatus; }
    public void setDhcp(boolean dhcp) { this.dhcp = dhcp; }
    public void setExists(boolean exists) { this.exists = exists; }
    public void setFib(int fib) { this.fib = fib; }
    public void setFqdn(String fqdn) { this.fqdn = fqdn; }
    public void setGroup(String group) { this.group = group; }
    public void setLaggId(String laggId) { this.laggId = laggId; }
    public void setLinkSpeed(long linkSpeed) { this.linkSpeed = linkSpeed; }
    public void setMtu(int mtu) { this.mtu = mtu; }
    public void setPortType(String portType) { this.portType = portType; }
    public void setSearchDomains(List<String> searchDomains) { this.searchDomains = searchDomains; }
    public void setSlot(int slot) { this.slot = slot; }
    public void setSupportedMedia(List<String> supportedMedia) { this.supportedMedia = supportedMedia; }
    public void setIfname(String ifname) { this.ifname = ifname; }
    public void setPeername(String peername) { this.peername = peername; }
    public void setManualDns(boolean manualDns) { this.manualDns = manualDns; }
    public void setOptions(long options) { this.options = options; }
    public void setMac(String mac) { this.mac = mac; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
    public void setUpdatedAt(String updatedAt) { this.updatedAt = updatedAt; }
    public void setUp(boolean up) { this.up = up; }
    public void setType(String type) { this.type = type; }
    public void setName(String name) { this.name = name; }
    public void setDefaultGateway(String defaultGateway) { this.defaultGateway = defaultGateway; }
    public void setIpv6DefaultGateway(String ipv6DefaultGateway) { this.ipv6DefaultGateway = ipv6DefaultGateway; }
    public void setAddresses(List<IpAddress> addresses) { this.addresses = addresses; }
    public void setHotpairAddresses(List<String> hotpairAddresses) { this.hotpairAddresses = hotpairAddresses; }
    public void setLaggPorts(List<String> laggPorts) { this.laggPorts = laggPorts; }
    public void setSlots(List<String> slots) { this.slots = slots; }
    public void setNameServers(List<String> nameServers) { this.nameServers = nameServers; }

    //===========================================
    // Inner Classes
    //===========================================
    public static class IpAddress {
        private String address;
        private boolean autoconf;

        //=======================================
        // Getters
        //=======================================
        public String getAddress() { return address; }
        public boolean getAutoconf() { return autoconf; }
        
        //=======================================
        // Setters
        //=======================================
        public void setAddress(String address) { this.address = address; }
        public void setAutoconf(boolean autoconf) { this.autoconf = autoconf; }
    }
}

