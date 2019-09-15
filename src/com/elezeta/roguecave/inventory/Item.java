package com.elezeta.roguecave.inventory;

import java.util.HashMap;
import java.util.Map;

import com.elezeta.roguecave.Constants;
import com.elezeta.roguecave.data.AmmoID;
import com.elezeta.roguecave.data.ItemID;
import com.elezeta.roguecave.entities.Stats;
import com.elezeta.roguecave.sprites.SingleSprite;
import com.elezeta.roguecave.sprites.SpriteSlotID;

public class Item {

	private ItemID itemId = null;
	
	private ItemType itemType = null;

	private Stats stats = new Stats();
	
	private boolean twoHanded = false;
	private boolean isStackable = false;
	private boolean requiresTarget = false;
	private boolean autoFire = false;

	private float height = 0f;
	private float radius = 0f;
	private float shadow = 0.5f;
	
	private SingleSprite sprite = null;
	private SingleSprite inventorySprite = null;
	private Map<SpriteSlotID,SingleSprite> equipmentSprite = new HashMap<SpriteSlotID,SingleSprite>();
	
	private int quantity = 1;
		
	private float recoveryTime = 0f;
	private float manaCost = 0f;
	private float heal = 0f;

	private AmmoID requiresAmmoID = null;
	private AmmoID providesAmmoID = null;

	private boolean attackHitCeiling = true;
	private float attackHorizontalAngleVariation = 0f;
	private float attackVerticalAngleVariation = 0f;
	private float attackMinVerticalAngle = 0f;
	private float attackMaxVerticalAngle = 0f;
	private float attackSpeed = 0f;
	private float attackSpeedVariation = 0f;
	private float attackRadius = 0f;
	private float attackHeight = 0f;
	private SingleSprite attackSprite = null;
	private float attackShadow = 0f;
	private float attackGravity = 0f;
	private float attackRange = 0f;

	private boolean attackBurst = false;
	private float attackBurstTime = 0f;
	private float attackBurstInterval = 0f;
	private boolean attackBurstScanning = false;
	private float attackBurstScanningMin = 0f;
	private float attackBurstScanningMax = 0f;

	private boolean attacks;
	private boolean heals;
	
	public ItemID getItemID() {
		return itemId;
	}

	public void setItemID(ItemID itemId) {
		this.itemId = itemId;
	}

	public Stats getStats() {
		return stats;
	}

	public void setStats(Stats stats) {
		this.stats = stats;
	}

	public void setTwoHanded(boolean twoHanded) {
		this.twoHanded = twoHanded;
	}

	public boolean isTwoHanded() {
		return twoHanded;
	}

	public void setRequiresAmmoID(AmmoID requiresAmmoID) {
		this.requiresAmmoID = requiresAmmoID;
	}

	public AmmoID getRequiresAmmoID() {
		return requiresAmmoID;
	}

	public void setProvidesAmmoID(AmmoID providesAmmoID) {
		this.providesAmmoID = providesAmmoID;
	}

	public AmmoID getProvidesAmmoID() {
		return providesAmmoID;
	}
	
	public void setIsStackable(boolean isStackable) {
		this.isStackable = isStackable;
	}

	public boolean isStackable() {
		return isStackable;
	}

	public boolean requiresTarget() {
		return requiresTarget;
	}

	public void setRequiresTarget(boolean requiresTarget) {
		this.requiresTarget = requiresTarget;
	}

	public boolean hasAutoFire() {
		return autoFire;
	}

	public void setAutoFire(boolean autoFire) {
		this.autoFire = autoFire;
	}
	
	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getRadius() {
		return radius;
	}

	public void setRadius(float radius) {
		this.radius = radius;
	}

	public SingleSprite getSprite() {
		return sprite;
	}

	public void setSprite(SingleSprite sprite) {
		this.sprite = sprite;
	}

	public SingleSprite getInventorySprite() {
		return inventorySprite;
	}

	public void setInventorySprite(SingleSprite inventorySprite) {
		this.inventorySprite = inventorySprite;
	}

	public Map<SpriteSlotID,SingleSprite> getEquipmentSprite() {
		return equipmentSprite;
	}

	public float getShadow() {
		return shadow;
	}

	public void setShadow(float shadow) {
		this.shadow = shadow;
	}

	public boolean getAttackBurst() {
		return attackBurst;
	}

	public void setAttackBurst(boolean attackBurst) {
		this.attackBurst = attackBurst;
	}

	public float getAttackBurstTime() {
		return attackBurstTime;
	}

	public void setAttackBurstTime(float attackBurstTime) {
		this.attackBurstTime = attackBurstTime;
	}

	public float getAttackBurstInterval() {
		return attackBurstInterval;
	}

	public void setAttackBurstInterval(float attackBurstInterval) {
		this.attackBurstInterval = attackBurstInterval;
	}

	public boolean getAttackBurstScanning() {
		return attackBurstScanning;
	}

	public void setAttackBurstScanning(boolean attackBurstScanning) {
		this.attackBurstScanning = attackBurstScanning;
	}

	public float getAttackBurstScanningMin() {
		return attackBurstScanningMin;
	}

	public void setAttackBurstScanningMin(float attackBurstScanningMin) {
		this.attackBurstScanningMin = attackBurstScanningMin;
	}

	public float getAttackBurstScanningMax() {
		return attackBurstScanningMax;
	}

	public void setAttackBurstScanningMax(float attackBurstScanningMax) {
		this.attackBurstScanningMax = attackBurstScanningMax;
	}

	public float getRecoveryTime() {
		return recoveryTime;
	}

	public void setRecoveryTime(float recoveryTime) {
		this.recoveryTime = recoveryTime;
	}

	public boolean getAttackHitCeiling() {
		return attackHitCeiling;
	}

	public void setAttackHitCeiling(boolean attackHitCeiling) {
		this.attackHitCeiling = attackHitCeiling;
	}

	public float getAttackHorizontalAngleVariation() {
		return attackHorizontalAngleVariation;
	}

	public void setAttackHorizontalAngleVariation(float attackHorizontalAngleVariation) {
		this.attackHorizontalAngleVariation = attackHorizontalAngleVariation;
	}

	public float getAttackVerticalAngleVariation() {
		return attackVerticalAngleVariation;
	}

	public void setAttackVerticalAngleVariation(
			float attackVerticalAngleVariation) {
		this.attackVerticalAngleVariation = attackVerticalAngleVariation;
	}

	public float getAttackMinVerticalAngle() {
		return attackMinVerticalAngle;
	}

	public void setAttackMinVerticalAngle(float attackMinVerticalAngle) {
		this.attackMinVerticalAngle = attackMinVerticalAngle;
	}

	public float getAttackMaxVerticalAngle() {
		return attackMaxVerticalAngle;
	}

	public void setAttackMaxVerticalAngle(float attackMaxVerticalAngle) {
		this.attackMaxVerticalAngle = attackMaxVerticalAngle;
	}

	public float getAttackSpeed() {
		return attackSpeed;
	}

	public void setAttackSpeed(float attackSpeed) {
		this.attackSpeed = attackSpeed;
	}

	public float getAttackSpeedVariation() {
		return attackSpeedVariation;
	}

	public void setAttackSpeedVariation(float attackSpeedVariation) {
		this.attackSpeedVariation = attackSpeedVariation;
	}

	public float getAttackRadius() {
		return attackRadius;
	}

	public void setAttackRadius(float attackRadius) {
		this.attackRadius = attackRadius;
	}

	public float getAttackHeight() {
		return attackHeight;
	}

	public void setAttackHeight(float attackHeight) {
		this.attackHeight = attackHeight;
	}

	public SingleSprite getAttackSprite() {
		return attackSprite;
	}

	public void setAttackSprite(SingleSprite attackSprite) {
		this.attackSprite = attackSprite;
	}

	public float getAttackShadow() {
		return attackShadow;
	}

	public void setAttackShadow(float attackShadow) {
		this.attackShadow = attackShadow;
	}

	public float getAttackGravity() {
		return attackGravity;
	}

	public void setAttackGravity(float attackForceZ) {
		this.attackGravity = attackForceZ;
	}

	public float getAttackRange() {
		return attackRange;
	}

	public void setAttackRange(float attackRange) {
		this.attackRange = attackRange;
	}


	public float getHeal() {
		return heal;
	}

	public void setHeal(float heal) {
		this.heal = heal;
	}

	public float getManaCost() {
		return manaCost;
	}

	public void setManaCost(float manaCost) {
		this.manaCost = manaCost;
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public ItemType getItemType() {
		return itemType;
	}

	public void setItemType(ItemType itemType) {
		this.itemType = itemType;
	}
	
	public boolean attacks() {
		return attacks;
	}

	public void setAttacks(boolean attacks) {
		this.attacks = attacks;
	}

	public boolean heals() {
		return heals;
	}

	public void setHeals(boolean heals) {
		this.heals = heals;
	}

	public float getAttackDamage() {
		float val = getStats().getAttack()*getRecoveryTime();
		if (val < 0)
			val = 0;
		return val;
	}

	public float getAttackDuration() {
		if (getAttackGravity()<-Constants.EPS)
			return 1000f;
		float val = getStats().getRange()*5f/getAttackSpeed();
		if (val < 0)
			val = 0;
		return val;
	}
	
	public boolean isTrashable() {
		if (getItemType()==ItemType.SPELL)
			return false;
		return true;
	}
	
	public boolean isUseable() {
		return getItemType()==ItemType.WEAPONDOESNOTNEEDAMMO ||getItemType()==ItemType.WEAPONNEEDSAMMO || getItemType()==ItemType.USEABLE || getItemType()==ItemType.SPELL;
	}
	
	public boolean isEquipable() {
		return (getItemType()==ItemType.HELMET ||
				getItemType()==ItemType.ARMOR ||
				getItemType()==ItemType.ARMS ||
				getItemType()==ItemType.LEGS ||
				getItemType()==ItemType.BOOTS ||
				getItemType()==ItemType.RING ||
				getItemType()==ItemType.AMULET);
	}
	

	public boolean isBetterOrEqualThan(Item other) {
		if (other == null)
			return true;
		
		if (getItemType()!=other.getItemType() ||
		    getRequiresAmmoID()!=other.getRequiresAmmoID() ||
		    getProvidesAmmoID()!=other.getProvidesAmmoID() ||
		    isStackable()!=other.isStackable() ||
    		getAttackBurst()!=other.getAttackBurst() ||
		    getAttackRadius()!=other.getAttackRadius() ||
		    getAttackHeight()!=other.getAttackHeight())
			return false;
				
		boolean better = false;
		if (hasAutoFire() && !other.hasAutoFire())
			better = true;
		if (!hasAutoFire() && other.hasAutoFire())
			return false;

		if (!isTwoHanded() && other.isTwoHanded())
			better = true;
		if (isTwoHanded() && !other.isTwoHanded())
			return false;

		if (getAttackDamage()>=other.getAttackDamage())
			better = true;
		if (getAttackDamage()<other.getAttackDamage())
			return false;

		if (getAttackDuration()>=other.getAttackDuration())
			better = true;
		if (getAttackDuration()<other.getAttackDuration())
			return false;

		if (getAttackRange()>=other.getAttackRange())
			better = true;
		if (getAttackRange()<other.getAttackRange())
			return false;

		if (getRecoveryTime()<=other.getRecoveryTime())
			better = true;
		if (getRecoveryTime()>other.getRecoveryTime())
			return false;

		if (getAttackMinVerticalAngle()<=other.getAttackMinVerticalAngle())
			better = true;
		if (getAttackMinVerticalAngle()>other.getAttackMinVerticalAngle())
			return false;

		if (getAttackMaxVerticalAngle()>=other.getAttackMaxVerticalAngle())
			better = true;
		if (getAttackMaxVerticalAngle()<other.getAttackMaxVerticalAngle())
			return false;
		
		if (getAttackVerticalAngleVariation()<=other.getAttackVerticalAngleVariation())
			better = true;
		if (getAttackVerticalAngleVariation()>other.getAttackVerticalAngleVariation())
			return false;

		if (getAttackHorizontalAngleVariation()!=other.getAttackHorizontalAngleVariation())
			return false;

		if (getAttackBurstScanningMin()!=other.getAttackBurstScanningMin())
			return false;

		if (getAttackBurstScanningMax()!=other.getAttackBurstScanningMax())
			return false;

		if (getAttackGravity()<=other.getAttackGravity())
			better = true;
		if (getAttackGravity()>other.getAttackGravity())
			return false;

		if (getManaCost()<=other.getManaCost())
			better = true;
		if (getManaCost()>other.getManaCost())
			return false;

		if (getAttackSpeed()>=other.getAttackSpeed())
			better = true;
		if (getAttackSpeed()<other.getAttackSpeed())
			return false;

		if (getHeal()>=other.getHeal())
			better = true;
		if (getHeal()<other.getHeal())
			return false;

		if (getStats().allBetterOrEqualThan(other.getStats()))
			better = true;
		else
			return false;

		if (getAttackBurstTime()>=other.getAttackBurstTime())
			better = true;
		if (getAttackBurstTime()<other.getAttackBurstTime())
			return false;

		if (getAttackBurstInterval()<=other.getAttackBurstInterval())
			better = true;
		if (getAttackBurstInterval()>other.getAttackBurstInterval())
			return false;

		return better;
	}
	
	public boolean isCompatibleWith(Item other) {
		if (other==null)
			return true;

		if (isTwoHanded())
			return false;
		if (other.isTwoHanded())
			return false;

		if (getItemType()==ItemType.USEABLE)
			return false;
		if (other.getItemType()==ItemType.USEABLE)
			return false;
		
		if (getItemType()==ItemType.WEAPONNEEDSAMMO) {
			if (other.getItemType()!=ItemType.AMMO)
				return false;
			if (getRequiresAmmoID()!=other.getProvidesAmmoID())
				return false;
		}

		if (getItemType()==ItemType.AMMO) {
			if (other.getItemType()!=ItemType.WEAPONNEEDSAMMO)
				return false;
			if (getProvidesAmmoID()!=other.getRequiresAmmoID())
				return false;
		}
		
		if (getItemType()==ItemType.WEAPONDOESNOTNEEDAMMO) {
			if (other.getItemType()!=ItemType.SHIELD)
				return false;
		}

		if (getItemType()==ItemType.SHIELD) {
			if (other.getItemType()!=ItemType.WEAPONDOESNOTNEEDAMMO)
				return false;
		}

		if (getItemType()==ItemType.SPELL || other.getItemType()==ItemType.SPELL) {
				return false;
		}

		if (getItemType()==ItemType.HELMET ||
			getItemType()==ItemType.ARMOR ||
			getItemType()==ItemType.ARMS ||
			getItemType()==ItemType.LEGS ||
			getItemType()==ItemType.BOOTS ||
			getItemType()==ItemType.RING ||
			getItemType()==ItemType.AMULET)
			return false;
		
		return true;
		
	}
}
