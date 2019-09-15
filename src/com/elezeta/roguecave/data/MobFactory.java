package com.elezeta.roguecave.data;

import com.elezeta.roguecave.entities.Mob;
import com.elezeta.roguecave.entities.Stats;
import com.elezeta.roguecave.inventory.Item;
import com.elezeta.roguecave.inventory.ItemType;
import com.elezeta.roguecave.sprites.ColorMask;
import com.elezeta.roguecave.sprites.ColorSprite;
import com.elezeta.roguecave.sprites.SingleSprite;

public class MobFactory {

	private MobFactory() {
	}
	public static Mob getMob(MobID mId) {
		Mob mob;
		Stats stats;
		Item item;
		
		switch (mId) {
		
		////////////////////////////////////
		//BIRDS
	 	////////////////////////////////////

			case rabbit1:
				mob = new Mob();
				mob.setGiveExp(1);
				mob.setZ(0);
				mob.setRadius(16f);
				mob.setHeight(23f);
				mob.setHandsHeight(1f);
				mob.setSprite(new SingleSprite(SpriteID.mobRabbit));
				mob.setHp(1);
				mob.setMp(0);
				mob.setShadow(0.6f);
				mob.setMoveAnimationFactor(1.7f);
				stats = new Stats();
				stats.setMaxHp(1);
				stats.setMaxMp(0);
				stats.setHpRegen(0);
				stats.setMpRegen(0);
				stats.setVision(300);
				stats.setSpeed(24f);
				mob.setSelfStats(stats);

				item = new Item();
				item.setItemType(ItemType.WEAPONDOESNOTNEEDAMMO);
				item.setAttacks(true);
				item.setRequiresTarget(true);
				item.setAutoFire(true);
				item.setSprite(new SingleSprite(SpriteID.naught));
				item.setInventorySprite(new SingleSprite(SpriteID.naught));
				item.setShadow(0.5f);
				item.setRecoveryTime(1.1f);
				item.setAttackHorizontalAngleVariation(5f);
				item.setAttackSpeed(200f);
				item.setAttackRadius(10f);
				item.setAttackShadow(0f);
				item.getStats().setAttack(0.5f);
				item.getStats().setRange(10f);
				item.setAttackSprite(new SingleSprite(SpriteID.attack1));
				mob.getInventory().setComboMainItem(0,item);
				break;

			case rabbit2:
				mob = new Mob();
				mob.setGiveExp(50);
				mob.setZ(0);
				mob.setRadius(16f);
				mob.setHeight(23f);
				mob.setHandsHeight(10f);
				mob.setSprite(new ColorSprite(SpriteID.mobRabbit,new ColorMask(0.9f,0.9f,1f,0.4f)));
				mob.setHp(100);
				mob.setMp(50);
				mob.setShadow(0.1f);
				mob.setMoveAnimationFactor(1.7f);
				stats = new Stats();
				stats.setMaxHp(100);
				stats.setMaxMp(50);
				stats.setHpRegen(0);
				stats.setMpRegen(5);
				stats.setVision(200);
				stats.setSpeed(15f);
				mob.setSelfStats(stats);
				break;

			case rabbit3:
				mob = new Mob();
				mob.setGiveExp(50000);
				mob.setZ(0);
				mob.setRadius(16f);
				mob.setHeight(23f);
				mob.setHandsHeight(10f);
				mob.setSprite(new ColorSprite(SpriteID.mobRabbit,new ColorMask(0.4f,0.4f,0.7f,1f)));
				mob.setHp(200);
				mob.setMp(20);
				mob.setMoveAnimationFactor(1.7f);
				stats = new Stats();
				stats.setMaxHp(200);
				stats.setMaxMp(20);
				stats.setHpRegen(0);
				stats.setMpRegen(5);
				stats.setVision(200);
				stats.setSpeed(6f);
				mob.setSelfStats(stats);
				mob.setShadow(0.6f);

				item = new Item();
				item.setItemType(ItemType.WEAPONDOESNOTNEEDAMMO);
				item.setAttacks(true);
				item.setRequiresTarget(true);
				item.setAutoFire(true);
				item.setSprite(new SingleSprite(SpriteID.naught));
				item.setInventorySprite(new SingleSprite(SpriteID.naught));
				item.setShadow(0.5f);
				item.setRecoveryTime(1.1f);
				item.setAttackHorizontalAngleVariation(5f);
				item.setAttackSpeed(200f);
				item.setAttackRadius(10f);
				item.setAttackShadow(0f);
				item.getStats().setAttack(0.5f);
				item.getStats().setRange(10f);
				item.setAttackSprite(new SingleSprite(SpriteID.attack5));
				mob.getInventory().setComboMainItem(0,item);
				break;
				
			default:
				System.err.println("--------------");
				System.err.println("CRITICAL ERROR");
	    		System.err.println("Invalid mob.");
				System.err.println("MobID "+mId);
				System.err.println("--------------");
	    		return new Mob(); 

		}
		return mob;

	}

	public static Mob generateMob(MobID mId, float x,float y) {
		Mob mob = getMob(mId);
		
		mob.setX(x);
		mob.setY(y);
		return mob;
	}

}
