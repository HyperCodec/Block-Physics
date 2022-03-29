package me.tristandasavage.physics;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.FallingBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class Listeners implements Listener {
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if(!event.isCancelled()) {
            UUID uuid = UUID.randomUUID();
            Main.iterations.put(uuid, 0);
            if(Main.plugin.getConfig().getInt("maxaffectedblocks") != 0) {
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        Main.iterations.remove(uuid);
                        this.cancel();
                    }
                }.runTaskLater(Main.plugin, Main.plugin.getConfig().getInt("maxaffectedblocks") + 20);
            }
            Main.updateNearbyBlocks(event.getBlock(), true, uuid);
        }
    }
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if(!event.isCancelled()) {
            UUID uuid = UUID.randomUUID();
            Main.iterations.put(uuid, 0);
            if(Main.plugin.getConfig().getInt("maxaffectedblocks") != 0) {
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        Main.iterations.remove(uuid);
                        this.cancel();
                    }
                }.runTaskLater(Main.plugin, Main.plugin.getConfig().getInt("maxaffectedblocks") + 20);
            }
            Main.updateNearbyBlocks(event.getBlock(), false, uuid);
        }
    }
    @EventHandler
    public void onEntityChangeBlock(EntityChangeBlockEvent event) {
        if (!event.isCancelled() && event.getEntity() instanceof FallingBlock) {
            if (event.getEntity().getPersistentDataContainer().has(new NamespacedKey(Main.plugin, "eventid"), PersistentDataType.STRING) && Main.plugin.getConfig().getBoolean("fallingblocksupdate")) {
                UUID uuid = UUID.fromString(event.getEntity().getPersistentDataContainer().get(new NamespacedKey(Main.plugin, "eventid"), PersistentDataType.STRING));
                if(Main.plugin.getConfig().getInt("maxaffectedblocks") != 0) {
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            Main.iterations.remove(uuid);
                            this.cancel();
                        }
                    }.runTaskLater(Main.plugin, Main.plugin.getConfig().getInt("maxaffectedblocks") + 20);
                }
                Main.updateNearbyBlocks(event.getBlock(), false, uuid);
            }
        }
    }
}
