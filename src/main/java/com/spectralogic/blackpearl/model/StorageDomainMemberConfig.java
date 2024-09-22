//===================================================================
// StorageDomainMemberConfig.java
//      Description:
//          This class allows for simplified input fields when loading
//          configurations. Users can specify human-readable fields,
//          such as partition or tapePartition instead of having to
//          identify specific partition information.
//===================================================================

package com.spectralogic.blackpearl.nacre.model;

import com.google.gson.annotations.SerializedName;

public class StorageDomainMemberConfig extends StorageDomainMember {
    private String partition;
    @SerializedName("tape_partition")
    private String tapePartition;
    @SerializedName("disk_partition")
    private String diskPartition;

    //===========================================
    // Constructor
    //===========================================
    public StorageDomainMemberConfig() {} // blank constructor to allow for a copy constructor
    
    public StorageDomainMemberConfig(StorageDomainMemberConfig member) {
        this.setPartition(member.getPartition());
        this.setTapePartition(member.getTapePartition());
        this.setDiskPartition(member.getDiskPartition());
        this.setPoolPartitionId(member.getPoolPartitionId());
        this.setTapePartitionId(member.getTapePartitionId());
        this.setStorageDomainId(member.getStorageDomainId());
        this.setWritePreference(member.getWritePreference());
        this.setTapeType(member.getTapeType());
        this.setState(member.getState());
        this.setAutoCompactionThreshold(member.getAutoCompactionThreshold());
        this.setCreatedAt(member.getCreatedAt());
        this.setUpdatedAt(member.getUpdatedAt());
        this.setId(member.getId());
    }

    //===========================================
    // Getters
    //===========================================
    public String getPartition() { return partition; } // synonymous with tapePartition.
    public String getTapePartition() { return tapePartition; }
    public String getDiskPartition() { return diskPartition; }
    //===========================================
    // Getters
    //===========================================
    public void setPartition(String partition) { this.partition = partition; }
    public void setTapePartition(String partition) { this.tapePartition = partition; }
    public void setDiskPartition(String partition) { this.diskPartition = partition; }
}
