package me.hypercodec.physics;

import com.jeff_media.customblockdata.CustomBlockData;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Snowable;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityDropItemEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.UUID;

public class BlockPhysicsListener implements Listener {
    @EventHandler(ignoreCancelled = true)
    public void onBlockPlace(BlockPlaceEvent event) {
        UUID uuid = UUID.randomUUID();
        Main.iterations.put(uuid, 0);

        if (Main.plugin.getConfig().getInt("maxaffectedblocks") != 0) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    Main.iterations.remove(uuid);
                    this.cancel();
                }
            }.runTaskLater(Main.plugin, Main.plugin.getConfig().getInt("maxaffectedblocks") + 20);
        }
        if (!event.getPlayer().isSneaking() || !Main.plugin.getConfig().getBoolean("shiftignorephysics") && event.getPlayer().hasPermission("blockphysics.shiftclick")) {
            Main.updateBlock(event.getBlock(), true, uuid);
            return;
        }

        new CustomBlockData(event.getBlock(), Main.plugin).set(Main.ignorephysicskey, PersistentDataType.INTEGER, 1);
        Main.updateBlock(event.getBlock(), false, uuid);
    }

    @EventHandler(ignoreCancelled = true)
    public void onBlockBreak(BlockBreakEvent event) {
        if(new CustomBlockData(event.getBlock(), Main.plugin).has(Main.ignorephysicskey, PersistentDataType.INTEGER)) {new CustomBlockData(event.getBlock(), Main.plugin).remove(Main.ignorephysicskey);}

        UUID uuid = UUID.randomUUID();
        Main.iterations.put(uuid, 0);
        if (Main.plugin.getConfig().getInt("maxaffectedblocks") != 0) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    Main.iterations.remove(uuid);
                    this.cancel();
                }
            }.runTaskLater(Main.plugin, Main.plugin.getConfig().getInt("maxaffectedblocks") + 20);
        }
        Main.updateBlock(event.getBlock(), false, uuid);
    }

    @EventHandler(ignoreCancelled = true)
    public void onEntityChangeBlock(EntityChangeBlockEvent event) {
        if(event.getEntity() instanceof FallingBlock) {
            if(event.getEntity().getPersistentDataContainer().has(Main.eventidkey, PersistentDataType.STRING) && Main.plugin.getConfig().getBoolean("fallingblocksupdate") && Main.plugin.getConfig().getBoolean("chainupdates")) {
                UUID uuid = UUID.fromString(event.getEntity().getPersistentDataContainer().get(Main.eventidkey, PersistentDataType.STRING));
                if(Main.plugin.getConfig().getInt("maxaffectedblocks") != 0) {
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            Main.iterations.remove(uuid);
                            this.cancel();
                        }
                    }.runTaskLater(Main.plugin, Main.plugin.getConfig().getInt("maxaffectedblocks") + 20);
                }
                Main.updateBlock(event.getBlock(), false, uuid);
            }
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onEntityExplode(EntityExplodeEvent event) {
        if(Main.plugin.getConfig().getBoolean("realisticexplosions")) {
            UUID uuid = UUID.randomUUID();
            Main.iterations.put(uuid, 0);

            for(Block block : event.blockList()) {
                if(!Main.unstableblocks.contains(block.getType()) && !Main.stableblocks.contains(block.getType()) && block.getType() != Material.TNT) {
                    Vector yeetvec = event.getLocation().toVector().subtract(block.getLocation().toVector()).multiply(10).normalize();

                    BlockData data = block.getBlockData();

                    if(data instanceof Snowable) {
                        ((Snowable) data).setSnowy(false);
                    }

                    if(data.getMaterial() == Material.POWDER_SNOW) {
                        data = Material.SNOW_BLOCK.createBlockData();
                    }

                    block.setType(Material.AIR);

                    FallingBlock fb = event.getLocation().getWorld().spawnFallingBlock(block.getLocation(), data);
                    fb.getPersistentDataContainer().set(Main.eventidkey, PersistentDataType.STRING, uuid.toString());
                    fb.setHurtEntities(true);
                    fb.setVelocity(yeetvec);

                    Main.iterations.put(uuid, Main.iterations.get(uuid) + 1);

                    if(Main.plugin.getConfig().getBoolean("explosionupdates")) {Main.updateBlock(block, false, uuid);}
                }
            }
        }
    }

    @EventHandler
    public void onBlockExplode(BlockExplodeEvent event) {if(Main.plugin.getConfig().getBoolean("realisticexplosions")) {event.setCancelled(true);}}

    @EventHandler(ignoreCancelled = true)
    public void onEntityDropItem(EntityDropItemEvent event) {
        if(event.getEntity().getType() == EntityType.FALLING_BLOCK && !Main.plugin.getConfig().getBoolean("unsolidblocksbreakfbs")) {
            try {
                event.setCancelled(true);
                event.getEntity().getLocation().getWorld().dropItemNaturally(event.getEntity().getLocation(), new ItemStack(event.getEntity().getLocation().getBlock().getLocation().getBlock().getType()));
                event.getEntity().getLocation().getBlock().getLocation().getBlock().setBlockData(((FallingBlock) event.getEntity()).getBlockData());
                event.getEntity().remove();
            }
            catch(IllegalArgumentException ignored) {}
        }
    }
}
