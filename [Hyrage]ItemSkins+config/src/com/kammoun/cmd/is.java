package com.kammoun.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.config.GetSkins;
import com.kammoun.main.main;

public class is implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
		if(!(sender instanceof Player))return true;
		Player  p = (Player)sender;
		if(!p.hasPermission("itemskins.admin"))return true;
		
		if(args.length == 1) {
			String cmd = args[0];
			if(cmd.equalsIgnoreCase("menu")) {
				GetSkins gs = new GetSkins();
				p.openInventory(gs.getSkins());
				
			}
			else if(cmd.equalsIgnoreCase("reload")) {
				GetSkins gs = new GetSkins();
				gs.reloadFile();
				p.sendMessage("§aItemSkins Config file Has been reloaded!");
				
			}
			else if(cmd.equalsIgnoreCase("list")) {
				p.sendMessage("List: " + main.getinstance().skins);
			}
		}
		
		return false;
	}
	
	
	

}
