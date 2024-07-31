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
import com.spectralogic.blackpearl.nacre.model.BpConfig;
import com.spectralogic.blackpearl.nacre.model.DataPersistenceRule;
import com.spectralogic.blackpearl.nacre.model.DataPersistenceRuleConfig;
import com.spectralogic.blackpearl.nacre.model.DataPolicy;
import com.spectralogic.blackpearl.nacre.model.DataPolicyConfig;
import com.spectralogic.blackpearl.nacre.model.DefaultsConfig;
import com.spectralogic.blackpearl.nacre.model.StorageDomain;
import com.spectralogic.blackpearl.nacre.model.StorageDomainConfig;
import com.spectralogic.blackpearl.nacre.model.StorageDomainMember;
import com.spectralogic.blackpearl.nacre.model.StorageDomainMemberConfig;
import com.spectralogic.blackpearl.nacre.model.TapePartition;
import com.spectralogic.blackpearl.nacre.model.TapeTypeSummary;
import com.spectralogic.blackpearl.nacre.util.io.LoadFile;
import com.spectralogic.blackpearl.nacre.util.map.MapStorageDomains;
import com.spectralogic.blackpearl.nacre.util.map.MapTapePartitions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigureBlackPearl {
    private static final Logger log = LoggerFactory.getLogger(ConfigureBlackPearl.class);

    public static ArrayList<String> fromFile(String file_name, DefaultsConfig defaults, BpConnector pearl) {
        log.info("Loading BlackPearl configuration from file: " + file_name);
        ArrayList<String> results = null;
        
        try {
            String file_string = LoadFile.toString(file_name);

            Gson gson = new Gson();
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
        // Create Storage Domains
        //=======================================
        // Copy to a new list to allow for empty sets in the configuration.
        ArrayList<StorageDomainConfig> new_domains = config.getStorageDomains() != null ? config.getStorageDomains() : new ArrayList<StorageDomainConfig>();
        success = createStorageDomains(new_domains, defaults, pearl);
        message = "Successfully created " + success + "/" + new_domains.size()  + " storage domains";
        results.add(message);

        //=======================================
        // Create Data Policies
        //=======================================
        // Copy to a new list to allow for empty sets in the configuration
        ArrayList<DataPolicyConfig> new_policies = config.getDataPolicies() != null ? config.getDataPolicies() : new ArrayList<DataPolicyConfig>();
        success = createDataPolicies(new_policies, defaults, pearl);
        message = "Successfully created " + success + "/" + new_policies.size() + " data policies";
        results.add(message);

        return results;
    }

    //===========================================
    // Private Functions
    //===========================================
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
    
    public static int addStorageDomainMembers(StorageDomainConfig domain, DefaultsConfig defaults, HashMap<String, TapePartition> par_map, BpConnector pearl) throws Exception {
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

            if(member.getPartition().equalsIgnoreCase("all")) {
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
            } else if(par_map.get(member.getPartition()) == null) {
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

    public static int createStorageDomains(ArrayList<StorageDomainConfig> domain_list, DefaultsConfig defaults, BpConnector pearl) {
        log.info("Configuration file contains (" + domain_list.size() + ") storage domains.");
        int success = 0;
        StorageDomain new_domain = null;

        ArrayList<TapePartition> par_list = ListTapePartitions.all(pearl);
        HashMap<String, TapePartition> par_map = MapTapePartitions.createNameObjectMap(par_list);

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
                    addStorageDomainMembers(domain, defaults, par_map, pearl);

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
}
