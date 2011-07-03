/*
 * Mining Drops for Bukkit
 * Copyright (C) 2011 simplyianm
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.simplyian.mc.miningdrops;

import java.io.File;
import java.util.List;

import org.bukkit.util.config.Configuration;

/**
 * Configuration Class
 * 
 * @since 0.2
 */
public class MDConfig {
    private static MiningDrops plugin;
    
    /**
     * Constructor
     * 
     * @param instance
     * 
     * @since 0.2
     */
    public MDConfig(MiningDrops instance) {
        plugin = instance;
    }
    
    /**
     * Configuration file path
     * 
     * @since 0.2
     */
    File config_file = new File("plugins/AlbieRPG/Skills/Mining.yml");

    /**
     * Config file checker
     * 
     * Does the config exist?
     * 
     * @since 0.2
     */
    public void configInit(){
        new File("plugins" + File.separator + "AlbieRPG").mkdir(); //Make the directory
        new File("plugins" + File.separator + "AlbieRPG" + File.separator + "Skills").mkdir(); //Make the directory
        if(!config_file.exists()){
            try {
                config_file.createNewFile(); //Create the config file if it's not here already
                generateDefaults(); //Apply the default settings to the file
            } catch (Exception ex) {
                plugin.log.info("[AlbieRPG] [Mining] Error creating the config file. >:-O");
            }
        } else {
            plugin.log.info("[AlbieRPG] [Mining] Configuration loaded."); //Load the configuration file
        }
    }
    
    /**
     * Loads the config file.
     * 
     * @return Config file object
     * 
     * @since 0.2
     */
    private Configuration loadConfig(){
        try {
            Configuration config = new Configuration(config_file);
            config.load();
            return config;
        } catch (Exception e) {
        }
        return null;
    }
    
    /**
     * Set a value for a key.
     * 
     * @param key
     * @param value
     * 
     * @since 0.2
     */
    private void setValue(String key, Object value) {
        Configuration config = loadConfig();
        config.setProperty(key, value);
        config.save();
    }
    
    /**
     * Reads the value of a boolean from the config file
     * 
     * @param key Key name
     * 
     * @since 0.2
     */
    public Boolean readBoolean(String key) {
        Configuration config = loadConfig();
        return config.getBoolean(key, false);
    }
    
    /**
     * Reads the value of a double from the config file
     * 
     * @param key Key name
     */
    public Double readDouble(String key) {
        Configuration config = loadConfig();
        return config.getDouble(key, 0);
    }
    
    /**
     * Reads the value of a string from the config file
     * 
     * @param key Key name
     * 
     * @since 0.2
     */
    public String readString(String key) {
        Configuration config = loadConfig();
        return config.getString(key, null);
    }
    
    /**
     * Reads the value of an integer from the config file
     * 
     * @param key Key name
     * 
     * @since 0.2
     */
    public Integer readInteger(String key) {
        Configuration config = loadConfig();
        return config.getInt(key, 0);
    }
    
    /**
     * Reads a string list from the config file
     * 
     * @param key Key name
     * 
     * @since 0.2
     */
    public List<String> readStringList(String key) {
        Configuration config = loadConfig();
        return config.getStringList(key, null);
    }    
    
    /**
     * Gets a list of keys at the specified location.
     * 
     * @param key Key name
     * 
     * @since 0.2
     */
    public List<String> getKeys(String key) {
        Configuration config = loadConfig();
        return config.getKeys(key);
    }
    
    /**
     * Inserts some defaults into the YAML file.
     * 
     * @since 0.2
     */
    private void generateDefaults() {
        //Stone drops
        setValue("blocks.1.options.xor" , false);
        setValue("blocks.1.drops.14.rate" , 100);
        setValue("blocks.1.drops.14.amount" , 1);
        setValue("blocks.1.drops.15.rate" , 50);
        setValue("blocks.1.drops.15.amount" , 1);
    }
}