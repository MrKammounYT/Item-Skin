package com.kammoun.listners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.kammoun.main.main;
import com.utils.skins.SkinType;

public class ApplySkin implements Listener {

	
	@EventHandler
	public void onSkinApply(InventoryClickEvent e) {
		if (e.getClick() == ClickType.LEFT) {
			if (e.getClickedInventory() != e.getWhoClicked().getInventory())
				return;
		
			if (!e.getCursor().hasItemMeta())
				return;
			
			if (!e.getCursor().getItemMeta().hasDisplayName())
				return;
			
			Player p = (Player) e.getWhoClicked();
			Material item = e.getCursor().getType();
			Material a_item = e.getCurrentItem().getType();
			String displayName = e.getCursor().getItemMeta().getDisplayName();
				if (main.getinstance().skins.containsKey(displayName)) {
					p.sendMessage(""+main.getinstance().skins.containsKey(displayName));
					if (main.getinstance().skins.get(displayName).getMaterial() == item) {
						p.sendMessage("nfs el material: "+(main.getinstance().skins.get(displayName).getMaterial().equals(item)));
						if (main.getinstance().skins.get(displayName).getSkinType().equals(getSkinType(a_item))) {
								e.setCursor(new ItemStack(Material.AIR));
								e.setCancelled(true);
								p.playSound(p.getLocation(), Sound.ANVIL_USE, 5.0f, 6.0f);
								addLore(p, e.getSlot(), e.getCurrentItem(), main.getinstance().skins.get(displayName).getApplyLore());
							
						}
					}

				
			}

		}
	}

	public SkinType getSkinType(Material mt) {
		if (mt == Material.DIAMOND_SWORD || mt == Material.IRON_SWORD || mt == Material.GOLD_SWORD
				|| mt == Material.STONE_SWORD) {
			return SkinType.sword;
		}
		if (mt == Material.DIAMOND_CHESTPLATE || mt == Material.IRON_CHESTPLATE || mt == Material.GOLD_CHESTPLATE
				|| mt == Material.LEATHER_CHESTPLATE) {
			return SkinType.chestplate;
		}
		if (mt == Material.DIAMOND_LEGGINGS || mt == Material.IRON_LEGGINGS || mt == Material.GOLD_LEGGINGS
				|| mt == Material.LEATHER_LEGGINGS) {
			return SkinType.leggings;
		}
		if (mt == Material.DIAMOND_BOOTS || mt == Material.IRON_BOOTS || mt == Material.GOLD_BOOTS
				|| mt == Material.LEATHER_BOOTS) {
			return SkinType.boots;
		}
		if (mt == Material.DIAMOND_HELMET || mt == Material.IRON_HELMET || mt == Material.GOLD_HELMET
				|| mt == Material.LEATHER_HELMET) {
			return SkinType.helmet;
		}
		if (mt == Material.DIAMOND_AXE || mt == Material.IRON_AXE || mt == Material.GOLD_AXE
				|| mt == Material.STONE_AXE) {
			return SkinType.axe;
		}
		if (mt == Material.DIAMOND_PICKAXE || mt == Material.IRON_PICKAXE || mt == Material.GOLD_PICKAXE
				|| mt == Material.STONE_PICKAXE) {
			return SkinType.pickaxe;
		}
		if (mt == Material.DIAMOND_SPADE || mt == Material.IRON_SPADE || mt == Material.GOLD_SPADE
				|| mt == Material.STONE_SPADE) {
			return SkinType.shovel;
		}
		if (mt == Material.BOW) {
			return SkinType.sword;
		}
		return null;
	}

	public void addLore(Player p, int slot, ItemStack item, String applyingLore) {
		ItemMeta meta = item.getItemMeta();
		if (meta.hasLore()) {
			List<String> Basiclore = meta.getLore();
			Basiclore.add(applyingLore);
			meta.setLore(Basiclore);
		} else {
			List<String> st = new ArrayList<>();
			st.add(applyingLore);
			meta.setLore(st);
		}
		item.setItemMeta(meta);
		p.getInventory().setItem(slot, item);

	}

}
