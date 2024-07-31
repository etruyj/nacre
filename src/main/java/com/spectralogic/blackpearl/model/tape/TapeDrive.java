//===================================================================
// TapeDrive.java
//      Description:
//          A model class for a tape drive.
// 
// Created by Sean Snyder
// Copyright Spectra Logic Corporation 2024
//===================================================================

package com.spectralogic.vail.configurator.model.blackpearl.tape;

import com.google.gson.annotations.SerializedName;
import java.time.ZonedDateTime;

public class TapeDrive {
    private String quiesced;
    @SerializedName("cleaning_required")
    private boolean cleaning_required;
    @SerializedName("error_message")
    private String error_message;
    @SerializedName("serial_number")
    private String serial_number;
    private String state;
    @SerializedName("tape_bar_code")
    private String tape_bar_code;
    @SerializedName("partition_id")
    private String partition_id;
    private String type;
    @SerializedName("reserved_task_type")
    private String reserved_task_type;
    @SerializedName("minimum_task_priority")
    private Integer minimum_task_priority;
    @SerializedName("created_at")
    private ZonedDateTime created_at;
    @SerializedName("updated_at")
    private ZonedDateTime updated_at;
    private String id;
    private String status;
    
    //===========================================
    // Gettors
    //===========================================
    public String getQuiesced() { return quiesced; }
    public boolean isCleaningRequired() { return cleaning_required; }
    public String getErrorMessage() { return error_message; }
    public String getSerialNumber() { return serial_number; }
    public String getState() { return state; }
    public String getTapeBarCode() { return tape_bar_code; }
    public String getPartitionId() { return partition_id; }
    public String getType() { return type; }
    public String getReservedTaskType() { return reserved_task_type; }
    public Integer getMinimumTaskPriority() { return minimum_task_priority; }
    public ZonedDateTime getCreatedAt() { return created_at; }
    public ZonedDateTime getUpdatedAt() { return updated_at; }
    public String getId() { return id; }
    public String getStatus() { return status; }
    
    //===========================================
    // Settors
    //===========================================
    public void setQuiesced(String quiesced) { this.quiesced = quiesced; }
    public void setCleaningRequired(boolean cleaning_required) { this.cleaning_required = cleaning_required; }
    public void setErrorMessage(String error_message) { this.error_message = error_message; }
    public void setSerialNumber(String serial_number) { this.serial_number = serial_number; }
    public void setState(String state) { this.state = state; }
    public void setTapeBarCode(String tape_bar_code) { this.tape_bar_code = tape_bar_code; }
    public void setPartitionId(String partition_id) { this.partition_id = partition_id; }
    public void setType(String type) { this.type = type; }
    public void setReservedTaskType(String reserved_task_type) { this.reserved_task_type = reserved_task_type; }
    public void setMinimumTaskPriority(Integer minimum_task_priority) { this.minimum_task_priority = minimum_task_priority; }
    public void setCreatedAt(ZonedDateTime created_at) { this.created_at = created_at; }
    public void setUpdatedAt(ZonedDateTime updated_at) { this.updated_at = updated_at; }
    public void setId(String id) { this.id = id; }
    public void setStatus(String status) { this.status = status; }

}
