package com.kammoun.main;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import com.config.GetSkins;
import com.kammoun.cmd.is;
import com.kammoun.listners.PlayerHitEvent;
import com.utils.skins.ItemSkin;

public class main extends JavaPlugin {

	
	public HashMap<String ,ItemSkin> skins = new HashMap<>();
	private static main instance;
		
	@Override
	public void onEnable() {
		instance = this;
		super.onEnable();		
		GetSkins gs = new GetSkins();
		gs.createFile();
		gs.LoadSkins();
		getCommand("is").setExecutor(new is());
		Bukkit.getPluginManager().registerEvents(new PlayerHitEvent(), this);
	}
	
	public static main getinstance() {
		return instance;
	}
	public static String color(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
		
	}
	
}
