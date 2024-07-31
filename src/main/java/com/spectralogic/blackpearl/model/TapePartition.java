//===================================================================
// TapePartition.java
//      Description:
//          A model class for the tape partition.
// 
// Created by Sean Snyder
// Copyright Spectra Logic Corporation 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.model;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class TapePartition {
    @SerializedName("case_id")
    private String case_id;
    private String name;
    @SerializedName("error_message")
    private String error_message;
    @SerializedName("serial_number")
    private String serial_number;
    private String state;
    @SerializedName("import_export_configuration")
    private String import_export_configuration;
    @SerializedName("drive_type")
    private String drive_type;
    @SerializedName("tape_types")
    private ArrayList<String> tape_types;
    private String quiesced;
    @SerializedName("minimum_read_reserved_drives")
    private int minimum_read_reserved_drives;
    @SerializedName("minimum_write_reserved_drives")
    private int minimum_write_reserved_drives;
    @SerializedName("auto_compaction_enabled")
    private boolean auto_compaction_enabled;
    @SerializedName("available_storage_capacity")
    private long available_storage_capacity;
    @SerializedName("used_storage_capacity")
    private long used_storage_capacity;
    @SerializedName("tape_count")
    private int tape_count;
    @SerializedName("tape_state_summaries")
    private ArrayList<TapeStateSummary> tape_state_summaries;
    @SerializedName("tape_type_summaries")
    private ArrayList<TapeTypeSummary> tape_type_summaries;
    @SerializedName("created_at")
    private String created_at;
    @SerializedName("updated_at")
    private String updated_at;
    private String id;
    
    //===========================================
    // Gettors
    //===========================================
    public String getCaseId() { return case_id; }
    public String getName() { return name; }
    public String getErrorMessage() { return error_message; }
    public String getSerialNumber() { return serial_number; }
    public String getState() { return state; }
    public String getImportExportConfiguration() { return import_export_configuration; }
    public String getDriveType() { return drive_type; }
    public ArrayList<String> getTapeTypes() { return tape_types; }
    public String getQuiesced() { return quiesced; }
    public int getMinimumReadReservedDrives() { return minimum_read_reserved_drives; }
    public int getMinimumWriteReservedDrives() { return minimum_write_reserved_drives; }
    public boolean isAutoCompactionEnabled() { return auto_compaction_enabled; }
    public long getAvailableStorageCapacity() { return available_storage_capacity; }
    public long getUsedStorageCapacity() { return used_storage_capacity; }
    public int getTapeCount() { return tape_count; }
    public ArrayList<TapeStateSummary> getTapeStateSummaries() { return tape_state_summaries; }
    public ArrayList<TapeTypeSummary> getTapeTypeSummaries() { return tape_type_summaries; }
    public String getCreatedAt() { return created_at; }
    public String getUpdatedAt() { return updated_at; }
    public String getId() { return id; }
    
    //===========================================
    // Settors
    //===========================================
    public void setCaseId(String case_id) { this.case_id = case_id; }
    public void setName(String name) { this.name = name; }
    public void setErrorMessage(String error_message) { this.error_message = error_message; }
    public void setSerialNumber(String serial_number) { this.serial_number = serial_number; }
    public void setState(String state) { this.state = state; }
    public void setImportExportConfiguration(String import_export_configuration) { this.import_export_configuration = import_export_configuration; }
    public void setDriveType(String drive_type) { this.drive_type = drive_type; }
    public void setTapeTypes(ArrayList<String> tape_types) { this.tape_types = tape_types; }
    public void setQuiesced(String quiesced) { this.quiesced = quiesced; }
    public void setMinimumReadReservedDrives(int minimum_read_reserved_drives) { this.minimum_read_reserved_drives = minimum_read_reserved_drives; }
    public void setMinimumWriteReservedDrives(int minimum_write_reserved_drives) { this.minimum_write_reserved_drives = minimum_write_reserved_drives; }
    public void setAutoCompactionEnabled(boolean auto_compaction_enabled) { this.auto_compaction_enabled = auto_compaction_enabled; }
    public void setAvailableStorageCapacity(long available_storage_capacity) { this.available_storage_capacity = available_storage_capacity; }
    public void setUsedStorageCapacity(long used_storage_capacity) { this.used_storage_capacity = used_storage_capacity; }
    public void setTapeCount(int tape_count) { this.tape_count = tape_count; }
    public void setTapeStateSummaries(ArrayList<TapeStateSummary> tape_state_summaries) { this.tape_state_summaries = tape_state_summaries; }
    public void setTapeTypeSummaries(ArrayList<TapeTypeSummary> tape_type_summaries) { this.tape_type_summaries = tape_type_summaries; }
    public void setCreatedAt(String created_at) { this.created_at = created_at; }
    public void setUpdatedAt(String updated_at) { this.updated_at = updated_at; }
    public void setId(String id) { this.id = id; }
}
