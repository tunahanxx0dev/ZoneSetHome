package dev.tunahanxx.files;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class SettingsFile {

    private static File onSettingsFile;
    private static FileConfiguration onSettingsFileConfig;

    public static void onSettingsFileSetup() {
        onSettingsFile = new File(Bukkit.getServer().getPluginManager().getPlugin("ZoneSetHome").getDataFolder(), "settings.yml");

        if (!onSettingsFile.exists()) {
            try {
                Bukkit.getServer().getPluginManager().getPlugin("ZoneSetHome").getDataFolder().mkdirs();
                onSettingsFile.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        onSettingsFileConfig = YamlConfiguration.loadConfiguration(onSettingsFile);
    }

    public static FileConfiguration onSettingsFileGet() {
        return onSettingsFileConfig;
    }

    public static void onSettingsFileSave() {
        try {
            onSettingsFileConfig.save(onSettingsFile);
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public static void onSettingsFileReload() {
        onSettingsFileConfig = YamlConfiguration.loadConfiguration(onSettingsFile);
    }
}