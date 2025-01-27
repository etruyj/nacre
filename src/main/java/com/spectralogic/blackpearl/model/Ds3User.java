// ======================================================================
// Ds3User.java
//      Description:
//          This class represents the structure of a user JSON 
//          object with various attributes such as id, name, 
//          username, and more.
//          Created by Sean Snyder. Copyright belongs to 
//          Spectra Logic Corporation.
// 
// Created by Sean Snyder
// Copyright Spectra Logic 2024
// ======================================================================

package com.spectralogic.blackpearl.nacre.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Ds3User {
    private int id;
    private String name;
    private String username;
    @SerializedName("session_timeout")
    private String sessionTimeout;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("updated_at")
    private String updatedAt;
    @SerializedName("ds3_user_id")
    private String ds3UserId;
    @SerializedName("mfa_configured")
    private Boolean mfaConfigured;
    private List<Integer> permissions;
    @SerializedName("mfa_code")
    private String mfaCode;
    @SerializedName("mfa_secret")
    private String mfaSecret;
    @SerializedName("remote_support_enabled")
    public Boolean remoteSupportEnabled;
    @SerializedName("default_data_policy_id")
    private String defaultDataPolicyId;
    @SerializedName("global_bucket_acl_permissions")
    private List<Object> globalBucketAclPermissions;
    @SerializedName("global_data_policy_acl_enabled")
    private Boolean globalDataPolicyAclEnabled;
    @SerializedName("max_buckets")
    private int maxBuckets;

    //===========================================
    // Getters
    //===========================================
    public int getId() { return id; }
    public String getName() { return name; }
    public String getUsername() { return username; }
    public String getSessionTimeout() { return sessionTimeout; }
    public String getCreatedAt() { return createdAt; }
    public String getUpdatedAt() { return updatedAt; }
    public String getDs3UserId() { return ds3UserId; }
    public Boolean getMfaConfigured() { return mfaConfigured; }
    public List<Integer> getPermissions() { return permissions; }
    public String getMfaCode() { return mfaCode; }
    public String getMfaSecret() { return mfaSecret; }
    public Boolean isRemoteSupportEnabled() { return remoteSupportEnabled; }
    public String getDefaultDataPolicyId() { return defaultDataPolicyId; }
    public List<Object> getGlobalBucketAclPermissions() { return globalBucketAclPermissions; }
    public Boolean getGlobalDataPolicyAclEnabled() { return globalDataPolicyAclEnabled; }
    public int getMaxBuckets() { return maxBuckets; }

    //===========================================
    // Setters
    //===========================================
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setUsername(String username) { this.username = username; }
    public void setSessionTimeout(String sessionTimeout) { this.sessionTimeout = sessionTimeout; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
    public void setUpdatedAt(String updatedAt) { this.updatedAt = updatedAt; }
    public void setDs3UserId(String ds3UserId) { this.ds3UserId = ds3UserId; }
    public void setMfaConfigured(Boolean mfaConfigured) { this.mfaConfigured = mfaConfigured; }
    public void setPermissions(List<Integer> permissions) { this.permissions = permissions; }
    public void setMfaCode(String mfaCode) { this.mfaCode = mfaCode; }
    public void setMfaSecret(String mfaSecret) { this.mfaSecret = mfaSecret; }
    public void setRemoteSupportEnabled(Boolean remoteSupportEnabled) { this.remoteSupportEnabled = remoteSupportEnabled; }
    public void setDefaultDataPolicyId(String defaultDataPolicyId) { this.defaultDataPolicyId = defaultDataPolicyId; }
    public void setGlobalBucketAclPermissions(List<Object> globalBucketAclPermissions) { this.globalBucketAclPermissions = globalBucketAclPermissions; }
    public void setGlobalDataPolicyAclEnabled(Boolean globalDataPolicyAclEnabled) { this.globalDataPolicyAclEnabled = globalDataPolicyAclEnabled; }
    public void setMaxBuckets(int maxBuckets) { this.maxBuckets = maxBuckets; }
}

