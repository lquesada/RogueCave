package com.elezeta.roguecave.entities;

import com.elezeta.roguecave.Constants;
import com.elezeta.roguecave.world.Level;


public class Mob extends Being {

	private int giveExp = 0;

	private float pushForceX = 0f;
	private float pushForceY = 0f;

	public Mob() {
		setShadow(0.6f);
		setLookAngle(270f);
	}
	
	@Override
	public void collideWithEntity(Entity e) {
		super.collideWithEntity(e);
		if (Attack.class.isInstance(e)) {
			Attack a = (Attack)e;
			if (Player.class.isInstance(a.getLauncher())) {
				Player p = (Player)(a.getLauncher());
				p.gainExp(getGiveExp());
			}
		}

		if (Being.class.isInstance(e)) {
			Being b = (Being)e;
			float d = this.distanceTo(b); 
			if (d == 0)
				d = Constants.EPS;
			if (b.getStats().getPower() >= this.getStats().getPower()) {
				float fx = (this.getX()-e.getX())*Constants.forcePushFactor/d;
				float fy = (this.getY()-e.getY())*Constants.forcePushFactor/d;
				fx += (Math.random()*Constants.forcePushRandom*2)-Constants.forcePushRandom;
				fy += (Math.random()*Constants.forcePushRandom*2)-Constants.forcePushRandom;
				if (fx != 0)
					if (Math.abs(fx) > Constants.forcePushMax)
						fx *= Constants.forcePushMax/Math.abs(fx);
				if (fy != 0)
					if (Math.abs(fy) > Constants.forcePushMax)
						fy *= Constants.forcePushMax/Math.abs(fy);
				setPushForceX(getPushForceX()+fx);
				setPushForceY(getPushForceY()+fy);
			}
		}
	}

	@Override
	public void reset() {
		super.reset();
		setPushForceX(0f);
		setPushForceY(0f);
	}


	public int getGiveExp() {
		return giveExp;
	}
	
	public void setGiveExp(int exp) {
		this.giveExp = exp;
	}

	public float getPushForceX() {
		return pushForceX;
	}

	public void setPushForceX(float pushForceX) {
		this.pushForceX = pushForceX;
	}

	public float getPushForceY() {
		return pushForceY;
	}

	public void setPushForceY(float pushForceY) {
		this.pushForceY = pushForceY;
	}

	@Override
	public void think(Level level) {
		super.think(level);
		if (isAlive()) {
			if (hates(getTarget())) {
				if (getTarget().isAlive() && seesTarget()) {
					setAttackAngle(getLookAngle());
					float range = getInventory().getSelectedItem().getAttackSpeed()*getInventory().getSelectedItem().getAttackDuration();
					float dist = distanceTo(getTarget());
					if (dist-getRadius()-getTarget().getRadius()<range) {
						setAttacking(true);
						setStartedAttacking(true);
						setAttackingDistance(dist);
						setAttackingHeight(getTarget().getHandsHeight());
					}
				}
			}
		}
	}

	@Override
	protected void acel(float delta) {
		float inhibition = 0f;
		inhibition = -0.05f+delta*10;
		if (inhibition>=0.9f)
			inhibition = 0.9f;
		if (inhibition<0f)
			inhibition = 0f;
		setForceX(getForceX()+getPushForceX()*(1f - inhibition));
		setForceY(getForceY()+getPushForceY()*(1f - inhibition));
		setSpeedX(getSpeedX() + getForceX() * delta
				* getAccelerationFactor() * getStats().getSpeed()/20f);
		setSpeedY(getSpeedY() + getForceY() * delta
				* getAccelerationFactor() * getStats().getSpeed()/20f);
		setSpeedZ(getSpeedZ() + getForceZ() * delta
				* getAccelerationFactor() * getStats().getSpeed()/20f);
	}
	
	@Override
	public boolean hates(Being b) {
		return Player.class.isInstance(b);
	}
	
}
