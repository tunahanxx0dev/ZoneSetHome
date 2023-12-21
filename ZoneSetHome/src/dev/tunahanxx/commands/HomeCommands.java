package dev.tunahanxx.commands;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import dev.tunahanxx.ZoneSetHome;
import dev.tunahanxx.files.SettingsFile;
import dev.tunahanxx.utils.MessagesUtils;
import dev.tunahanxx.utils.SoundUtils;
import net.md_5.bungee.api.ChatColor;

public class HomeCommands implements TabExecutor {
	
	public static String homeName;
	
	public static String playerID;
	
	public static String playerName;
	
	public static FileConfiguration homeConfig;
	
	@Override
	public List<String> onTabComplete(CommandSender s, Command c, String l, String[] a) {
		
		if (a.length == 1) {
			List<String> arg = new ArrayList<>();
			
			arg.add("set");
			arg.add("tp");
			arg.add("list");
			arg.add("remove");
			arg.add("info");
			
			return arg;
		}
		
		if (a.length == 2) {
			List<String> arg = new ArrayList<>();
			
			arg.add("--home_name");
			
			return arg;
		}
		
		return null;
	}
	
	@Override
	public boolean onCommand(CommandSender s, Command c, String l, String[] a) {
		
		if (s instanceof Player) {
			
			Player p = (Player) s;
			
			Location loc = p.getLocation();
			
			if (p.hasPermission("zone_home.default")) {
				
				if (l.equalsIgnoreCase("home")) {
					
					if (a.length >= 2) {
						if (a[0].equalsIgnoreCase("set")) {
							
							homeName = a[1].toString();
							
							playerName = p.getDisplayName();
							
							playerID = p.getUniqueId().toString();
							
						    String fileName = playerID + "_home" + ".yml";
						    File fileDirectory = new File(Bukkit.getPluginManager().getPlugin("ZoneSetHome").getDataFolder(), "homes");
						    File file = new File(fileDirectory, fileName);
						    FileConfiguration fileConfig = YamlConfiguration.loadConfiguration(file);
						    
						    homeConfig = fileConfig;
						    
						    try {
							    fileConfig.set(homeName + ".Player", playerName);
							    fileConfig.set(homeName + ".World", loc.getWorld().getName());
							    fileConfig.set(homeName + ".Location-X", loc.getBlockX());
							    fileConfig.set(homeName + ".Location-Y", loc.getBlockY());
							    fileConfig.set(homeName + ".Location-Z", loc.getBlockZ());
							    
							    fileConfig.save(file);
							    
								p.sendMessage(MessagesUtils.onHomeSet());
								p.playSound(loc, SoundUtils.levelUp, 10, 1);
						    } catch (Exception e) {
						    	e.printStackTrace();
						    }
						}
					}
					
					if (a.length == 2 && a[0].equalsIgnoreCase("tp")) {
						
					    String homeName = a[1];

					    File fileDirectory = new File(Bukkit.getPluginManager().getPlugin("ZoneSetHome").getDataFolder(), "homes");
					    
					    String fileName = p.getUniqueId().toString() + "_home.yml";
					    
					    File file = new File(fileDirectory, fileName);

					    if (file.exists()) {
					    	
					        FileConfiguration fileConfig = YamlConfiguration.loadConfiguration(file);

					        if (fileConfig.contains(homeName)) {
					        	
					            String ownerID = fileConfig.getString(homeName + ".Player");  

					            if (ownerID.equalsIgnoreCase(p.getDisplayName())) {
					            	
									World w = Bukkit.getWorld(homeConfig.getString(homeName + ".World"));
									
									double x = homeConfig.getInt(homeName + ".Location-X");
									double y = homeConfig.getInt(homeName + ".Location-Y");
									double z = homeConfig.getInt(homeName + ".Location-Z");
									
									int delay = SettingsFile.onSettingsFileGet().getInt("Settings.Home-Teleport-Delay");
									
									String message = MessagesUtils.onHomeTeleportDelay();
									
									message = message.replace("%delay%", String.valueOf(delay));
									
									p.sendMessage(message);
										
					                Bukkit.getScheduler().runTaskLater(ZoneSetHome.getInstance(), () -> {
					                    p.teleport(new Location(w, x, y, z));
					                    p.sendMessage(MessagesUtils.onHomeTeleport());
					                    p.playSound(loc, SoundUtils.endermanTeleport, 10, 1);
					                }, delay);     
					            } else {
					                p.sendMessage(MessagesUtils.onHomeOwnerWarring());
					                p.playSound(loc, SoundUtils.villagerNo, 10, 1);
					            }
					        } else {
					            p.sendMessage(MessagesUtils.onHomeNotFound());
					            p.playSound(loc, SoundUtils.villagerNo, 10, 1);
					        }
					    } else {
					        p.sendMessage(MessagesUtils.onHomeNotFound());
					        p.playSound(loc, SoundUtils.villagerNo, 10, 1);
					    }
					}
					
					if (a.length >= 1 && a[0].equalsIgnoreCase("list")) {
						
					    File fileDirectory = new File(Bukkit.getPluginManager().getPlugin("ZoneSetHome").getDataFolder(), "homes");
					    File[] files = fileDirectory.listFiles();

					    if (files != null) {
					        for (File file : files) {
					            if (file.isFile() && file.getName().endsWith("_home.yml")) {
					            	
					                String fileName = file.getName();
					                
					                String[] parts = fileName.split("_home.yml");
					                
					                String playerID = parts[0];

					                if (playerID.equals(p.getUniqueId().toString())) {
					                    FileConfiguration fileConfig = YamlConfiguration.loadConfiguration(file);

					                    Set<String> homeNames = fileConfig.getKeys(false);
					                    
					                    if (homeNames != null && !homeNames.isEmpty()) {
					                        p.sendMessage(MessagesUtils.onHomeList());
					                        p.playSound(loc, SoundUtils.levelUp, 10, 1);

					                        for (String home : homeNames) {
					                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&r-&e " + home));
					                        }
					                    }
					                }
					            }
					        }
					    }
					}
					
					if (a.length == 2 && a[0].equalsIgnoreCase("remove")) {
						
					    String homeName = a[1];

					    File fileDirectory = new File(Bukkit.getPluginManager().getPlugin("ZoneSetHome").getDataFolder(), "homes");
					    
					    String fileName = p.getUniqueId().toString() + "_home.yml";
					    
					    File file = new File(fileDirectory, fileName);

					    if (file.exists()) {
					    	
					        FileConfiguration fileConfig = YamlConfiguration.loadConfiguration(file);

					        if (fileConfig.contains(homeName)) {
					            String ownerID = fileConfig.getString(homeName + ".Player");

					            if (ownerID.equalsIgnoreCase(p.getDisplayName())) {
					                fileConfig.set(homeName, null);

					                try {
					                    fileConfig.save(file);
					                    p.sendMessage(MessagesUtils.onHomeRemove());
					                    p.playSound(loc, SoundUtils.levelUp, 10, 1);
					                } catch (IOException e) {
					                    e.printStackTrace();
					                }
					            } else {
					                p.sendMessage(MessagesUtils.onHomeOwnerWarring());
					                p.playSound(loc, SoundUtils.villagerNo, 10, 1);
					            }
					        } else {
					            p.sendMessage(MessagesUtils.onHomeNotFound());
					            p.playSound(loc, SoundUtils.villagerNo, 10, 1);
					        }
					    } else {
					        p.sendMessage(MessagesUtils.onHomeNotFound());
							p.playSound(loc, SoundUtils.villagerNo, 10, 1);
					    }
					}
					
					if (a.length == 2 && a[0].equalsIgnoreCase("info")) {
						
					    String homeName = a[1];

					    File fileDirectory = new File(Bukkit.getPluginManager().getPlugin("ZoneSetHome").getDataFolder(), "homes");
					    
					    String fileName = p.getUniqueId().toString() + "_home.yml";
					    
					    File file = new File(fileDirectory, fileName);

					    if (file.exists()) {
					    	
					        FileConfiguration fileConfig = YamlConfiguration.loadConfiguration(file);

					        if (fileConfig.contains(homeName)) {
					        	
					            String ownerID = fileConfig.getString(homeName + ".Player");

					            if (ownerID.equalsIgnoreCase(p.getDisplayName())) {
					            	
					                String worldName = fileConfig.getString(homeName + ".World");
					                
					                double locX = fileConfig.getDouble(homeName + ".Location-X");
					                
					                double locY = fileConfig.getDouble(homeName + ".Location-Y");
					                
					                double locZ = fileConfig.getDouble(homeName + ".Location-Z");

					                String message = MessagesUtils.onHomeInfo();
					                
					                message = message.replace("%home_name%", homeName);
					                
					                message = message.replace("%world%", worldName);
					                
					                message = message.replace("%loc_x%", String.valueOf(locX));
					                
					                message = message.replace("%loc_y%", String.valueOf(locY));
					                
					                message = message.replace("%loc_z%", String.valueOf(locZ));
					                
					                p.sendMessage(message);
					                p.playSound(loc, SoundUtils.levelUp, 10, 1);
					                
					            } else {
					                p.sendMessage(MessagesUtils.onHomeOwnerWarring());
					                p.playSound(loc, SoundUtils.villagerNo, 10, 1);
					            }
					        } else {
					            p.sendMessage(MessagesUtils.onHomeNotFound());
					            p.playSound(loc, SoundUtils.villagerNo, 10, 1);
					        }
					    } else {
					        p.sendMessage(MessagesUtils.onHomeNotFound());
					        p.playSound(loc, SoundUtils.villagerNo, 10, 1);
					    }
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