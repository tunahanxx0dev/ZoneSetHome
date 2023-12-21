package dev.tunahanxx.utils;

import dev.tunahanxx.files.MessagesFile;
import dev.tunahanxx.files.SettingsFile;

public class FileBuild {
	
	public static void onFileMain() {
		onMessagesFileBuilder();
		onSettingsFileBuilder();
	}

	public static void onFileReload() {
		MessagesFile.onMessagesFileReload();
		SettingsFile.onSettingsFileReload();
	}
	
	public static void onFileSave() {
		MessagesFile.onMessagesFileSave();
		SettingsFile.onSettingsFileSave();
	}
	
	public static void onMessagesFileBuilder() {
		MessagesFile.onMessagesFileSetup();
		
		MessagesFile.onMessagesFileGet().addDefault("Messages.Reload", "&aPlugin Reload Succes.");
		MessagesFile.onMessagesFileGet().addDefault("Messages.No-Permission", "&cYou Do Not Have Enough Permissions!");
		MessagesFile.onMessagesFileGet().addDefault("Messages.Console-Sender", "&eOnly Player Can Use the Command!");
		MessagesFile.onMessagesFileGet().addDefault("Messages.Home-Set", "&aHome Set Succes.");
		MessagesFile.onMessagesFileGet().addDefault("Messages.Home-Teleport", "&aTeleported The Your Homes.");
		MessagesFile.onMessagesFileGet().addDefault("Messages.Home-List", "&r-&bYour Homes&r-");
		MessagesFile.onMessagesFileGet().addDefault("Messages.Home-Remove", "&aYour Home Has Been Successfully Removed.");
		MessagesFile.onMessagesFileGet().addDefault("Messages.Home-Not-Found", "&cNo Hause Found!");
		MessagesFile.onMessagesFileGet().addDefault("Messages.Home-Owner-Warring", "&eYou Don't Own The House.");
		MessagesFile.onMessagesFileGet().addDefault("Messages.Home-Teleport-Delay", "&eYou Will Be Teleported After %delay% Ticks..");
		MessagesFile.onMessagesFileGet().addDefault("Messages.Home-Info", "&aYour &d%home_name% &aNamed Homes Info&r: &bWorld&r: &e%world%&e, &bX&r: &e%loc_x%&e, &bY&r: &e%loc_y%&e, &bZ&r: &e%loc_z%&e,");
		
		MessagesFile.onMessagesFileGet().options().copyDefaults(true);
		MessagesFile.onMessagesFileSave();
	}
	
	public static void onSettingsFileBuilder() {
		SettingsFile.onSettingsFileSetup();
		
		SettingsFile.onSettingsFileGet().addDefault("Settings.Home-Teleport-Delay", 60);
		
		SettingsFile.onSettingsFileGet().options().copyDefaults(true);
		SettingsFile.onSettingsFileSave();
	}
}