//===================================================================
// NetworkInterfaceSend.java
//      Description:
//          This class is required to send network interface configuration
//          information to the server because we use two variables with
//          the same name and different types depending on if we're
//          sending the info to BP or retrieving it. Dumb.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.model;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class NetworkInterfaceSend {
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
    private String mtu;
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
    private List<String> addresses;
    @SerializedName("hotpair_addresses")
    private List<String> hotpairAddresses;
    @SerializedName("lagg_port_options")
    private Boolean laggPortOptions;
    @SerializedName("lagg_ports")
    private List<String> laggPorts;
    @SerializedName("slots")
    private List<String> slots;
    @SerializedName("name_servers")
    private List<String> nameServers;

    //===========================================
    // Constructors
    //===========================================
    public NetworkInterfaceSend() {} // blank to allow for a copy constructor

    public NetworkInterfaceSend(NetworkInterface other) {
        setId(other.getId());
        setLinkStatus(other.getLinkStatus());
        setDhcp(other.isDhcp());
        setExists(other.isExists());
        setFib(other.getFib());
        setFqdn(other.getFqdn());
        setGroup(other.getGroup());
        setLaggId(other.getLaggId());
        setLinkSpeed(other.getLinkSpeed());
        setMtu(String.valueOf(other.getMtu()));
        setPortType(other.getPortType());
        if(other.getSearchDomains() != null) {
            setSearchDomains(new ArrayList<>(other.getSearchDomains()));
        } else {
            setSearchDomains(new ArrayList<>());
        }

        setSlot(other.getSlot());
        if(other.getSupportedMedia() != null) {
            setSupportedMedia(new ArrayList<>(other.getSupportedMedia()));
        } else {
            setSupportedMedia(new ArrayList<>());
        }

        setIfname(other.getIfname());
        setPeername(other.getPeername());
        setManualDns(other.isManualDns());
        setOptions(other.getOptions());
        setMac(other.getMac());
        setCreatedAt(other.getCreatedAt());
        setUpdatedAt(other.getUpdatedAt());
        setUp(other.isUp());
        setType(other.getType());
        setName(other.getName());
        setDefaultGateway(other.getDefaultGateway());
        setIpv6DefaultGateway(other.getIpv6DefaultGateway());
        
        if(other.getHotpairAddresses() != null) {
            setHotpairAddresses(new ArrayList<>(other.getHotpairAddresses()));
        } else {
            setHotpairAddresses(new ArrayList<>());
        }
        
        if(other.getLaggPorts() != null) {
            setLaggPorts(new ArrayList<>(other.getLaggPorts()));
        } else {
            setLaggPorts(new ArrayList<>());
        }

        if(other.getSlots() != null) {
            setSlots(new ArrayList<>(other.getSlots()));
        } else {
            setSlots(new ArrayList<>());
        }

        if(other.getNameServers() != null) {
            setNameServers(new ArrayList<>(other.getNameServers()));
        } else {
            setNameServers(new ArrayList<>());
        }

        // Convert IpAddresses to a list of string
        // because of dumb stuff
        ArrayList<String> address_list = new ArrayList<String>();
        for(NetworkInterface.IpAddress address : other.getAddresses()) {
            address_list.add(address.getAddress());
        }

        setAddresses(address_list);
    }

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
    public String getMtu() { return mtu; }
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
    public List<String> getAddresses() { return addresses; }
    public List<String> getHotpairAddresses() { return hotpairAddresses; }
    public Boolean isLaggPortOptions() { return laggPortOptions; }
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
    public void setMtu(String mtu) { this.mtu = mtu; }
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
    public void setAddresses(List<String> addresses) { this.addresses = addresses; }
    public void setHotpairAddresses(List<String> hotpairAddresses) { this.hotpairAddresses = hotpairAddresses; }
    public void setLaggPortOptions(Boolean options) { this.laggPortOptions = options; }
    public void setLaggPorts(List<String> laggPorts) { this.laggPorts = laggPorts; }
    public void setSlots(List<String> slots) { this.slots = slots; }
    public void setNameServers(List<String> nameServers) { this.nameServers = nameServers; }
    public void setAddresses(ArrayList<String> address) { this.addresses = address; }
    @Deprecated // Doing it this way was dumb. Split into setAddresses(ArrayList) and setAddress(String)
    public void setAddresses(String address) {
        ArrayList<String> addresses = new ArrayList<String>();
        addresses.add(address);
        this.setAddresses(addresses);
    }
    public void setAddress(String address) {
        ArrayList<String> addresses = new ArrayList<String>();
        addresses.add(address);
        this.setAddresses(addresses);
    }
}
