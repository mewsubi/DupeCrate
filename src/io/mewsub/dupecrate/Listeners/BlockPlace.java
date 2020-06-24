package io.mewsub.dupecrate.Listeners;

import java.util.UUID;

import org.bukkit.NamespacedKey;

import org.bukkit.block.Block;
import org.bukkit.block.Barrel;

import org.bukkit.event.block.BlockPlaceEvent;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;

import org.bukkit.inventory.ItemStack;

import org.bukkit.inventory.meta.ItemMeta;

import io.mewsub.dupecrate.DupeCrate;
import io.mewsub.dupecrate.Data.UUIDDataType;

public class BlockPlace implements Listener {
    
    @EventHandler
    public void onBlockPlace( BlockPlaceEvent evt ) {
        ItemStack item = evt.getItemInHand();
        ItemMeta itemMeta = item.getItemMeta();
        String name = itemMeta.getDisplayName();
        if( name.equals( DupeCrate.DCNAME ) ) {
            evt.setCancelled( true );
            return;
        }
        if( name.equals( "Loot Crate" ) ) {
            NamespacedKey key = new NamespacedKey( DupeCrate.plugin, "uuid" );
            UUID uuid = itemMeta.getPersistentDataContainer().get( key, new UUIDDataType() );
            ItemStack[] contents = DupeCrate.crates.remove( uuid );
            Block block = evt.getBlockPlaced();
            Barrel barrel = ( Barrel ) ( block.getState() );
            barrel.getInventory().setContents( contents );
        }
    }

}