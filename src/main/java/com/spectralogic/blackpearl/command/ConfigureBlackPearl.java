//===================================================================
// ConfigureBlackPearl.java
//      Description:
//          This class takes a configuration object and uploads it
//          to the BlackPearl.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.command;

import com.spectralogic.blackpearl.nacre.api.BpConnector;
import com.spectralogic.blackpearl.nacre.model.ActivationKey;
import com.spectralogic.blackpearl.nacre.model.ActivationKeyConfig;
import com.spectralogic.blackpearl.nacre.model.BpConfig;
import com.spectralogic.blackpearl.nacre.model.Ds3Bucket;
import com.spectralogic.blackpearl.nacre.model.Ds3BucketConfig;
import com.spectralogic.blackpearl.nacre.model.Ds3User;
import com.spectralogic.blackpearl.nacre.model.DataPersistenceRule;
import com.spectralogic.blackpearl.nacre.model.DataPersistenceRuleConfig;
import com.spectralogic.blackpearl.nacre.model.DataPolicy;
import com.spectralogic.blackpearl.nacre.model.DataPolicyConfig;
import com.spectralogic.blackpearl.nacre.model.DefaultsConfig;
import com.spectralogic.blackpearl.nacre.model.DiskDrive;
import com.spectralogic.blackpearl.nacre.model.DiskPartition;
import com.spectralogic.blackpearl.nacre.model.NetworkInterface;
import com.spectralogic.blackpearl.nacre.model.NetworkInterfaceConfig;
import com.spectralogic.blackpearl.nacre.model.NetworkInterfaceSend;
import com.spectralogic.blackpearl.nacre.model.NtpSettings;
import com.spectralogic.blackpearl.nacre.model.Pool;
import com.spectralogic.blackpearl.nacre.model.PoolConfig;
import com.spectralogic.blackpearl.nacre.model.ScheduleDatabaseBackup;
import com.spectralogic.blackpearl.nacre.model.ScheduleDatabaseConfig;
import com.spectralogic.blackpearl.nacre.model.ScheduleLogConfig;
import com.spectralogic.blackpearl.nacre.model.ScheduleLogSet;
import com.spectralogic.blackpearl.nacre.model.Service;
import com.spectralogic.blackpearl.nacre.model.ServiceCifs;
import com.spectralogic.blackpearl.nacre.model.ServiceNfs;
import com.spectralogic.blackpearl.nacre.model.ServiceS3;
import com.spectralogic.blackpearl.nacre.model.Share;
import com.spectralogic.blackpearl.nacre.model.ShareConfig;
import com.spectralogic.blackpearl.nacre.model.SmtpSettings;
import com.spectralogic.blackpearl.nacre.model.StorageDomain;
import com.spectralogic.blackpearl.nacre.model.StorageDomainConfig;
import com.spectralogic.blackpearl.nacre.model.StorageDomainMember;
import com.spectralogic.blackpearl.nacre.model.StorageDomainMemberConfig;
import com.spectralogic.blackpearl.nacre.model.Stripe;
import com.spectralogic.blackpearl.nacre.model.TapePartition;
import com.spectralogic.blackpearl.nacre.model.TapeTypeSummary;
import com.spectralogic.blackpearl.nacre.model.Volume;
import com.spectralogic.blackpearl.nacre.model.VolumeConfig;
import com.spectralogic.blackpearl.nacre.util.convert.StorageConversion;
import com.spectralogic.blackpearl.nacre.util.io.LoadFile;
import com.spectralogic.blackpearl.nacre.util.json.StringOrArrayDeserializer;
import com.spectralogic.blackpearl.nacre.util.map.MapDataPolicies;
import com.spectralogic.blackpearl.nacre.util.map.MapDiskPartitions;
import com.spectralogic.blackpearl.nacre.util.map.MapNasPools;
import com.spectralogic.blackpearl.nacre.util.map.MapServices;
import com.spectralogic.blackpearl.nacre.util.map.MapStorageDomains;
import com.spectralogic.blackpearl.nacre.util.map.MapTapePartitions;
import com.spectralogic.blackpearl.nacre.util.map.MapUsers;
import com.spectralogic.blackpearl.nacre.util.map.MapVolumes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigureBlackPearl {
    private static final Logger log = LoggerFactory.getLogger(ConfigureBlackPearl.class);

    public static ArrayList<String> fromFile(String file_name, DefaultsConfig defaults, BpConnector pearl) {
        log.info("Loading BlackPearl configuration from file: " + file_name);
        ArrayList<String> results = null;
        
        try {
            String file_string = LoadFile.toString(file_name);

            Gson gson = new GsonBuilder()
                                .registerTypeAdapter(List.class, new StringOrArrayDeserializer())
                                .create();
            BpConfig config = gson.fromJson(file_string, BpConfig.class);
            
            results = fromObject(config, defaults, pearl);
        } catch(Exception e) {
            log.error(e.getMessage());
            log.error("Failed to load file");
        }

        return results;
    }

    public static ArrayList<String> fromObject(BpConfig config, DefaultsConfig defaults, BpConnector pearl) {
        log.info("Configuring BlackPearl...");

        ArrayList<String> results = new ArrayList<String>();
        int success = 0;
        String message = "";

        //=======================================
        // Add Activation Keys
        //=======================================
        // Copy to a new list to allow for empty sets in the configuration.
        ArrayList<ActivationKeyConfig> new_keys = config.getActivationKeys() != null ? config.getActivationKeys() : new ArrayList<ActivationKeyConfig>();
        
        if(new_keys.size() > 0) {
            System.out.println("Adding activation keys to BlackPearl....");
            success = addActivationKeys(new_keys, defaults, pearl);
            message = "Successfully added " + success + "/" + new_keys.size()  + " activation keys";
            System.out.println(message);
            results.add(message);
        }

        //=======================================
        // Create Pools
        //=======================================
        // Copy to a new list to allow for empty sets in the configuration.
        ArrayList<PoolConfig> new_pools = config.getNasPools() != null ? config.getNasPools() : new ArrayList<PoolConfig>();
        
        if(new_pools.size() > 0) {
            System.out.println("Creating NAS pools...");
            success = createPools(new_pools, defaults, pearl);
            message = "Successfully created " + success + "/" + new_pools.size()  + " pools";
            System.out.println(message);
            results.add(message);
        }

        //=======================================
        // Create Volumes
        //=======================================
        // Copy to a new list to allow for empty sets in the configuration.
        ArrayList<VolumeConfig> new_volumes = config.getVolumes() != null ? config.getVolumes() : new ArrayList<VolumeConfig>();
        
        if(new_volumes.size() > 0) {
            System.out.println("Creating volumes...");
            success = createVolumes(new_volumes, defaults, pearl);
            message = "Successfully created " + success + "/" + new_volumes.size()  + " volumes";
            System.out.println(message);
            results.add(message);
        }

        //=======================================
        // Create Shares
        //=======================================
        // Copy to a new list to allow for empty sets in the configuration.
        // ==== CIFS ====
        ArrayList<ShareConfig> new_shares = config.getCifsShares() != null ? config.getCifsShares() : new ArrayList<ShareConfig>();
        
        if(new_shares.size() > 0) {
            System.out.println("Configuring CIFS shares...");
            success = createShares(new_shares, "CIFS", defaults, pearl);
            message = "Successfully created " + success + "/" + new_shares.size()  + " CIFS shares";
            System.out.println(message);
            results.add(message);
        }

        // === NFS ====
        new_shares = config.getNfsShares() != null ? config.getNfsShares() : new ArrayList<ShareConfig>();
        
        if(new_shares.size() > 0) {
            System.out.println("Configuring NFS shares...");
            success = createShares(new_shares, "NFS", defaults, pearl);
            message = "Successfully created " + success + "/" + new_shares.size()  + " NFS shares";
            System.out.println(message);
            results.add(message);
        }

        //=======================================
        // Create Ds3 Disk Partitions
        //=======================================
        // Copy to a new list to allow for empty sets in the configuration.
        ArrayList<PoolConfig> new_ds3_pars = config.getDiskPartitions() != null ? config.getDiskPartitions() : new ArrayList<PoolConfig>();
        if(new_ds3_pars.size() > 0) {
            System.out.println("Configuring DS3 disk partitions...");
            success = createDs3DiskPartitions(new_ds3_pars, defaults, pearl);
            message = "Successfully created " + success + "/" + new_ds3_pars.size()  + " disk partitions";
            results.add(message);
        }

        //=======================================
        // Create Storage Domains
        //=======================================
        // Copy to a new list to allow for empty sets in the configuration.
        ArrayList<StorageDomainConfig> new_domains = config.getStorageDomains() != null ? config.getStorageDomains() : new ArrayList<StorageDomainConfig>();
        
        if(new_domains.size() > 0) {
            System.out.println("Configuring storage domains...");
            success = createStorageDomains(new_domains, defaults, pearl);
            message = "Successfully created " + success + "/" + new_domains.size()  + " storage domains";
            results.add(message);
        }

        //=======================================
        // Create Data Policies
        //=======================================
        // Copy to a new list to allow for empty sets in the configuration
        ArrayList<DataPolicyConfig> new_policies = config.getDataPolicies() != null ? config.getDataPolicies() : new ArrayList<DataPolicyConfig>();
        
        if(new_policies.size() > 0) {
            System.out.println("Configuring data policies...");
            success = createDataPolicies(new_policies, defaults, pearl);
            message = "Successfully created " + success + "/" + new_policies.size() + " data policies";
            results.add(message);
        }

        //=======================================
        // Create Buckets
        //=======================================
        // Copy to a new list to allow for empty sets in the configuration
        ArrayList<Ds3BucketConfig> new_buckets = config.getBuckets() != null ? config.getBuckets() : new ArrayList<Ds3BucketConfig>();
        
        if(new_buckets.size() > 0) {
            System.out.println("Configuring DS3 buckets...");
            success = createBuckets(new_buckets, defaults, pearl);
            message = "Successfully created " + success + "/" + new_buckets.size() + " buckets";
            results.add(message);
        }

        //=======================================
        // Configure Database Backups
        //=======================================
        // Configure the database backup and set the backup schedule.
        ScheduleDatabaseConfig backup_schedule = config.getDatabaseBackup() != null ? config.getDatabaseBackup() : new ScheduleDatabaseConfig();

        if(configureDatabaseBackups(backup_schedule, defaults, pearl)) {
            message = "Successfully configured database backup schedule.";
        } else {
            message = "Failed to configure database backup scheduel.";
        }

        results.add(message);

        //=======================================
        // Configure Log Set Schedule
        //=======================================
        ScheduleLogConfig log_schedule = config.getLogSchedule() != null ? config.getLogSchedule() : defaults.getLogSchedule();

        if(log_schedule != null) {
            System.out.println("Configuring log set schedule...");
            if(ScheduleLogs.fromConfig(log_schedule, pearl) != null) {
                message = "Successfully configured log set schedule.";
            } else {
                message = "Failed to configure log set schedule.";
            }
        
            results.add(message);
        }


        //=======================================
        // Set Hostname
        //=======================================
        if(config.getHostname() != null) {
            System.out.println("Configuring hostname...");
            if(SetHostname.fromString(config.getHostname(), pearl)) {
                message = "Successfully set hostname [" + config.getHostname() + "]";
            } else {
                message = "Failed to set hostname [" + config.getHostname() + "].";
            }
        
            results.add(message);
        }


        //=======================================
        // Set DNS Servers
        //=======================================
        List<String> dns_servers = config.getDnsServers() != null ? config.getDnsServers() : new ArrayList<String>();

        if(dns_servers.size() > 0 || defaults.getDnsServers() != null) {
            System.out.println("Configuing DNS servers...");
            if(setDnsServers(dns_servers, defaults.getDnsServers(), pearl)) {
                message = "Successfully configured DNS servers.";
            } else {
                message = "Failed to configure DNS servers.";
            }
        
            results.add(message);
        }


        //=======================================
        // Set NTP Servers
        //=======================================
        List<String> ntp_servers = config.getNtpServers() != null ? config.getNtpServers() : new ArrayList<String>();

        if(ntp_servers.size() > 0 || defaults.getNtpServers() != null) {
            System.out.println("Configuring NTP servers...");
            if(setNtpServers(ntp_servers, defaults.getNtpServers(), pearl)) {
                message = "Successfully configured NTP servers.";
            } else {
                message = "Failed to configure NTP servers.";
            }
        
            results.add(message);
        }
        

        //=======================================
        // Set SMTP Servers
        //=======================================
        SmtpSettings smtp_settings = config.getSmtpSettings() != null ? config.getSmtpSettings() : null;

        if(smtp_settings != null || defaults.getSmtpSettings() != null) {
            System.out.println("Configuring SMTP server...");
            if(setSmtpSettings(smtp_settings, defaults.getSmtpSettings(), pearl)) {
                message = "Successfully configured SMTP settings.";
            } else {
                message = "Failed to configure SMTP settings.";
            }
        
            results.add(message);
        }


        //=======================================
        // Configure Network Interfaces
        //=======================================
        System.out.println("Configuring network interfaces.");
        String network_message = ConfigureNetworkInterface.interfacesFromMap(config.getNetworkInterfaces(), pearl);

        results.add(network_message);

        System.out.println("BlackPearl configuration is complete.");

        return results;
    }

    //===========================================
    // Private Functions
    //===========================================
    public static int addActivationKeys(ArrayList<ActivationKeyConfig> key_list, DefaultsConfig defaults, BpConnector pearl) {
        log.info("Configuration contains (" + key_list.size() + ") activation keys.");

        int success = 0;
        ActivationKey new_key = null;

        //==== Sort the Activation Keys ====
        // Some keys need to be entered in a
        // specific order. Create a sorted list of the keys.
        ArrayList<ActivationKeyConfig> unsorted_list = new ArrayList<ActivationKeyConfig>();
        ArrayList<ActivationKeyConfig> sorted_list = new ArrayList<ActivationKeyConfig>();
        boolean key_added = false;
        int iterator = 0;

        for(ActivationKeyConfig key : key_list) {
            if(defaults.getServer().getKeySettings().get(key.getName()) != null 
                    && defaults.getServer().getKeySettings().get(key.getName()).getLoadOrder() != null) {
                // Add the key to the list if no other keys exist.
                key_added = false; // reset the flag
                key.setLoadOrder(defaults.getServer().getKeySettings().get(key.getName()).getLoadOrder());
                log.debug("Key " + key.getName() + " [" + key.getKey() + "] has a specific load order. Queuing at end of list.");

                if(sorted_list.size() == 0) {
                    log.debug("Starting new list for specific keys.");
                    sorted_list.add(key);
                } else {
                    // reset the iterator and search the list for the place to insert
                    // the key.
                    iterator = sorted_list.size()-1;
                    
                    while(!key_added && iterator >= 0) {
                        if(key.getLoadOrder() > sorted_list.get(iterator).getLoadOrder()) {
                            log.debug("Adding key at position " + (iterator + 1) + " of the load order.");
                            sorted_list.add(iterator+1, key);
                            key_added = true;
                        }

                        iterator--;
                    }
                }
            } else {
                unsorted_list.add(key);
            }
        }

        // Add the sorted list to the end of the unsorted list.
        // this allows the generic keys to be added first.
        log.debug("Adding (" + sorted_list.size() + ") ordered keys to the end of the list.");
        unsorted_list.addAll(sorted_list);
        log.debug("There are (" + unsorted_list.size() + ") activation keys to load onto the BlackPearl.");
        

        //==== Add they keys to the BlackPearl ===
        for(ActivationKeyConfig key : unsorted_list) {
            new_key = AddActivationKey.fromObject(key, defaults, pearl);

            if(new_key != null) {
                success++;
            }
        }
        
        log.info("Successfully added " + success + " activation keys.");
        log.info("Failed to add " + (key_list.size() - success) + "/" + key_list.size() + " activation keys.");

        waitForDs3BackendToComeOnline(defaults.getServer().getPingInterval(), pearl);

        return success;
    }
    
    public static int addDataPersistenceRules(DataPolicyConfig policy, DefaultsConfig defaults, HashMap<String, String> sd_map, BpConnector pearl) throws Exception {
        log.info("Adding (" + policy.getDataPersistenceRules().size() + ") data persistence rules to data policy " + policy.getName());
        int success = 0;

        DataPersistenceRule new_rule = null;

        for(DataPersistenceRuleConfig rule : policy.getDataPersistenceRules()) {
            // Load default values
            new_rule = new DataPersistenceRule(defaults.getDataPersistenceRule());

            // Copy configs over to the rule.
            new_rule.setDataPolicyId(policy.getId());
            
            if(sd_map.get(rule.getName()) != null) {
                new_rule.setStorageDomainId(sd_map.get(rule.getName()));
            } else {
                throw new Exception("Could not find storage domain [" + rule.getName() + "]");
            }

            if(rule.getIsolationLevel() != null) {
                new_rule.setIsolationLevel(rule.getIsolationLevel());
            }

            if(rule.getType() != null) {
                new_rule.setType(rule.getType());
            }

            if(rule.getMinimumDaysToRetain() != null) {
                new_rule.setMinimumDaysToRetain(rule.getMinimumDaysToRetain());
            }

            new_rule = AddDataPersistenceRule.fromObject(new_rule, pearl);

            if(new_rule != null) {
                success++;
            }
        }

        log.info("Successfully add (" + success + ") data persistence rules.");
        return success;
    }
    
    public static int addStorageDomainMembers(StorageDomainConfig domain, DefaultsConfig defaults, HashMap<String, TapePartition> par_map, HashMap<String, DiskPartition> disk_map, BpConnector pearl) throws Exception {
        // Add storage domain members to the storage domain.
        // This function allows for variable input. Users can either specify specific tape
        // partitions and/or tape types or include everything with an all command.
        // If partition=all, all tape partitions will be added as storage domain members.
        // If tape_type=all or is omitted, all tape types in the partitions will be added
        // as storage domain members.
        // Partition can be specified and tape_type can be all. This allows for flexibility.
        // Partition can be all and tape_type can be specified (i.e. lto5) to add all 
        // LTO-5 tapes to the storage domain.
        log.info("Adding (" + domain.getMembers().size() + ") storage domain members to storage domain " + domain.getName());
        int success = 0;
        ArrayList<StorageDomainMember> member_list = new ArrayList<StorageDomainMember>();
        StorageDomainMember new_member = null;
        StorageDomainMember nm_iter = null;
        // build a list of stroage domain members to add.
        for(String key : disk_map.keySet()) { System.out.println("STring: " + key); }
        
        for(StorageDomainMemberConfig member : domain.getMembers()) {
            log.info("Member tape partition: [" + member.getTapePartition() + "]  Member disk Partition: [" + member.getDiskPartition() + "]");

            new_member = new StorageDomainMember(defaults.getStorageDomainMember());

            if(member.getWritePreference() != null) {
                new_member.setWritePreference(member.getWritePreference());
            }

            if(member.getAutoCompactionThreshold() != null) {
                log.info("Setting auto compaction threshold.");
                new_member.setAutoCompactionThreshold(member.getAutoCompactionThreshold());
            }
            new_member.setStorageDomainId(domain.getId());

            // Find the storage domain members
            // Check for disk partition members independently from the tape partition members
            // as the tape partition and partition field point to the same value.
            if(member.getDiskPartition() != null) {
                // Set disk partition values
                // auto_compaction_threshold and tape_type must by ""
                new_member.setAutoCompactionThreshold(null);
                new_member.setTapeType("");

                if(member.getDiskPartition().equalsIgnoreCase("all")) {
                    if(disk_map.size() > 0) {
                        log.info("Adding all disk partitions to storage domain.");

                        for(String key : disk_map.keySet()) {
                            new_member = new StorageDomainMember(new_member); // Create a new copy due to Java referencing behavior.
                            new_member.setPoolPartitionId(disk_map.get(key).getId());

                            member_list.add(new_member);
                        }
                    } else {
                        throw new Exception("Unable to add disk partition to storage domain. No disk partitions exists.");
                    }
                } else {
                    if(disk_map.get(member.getDiskPartition()) != null) {
                        log.info("Adding " + member.getDiskPartition() + " as storage domain member.");
                        new_member.setPoolPartitionId(disk_map.get(member.getDiskPartition()).getId());

                        member_list.add(new_member);
                    } else {
                        throw new Exception("Unable to add disk partition to storage domain. Disk partition [" + member.getDiskPartition() + "] does not exist.");
                    }
                }
            } else if(member.getTapePartition() != null) {
                if(member.getTapePartition().equalsIgnoreCase("all")) {
                    if(par_map.size() > 0) {
                        log.info("Adding all tape partitions to storage domain.");

                        for(String key : par_map.keySet()) {
                            new_member = new StorageDomainMember(new_member); // Create a copy due to Java referencing behavior
                            new_member.setTapePartitionId(par_map.get(key).getId());
                            
                            // Determine tape types
                            // If tape type is all or omitted, all tapes should be added.
                            if(member.getTapeType() == null || member.getTapeType().equalsIgnoreCase("all")) {
                                log.info("Adding all type types to the storage domain.");

                                for(String tape_type : par_map.get(key).getTapeTypes()) {
                                    // Create another copy of the new_member on each loop iteration
                                    // due to Java's referencing behavior
                                    new_member = new StorageDomainMember(new_member);
                                    new_member.setTapeType(tape_type);

                                    member_list.add(new_member);
                                }
                            } else {
                                log.info("Adding " + member.getTapeType() + " media to the storage domain.");
                                new_member.setTapeType(member.getTapeType());

                                member_list.add(new_member);
                            }

                            member_list.add(new_member);        
                        }
                    } else {
                        throw new Exception("Unable to add tape partition to storage domain. No tape partitions exists.");
                    }
                } else {
                    if(par_map.get(member.getTapePartition()) != null) {
                        log.info("Adding tape partition [" + member.getTapePartition() + "] to the storage domain.");

                        new_member.setTapePartitionId(par_map.get(member.getTapePartition()).getId());

                        // Determine tape types
                        // If tape type is all or omitted, all tapes should be added.
                        if(member.getTapeType() == null || member.getTapeType().equalsIgnoreCase("all")) {
                            log.info("Adding all type types to the storage domain.");

                            for(String tape_type : par_map.get(member.getTapePartition()).getTapeTypes()) {
                                // Create another copy of the new_member on each loop iteration
                                // due to Java's referencing behavior
                                new_member = new StorageDomainMember(new_member);
                                new_member.setTapeType(tape_type);

                                member_list.add(new_member);
                            }
                        } else {
                            log.info("Adding " + member.getTapeType() + " media to the storage domain.");
                            new_member.setTapeType(member.getTapeType());

                            member_list.add(new_member);
                        }

                        member_list.add(new_member);        
                    } else {
                        throw new Exception("Unable to add tape partition to storage domain. Tape partition [" + member.getTapePartition() + "] does not exist.");
                    }
                }
            } 
        }    
            /* Removed by the if-else-if statements above when 
               * disk partitions support was added.
               * saving until we verify this works.
            if(member.getTapePartition().equalsIgnoreCase("all")) {
                // Add all tape partitions as storage domain members.
                log.info("Adding all tape partitions to the storage domain.");
                for(String key : par_map.keySet()) {
                    // Create duplicate new_members for each iteration of the for loop.
                    new_member = new StorageDomainMember(new_member);
                    new_member.setTapePartitionId(par_map.get(key).getId());

                    // Determine tape types.
                    // If tape type is all or omitted, consider all tapes should be added.
                    if(member.getTapeType() == null || member.getTapeType().equalsIgnoreCase("all")) {
                        log.info("Adding all tape types to the storage domain.");
                        for(String tape_type : par_map.get(key).getTapeTypes()) {
                            // Create duplicate new_members for each iteration of the for loop.
                            new_member = new StorageDomainMember(new_member);
                            new_member.setTapeType(tape_type);
                            member_list.add(new_member);
                        }
                    } else {
                        log.info("Adding " + member.getTapeType() + " media to the storage domain.");
                        new_member.setTapeType(member.getTapeType());
                        member_list.add(new_member);
                    }
                }
            } else if(par_map.get(member.getTapePartition()) == null) {
                // Tape partition does not exist.
                throw new Exception("Cannot find tape partition [" + member.getPartition() + "]");
            } else {
                // Add specific tape partition as storage domain member.
                new_member.setTapePartitionId(par_map.get(member.getPartition()).getId());
                    
                // Determine tape types.
                // If tape type is all or omitted, consider all tapes should be added.
                if(member.getTapeType() == null || member.getTapeType().equalsIgnoreCase("all")) {
                    log.info("Adding all tape types to the storage domain.");
                    for(String tape_type : par_map.get(member.getPartition()).getTapeTypes()) {
                        new_member = new StorageDomainMember(new_member);
                        new_member.setTapeType(tape_type);
                        member_list.add(new_member);
                    }
                } else {
                    log.info("Adding " + member.getTapeType() + " media to the storage domain.");
                    new_member.setTapeType(member.getTapeType());
                    member_list.add(new_member);
                }
            }
        }
        * END OF REMOVED CODE
        */

        // Add the storage domain members for the storage domain.
        for(StorageDomainMember nm : member_list) {
            new_member = AddStorageDomainMember.fromObject(nm, pearl);

            if(new_member != null) {
                success++;
            }
        }    

        log.info("Successfully added (" + success + ") storage domain members to the storage domain.");      
        return success;
    }

    public static boolean configureDatabaseBackups(ScheduleDatabaseConfig backup_schedule, DefaultsConfig defaults, BpConnector pearl) {
        log.info("Configuring database backups");

        // Configure Database Backup

        ScheduleDatabaseConfig schedule = new ScheduleDatabaseConfig(defaults.getDatabaseBackup());

        if(backup_schedule.getSchedule() != null) {
            schedule.setSchedule(backup_schedule.getSchedule());
        }

        if(backup_schedule.getTime() != null) {
            schedule.setTime(backup_schedule.getTime());
        }

        if(backup_schedule.getCopiesToKeep() != null) {
            schedule.setCopiesToKeep(backup_schedule.getCopiesToKeep());
        }

        if(backup_schedule.getDays() != null && backup_schedule.getDays().size() > 1) {
            schedule.setDays(backup_schedule.getDays());
        }

        if(backup_schedule.getDataPolicy() != null) {
            schedule.setDataPolicy(backup_schedule.getDataPolicy());
        }

        //=======================================
        // Create Database Backup Bucket
        //=======================================
        
        CreateDs3Bucket.forDatabase(schedule.getDataPolicy(), pearl);
   
        //=======================================
        // Create Database Backup Schedule
        //=======================================
        
        ScheduleDatabaseBackup new_schedule = ScheduleBackup.fromConfig(schedule, pearl);

        if(new_schedule != null) {
            log.info("Successfully configured database backups.");
            return true;
        } else {
            log.warn("Failed to configure database backups.");
            return false;
        }
    }

    public static String configureNetworkInterface(NetworkInterfaceConfig inter_conf, String type, BpConnector pearl) {
        log.info("Configuring the " + type + " interface to " + inter_conf.getAddress() + "/" + inter_conf.getPrefix());

        ArrayList<NetworkInterface> interface_list = ListNetworkInterfaces.activeByType(type, pearl);
        NetworkInterfaceSend new_interface = null;

        String message = "";

        if(interface_list.size() > 1) {
            log.info("Building aggregate interface for (" + interface_list.size() + ") interfaces.");
            ArrayList<String> lagg_ports = new ArrayList<String>();

            if(inter_conf.isAggregate()) {
                new_interface = new NetworkInterfaceSend();

                for(NetworkInterface interf : interface_list) {
                    lagg_ports.add(interf.getIfname());        
                }

                new_interface.setAddresses(inter_conf.getAddress() + "/" + inter_conf.getPrefix());
                new_interface.setDefaultGateway(inter_conf.getDefaultGateway());
                new_interface.setDhcp(false);
                new_interface.setLaggPortOptions(false);
                new_interface.setLaggPorts(lagg_ports);
                new_interface.setMtu("1500");
                new_interface.setType("lagg");
                new_interface.setUp(true);
            } else {
                log.warn("Multiple available interfaces found. Configuring the first available.");
            
                log.info("Configuring interface " + interface_list.get(0).getName() + " [" + interface_list.get(0).getId() + "]");   
                new_interface = new NetworkInterfaceSend(interface_list.get(0));
                
                new_interface.setAddresses(inter_conf.getAddress() + "/" + inter_conf.getPrefix());
                new_interface.setDefaultGateway(inter_conf.getDefaultGateway());
                new_interface.setUp(true);
            }
        } else if(interface_list.size() == 1) {
            if(inter_conf.isAggregate()) {
                log.warn("Unable to aggregage. Only one interface is available for configuration.");
            }
            
            if(!interface_list.get(0).getAddresses().get(0).getAddress().equals(inter_conf.getAddress() + "/" + inter_conf.getPrefix())) {
                log.info("Configuring interface " + interface_list.get(0).getName() + " [" + interface_list.get(0).getId() + "]");   

                new_interface = new NetworkInterfaceSend(interface_list.get(0));
                
                new_interface.setAddresses(inter_conf.getAddress() + "/" + inter_conf.getPrefix());
                new_interface.setDefaultGateway(inter_conf.getDefaultGateway());
                new_interface.setUp(true);
            } else {
                // Interface address already matches the desired IP.
                message = "Interface " + interface_list.get(0).getName() + " already has desired address " + inter_conf.getAddress();
            }
        } else {
            log.warn("No " + type + " interfaces availabe to configure.");
            message = "Failed to configure " + type + " interface. No interfaces available to configure.";
        }

        NetworkInterface ret_int = null;
        if(new_interface != null) {
            if(new_interface.getType().equals("lagg")) {
                ret_int = CreateAggregateInterface.fromObject(new_interface, pearl);
            
                if(ret_int != null) {
                    message = "Successfully configured aggregate " + type + " interface: " + ret_int.getName();
                    log.info(message);
                } else {
                    message = "Failed to configure aggregate " + type + " inteface.";
                    log.error(message);
                }
            } else {
                ret_int = UpdateNetworkInterface.fromObject(new_interface, pearl);
            
                if(ret_int != null) {
                    message = "Successfully configured " + type + " interface: " + ret_int.getName();
                    log.info(message);
                } else {
                    message = "Failed to configure " + type + " inteface.";
                    log.error(message);
                }
            }
        }

        return message; 
    }

    public static int createBuckets(ArrayList<Ds3BucketConfig> bucket_list, DefaultsConfig defaults, BpConnector pearl) {
        log.info("Configuration contains (" + bucket_list.size() + ") DS3 buckets.");
        int success = 0;

        ArrayList<DataPolicy> policy_list = ListDataPolicies.all(pearl);
        HashMap<String, String> policy_map = MapDataPolicies.createNameIdMap(policy_list);

        ArrayList<Ds3User> user_list = ListUsers.all(pearl);
        HashMap<String, String> user_map = MapUsers.createUsernameIdMap(user_list);

        for(Ds3BucketConfig bucket : bucket_list) {
            try {
                log.info("Attempting to create bucket [" + bucket.getName() + "] for owner " + bucket.getOwner() + " with data policy " + bucket.getDataPolicy());

                Ds3Bucket new_bucket = new Ds3Bucket(defaults.getDs3Bucket());
               
                new_bucket.setName(bucket.getName());

                //=== Assign User ===
                if(bucket.getOwner() != null) {
                    new_bucket.setUserId(bucket.getOwner());
                }

                // Convert the human-readable name to a user id
                if(user_map.get(new_bucket.getUserId()) != null) {
                    new_bucket.setUserId(user_map.get(new_bucket.getUserId()));
                } else {
                    throw new Exception("User [" + new_bucket.getUserId() + "] does not exist.");
                }

                //=== Assign Data Policy ===
                if(bucket.getDataPolicy() != null) {
                    new_bucket.setDataPolicyId(bucket.getDataPolicy());
                }

                // Convert the human-readable name to a data policy id
                if(policy_map.get(new_bucket.getDataPolicyId()) != null) {
                    new_bucket.setDataPolicyId(policy_map.get(new_bucket.getDataPolicyId()));
                } else {
                    throw new Exception("Data policy [" + new_bucket.getDataPolicyId() + "] does not exist.");
                }

                // Added a vail-owned flag to the Ds3BucketConfig to control for Buckets in the Vail
                // configuration. As Vail creates the buckets, these buckets should be skipped during
                // the nacre bucket creation process. 
                if(bucket.isVailOwned() != null && !bucket.isVailOwned()) {
                    new_bucket = CreateDs3Bucket.fromObject(new_bucket, pearl);
                } else {
                    log.info("Bucket is vail-owned. Skipping the creation of " + bucket.getName() + ". Vail will handle bucket creation.");
                }

                if(new_bucket != null) {
                    success++;
                }

            } catch(Exception e) {
                log.error(e.getMessage());
                log.error("Failed to create bucket [" + bucket.getName() + "]");
            }
        }

        log.info("Successfully created (" + success + ") DS3 buckets.");
        log.info("Failed to create (" + (bucket_list.size() - success) + "/" + bucket_list.size() + ") buckets.");

        return success;
    }

    public static int createDataPolicies(ArrayList<DataPolicyConfig> policy_list, DefaultsConfig defaults, BpConnector pearl) {
        log.info("Configuration file contains (" + policy_list.size() + ") data policies");

        int success = 0;
        DataPolicy new_policy = null;

        // Gather Storage Domain Information from the BlackPearl to use for adding Data Persistence Rules.
        ArrayList<StorageDomain> domain_list = ListStorageDomains.all(pearl);
        HashMap<String, String> sd_map = MapStorageDomains.createNameIdMap(domain_list);

        for(DataPolicyConfig policy : policy_list) {
            new_policy = new DataPolicy(defaults.getDataPolicy());
            try {
                // Copy information over from the object to the data policy.
                // Only copy fields that are explicitly defined.
                new_policy.setName(policy.getName());
                
                if(policy.isAlwaysForcePutJobCreation() != null) {
                    new_policy.setAlwaysForcePutJobCreation(policy.isAlwaysForcePutJobCreation());
                }

                if(policy.isAlwaysMinimizeSpanningAcrossMedia() != null) {
                    new_policy.setAlwaysMinimizeSpanningAcrossMedia(policy.isAlwaysMinimizeSpanningAcrossMedia());
                }

                if(policy.isBlobbingEnabled() != null) {
                    new_policy.setBlobbingEnabled(policy.isBlobbingEnabled());
                }
                
                if(policy.getChecksumType() != null) {
                    new_policy.setChecksumType(policy.getChecksumType());
                }

                if(policy.getDefaultBlobSize() != null) {
                    new_policy.setDefaultBlobSize(policy.getDefaultBlobSize());
                }

                if(policy.getDefaultGetJobPriority() != null) {
                    new_policy.setDefaultGetJobPriority(policy.getDefaultGetJobPriority());
                }
                
                if(policy.getDefaultPutJobPriority() != null) {
                    new_policy.setDefaultPutJobPriority(policy.getDefaultPutJobPriority());
                }
                
                if(policy.getDefaultVerifyJobPriority() != null) {
                    new_policy.setDefaultVerifyJobPriority(policy.getDefaultVerifyJobPriority());
                }
                
                if(policy.isDefaultVerifyAfterWrite() != null) {
                    new_policy.setDefaultVerifyAfterWrite(policy.isDefaultVerifyAfterWrite());
                }

                if(policy.isEndToEndCrcRequired() != null) {
                    new_policy.setEndToEndCrcRequired(policy.isEndToEndCrcRequired());
                }

                if(policy.getRebuildPriority() != null) {
                    new_policy.setRebuildPriority(policy.getRebuildPriority());
                }
                
                if(policy.getVersioning() != null) {
                    new_policy.setVersioning(policy.getVersioning());
                }

                if(policy.getMaxVersionsToKeep() != null) {
                    new_policy.setMaxVersionsToKeep(policy.getMaxVersionsToKeep());
                }

                // Create the policy.
                new_policy = CreateDataPolicy.fromObject(new_policy, pearl);

                // Check if policy was created successfully.
                if(new_policy.getName() != null) {
                    // Copy the data policy id over to the config object.
                    // This allows us to reference it when adding the data persistence rules.
                    policy.setId(new_policy.getId());

                    addDataPersistenceRules(policy, defaults, sd_map, pearl);
                    success++;
                }
            } catch(Exception e) {
                log.error(e.getMessage());
            }
        }

        log.info("Successfully created (" + success + ") data policies.");
        log.info("Failed to create " + (policy_list.size() - success) + "/" + policy_list.size() + " data policies");
        return success;
    }

    public static int createDs3DiskPartitions(ArrayList<PoolConfig> pool_list, DefaultsConfig defaults, BpConnector pearl) {
        log.info("Configuration contains (" + pool_list.size() + ") DS3 disk partitions.");
        int success = 0;
        
        DiskPartition disk_par = null;
        PoolConfig stripe = null; // Ds3 Partitions are composed of single stripe ds3 disk pools
        Pool new_stripe = null;
        int disks_per_stripe = 0;

        for(PoolConfig pool : pool_list) {
            log.info("Creating DS3 disk partition [" + pool.getName() + "]");

            disks_per_stripe = pool.getDriveCount() / pool.getStripes();

            log.info("DS3 disk partitions contains (" + pool.getStripes() + ") disk partitions of (" + disks_per_stripe + ") DS3 disk pools.");

            // Configure the disk partition
            disk_par = new DiskPartition(defaults.getDs3DiskPartition());

            disk_par.setName(pool.getName());
           
            if(pool.getType() != null) {
                disk_par.setType(pool.getType());
            }
            
            try {
                // Create the stripes
                for(int i=0; i<pool.getStripes(); i++) {
                    stripe = new PoolConfig(pool);

                    stripe.setDriveCount(disks_per_stripe);
                    stripe.setStripes(1);

                    stripe.setType(disk_par.getType());

                    if(stripe.getType().equals("nearline")) {
                        stripe.setName("Arctic_Blue_"); // default name
                    } else if(stripe.getType().equals("online")) {
                        stripe.setName("Online_Disk_");
                        stripe.setPowerSavingMode(null);
                    } else {
                        throw new Exception("Unable to process pool type: " + stripe.getType());
                    }

                    if(pool.getProtection() != null) {
                        stripe.setProtection(pool.getProtectionLevel());
                    }
                
                    new_stripe = CreatePool.fromPoolConfig(stripe, defaults.getDs3Pool(), pearl); // this function dupes the above

                    if(new_stripe != null) {
                        log.debug("Adding pool id [" + new_stripe.getId() + "] to DS3 disk partition.");
                        disk_par.addPoolId(new_stripe.getId());
                    } else {
                        throw new Exception("Failed to create stripe [" + stripe.getName() + "_n" + i + "].");
                    }
                }

                if(disk_par.getPoolIds().size() > 0) {
                    disk_par = CreateDs3DiskPartition.fromObject(disk_par, pearl);

                    if(disk_par != null) {
                        success++;
                    }
                } else {
                    throw new Exception("No DS3 disk pools were allocated to disk partition [" + disk_par.getName() + "]");
                }
            } catch(Exception e) {
                log.error(e.getMessage());
                log.error("Failed to create disk partition [" + pool.getName() + "]");
            }

        }

        log.info("Successfully created (" + success + ") DS3 disk partitions.");
        log.info("Failed to create " + (pool_list.size() - success) + "/" + pool_list.size() + " DS3 disk partitions.");

        return success;
    }

    public static int createPools(ArrayList<PoolConfig> pool_list, DefaultsConfig defaults, BpConnector pearl) {
        log.info("Configuration contains(" + pool_list.size() + ") pools");
        int success = 0;
        Pool new_pool = null;

        ArrayList<DiskDrive> available_drives = ListDiskDrives.available(pearl);
        Stripe stripe = null;
        int drives_assigned = 0; // how many drives were assigned
        int drive_iterator = 0; // what drive is currently being assigned.
        int stripe_size = 0; // the number of drives in a stripe
        int stripe_counter = 0; // the number of drives assigned to a stripe.
        long pool_available = 0; // available space in bytes for the pool
        long pool_overhead = 0; // overhead space in bytes for the pool
        long pool_total = 0; // total (raw) size of the pool
        
        for(PoolConfig pool : pool_list) {
            try {
                // Load the default parameters
                // Boolean values can't be defaults as
                // omitting those fields will automatically set them as false.
                new_pool = new Pool(defaults.getNasPool());

                // Poplate the configuration fields
                new_pool.setName(pool.getName());
                new_pool.setStripes(pool.getStripes());
                new_pool.setProtection(pool.getProtectionLevel());
                new_pool.setProtectionSelect(pool.getProtectionLevel()); // possibly duplicate requirement from BP.

                // Check for potential options
                if(pool.getType() != null) {
                    if(pool.getType().equals("nas")) {
                        // The actual pool type for a NAS pool when created via the GUI
                        // is pool
                        new_pool.setType("pool"); 
                    } else {
                        new_pool.setType(pool.getType());
                    }
                }

                if(pool.getPowerSavingMode() != null) {
                    new_pool.setPowerSavingMode(pool.getPowerSavingMode());
                }

                if(pool.getHighWaterMark() != null) {
                    new_pool.setHighWaterMark(pool.getHighWaterMark());
                }

                if(pool.getEncryptionState() != null) {
                    new_pool.setEncryptionState(pool.getEncryptionState());
                } else {
                    // Set the encryption state as disabled if not specified.
                    new_pool.setEncryptionState("Disabled");
                }

               

                // Assign Drives to Pool
                // reset to 0
                drives_assigned = 0;
                pool_available = 0;
                pool_overhead = 0;
                pool_total = 0;
                stripe_size = pool.getDriveCount() / pool.getStripes();
                stripe_counter = 0;

                log.debug("Assigning drives for pool [" + new_pool.getName() + "]");
                log.debug("Stripe size: " + stripe_size);

                while(drives_assigned < pool.getDriveCount() && drive_iterator < available_drives.size()) {
                    log.debug("Searching for drive " + drives_assigned + " which belongs to stripe slot " + stripe_counter);
                    new_pool.addDiskId(available_drives.get(drive_iterator).getId());
                    pool_total += available_drives.get(drive_iterator).getSize();

                    //==== Build each stripe ====
                    // Create a new one when the stripe counter is reset to 0
                    if(stripe_counter == 0) {
                        stripe = new Stripe();
                        stripe.setType(pool.getProtectionLevel());
                    }

                    stripe.addDiskDrive(available_drives.get(drive_iterator));

                    stripe_counter++;

                    // Close out the stripe
                    if(stripe_counter >= stripe_size) {
                        new_pool.addStripe(stripe);
                        stripe_counter = 0;
                        log.debug("Adding stripe to pool. Current stripe count: " + new_pool.getTopology().size());
                    }
                   
                    //==== END STRIPE ====
                    
                
                    // Increment Iterator
                    drive_iterator++;
                    drives_assigned++;    
                }

                // Calculate capacities
                // use the first disk in the topology as the reference size
                log.debug("Calculating available capacity and pool overhead with protect level " + pool.getProtectionLevel());
                log.debug("Stripe count: " + new_pool.getTopology().size());
                log.debug("Stripe 0 disk drive count: " + new_pool.getTopology().get(0).getChildren().size());
                switch(new_pool.getProtection()) {
                    case "none":
                        pool_available = pool_total;
                        pool_overhead = 0;
                        break;
                    case "mirror":
                        pool_available = pool_total / 2;
                        pool_overhead = pool_available;
                        break;
                    case "single":
                        pool_overhead = (new_pool.getTopology().size() * new_pool.getTopology()
                                                                                .get(0)
                                                                                .getChildren()
                                                                                .get(0)
                                                                                .getSize()) * 1;
                        pool_available = pool_total - pool_overhead;
                        break; 
                    case "double":
                        pool_overhead = (new_pool.getTopology().size() * new_pool.getTopology()
                                                                                .get(0)
                                                                                .getChildren()
                                                                                .get(0)
                                                                                .getSize()) * 2;
                        pool_available = pool_total - pool_overhead;
                        break; 
                    case "triple":
                        pool_overhead = (new_pool.getTopology().size() * new_pool.getTopology()
                                                                                .get(0)
                                                                                .getChildren()
                                                                                .get(0)
                                                                                .getSize()) * 3;
                        pool_available = pool_total - pool_overhead;
                        break; 
                }

                new_pool.setRawSize(pool_total);
                new_pool.setAvailable(pool_available);
                new_pool.setOverhead(pool_overhead);
                new_pool.setUsed(0L); // set used capacity to 0, but the long version of it.


                log.debug("Pool [" + new_pool.getName() + "] will have " + pool_available + " bytes available.");
                // Check to make sure all the required drives were assigned.
                if(drives_assigned == pool.getDriveCount()) {
                    new_pool = CreatePool.fromObject(new_pool, pearl);

                    if(new_pool != null) {
                        success++; 
                    }
                } else {
                    throw new Exception("Unable to create pool. Not enough drives available.");
                }

            } catch(Exception e) {
                log.error(e.getMessage());
                e.printStackTrace();
                log.error("Failed to create pool [" + pool.getName() + "]");
            }
        }

        log.info("Successfully created (" + success + ") NAS pools.");

        return success;
    }

    public static int createShares(ArrayList<ShareConfig> share_list, String type, DefaultsConfig defaults, BpConnector pearl) {
        log.info("Configuration contains (" + share_list.size() + ") " + type + " shares.");
        int success = 0;

        Share new_share = null;

        ArrayList<Volume> vol_list = ListVolumes.all(pearl);
        HashMap<String, String> vol_map = MapVolumes.createNameIdMap(vol_list);

        ArrayList<Service> service_list = ListServices.all(pearl);
        HashMap<String, Service> service_map = MapServices.createNameServiceMap(service_list);

        for(ShareConfig share : share_list) {
            try {
                log.info("Attempting to create " + type + " share [" + share.getName() + "]");
           
                if(type.equals("NFS")) {
                    new_share = new Share(defaults.getNfsShare());
                    new_share.setComment(share.getName());
                    new_share.setType("Nfs");

                    // Overwrite default permissions if they are set.
                    //      The ShareConfig class takes permissions values
                    //      as an array and converts that array to a single 
                    //      string called access control. Thus check to see if 
                    //      there is at least 1 line in the permissions field,
                    //      and then output that line in the correct format.
                    if(share.getPermissions().size() > 0) {
                        new_share.setAccessControl(share.getAccessControl());
                    }
                } else { // default to cifs
                    new_share = new Share(defaults.getCifsShare());
                    new_share.setName(share.getName());
                    new_share.setType("Cifs");
                }

                if(share.getMountPoint() != null) {
                    new_share.setMountPoint(share.getMountPoint());
                }

                if(new_share.getMountPoint() != null) {
                    new_share.setMountPoint(new_share.getMountPoint().replace("{{volume}}", share.getVolume()));
                }
                
                if(share.getAccessControl() != null) {
                    new_share.setAccessControl(share.getAccessControl());
                }

                new_share.setReadonly(share.isReadonly());

                if(share.getPath() != null) {
                    new_share.setPath(share.getPath());
                }

                if(vol_map.get(share.getVolume()) != null) {
                    new_share.setVolumeId(vol_map.get(share.getVolume()));
                } else {
                    throw new Exception("Unable to create share [" + share.getName() + "]. Can't locate volume " + share.getVolume());
                }

                if(service_map.get(type) != null) {
                    new_share.setServiceId(service_map.get(type).getId());
                } else {
                    throw new Exception("Unable to create share [" + share.getName() + "]. Can't find service id for " + type);
                }

                new_share = CreateShare.fromObject(new_share, pearl);

                if(new_share != null) {
                    success++;
                }
            } catch(Exception e) {
                log.error(e.getMessage());
                log.error("Failed to create " + type + " share [" + share.getName() + "]");
            }
        }

        log.info("Successfully created (" + success + ") " + type + " shares.");
        log.info("Failed to create (" + (share_list.size() - success) + "/" + share_list.size() + ") " + type + " shares.");

        return success;
    }
    
    public static int createStorageDomains(ArrayList<StorageDomainConfig> domain_list, DefaultsConfig defaults, BpConnector pearl) {
        log.info("Configuration contains (" + domain_list.size() + ") storage domains.");
        int success = 0;
        StorageDomain new_domain = null;

        ArrayList<TapePartition> par_list = ListTapePartitions.all(pearl);
        HashMap<String, TapePartition> par_map = MapTapePartitions.createNameObjectMap(par_list);

        ArrayList<DiskPartition> disk_list = ListDs3DiskPartitions.all(pearl);
        HashMap<String, DiskPartition> disk_map = MapDiskPartitions.createNameObjectMap(disk_list);

        for(StorageDomainConfig domain : domain_list) {
            try {
                // Load the default parameters.
                // Boolean values can't be defaults as
                // omitting this fields will automatically set them to false.
                new_domain = new StorageDomain(defaults.getStorageDomain());

                // Populate the configuration fields.
                new_domain.setName(domain.getName());

                if(domain.getWriteOptimization() != null) {
                    new_domain.setWriteOptimization(domain.getWriteOptimization());
                }

                if(domain.getLtfsFileNaming() != null) {
                    new_domain.setLtfsFileNaming(domain.getLtfsFileNaming());
                }

                if(domain.getAutoEjectUponCron() != null) {
                    new_domain.setAutoEjectUponCron(domain.getAutoEjectUponCron());
                }

                // Populate required boolean values
                new_domain.setSecureMediaAllocation(domain.isSecureMediaAllocation());
                new_domain.setMediaEjectionAllowed(domain.isMediaEjectionAllowed());
                new_domain.setAutoEjectUponJobCompletion(domain.isAutoEjectUponJobCompletion());
                new_domain.setAutoEjectUponJobCancellation(domain.isAutoEjectUponJobCancellation());
                new_domain.setAutoEjectUponMediaFull(domain.isAutoEjectUponMediaFull());        
                new_domain.setAutoEjectMediaFullThreshold(domain.getAutoEjectMediaFullThreshold());
                new_domain.setMaximumAutoVerificationFrequencyInDays(domain.getMaximumAutoVerificationFrequencyInDays());             

                new_domain = CreateStorageDomain.fromObject(new_domain, pearl);

                if(new_domain.getName() != null) {
                    domain.setId(new_domain.getId()); // grab the id from the newly created domain so members can be added.
                    
                    addStorageDomainMembers(domain, defaults, par_map, disk_map, pearl);

                    success++;
                }
            } catch(Exception e) {
                log.error(e.getMessage());
            }
        }

        log.info("Successfully created (" + success + ") storage domains.");
        log.info("Failed to create " + (domain_list.size() - success) + "/" + domain_list.size() + " storage domains.");
        return success;
    }

    public static int createVolumes(ArrayList<VolumeConfig> volume_list, DefaultsConfig defaults, BpConnector pearl) {
        log.info("Configuration contains (" + volume_list.size() + ") volumes.");
        int success = 0;
        Volume new_volume = null;

        ArrayList<Pool> pool_list = ListPools.all(pearl);
        HashMap<String, String> pool_map = MapNasPools.createNameIdMap(pool_list);
        
        for(VolumeConfig vol : volume_list) {
            try {
                log.info("Attempting to create volume [" + vol.getName() + "]");
                new_volume = new Volume(defaults.getVolume());

                new_volume.setName(vol.getName());

                if(pool_map.get(vol.getPool()) != null) {
                    new_volume.setPoolId(pool_map.get(vol.getPool()));
                } else {
                    throw new Exception("Unabled to create volume [" + vol.getName() + "] pool [" + vol.getPool() + "] does not exist.");
                }

                // Set min and max sizes
                // Reservation is min size
                // Quota is max size
                if(vol.getMinSize() != null && !vol.getMinSize().equals("")) {
                    log.debug("Setting minimum volume size of " + vol.getMinSize());
                    new_volume.setReservation(StorageConversion.humanReadableToBytes(vol.getMinSize()));
                }

                if(vol.getMaxSize() != null && !vol.getMaxSize().equals("")) {
                    log.debug("Setting maximum volume size of " + vol.getMaxSize());
                    new_volume.setQuota(StorageConversion.humanReadableToBytes(vol.getMaxSize()));
                }

                // Check to make sure type is set
                if(new_volume.getType() == null) {
                    new_volume.setType("data");
                }

                // Create the volume
                new_volume = CreateVolume.fromObject(new_volume, pearl);

                if(new_volume != null) {
                    log.debug("Volume created successfully.");
                    success++;
                }

            } catch(Exception e) {
                log.error(e.getMessage());
                log.error("Failed to create volume [" + vol.getName() + "]");
            }
        }

        log.info("Successfully created (" + success + ") volumes.");
        log.info("Failed to create " + (volume_list.size() - success) + "/" + volume_list.size() + " volumes.");

        return success;
    }
   
    public static boolean setDnsServers(List<String> dns_servers, List<String> default_servers, BpConnector pearl) {
        log.info("Configuration contains (" + dns_servers.size() + ") DNS servers.");
        log.info("Default configuration contains (" + default_servers.size() + ") DNS servers.");
    
        NetworkInterface interf = null;

        if(dns_servers.size() > 0) {
            log.info("Using configuration settings to update the DNS servers.");
            interf = UpdateDnsSettings.fromList(dns_servers, pearl);
        } else if(default_servers.size() > 0) {
            log.info("Using default settings to update the DNS servers.");
            interf = UpdateDnsSettings.fromList(default_servers, pearl);
        } else {
            log.info("No DNS servers provided for configuration.");
        }
    
        if(interf != null) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean setNtpServers(List<String> config_servers, List<String> default_servers, BpConnector pearl) {
        log.info("Configuration contains (" + config_servers.size() + ") NTP servers.");
        log.info("Default configuration contains (" + default_servers.size() + ") NTP servers.");
    
        String[] servers = new String[2];
        
        // Set default values
        for(int i=0; i<default_servers.size(); i++) {
            if(i < 2) { // max allowed servers
                servers[i] = default_servers.get(i);
            }
        }

        // Overwrite defaults with settings.
        for(int i=0; i<config_servers.size(); i++) {
            if(i < 2) { // max allowed servers
                servers[i] = config_servers.get(i);
            }
        }

        NtpSettings settings = UpdateNtpServers.setServers(servers[0], servers[1], pearl);

        if(settings != null) {
            log.info("Successfully updated NTP settings from configuration.");
            return true;
        } else {
            log.error("Could not load NTP settings from configuraton.");
            return false;
        }
    }

    public static boolean setSmtpSettings(SmtpSettings config, SmtpSettings defaults, BpConnector pearl) {
        log.info("Setting SMTP settings from configuration.");

        SmtpSettings settings = new SmtpSettings(defaults);

        // Replace default parameters with values included in the configuration.
        if(config != null) {
            if(config.getAddress() != null) {
                settings.setAddress(config.getAddress());
            }

            if(config.getPort() != null) {
                settings.setPort(config.getPort());     
            }

            if(config.getAuthenticationType() != null) {
                settings.setAuthenticationType(config.getAuthenticationType());
            }   

            if(config.isTls() != null) {
                settings.setTls(config.isTls());
            }

            if(config.getUsername() != null) {
                settings.setUsername(config.getUsername());
            }

            if(config.getPassword() != null) {
                settings.setPassword(config.getPassword());
            }

            if(config.getFrom() != null) {
                settings.setFrom(config.getFrom());
            }
        }

        settings = UpdateSmtpSettings.fromObject(settings, pearl);

        if(settings != null) {
            log.info("SMTP settings configured successfully");
            return true;
        } else {
            log.warn("SMTP settings were not configured.");
            return false;
        }
        
    }

    public static void waitForDs3BackendToComeOnline(int wait_timer, BpConnector pearl) {
        log.info("Checking if a pause is needed for the DS3 backed to come online.");

        List<ActivationKey> key_list = ListActivationKeys.all(pearl);
        boolean wait_required = false;

        for(ActivationKey key : key_list) {
            if(key.getKeyType().equals("em_s3")) {
                log.info("DS3 Nearline Gateway key is present. Waiting for the backend configuration to complete.");
                wait_required = true;
            }
        }

        if(wait_required) {
            System.out.println("Waiting on DS3 Nearline Gateway backend to come online.");
            
            ServiceS3 s3_service;

            do {
                log.debug("Waiting " + wait_timer + " seconds for the backend to come online.");
                s3_service = GetService.s3(pearl);
            } while(s3_service != null && !s3_service.getState().equals("operational"));
        } else {
            log.debug("No need to wait for DS3 Nearline Gateway backend.");
        }
    }
}
