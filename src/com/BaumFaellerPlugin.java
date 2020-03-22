package com;

import com.helpers.InventoryHelp;
import com.helpers.ToolType;
import com.helpers.TreeHelpers;
import com.wrapers.TreeWrapper;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class BaumFaellerPlugin extends JavaPlugin implements Listener {

    private FileConfiguration customConfig;
    private static ConsoleCommandSender console;
    private static final String prefix = "[PLUGIN][Baumfaeller]";

    public BaumFaellerPlugin() {

    }

    @Override
    public void onEnable() {
        console = getServer().getConsoleSender();
        sendMessage("Trying to enable Plugin");
        createCustomConfig();

        TreeCutting.init(this);

        getServer().getPluginManager().registerEvents(this, this);
        sendMessage("Plugin enabled");
    }

    private void createCustomConfig() {
        File customConfigFile = new File(getDataFolder(), "custom.yml");
        if (!customConfigFile.exists()) {
            customConfigFile.getParentFile().mkdirs();
            saveResource("custom.yml", false);
        }
        customConfig = new YamlConfiguration();
        try {
            customConfig.load(customConfigFile);
        } catch (InvalidConfigurationException | IOException e) {
            e.printStackTrace();
        }
    }

    public FileConfiguration getCustomConfig() {

        return this.customConfig;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        ItemStack heldItem = player.getInventory().getItemInMainHand();
        Material heldItemMat = heldItem.getType();

        Block startBlock = event.getBlock();

        if (InventoryHelp.isTool(heldItemMat, ToolType.AXE) && TreeHelpers.isLog(startBlock.getType())) {
            event.setCancelled(true);
            TreeWrapper wrapper = TreeCutting.cutTreeV2(startBlock, heldItem);
            wrapper.spawnDrops(startBlock.getLocation(), getServer().getWorld("world"), console);
        }

    }

    @Override
    public void onDisable() {
        sendMessage("Plugin disabled");
    }

    public static void sendMessage(String m) {
        console.sendMessage(prefix + " " + m);
    }

}
