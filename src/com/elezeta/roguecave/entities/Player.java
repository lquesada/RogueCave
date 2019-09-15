package com.elezeta.roguecave.entities;

import java.util.HashMap;
import java.util.Map;

import com.elezeta.roguecave.data.ProfessionID;
import com.elezeta.roguecave.data.UpgradeID;
import com.elezeta.roguecave.inventory.Item;


public class Player extends Being {

	private String name = "Anonymous";
	private ProfessionID profession = ProfessionID.naught;
	private int points = 0;
	
	private int exp = 0;
	private int upgradePoints = 0;
	private Map<UpgradeID,Integer> acquiredUpgrades = new HashMap<UpgradeID,Integer>();
	
	public Player() {
		setShadow(0.6f);
		setLookAngle(270f);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public ProfessionID getProfession() {
		return profession;
	}
	
	public void setProfession(ProfessionID profession) {
		this.profession = profession;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
	
	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getUpgradePoints() {
		return upgradePoints;
	}
	
	public void setUpgradePoints(int upgradePoints) {
		this.upgradePoints = upgradePoints;
	}
	
	public Map<UpgradeID,Integer> getAcquiredUpgrades() {
		return acquiredUpgrades;
	}

	@Override
	public void collideWithEntity(Entity e) {
		super.collideWithEntity(e);	
		if (Pickup.class.isInstance(e)) {
			if (!getPickupTimer().containsKey(e)) {
				Pickup p = (Pickup)e;
				Item item = p.getItem();
				if (getInventory().wantToPickUp(item)) {
    				getInventory().pickup(item);
					e.destroy();
				}
			}
		}
	}

	public void gainPoints(int points) {
	    setPoints(getPoints()+points);
	}
	
	public void gainExp(int exp) {
		gainPoints(exp);
		setExp(getExp()+exp);
		while (getExp()>=getExpForLevelUp()) {
			setExp(getExp()-getExpForLevelUp());
			levelUp();
		}
	}
	
	public void levelUp() {
		setUpgradePoints(getUpgradePoints()+((getLevel()/10)+1));
		setLevel(getLevel()+1);
	}

	public int getExpForLevelUp() {
		return (((getLevel()*getLevel())/2)+getLevel())*10;
	}

	public void addUpgrade(UpgradeID upgradeID) {
		Integer ret = getAcquiredUpgrades().get(upgradeID);
		if (ret == null)
			getAcquiredUpgrades().put(upgradeID,1);
		else
			getAcquiredUpgrades().put(upgradeID,ret+1);
	}



}
