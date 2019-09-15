package com.elezeta.roguecave.entities;

import com.elezeta.roguecave.Constants;

public class Attack extends Entity {

	private float damage = 0f;
	private float attack = 0f;
	private boolean hitCeiling = false;
	private float duration = 0f;
	private Being launcher = null;
	private float originX = 0f;
	private float originY = 0f;
		
	public Attack() {
		setGravity(0f);
		setDecelerationFactor(0f);		
	}
	
	public float getDamage() {
		return damage;
	}

	public void setDamage(float damage) {
		this.damage = damage;
	}

	public float getAttack() {
		return attack;
	}

	public void setAttack(float attack) {
		this.attack = attack;
	}

	public boolean hitsCeiling() {
		return hitCeiling;
	}

	public void setHitCeiling(boolean hitCeiling) {
		this.hitCeiling = hitCeiling;
	}

	public float getDuration() {
		return duration;
	}
	
	public void setDuration(float duration) {
		this.duration = duration;
	}
	
	public Being getLauncher() {
		return launcher;
	}

	public void setLauncher(Being launcher) {
		this.launcher = launcher;
	}

	public float getOriginX() {
		return originX;
	}

	public void setOriginX(float originX) {
		this.originX = originX;
	}

	public float getOriginY() {
		return originY;
	}

	public void setOriginY(float originY) {
		this.originY = originY;
	}
	
	//
	
	@Override
	public float getMaxSpeed() {
		return Constants.INF;
	}

	@Override
	protected void pass(float delta) {
		super.pass(delta);
		setDuration(getDuration() - delta);
		if (getDuration() <= 0)
			destroy();
	}
	
	@Override
	public void collideWithEntity(Entity e) {
		destroy();		
	}

	@Override
	public void collideWithWall() {
		destroy();
	}

	@Override
	public void collideWithGround() {
		destroy();
	}

	@Override
	public void collideWithCeiling() {
		if (hitsCeiling())
			destroy();
	}
	
}
