//======================================================================
// Message.java
//      Description
//          This class represents an alert model containing message data
//          about system events, such as severity, SNMP data, timestamps,
//          and installation details.
//
// Created by Sean Snyder 
//======================================================================

package com.spectralogic.blackpearl.nacre.model;

import com.google.gson.annotations.SerializedName;
import java.util.Map;

public class Message {
    private int id;
    private int severity;
    @SerializedName("snmp_name")
    private String snmp_name;
    @SerializedName("snmp_details")
    private Map<String, Object> snmp_details;
    @SerializedName("created_at")
    private String created_at;
    @SerializedName("updated_at")
    private String updated_at;
    private boolean read;
    private String key;
    private boolean resolved;
    @SerializedName("description_path")
    private String description_path;
    @SerializedName("details_path")
    private String details_path;
    private boolean alert;
    @SerializedName("description_parameters")
    private Map<String, Object> description_parameters;
    @SerializedName("details_parameters")
    private Map<String, String> details_parameters;
    @SerializedName("description_text")
    private String description_text;
    @SerializedName("details_text")
    private String details_text;

    //===========================================
    // Getters
    //===========================================
    public int getId() { return id; }
    public int getSeverity() { return severity; }
    public String getSnmpName() { return snmp_name; }
    public Map<String, Object> getSnmpDetails() { return snmp_details; }
    public String getCreatedAt() { return created_at; }
    public String getUpdatedAt() { return updated_at; }
    public boolean isRead() { return read; }
    public String getKey() { return key; }
    public boolean isResolved() { return resolved; }
    public String getDescriptionPath() { return description_path; }
    public String getDetailsPath() { return details_path; }
    public boolean isAlert() { return alert; }
    public Map<String, Object> getDescriptionParameters() { return description_parameters; }
    public Map<String, String> getDetailsParameters() { return details_parameters; }
    public String getDescriptionText() { return description_text; }
    public String getDetailsText() { return details_text; }

    //===========================================
    // Setters
    //===========================================
    public void setId(int id) { this.id = id; }
    public void setSeverity(int severity) { this.severity = severity; }
    public void setSnmpName(String snmp_name) { this.snmp_name = snmp_name; }
    public void setSnmpDetails(Map<String, Object> snmp_details) { this.snmp_details = snmp_details; }
    public void setCreatedAt(String created_at) { this.created_at = created_at; }
    public void setUpdatedAt(String updated_at) { this.updated_at = updated_at; }
    public void setRead(boolean read) { this.read = read; }
    public void setKey(String key) { this.key = key; }
    public void setResolved(boolean resolved) { this.resolved = resolved; }
    public void setDescriptionPath(String description_path) { this.description_path = description_path; }
    public void setDetailsPath(String details_path) { this.details_path = details_path; }
    public void setAlert(boolean alert) { this.alert = alert; }
    public void setDescriptionParameters(Map<String, Object> description_parameters) { this.description_parameters = description_parameters; }
    public void setDetailsParameters(Map<String, String> details_parameters) { this.details_parameters = details_parameters; }
    public void setDescriptionText(String description_text) { this.description_text = description_text; }
    public void setDetailsText(String details_text) { this.details_text = details_text; }
}

