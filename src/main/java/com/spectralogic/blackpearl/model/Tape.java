//=======================================================================
//  Tape.java
//      Description: 
//          This class represents the structure of a tape JSON 
//          object with various attributes such as bucketId, 
//          ejectLabel, serialNumber, and more.
//          
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//=======================================================================

package com.spectralogic.blackpearl.nacre.model;

import com.google.gson.annotations.SerializedName;

public class Tape {
    @SerializedName("bucket_id")
    private String bucketId;
    @SerializedName("eject_label")
    private String ejectLabel;
    @SerializedName("eject_location")
    private String ejectLocation;
    @SerializedName("user_id")
    private String userId;
    @SerializedName("assigned_to_storage_domain")
    private boolean assignedToStorageDomain;
    @SerializedName("storage_domain_member_id")
    private String storageDomainMemberId;
    @SerializedName("data_policy_id")
    private String dataPolicyId;
    private String role;
    private String state;
    @SerializedName("verify_data_after_import")
    private Boolean verifyDataAfterImport;
    @SerializedName("verify_data_prior_to_import")
    private Boolean verifyDataPriorToImport;
    @SerializedName("bar_code")
    private String barCode;
    @SerializedName("eject_pending")
    private Boolean ejectPending;
    @SerializedName("element_address")
    private Integer elementAddress;
    @SerializedName("full_of_data")
    private boolean fullOfData;
    @SerializedName("last_accessed")
    private String lastAccessed;
    @SerializedName("last_verified")
    private String lastVerified;
    @SerializedName("previous_state")
    private String previousState;
    @SerializedName("partially_verified_end_of_tape")
    private Boolean partiallyVerifiedEndOfTape;
    @SerializedName("serial_number")
    private String serialNumber;
    @SerializedName("partition_id")
    private String partitionId;
    private String type;
    @SerializedName("total_raw_capacity")
    private long totalRawCapacity;
    @SerializedName("available_raw_capacity")
    private long availableRawCapacity;
    @SerializedName("description_for_identification")
    private String descriptionForIdentification;
    @SerializedName("write_protected")
    private boolean writeProtected;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("updated_at")
    private String updatedAt;
    private String id;

    //===========================================
    // Getters
    //===========================================
    public String getBucketId() { return bucketId; }
    public String getEjectLabel() { return ejectLabel; }
    public String getEjectLocation() { return ejectLocation; }
    public String getUserId() { return userId; }
    public boolean isAssignedToStorageDomain() { return assignedToStorageDomain; }
    public String getStorageDomainMemberId() { return storageDomainMemberId; }
    public String getDataPolicyId() { return dataPolicyId; }
    public String getRole() { return role; }
    public String getState() { return state; }
    public Boolean getVerifyDataAfterImport() { return verifyDataAfterImport; }
    public Boolean getVerifyDataPriorToImport() { return verifyDataPriorToImport; }
    public String getBarCode() { return barCode; }
    public Boolean getEjectPending() { return ejectPending; }
    public Integer getElementAddress() { return elementAddress; }
    public boolean isFullOfData() { return fullOfData; }
    public String getLastAccessed() { return lastAccessed; }
    public String getLastVerified() { return lastVerified; }
    public String getPreviousState() { return previousState; }
    public Boolean getPartiallyVerifiedEndOfTape() { return partiallyVerifiedEndOfTape; }
    public String getSerialNumber() { return serialNumber; }
    public String getPartitionId() { return partitionId; }
    public String getType() { return type; }
    public long getTotalRawCapacity() { return totalRawCapacity; }
    public long getAvailableRawCapacity() { return availableRawCapacity; }
    public String getDescriptionForIdentification() { return descriptionForIdentification; }
    public boolean isWriteProtected() { return writeProtected; }
    public String getCreatedAt() { return createdAt; }
    public String getUpdatedAt() { return updatedAt; }
    public String getId() { return id; }

    //===========================================
    // Setters
    //===========================================
    public void setBucketId(String bucketId) { this.bucketId = bucketId; }
    public void setEjectLabel(String ejectLabel) { this.ejectLabel = ejectLabel; }
    public void setEjectLocation(String ejectLocation) { this.ejectLocation = ejectLocation; }
    public void setUserId(String userId) { this.userId = userId; }
    public void setAssignedToStorageDomain(boolean assignedToStorageDomain) { this.assignedToStorageDomain = assignedToStorageDomain; }
    public void setStorageDomainMemberId(String storageDomainMemberId) { this.storageDomainMemberId = storageDomainMemberId; }
    public void setDataPolicyId(String dataPolicyId) { this.dataPolicyId = dataPolicyId; }
    public void setRole(String role) { this.role = role; }
    public void setState(String state) { this.state = state; }
    public void setVerifyDataAfterImport(Boolean verifyDataAfterImport) { this.verifyDataAfterImport = verifyDataAfterImport; }
    public void setVerifyDataPriorToImport(Boolean verifyDataPriorToImport) { this.verifyDataPriorToImport = verifyDataPriorToImport; }
    public void setBarCode(String barCode) { this.barCode = barCode; }
    public void setEjectPending(Boolean ejectPending) { this.ejectPending = ejectPending; }
    public void setElementAddress(Integer elementAddress) { this.elementAddress = elementAddress; }
    public void setFullOfData(boolean fullOfData) { this.fullOfData = fullOfData; }
    public void setLastAccessed(String lastAccessed) { this.lastAccessed = lastAccessed; }
    public void setLastVerified(String lastVerified) { this.lastVerified = lastVerified; }
    public void setPreviousState(String previousState) { this.previousState = previousState; }
    public void setPartiallyVerifiedEndOfTape(Boolean partiallyVerifiedEndOfTape) { this.partiallyVerifiedEndOfTape = partiallyVerifiedEndOfTape; }
    public void setSerialNumber(String serialNumber) { this.serialNumber = serialNumber; }
    public void setPartitionId(String partitionId) { this.partitionId = partitionId; }
    public void setType(String type) { this.type = type; }
    public void setTotalRawCapacity(long totalRawCapacity) { this.totalRawCapacity = totalRawCapacity; }
    public void setAvailableRawCapacity(long availableRawCapacity) { this.availableRawCapacity = availableRawCapacity; }
    public void setDescriptionForIdentification(String descriptionForIdentification) { this.descriptionForIdentification = descriptionForIdentification; }
    public void setWriteProtected(boolean writeProtected) { this.writeProtected = writeProtected; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
    public void setUpdatedAt(String updatedAt) { this.updatedAt = updatedAt; }
    public void setId(String id) { this.id = id; }
}

