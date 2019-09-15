package com.elezeta.roguecave.data;


import com.elezeta.roguecave.inventory.Item;
import com.elezeta.roguecave.inventory.ItemType;
import com.elezeta.roguecave.sprites.ColorMask;
import com.elezeta.roguecave.sprites.ColorSprite;
import com.elezeta.roguecave.sprites.SingleSprite;
import com.elezeta.roguecave.sprites.SpriteSlotID;

public abstract class ItemFactory {

	private ItemFactory() {
	}

	public static Item generateItem(ItemID iId) {
		Item item;
		item = new Item();
		item.setItemID(iId);
		switch (iId) {

		////////////////////////////////////
		//DEFAULT
	 	////////////////////////////////////

			case noitem:
				item.setItemType(null);
				item.setSprite(new SingleSprite(SpriteID.naught));
				item.setInventorySprite(new SingleSprite(SpriteID.naught));
				return item;
				
			case fists:
				item.setItemType(ItemType.WEAPONDOESNOTNEEDAMMO);
				item.setAttacks(true);
				item.setRequiresTarget(true);
				item.setAutoFire(true);
				item.setSprite(new SingleSprite(SpriteID.naught));
				item.setInventorySprite(new SingleSprite(SpriteID.naught));
				item.setShadow(0.5f);
				item.setRecoveryTime(0.3f);
				item.setAttackHorizontalAngleVariation(5f);
				item.setAttackSpeed(150f);
				item.setAttackRadius(4f);
				item.setAttackShadow(0f);
				item.setAttackHeight(7f);
				item.getStats().setAttack(1f);
				item.getStats().setRange(5f);
				item.setAttackSprite(new SingleSprite(SpriteID.flyingHit1));
				return item;

		////////////////////////////////////
		//ARMOR
	 	////////////////////////////////////
						
			case armor1:
				item.setItemType(ItemType.ARMOR);
				item.setSprite(new ColorSprite(SpriteID.pickuparmor1,new ColorMask(0.79f,0.70f,0.54f,1f)));
				item.setInventorySprite(new SingleSprite(SpriteID.armor1));
				item.getEquipmentSprite().put(SpriteSlotID.ARMOR,new ColorSprite(SpriteID.humanarmor1,new ColorMask(0.79f,0.70f,0.54f,1f)));
				item.getStats().setDefense(1f);
				item.getStats().setRange(1f);
				item.setShadow(0.8f);
				item.setRadius(12f);
				item.setHeight(10f);
				return item;
				
			case armor2:
				item.setItemType(ItemType.ARMOR);
				item.setSprite(new ColorSprite(SpriteID.pickuparmor1,new ColorMask(1f,0.70f,0.54f,1f)));
				item.setInventorySprite(new ColorSprite(SpriteID.armor1,new ColorMask(1f,0.70f,0.54f,1f)));
				item.getEquipmentSprite().put(SpriteSlotID.ARMOR,new ColorSprite(SpriteID.humanarmor1,new ColorMask(1f,0.70f,0.54f,1f)));
				item.getStats().setDefense(2f);
				item.setShadow(0.8f);
				item.setRadius(15f);
				item.setHeight(10f);
				return item;

			case helmet1:
				item.setItemType(ItemType.HELMET);
				item.setSprite(new ColorSprite(SpriteID.pickuphelmet1,new ColorMask(0.79f,0.70f,0.54f,1f)));
				item.setInventorySprite(new SingleSprite(SpriteID.helmet1));
				item.getEquipmentSprite().put(SpriteSlotID.HELMETHALF,new ColorSprite(SpriteID.humanhelmethalf1,new ColorMask(0.79f,0.70f,0.54f,1f)));
				item.getStats().setDefense(1f);
				item.setShadow(0.8f);
				item.setRadius(15f);
				item.setHeight(10f);
				return item;

			case amulet1:
				item.setItemType(ItemType.AMULET);
				item.setSprite(new SingleSprite(SpriteID.pickupamulet1));
				item.setInventorySprite(new SingleSprite(SpriteID.amulet1));
				item.getEquipmentSprite().put(SpriteSlotID.AMULET,new SingleSprite(SpriteID.humanamulet1));
				item.getStats().setDefense(1f);
				item.setShadow(0.8f);
				item.setRadius(6f);
				item.setHeight(6f);
				return item;

			case ring1:
				item.setItemType(ItemType.RING);
				item.setSprite(new ColorSprite(SpriteID.pickupring1,new ColorMask(1f,0.5f,0f,1f)));
				item.setInventorySprite(new SingleSprite(SpriteID.ring1));
				item.getEquipmentSprite().put(SpriteSlotID.RINGLEFT,new ColorSprite(SpriteID.humanringleft1,new ColorMask(1f,0.5f,0f,1f)));
				item.getEquipmentSprite().put(SpriteSlotID.RINGRIGHT,new ColorSprite(SpriteID.humanringright1,new ColorMask(1f,0.5f,0f,1f)));
				item.getStats().setDefense(1f);
				item.setShadow(0.8f);
				item.setRadius(6f);
				item.setHeight(5f);
				return item;

			case ring2:
				item.setItemType(ItemType.RING);
				item.setSprite(new ColorSprite(SpriteID.pickupring1,new ColorMask(1f,0f,0f,1f)));
				item.setInventorySprite(new SingleSprite(SpriteID.ring2));
				item.getEquipmentSprite().put(SpriteSlotID.RINGLEFT,new ColorSprite(SpriteID.humanringleft1,new ColorMask(1f,0f,0f,1f)));
				item.getEquipmentSprite().put(SpriteSlotID.RINGRIGHT,new ColorSprite(SpriteID.humanringright1,new ColorMask(1f,0f,0f,1f)));
				item.getStats().setDefense(3f);
				item.setShadow(0.8f);
				item.setRadius(6f);
				item.setHeight(5f);
				return item;

			case ring3:
				item.setItemType(ItemType.RING);
				item.setSprite(new ColorSprite(SpriteID.pickupring1,new ColorMask(0.5f,0.5f,0.5f,1f)));
				item.setInventorySprite(new ColorSprite(SpriteID.ring2,new ColorMask(0.5f,0.5f,0.5f,1f)));
				item.getEquipmentSprite().put(SpriteSlotID.RINGLEFT,new ColorSprite(SpriteID.humanringleft1,new ColorMask(0.5f,0.5f,0.5f,1f)));
				item.getEquipmentSprite().put(SpriteSlotID.RINGRIGHT,new ColorSprite(SpriteID.humanringright1,new ColorMask(0.5f,0.5f,0.5f,1f)));
				item.getStats().setDefense(6f);
				item.setShadow(0.8f);
				item.setRadius(6f);
				item.setHeight(5f);
				return item;

			case boots1:
				item.setItemType(ItemType.BOOTS);
				item.setSprite(new ColorSprite(SpriteID.pickupboots1,new ColorMask(0.79f,0.70f,0.54f,1f)));
				item.setInventorySprite(new SingleSprite(SpriteID.boots1));
				item.getEquipmentSprite().put(SpriteSlotID.BOOTS,new ColorSprite(SpriteID.humanboots1,new ColorMask(0.79f,0.70f,0.54f,1f)));
				item.getStats().setDefense(1f);
				item.getStats().setSpeed(6f);
				item.setShadow(0.8f);
				item.setRadius(15f);
				item.setHeight(7f);
				return item;

			case legs1:
				item.setItemType(ItemType.LEGS);
				item.setSprite(new ColorSprite(SpriteID.pickuplegs1,new ColorMask(0.79f,0.70f,0.54f,1f)));
				item.setInventorySprite(new SingleSprite(SpriteID.legs1));
				item.getEquipmentSprite().put(SpriteSlotID.LEGS,new ColorSprite(SpriteID.humanlegs1,new ColorMask(0.79f,0.70f,0.54f,1f)));
				item.getStats().setDefense(1f);
				item.setShadow(0.8f);
				item.setRadius(13f);
				item.setHeight(10f);
				return item;

			case arms1:
				item.setItemType(ItemType.ARMS);
				item.setSprite(new ColorSprite(SpriteID.pickuparms1,new ColorMask(0.79f,0.70f,0.54f,1f)));
				item.setInventorySprite(new SingleSprite(SpriteID.arms1));
				item.getEquipmentSprite().put(SpriteSlotID.ARMSFRONT,new ColorSprite(SpriteID.humanarmsfront1,new ColorMask(0.79f,0.70f,0.54f,1f)));
				item.getEquipmentSprite().put(SpriteSlotID.ARMSBACK,new ColorSprite(SpriteID.humanarmsback1,new ColorMask(0.79f,0.70f,0.54f,1f)));
				item.getStats().setDefense(1f);
				item.setShadow(0.8f);
				item.setRadius(13f);
				item.setHeight(7f);
				return item;
							
		////////////////////////////////////
		//MELEE WEAPONS
	 	////////////////////////////////////
								
			case knife:
				item.setItemType(ItemType.WEAPONDOESNOTNEEDAMMO);
				item.setAttacks(true);
				item.setHeight(9);
				item.setRadius(4);
				item.setRequiresTarget(true);
				item.setAutoFire(true);
				item.setSprite(new SingleSprite(SpriteID.pickupdagger1));
				item.setInventorySprite(new SingleSprite(SpriteID.meleeWeapon1));
				item.getEquipmentSprite().put(SpriteSlotID.RIGHTHANDFRONT,new SingleSprite(SpriteID.humandaggerfront1));
				item.getEquipmentSprite().put(SpriteSlotID.RIGHTHANDBACK,new SingleSprite(SpriteID.humandaggerback1));
				item.setShadow(0.8f);
				item.setRecoveryTime(0.1f);
				item.setAttackHorizontalAngleVariation(4f);
				item.setAttackSpeed(250f);
				item.setAttackRadius(4f);
				item.setAttackSprite(new SingleSprite(SpriteID.flyingSlash1));
				item.setAttackShadow(0f);
				item.setAttackHeight(5f);
				item.getStats().setDefense(0.1f);
				item.getStats().setAttack(10f);
				item.getStats().setRange(6f);
				return item;

		////////////////////////////////////
		//RANGED WEAPONS
	 	////////////////////////////////////
				
			case bow:
				item.setItemType(ItemType.WEAPONNEEDSAMMO);
				item.setAttacks(true);
				item.setHeight(10);
				item.setRadius(15);
				item.setRequiresAmmoID(AmmoID.arrow);
				item.setRequiresTarget(true);
				item.setSprite(new ColorSprite(SpriteID.pickupbow1,new ColorMask(0.79f,0.70f,0.54f,1f)));
				item.setInventorySprite(new SingleSprite(SpriteID.rangedWeapon1));
				item.getEquipmentSprite().put(SpriteSlotID.RIGHTHANDFRONT,new SingleSprite(SpriteID.humanbowfront1));
				item.getEquipmentSprite().put(SpriteSlotID.RIGHTHANDBACK,new SingleSprite(SpriteID.humanbowback1));
				item.setShadow(0.8f);
				item.setRecoveryTime(0.40f);
				item.setAttackHorizontalAngleVariation(3f);
				item.setAttackSpeed(300f);
				item.setAttackHitCeiling(false);
				item.getStats().setDefense(0.2f);
				item.getStats().setAttack(10f);
				item.getStats().setRange(30f);
				return item;

			case crossbow: //TODO
				item.setItemType(ItemType.WEAPONNEEDSAMMO); 
				item.setAttacks(true);
				item.setHeight(10);
				item.setRadius(16);
				item.setRequiresAmmoID(AmmoID.bolt);
				item.setRequiresTarget(true);
				item.setSprite(new SingleSprite(SpriteID.rangedWeapon1));
				item.setInventorySprite(new SingleSprite(SpriteID.rangedWeapon1));
				item.getEquipmentSprite().put(SpriteSlotID.RIGHTHANDFRONT,new SingleSprite(SpriteID.humanbowfront1));
				item.getEquipmentSprite().put(SpriteSlotID.RIGHTHANDBACK,new SingleSprite(SpriteID.humanbowback1));
				item.setShadow(0.5f);
				item.setRecoveryTime(0.40f);
				item.setAttackHorizontalAngleVariation(3f);
				item.setAttackSpeed(300f);
				item.getStats().setDefense(0.2f);
				item.getStats().setAttack(3f);
				item.getStats().setRange(80f);
				return item;

		////////////////////////////////////
		//AMMO
	 	////////////////////////////////////
				
			case arrows:
				item.setItemType(ItemType.AMMO);
				item.setHeight(4);
				item.setRadius(6);
				item.setIsStackable(true);
				item.setProvidesAmmoID(AmmoID.arrow);
				item.setSprite(new SingleSprite(SpriteID.pickuparrow1));
				item.setInventorySprite(new SingleSprite(SpriteID.rangedAmmo1));
				item.getEquipmentSprite().put(SpriteSlotID.LEFTHANDFRONT,new SingleSprite(SpriteID.humanarrowsfront1));
				item.getEquipmentSprite().put(SpriteSlotID.LEFTHANDBACK,new SingleSprite(SpriteID.humanarrowsback1));
				item.setShadow(0.8f);
				item.setAttackHorizontalAngleVariation(1f);
				item.setAttackSpeed(500f);
				item.setAttackRadius(5f);
				item.setAttackShadow(0.5f);
				item.setAttackHeight(5f);
				item.setAttackRange(400f);
				item.setAttackGravity(-0.3f);
				item.setAttackMinVerticalAngle(-20f);
				item.setAttackMaxVerticalAngle(35f);
				item.setAttackSprite(new SingleSprite(SpriteID.flyingArrow1));
				item.getStats().setAttack(5f);
				item.getStats().setRange(30f);
				return item;

			case bolts: //TODO
				item.setItemType(ItemType.AMMO);
				item.setHeight(4);
				item.setRadius(6);
				item.setIsStackable(true);
				item.setProvidesAmmoID(AmmoID.bolt);
				item.setSprite(new SingleSprite(SpriteID.rangedAmmo1));
				item.setInventorySprite(new SingleSprite(SpriteID.rangedAmmo1));
				item.getEquipmentSprite().put(SpriteSlotID.LEFTHANDFRONT,new SingleSprite(SpriteID.humanarrowsfront1));
				item.getEquipmentSprite().put(SpriteSlotID.LEFTHANDBACK,new SingleSprite(SpriteID.humanarrowsback1));
				item.setShadow(0.5f);
				item.setAttackHorizontalAngleVariation(1f);
				item.setAttackSpeed(500f);
				item.setAttackRadius(5f);
				item.setAttackShadow(0.5f);
				item.setAttackHeight(5f);
				item.setAttackRange(400f);
				item.setAttackGravity(-0.3f);
				item.setAttackMinVerticalAngle(-20f);
				item.setAttackMaxVerticalAngle(35f);
				item.setAttackSprite(new SingleSprite(SpriteID.flyingArrow1));
				item.getStats().setAttack(20f);
				item.getStats().setRange(100f);
				return item;

		////////////////////////////////////
		//SHIELDS
	 	////////////////////////////////////
								
			case shield:
				item.setItemType(ItemType.SHIELD);
				item.setHeight(10);
				item.setRadius(8);
				item.setSprite(new SingleSprite(SpriteID.pickupshield1));
				item.setInventorySprite(new SingleSprite(SpriteID.shield1));
				item.getEquipmentSprite().put(SpriteSlotID.LEFTHANDFRONT,new SingleSprite(SpriteID.humanshieldfront1));
				item.getEquipmentSprite().put(SpriteSlotID.LEFTHANDBACK,new SingleSprite(SpriteID.humanshieldback1));
				item.setShadow(0.8f);
				item.getStats().setDefense(1f);
				return item;
		

		////////////////////////////////////
		//SPELLS
	 	////////////////////////////////////
				
			case fireSpell:
				item.setItemType(ItemType.SPELL);
				item.setAttacks(true);
				item.setTwoHanded(true);
				item.setRequiresTarget(true);
				item.setAutoFire(true);
				item.setSprite(new SingleSprite(SpriteID.spell1));
				item.setInventorySprite(new SingleSprite(SpriteID.spell1));
				item.setShadow(0.5f);
				item.setRecoveryTime(0.0008f);
				item.setAttackHorizontalAngleVariation(20f);
				item.setAttackVerticalAngleVariation(20f);
				item.setAttackSpeedVariation(50f);
				item.setAttackSpeed(700f);
				item.setAttackRadius(10f);
				item.setAttackHeight(16f);
				item.setAttackHeight(16f);
				item.setAttackSprite(new SingleSprite(SpriteID.fire));
				item.setAttackShadow(0.2f);
				item.getStats().setDefense(0.5f);
				item.setManaCost(0.002f);
				item.getStats().setAttack(50f);
				item.getStats().setRange(100f);
				return item;

			case fireExplosionSpell:
				item.setItemType(ItemType.SPELL);
				item.setAttacks(true);
				item.setTwoHanded(true);
				item.setRequiresTarget(false);
				item.setAutoFire(true);
				item.setSprite(new SingleSprite(SpriteID.spell2));
				item.setInventorySprite(new SingleSprite(SpriteID.spell2));
				item.setShadow(0.5f);
				item.setAttackBurst(true);
				item.setAttackBurstTime(0.08f);
				item.setAttackBurstInterval(0.00015f);
				item.setRecoveryTime(0.50f);
				item.setAttackHorizontalAngleVariation(360f);
				item.setAttackVerticalAngleVariation(7f);
				item.setAttackSpeedVariation(0f);
				item.setAttackSpeed(700f);
				item.setAttackRadius(10f);
				item.setAttackHeight(16f);
				item.setAttackSprite(new SingleSprite(SpriteID.fire));
				item.setAttackShadow(0.1f);
				item.getStats().setDefense(0.5f);
				item.getStats().setAttack(50f);
				item.getStats().setRange(100f);
				item.setManaCost(0.004f);
				return item;

				
		////////////////////////////////////
		//MAGIC WEAPONS
	 	////////////////////////////////////
		
			case fireWand:
				item.setItemType(ItemType.WEAPONDOESNOTNEEDAMMO);
				item.setAttacks(true);
				item.setHeight(10);
				item.setRadius(8);
				item.setTwoHanded(true);
				item.setRequiresTarget(true);
				item.setAutoFire(true);
				item.setSprite(new ColorSprite(SpriteID.pickupsword1,new ColorMask(1f,0.4f,0.4f,1f)));
				item.setInventorySprite(new SingleSprite(SpriteID.meleeWeapon38));
				item.getEquipmentSprite().put(SpriteSlotID.RIGHTHANDFRONT,new ColorSprite(SpriteID.humanswordfront1,new ColorMask(1f,0.4f,0.4f,1f)));
				item.getEquipmentSprite().put(SpriteSlotID.RIGHTHANDBACK,new ColorSprite(SpriteID.humanswordback1,new ColorMask(1f,0.4f,0.4f,1f)));
				item.setShadow(0.5f);
				item.setRecoveryTime(0.0004f);
				item.setAttackHorizontalAngleVariation(20f);
				item.setAttackVerticalAngleVariation(10f);
				item.setAttackSpeedVariation(50f);
				item.setAttackSpeed(700f);
				item.setAttackRadius(10f);
				item.setAttackHeight(16f);
				item.setAttackSprite(new SingleSprite(SpriteID.fire));
				item.setAttackShadow(0.2f);
				item.getStats().setDefense(0.5f);
				item.getStats().setAttack(100f);
				item.getStats().setRange(100f);
				item.setManaCost(0.002f);
				return item;
				
			case explosionWand:
				item.setItemType(ItemType.WEAPONDOESNOTNEEDAMMO);
				item.setAttacks(true);
				item.setHeight(10);
				item.setRadius(6);
				item.setTwoHanded(true);
				item.setRequiresTarget(true);
				item.setAutoFire(true);
				item.setSprite(new ColorSprite(SpriteID.pickupsword1,new ColorMask(1f,1f,1f,1f)));
				item.setInventorySprite(new SingleSprite(SpriteID.meleeWeapon33));
				item.getEquipmentSprite().put(SpriteSlotID.RIGHTHANDFRONT,new ColorSprite(SpriteID.humanswordfront1,new ColorMask(1f,1f,1f,1f)));
				item.getEquipmentSprite().put(SpriteSlotID.RIGHTHANDBACK,new ColorSprite(SpriteID.humanswordback1,new ColorMask(1f,1f,1f,1f)));
				item.setShadow(0.8f);
				item.setAttackBurst(true);
				item.setAttackBurstTime(0.08f);
				item.setAttackBurstInterval(0.00015f);
				item.setRecoveryTime(0.50f);
				item.setAttackHorizontalAngleVariation(360f);
				item.setAttackVerticalAngleVariation(7f);
				item.setAttackSpeedVariation(0f);
				item.setAttackSpeed(700f);
				item.setAttackRadius(10f);
				item.setAttackHeight(16f);
				item.setAttackSprite(new SingleSprite(SpriteID.fire));
				item.setAttackShadow(0.1f);
				item.getStats().setDefense(0.5f);
				item.getStats().setAttack(50f);
				item.getStats().setRange(100f);
				item.setManaCost(0.004f);
				return item;

			case firewallWand:
				item.setItemType(ItemType.WEAPONDOESNOTNEEDAMMO);
				item.setAttacks(true);
				item.setHeight(10);
				item.setRadius(6);
				item.setTwoHanded(true);
				item.setRequiresTarget(true);
				item.setAutoFire(true);
				item.setSprite(new ColorSprite(SpriteID.pickupaxe1,new ColorMask(0.3f,1f,1f,1f)));
				item.setInventorySprite(new SingleSprite(SpriteID.meleeWeapon34));
				item.getEquipmentSprite().put(SpriteSlotID.RIGHTHANDFRONT,new ColorSprite(SpriteID.humanaxefront1,new ColorMask(0.3f,1f,1f,1f)));
				item.getEquipmentSprite().put(SpriteSlotID.RIGHTHANDBACK,new ColorSprite(SpriteID.humanaxeback1,new ColorMask(0.3f,1f,1f,1f)));
				item.setShadow(0.8f);
				item.setAttackBurst(true);
				item.setAttackBurstTime(0.04f);
				item.setAttackBurstInterval(0.00015f);
				item.setRecoveryTime(0.50f);
				item.setAttackHorizontalAngleVariation(10f);
				item.setAttackVerticalAngleVariation(7f);
				item.setAttackSpeedVariation(0f);
				item.setAttackSpeed(500f);
				item.setAttackRadius(10f);
				item.setAttackHeight(16f);
				item.setAttackSprite(new SingleSprite(SpriteID.fire));
				item.setAttackShadow(0.1f);
				item.setAttackHitCeiling(false);
				item.getStats().setDefense(0.5f);
				item.getStats().setAttack(50f);
				item.getStats().setRange(400f);
				item.setManaCost(0.004f);
				return item;

			case fireSlashWand:
				item.setItemType(ItemType.WEAPONDOESNOTNEEDAMMO);
				item.setAttacks(true);
				item.setHeight(10);
				item.setRadius(6);
				item.setTwoHanded(true);
				item.setRequiresTarget(true);
				item.setAutoFire(true);
				item.setSprite(new ColorSprite(SpriteID.pickupmace1,new ColorMask(0.8f,0.8f,0.8f,1f)));
				item.setInventorySprite(new SingleSprite(SpriteID.meleeWeapon14));
				item.getEquipmentSprite().put(SpriteSlotID.RIGHTHANDFRONT,new SingleSprite(SpriteID.humanmacefront1));
				item.getEquipmentSprite().put(SpriteSlotID.RIGHTHANDBACK,new SingleSprite(SpriteID.humanmaceback1));
				item.setShadow(0.5f);
				item.setAttackBurst(true);
				item.setAttackBurstTime(0.80f);
				item.setAttackBurstInterval(0.001f);
				item.setAttackBurstScanningMin(720f);
				item.setAttackBurstScanningMax(0f);
				item.setRecoveryTime(0.90f);
				item.setAttackHorizontalAngleVariation(10f);
				item.setAttackVerticalAngleVariation(7f);
				item.setAttackSpeedVariation(0f);
				item.setAttackSpeed(500f);
				item.setAttackRadius(10f);
				item.setAttackHeight(16f);
				item.setAttackSprite(new SingleSprite(SpriteID.fire));
				item.setAttackShadow(0.1f);
				item.getStats().setDefense(0.5f);
				item.getStats().setAttack(50f);
				item.getStats().setRange(100f);
				item.setManaCost(0.004f);
				return item;
				
			case waterWand:
				item.setItemType(ItemType.WEAPONDOESNOTNEEDAMMO);
				item.setAttacks(true);
				item.setHeight(10);
				item.setRadius(6);
				item.setTwoHanded(true);
				item.setRequiresTarget(true);
				item.setAutoFire(true);
				item.setSprite(new ColorSprite(SpriteID.pickupdagger1,new ColorMask(0.2f,0.9f,1f,1f)));
				item.setInventorySprite(new SingleSprite(SpriteID.meleeWeapon28));
				item.getEquipmentSprite().put(SpriteSlotID.RIGHTHANDFRONT,new ColorSprite(SpriteID.humandaggerfront1,new ColorMask(0.2f,0.9f,1f,1f)));
				item.getEquipmentSprite().put(SpriteSlotID.RIGHTHANDBACK,new ColorSprite(SpriteID.humandaggerback1,new ColorMask(0.2f,0.9f,1f,1f)));
				item.setShadow(0.5f);
				item.setRecoveryTime(0.0008f);
				item.setAttackHitCeiling(false);
				item.setAttackHorizontalAngleVariation(9f);
				item.setAttackVerticalAngleVariation(0f);
				item.setAttackMinVerticalAngle(-20f);
				item.setAttackMaxVerticalAngle(35f);
				item.setAttackSpeedVariation(50f);
				item.setAttackSpeed(600f);
				item.setAttackRadius(10f);
				item.setAttackHeight(16f);
				item.setAttackGravity(-0.1f);
				item.setAttackSprite(new SingleSprite(SpriteID.water));
				item.setAttackShadow(0.2f);
				item.getStats().setDefense(0.5f);
				item.getStats().setAttack(10f);
				item.getStats().setRange(100f);
				item.setManaCost(0.002f);
				return item;

			case healWand:
				item.setItemType(ItemType.WEAPONDOESNOTNEEDAMMO);
				item.setHeight(10);
				item.setRadius(6);
				item.setHeals(true);
				item.setTwoHanded(true);
				item.setAutoFire(true);
				item.setSprite(new ColorSprite(SpriteID.pickupsword1,new ColorMask(1f,0.7f,0.4f,1f)));
				item.setInventorySprite(new SingleSprite(SpriteID.meleeWeapon33));
				item.getEquipmentSprite().put(SpriteSlotID.RIGHTHANDFRONT,new ColorSprite(SpriteID.humanswordfront1,new ColorMask(1f,0.7f,0.4f,1f)));
				item.getEquipmentSprite().put(SpriteSlotID.RIGHTHANDBACK,new ColorSprite(SpriteID.humanswordback1,new ColorMask(1f,0.7f,0.4f,1f)));
				item.setShadow(0.5f);
				item.setRecoveryTime(0.05f);
				item.setHeal(2f);
				item.setManaCost(3f);
				return item;

		////////////////////////////////////
		//USEABLE
	 	////////////////////////////////////

			case healPotion:
				item.setItemType(ItemType.USEABLE);
				item.setHeight(7);
				item.setRadius(5);
				item.setHeals(true);
				item.setSprite(new SingleSprite(SpriteID.pickuppotion1));
				item.setInventorySprite(new SingleSprite(SpriteID.potion1));
				item.setTwoHanded(true);
				item.setShadow(0.5f);
				item.setIsStackable(true);
				item.setRecoveryTime(1f);
				item.setHeal(5f);
				return item;

			case potion:
				item.setItemType(ItemType.USEABLE);
				item.setHeight(7);
				item.setRadius(5);
				item.setHeals(true);
				item.setSprite(new SingleSprite(SpriteID.pickuppotion1));
				item.setInventorySprite(new SingleSprite(SpriteID.potion1));
				item.setTwoHanded(true);
				item.setShadow(0.5f);
				item.setIsStackable(true);
				item.setRecoveryTime(1f);
				item.setHeal(5f);
				return item;

			case potion2:
				item.setItemType(ItemType.USEABLE);
				item.setHeight(7);
				item.setRadius(5);
				item.setHeals(true);
				item.setSprite(new SingleSprite(SpriteID.potion1));
				item.setInventorySprite(new SingleSprite(SpriteID.potion1));
				item.setTwoHanded(true);
				item.setShadow(0.5f);
				item.setIsStackable(true);
				item.setRecoveryTime(1f);
				item.setHeal(5f);
				return item;

			case potion3:
				item.setItemType(ItemType.USEABLE);
				item.setHeight(7);
				item.setRadius(5);
				item.setHeals(true);
				item.setSprite(new SingleSprite(SpriteID.potion1));
				item.setInventorySprite(new SingleSprite(SpriteID.potion1));
				item.setTwoHanded(true);
				item.setShadow(0.5f);
				item.setIsStackable(true);
				item.setRecoveryTime(1f);
				item.setHeal(5f);
				return item;

			case potion4:
				item.setItemType(ItemType.USEABLE);
				item.setHeight(7);
				item.setRadius(5);
				item.setHeals(true);
				item.setSprite(new SingleSprite(SpriteID.pickuppotion1));
				item.setInventorySprite(new SingleSprite(SpriteID.potion1));
				item.setTwoHanded(true);
				item.setShadow(0.5f);
				item.setIsStackable(true);
				item.setRecoveryTime(1f);
				item.setHeal(5f);
				return item;

			case potion5:
				item.setItemType(ItemType.USEABLE);
				item.setHeight(7);
				item.setRadius(5);
				item.setHeals(true);
				item.setSprite(new SingleSprite(SpriteID.pickuppotion1));
				item.setInventorySprite(new SingleSprite(SpriteID.potion1));
				item.setTwoHanded(true);
				item.setShadow(0.5f);
				item.setIsStackable(true);
				item.setRecoveryTime(1f);
				item.setHeal(5f);
				return item;

		////////////////////////////////////
		//USEABLE
	 	////////////////////////////////////


			case key:
				item.setHeight(5);
				item.setRadius(6);
				item.setItemType(ItemType.MISC);
				item.setSprite(new SingleSprite(SpriteID.pickupkey1));
				item.setInventorySprite(new SingleSprite(SpriteID.key));
				item.setShadow(0.5f);
				item.setIsStackable(true);
				return item;

		////////////////////////////////////
		//ERROR
	 	////////////////////////////////////

			default:
				System.err.println("--------------");
				System.err.println("CRITICAL ERROR");
	    		System.err.println("Invalid item.");
				System.err.println("ItemID "+iId);
				System.err.println("--------------");
				item.setItemID(null);
				return item;
		}
	}

	public static Item generateItem(ItemID iId, int quantity) {
		Item item = generateItem(iId);
		item.setQuantity(quantity);
		return item;
	}

}
