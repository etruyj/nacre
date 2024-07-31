//===================================================================
// Share.java
//      Description:
//          A model for a NAS share.
// 
// Created by Sean Snyder
// Copyright Spectra Logic Corporation 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.model;

import com.google.gson.annotations.SerializedName;

public class Share {
    private String name;
    @SerializedName("storage_id")
    private String storage_id;
    private String path;
    private String type;
    @SerializedName("service_id")
    private String service_id;
    @SerializedName("volume_id")
    private String volume_id;
    @SerializedName("created_at")
    private String created_at;
    @SerializedName("updated_at")
    private String updated_at;
    private String id;
    @SerializedName("mount_point")
    private String mount_point;
    private String status;
    
    //===========================================
    // Constructors
    //===========================================
    public Share() {} // blank constructor to allow for a copy constructor

    // Copy constructor
    public Share(Share other) {
        this.setName(other.getName());
        this.setStorageId(other.getStorageId());
        this.setPath(other.getPath());
        this.setType(other.getType());
        this.setServiceId(other.getServiceId());
        this.setVolumeId(other.getVolumeId());
        this.setCreatedAt(other.getCreatedAt());
        this.setUpdatedAt(other.getUpdatedAt());
        this.setId(other.getId());
        this.setMountPoint(other.getMountPoint());
        this.setStatus(other.getStatus());
    }

    //===========================================
    // Gettors
    //===========================================
    public String getName() { return name; }
    public String getStorageId() { return storage_id; }
    public String getPath() { return path; }
    public String getType() { return type; }
    public String getServiceId() { return service_id; }
    public void setServiceId(String service_id) { this.service_id = service_id; }
    public String getVolumeId() { return volume_id; }
    public String getCreatedAt() { return created_at; }
    public String getUpdatedAt() { return updated_at; }
    public String getId() { return id; }
    public String getMountPoint() { return mount_point; }
    public String getStatus() { return status; }
    
    //===========================================
    // Settors
    //===========================================
    public void setName(String name) { this.name = name; }
    public void setStorageId(String storage_id) { this.storage_id = storage_id; }
    public void setPath(String path) { this.path = path; }
    public void setType(String type) { this.type = type; }
    public void setVolumeId(String volume_id) { this.volume_id = volume_id; }
    public void setCreatedAt(String created_at) { this.created_at = created_at; }
    public void setUpdatedAt(String updated_at) { this.updated_at = updated_at; }
    public void setId(String id) { this.id = id; }
    public void setMountPoint(String mount_point) { this.mount_point = mount_point; }
    public void setStatus(String status) { this.status = status; }

}
