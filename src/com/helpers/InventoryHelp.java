package com.helpers;


import org.bukkit.Material;

public class InventoryHelp {

    public static boolean isTool(Material toCheck, ToolType type) {
        switch (type) {
            case AXE:
                if (toCheck == Material.WOODEN_AXE) return true;
                if (toCheck == Material.STONE_AXE) return true;
                if (toCheck == Material.IRON_AXE) return true;
                if (toCheck == Material.GOLDEN_AXE) return true;
                if (toCheck == Material.DIAMOND_AXE) return true;
                break;
            case HOE:
                if (toCheck == Material.WOODEN_HOE) return true;
                if (toCheck == Material.STONE_HOE) return true;
                if (toCheck == Material.IRON_HOE) return true;
                if (toCheck == Material.GOLDEN_HOE) return true;
                if (toCheck == Material.DIAMOND_HOE) return true;
                break;
            case SWORD:
                if (toCheck == Material.WOODEN_SWORD) return true;
                if (toCheck == Material.STONE_SWORD) return true;
                if (toCheck == Material.IRON_SWORD) return true;
                if (toCheck == Material.GOLDEN_SWORD) return true;
                if (toCheck == Material.DIAMOND_SWORD) return true;
                break;
            case PCIKAXE:
                if (toCheck == Material.WOODEN_PICKAXE) return true;
                if (toCheck == Material.STONE_PICKAXE) return true;
                if (toCheck == Material.IRON_PICKAXE) return true;
                if (toCheck == Material.GOLDEN_PICKAXE) return true;
                if (toCheck == Material.DIAMOND_PICKAXE) return true;
                break;
            case SHOVEL:
                if (toCheck == Material.WOODEN_SHOVEL) return true;
                if (toCheck == Material.STONE_SHOVEL) return true;
                if (toCheck == Material.IRON_SHOVEL) return true;
                if (toCheck == Material.GOLDEN_SHOVEL) return true;
                if (toCheck == Material.DIAMOND_SHOVEL) return true;
                break;
            case ALL:
                if (isTool(toCheck, ToolType.AXE)) return true;
                if (isTool(toCheck, ToolType.PCIKAXE)) return true;
                if (isTool(toCheck, ToolType.SHOVEL)) return true;
                if (isTool(toCheck, ToolType.SWORD)) return true;
                if (isTool(toCheck, ToolType.HOE)) return true;
                break;
            default:
                break;
        }
        return false;
    }

}
