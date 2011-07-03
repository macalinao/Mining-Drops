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

import java.util.Random;
import java.util.logging.Logger;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * AlbieRPG Mining Skill Main Object
 * 
 * @since 0.1
 */
public class MiningDrops extends JavaPlugin {
    
    /**
     * Instance of the configuration file.
     * 
     * @since 0.2
     */
    MDConfig config = new MDConfig(this);
    
    /**
     * Block listener
     * 
     * @since 0.1
     */
    private final MDBL blockListener = new MDBL(this);
    
    /**
     * Random object
     * 
     * @since 0.1
     */
    private static Random r = new Random();
    
    /**
     * Output to console
     * 
     * @since 0.1
     */
    Logger log = Logger.getLogger("Minecraft");
    
    /**
     * onEnable Function
     * 
     * Registers events etc. on the enabling
     * of the plugin.
     * 
     * @since 0.1
     */
    @Override
    public void onEnable() {
        config.configInit();
        PluginManager pm = this.getServer().getPluginManager();
        pm.registerEvent(Event.Type.BLOCK_BREAK, blockListener, Event.Priority.Lowest, this);
        log.info("[MiningDrops] Plugin Enabled.");
    }
    
    /**
     * onDisable Function
     * 
     * Called when the plugin is disabled.
     * 
     * @since 0.1
     */
    @Override
    public void onDisable() {
        log.info("[MiningDrops] Plugin Disabled.");
    }
}