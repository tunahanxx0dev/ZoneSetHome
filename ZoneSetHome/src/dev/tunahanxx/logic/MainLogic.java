package dev.tunahanxx.logic;

import org.bukkit.ChatColor;

import dev.tunahanxx.utils.FileBuild;

public class MainLogic {

	public static void onStart() {
		try {
			System.out.println(ChatColor.translateAlternateColorCodes('&', ""));
			System.out.println(ChatColor.translateAlternateColorCodes('&', "&dZoneSetHome &r- &aPlugin Active &r- &bTunahanXXDev"));
			System.out.println(ChatColor.translateAlternateColorCodes('&', ""));
			
			FileBuild.onFileMain();
		} catch (Exception e) {
			System.out.println(ChatColor.translateAlternateColorCodes('&', "&e&l" + e.getMessage()));
		}
	}
	
	public static void onStop() {
		try {
			System.out.println(ChatColor.translateAlternateColorCodes('&', ""));
			System.out.println(ChatColor.translateAlternateColorCodes('&', "&dZoneSetHome &r- &cPlugin De-Active &r- &bTunahanXXDev"));
			System.out.println(ChatColor.translateAlternateColorCodes('&', ""));
			
			FileBuild.onFileSave();
		} catch (Exception e) {
			System.out.println(ChatColor.translateAlternateColorCodes('&', "&e&l" + e.getMessage()));
		}
	}
}