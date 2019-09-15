package com.elezeta.roguecave.upgrades;

import com.elezeta.roguecave.data.SpriteID;
import com.elezeta.roguecave.data.UpgradeID;
import com.elezeta.roguecave.entities.Player;


public final class Upgrade {
	
	private UpgradeID upgradeID;
	private SpriteID sprite;
	private int cost;
	private int maxTimes;
	//TODO method

	public UpgradeID getUpgradeID() {
		return upgradeID;
	}

	public void setUpgradeID(UpgradeID upgradeId) {
		this.upgradeID = upgradeId;
	}

	public SpriteID getSprite() {
		return sprite;
	}

	public void setSprite(SpriteID sprite) {
		this.sprite = sprite;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getMaxTimes() {
		return maxTimes;
	}

	public void setMaxTimes(int maxTimes) {
		this.maxTimes = maxTimes;
	}

	public void apply(Player p) {
		Integer times = p.getAcquiredUpgrades().get(getUpgradeID());
		if (times == null || times<getMaxTimes()) {
			if (p.getUpgradePoints()>=getCost()) {
				p.setUpgradePoints(p.getUpgradePoints()-cost);
				p.addUpgrade(getUpgradeID());
				// TODO effect 		
				
			}
		}
	}

}
