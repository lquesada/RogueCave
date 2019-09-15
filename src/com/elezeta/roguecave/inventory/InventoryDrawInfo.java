package com.elezeta.roguecave.inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.elezeta.roguecave.sprites.SingleSprite;
import com.elezeta.roguecave.sprites.SpriteSlotID;

public abstract class InventoryDrawInfo {

	private InventoryDrawInfo() { }
	
	static private Map<SpriteSlotID,Integer> inventorySprites;
	
	static public Map<SpriteSlotID,Integer> getInventorySprites() {
		if (inventorySprites == null) {
			inventorySprites = new HashMap<SpriteSlotID,Integer>();		
			inventorySprites.put(SpriteSlotID.RINGRIGHT,Inventory.RING1);
			inventorySprites.put(SpriteSlotID.RINGLEFT,Inventory.RING2);
			inventorySprites.put(SpriteSlotID.AMULET,Inventory.AMULET);
			inventorySprites.put(SpriteSlotID.ARMOR,Inventory.ARMOR);
			inventorySprites.put(SpriteSlotID.ARMSFRONT,Inventory.ARMS);
			inventorySprites.put(SpriteSlotID.ARMSBACK,Inventory.ARMS);
			inventorySprites.put(SpriteSlotID.HELMETFULL,Inventory.HELMET);
			inventorySprites.put(SpriteSlotID.HELMETHALF,Inventory.HELMET);
			inventorySprites.put(SpriteSlotID.BOOTS,Inventory.BOOTS);
			inventorySprites.put(SpriteSlotID.LEGS,Inventory.LEGS);
		}
		return inventorySprites;
	}

	static private List<SpriteSlotID> inventoryDrawOrder;
	
	static public List<SpriteSlotID> getInventoryDrawOrder() {
		if (inventoryDrawOrder == null) {
			inventoryDrawOrder = new ArrayList<SpriteSlotID>();		
			inventoryDrawOrder.add(SpriteSlotID.BODY);
			inventoryDrawOrder.add(SpriteSlotID.TOP);
			inventoryDrawOrder.add(SpriteSlotID.SHORTS);
			inventoryDrawOrder.add(SpriteSlotID.EYESKIN);
			inventoryDrawOrder.add(SpriteSlotID.EYEWHITE);
			inventoryDrawOrder.add(SpriteSlotID.EYECOLOR);
			inventoryDrawOrder.add(SpriteSlotID.EYEBROWS);
			inventoryDrawOrder.add(SpriteSlotID.BEARDSHORT);
			inventoryDrawOrder.add(SpriteSlotID.HAIRSHORT);
			inventoryDrawOrder.add(SpriteSlotID.AMULET);
			inventoryDrawOrder.add(SpriteSlotID.RINGLEFT);
			inventoryDrawOrder.add(SpriteSlotID.RINGRIGHT);
			inventoryDrawOrder.add(SpriteSlotID.ARMSBACK);
			inventoryDrawOrder.add(SpriteSlotID.LEFTHANDBACK);
			inventoryDrawOrder.add(SpriteSlotID.RIGHTHANDBACK);
			inventoryDrawOrder.add(SpriteSlotID.ARMOR);
			inventoryDrawOrder.add(SpriteSlotID.ARMSFRONT);
			inventoryDrawOrder.add(SpriteSlotID.HAIRDROPBACK);
			inventoryDrawOrder.add(SpriteSlotID.HELMETFULL);
			inventoryDrawOrder.add(SpriteSlotID.HELMETHALF);
			inventoryDrawOrder.add(SpriteSlotID.HAIRLONG);
			inventoryDrawOrder.add(SpriteSlotID.HAIRDROPFRONT);
			inventoryDrawOrder.add(SpriteSlotID.LEGS);
			inventoryDrawOrder.add(SpriteSlotID.BOOTS);
			inventoryDrawOrder.add(SpriteSlotID.LEFTHANDFRONT);
			inventoryDrawOrder.add(SpriteSlotID.RIGHTHANDFRONT);
		}
		return inventoryDrawOrder;
	}
	
	static public void filter(Map<SpriteSlotID,SingleSprite> h) {
		if (h.get(SpriteSlotID.LEGS) != null)
			h.remove(SpriteSlotID.SHORTS);
		if (h.get(SpriteSlotID.HELMETHALF) != null)
			h.remove(SpriteSlotID.HAIRLONG);
		if (h.get(SpriteSlotID.HELMETFULL) != null) {
			h.remove(SpriteSlotID.HAIRLONG);
			h.remove(SpriteSlotID.HAIRDROPFRONT);
			h.remove(SpriteSlotID.HAIRDROPBACK);
			h.remove(SpriteSlotID.BEARDLONG);
		}
	}
}
