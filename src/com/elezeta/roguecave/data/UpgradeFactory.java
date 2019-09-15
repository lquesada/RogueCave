package com.elezeta.roguecave.data;

import com.elezeta.roguecave.upgrades.Upgrade;

public abstract class UpgradeFactory {

	private UpgradeFactory() {
	}

	public static Upgrade generateUpgrade(UpgradeID uId) {
		Upgrade up;
		up = new Upgrade();
		up.setUpgradeID(uId);
		switch (uId) {

		////////////////////////////////////
		//DEFAULT
	 	////////////////////////////////////

			case HUMAN:
				up.setCost(0);
				up.setMaxTimes(1);
				up.setSprite(SpriteID.upgradehuman);
				return up;
				
			case FIGHTER:
				up.setCost(0);
				up.setMaxTimes(1);
				up.setSprite(SpriteID.upgradebody);
				return up;

			case MAXHP:
				up.setCost(1);
				up.setMaxTimes(10);
				up.setSprite(SpriteID.upgrademaxhp);
				return up;
				
			default:
				System.err.println("--------------");
				System.err.println("CRITICAL ERROR");
	    		System.err.println("Invalid upgrade.");
				System.err.println("UpgradeID "+uId);
				System.err.println("--------------");
				up.setUpgradeID(null);
				return up;
		}
	}

}
