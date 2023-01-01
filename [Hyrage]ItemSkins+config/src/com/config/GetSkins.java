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
	
	
	public void reloadFile() {
		 cfg = YamlConfiguration.loadConfiguration(file);
	}
	
	public void createFile() {
		if(file.exists()) {
			return;
		}
		try {
			cfg.save(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	String color(String f) {
		
		return ChatColor.translateAlternateColorCodes('&', f);
	}
	
	public Inventory loadInventory() {
		Inventory inv = Bukkit.createInventory(null, main.getinstance().getConfig().getInt("inventory-size")
				, ChatColor.translateAlternateColorCodes('&',main.getinstance().getConfig().getString("inventory-name")));
		for(String keys : main.getinstance().getConfig().getConfigurationSection("items").getKeys(false)) {
			FileConfiguration cf = main.getinstance().getConfig();
			String slot = "items."+keys;
			if(cf.getString(slot+".type") ==null)return inv;
			int amount =0;
			int data = 0;
			if(cf.getInt(slot+".data") != 0) {
				data = cf.getInt(slot+".data");
			}
			if(cf.getInt(slot+".amount") != 0) {
				amount = cf.getInt(slot+".amount");
			}
			
			ItemStack item = new ItemStack(Material.getMaterial(cf.getString(slot+".type").toUpperCase()),amount,(short)data);
			ItemMeta meta =item.getItemMeta();
			if(cf.getString(slot+".displayname") !=null) {
				meta.setDisplayName(color(cf.getString(slot+".displayname")));
			}
			if(cf.getStringList(slot+".lore") !=null) {
				List<String> newlore = new ArrayList<>();
				for(String ss : cf.getStringList(slot+".lore")) {
					newlore.add(color(ss));
				}
				meta.setLore(newlore);
			}
			item.setItemMeta(meta);
			inv.setItem(Integer.parseInt(keys), item);
		}
		return inv;
	}
	
	public Inventory getSkins() {
		if(file.exists()) {
		ConfigurationSection cs = cfg.getConfigurationSection("skins.");
		Inventory inv = loadInventory();
		for(String s :cs.getKeys(false)) {
			String slot = "skins."+s;
			ItemStack item = new ItemStack(Material.getMaterial(cfg.getString(slot+".type").toUpperCase()));
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(main.color(cfg.getString(slot+".displayname")));
			if(cfg.getStringList(slot+".lore") !=null) {
				List<String> newlore = new ArrayList<>();
				for(String ss : cfg.getStringList(slot+".lore")) {
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
			int incomingdamage = cfg.getInt(slot+".IncomingDamagePercentageReduced");
			int outcomingdamage = cfg.getInt(slot+".OutcomingDamagePercentage");
			SkinType skintype = SkinType.valueOf(cfg.getString(slot+".skintype"));
			main.getinstance().skins.put(name, new ItemSkin(name, item, applylore, incomingdamage, outcomingdamage,skintype));
		}
		}
	}

}
