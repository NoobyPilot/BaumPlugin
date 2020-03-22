package com.helpers;

import org.bukkit.Material;

public class TreeHelpers {

    public static boolean isTreeType(Material material, TreeType treeType) {
        switch (treeType) {
            case OAK:
                return ((material == Material.OAK_LOG) || (material == Material.OAK_LEAVES));
            case BIRCH:
                return ((material == Material.BIRCH_LOG) || (material == Material.BIRCH_LEAVES));
            case ACACIA:
                return ((material == Material.ACACIA_LOG) || (material == Material.ACACIA_LEAVES));
            case DARK_OAK:
                return ((material == Material.DARK_OAK_LOG) || (material == Material.DARK_OAK_LEAVES));
            case JUNGLE:
                return ((material == Material.JUNGLE_LOG) || (material == Material.JUNGLE_LEAVES));
            case SPRUCE:
                return ((material == Material.SPRUCE_LOG) || (material == Material.SPRUCE_LEAVES));
        }
        return false;
    }

    public static boolean isLog(Material material) {
        if (material == Material.OAK_LOG)
            return true;
        if (material == Material.BIRCH_LOG)
            return true;
        if (material == Material.ACACIA_LOG)
            return true;
        if (material == Material.DARK_OAK_LOG)
            return true;
        if (material == Material.JUNGLE_LOG)
            return true;
        return material == Material.SPRUCE_LOG;
    }

}
