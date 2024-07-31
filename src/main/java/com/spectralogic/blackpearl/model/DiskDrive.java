//======================================================================
// DiskDrive.java
//      Description:
//          This model holds the disk drive information returned by the
//          data disk API call.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//======================================================================

package com.spectralogic.blackpearl.nacre.model;

import com.google.gson.annotations.SerializedName;

public class DiskDrive {
    private String id;
    @SerializedName("case_id")
    private String caseId;
    private String devName;
    @SerializedName("physical_path")
    private String physicalPath;
    private Boolean powered;
    @SerializedName("ses_status")
    private String sesStatus;
    private int slot;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("updated_at")
    private String updatedAt;
    private Boolean locate;
    private Boolean reserved;
    private String status;
    @SerializedName("pool_status")
    private String poolStatus;
    @SerializedName("case_slot")
    private int caseSlot;
    @SerializedName("pool_id")
    private String poolId;
    @SerializedName("avg_erase_count")
    private Integer avgEraseCount;
    @SerializedName("bezel")
    private Boolean bezel;
    @SerializedName("cam_status")
    private String camStatus;
    private Boolean encryption;
    @SerializedName("encryption_state")
    private String encryptionState;
    @SerializedName("firmware_version")
    private String firmwareVersion;
    @SerializedName("interface_type")
    private String interfaceType;
    @SerializedName("label_guid")
    private String labelGuid;
    @SerializedName("label_state")
    private String labelState;
    @SerializedName("percentage_used")
    private Integer percentageUsed;
    @SerializedName("physical_sector_size")
    private int physicalSectorSize;
    @SerializedName("pool_hostid")
    private String poolHostId;
    private String product;
    @SerializedName("serial_number")
    private String serialNumber;
    @SerializedName("sector_size")
    private int sectorSize;
    private long size;
    private String speed;
    private String vendor;

    //===========================================
    // Getters
    //===========================================
    public String getId() { return id; }
    public String getCaseId() { return caseId; }
    public String getDevName() { return devName; }
    public String getPhysicalPath() { return physicalPath; }
    public Boolean getPowered() { return powered; }
    public String getSesStatus() { return sesStatus; }
    public int getSlot() { return slot; }
    public String getCreatedAt() { return createdAt; }
    public String getUpdatedAt() { return updatedAt; }
    public Boolean getLocate() { return locate; }
    public Boolean getReserved() { return reserved; }
    public String getStatus() { return status; }
    public String getPoolStatus() { return poolStatus; }
    public int getCaseSlot() { return caseSlot; }
    public String getPoolId() { return poolId; }
    public Integer getAvgEraseCount() { return avgEraseCount; }
    public Boolean getBezel() { return bezel; }
    public String getCamStatus() { return camStatus; }
    public Boolean getEncryption() { return encryption; }
    public String getEncryptionState() { return encryptionState; }
    public String getFirmwareVersion() { return firmwareVersion; }
    public String getInterfaceType() { return interfaceType; }
    public String getLabelGuid() { return labelGuid; }
    public String getLabelState() { return labelState; }
    public Integer getPercentageUsed() { return percentageUsed; }
    public int getPhysicalSectorSize() { return physicalSectorSize; }
    public String getPoolHostId() { return poolHostId; }
    public String getProduct() { return product; }
    public String getSerialNumber() { return serialNumber; }
    public int getSectorSize() { return sectorSize; }
    public long getSize() { return size; }
    public String getSpeed() { return speed; }
    public String getVendor() { return vendor; }

    //===========================================
    // Setters
    //===========================================
    public void setId(String id) { this.id = id; }
    public void setCaseId(String caseId) { this.caseId = caseId; }
    public void setDevName(String devName) { this.devName = devName; }
    public void setPhysicalPath(String physicalPath) { this.physicalPath = physicalPath; }
    public void setPowered(Boolean powered) { this.powered = powered; }
    public void setSesStatus(String sesStatus) { this.sesStatus = sesStatus; }
    public void setSlot(int slot) { this.slot = slot; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
    public void setUpdatedAt(String updatedAt) { this.updatedAt = updatedAt; }
    public void setLocate(Boolean locate) { this.locate = locate; }
    public void setReserved(Boolean reserved) { this.reserved = reserved; }
    public void setStatus(String status) { this.status = status; }
    public void setPoolStatus(String poolStatus) { this.poolStatus = poolStatus; }
    public void setCaseSlot(int caseSlot) { this.caseSlot = caseSlot; }
    public void setPoolId(String poolId) { this.poolId = poolId; }
    public void setAvgEraseCount(Integer avgEraseCount) { this.avgEraseCount = avgEraseCount; }
    public void setBezel(Boolean bezel) { this.bezel = bezel; }
    public void setCamStatus(String camStatus) { this.camStatus = camStatus; }
    public void setEncryption(Boolean encryption) { this.encryption = encryption; }
    public void setEncryptionState(String encryptionState) { this.encryptionState = encryptionState; }
    public void setFirmwareVersion(String firmwareVersion) { this.firmwareVersion = firmwareVersion; }
    public void setInterfaceType(String interfaceType) { this.interfaceType = interfaceType; }
    public void setLabelGuid(String labelGuid) { this.labelGuid = labelGuid; }
    public void setLabelState(String labelState) { this.labelState = labelState; }
    public void setPercentageUsed(Integer percentageUsed) { this.percentageUsed = percentageUsed; }
    public void setPhysicalSectorSize(int physicalSectorSize) { this.physicalSectorSize = physicalSectorSize; }
    public void setPoolHostId(String poolHostId) { this.poolHostId = poolHostId; }
    public void setProduct(String product) { this.product = product; }
    public void setSerialNumber(String serialNumber) { this.serialNumber = serialNumber; }
    public void setSectorSize(int sectorSize) { this.sectorSize = sectorSize; }
    public void setSize(long size) { this.size = size; }
    public void setSpeed(String speed) { this.speed = speed; }
    public void setVendor(String vendor) { this.vendor = vendor; }
}

