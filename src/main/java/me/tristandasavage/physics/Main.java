package me.tristandasavage.physics;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.FallingBlock;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;
import java.util.logging.Level;

public class Main extends JavaPlugin {
    public static Main plugin;

    public static Set<Material> stableblocks = new HashSet<>();
    public static Set<Material> unstableblocks = new HashSet<>();

    public static Map<UUID, Integer> iterations = new HashMap<>();

    @Override
    public void onDisable() {
        this.getLogger().info("Block Physics plugin unloaded");
    }

    @Override
    public void onEnable() {
        plugin = this;

        this.getServer().getPluginManager().registerEvents(new Listeners(), this);

        this.saveDefaultConfig();

        stableblocks.add(Material.BEDROCK);
        stableblocks.add(Material.BARRIER);
        stableblocks.add(Material.OBSIDIAN);
        stableblocks.add(Material.CRYING_OBSIDIAN);
        stableblocks.add(Material.CHEST);
        stableblocks.add(Material.FURNACE);
        stableblocks.add(Material.SMOKER);
        stableblocks.add(Material.BLAST_FURNACE);
        stableblocks.add(Material.WALL_TORCH);
        stableblocks.add(Material.SOUL_WALL_TORCH);
        stableblocks.add(Material.REDSTONE_WALL_TORCH);
        stableblocks.add(Material.SHULKER_BOX);
        stableblocks.add(Material.HOPPER);
        stableblocks.add(Material.BREWING_STAND);
        stableblocks.add(Material.DROPPER);
        stableblocks.add(Material.TRAPPED_CHEST);
        stableblocks.add(Material.DISPENSER);
        stableblocks.add(Material.BARREL);
        stableblocks.add(Material.CAMPFIRE);
        stableblocks.add(Material.SOUL_CAMPFIRE);
        stableblocks.add(Material.LECTERN);
        stableblocks.add(Material.GLOW_LICHEN);
        stableblocks.add(Material.VINE);
        stableblocks.add(Material.COBWEB);
        stableblocks.add(Material.RAIL);
        stableblocks.add(Material.ACTIVATOR_RAIL);
        stableblocks.add(Material.DETECTOR_RAIL);
        stableblocks.add(Material.POWERED_RAIL);
        stableblocks.add(Material.CHAIN);
        stableblocks.add(Material.POINTED_DRIPSTONE);
        stableblocks.add(Material.FLOWER_POT);
        stableblocks.add(Material.FLOWERING_AZALEA);
        stableblocks.add(Material.AZALEA);
        stableblocks.add(Material.BLACK_CARPET);
        stableblocks.add(Material.RED_CARPET);
        stableblocks.add(Material.BLUE_CARPET);
        stableblocks.add(Material.CYAN_CARPET);
        stableblocks.add(Material.BROWN_CARPET);
        stableblocks.add(Material.GRAY_CARPET);
        stableblocks.add(Material.GREEN_CARPET);
        stableblocks.add(Material.LIGHT_BLUE_CARPET);
        stableblocks.add(Material.LIGHT_GRAY_CARPET);
        stableblocks.add(Material.LIME_CARPET);
        stableblocks.add(Material.MAGENTA_CARPET);
        stableblocks.add(Material.MOSS_CARPET);
        stableblocks.add(Material.ORANGE_CARPET);
        stableblocks.add(Material.PINK_CARPET);
        stableblocks.add(Material.PURPLE_CARPET);
        stableblocks.add(Material.WHITE_CARPET);
        stableblocks.add(Material.YELLOW_CARPET);
        stableblocks.add(Material.KELP_PLANT);
        stableblocks.add(Material.SEAGRASS);
        stableblocks.add(Material.SEA_PICKLE);
        stableblocks.add(Material.CAKE);
        stableblocks.add(Material.BAMBOO_SAPLING);
        stableblocks.add(Material.BAMBOO);
        stableblocks.add(Material.SUGAR_CANE);
        stableblocks.add(Material.BLACK_BANNER);
        stableblocks.add(Material.BLACK_WALL_BANNER);
        stableblocks.add(Material.BLUE_BANNER);
        stableblocks.add(Material.BROWN_BANNER);
        stableblocks.add(Material.BLUE_WALL_BANNER);
        stableblocks.add(Material.BROWN_WALL_BANNER);
        stableblocks.add(Material.CYAN_BANNER);
        stableblocks.add(Material.CYAN_WALL_BANNER);
        stableblocks.add(Material.GRAY_BANNER);
        stableblocks.add(Material.GRAY_WALL_BANNER);
        stableblocks.add(Material.GREEN_BANNER);
        stableblocks.add(Material.GREEN_WALL_BANNER);
        stableblocks.add(Material.LIGHT_BLUE_BANNER);
        stableblocks.add(Material.LIGHT_BLUE_WALL_BANNER);
        stableblocks.add(Material.LIGHT_GRAY_BANNER);
        stableblocks.add(Material.LIGHT_GRAY_WALL_BANNER);
        stableblocks.add(Material.LIME_BANNER);
        stableblocks.add(Material.LIME_WALL_BANNER);
        stableblocks.add(Material.MAGENTA_BANNER);
        stableblocks.add(Material.MAGENTA_WALL_BANNER);
        stableblocks.add(Material.ORANGE_BANNER);
        stableblocks.add(Material.ORANGE_WALL_BANNER);
        stableblocks.add(Material.PINK_BANNER);
        stableblocks.add(Material.PINK_WALL_BANNER);
        stableblocks.add(Material.PURPLE_BANNER);
        stableblocks.add(Material.PURPLE_WALL_BANNER);
        stableblocks.add(Material.RED_BANNER);
        stableblocks.add(Material.RED_WALL_BANNER);
        stableblocks.add(Material.WHITE_BANNER);
        stableblocks.add(Material.WHITE_WALL_BANNER);
        stableblocks.add(Material.YELLOW_BANNER);
        stableblocks.add(Material.YELLOW_WALL_BANNER);
        stableblocks.add(Material.BLACK_BED);
        stableblocks.add(Material.BLUE_BED);
        stableblocks.add(Material.BROWN_BED);
        stableblocks.add(Material.CYAN_BED);
        stableblocks.add(Material.GRAY_BED);
        stableblocks.add(Material.GREEN_BED);
        stableblocks.add(Material.LIGHT_BLUE_BED);
        stableblocks.add(Material.LIGHT_GRAY_BED);
        stableblocks.add(Material.LIME_BED);
        stableblocks.add(Material.MAGENTA_BED);
        stableblocks.add(Material.ORANGE_BED);
        stableblocks.add(Material.PINK_BED);
        stableblocks.add(Material.PURPLE_BED);
        stableblocks.add(Material.WHITE_BED);
        stableblocks.add(Material.RED_BED);
        stableblocks.add(Material.YELLOW_BED);
        stableblocks.add(Material.CACTUS);
        stableblocks.add(Material.SCAFFOLDING);
        stableblocks.add(Material.LEVER);
        stableblocks.add(Material.SNOW);
        stableblocks.add(Material.END_PORTAL_FRAME);
        stableblocks.add(Material.END_PORTAL);
        stableblocks.add(Material.END_GATEWAY);
        stableblocks.add(Material.END_ROD);
        stableblocks.add(Material.CHORUS_FLOWER);
        stableblocks.add(Material.CHORUS_FRUIT);
        stableblocks.add(Material.CHORUS_PLANT);

        unstableblocks.add(Material.AIR);
        unstableblocks.add(Material.WATER);
        unstableblocks.add(Material.LAVA);
        unstableblocks.add(Material.CAVE_AIR);

        this.getLogger().info("Block Physics v1.2 loaded");
    }
    public static void updateNearbyBlocks(Block block, boolean includeself, UUID uuid) {
        new BukkitRunnable() {
            @Override
            public void run() {
                Set<Location> affectedblocks = new HashSet<>();
                if(includeself) {
                    affectedblocks.add(block.getLocation());
                }
                affectedblocks.add(block.getLocation().subtract(1, 0, 0));
                affectedblocks.add(block.getLocation().subtract(1, 0, 1));
                affectedblocks.add(block.getLocation().subtract(0, 0, 1));
                affectedblocks.add(block.getLocation().subtract(1, 1, 0));
                affectedblocks.add(block.getLocation().subtract(0, 1, 1));
                affectedblocks.add(block.getLocation().subtract(1, 1, 1));
                affectedblocks.add(block.getLocation().add(1, 0, 0));
                affectedblocks.add(block.getLocation().add(1, 0, 1));
                affectedblocks.add(block.getLocation().add(0, 0, 1));
                affectedblocks.add(block.getLocation().add(0, 1, 0));
                affectedblocks.add(block.getLocation().add(1, 1, 0));
                affectedblocks.add(block.getLocation().add(0, 1, 1));
                affectedblocks.add(block.getLocation().add(1, 1, 1));
                affectedblocks.add(block.getLocation().add(0, -1, 1));
                affectedblocks.add(block.getLocation().add(1, -1, 0));

                for(Location nlocation : affectedblocks) {
                    Block nblock = nlocation.getWorld().getBlockAt(nlocation);
                    if (!Main.unstableblocks.contains(nblock.getType()) && Main.unstableblocks.contains(nblock.getWorld().getBlockAt(nblock.getX(), nblock.getY() - 1, nblock.getZ()).getType()) && !Main.stableblocks.contains(nblock.getType())) {
                        iterations.put(uuid, iterations.get(uuid) + 1);
                        if(plugin.getConfig().getInt("maxaffectedblocks") != 0 && iterations.get(uuid) > plugin.getConfig().getInt("maxaffectedblocks")) {
                            return;
                        }
                        BlockData data = nblock.getBlockData();
                        nblock.setType(Material.AIR);
                        FallingBlock fblock = nblock.getWorld().spawnFallingBlock(nblock.getLocation().add(0.5, 0.5, 0.5), data);
                        fblock.getPersistentDataContainer().set(new NamespacedKey(plugin, "eventid"), PersistentDataType.STRING, uuid.toString());
                        updateNearbyBlocks(nblock, false, uuid);
                    }
                }
            }
        }.runTaskLater(plugin, 1);
    }
}
