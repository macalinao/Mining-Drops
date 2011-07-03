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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author simplyianm
 */
public class MDBL extends BlockListener {
    
    /**
     * Random object
     * 
     * @since 0.2
     */
    private static Random r = new Random();
    
    /**
     * Plugin instance
     * 
     * @since 0.1
     */
    public static MiningDrops plugin;
    
    /**
     * Output to console
     * 
     * @since 0.1
     */
    Logger log = Logger.getLogger("Minecraft");
    
    /**
     * Constructor
     * 
     * @param instance Main Class
     * 
     * @since 0.1
     */
    public MDBL(MiningDrops instance) { 
        plugin = instance;
    }
    
    /**
     * Drops the items from the block.
     * 
     * @param event Event
     */
    @Override
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.isCancelled()) return;
        Integer skill = 100;
        Block block = event.getBlock();
        Integer block_material = block.getTypeId();
        List possible_drops = plugin.config.getKeys("blocks." + block_material + ".drops");
        if(possible_drops != null) {
            Boolean is_xor = plugin.config.readBoolean("blocks." + block_material + ".options.xor");
            Iterator<String> iterator = possible_drops.iterator();
            ArrayList xor_drops = new ArrayList();
            while (iterator.hasNext()) {
                String block_drop_string = iterator.next();
                Integer block_drop = Integer.parseInt(block_drop_string);
                Integer drop_rate = plugin.config.readInteger("blocks." + block_material + ".drops." + block_drop_string + "." + "rate");
                Integer drop_amount = plugin.config.readInteger("blocks." + block_material + ".drops." + block_drop_string + "." + "amount");
                Integer generated = r.nextInt(1000000) + 1;
                Integer user_chance = drop_rate * 100;
                if (generated <= user_chance) {
                    if (is_xor == true) {
                        xor_drops.add(block_drop + ";" + drop_amount);
                    } else {
                        World world = block.getWorld();
                        ItemStack drop_stack = new ItemStack(block_drop, drop_amount);
                        world.dropItemNaturally(block.getLocation(), drop_stack);
                    }
                }
            }
            if (is_xor == true) {
                Integer roll = r.nextInt(xor_drops.size());
                Object theDrop_obj = xor_drops.get(roll);
                String theDrop = theDrop_obj.toString();
                String[] dropInfo = theDrop.split(";");
                World world = block.getWorld();
                ItemStack drop_stack = new ItemStack(Integer.parseInt(dropInfo[0]), Integer.parseInt(dropInfo[1]));
                world.dropItemNaturally(block.getLocation(), drop_stack);
            }
        }
    }
}