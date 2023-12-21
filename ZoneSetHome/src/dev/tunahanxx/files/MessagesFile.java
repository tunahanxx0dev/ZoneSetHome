package dev.tunahanxx.files;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class MessagesFile {

    private static File onMessagesFile;
    private static FileConfiguration onMessagesFileConfig;

    public static void onMessagesFileSetup() {
        onMessagesFile = new File(Bukkit.getServer().getPluginManager().getPlugin("ZoneSetHome").getDataFolder(), "messages.yml");

        if (!onMessagesFile.exists()) {
            try {
                Bukkit.getServer().getPluginManager().getPlugin("ZoneSetHome").getDataFolder().mkdirs();
                onMessagesFile.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        onMessagesFileConfig = YamlConfiguration.loadConfiguration(onMessagesFile);
    }

    public static FileConfiguration onMessagesFileGet() {
        return onMessagesFileConfig;
    }

    public static void onMessagesFileSave() {
        try {
            onMessagesFileConfig.save(onMessagesFile);
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public static void onMessagesFileReload() {
        onMessagesFileConfig = YamlConfiguration.loadConfiguration(onMessagesFile);
    }
}