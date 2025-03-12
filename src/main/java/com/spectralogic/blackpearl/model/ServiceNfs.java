//=======================================================================
//  ServiceNfs.java
//      Description: 
//          This class represents the configuration of an NFS (Network 
//          File System) with attributes such as tcp, udp, threads, 
//          dependencyAttrs, name, type, and more.
//          
//  Created by Sean Snyder 
//  Copyright Spectra Logic 2024
//=======================================================================

package com.spectralogic.blackpearl.nacre.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ServiceNfs extends Service{
    @SerializedName("tcp")
    private boolean tcp;
    @SerializedName("udp")
    private boolean udp;
    private int threads;
    @SerializedName("dependency_attrs")
    private List<Object> dependencyAttrs;
    @SerializedName("sub_type")
    private String subType;
    @SerializedName("state")
    private String state;
    private boolean enabled;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("updated_at")
    private String updatedAt;
    private String status;

    //===========================================
    // Getters
    //===========================================
    public boolean isTcp() { return tcp; }
    public boolean isUdp() { return udp; }
    public int getThreads() { return threads; }
    public List<Object> getDependencyAttrs() { return dependencyAttrs; }
    public String getSubType() { return subType; }
    public String getState() { return state; }
    public boolean isEnabled() { return enabled; }
    public String getCreatedAt() { return createdAt; }
    public String getUpdatedAt() { return updatedAt; }
    public String getStatus() { return status; }

    //===========================================
    // Setters
    //===========================================
    public void setTcp(boolean tcp) { this.tcp = tcp; }
    public void setUdp(boolean udp) { this.udp = udp; }
    public void setThreads(int threads) { this.threads = threads; }
    public void setDependencyAttrs(List<Object> dependencyAttrs) { this.dependencyAttrs = dependencyAttrs; }
    public void setSubType(String subType) { this.subType = subType; }
    public void setState(String state) { this.state = state; }
    public void setEnabled(boolean enabled) { this.enabled = enabled; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
    public void setUpdatedAt(String updatedAt) { this.updatedAt = updatedAt; }
    public void setStatus(String status) { this.status = status; }
}

