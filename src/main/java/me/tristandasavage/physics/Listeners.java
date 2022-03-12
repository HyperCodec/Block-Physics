package me.tristandasavage.physics;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.EntityChangeBlockEvent;

import java.util.UUID;

public class Listeners implements Listener {
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if(!event.isCancelled()) {
            UUID uuid = UUID.randomUUID();
            Main.iterations.put(uuid, 0);
            Main.updateNearbyBlocks(event.getBlock(), true, uuid);
        }
    }
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if(!event.isCancelled()) {
            UUID uuid = UUID.randomUUID();
            Main.iterations.put(uuid, 0);
            Main.updateNearbyBlocks(event.getBlock(), false, uuid);
        }
    }
    @EventHandler
    public void onPistonExtend(BlockPistonExtendEvent event) {
        if(!event.isCancelled()) {
            UUID uuid = UUID.randomUUID();
            Main.iterations.put(uuid, 0);
            Main.updateNearbyBlocks(event.getBlock(), true, uuid);
        }
    }
    @EventHandler
    public void onPistonRetract(BlockPistonRetractEvent event) {
        if(!event.isCancelled()) {
            UUID uuid = UUID.randomUUID();
            Main.iterations.put(uuid, 0);
            Main.updateNearbyBlocks(event.getBlock(), true, uuid);
        }
    }
}
