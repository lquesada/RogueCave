package com.elezeta.roguecave.entities;

import com.elezeta.roguecave.Constants;
import com.elezeta.roguecave.inventory.Item;
import com.elezeta.roguecave.sprites.SpriteData;
import com.elezeta.roguecave.world.Level;

public class Pickup extends Entity {

	private Item item = null;

	public Item getItem() {
		return item;
	}
	
	public void setItem(Item item) {
		this.item = item;
	}
	
	public Pickup() {
		setGravity(-0.5f);
		setDecelerationFactor(30f);		
	}
	
	//
	
	@Override
	public float getShadow() {
		return getItem().getShadow();
	}

	@Override
	public float getRadius() {
		return getItem().getRadius();
	}

	@Override
	public float getHeight() {
		return getItem().getHeight();
	}
	
	@Override
	public float getMaxSpeed() {
		return 5000f;
	}

	@Override
	public SpriteData getSprite() {
		return getItem().getSprite();
	}

	@Override
	protected void decel(float delta) {
		if (getTarget() == null)
			super.decel(delta);
	}
	
	@Override
	public void think(Level level) {
		float dist = 0f;
		if (getTarget()!=null)
			dist = distanceTo(getTarget());
		if (getTarget()!=null)
			if (Being.class.isInstance(getTarget()))
				if (!((Being)getTarget()).getInventory().wantToPickUp(getItem()))
					unsetTarget();
		if (getTarget()!=null)
			if (dist>Constants.pickupMagnetDistance)
				unsetTarget();
		if (getTarget()!=null)
			setZ((1-dist/Constants.pickupMagnetDistance)*(getTarget().getHandsHeight()/1.5f));
		super.think(level);
	}

}
