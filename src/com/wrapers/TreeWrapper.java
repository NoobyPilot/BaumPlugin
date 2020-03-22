package com.wrapers;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.HashMap;

public class TreeWrapper {

    private HashMap<String, ItemStack> drops;

    public void spawnDrops(Location location, World world, ConsoleCommandSender console) {
        if (drops == null||drops.isEmpty())
            return;
        for (ItemStack stack:drops.values()) {
            world.dropItem(location, stack);
        }
    }

    public void addDrop(Collection<ItemStack> drop) {
        if (drops == null) {
            drops = new HashMap<>();
        }
        for (ItemStack stack:drop) {
            String name = stack.getType().name();
            if (drops.containsKey(name)) {
                ItemStack temp = drops.get(name);
                temp.setAmount(temp.getAmount() + stack.getAmount());
                drops.remove(name);
                drops.put(name, temp);
            } else {
                drops.put(name, stack);
            }
        }
    }

}
