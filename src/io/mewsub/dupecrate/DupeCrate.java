package io.mewsub.dupecrate;

import java.util.UUID;
import java.util.HashMap;

import org.bukkit.plugin.Plugin;

import org.bukkit.plugin.java.JavaPlugin;

import org.bukkit.Server;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import org.bukkit.inventory.meta.ItemMeta;

import io.mewsub.dupecrate.Listeners.BlockPlace;
import io.mewsub.dupecrate.Listeners.PlayerInteract;

public class DupeCrate extends JavaPlugin {

    public static Plugin plugin;
    public static Server server;
    public static final String DCNAME = "Dupe Crate";

    public static HashMap<UUID, ItemStack[]> crates;

    @Override
    public void onEnable() {
        this.plugin = ( Plugin ) this;
        this.server = this.getServer();
        this.server.getPluginManager().registerEvents( new PlayerInteract(), this );
        this.server.getPluginManager().registerEvents( new BlockPlace(), this );

        this.crates = new HashMap<UUID, ItemStack[]>();

        ItemStack dc = new ItemStack( Material.BARREL );

        ItemMeta dcMeta = dc.getItemMeta();
        dcMeta.setDisplayName( DCNAME );
        dc.setItemMeta( dcMeta );

        NamespacedKey dcKey = new NamespacedKey( this, "dc" );

        ShapedRecipe dcRecipe = new ShapedRecipe( dcKey, dc );
        dcRecipe.shape( " C ", "CBC", " C " );
        dcRecipe.setIngredient( 'C', Material.CRAFTING_TABLE );
        dcRecipe.setIngredient( 'B', Material.BARREL );

        this.server.addRecipe( dcRecipe );
    }

    @Override
    public void onDisable() {

    }

}
