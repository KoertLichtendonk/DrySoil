package com.koertlichtendonk.spigot.drysoil;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class ConfigManager {

    private JavaPlugin plugin;
    private List<Material> blocksThatCantBeBrokenWithAHoe;
    public ConfigManager(JavaPlugin plugin) {
        this.plugin = plugin;
        loadConfig();
    }

    public void loadConfig() {
        // Load the default configuration values
        plugin.saveDefaultConfig();

        // Read the list of allowed materials from the config
        blocksThatCantBeBrokenWithAHoe = new ArrayList<>();
        FileConfiguration config = plugin.getConfig();
        List<String> materialStrings = config.getStringList("blocks-that-cant-be-broken-with-a-hoe");

        // Convert the strings into Material objects
        for (String materialString : materialStrings) {
            Material material = Material.matchMaterial(materialString);
            if (material != null) {
                blocksThatCantBeBrokenWithAHoe.add(material);
            } else {
                plugin.getLogger().warning("Invalid material: " + materialString);
            }
        }
    }

    public List<Material> getBlocksThatCantBeBrokenWithAHoe() {
        return blocksThatCantBeBrokenWithAHoe;
    }

    public void saveConfig() {
        plugin.saveConfig();
    }
}
