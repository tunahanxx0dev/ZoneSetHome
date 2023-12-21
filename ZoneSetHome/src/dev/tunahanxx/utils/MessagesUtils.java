package dev.tunahanxx.utils;

import dev.tunahanxx.files.MessagesFile;
import net.md_5.bungee.api.ChatColor;

public class MessagesUtils {

	public static String noPermission() {
		return ChatColor.translateAlternateColorCodes('&', MessagesFile.onMessagesFileGet().getString("Messages.No-Permission"));
	}
	
	public static String noPlayer() {
		return ChatColor.translateAlternateColorCodes('&', MessagesFile.onMessagesFileGet().getString("Messages.Console-Sender"));
	}
	
	public static String onReload() {
		return ChatColor.translateAlternateColorCodes('&', MessagesFile.onMessagesFileGet().getString("Messages.Reload"));
	}
	
	public static String onHomeSet() {
		return ChatColor.translateAlternateColorCodes('&', MessagesFile.onMessagesFileGet().getString("Messages.Home-Set"));
	}
	
	public static String onHomeTeleport() {
		return ChatColor.translateAlternateColorCodes('&', MessagesFile.onMessagesFileGet().getString("Messages.Home-Teleport"));
	}
	
	public static String onHomeList() {
		return ChatColor.translateAlternateColorCodes('&', MessagesFile.onMessagesFileGet().getString("Messages.Home-List"));
	}
	
	public static String onHomeRemove() {
		return ChatColor.translateAlternateColorCodes('&', MessagesFile.onMessagesFileGet().getString("Messages.Home-Remove"));
	}
	
	public static String onHomeNotFound() {
		return ChatColor.translateAlternateColorCodes('&', MessagesFile.onMessagesFileGet().getString("Messages.Home-Not-Found"));
	}
	
	public static String onHomeOwnerWarring() {
		return ChatColor.translateAlternateColorCodes('&', MessagesFile.onMessagesFileGet().getString("Messages.Home-Owner-Warring"));
	}
	
	public static String onHomeInfo() {
		return ChatColor.translateAlternateColorCodes('&', MessagesFile.onMessagesFileGet().getString("Messages.Home-Info"));
	}
	
	public static String onHomeTeleportDelay() {
		return ChatColor.translateAlternateColorCodes('&', MessagesFile.onMessagesFileGet().getString("Messages.Home-Teleport-Delay"));
	}
}