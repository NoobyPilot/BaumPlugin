package com;

import com.helpers.TreeType;
import com.helpers.TreeHelpers;
import com.wrapers.TreeWrapper;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

import static org.bukkit.Material.JUNGLE_LOG;

public class TreeCutting {

    private static int OAK_SMALL = 2, OAK_LARGE = 6, BIRCH_SMALL = 2, ACACIA_SMALL = 3;
    private static int DARK_OAK_LARGE = 3, JUNGLE_SMALL = 2, JUNGLE_LARGE = 7, SPRUCE_SMALL = 3;

    public static void init(BaumFaellerPlugin plugin) {
        FileConfiguration config = plugin.getCustomConfig();
        if (config != null) {
            OAK_SMALL = config.getInt("OAK_SMALL");
            OAK_LARGE = config.getInt("OAK_LARGE");
            BIRCH_SMALL = config.getInt("BIRCH_SMALL");
            ACACIA_SMALL = config.getInt("ACACIA_SMALL");
            DARK_OAK_LARGE = config.getInt("DARK_OAK_LARGE");
            JUNGLE_SMALL = config.getInt("JUNGLE_SMALL");
            JUNGLE_LARGE = config.getInt("JUNGLE_LARGE");
            SPRUCE_SMALL = config.getInt("SPRUCE_SMALL");
        }
    }

    public static TreeWrapper cutTreeV2(Block start, ItemStack handItem) {
        TreeWrapper wrapper = new TreeWrapper();
        Material mat = start.getType();
        Location l = start.getLocation();
        boolean run;
        do {
            l.add(0, -1, 0);
            run = mat == l.getBlock().getType();
        } while (run);
        l.add(0, 1, 0);
        start = l.getBlock();
        switch (mat) {
            case OAK_LOG:
                l = start.getLocation();
                l.add(0, 7, 0);
                if (TreeHelpers.isTreeType(l.getBlock().getType(),TreeType.OAK)) {
                    cutTreeV2(start, 0, OAK_LARGE, TreeType.OAK, handItem, wrapper);
                } else {
                    cutTreeV2(start, 0, OAK_SMALL, TreeType.OAK, handItem, wrapper);
                }
                break;
            case BIRCH_LOG:
                cutTreeV2(start, 0, BIRCH_SMALL, TreeType.BIRCH, handItem, wrapper);
                break;
            case ACACIA_LOG:
                cutTreeV2(start, 0, ACACIA_SMALL, TreeType.ACACIA, handItem, wrapper);
                break;
            case DARK_OAK_LOG:
                cutTreeV2(start, 0, DARK_OAK_LARGE, TreeType.DARK_OAK, handItem, wrapper);
                break;
            case JUNGLE_LOG:
                l = start.getLocation();
                boolean large = false;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (i == 1 && j == 1)
                            continue;
                        Location t = l.clone();
                        l.add(i-1, 0, j-1);
                        if (l.getBlock().getType() == JUNGLE_LOG)
                            large = true;
                    }
                }
                if (large) {
                    cutTreeV2(start, 0, JUNGLE_LARGE, TreeType.JUNGLE, handItem, wrapper);
                } else {
                    cutTreeV2(start, 0, JUNGLE_SMALL, TreeType.JUNGLE, handItem, wrapper);
                }
                break;
            case SPRUCE_LOG:
                cutTreeV2(start, 0, SPRUCE_SMALL, TreeType.SPRUCE, handItem, wrapper);
        }
        return wrapper;
    }

    private static void cutTreeV2(Block block, int curLevel, int maxLevel, TreeType type, ItemStack handItem, TreeWrapper wrapper) {
        if (curLevel > maxLevel)
            return;
        if (TreeHelpers.isLog(block.getType()))
            curLevel = 0;
        Location blockLoc = block.getLocation();
        for (int x = 0; x < 3; x++) {
            for (int z = 0; z < 3; z++) {
                Location test = blockLoc.clone();
                test.add(x-1, -1, z-1);
                if (TreeHelpers.isLog(test.getBlock().getType()))
                    return;
            }
        }
        wrapper.addDrop(block.getDrops());
        block.setType(Material.AIR);
        if (curLevel == maxLevel)
            return;
        for (int y = 0; y < 2; y++) {
            for (int x = 0; x < 3; x++) {
                for (int z = 0; z < 3; z++) {
                    if (x == 1 && y == 0 && z == 1)
                        continue;
                    Location temp = blockLoc.clone();
                    temp.add(-1 + x, y, -1 + z);
                    Block tempBlock = temp.getBlock();
                    Material tempMat = tempBlock.getType();
                    if (TreeHelpers.isTreeType(tempMat, type)) {
                        cutTreeV2(tempBlock, curLevel+1, maxLevel, type, handItem, wrapper);
                    }
                }
            }
        }
    }

}
