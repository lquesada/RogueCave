package test.com.elezeta.roguecave.inventory;

import static org.junit.Assert.*;

import org.junit.Test;

import com.elezeta.roguecave.Constants;
import com.elezeta.roguecave.data.AmmoID;
import com.elezeta.roguecave.data.ItemID;
import com.elezeta.roguecave.data.SpriteID;
import com.elezeta.roguecave.inventory.Inventory;
import com.elezeta.roguecave.inventory.Item;
import com.elezeta.roguecave.inventory.ItemType;
import com.elezeta.roguecave.sprites.SingleSprite;
import com.elezeta.roguecave.sprites.SpriteSlotID;

public class InventoryTest {
	
	
	private Item amulet;
	private Item armor;
	private Item armor2;
	private Item helmet;
	private Item arms;
	private Item legs;
	private Item boots;
	private Item ring1;
	private Item ring2;
	private Item ring3;
	private Item ring1b;
	private Item knife;
	private Item shield;
	private Item bow;
	private Item arrow;
	private Item explosionWand;
	private Item fireWand;
	private Item potion;
	private Item potion2;
	private Item potion3;
	private Item potion4;
	private Item potion5;
	private Item fireSpell;
	private Item explosionSpell;
	private Item key;
	@SuppressWarnings("unused")
	private Item firewallWand;
	@SuppressWarnings("unused")
	private Item waterWand;
	@SuppressWarnings("unused")
	private Item fireslashWand;
	@SuppressWarnings("unused")
	private Item healWand;

	public InventoryTest() {

		Item item;
		
		item = new Item();
		armor = item;
        item.setItemID(ItemID.armor1);
		item.setItemType(ItemType.ARMOR);
		item.getStats().setDefense(1f);
		item.setShadow(0.5f);
		item.setRadius(14f);
		item.setHeight(28f);
		
		item = new Item();
		armor2 = item;
        item.setItemID(ItemID.armor2);
		item.setItemType(ItemType.ARMOR);
		item.getStats().setDefense(2f);
		item.setShadow(0.5f);
		item.setRadius(14f);
		item.setHeight(28f);

		item = new Item();
		helmet = item;
        item.setItemID(ItemID.helmet1);
		item.setItemType(ItemType.HELMET);
		item.getStats().setDefense(1f);
		item.setShadow(0.5f);
		item.setRadius(13f);
		item.setHeight(28f);

		item = new Item();
		amulet = item;
        item.setItemID(ItemID.amulet1);
		item.setItemType(ItemType.AMULET);
		item.getStats().setDefense(1f);
		item.setShadow(0.3f);
		item.setRadius(11f);
		item.setHeight(28f);

		item = new Item();
		ring1 = item;
        item.setItemID(ItemID.ring1);
		item.setItemType(ItemType.RING);
		item.getStats().setDefense(1f);
		item.setShadow(0.4f);
		item.setRadius(5f);
		item.setHeight(8f);

		item = new Item();
		ring2 = item;
        item.setItemID(ItemID.ring2);
		item.setItemType(ItemType.RING);
		item.getStats().setDefense(3f);
		item.setShadow(0.4f);
		item.setRadius(5f);
		item.setHeight(8f);

		item = new Item();
		ring1b = item;
        item.setItemID(ItemID.ring1);
		item.setItemType(ItemType.RING);
		item.getStats().setDefense(1f);
		item.setShadow(0.4f);
		item.setRadius(5f);
		item.setHeight(8f);

		item = new Item();
		ring3 = item;
        item.setItemID(ItemID.ring3);
		item.setItemType(ItemType.RING);
		item.getStats().setDefense(6f);
		item.setShadow(0.4f);
		item.setRadius(5f);
		item.setHeight(8f);

		item = new Item();
		boots = item;
        item.setItemID(ItemID.boots1);
		item.setItemType(ItemType.BOOTS);
		item.getStats().setDefense(1f);
		item.setShadow(0.5f);
		item.setRadius(9f);
		item.setHeight(23f);

		item = new Item();
		legs = item;
        item.setItemID(ItemID.legs1);
		item.setItemType(ItemType.LEGS);
		item.getStats().setDefense(1f);
		item.setShadow(0.5f);
		item.setRadius(12f);
		item.setHeight(28f);

		item = new Item();
		arms = item;
        item.setItemID(ItemID.arms1);
		item.setItemType(ItemType.ARMS);
		item.getStats().setDefense(1f);
		item.setShadow(0.5f);
		item.setRadius(11f);
		item.setHeight(12f);
						
		item = new Item();
		knife = item;
        item.setItemID(ItemID.knife);
		item.setItemType(ItemType.WEAPONDOESNOTNEEDAMMO);
		item.setAttacks(true);
		item.setHeight(32);
		item.setRadius(5);
		item.setRequiresTarget(true);
		item.setAutoFire(true);
		item.setShadow(0.5f);
		item.setRecoveryTime(0.1f);
		item.setAttackHorizontalAngleVariation(4f);
		item.setAttackSpeed(240f);
		item.setAttackRadius(10f);
		item.setAttackShadow(0f);
		item.setAttackHeight(30f);
		item.getStats().setDefense(0.1f);
		item.getStats().setAttack(1f);
		item.getStats().setRange(10f);

		item = new Item();
		bow = item;
        item.setItemID(ItemID.bow);
		item.setItemType(ItemType.WEAPONNEEDSAMMO);
		item.setAttacks(true);
		item.setHeight(32);
		item.setRadius(16);
		item.setRequiresAmmoID(AmmoID.arrow);
		item.setRequiresTarget(true);
		item.setShadow(0.5f);
		item.setRecoveryTime(0.40f);
		item.setAttackHorizontalAngleVariation(3f);
		item.setAttackSpeed(300f);
		item.getStats().setDefense(0.2f);
		item.getStats().setAttack(1f);
		item.getStats().setRange(10f);

		item = new Item();
		arrow = item;
        item.setItemID(ItemID.arrows);
		item.setItemType(ItemType.AMMO);
		item.setHeight(5);
		item.setRadius(16);
		item.setIsStackable(true);
		item.setProvidesAmmoID(AmmoID.arrow);
		item.setShadow(0.5f);
		item.setAttackHorizontalAngleVariation(1f);
		item.setAttackSpeed(500f);
		item.setAttackRadius(5f);
		item.setAttackShadow(0.5f);
		item.setAttackRange(400f);
		item.setAttackGravity(-0.3f);
		item.setAttackMinVerticalAngle(-20f);
		item.setAttackMaxVerticalAngle(35f);
		item.getStats().setAttack(1f);
		item.getStats().setRange(10f);

		item = new Item();
		shield = item;
        item.setItemID(ItemID.shield);
		item.setItemType(ItemType.SHIELD);
		item.setHeight(32);
		item.setRadius(16);
		item.setShadow(0.5f);
		item.getStats().setDefense(1f);

		item = new Item();
		fireSpell = item;
        item.setItemID(ItemID.fireSpell);
		item.setItemType(ItemType.SPELL);
		item.setAttacks(true);
		item.setHeight(32);
		item.setRadius(16);
		item.setTwoHanded(true);
		item.setRequiresTarget(true);
		item.setAutoFire(true);
		item.setShadow(0.5f);
		item.setRecoveryTime(0.0008f);
		item.setAttackHorizontalAngleVariation(20f);
		item.setAttackVerticalAngleVariation(20f);
		item.setAttackSpeedVariation(50f);
		item.setAttackSpeed(700f);
		item.setAttackRadius(10f);
		item.setAttackHeight(16f);
		item.setAttackShadow(0.2f);
		item.getStats().setDefense(0.5f);
		item.getStats().setAttack(1f);
		item.getStats().setRange(10f);
		item.setManaCost(0.002f);

		item = new Item();
		explosionSpell = item;
        item.setItemID(ItemID.fireExplosionSpell);
		item.setItemType(ItemType.SPELL);
		item.setAttacks(true);
		item.setHeight(32);
		item.setRadius(16);
		item.setTwoHanded(true);
		item.setRequiresTarget(false);
		item.setAutoFire(true);
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
		item.setAttackHeight(20f);
		item.setAttackShadow(0.1f);
		item.getStats().setDefense(0.5f);
		item.getStats().setAttack(1f);
		item.getStats().setRange(10f);
		item.setManaCost(0.004f);

		item = new Item();
		fireWand = item;
        item.setItemID(ItemID.fireWand);
		item.setItemType(ItemType.WEAPONDOESNOTNEEDAMMO);
		item.setAttacks(true);
		item.setHeight(32);
		item.setRadius(16);
		item.setTwoHanded(true);
		item.setRequiresTarget(true);
		item.setAutoFire(true);
		item.setShadow(0.5f);
		item.setRecoveryTime(0.0008f);
		item.setAttackHorizontalAngleVariation(20f);
		item.setAttackVerticalAngleVariation(20f);
		item.setAttackSpeedVariation(50f);
		item.setAttackSpeed(700f);
		item.setAttackRadius(10f);
		item.setAttackHeight(16f);
		item.setAttackShadow(0.2f);
		item.getStats().setDefense(0.5f);
		item.getStats().setAttack(1f);
		item.getStats().setRange(10f);
		item.setManaCost(0.002f);
		
		item = new Item();
		explosionWand = item;
        item.setItemID(ItemID.explosionWand);
		item.setItemType(ItemType.WEAPONDOESNOTNEEDAMMO);
		item.setAttacks(true);
		item.setHeight(32);
		item.setRadius(16);
		item.setTwoHanded(true);
		item.setRequiresTarget(false);
		item.setAutoFire(true);
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
		item.setAttackHeight(20f);
		item.setAttackShadow(0.1f);
		item.getStats().setDefense(0.5f);
		item.getStats().setAttack(1f);
		item.getStats().setRange(10f);
		item.setManaCost(0.004f);

		item = new Item();
		potion = item;
        item.setItemID(ItemID.potion);
		item.setItemType(ItemType.USEABLE);
		item.setHeight(32);
		item.setRadius(16);
		item.setHeals(true);
		item.setTwoHanded(true);
		item.setShadow(0.5f);
		item.setIsStackable(true);
		item.setRecoveryTime(1f);
		item.setHeal(5f);


		item = new Item();
		potion2 = item;
        item.setItemID(ItemID.potion2);
		item.setItemType(ItemType.USEABLE);
		item.setHeight(32);
		item.setRadius(16);
		item.setHeals(true);
		item.setTwoHanded(true);
		item.setShadow(0.5f);
		item.setIsStackable(true);
		item.setRecoveryTime(2f);
		item.setHeal(5f);

		item = new Item();
		potion3 = item;
        item.setItemID(ItemID.potion3);
		item.setItemType(ItemType.USEABLE);
		item.setHeight(32);
		item.setRadius(16);
		item.setHeals(true);
		item.setTwoHanded(true);
		item.setShadow(0.5f);
		item.setIsStackable(true);
		item.setRecoveryTime(3f);
		item.setHeal(5f);
		
		item = new Item();
		potion4 = item;
        item.setItemID(ItemID.potion4);
		item.setItemType(ItemType.USEABLE);
		item.setHeight(32);
		item.setRadius(16);
		item.setHeals(true);
		item.setTwoHanded(true);
		item.setShadow(0.5f);
		item.setIsStackable(true);
		item.setRecoveryTime(2f);
		item.setHeal(10f);

		item = new Item();
		potion5 = item;
        item.setItemID(ItemID.potion5);
		item.setItemType(ItemType.USEABLE);
		item.setHeight(32);
		item.setRadius(16);
		item.setHeals(true);
		item.setTwoHanded(true);
		item.setShadow(0.5f);
		item.setIsStackable(true);
		item.setRecoveryTime(0.2f);
		item.setHeal(7f);

		item = new Item();
		key = item;
		item.setHeight(32);
		item.setRadius(16);
		item.setItemType(ItemType.MISC);
		item.setShadow(0.5f);
		item.setIsStackable(true);
		
		item = new Item();
		firewallWand = item;
		item.setItemType(ItemType.WEAPONDOESNOTNEEDAMMO);
		item.setAttacks(true);
		item.setHeight(32);
		item.setRadius(16);
		item.setTwoHanded(true);
		item.setRequiresTarget(true);
		item.setAutoFire(true);
		item.setShadow(0.5f);
		item.setAttackBurst(true);
		item.setAttackBurstTime(0.04f);
		item.setAttackBurstInterval(0.00015f);
		item.setRecoveryTime(0.50f);
		item.setAttackHorizontalAngleVariation(10f);
		item.setAttackVerticalAngleVariation(7f);
		item.setAttackSpeedVariation(0f);
		item.setAttackSpeed(300f);
		item.setAttackRadius(10f);
		item.setAttackHeight(20f);
		item.setAttackShadow(0.1f);
		item.getStats().setDefense(0.5f);
		item.getStats().setAttack(1f);
		item.getStats().setRange(10f);
		item.setManaCost(0.004f);

		item = new Item();
		fireslashWand = item;
		item.setItemType(ItemType.WEAPONDOESNOTNEEDAMMO);
		item.setAttacks(true);
		item.setHeight(32);
		item.setRadius(16);
		item.setTwoHanded(true);
		item.setRequiresTarget(true);
		item.setAutoFire(true);
		item.setShadow(0.5f);
		item.setAttackBurst(true);
		item.setAttackBurstTime(0.40f);
		item.setAttackBurstInterval(0.00015f);
		item.setAttackBurstScanningMin(720f);
		item.setAttackBurstScanningMax(0f);
		item.setRecoveryTime(0.50f);
		item.setAttackHorizontalAngleVariation(10f);
		item.setAttackVerticalAngleVariation(7f);
		item.setAttackSpeedVariation(0f);
		item.setAttackSpeed(300f);
		item.setAttackRadius(10f);
		item.setAttackHeight(20f);
		item.setAttackShadow(0.1f);
		item.getStats().setDefense(0.5f);
		item.getStats().setAttack(1f);
		item.getStats().setRange(10f);
		item.setManaCost(0.004f);
		
		item = new Item();
		waterWand = item;
		item.setItemType(ItemType.WEAPONDOESNOTNEEDAMMO);
		item.setAttacks(true);
		item.setHeight(32);
		item.setRadius(16);
		item.setTwoHanded(true);
		item.setRequiresTarget(true);
		item.setAutoFire(true);
		item.setSprite(new SingleSprite(SpriteID.meleeWeapon31));
		item.getEquipmentSprite().put(SpriteSlotID.RIGHTHANDFRONT,new SingleSprite(SpriteID.humanwandfront1));
		item.getEquipmentSprite().put(SpriteSlotID.RIGHTHANDBACK,new SingleSprite(SpriteID.humanwandback1));
		item.setShadow(0.5f);
		item.setRecoveryTime(0.0008f);
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
		item.getStats().setAttack(1f);
		item.getStats().setRange(10f);
		item.setManaCost(0.002f);
		
		item = new Item();
		healWand = item;
		item.setItemType(ItemType.WEAPONDOESNOTNEEDAMMO);
		item.setHeight(32);
		item.setRadius(16);
		item.setHeals(true);
		item.setTwoHanded(true);
		item.setAutoFire(true);
		item.setShadow(0.5f);
		item.setRecoveryTime(0.05f);
		item.setHeal(2f);
		item.setManaCost(3f);

	}
	
	@Test
	public void armorTest() {
		
		Inventory in = new Inventory();

		in.setItem(Inventory.INVARMOR+1,amulet);
		in.setItem(Inventory.INVARMOR+2,armor);
		in.setItem(Inventory.INVARMOR+3,helmet);
		in.setItem(Inventory.INVARMOR+4,arms);
		in.setItem(Inventory.INVARMOR+5,legs);
		in.setItem(Inventory.INVARMOR+6,boots);
		in.setItem(Inventory.INVARMOR+7,ring1);
		in.setItem(Inventory.INVARMOR+8,ring2);
		
		assertEquals(amulet,in.getItem(Inventory.INVARMOR+1));
		assertEquals(armor,in.getItem(Inventory.INVARMOR+2));
		assertEquals(helmet,in.getItem(Inventory.INVARMOR+3));
		assertEquals(arms,in.getItem(Inventory.INVARMOR+4));
		assertEquals(legs,in.getItem(Inventory.INVARMOR+5));
		assertEquals(boots,in.getItem(Inventory.INVARMOR+6));
		assertEquals(ring1,in.getItem(Inventory.INVARMOR+7));
		assertEquals(ring2,in.getItem(Inventory.INVARMOR+8));

		in.move(Inventory.INVARMOR+1,Inventory.INVARMOR+11);
		in.move(Inventory.INVARMOR+2,Inventory.INVARMOR+12);
		in.move(Inventory.INVARMOR+3,Inventory.INVARMOR+13);
		in.move(Inventory.INVARMOR+4,Inventory.INVARMOR+14);
		in.move(Inventory.INVARMOR+5,Inventory.INVARMOR+15);
		in.move(Inventory.INVARMOR+6,Inventory.INVARMOR+16);
		in.move(Inventory.INVARMOR+7,Inventory.INVARMOR+17);
		in.move(Inventory.INVARMOR+8,Inventory.INVARMOR+18);
		
		assertEquals(null,in.getItem(Inventory.INVARMOR+1));
		assertEquals(amulet,in.getItem(Inventory.INVARMOR+11));

		assertEquals(null,in.getItem(Inventory.INVARMOR+2));
		assertEquals(armor,in.getItem(Inventory.INVARMOR+12));

		assertEquals(null,in.getItem(Inventory.INVARMOR+3));
		assertEquals(helmet,in.getItem(Inventory.INVARMOR+13));

		assertEquals(null,in.getItem(Inventory.INVARMOR+4));
		assertEquals(arms,in.getItem(Inventory.INVARMOR+14));

		assertEquals(null,in.getItem(Inventory.INVARMOR+5));
		assertEquals(legs,in.getItem(Inventory.INVARMOR+15));

		assertEquals(null,in.getItem(Inventory.INVARMOR+6));
		assertEquals(boots,in.getItem(Inventory.INVARMOR+16));

		assertEquals(null,in.getItem(Inventory.INVARMOR+7));
		assertEquals(ring1,in.getItem(Inventory.INVARMOR+17));

		assertEquals(null,in.getItem(Inventory.INVARMOR+8));
		assertEquals(ring2,in.getItem(Inventory.INVARMOR+18));

		in.move(Inventory.INVARMOR+11,Inventory.INVARMOR+17);
		
		assertEquals(amulet,in.getItem(Inventory.INVARMOR+17));
		assertEquals(ring1,in.getItem(Inventory.INVARMOR+11));

		in.move(Inventory.INVARMOR+11,Inventory.INVWEAPONS+1);
		assertEquals(null,in.getItem(Inventory.INVWEAPONS+1));
		assertEquals(ring1,in.getItem(Inventory.INVARMOR+11));

		in.move(Inventory.INVARMOR+11,Inventory.INVSPELLS+1);
		assertEquals(null,in.getItem(Inventory.INVSPELLS+1));
		assertEquals(ring1,in.getItem(Inventory.INVARMOR+11));

		in.move(Inventory.INVARMOR+11,Inventory.INVMISC+1);
		assertEquals(null,in.getItem(Inventory.INVMISC+1));
		assertEquals(ring1,in.getItem(Inventory.INVARMOR+11));

		in.move(Inventory.INVARMOR+11,Inventory.COMBOS+11);
		assertEquals(null,in.getItem(Inventory.COMBOS+11));
		assertEquals(ring1,in.getItem(Inventory.INVARMOR+11));

		in.move(Inventory.INVARMOR+11,Inventory.COMBOSSELF+1);
		assertEquals(null,in.getItem(Inventory.COMBOSSELF+1));
		assertEquals(ring1,in.getItem(Inventory.INVARMOR+11));

		in.move(Inventory.INVARMOR+11,Inventory.INVARMOR+17);

		assertEquals(amulet,in.getItem(Inventory.INVARMOR+11));
		assertEquals(ring1,in.getItem(Inventory.INVARMOR+17));

		in.move(Inventory.INVARMOR+11,Inventory.AMULET);
		in.move(Inventory.INVARMOR+12,Inventory.ARMOR);
		in.move(Inventory.INVARMOR+13,Inventory.HELMET);
		in.move(Inventory.INVARMOR+14,Inventory.ARMS);
		in.move(Inventory.INVARMOR+15,Inventory.LEGS);
		in.move(Inventory.INVARMOR+16,Inventory.BOOTS);
		in.move(Inventory.INVARMOR+17,Inventory.RING1);
		in.move(Inventory.INVARMOR+18,Inventory.RING2);

		assertEquals(amulet,in.getItem(Inventory.AMULET));
		assertEquals(armor,in.getItem(Inventory.ARMOR));
		assertEquals(helmet,in.getItem(Inventory.HELMET));
		assertEquals(arms,in.getItem(Inventory.ARMS));
		assertEquals(legs,in.getItem(Inventory.LEGS));
		assertEquals(boots,in.getItem(Inventory.BOOTS));
		assertEquals(ring1,in.getItem(Inventory.RING1));
		assertEquals(ring2,in.getItem(Inventory.RING2));

		assertEquals(amulet,in.getItem(Inventory.INVARMOR+11));
		assertEquals(armor,in.getItem(Inventory.INVARMOR+12));
		assertEquals(helmet,in.getItem(Inventory.INVARMOR+13));
		assertEquals(arms,in.getItem(Inventory.INVARMOR+14));
		assertEquals(legs,in.getItem(Inventory.INVARMOR+15));
		assertEquals(boots,in.getItem(Inventory.INVARMOR+16));
		assertEquals(ring1,in.getItem(Inventory.INVARMOR+17));
		assertEquals(ring2,in.getItem(Inventory.INVARMOR+18));
		
		in.move(Inventory.RING2,Inventory.RING1);
		assertEquals(ring1,in.getItem(Inventory.RING2));
		assertEquals(ring2,in.getItem(Inventory.RING1));
		
		in.move(Inventory.RING1,Inventory.RING2);
		assertEquals(ring1,in.getItem(Inventory.RING1));
		assertEquals(ring2,in.getItem(Inventory.RING2));

		in.move(Inventory.ARMOR,Inventory.RING1);
		assertEquals(ring1,in.getItem(Inventory.RING1));
		assertEquals(armor,in.getItem(Inventory.ARMOR));

		in.move(Inventory.AMULET,Inventory.INVWEAPONS+1);
		assertEquals(null,in.getItem(Inventory.INVWEAPONS+1));
		in.move(Inventory.INVARMOR+11,Inventory.AMULET);

		in.move(Inventory.AMULET,Inventory.INVSPELLS+1);
		assertEquals(null,in.getItem(Inventory.INVSPELLS+1));
		in.move(Inventory.INVARMOR+11,Inventory.AMULET);

		in.move(Inventory.AMULET,Inventory.INVMISC+1);
		assertEquals(null,in.getItem(Inventory.INVMISC+1));
		in.move(Inventory.INVARMOR+11,Inventory.AMULET);

		in.move(Inventory.AMULET,Inventory.INVARMOR+1);
		in.move(Inventory.ARMOR,Inventory.INVARMOR+2);
		in.move(Inventory.HELMET,Inventory.INVARMOR+3);
		in.move(Inventory.ARMS,Inventory.INVARMOR+4);
		in.move(Inventory.LEGS,Inventory.INVARMOR+5);
		in.move(Inventory.BOOTS,Inventory.INVARMOR+6);
		in.move(Inventory.RING1,Inventory.INVARMOR+7);
		in.move(Inventory.RING2,Inventory.INVARMOR+8);

		assertEquals(amulet,in.getItem(Inventory.INVARMOR+1));
		assertEquals(armor,in.getItem(Inventory.INVARMOR+2));
		assertEquals(helmet,in.getItem(Inventory.INVARMOR+3));
		assertEquals(arms,in.getItem(Inventory.INVARMOR+4));
		assertEquals(legs,in.getItem(Inventory.INVARMOR+5));
		assertEquals(boots,in.getItem(Inventory.INVARMOR+6));
		assertEquals(ring1,in.getItem(Inventory.INVARMOR+7));
		assertEquals(ring2,in.getItem(Inventory.INVARMOR+8));
		assertEquals(null,in.getItem(Inventory.AMULET));
		assertEquals(null,in.getItem(Inventory.ARMOR));
		assertEquals(null,in.getItem(Inventory.HELMET));
		assertEquals(null,in.getItem(Inventory.ARMS));
		assertEquals(null,in.getItem(Inventory.LEGS));
		assertEquals(null,in.getItem(Inventory.BOOTS));
		assertEquals(null,in.getItem(Inventory.RING1));
		assertEquals(null,in.getItem(Inventory.RING2));

		in.move(Inventory.INVARMOR+7,Inventory.RING1);
		in.move(Inventory.INVARMOR+7,Inventory.RING2);
		assertEquals(null,in.getItem(Inventory.RING1));
		assertEquals(ring1,in.getItem(Inventory.RING2));

		in.setItem(Inventory.INVARMOR+9,ring1b);
		in.move(Inventory.INVARMOR+9,Inventory.RING1);

		assertEquals(ring1b,in.getItem(Inventory.RING1));
		assertEquals(ring1,in.getItem(Inventory.RING2));

		in.move(Inventory.INVARMOR+9,Inventory.RING1);
		assertEquals(ring1b,in.getItem(Inventory.RING1));
		assertEquals(ring1,in.getItem(Inventory.RING2));
		
		in.move(Inventory.RING1,Inventory.NONE);
		assertEquals(null,in.getItem(Inventory.RING1));
		assertEquals(ring1,in.getItem(Inventory.RING2));
		assertEquals(ring1b,in.getItem(Inventory.INVARMOR+9));
		assertEquals(ring1,in.getItem(Inventory.INVARMOR+7));

		in.move(Inventory.INVARMOR+7,Inventory.NONE);
		in.move(Inventory.INVARMOR+9,Inventory.NONE);
		assertEquals(null,in.getItem(Inventory.RING1));
		assertEquals(ring1,in.getItem(Inventory.RING2));
		assertEquals(ring1b,in.getItem(Inventory.INVARMOR+9));
		assertEquals(ring1,in.getItem(Inventory.INVARMOR+7));

		in.move(Inventory.INVARMOR+7,Inventory.TRASH);
		assertEquals(null,in.getItem(Inventory.RING1));
		assertEquals(null,in.getItem(Inventory.RING2));
		assertEquals(ring1b,in.getItem(Inventory.INVARMOR+9));
		assertEquals(null,in.getItem(Inventory.INVARMOR+7));
		assertEquals(ring1,in.getDrop().get(0));
		
		in.getDrop().clear();

		in.move(Inventory.INVARMOR+7,Inventory.TRASH);
		in.move(Inventory.INVARMOR+9,Inventory.TRASH);
		assertEquals(null,in.getItem(Inventory.RING1));
		assertEquals(null,in.getItem(Inventory.RING2));
		assertEquals(null,in.getItem(Inventory.INVARMOR+9));
		assertEquals(null,in.getItem(Inventory.INVARMOR+7));
		assertEquals(ring1b,in.getDrop().get(0));
		
		in.getDrop().clear();

		assertEquals(amulet,in.getItem(Inventory.INVARMOR+1));
		in.move(Inventory.INVARMOR+1,Inventory.INVSPELLS+1);
		assertEquals(amulet,in.getItem(Inventory.INVARMOR+1));
		assertEquals(null,in.getItem(Inventory.INVSPELLS+1));

		assertEquals(amulet,in.getItem(Inventory.INVARMOR+1));
		in.move(Inventory.INVARMOR+1,Inventory.INVMISC+1);
		assertEquals(amulet,in.getItem(Inventory.INVARMOR+1));
		assertEquals(null,in.getItem(Inventory.INVMISC+1));

		assertEquals(amulet,in.getItem(Inventory.INVARMOR+1));
		in.move(Inventory.INVARMOR+1,Inventory.INVWEAPONS+1);
		assertEquals(amulet,in.getItem(Inventory.INVARMOR+1));
		assertEquals(null,in.getItem(Inventory.INVWEAPONS+1));

		assertEquals(amulet,in.getItem(Inventory.INVARMOR+1));
		in.move(Inventory.INVARMOR+1,Inventory.COMBOSSELF+1);
		assertEquals(amulet,in.getItem(Inventory.INVARMOR+1));
		assertEquals(null,in.getItem(Inventory.COMBOSSELF+1));

		assertEquals(amulet,in.getItem(Inventory.INVARMOR+1));
		in.move(Inventory.INVARMOR+1,Inventory.COMBOS+1*10+1);
		assertEquals(amulet,in.getItem(Inventory.INVARMOR+1));
		assertEquals(null,in.getItem(Inventory.COMBOS+1*10+1));

		assertEquals(amulet,in.getItem(Inventory.INVARMOR+1));
		in.move(Inventory.INVARMOR+1,Inventory.COMBOS+1*10+2);
		assertEquals(amulet,in.getItem(Inventory.INVARMOR+1));
		assertEquals(null,in.getItem(Inventory.COMBOS+1*10+2));

		assertEquals(null,in.getItem(Inventory.AMULET));
		in.move(Inventory.INVARMOR+1,Inventory.AMULET);
		assertEquals(amulet,in.getItem(Inventory.AMULET));
		assertEquals(armor,in.getItem(Inventory.INVARMOR+2));
		in.move(Inventory.AMULET,Inventory.INVARMOR+2);
		assertEquals(null,in.getItem(Inventory.AMULET));
		assertEquals(amulet,in.getItem(Inventory.INVARMOR+2));
		assertEquals(armor,in.getItem(Inventory.INVARMOR+1));
		
		in.move(Inventory.INVARMOR+5,Inventory.LEGS);
		assertEquals(legs,in.getItem(Inventory.LEGS));
		assertEquals(legs,in.getItem(Inventory.INVARMOR+5));
		in.move(Inventory.LEGS,Inventory.INVSPELLS+10);
		assertEquals(legs,in.getItem(Inventory.INVARMOR+5));
		assertEquals(null,in.getItem(Inventory.INVSPELLS+10));
		assertEquals(null,in.getItem(Inventory.LEGS));
		in.move(Inventory.INVARMOR+5,Inventory.LEGS);
		in.move(Inventory.LEGS,Inventory.INVMISC+10);
		assertEquals(legs,in.getItem(Inventory.INVARMOR+5));
		assertEquals(null,in.getItem(Inventory.INVMISC+10));
		assertEquals(null,in.getItem(Inventory.LEGS));
		in.move(Inventory.INVARMOR+5,Inventory.LEGS);
		in.move(Inventory.LEGS,Inventory.INVWEAPONS+10);
		assertEquals(legs,in.getItem(Inventory.INVARMOR+5));
		assertEquals(null,in.getItem(Inventory.INVWEAPONS+10));
		assertEquals(null,in.getItem(Inventory.LEGS));
		in.move(Inventory.INVARMOR+5,Inventory.LEGS);

		assertEquals(legs,in.getItem(Inventory.LEGS));
		assertEquals(legs,in.getItem(Inventory.INVARMOR+5));
		in.move(Inventory.INVARMOR+5,Inventory.INVWEAPONS+10);
		assertEquals(legs,in.getItem(Inventory.LEGS));
		assertEquals(legs,in.getItem(Inventory.INVARMOR+5));
		in.move(Inventory.INVARMOR+5,Inventory.NONE);
		assertEquals(legs,in.getItem(Inventory.LEGS));
		assertEquals(legs,in.getItem(Inventory.INVARMOR+5));

	}


	@Test
	public void weaponsTest() {
		
		Inventory in = new Inventory();
		
		in.setItem(Inventory.INVWEAPONS+1,knife);
		in.setItem(Inventory.INVWEAPONS+2,shield);
		in.setItem(Inventory.INVWEAPONS+3,bow);
		in.setItem(Inventory.INVWEAPONS+4,arrow);
		in.setItem(Inventory.INVWEAPONS+5,explosionWand);
		in.setItem(Inventory.INVWEAPONS+6,fireWand);
		in.setItem(Inventory.INVMISC+1,potion);
		
		assertEquals(knife,in.getItem(Inventory.INVWEAPONS+1));
		assertEquals(shield,in.getItem(Inventory.INVWEAPONS+2));
		assertEquals(bow,in.getItem(Inventory.INVWEAPONS+3));
		assertEquals(arrow,in.getItem(Inventory.INVWEAPONS+4));
		assertEquals(explosionWand,in.getItem(Inventory.INVWEAPONS+5));
		assertEquals(fireWand,in.getItem(Inventory.INVWEAPONS+6));

		in.move(Inventory.INVWEAPONS+1,Inventory.INVWEAPONS+11);
		in.move(Inventory.INVWEAPONS+2,Inventory.INVWEAPONS+12);
		in.move(Inventory.INVWEAPONS+3,Inventory.INVWEAPONS+13);
		in.move(Inventory.INVWEAPONS+4,Inventory.INVWEAPONS+14);
		in.move(Inventory.INVWEAPONS+5,Inventory.INVWEAPONS+15);
		in.move(Inventory.INVWEAPONS+6,Inventory.INVWEAPONS+16);
		
		assertEquals(null,in.getItem(Inventory.INVWEAPONS+1));
		assertEquals(knife,in.getItem(Inventory.INVWEAPONS+11));

		assertEquals(null,in.getItem(Inventory.INVWEAPONS+2));
		assertEquals(shield,in.getItem(Inventory.INVWEAPONS+12));

		assertEquals(null,in.getItem(Inventory.INVWEAPONS+3));
		assertEquals(bow,in.getItem(Inventory.INVWEAPONS+13));

		assertEquals(null,in.getItem(Inventory.INVWEAPONS+4));
		assertEquals(arrow,in.getItem(Inventory.INVWEAPONS+14));

		assertEquals(null,in.getItem(Inventory.INVWEAPONS+5));
		assertEquals(explosionWand,in.getItem(Inventory.INVWEAPONS+15));

		assertEquals(null,in.getItem(Inventory.INVWEAPONS+6));
		assertEquals(fireWand,in.getItem(Inventory.INVWEAPONS+16));

		in.move(Inventory.INVWEAPONS+11,Inventory.INVWEAPONS+16);
		
		assertEquals(fireWand,in.getItem(Inventory.INVWEAPONS+11));
		assertEquals(knife,in.getItem(Inventory.INVWEAPONS+16));

		in.move(Inventory.INVWEAPONS+11,Inventory.INVWEAPONS+16);
		
		assertEquals(knife,in.getItem(Inventory.INVWEAPONS+11));
		assertEquals(fireWand,in.getItem(Inventory.INVWEAPONS+16));

		in.move(Inventory.INVWEAPONS+11,Inventory.INVARMOR+1);
		assertEquals(null,in.getItem(Inventory.INVARMOR+1));
		assertEquals(knife,in.getItem(Inventory.INVWEAPONS+11));

		in.move(Inventory.INVWEAPONS+11,Inventory.INVSPELLS+1);
		assertEquals(null,in.getItem(Inventory.INVSPELLS+1));
		assertEquals(knife,in.getItem(Inventory.INVWEAPONS+11));

		in.move(Inventory.INVWEAPONS+11,Inventory.INVMISC+1);
		assertEquals(potion,in.getItem(Inventory.INVMISC+1));
		assertEquals(knife,in.getItem(Inventory.INVWEAPONS+11));

		in.move(Inventory.INVWEAPONS+11,Inventory.ARMOR+1);
		assertEquals(null,in.getItem(Inventory.ARMOR+1));
		assertEquals(knife,in.getItem(Inventory.INVWEAPONS+11));

		in.move(Inventory.INVWEAPONS+11,Inventory.HELMET+1);
		assertEquals(null,in.getItem(Inventory.HELMET+1));
		assertEquals(knife,in.getItem(Inventory.INVWEAPONS+11));

		in.move(Inventory.INVWEAPONS+11,Inventory.ARMS+1);
		assertEquals(null,in.getItem(Inventory.ARMS+1));
		assertEquals(knife,in.getItem(Inventory.INVWEAPONS+11));

		in.move(Inventory.INVWEAPONS+11,Inventory.AMULET+1);
		assertEquals(null,in.getItem(Inventory.AMULET+1));
		assertEquals(knife,in.getItem(Inventory.INVWEAPONS+11));

		in.move(Inventory.INVWEAPONS+11,Inventory.BOOTS+1);
		assertEquals(null,in.getItem(Inventory.BOOTS+1));
		assertEquals(knife,in.getItem(Inventory.INVWEAPONS+11));

		in.move(Inventory.INVWEAPONS+11,Inventory.RING1+1);
		assertEquals(null,in.getItem(Inventory.RING1+1));
		assertEquals(knife,in.getItem(Inventory.INVWEAPONS+11));

		in.move(Inventory.INVWEAPONS+11,Inventory.RING2+1);
		assertEquals(null,in.getItem(Inventory.RING2+1));
		assertEquals(knife,in.getItem(Inventory.INVWEAPONS+11));

		in.move(Inventory.INVWEAPONS+11,Inventory.COMBOSSELF+1);
		assertEquals(null,in.getItem(Inventory.COMBOSSELF+1));
		assertEquals(knife,in.getItem(Inventory.INVWEAPONS+11));

		in.move(Inventory.INVWEAPONS+15,Inventory.COMBOSSELF+1);
		assertEquals(explosionWand,in.getItem(Inventory.COMBOSSELF+1));
		assertEquals(explosionWand,in.getItem(Inventory.INVWEAPONS+15));

		in.move(Inventory.INVWEAPONS+15,Inventory.COMBOSSELF+2);
		assertEquals(explosionWand,in.getItem(Inventory.COMBOSSELF+1));
		assertEquals(explosionWand,in.getItem(Inventory.COMBOSSELF+2));
		assertEquals(explosionWand,in.getItem(Inventory.INVWEAPONS+15));
		
		in.move(Inventory.COMBOSSELF+2,Inventory.COMBOSSELF+1);
		assertEquals(explosionWand,in.getItem(Inventory.COMBOSSELF+1));
		assertEquals(explosionWand,in.getItem(Inventory.COMBOSSELF+2));
		
		in.move(Inventory.COMBOSSELF+3,Inventory.COMBOSSELF+1);
		assertEquals(explosionWand,in.getItem(Inventory.COMBOSSELF+1));
		assertEquals(explosionWand,in.getItem(Inventory.COMBOSSELF+2));
		assertEquals(null,in.getItem(Inventory.COMBOSSELF+3));

		in.move(Inventory.INVMISC+1,Inventory.COMBOSSELF+3);
		assertEquals(potion,in.getItem(Inventory.COMBOSSELF+3));
		assertEquals(explosionWand,in.getItem(Inventory.COMBOSSELF+1));
		in.move(Inventory.COMBOSSELF+3,Inventory.COMBOSSELF+1);
		assertEquals(potion,in.getItem(Inventory.COMBOSSELF+1));
		assertEquals(explosionWand,in.getItem(Inventory.COMBOSSELF+3));

		in.move(Inventory.INVWEAPONS+11,Inventory.COMBOS+1*10+1);
		assertEquals(knife,in.getItem(Inventory.COMBOS+1*10+1));
		assertEquals(knife,in.getItem(Inventory.INVWEAPONS+11));
		assertEquals(null,in.getItem(Inventory.COMBOS+1*10+2));

		in.move(Inventory.INVWEAPONS+12,Inventory.COMBOS+1*10+2);
		assertEquals(shield,in.getItem(Inventory.COMBOS+1*10+2));
		assertEquals(shield,in.getItem(Inventory.INVWEAPONS+12));
		assertEquals(knife,in.getItem(Inventory.COMBOS+1*10+1));
		assertEquals(knife,in.getItem(Inventory.INVWEAPONS+11));

		in.move(Inventory.INVWEAPONS+13,Inventory.COMBOS+1*10+1);
		assertEquals(bow,in.getItem(Inventory.COMBOS+1*10+1));
		assertEquals(bow,in.getItem(Inventory.INVWEAPONS+13));
		assertEquals(null,in.getItem(Inventory.COMBOS+1*10+2));

		in.move(Inventory.INVWEAPONS+14,Inventory.COMBOS+1*10+1);
		assertEquals(arrow,in.getItem(Inventory.COMBOS+1*10+1));
		assertEquals(bow,in.getItem(Inventory.INVWEAPONS+13));
		assertEquals(arrow,in.getItem(Inventory.INVWEAPONS+14));
		assertEquals(bow,in.getItem(Inventory.COMBOS+1*10+2));

		in.move(Inventory.INVWEAPONS+13,Inventory.COMBOS+1*10+1);
		assertEquals(bow,in.getItem(Inventory.COMBOS+1*10+1));
		assertEquals(bow,in.getItem(Inventory.INVWEAPONS+13));
		assertEquals(arrow,in.getItem(Inventory.COMBOS+1*10+2));

		in.move(Inventory.INVWEAPONS+14,Inventory.COMBOS+1*10+2);
		assertEquals(arrow,in.getItem(Inventory.COMBOS+1*10+2));
		assertEquals(bow,in.getItem(Inventory.INVWEAPONS+13));
		assertEquals(arrow,in.getItem(Inventory.INVWEAPONS+14));
		assertEquals(bow,in.getItem(Inventory.COMBOS+1*10+1));

		in.move(Inventory.COMBOS+1*10+2,Inventory.COMBOS+2*10+1);
		in.move(Inventory.COMBOS+2*10+1,Inventory.COMBOS+1*10+1);
		assertEquals(arrow,in.getItem(Inventory.COMBOS+1*10+1));
		assertEquals(null,in.getItem(Inventory.COMBOS+2*10+1));
		assertEquals(bow,in.getItem(Inventory.COMBOS+1*10+2));

		in.move(Inventory.INVWEAPONS+11,Inventory.COMBOS+1*10+1);
		assertEquals(knife,in.getItem(Inventory.COMBOS+1*10+1));
		assertEquals(knife,in.getItem(Inventory.INVWEAPONS+11));
		assertEquals(null,in.getItem(Inventory.COMBOS+1*10+2));

		in.move(Inventory.INVWEAPONS+13,Inventory.COMBOS+1*10+1);
		assertEquals(bow,in.getItem(Inventory.COMBOS+1*10+1));
		assertEquals(bow,in.getItem(Inventory.INVWEAPONS+13));
		assertEquals(null,in.getItem(Inventory.COMBOS+1*10+2));

		in.move(Inventory.INVWEAPONS+14,Inventory.COMBOS+1*10+2);
		assertEquals(arrow,in.getItem(Inventory.COMBOS+1*10+2));
		assertEquals(bow,in.getItem(Inventory.INVWEAPONS+13));
		assertEquals(arrow,in.getItem(Inventory.INVWEAPONS+14));
		assertEquals(bow,in.getItem(Inventory.COMBOS+1*10+1));

		in.move(Inventory.COMBOS+1*10+1,Inventory.COMBOS+1*10+2);
		assertEquals(bow,in.getItem(Inventory.COMBOS+1*10+2));
		assertEquals(arrow,in.getItem(Inventory.COMBOS+1*10+1));

		in.move(Inventory.COMBOS+1*10+1,Inventory.COMBOS+1*10+2);
		assertEquals(arrow,in.getItem(Inventory.COMBOS+1*10+2));
		assertEquals(bow,in.getItem(Inventory.COMBOS+1*10+1));

		in.move(Inventory.INVWEAPONS+11,Inventory.COMBOS+2*10+1);
		assertEquals(knife,in.getItem(Inventory.COMBOS+2*10+1));
		assertEquals(knife,in.getItem(Inventory.INVWEAPONS+11));
		assertEquals(null,in.getItem(Inventory.COMBOS+2*10+2));

		in.move(Inventory.COMBOS+1*10+1,Inventory.COMBOS+2*10+2);
		assertEquals(null,in.getItem(Inventory.COMBOS+1*10+1));
		assertEquals(null,in.getItem(Inventory.COMBOS+2*10+1));
		assertEquals(bow,in.getItem(Inventory.COMBOS+2*10+2));

		in.move(Inventory.INVWEAPONS+15,Inventory.COMBOS+5*10+1);
		assertEquals(explosionWand,in.getItem(Inventory.COMBOS+5*10+1));
		assertEquals(explosionWand,in.getItem(Inventory.INVWEAPONS+15));
		assertEquals(null,in.getItem(Inventory.COMBOS+5*10+2));

		in.move(Inventory.INVWEAPONS+16,Inventory.COMBOS+6*10+1);
		assertEquals(fireWand,in.getItem(Inventory.COMBOS+6*10+1));
		assertEquals(fireWand,in.getItem(Inventory.INVWEAPONS+16));
		assertEquals(null,in.getItem(Inventory.COMBOS+6*10+2));

		in.move(Inventory.INVWEAPONS+11,Inventory.COMBOS+6*10+2);
		assertEquals(knife,in.getItem(Inventory.COMBOS+6*10+2));
		assertEquals(fireWand,in.getItem(Inventory.INVWEAPONS+16));
		assertEquals(null,in.getItem(Inventory.COMBOS+6*10+1));

		in.move(Inventory.INVWEAPONS+11,Inventory.COMBOS+7*10+1);
		assertEquals(knife,in.getItem(Inventory.COMBOS+6*10+2));
		assertEquals(knife,in.getItem(Inventory.COMBOS+7*10+1));

		in.move(Inventory.COMBOS+7*10+1,Inventory.NONE);
		assertEquals(knife,in.getItem(Inventory.INVWEAPONS+11));
		assertEquals(knife,in.getItem(Inventory.COMBOS+6*10+2));
		assertEquals(null,in.getItem(Inventory.COMBOS+7*10+1));

		in.move(Inventory.INVWEAPONS+11,Inventory.COMBOS+7*10+1);
		in.move(Inventory.COMBOS+7*10+1,Inventory.TRASH);
		assertEquals(null,in.getItem(Inventory.INVWEAPONS+11));
		assertEquals(null,in.getItem(Inventory.COMBOS+6*10+2));
		assertEquals(null,in.getItem(Inventory.COMBOS+7*10+1));

		assertEquals(knife,in.getDrop().get(0));
		in.getDrop().clear();

		in.setItem(Inventory.INVWEAPONS+11,knife);
		in.move(Inventory.INVWEAPONS+11,Inventory.COMBOS+7*10+1);
		in.move(Inventory.INVWEAPONS+11,Inventory.TRASH);
		assertEquals(null,in.getItem(Inventory.INVWEAPONS+11));
		assertEquals(null,in.getItem(Inventory.COMBOS+6*10+2));
		assertEquals(null,in.getItem(Inventory.COMBOS+7*10+1));

		assertEquals(knife,in.getDrop().get(0));
		in.getDrop().clear();
		
		in.setItem(Inventory.INVWEAPONS+11,knife);
		in.move(Inventory.INVWEAPONS+11,Inventory.COMBOS+7*10+1);
		in.move(Inventory.COMBOS+7*10+1,Inventory.ARMOR);
		assertEquals(null,in.getItem(Inventory.ARMOR));
}


	@Test
	public void spellsTest() {
		
		Inventory in = new Inventory();
		
		in.setItem(Inventory.INVMISC+1,potion);

		in.setItem(Inventory.INVSPELLS+1,fireSpell);
		in.setItem(Inventory.INVSPELLS+2,explosionSpell);
		
		assertEquals(fireSpell,in.getItem(Inventory.INVSPELLS+1));
		assertEquals(explosionSpell,in.getItem(Inventory.INVSPELLS+2));

		in.move(Inventory.INVSPELLS+1,Inventory.INVSPELLS+11);
		in.move(Inventory.INVSPELLS+2,Inventory.INVSPELLS+12);
		
		assertEquals(null,in.getItem(Inventory.INVSPELLS+1));
		assertEquals(fireSpell,in.getItem(Inventory.INVSPELLS+11));

		assertEquals(null,in.getItem(Inventory.INVSPELLS+2));
		assertEquals(explosionSpell,in.getItem(Inventory.INVSPELLS+12));

		in.move(Inventory.INVSPELLS+11,Inventory.INVSPELLS+12);
		
		assertEquals(explosionSpell,in.getItem(Inventory.INVSPELLS+11));
		assertEquals(fireSpell,in.getItem(Inventory.INVSPELLS+12));

		in.move(Inventory.INVSPELLS+11,Inventory.INVSPELLS+12);

		in.move(Inventory.INVSPELLS+11,Inventory.INVARMOR+1);
		assertEquals(null,in.getItem(Inventory.INVARMOR+1));
		assertEquals(fireSpell,in.getItem(Inventory.INVSPELLS+11));

		in.move(Inventory.INVSPELLS+11,Inventory.INVWEAPONS+1);
		assertEquals(null,in.getItem(Inventory.INVWEAPONS+1));
		assertEquals(fireSpell,in.getItem(Inventory.INVSPELLS+11));

		in.move(Inventory.INVSPELLS+11,Inventory.INVMISC+1);
		assertEquals(potion,in.getItem(Inventory.INVMISC+1));
		assertEquals(fireSpell,in.getItem(Inventory.INVSPELLS+11));

		in.move(Inventory.INVSPELLS+11,Inventory.ARMOR+1);
		assertEquals(null,in.getItem(Inventory.ARMOR+1));
		assertEquals(fireSpell,in.getItem(Inventory.INVSPELLS+11));

		in.move(Inventory.INVSPELLS+11,Inventory.HELMET+1);
		assertEquals(null,in.getItem(Inventory.HELMET+1));
		assertEquals(fireSpell,in.getItem(Inventory.INVSPELLS+11));

		in.move(Inventory.INVSPELLS+11,Inventory.ARMS+1);
		assertEquals(null,in.getItem(Inventory.ARMS+1));
		assertEquals(fireSpell,in.getItem(Inventory.INVSPELLS+11));

		in.move(Inventory.INVSPELLS+11,Inventory.AMULET+1);
		assertEquals(null,in.getItem(Inventory.AMULET+1));
		assertEquals(fireSpell,in.getItem(Inventory.INVSPELLS+11));

		in.move(Inventory.INVSPELLS+11,Inventory.BOOTS+1);
		assertEquals(null,in.getItem(Inventory.BOOTS+1));
		assertEquals(fireSpell,in.getItem(Inventory.INVSPELLS+11));

		in.move(Inventory.INVSPELLS+11,Inventory.RING1+1);
		assertEquals(null,in.getItem(Inventory.RING1+1));
		assertEquals(fireSpell,in.getItem(Inventory.INVSPELLS+11));

		in.move(Inventory.INVSPELLS+11,Inventory.RING2+1);
		assertEquals(null,in.getItem(Inventory.RING2+1));
		assertEquals(fireSpell,in.getItem(Inventory.INVSPELLS+11));

		in.move(Inventory.INVSPELLS+11,Inventory.COMBOSSELF+1);
		assertEquals(null,in.getItem(Inventory.COMBOSSELF+1));
		assertEquals(fireSpell,in.getItem(Inventory.INVSPELLS+11));

		in.move(Inventory.INVSPELLS+12,Inventory.COMBOSSELF+1);
		assertEquals(explosionSpell,in.getItem(Inventory.COMBOSSELF+1));
		assertEquals(explosionSpell,in.getItem(Inventory.INVSPELLS+12));

		in.move(Inventory.INVSPELLS+12,Inventory.COMBOSSELF+1);
		assertEquals(explosionSpell,in.getItem(Inventory.COMBOSSELF+1));
		assertEquals(explosionSpell,in.getItem(Inventory.INVSPELLS+12));

		in.move(Inventory.INVSPELLS+12,Inventory.COMBOSSELF+2);
		assertEquals(explosionSpell,in.getItem(Inventory.COMBOSSELF+1));
		assertEquals(explosionSpell,in.getItem(Inventory.COMBOSSELF+2));
		assertEquals(explosionSpell,in.getItem(Inventory.INVSPELLS+12));
		
		in.move(Inventory.COMBOSSELF+2,Inventory.COMBOSSELF+1);
		assertEquals(explosionSpell,in.getItem(Inventory.COMBOSSELF+1));
		assertEquals(explosionSpell,in.getItem(Inventory.COMBOSSELF+2));
		
		in.move(Inventory.COMBOSSELF+3,Inventory.COMBOSSELF+1);
		assertEquals(explosionSpell,in.getItem(Inventory.COMBOSSELF+1));
		assertEquals(explosionSpell,in.getItem(Inventory.COMBOSSELF+2));
		assertEquals(null,in.getItem(Inventory.COMBOSSELF+3));

		in.move(Inventory.INVMISC+1,Inventory.COMBOSSELF+3);
		assertEquals(potion,in.getItem(Inventory.COMBOSSELF+3));
		assertEquals(explosionSpell,in.getItem(Inventory.COMBOSSELF+1));
		in.move(Inventory.COMBOSSELF+3,Inventory.COMBOSSELF+1);
		assertEquals(potion,in.getItem(Inventory.COMBOSSELF+1));
		assertEquals(explosionSpell,in.getItem(Inventory.COMBOSSELF+3));

		in.move(Inventory.INVSPELLS+11,Inventory.COMBOS+1*10+1);
		assertEquals(fireSpell,in.getItem(Inventory.COMBOS+1*10+1));
		assertEquals(fireSpell,in.getItem(Inventory.INVSPELLS+11));
		assertEquals(null,in.getItem(Inventory.COMBOS+1*10+2));

		in.move(Inventory.COMBOS+1*10+1,Inventory.COMBOS+1*10+2);
		assertEquals(fireSpell,in.getItem(Inventory.COMBOS+1*10+2));
		assertEquals(null,in.getItem(Inventory.COMBOS+1*10+1));

		in.move(Inventory.COMBOS+2*10+1,Inventory.TRASH);
		assertEquals(fireSpell,in.getItem(Inventory.INVSPELLS+11));
		assertEquals(null,in.getItem(Inventory.COMBOS+2*10+1));

		in.move(Inventory.INVSPELLS+11,Inventory.COMBOS+1*10+1);
		in.move(Inventory.INVSPELLS+11,Inventory.TRASH);
		assertEquals(fireSpell,in.getItem(Inventory.INVSPELLS+11));
		assertEquals(fireSpell,in.getItem(Inventory.COMBOS+1*10+1));
		
		assertEquals(0,in.getDrop().size());
	}

	@Test
	public void miscTest() {
		
		Inventory in = new Inventory();
		
		in.setItem(Inventory.INVSPELLS+1,explosionSpell);

		in.setItem(Inventory.INVMISC+1,potion);
		in.setItem(Inventory.INVMISC+2,key);
		
		assertEquals(potion,in.getItem(Inventory.INVMISC+1));
		assertEquals(key,in.getItem(Inventory.INVMISC+2));

		in.move(Inventory.INVMISC+1,Inventory.INVMISC+11);
		in.move(Inventory.INVMISC+2,Inventory.INVMISC+12);
		
		assertEquals(null,in.getItem(Inventory.INVMISC+1));
		assertEquals(potion,in.getItem(Inventory.INVMISC+11));

		assertEquals(null,in.getItem(Inventory.INVMISC+2));
		assertEquals(key,in.getItem(Inventory.INVMISC+12));

		in.move(Inventory.INVMISC+11,Inventory.INVMISC+12);
		
		assertEquals(key,in.getItem(Inventory.INVMISC+11));
		assertEquals(potion,in.getItem(Inventory.INVMISC+12));

		in.move(Inventory.INVMISC+11,Inventory.INVMISC+12);

		in.move(Inventory.INVMISC+11,Inventory.INVARMOR+1);
		assertEquals(null,in.getItem(Inventory.INVARMOR+1));
		assertEquals(potion,in.getItem(Inventory.INVMISC+11));

		in.move(Inventory.INVMISC+11,Inventory.INVWEAPONS+1);
		assertEquals(null,in.getItem(Inventory.INVWEAPONS+1));
		assertEquals(potion,in.getItem(Inventory.INVMISC+11));

		in.move(Inventory.INVMISC+11,Inventory.INVSPELLS+1);
		assertEquals(explosionSpell,in.getItem(Inventory.INVSPELLS+1));
		assertEquals(potion,in.getItem(Inventory.INVMISC+11));

		in.move(Inventory.INVMISC+11,Inventory.ARMOR+1);
		assertEquals(null,in.getItem(Inventory.ARMOR+1));
		assertEquals(potion,in.getItem(Inventory.INVMISC+11));

		in.move(Inventory.INVMISC+11,Inventory.HELMET+1);
		assertEquals(null,in.getItem(Inventory.HELMET+1));
		assertEquals(potion,in.getItem(Inventory.INVMISC+11));

		in.move(Inventory.INVMISC+11,Inventory.ARMS+1);
		assertEquals(null,in.getItem(Inventory.ARMS+1));
		assertEquals(potion,in.getItem(Inventory.INVMISC+11));

		in.move(Inventory.INVMISC+11,Inventory.AMULET+1);
		assertEquals(null,in.getItem(Inventory.AMULET+1));
		assertEquals(potion,in.getItem(Inventory.INVMISC+11));

		in.move(Inventory.INVMISC+11,Inventory.BOOTS+1);
		assertEquals(null,in.getItem(Inventory.BOOTS+1));
		assertEquals(potion,in.getItem(Inventory.INVMISC+11));

		in.move(Inventory.INVMISC+11,Inventory.RING1+1);
		assertEquals(null,in.getItem(Inventory.RING1+1));
		assertEquals(potion,in.getItem(Inventory.INVMISC+11));

		in.move(Inventory.INVMISC+11,Inventory.RING2+1);
		assertEquals(null,in.getItem(Inventory.RING2+1));
		assertEquals(potion,in.getItem(Inventory.INVMISC+11));

		in.move(Inventory.INVMISC+11,Inventory.COMBOSSELF+1);
		assertEquals(potion,in.getItem(Inventory.COMBOSSELF+1));
		assertEquals(potion,in.getItem(Inventory.INVMISC+11));

		in.move(Inventory.INVMISC+12,Inventory.COMBOSSELF+1);
		assertEquals(potion,in.getItem(Inventory.COMBOSSELF+1));
		assertEquals(key,in.getItem(Inventory.INVMISC+12));

		in.move(Inventory.INVMISC+12,Inventory.COMBOSSELF+1);
		assertEquals(potion,in.getItem(Inventory.COMBOSSELF+1));
		assertEquals(key,in.getItem(Inventory.INVMISC+12));

		in.move(Inventory.INVMISC+11,Inventory.COMBOSSELF+2);
		assertEquals(potion,in.getItem(Inventory.COMBOSSELF+1));
		assertEquals(potion,in.getItem(Inventory.COMBOSSELF+2));
		assertEquals(potion,in.getItem(Inventory.INVMISC+11));
		
		in.move(Inventory.COMBOSSELF+2,Inventory.COMBOSSELF+1);
		assertEquals(potion,in.getItem(Inventory.COMBOSSELF+1));
		assertEquals(potion,in.getItem(Inventory.COMBOSSELF+2));
		
		in.move(Inventory.COMBOSSELF+3,Inventory.COMBOSSELF+1);
		assertEquals(potion,in.getItem(Inventory.COMBOSSELF+1));
		assertEquals(potion,in.getItem(Inventory.COMBOSSELF+2));
		assertEquals(null,in.getItem(Inventory.COMBOSSELF+3));

		in.move(Inventory.INVSPELLS+1,Inventory.COMBOSSELF+3);
		assertEquals(potion,in.getItem(Inventory.COMBOSSELF+1));
		assertEquals(explosionSpell,in.getItem(Inventory.COMBOSSELF+3));
		in.move(Inventory.COMBOSSELF+3,Inventory.COMBOSSELF+1);
		assertEquals(potion,in.getItem(Inventory.COMBOSSELF+3));
		assertEquals(explosionSpell,in.getItem(Inventory.COMBOSSELF+1));

		in.move(Inventory.INVMISC+11,Inventory.COMBOS+1*10+1);
		assertEquals(potion,in.getItem(Inventory.COMBOS+1*10+1));
		assertEquals(potion,in.getItem(Inventory.INVMISC+11));
		assertEquals(null,in.getItem(Inventory.COMBOS+1*10+2));

		in.move(Inventory.COMBOS+1*10+1,Inventory.COMBOS+1*10+2);
		assertEquals(potion,in.getItem(Inventory.COMBOS+1*10+2));
		assertEquals(null,in.getItem(Inventory.COMBOS+1*10+1));

		in.move(Inventory.INVMISC+11,Inventory.COMBOS+1*10+1);
		in.move(Inventory.INVMISC+11,Inventory.TRASH);
		assertEquals(null,in.getItem(Inventory.INVMISC+11));
		assertEquals(null,in.getItem(Inventory.COMBOS+1*10+1));

		assertEquals(potion,in.getDrop().get(0));
		in.getDrop().clear();
		
		in.setItem(Inventory.INVMISC+1,potion);
		in.move(Inventory.INVMISC+1,Inventory.COMBOS+1*10+1);
		in.move(Inventory.COMBOS+1*10+1,Inventory.TRASH);
		assertEquals(null,in.getItem(Inventory.INVMISC+11));
		assertEquals(null,in.getItem(Inventory.COMBOS+1*10+1));
		

		assertEquals(potion,in.getDrop().get(0));
	}
	
	@Test
	public void noAutomaticManagementTest() {
		Inventory in = new Inventory();
		in.setAutomaticManager(false);

		Item items[] = new Item[Constants.inventorySize+1];
		
		for (int i = 0;i < Constants.inventorySize+1;i++) {
			items[i] = new Item();
			Item item = items[i];
			item.setItemType(ItemType.ARMOR);
			item.getStats().setDefense(1f);
			item.setShadow(0.5f);
			item.setRadius(14f);
			item.setHeight(28f);
			in.pickup(item);
			if (i < Constants.inventorySize) {
				assertEquals(items[i],in.getItem(Inventory.INVARMOR+i+1));
				assertEquals(i+1,in.getNewItems().size());
			}
			else
				assertNull(in.getItem(Inventory.INVARMOR+i+1));
		}

		for (int i = 0;i < Constants.inventorySize+1;i++) {
			items[i] = new Item();
			Item item = items[i];
			item.setItemType(ItemType.SHIELD);
			item.getStats().setDefense(1f);
			item.setShadow(0.5f);
			item.setRadius(14f);
			item.setHeight(28f);
			in.pickup(item);
			if (i < Constants.inventorySize) {
				assertEquals(items[i],in.getItem(Inventory.INVWEAPONS+i+1));
				assertEquals(i+1+Constants.inventorySize,in.getNewItems().size());
			}
			else
				assertNull(in.getItem(Inventory.INVWEAPONS+i+1));
		}

		for (int i = 0;i < Constants.inventorySize+1;i++) {
			items[i] = new Item();
			Item item = items[i];
			item.setItemType(ItemType.SPELL);
			item.getStats().setDefense(1f);
			item.setShadow(0.5f);
			item.setRadius(14f);
			item.setHeight(28f);
			in.pickup(item);
			if (i < 1) {
				assertEquals(items[i],in.getItem(Inventory.INVSPELLS+i+1));
			}				
			else
				assertNull(in.getItem(Inventory.INVSPELLS+i+1));
		}

	}
	
	@Test
	public void automaticManagementTest1() {
		Inventory in = new Inventory();
		in.setAutomaticManager(true);

		Item items[] = new Item[Constants.inventorySize+1];
		
		for (int i = 0;i < Constants.inventorySize+1;i++) {
			items[i] = new Item();
			Item item = items[i];
			item.setItemType(ItemType.ARMOR);
			item.getStats().setDefense(1f);
			item.setShadow(0.5f);
			item.setRadius(14f);
			item.setHeight(28f);
			in.pickup(item);
			if (i == 0)
				assertEquals(items[i],in.getItem(Inventory.INVARMOR+i+1));
			else
				assertNull(in.getItem(Inventory.INVARMOR+i+1));
			assertEquals(0,in.getNewItems().size());
		}

		for (int i = 0;i < Constants.inventorySize+1;i++) {
			items[i] = new Item();
			Item item = items[i];
			item.setItemType(ItemType.SHIELD);
			item.getStats().setDefense(1f);
			item.setShadow(0.5f);
			item.setRadius(14f);
			item.setHeight(28f);
			in.pickup(item);
			if (i == 0)
				assertEquals(items[i],in.getItem(Inventory.INVWEAPONS+i+1));
			else
				assertNull(in.getItem(Inventory.INVWEAPONS+i+1));
			assertEquals(0,in.getNewItems().size());
		}

		for (int i = 0;i < Constants.inventorySize+1;i++) {
			items[i] = new Item();
			Item item = items[i];
			item.setItemType(ItemType.SPELL);
			item.getStats().setDefense(1f);
			item.setShadow(0.5f);
			item.setRadius(14f);
			item.setHeight(28f);
			in.pickup(item);
			if (i == 0)
				assertEquals(items[i],in.getItem(Inventory.INVSPELLS+i+1));
			else
				assertNull(in.getItem(Inventory.INVSPELLS+i+1));
			assertEquals(0,in.getNewItems().size());
		}

	}
	

	@Test
	public void automaticManagementTest2() {
		Inventory in = new Inventory();
		in.setAutomaticManager(true);
		Item item;
		
		assertNull(in.getItem(Inventory.ARMOR));
		assertEquals(0,in.getNewItems().size());

		item = new Item();
		Item armor1 = item;
		item.setItemType(ItemType.ARMOR);
		item.getStats().setDefense(1f);
		item.setShadow(0.5f);
		item.setRadius(14f);
		item.setHeight(28f);
		in.pickup(item);

		assertEquals(armor1,in.getItem(Inventory.ARMOR));
		assertEquals(0,in.getNewItems().size());

		item = new Item();
		Item armor2 = item;
		item.setItemType(ItemType.ARMOR);
		item.getStats().setDefense(2f);
		item.setShadow(0.5f);
		item.setRadius(14f);
		item.setHeight(28f);
		in.pickup(item);
		
		assertEquals(armor1,in.getDrop().get(0));
		assertEquals(armor2,in.getItem(Inventory.INVARMOR+1));
		assertEquals(armor2,in.getItem(Inventory.ARMOR));

		in.getDrop().clear();
		in.pickup(armor1);
		assertEquals(0,in.getNewItems().size());

		assertEquals(armor1,in.getDrop().get(0));
		assertEquals(armor2,in.getItem(Inventory.INVARMOR+1));

		in.getDrop().clear();

		item = new Item();
		Item armor3 = item;
		item.setItemType(ItemType.ARMOR);
		item.getStats().setDefense(1f);
		item.setShadow(0.5f);
		item.setRadius(14f);
		item.setHeight(28f);
		item.getStats().setAttack(1f);
		in.pickup(item);
		
		assertEquals(1,in.getNewItems().size());

		assertTrue(in.getDrop().isEmpty());
		assertEquals(armor2,in.getItem(Inventory.INVARMOR+1));
		assertEquals(armor3,in.getItem(Inventory.INVARMOR+2));
		assertEquals(armor2,in.getItem(Inventory.ARMOR));

		item = new Item();
		Item armor4 = item;
		item.setItemType(ItemType.ARMOR);
		item.getStats().setDefense(1f);
		item.setShadow(0.5f);
		item.setRadius(14f);
		item.setHeight(28f);
		item.getStats().setAttack(2f);
		in.pickup(item);

		assertEquals(1,in.getNewItems().size());
		assertEquals(armor3,in.getDrop().get(0));
		assertEquals(armor2,in.getItem(Inventory.INVARMOR+1));
		assertEquals(armor4,in.getItem(Inventory.INVARMOR+2));
		assertEquals(armor2,in.getItem(Inventory.ARMOR));

		in.getDrop().clear();
		
		item = new Item();
		Item armor5 = item;
		item.setItemType(ItemType.ARMOR);
		item.getStats().setDefense(2f);
		item.setShadow(0.5f);
		item.setRadius(14f);
		item.setHeight(28f);
		item.getStats().setAttack(2f);
		in.pickup(item);

		assertEquals(0,in.getNewItems().size());
		assertTrue(in.getDrop().contains(armor2));
		assertTrue(in.getDrop().contains(armor4));
		assertTrue(in.getItem(Inventory.INVARMOR+1)==armor5||in.getItem(Inventory.INVARMOR+1)==armor4);
		assertEquals(armor5,in.getItem(Inventory.ARMOR));
	}

	@Test
	public void automaticManagementTest3() {
		for (int i = 0;i < 2;i++) {
			Inventory in = new Inventory();
			in.setAutomaticManager(i==0);
			Item item;
	
			Item arrow2;
			item = new Item();
	        arrow2 = item;
	        item.setItemID(ItemID.arrows);
			item.setItemType(ItemType.AMMO);
			item.setHeight(5);
			item.setRadius(16);
			item.setIsStackable(true);
			item.setQuantity(10);
			item.setProvidesAmmoID(AmmoID.arrow);
			item.setShadow(0.5f);
			item.setAttackHorizontalAngleVariation(1f);
			item.setAttackSpeed(500f);
			item.setAttackRadius(5f);
			item.setAttackShadow(0.5f);
			item.setAttackRange(400f);
			item.setAttackGravity(-0.3f);
			item.setAttackMinVerticalAngle(-20f);
			item.setAttackMaxVerticalAngle(35f);
			item.getStats().setAttack(1f);
			item.getStats().setRange(10f);

			Item arrow3;
			item = new Item();
	        arrow3 = item;
			item.setItemType(ItemType.AMMO);
	        item.setItemID(ItemID.arrows);
			item.setHeight(5);
			item.setRadius(16);
			item.setIsStackable(true);
			item.setQuantity(12);
			item.setProvidesAmmoID(AmmoID.arrow);
			item.setShadow(0.5f);
			item.setAttackHorizontalAngleVariation(1f);
			item.setAttackSpeed(500f);
			item.setAttackRadius(5f);
			item.setAttackShadow(0.5f);
			item.setAttackRange(400f);
			item.setAttackGravity(-0.3f);
			item.setAttackMinVerticalAngle(-20f);
			item.setAttackMaxVerticalAngle(35f);
			item.getStats().setAttack(1f);
			item.getStats().setRange(10f);

			in.pickup(arrow2);
			assertEquals(10,in.getItem(Inventory.INVWEAPONS+1).getQuantity());
			in.pickup(arrow3);
			assertEquals(22,in.getItem(Inventory.INVWEAPONS+1).getQuantity());
		}

	}

	@Test
	public void automaticManagementTest4() {
		Inventory in = new Inventory();
		in.setAutomaticManager(true);
		
		assertNull(in.getItem(Inventory.COMBOSSELF+1));

		in.pickup(potion);

		assertEquals(potion,in.getItem(Inventory.COMBOSSELF+1));
		assertNull(in.getItem(Inventory.COMBOSSELF+2));

		in.pickup(potion2);

		assertEquals(potion2,in.getItem(Inventory.COMBOSSELF+2));

		in.pickup(potion3);

		assertEquals(potion3,in.getItem(Inventory.COMBOSSELF+3));

		in.pickup(potion4);

		assertEquals(potion4,in.getItem(Inventory.COMBOSSELF+4));

		in.pickup(potion5);

		assertEquals(potion5,in.getItem(Inventory.COMBOS+0*10+1));

		in.pickup(knife);

		assertEquals(knife,in.getItem(Inventory.COMBOS+1*10+1));

		in.pickup(knife);

		assertEquals(knife,in.getItem(Inventory.COMBOS+1*10+1));

		in.pickup(bow);

		assertEquals(bow,in.getItem(Inventory.COMBOS+2*10+1));

		in.pickup(arrow);

		assertEquals(arrow,in.getItem(Inventory.COMBOS+2*10+2));

		in.pickup(shield);

		assertEquals(shield,in.getItem(Inventory.COMBOS+1*10+2));

		in.pickup(fireWand);

		assertEquals(fireWand,in.getItem(Inventory.COMBOS+3*10+1));

		in.pickup(fireSpell);

		assertEquals(fireSpell,in.getItem(Inventory.COMBOS+4*10+1));

		assertEquals(0,in.getNewItems().size());

		Item item;
		
		item = new Item();
		item.setItemType(ItemType.WEAPONDOESNOTNEEDAMMO);
		item.setAttacks(true);
		item.setHeight(32);
		item.setRadius(5);
		item.setRequiresTarget(true);
		item.setAutoFire(true);
		item.setShadow(0.5f);
		item.setRecoveryTime(0.1f);
		item.setAttackHorizontalAngleVariation(4f);
		item.setAttackSpeed(245f);
		item.setAttackRadius(10f);
		item.setAttackShadow(0f);
		item.setAttackHeight(30f);
		item.getStats().setAttack(1f);
		item.getStats().setRange(10f);
		in.pickup(item);
		assertEquals(item,in.getItem(Inventory.COMBOS+5*10+1));

		item = new Item();
		item.setItemType(ItemType.WEAPONDOESNOTNEEDAMMO);
		item.setAttacks(true);
		item.setHeight(32);
		item.setRadius(5);
		item.setRequiresTarget(true);
		item.setAutoFire(true);
		item.setShadow(0.5f);
		item.setRecoveryTime(0.2f);
		item.setAttackHorizontalAngleVariation(4f);
		item.setAttackSpeed(240f);
		item.setAttackRadius(18f);
		item.setAttackShadow(0f);
		item.setAttackHeight(30f);
		item.getStats().setAttack(1f);
		item.getStats().setRange(10f);
		in.pickup(item);
		assertEquals(item,in.getItem(Inventory.COMBOS+6*10+1));

		item = new Item();
		item.setItemType(ItemType.WEAPONDOESNOTNEEDAMMO);
		item.setAttacks(true);
		item.setHeight(32);
		item.setRadius(5);
		item.setRequiresTarget(true);
		item.setAutoFire(true);
		item.setShadow(0.5f);
		item.setRecoveryTime(0.1f);
		item.setAttackHorizontalAngleVariation(4f);
		item.setAttackSpeed(240f);
		item.setAttackRadius(15f);
		item.setAttackShadow(0f);
		item.setAttackHeight(30f);
		item.getStats().setAttack(1f);
		item.getStats().setRange(10f);
		in.pickup(item);
		assertEquals(item,in.getItem(Inventory.COMBOS+7*10+1));

		item = new Item();
		item.setItemType(ItemType.WEAPONDOESNOTNEEDAMMO);
		item.setAttacks(true);
		item.setHeight(32);
		item.setRadius(5);
		item.setRequiresTarget(true);
		item.setAutoFire(true);
		item.setShadow(0.5f);
		item.setRecoveryTime(0.14f);
		item.setAttackHorizontalAngleVariation(46f);
		item.setAttackSpeed(240f);
		item.setAttackRadius(12f);
		item.setAttackShadow(0f);
		item.setAttackHeight(30f);
		item.getStats().setAttack(1f);
		item.getStats().setRange(10f);
		in.pickup(item);
		assertEquals(item,in.getItem(Inventory.COMBOS+8*10+1));

		item = new Item();
		item.setItemType(ItemType.WEAPONDOESNOTNEEDAMMO);
		item.setAttacks(true);
		item.setHeight(32);
		item.setRadius(5);
		item.setRequiresTarget(false);
		item.setAutoFire(false);
		item.setShadow(0.5f);
		item.setRecoveryTime(0.1f);
		item.setAttackHorizontalAngleVariation(4f);
		item.setAttackSpeed(240f);
		item.setAttackRadius(10f);
		item.setAttackShadow(0f);
		item.setAttackHeight(30f);
		item.getStats().setAttack(2f);
		item.getStats().setRange(8f);
		in.pickup(item);
		assertEquals(item,in.getItem(Inventory.COMBOS+9*10+1));

		assertEquals(0,in.getNewItems().size());

		item = new Item();
		item.setItemType(ItemType.WEAPONDOESNOTNEEDAMMO);
		item.setAttacks(true);
		item.setHeight(32);
		item.setRadius(5);
		item.setRequiresTarget(true);
		item.setAutoFire(true);
		item.setShadow(0.5f);
		item.setRecoveryTime(0.1f);
		item.setAttackHorizontalAngleVariation(4f);
		item.setAttackSpeed(240f);
		item.setAttackRadius(15f);
		item.setAttackShadow(0f);
		item.setAttackHeight(30f);
		item.getStats().setAttack(0.2f);
		item.getStats().setRange(11f);
		in.pickup(item);
		
		assertEquals(1,in.getNewItems().size());

		item = new Item();
		item.setItemType(ItemType.WEAPONDOESNOTNEEDAMMO);
		item.setAttacks(true);
		item.setHeight(32);
		item.setRadius(5);
		item.setRequiresTarget(true);
		item.setAutoFire(true);
		item.setShadow(0.5f);
		item.setRecoveryTime(0.1f);
		item.setAttackHorizontalAngleVariation(4f);
		item.setAttackSpeed(240f);
		item.setAttackRadius(15f);
		item.setAttackShadow(0f);
		item.setAttackHeight(30f);
		item.getStats().setAttack(10f);
		item.getStats().setRange(100f);
		in.pickup(item);
		
		assertEquals(0,in.getNewItems().size());

	}

	@Test
	public void automaticManagementTest5() {
		Inventory in = new Inventory();
		in.setAutomaticManager(true);

		Item item;
		
		Item bow1;
		Item arrow1;
		Item arrow2;

		Item crossbow1;
		Item bolt1;
		Item bolt2;

		item = new Item();
		bow1 = item;
        item.setItemID(ItemID.bow);
		item.setItemType(ItemType.WEAPONNEEDSAMMO);
		item.setAttacks(true);
		item.setHeight(32);
		item.setRadius(16);
		item.setRequiresAmmoID(AmmoID.arrow);
		item.setRequiresTarget(true);
		item.setShadow(0.5f);
		item.setRecoveryTime(0.40f);
		item.setAttackHorizontalAngleVariation(3f);
		item.setAttackSpeed(300f);
		item.getStats().setDefense(0.2f);
		item.getStats().setAttack(1f);

		item = new Item();
		arrow1 = item;
        item.setItemID(ItemID.arrows);
		item.setItemType(ItemType.AMMO);
		item.setHeight(5);
		item.setRadius(16);
		item.setIsStackable(true);
		item.setProvidesAmmoID(AmmoID.arrow);
		item.setShadow(0.5f);
		item.setAttackHorizontalAngleVariation(1f);
		item.setAttackSpeed(500f);
		item.setAttackRadius(5f);
		item.setAttackShadow(0.5f);
		item.setAttackRange(400f);
		item.setAttackGravity(-0.3f);
		item.setAttackMinVerticalAngle(-20f);
		item.setAttackMaxVerticalAngle(35f);
		item.getStats().setAttack(1f);
		item.getStats().setRange(10f);
		item.setQuantity(10);

		item = new Item();
		arrow2 = item;
        item.setItemID(ItemID.arrows);
		item.setItemType(ItemType.AMMO);
		item.setHeight(5);
		item.setRadius(16);
		item.setIsStackable(true);
		item.setProvidesAmmoID(AmmoID.arrow);
		item.setShadow(0.5f);
		item.setAttackHorizontalAngleVariation(1f);
		item.setAttackSpeed(500f);
		item.setAttackRadius(5f);
		item.setAttackShadow(0.5f);
		item.setAttackRange(400f);
		item.setAttackGravity(-0.3f);
		item.setAttackMinVerticalAngle(-20f);
		item.setAttackMaxVerticalAngle(35f);
		item.getStats().setAttack(1f);
		item.getStats().setRange(10f);
		item.setQuantity(20);

		item = new Item();
		crossbow1 = item;
        item.setItemID(ItemID.crossbow);
		item.setItemType(ItemType.WEAPONNEEDSAMMO);
		item.setAttacks(true);
		item.setHeight(32);
		item.setRadius(16);
		item.setRequiresAmmoID(AmmoID.bolt);
		item.setRequiresTarget(true);
		item.setShadow(0.5f);
		item.setRecoveryTime(0.40f);
		item.setAttackHorizontalAngleVariation(3f);
		item.setAttackSpeed(300f);
		item.getStats().setDefense(0.2f);
		item.getStats().setAttack(1f);
		item.getStats().setRange(10f);

		item = new Item();
		bolt1 = item;
        item.setItemID(ItemID.bolts);
		item.setItemType(ItemType.AMMO);
		item.setHeight(5);
		item.setRadius(16);
		item.setIsStackable(true);
		item.setProvidesAmmoID(AmmoID.bolt);
		item.setShadow(0.5f);
		item.setAttackHorizontalAngleVariation(1f);
		item.setAttackSpeed(500f);
		item.setAttackRadius(5f);
		item.setAttackShadow(0.5f);
		item.setAttackRange(400f);
		item.setAttackGravity(-0.3f);
		item.setAttackMinVerticalAngle(-20f);
		item.setAttackMaxVerticalAngle(35f);
		item.setQuantity(10);
		item.getStats().setAttack(1f);
		item.getStats().setRange(10f);

		item = new Item();
		bolt2 = item;
        item.setItemID(ItemID.bolts);
		item.setItemType(ItemType.AMMO);
		item.setHeight(5);
		item.setRadius(16);
		item.setIsStackable(true);
		item.setProvidesAmmoID(AmmoID.bolt);
		item.setShadow(0.5f);
		item.setAttackHorizontalAngleVariation(1f);
		item.setAttackSpeed(500f);
		item.setAttackRadius(5f);
		item.setAttackShadow(0.5f);
		item.setAttackRange(400f);
		item.setAttackGravity(-0.3f);
		item.setAttackMinVerticalAngle(-20f);
		item.setAttackMaxVerticalAngle(35f);
		item.setQuantity(20);
		item.getStats().setAttack(1f);
		item.getStats().setRange(10f);

		in.pickup(bow1);
		in.pickup(arrow1);
		in.pickup(arrow2);

		assertEquals(30,in.getItem(Inventory.COMBOS+0*10+2).getQuantity());
		assertNull(in.getItem(Inventory.COMBOS+1*10+1));
		assertEquals(bow1,in.getItem(Inventory.COMBOS+0*10+1));
		assertEquals(arrow1,in.getItem(Inventory.COMBOS+0*10+2));

		in.pickup(bolt1);
		in.pickup(bolt2);
		in.pickup(crossbow1);
		
		assertEquals(30,in.getItem(Inventory.COMBOS+1*10+1).getQuantity());
		assertEquals(crossbow1,in.getItem(Inventory.COMBOS+1*10+2));
		assertEquals(bolt1,in.getItem(Inventory.COMBOS+1*10+1));
	}

	@Test
	public void replaceItemTest() {

		Inventory in = new Inventory();
		in.setAutomaticManager(false);

		in.setItem(Inventory.INVARMOR+1,amulet);
		in.setItem(Inventory.INVARMOR+2,armor);
		in.setItem(Inventory.INVARMOR+3,helmet);
		in.setItem(Inventory.INVARMOR+4,arms);
		in.setItem(Inventory.INVARMOR+5,legs);
		in.setItem(Inventory.INVARMOR+6,boots);
		in.setItem(Inventory.INVARMOR+7,ring1);
		in.setItem(Inventory.INVARMOR+8,ring2);
		in.setItem(Inventory.INVARMOR+9,ring3);

		in.setItem(Inventory.INVWEAPONS+1,knife);
		in.setItem(Inventory.INVWEAPONS+2,shield);
		in.setItem(Inventory.INVWEAPONS+3,bow);
		in.setItem(Inventory.INVWEAPONS+4,arrow);
		in.setItem(Inventory.INVWEAPONS+5,explosionWand);
		in.setItem(Inventory.INVWEAPONS+6,fireWand);
		
		in.setItem(Inventory.INVSPELLS+1,fireSpell);
		in.setItem(Inventory.INVSPELLS+2,explosionSpell);

		in.setItem(Inventory.INVMISC+1,potion);

		assertEquals(Inventory.ARMOR,in.getReplaceSlot(armor));
		assertEquals(Inventory.AMULET,in.getReplaceSlot(amulet));
		assertEquals(Inventory.HELMET,in.getReplaceSlot(helmet));
		assertEquals(Inventory.ARMS,in.getReplaceSlot(arms));
		assertEquals(Inventory.LEGS,in.getReplaceSlot(legs));
		assertEquals(Inventory.BOOTS,in.getReplaceSlot(boots));
		assertEquals(Inventory.RING1,in.getReplaceSlot(ring2));
		in.replaceItem(ring2);
		assertEquals(Inventory.RING2,in.getReplaceSlot(ring1));
		in.replaceItem(ring1);
		assertEquals(Inventory.RING1,in.getReplaceSlot(ring3));

		assertEquals(Inventory.COMBOS+0*10+1,in.getReplaceSlot(knife));
		in.replaceItem(knife);
		assertEquals(knife,in.getItem(Inventory.COMBOS+0*10+1));
		assertEquals(Inventory.COMBOS+0*10+2,in.getReplaceSlot(shield));
		in.replaceItem(shield);
		assertEquals(shield,in.getItem(Inventory.COMBOS+0*10+2));
		in.setChosenCombo(1);
		assertEquals(Inventory.COMBOS+1*10+1,in.getReplaceSlot(knife));
		in.replaceItem(knife);
		assertEquals(knife,in.getItem(Inventory.COMBOS+1*10+1));
		assertEquals(Inventory.COMBOS+1*10+2,in.getReplaceSlot(shield));
		in.replaceItem(shield);
		assertEquals(shield,in.getItem(Inventory.COMBOS+1*10+2));
		in.setChosenCombo(2);
		assertEquals(Inventory.COMBOS+2*10+1,in.getReplaceSlot(bow));
		in.replaceItem(bow);
		assertEquals(bow,in.getItem(Inventory.COMBOS+2*10+1));
		assertEquals(Inventory.COMBOS+2*10+2,in.getReplaceSlot(arrow));
		in.replaceItem(arrow);
		assertEquals(arrow,in.getItem(Inventory.COMBOS+2*10+2));

		assertEquals(Inventory.COMBOS+2*10+1,in.getReplaceSlot(knife));
		in.replaceItem(knife);
		assertEquals(knife,in.getItem(Inventory.COMBOS+2*10+1));
		assertEquals(Inventory.COMBOS+2*10+1,in.getReplaceSlot(arrow));
		in.replaceItem(arrow);
		assertEquals(arrow,in.getItem(Inventory.COMBOS+2*10+1));
		
	}

	@Test
	public void betterItemTest() {

		Inventory in = new Inventory();
		in.setAutomaticManager(false);

		in.setItem(Inventory.INVARMOR+1,armor);
		in.setItem(Inventory.INVARMOR+2,armor2);
		in.setItem(Inventory.ARMOR, armor);
		assertEquals(1,in.getStats().getDefense(),Constants.EPS);
		assertEquals(2,in.getStatsWithReplace(armor2).getDefense(),Constants.EPS);

	}

}
