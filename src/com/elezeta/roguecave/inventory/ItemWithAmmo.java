package com.elezeta.roguecave.inventory;

public class ItemWithAmmo extends Item {

	private Item ammo;
	
	public ItemWithAmmo() {
		setAmmo(null);
	}

	public Item getAmmo() {
		return ammo;
	}

	public void setAmmo(Item ammo) {
		this.ammo = ammo;
	}
	
}
