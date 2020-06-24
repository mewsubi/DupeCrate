package io.mewsub.dupecrate.Listeners;


import org.bukkit.event.inventory.PrepareItemCraftEvent;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.CraftingInventory;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;

public class PrepareItemCraft implements Listener {

    @EventHandler
    public void onPrepareItemCraft( PrepareItemCraftEvent evt ) {
        CraftingInventory inv = evt.getInventory();
        ItemStack[] matrix = inv.getMatrix();
        for( ItemStack item : matrix ) {
            if( )
        }
    }

}