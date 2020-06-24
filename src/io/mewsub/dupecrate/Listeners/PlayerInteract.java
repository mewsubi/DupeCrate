package io.mewsub.dupecrate.Listeners;

import java.util.UUID;

import org.bukkit.NamespacedKey;

import org.bukkit.persistence.PersistentDataContainer;

import org.bukkit.Material;

import org.bukkit.block.Block;
import org.bukkit.block.Barrel;

import org.bukkit.block.data.BlockData;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;

import org.bukkit.event.player.PlayerInteractEvent;

import org.bukkit.event.block.Action;

import org.bukkit.entity.Player;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import org.bukkit.inventory.meta.ItemMeta;

import io.mewsub.dupecrate.DupeCrate;
import io.mewsub.dupecrate.Data.UUIDDataType;

public class PlayerInteract implements Listener {
    
    @EventHandler
    public void onPlayerInteract( PlayerInteractEvent evt ) {
        Player player = evt.getPlayer();
        if( evt.hasItem() && evt.getAction() == Action.RIGHT_CLICK_BLOCK ) {
            Block block = evt.getClickedBlock();
            BlockData blockData = block.getBlockData();
            if( blockData.getMaterial() == Material.BARREL ) {
                ItemStack item = evt.getItem();
                ItemMeta itemMeta = item.getItemMeta();
                if( itemMeta.getDisplayName().equals( DupeCrate.DCNAME ) ) {
                    evt.setCancelled( true );
                    ItemStack duped = new ItemStack( Material.BARREL );
                    ItemMeta dupedMeta = duped.getItemMeta();
                    dupedMeta.setDisplayName( "Loot Crate" );

                    NamespacedKey key = new NamespacedKey( DupeCrate.plugin, "uuid" );
                    PersistentDataContainer dupedData = dupedMeta.getPersistentDataContainer();
                    UUID uuid = UUID.randomUUID();
                    dupedData.set( key, new UUIDDataType(), uuid );
                    duped.setItemMeta( dupedMeta );

                    ItemStack stack = player.getInventory().getItemInMainHand();
                    int amt = stack.getAmount();
                    if( amt == 1 ) {
                        player.getInventory().setItemInMainHand( duped );
                    } else {
                        block.getWorld().dropItemNaturally( block.getLocation().clone().add( 0.5, 0.0, 0.5 ), duped );
                        stack.setAmount( amt - 1 );
                        player.getInventory().setItemInMainHand( stack );
                    }

                    Barrel barrel = ( Barrel ) ( block.getState() );
                    Inventory inv = barrel.getInventory();
                    DupeCrate.crates.put( uuid, inv.getContents().clone() );
                }
            }
        }
    }

}