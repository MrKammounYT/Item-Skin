package com.utils.skins;

import org.bukkit.Material;

public class ItemSkin {
	
	private String name;
	private Material material;
	private String Applylore;
	private int IncomingDamagePercentage;
	private int OutComingDamagePercentage;
	private SkinType skintype;
	
	public ItemSkin(String nom,Material material,String applyLore,int incomingdamage,int outcomingdamage,SkinType skintype) {
		this.name = nom;
		this.material = material;
		this.Applylore = applyLore;
		this.IncomingDamagePercentage = incomingdamage;
		this.OutComingDamagePercentage = outcomingdamage;
		this.skintype = skintype;
		
		
	}
	
	
	public String getName() {
		return name;
	}
	public String getApplyLore() {
		return Applylore;
	}
	
	public Material getMaterial() {
		return material;
	}
	
	public int getIncomingDamagePercentage() {
		return IncomingDamagePercentage;
	}
	public int getOutcomingDamagePercentage() {
		return OutComingDamagePercentage;
	}
	
	public SkinType getSkinType() {
		return skintype;
	}
	
}
