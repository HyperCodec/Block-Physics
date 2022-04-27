package me.hypercodec.physics;

import com.jeff_media.customblockdata.CustomBlockData;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Snowable;
import org.bukkit.entity.FallingBlock;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

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
        stableblocks.add(Material.COMMAND_BLOCK);
        stableblocks.add(Material.CHAIN_COMMAND_BLOCK);
        stableblocks.add(Material.REPEATING_COMMAND_BLOCK);
        stableblocks.add(Material.PISTON_HEAD);
        stableblocks.add(Material.MOVING_PISTON);
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
        stableblocks.add(Material.SCAFFOLDING);
        stableblocks.add(Material.END_PORTAL_FRAME);
        stableblocks.add(Material.END_PORTAL);
        stableblocks.add(Material.END_GATEWAY);
        stableblocks.add(Material.SPAWNER);
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

        unstableblocks.add(Material.COBWEB);
        unstableblocks.add(Material.FIRE);
        unstableblocks.add(Material.RAIL);
        unstableblocks.add(Material.ACTIVATOR_RAIL);
        unstableblocks.add(Material.DETECTOR_RAIL);
        unstableblocks.add(Material.POWERED_RAIL);
        unstableblocks.add(Material.CHAIN);
        unstableblocks.add(Material.FLOWER_POT);
        unstableblocks.add(Material.FLOWERING_AZALEA);
        unstableblocks.add(Material.AZALEA);
        unstableblocks.add(Material.BLACK_CARPET);
        unstableblocks.add(Material.RED_CARPET);
        unstableblocks.add(Material.BLUE_CARPET);
        unstableblocks.add(Material.CYAN_CARPET);
        unstableblocks.add(Material.BROWN_CARPET);
        unstableblocks.add(Material.GRAY_CARPET);
        unstableblocks.add(Material.GREEN_CARPET);
        unstableblocks.add(Material.LIGHT_BLUE_CARPET);
        unstableblocks.add(Material.LIGHT_GRAY_CARPET);
        unstableblocks.add(Material.LIME_CARPET);
        unstableblocks.add(Material.MAGENTA_CARPET);
        unstableblocks.add(Material.MOSS_CARPET);
        unstableblocks.add(Material.ORANGE_CARPET);
        unstableblocks.add(Material.PINK_CARPET);
        unstableblocks.add(Material.PURPLE_CARPET);
        unstableblocks.add(Material.WHITE_CARPET);
        unstableblocks.add(Material.YELLOW_CARPET);
        unstableblocks.add(Material.KELP);
        unstableblocks.add(Material.ALLIUM);
        unstableblocks.add(Material.KELP_PLANT);
        unstableblocks.add(Material.SEAGRASS);
        unstableblocks.add(Material.TALL_SEAGRASS);
        unstableblocks.add(Material.GRASS);
        unstableblocks.add(Material.TALL_GRASS);
        unstableblocks.add(Material.SEA_PICKLE);
        unstableblocks.add(Material.CAKE);
        unstableblocks.add(Material.BAMBOO_SAPLING);
        unstableblocks.add(Material.BAMBOO);
        unstableblocks.add(Material.SUGAR_CANE);
        unstableblocks.add(Material.BLACK_BANNER);
        unstableblocks.add(Material.BLACK_WALL_BANNER);
        unstableblocks.add(Material.BLUE_BANNER);
        unstableblocks.add(Material.BROWN_BANNER);
        unstableblocks.add(Material.BLUE_WALL_BANNER);
        unstableblocks.add(Material.BROWN_WALL_BANNER);
        unstableblocks.add(Material.CYAN_BANNER);
        unstableblocks.add(Material.CYAN_WALL_BANNER);
        unstableblocks.add(Material.GRAY_BANNER);
        unstableblocks.add(Material.GRAY_WALL_BANNER);
        unstableblocks.add(Material.GREEN_BANNER);
        unstableblocks.add(Material.GREEN_WALL_BANNER);
        unstableblocks.add(Material.LIGHT_BLUE_BANNER);
        unstableblocks.add(Material.LIGHT_BLUE_WALL_BANNER);
        unstableblocks.add(Material.LIGHT_GRAY_BANNER);
        unstableblocks.add(Material.LIGHT_GRAY_WALL_BANNER);
        unstableblocks.add(Material.LIME_BANNER);
        unstableblocks.add(Material.LIME_WALL_BANNER);
        unstableblocks.add(Material.MAGENTA_BANNER);
        unstableblocks.add(Material.MAGENTA_WALL_BANNER);
        unstableblocks.add(Material.ORANGE_BANNER);
        unstableblocks.add(Material.ORANGE_WALL_BANNER);
        unstableblocks.add(Material.PINK_BANNER);
        unstableblocks.add(Material.PINK_WALL_BANNER);
        unstableblocks.add(Material.PURPLE_BANNER);
        unstableblocks.add(Material.PURPLE_WALL_BANNER);
        unstableblocks.add(Material.RED_BANNER);
        unstableblocks.add(Material.RED_WALL_BANNER);
        unstableblocks.add(Material.WHITE_BANNER);
        unstableblocks.add(Material.WHITE_WALL_BANNER);
        unstableblocks.add(Material.YELLOW_BANNER);
        unstableblocks.add(Material.YELLOW_WALL_BANNER);
        unstableblocks.add(Material.CACTUS);
        unstableblocks.add(Material.LEVER);
        unstableblocks.add(Material.SNOW);
        unstableblocks.add(Material.RED_TULIP);
        unstableblocks.add(Material.ORANGE_TULIP);
        unstableblocks.add(Material.PINK_TULIP);
        unstableblocks.add(Material.END_ROD);
        unstableblocks.add(Material.CHORUS_FLOWER);
        unstableblocks.add(Material.CHORUS_FRUIT);
        unstableblocks.add(Material.CHORUS_PLANT);
        unstableblocks.add(Material.AIR);
        unstableblocks.add(Material.WATER);
        unstableblocks.add(Material.LAVA);
        unstableblocks.add(Material.CAVE_AIR);
        unstableblocks.add(Material.POINTED_DRIPSTONE);
        unstableblocks.add(Material.CAVE_VINES_PLANT);
        unstableblocks.add(Material.GLOW_LICHEN);
        unstableblocks.add(Material.SPORE_BLOSSOM);
        unstableblocks.add(Material.HANGING_ROOTS);
        unstableblocks.add(Material.BRAIN_CORAL);
        unstableblocks.add(Material.TUBE_CORAL);
        unstableblocks.add(Material.BUBBLE_CORAL);
        unstableblocks.add(Material.DEAD_BRAIN_CORAL);
        unstableblocks.add(Material.DEAD_FIRE_CORAL);
        unstableblocks.add(Material.FIRE_CORAL);
        unstableblocks.add(Material.HORN_CORAL);
        unstableblocks.add(Material.DEAD_HORN_CORAL);
        unstableblocks.add(Material.DEAD_TUBE_CORAL);
        unstableblocks.add(Material.DEAD_BUBBLE_CORAL);
        unstableblocks.add(Material.BRAIN_CORAL_FAN);
        unstableblocks.add(Material.TUBE_CORAL_FAN);
        unstableblocks.add(Material.BUBBLE_CORAL_FAN);
        unstableblocks.add(Material.DEAD_BRAIN_CORAL_FAN);
        unstableblocks.add(Material.DEAD_FIRE_CORAL_FAN);
        unstableblocks.add(Material.FIRE_CORAL_FAN);
        unstableblocks.add(Material.HORN_CORAL_FAN);
        unstableblocks.add(Material.DEAD_HORN_CORAL_FAN);
        unstableblocks.add(Material.DEAD_TUBE_CORAL_FAN);
        unstableblocks.add(Material.DEAD_BUBBLE_CORAL_FAN);
        unstableblocks.add(Material.BRAIN_CORAL_WALL_FAN);
        unstableblocks.add(Material.TUBE_CORAL_WALL_FAN);
        unstableblocks.add(Material.BUBBLE_CORAL_WALL_FAN);
        unstableblocks.add(Material.DEAD_BRAIN_CORAL_WALL_FAN);
        unstableblocks.add(Material.DEAD_FIRE_CORAL_WALL_FAN);
        unstableblocks.add(Material.FIRE_CORAL_WALL_FAN);
        unstableblocks.add(Material.HORN_CORAL_WALL_FAN);
        unstableblocks.add(Material.DEAD_HORN_CORAL_WALL_FAN);
        unstableblocks.add(Material.DEAD_TUBE_CORAL_WALL_FAN);
        unstableblocks.add(Material.DEAD_BUBBLE_CORAL_WALL_FAN);
        unstableblocks.add(Material.VINE);
        unstableblocks.add(Material.DEAD_BUSH);

        this.getLogger().info("Block Physics v1.4 loaded");
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
                    if (!unstableblocks.contains(nblock.getType()) && unstableblocks.contains(nblock.getWorld().getBlockAt(nblock.getX(), nblock.getY() - 1, nblock.getZ()).getType()) && !stableblocks.contains(nblock.getType()) && !new CustomBlockData(nblock, plugin).has(new NamespacedKey(plugin, "ignorephysics"), PersistentDataType.INTEGER)) {
                        iterations.put(uuid, iterations.get(uuid) + 1);
                        if(plugin.getConfig().getInt("maxaffectedblocks") != 0 && iterations.get(uuid) > plugin.getConfig().getInt("maxaffectedblocks")) {
                            return;
                        }
                        BlockData data = nblock.getBlockData();

                        if(data instanceof Snowable) {
                            ((Snowable) data).setSnowy(false);
                        }

                        if(data.getMaterial() == Material.POWDER_SNOW) {
                            data = Material.SNOW_BLOCK.createBlockData();
                        }

                        nblock.setType(Material.AIR);

                        FallingBlock fblock = nblock.getWorld().spawnFallingBlock(nblock.getLocation().add(0.5, 0.5, 0.5), data);
                        fblock.getPersistentDataContainer().set(new NamespacedKey(plugin, "eventid"), PersistentDataType.STRING, uuid.toString());
                        if(plugin.getConfig().getBoolean("chainupdates")) {
                            updateNearbyBlocks(nblock, false, uuid);
                        }
                    }
                }
            }
        }.runTaskLater(plugin, 1);
    }
}
