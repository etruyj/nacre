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
import com.spectralogic.blackpearl.nacre.model.DataPersistenceRule;
import com.spectralogic.blackpearl.nacre.model.DataPersistenceRuleConfig;
import com.spectralogic.blackpearl.nacre.model.DataPolicy;
import com.spectralogic.blackpearl.nacre.model.DataPolicyConfig;
import com.spectralogic.blackpearl.nacre.model.DefaultsConfig;
import com.spectralogic.blackpearl.nacre.model.DiskDrive;
import com.spectralogic.blackpearl.nacre.model.DiskPartition;
import com.spectralogic.blackpearl.nacre.model.Pool;
import com.spectralogic.blackpearl.nacre.model.PoolConfig;
import com.spectralogic.blackpearl.nacre.model.Service;
import com.spectralogic.blackpearl.nacre.model.ServiceCifs;
import com.spectralogic.blackpearl.nacre.model.ServiceNfs;
import com.spectralogic.blackpearl.nacre.model.Share;
import com.spectralogic.blackpearl.nacre.model.ShareConfig;
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
import com.spectralogic.blackpearl.nacre.util.map.MapDiskPartitions;
import com.spectralogic.blackpearl.nacre.util.map.MapNasPools;
import com.spectralogic.blackpearl.nacre.util.map.MapServices;
import com.spectralogic.blackpearl.nacre.util.map.MapStorageDomains;
import com.spectralogic.blackpearl.nacre.util.map.MapTapePartitions;
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
            success = addActivationKeys(new_keys, defaults, pearl);
            message = "Successfully added " + success + "/" + new_keys.size()  + " activation keys";
            results.add(message);
        }

        //=======================================
        // Create Pools
        //=======================================
        // Copy to a new list to allow for empty sets in the configuration.
        ArrayList<PoolConfig> new_pools = config.getNasPools() != null ? config.getNasPools() : new ArrayList<PoolConfig>();
        
        if(new_pools.size() > 0) {
            success = createPools(new_pools, defaults, pearl);
            message = "Successfully created " + success + "/" + new_pools.size()  + " pools";
            results.add(message);
        }

        //=======================================
        // Create Volumes
        //=======================================
        // Copy to a new list to allow for empty sets in the configuration.
        ArrayList<VolumeConfig> new_volumes = config.getVolumes() != null ? config.getVolumes() : new ArrayList<VolumeConfig>();
        
        if(new_volumes.size() > 0) {
            success = createVolumes(new_volumes, defaults, pearl);
            message = "Successfully created " + success + "/" + new_volumes.size()  + " volumes";
            results.add(message);
        }

        //=======================================
        // Create Shares
        //=======================================
        // Copy to a new list to allow for empty sets in the configuration.
        // ==== CIFS ====
        ArrayList<ShareConfig> new_shares = config.getCifsShares() != null ? config.getCifsShares() : new ArrayList<ShareConfig>();
        
        if(new_shares.size() > 0) {
            success = createShares(new_shares, "CIFS", defaults, pearl);
            message = "Successfully created " + success + "/" + new_shares.size()  + " CIFS shares";
            results.add(message);
        }

        // === NFS ====
        new_shares = config.getNfsShares() != null ? config.getNfsShares() : new ArrayList<ShareConfig>();
        
        if(new_shares.size() > 0) {
            success = createShares(new_shares, "NFS", defaults, pearl);
            message = "Successfully created " + success + "/" + new_shares.size()  + " NFS shares";
            results.add(message);
        }

        //=======================================
        // Create Ds3 Disk Partitions
        //=======================================
        // Copy to a new list to allow for empty sets in the configuration.
        ArrayList<PoolConfig> new_ds3_pars = config.getDiskPartitions() != null ? config.getDiskPartitions() : new ArrayList<PoolConfig>();
        if(new_ds3_pars.size() > 0) {
            success = createDs3DiskPartitions(new_ds3_pars, defaults, pearl);
            message = "Successfully created " + success + "/" + new_pools.size()  + " disk partitions";
            results.add(message);
        }

        //=======================================
        // Create Storage Domains
        //=======================================
        // Copy to a new list to allow for empty sets in the configuration.
        ArrayList<StorageDomainConfig> new_domains = config.getStorageDomains() != null ? config.getStorageDomains() : new ArrayList<StorageDomainConfig>();
        
        if(new_domains.size() > 0) {
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
            success = createDataPolicies(new_policies, defaults, pearl);
            message = "Successfully created " + success + "/" + new_policies.size() + " data policies";
            results.add(message);
        }

        return results;
    }

    //===========================================
    // Private Functions
    //===========================================
    public static int addActivationKeys(ArrayList<ActivationKeyConfig> key_list, DefaultsConfig defaults, BpConnector pearl) {
        log.info("Configuration contains (" + key_list.size() + ") activation keys.");

        int success = 0;
        ActivationKey new_key = null;

        for(ActivationKeyConfig key : key_list) {
            new_key = AddActivationKey.fromObject(key, defaults, pearl);

            if(new_key != null) {
                success++;
            }
        }
        
        log.info("Successfully added " + success + " activation keys.");
        log.info("Failed to add " + (key_list.size() - success) + "/" + key_list.size() + " activation keys.");

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
        for(StorageDomainMemberConfig member : domain.getMembers()) {
            log.info("Member partition: " + member.getPartition());

            new_member = new StorageDomainMember(defaults.getStorageDomainMember());

            if(member.getWritePreference() != null) {
                new_member.setWritePreference(member.getWritePreference());
            }

            if(member.getAutoCompactionThreshold() != null) {
                System.err.println("Setting auto compaction threshold.");
                new_member.setAutoCompactionThreshold(member.getAutoCompactionThreshold());
            }
            new_member.setStorageDomainId(domain.getId());

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

                    if(pool.getProtectionLevel() != null) {
                        stripe.setProtectionLevel(pool.getProtectionLevel());
                    }
                
                    new_stripe = CreatePool.fromPoolConfig(stripe, defaults.getDs3Pool(), pearl); // this function dupes the above

                    if(new_stripe != null) {
                        log.debug("Adding pool id [" + new_stripe.getId() + "] to DS3 disk partition.");
                        disk_par.addPoolId(new_stripe.getId());
                    } else {
                        throw new Exception("Failed to create stripei [" + stripe.getName() + "_n" + i + "].");
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
                
                // Check for potential options
                if(pool.getType() != null) {
                    new_pool.setType(pool.getType());
                }

                if(pool.getPowerSavingMode() != null) {
                    new_pool.setPowerSavingMode(pool.getPowerSavingMode());
                }

                if(pool.getHighWaterMark() != null) {
                    new_pool.setHighWaterMark(pool.getHighWaterMark());
                }

                if(pool.getEncryptionState() != null) {
                    new_pool.setEncryptionState(pool.getEncryptionState());
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
                    log.info("Searching for drive " + drives_assigned + " which belongs to stripe slot " + stripe_counter);
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

                new_pool.setAvailable(pool_available);
                new_pool.setOverhead(pool_overhead);
                new_pool.setUsed(0); // set used capacity to 0


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
                }

                new_share.setMountPoint(new_share.getMountPoint().replace("{{volume}}", share.getVolume()));
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
                if(vol.getMinSize() != null) {
                    log.debug("Setting minimum volume size of " + vol.getMinSize());
                    new_volume.setReservation(StorageConversion.humanReadableToBytes(vol.getMinSize()));
                }

                if(vol.getMaxSize() != null) {
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
}
