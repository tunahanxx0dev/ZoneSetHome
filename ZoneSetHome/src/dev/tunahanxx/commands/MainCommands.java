package dev.tunahanxx.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import dev.tunahanxx.utils.FileBuild;
import dev.tunahanxx.utils.MessagesUtils;
import dev.tunahanxx.utils.SoundUtils;

public class MainCommands implements TabExecutor {

	@Override
	public List<String> onTabComplete(CommandSender s, Command c, String l, String[] a) {
		
		if (a.length == 1) {
			List<String> arg = new ArrayList<>();
			
			arg.add("reload");
			
			return arg;
		}
		
		return null;
	}

	@Override
	public boolean onCommand(CommandSender s, Command c, String l, String[] a) {
		
		if (s instanceof Player) {
			
			Player p = (Player) s;
			
			Location loc = p.getLocation();
			
			if (p.hasPermission("zone_home.admin")) {
				
				if (l.equalsIgnoreCase("zs")) {
					
					if (a.length >= 1 && a[0].equalsIgnoreCase("reload")) {
						FileBuild.onFileReload();
						FileBuild.onFileSave();
						p.sendMessage(MessagesUtils.onReload());
						p.playSound(loc, SoundUtils.levelUp, 10, 1);
					}
					
				}
			} else {
				p.sendMessage(MessagesUtils.noPermission());
				p.playSound(loc, SoundUtils.villagerNo, 10, 1);
				return true;
			}
		} else {
			s.sendMessage(MessagesUtils.noPlayer());
			return true;
		}
		return true;
	}
}