//======================================================================
// BlackPearlNode.java
//      Description: 
//              This class represents the system configuration of a device
//              including its name, part number, serial number, software 
//              version, and other attributes like expansion cards, 
//              hotpair status, etc.
//
// Created by Sean Snyder 
// Copyright Spectra Logic 2024
//======================================================================

package com.spectralogic.blackpearl.nacre.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class BlackPearlNode {
    private String name;
    @SerializedName("part_number")
    private String partNumber;
    @SerializedName("serial_number")
    private String serialNumber;
    @SerializedName("software_version")
    private String softwareVersion;
    @SerializedName("safe_mode")
    private boolean safeMode;
    @SerializedName("cod_restore_in_progress")
    private Boolean codRestoreInProgress;
    @SerializedName("manufacturing_mode")
    private boolean manufacturingMode;
    @SerializedName("run_state")
    private String runState;
    @SerializedName("pending_shutdown")
    private RebootState pendingShutdown;
    private long memory;
    @SerializedName("expansion_cards")
    private List<ExpansionCard> expansionCards;
    @SerializedName("hotpair_status")
    private HotpairStatus hotpairStatus;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("updated_at")
    private String updatedAt;
    private String id;

    //===========================================
    // Getters
    //===========================================
    public String getName() { return name; }
    public String getPartNumber() { return partNumber; }
    public String getSerialNumber() { return serialNumber; }
    public String getSoftwareVersion() { return softwareVersion; }
    public boolean isSafeMode() { return safeMode; }
    public Boolean getCodRestoreInProgress() { return codRestoreInProgress; }
    public boolean isManufacturingMode() { return manufacturingMode; }
    public String getRunState() { return runState; }
    public RebootState getPendingShutdown() { return pendingShutdown; }
    public long getMemory() { return memory; }
    public List<ExpansionCard> getExpansionCards() { return expansionCards; }
    public HotpairStatus getHotpairStatus() { return hotpairStatus; }
    public String getCreatedAt() { return createdAt; }
    public String getUpdatedAt() { return updatedAt; }
    public String getId() { return id; }

    //===========================================
    // Setters
    //===========================================
    public void setName(String name) { this.name = name; }
    public void setPartNumber(String partNumber) { this.partNumber = partNumber; }
    public void setSerialNumber(String serialNumber) { this.serialNumber = serialNumber; }
    public void setSoftwareVersion(String softwareVersion) { this.softwareVersion = softwareVersion; }
    public void setSafeMode(boolean safeMode) { this.safeMode = safeMode; }
    public void setCodRestoreInProgress(Boolean codRestoreInProgress) { this.codRestoreInProgress = codRestoreInProgress; }
    public void setManufacturingMode(boolean manufacturingMode) { this.manufacturingMode = manufacturingMode; }
    public void setRunState(String runState) { this.runState = runState; }
    public void setPendingShutdown(RebootState pendingShutdown) { this.pendingShutdown = pendingShutdown; }
    public void setMemory(long memory) { this.memory = memory; }
    public void setExpansionCards(List<ExpansionCard> expansionCards) { this.expansionCards = expansionCards; }
    public void setHotpairStatus(HotpairStatus hotpairStatus) { this.hotpairStatus = hotpairStatus; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
    public void setUpdatedAt(String updatedAt) { this.updatedAt = updatedAt; }
    public void setId(String id) { this.id = id; }

    //===========================================
    // Inner classe
    //===========================================
    public static class ExpansionCard {
        private int slot;
        private String type;
        private List<Integer> address;
        private String devname;
        private int unit;
        private List<String> devices;

        //=======================================
        // Getters
        //=======================================
        public int getSlot() { return slot; }
        public String getType() { return type; }
        public List<Integer> getAddress() { return address; }
        public String getDevname() { return devname; }
        public int getUnit() { return unit; }
        public List<String> getDevices() { return devices; }

        //=======================================
        // Setters
        //=======================================
        public void setSlot(int slot) { this.slot = slot; }
        public void setType(String type) { this.type = type; }
        public void setAddress(List<Integer> address) { this.address = address; }
        public void setDevname(String devname) { this.devname = devname; }
        public void setUnit(int unit) { this.unit = unit; }
        public void setDevices(List<String> devices) { this.devices = devices; }
    }

    public static class HotpairStatus {
        @SerializedName("active_hostid")
        private String activeHostid;
        private boolean hotpair;
        @SerializedName("hotpair_version")
        private String hotpairVersion;
        @SerializedName("local_serial")
        private String localSerial;
        @SerializedName("local_state")
        private String localState;
        @SerializedName("peer_previous_serial")
        private String peerPreviousSerial;
        @SerializedName("peer_serial")
        private String peerSerial;
        @SerializedName("active_zfs_hostid")
        private String activeZfsHostid;
        private String state;
        @SerializedName("peer_state")
        private String peerState;

        //=======================================
        // Getters
        //=======================================
        public String getActiveHostid() { return activeHostid; }
        public boolean isHotpair() { return hotpair; }
        public String getHotpairVersion() { return hotpairVersion; }
        public String getLocalSerial() { return localSerial; }
        public String getLocalState() { return localState; }
        public String getPeerPreviousSerial() { return peerPreviousSerial; }
        public String getPeerSerial() { return peerSerial; }
        public String getActiveZfsHostid() { return activeZfsHostid; }
        public String getState() { return state; }
        public String getPeerState() { return peerState; }

        //=======================================
        // Setters
        //=======================================
        public void setActiveHostid(String activeHostid) { this.activeHostid = activeHostid; }
        public void setHotpair(boolean hotpair) { this.hotpair = hotpair; }
        public void setHotpairVersion(String hotpairVersion) { this.hotpairVersion = hotpairVersion; }
        public void setLocalSerial(String localSerial) { this.localSerial = localSerial; }
        public void setLocalState(String localState) { this.localState = localState; }
        public void setPeerPreviousSerial(String peerPreviousSerial) { this.peerPreviousSerial = peerPreviousSerial; }
        public void setPeerSerial(String peerSerial) { this.peerSerial = peerSerial; }
        public void setActiveZfsHostid(String activeZfsHostid) { this.activeZfsHostid = activeZfsHostid; }
        public void setState(String state) { this.state = state; }
        public void setPeerState(String peerState) { this.peerState = peerState; }
    }

    //===========================================
    // Inner Classes
    //===========================================
    public class RebootState {
        private String command;
        private String message;
        private String pid;
        private String time;
        private String action;

        //=======================================
        // Getters
        //=======================================
        public String getCommand() { return command; }
        public String getMessage() { return message; }
        public String getPid() { return pid; }
        public String getTime() { return time; }
        public String getAction() { return action; }

        //=======================================
        // Setters
        //=======================================
        public void setCommand(String command) { this.command = command; }
        public void setMessage(String message) { this.message = message; }
        public void setPid(String pid) { this.pid = pid; }
        public void setTime(String time) { this.time = time; }
        public void setAction(String action) { this.action = action; }
    }
}

