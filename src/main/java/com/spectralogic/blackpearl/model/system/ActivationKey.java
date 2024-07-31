//===================================================================
// ActivationKey.java
//      Description:
//          A model for the BlackPearl's activation key
// 
// Created by Sean Snyder
// Copyright Spectra Logic Corporation 2024
//===================================================================

package com.spectralogic.vail.configurator.model.blackpearl.system;

import com.google.gson.annotations.SerializedName;
import java.time.ZonedDateTime;

public class ActivationKey {
    private int id;
    private String action;
    private boolean current;
    @SerializedName("expires_at")
    private ZonedDateTime expires_at;
    @SerializedName("key_type")
    private String key_type;
    @SerializedName("raw_key")
    private String raw_key;
    @SerializedName("created_at")
    private ZonedDateTime created_at;
    @SerializedName("updated_at")
    private ZonedDateTime updated_at;
    @SerializedName("key_data")
    private int key_data;
    @SerializedName("decoded_key_data")
    private int decoded_key_data;
    @SerializedName("decoded_key_data_str")
    private String decoded_key_data_str;

    //===========================================
    // Gettors
    //===========================================
    public int getId() { return id; }
    public String getAction() { return action; }
    public boolean isCurrent() { return current; }
    public ZonedDateTime getExpiresAt() { return expires_at; }
    public String getKeyType() { return key_type; }
    public String getRawKey() { return raw_key; }
    public ZonedDateTime getCreatedAt() { return created_at; }
    public ZonedDateTime getUpdatedAt() { return updated_at; }
    public int getKeyData() { return key_data; }
    public int getDecodedKeyData() { return decoded_key_data; }
    public String getDecodedKeyDataStr() { return decoded_key_data_str; }
    
    //===========================================
    // Settors
    //===========================================
    public void setId(int id) { this.id = id; }
    public void setAction(String action) { this.action = action; }
    public void setCurrent(boolean current) { this.current = current; }
    public void setExpiresAt(ZonedDateTime expires_at) { this.expires_at = expires_at; }
    public void setKeyType(String key_type) { this.key_type = key_type; }
    public void setRawKey(String raw_key) { this.raw_key = raw_key; }
    public void setCreatedAt(ZonedDateTime created_at) { this.created_at = created_at; }
    public void setUpdatedAt(ZonedDateTime updated_at) { this.updated_at = updated_at; }
    public void setKeyData(int key_data) { this.key_data = key_data; }
    public void setDecodedKeyData(int decoded_key_data) { this.decoded_key_data = decoded_key_data; }
    public void setDecodedKeyDataStr(String decoded_key_data_str) { this.decoded_key_data_str = decoded_key_data_str; }

}
