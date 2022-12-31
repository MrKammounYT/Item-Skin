package com.config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.kammoun.main.main;
import com.utils.skins.ItemSkin;
import com.utils.skins.SkinType;

public class GetSkins {
	
	
	 File file = new File(main.getinstance().getDataFolder(),"Skins.yml");
	FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
	
	public void createFile() {
		if(file.exists())return;
		try {
			cfg.save(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Inventory getSkins() {
		if(file.exists()) {
		ConfigurationSection cs = cfg.getConfigurationSection("skins.");
		Inventory inv = Bukkit.createInventory(null, 27
				, "ss");
		for(String s :cs.getKeys(false)) {
			String slot = "skins."+s;
			ItemStack item = new ItemStack(Material.getMaterial(cfg.getString(slot+".type").toUpperCase()));
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(main.color(cfg.getString(slot+".displayname")));
			if(cfg.getList(slot+".lore") !=null) {
				List<String> lore = (List<String>) cfg.getList(slot+".lore");
				List<String> newlore = new ArrayList<>();
				for(String ss : lore) {
					newlore.add(ChatColor.translateAlternateColorCodes('&', ss));
				}
				meta.setLore(newlore);
			}
			item.setItemMeta(meta);
			inv.setItem(cfg.getInt(slot+".displayslot"),item);
		}
		return inv;	
		}
		return null;
	}
	public void LoadSkins() {
		if(file.exists()) {
		ConfigurationSection cs = cfg.getConfigurationSection("skins.");
		for(String key :cs.getKeys(false)) {
			String slot = "skins."+key;
			Material item = Material.getMaterial(cfg.getString(slot+".type"));
			String name = cfg.getString(slot+".displayname");
			String applylore = ChatColor.translateAlternateColorCodes('&',cfg.getString(slot+".appliedlore"));
			int incomingdamage = cfg.getInt(slot+".IncomingDamagePercentage");
			int outcomingdamage = cfg.getInt(slot+".OutcomingDamagePercentage");
			SkinType skintype = SkinType.valueOf(cfg.getString(slot+".skintype"));
			main.getinstance().skins.put(applylore, new ItemSkin(name, item, applylore, incomingdamage, outcomingdamage,skintype));
		}
		}
	}

}
