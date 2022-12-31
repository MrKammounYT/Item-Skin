package com.kammoun.listners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.kammoun.main.main;

public class PlayerHitEvent implements Listener {

	@EventHandler
	public void onentitydamage(EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Player) {
			Player p = (Player) e.getDamager();
			if (is_a_Valid_Sword(p)) {
				if (p.getItemInHand().hasItemMeta()) {
					if (p.getItemInHand().getItemMeta().hasLore()) {
						for (String lore : p.getItemInHand().getItemMeta().getLore()) {
							if (main.getinstance().skins.containsKey(lore)) {
								// bad code
								e.setDamage(e.getDamage() + (e.getDamage()
										* main.getinstance().skins.get(lore).getOutcomingDamagePercentage() / 100));

							}
						}
					}
				}
			}
		}
	}

	public boolean is_a_Valid_Sword(Player p) {
		if (p.getInventory().getItemInHand().getType() == Material.DIAMOND_SWORD)
			return true;
		else if (p.getInventory().getItemInHand().getType() == Material.IRON_SWORD)
			return true;
		else if (p.getInventory().getItemInHand().getType() == Material.GOLD_SWORD)
			return true;
		else if (p.getInventory().getItemInHand().getType() == Material.STONE_SWORD)
			return true;
		else if (p.getInventory().getItemInHand().getType() == Material.WOOD_SWORD)
			return true;
		else
			return false;
	}

}
