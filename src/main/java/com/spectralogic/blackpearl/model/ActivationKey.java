//===================================================================
// ActivationKey.java
//      Description:
//          This model holds the information related to BlackPearl
//          activation keys.
//
// Created by Sean Snyder
// Copyright Spectra Logic
//===================================================================

package com.spectralogic.blackpearl.nacre.model;

import com.google.gson.annotations.SerializedName;

public class ActivationKey {
    private Integer id;
    private String action;
    private Boolean current;
    @SerializedName("expires_at")
    private String expiresAt;
    @SerializedName("key_type")
    private String keyType;
    @SerializedName("raw_key")
    private String rawKey;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("updated_at")
    private String updatedAt;
    @SerializedName("key_data")
    private Integer keyData;
    @SerializedName("decoded_key_data")
    private Integer decodedKeyData;
    @SerializedName("decoded_key_data_str")
    private String decodedKeyDataStr;

    //===========================================
    // Getters
    //===========================================
    public Integer getId() { return id; }
    public String getAction() { return action; }
    public Boolean isCurrent() { return current; }
    public String getExpiresAt() { return expiresAt; }
    public String getKeyType() { return keyType; }
    public String getRawKey() { return rawKey; }
    public String getCreatedAt() { return createdAt; }
    public String getUpdatedAt() { return updatedAt; }
    public Integer getKeyData() { return keyData; }
    public Integer getDecodedKeyData() { return decodedKeyData; }
    public String getDecodedKeyDataStr() { return decodedKeyDataStr; }

    //===========================================
    // Setters
    //===========================================
    public void setId(Integer id) { this.id = id; }
    public void setAction(String action) { this.action = action; }
    public void setCurrent(Boolean current) { this.current = current; }
    public void setExpiresAt(String expiresAt) { this.expiresAt = expiresAt; }
    public void setKeyType(String keyType) { this.keyType = keyType; }
    public void setRawKey(String rawKey) { this.rawKey = rawKey; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
    public void setUpdatedAt(String updatedAt) { this.updatedAt = updatedAt; }
    public void setKeyData(Integer keyData) { this.keyData = keyData; }
    public void setDecodedKeyData(Integer decodedKeyData) { this.decodedKeyData = decodedKeyData; }
    public void setDecodedKeyDataStr(String decodedKeyDataStr) { this.decodedKeyDataStr = decodedKeyDataStr; }
}

