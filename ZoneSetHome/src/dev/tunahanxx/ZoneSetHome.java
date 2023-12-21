package dev.tunahanxx;

import org.bukkit.plugin.java.JavaPlugin;

import dev.tunahanxx.commands.HomeCommands;
import dev.tunahanxx.commands.MainCommands;
import dev.tunahanxx.logic.MainLogic;

public class ZoneSetHome extends JavaPlugin {
	
	public static ZoneSetHome plugin;

	@Override
	public void onEnable() {
		enableBuilder();
	}
	
	@Override
	public void onDisable() {
		MainLogic.onStop();
	}
	
	public void enableBuilder() {
		MainLogic.onStart();
		onCommandLoader();
		plugin = this;
	}
	
	public void onCommandLoader() {
		this.getCommand("zs").setExecutor(new MainCommands());
		this.getCommand("home").setExecutor(new HomeCommands());
	}
	
	public static ZoneSetHome getInstance() {
		return plugin;
	}
}