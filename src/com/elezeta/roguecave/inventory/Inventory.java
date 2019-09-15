package com.elezeta.roguecave.inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.elezeta.roguecave.Constants;
import com.elezeta.roguecave.data.ItemFactory;
import com.elezeta.roguecave.data.ItemID;
import com.elezeta.roguecave.entities.Stats;
import com.elezeta.roguecave.sprites.SingleSprite;
import com.elezeta.roguecave.sprites.SpriteSlotID;

public class Inventory {

	private int chosenCombo = 0;
	private int chosenComboSelf = 0;
	private int altRing = 0;
	private boolean useSelf = false;

	private Map<Integer,Item> items = new HashMap<Integer,Item>(); 

	private List<Item> drop = new ArrayList<Item>();

	public List<Item> newItems = new ArrayList<Item>(); 

	private boolean automaticManager = false;
	private int combos = 10;
	
	public static final int TRASH = -1;
	
	public static final int NONE = 0;
	
	public static final int HELMET = 1001;
	public static final int ARMS = 1002;
	public static final int AMULET = 1003;
	public static final int ARMOR = 1004;
	public static final int LEGS = 1005;
	public static final int BOOTS = 1006;
	public static final int RING1 = 1007;
	public static final int RING2 = 1008;
	public static final int COMBOS = 1100;
	public static final int COMBOSSELF = 1200;
	
	public static final int INVARMOR = 2000;
	public static final int INVWEAPONS = 3000;
	public static final int INVSPELLS = 4000;
	public static final int INVMISC = 5000;

	public int getChosenCombo() {
		return chosenCombo;
	}

	public void setChosenCombo(int chosenCombo) {
		this.chosenCombo = chosenCombo;
	}
	
	public int getChosenComboSelf() {
		return chosenComboSelf;
	}

	public void setChosenComboSelf(int selfCombo) {
		if (!getUseSelf())
			this.chosenComboSelf = selfCombo;
	}

	public boolean getUseSelf() {
		return useSelf;
	}

	public void setUseSelf(boolean useSelf) {
		this.useSelf = useSelf;
	}

	private Map<Integer,Item> getItems() {
		return items;
	}

	public Item getComboMainItem(int combo) {
		return getItems().get(Inventory.COMBOS+combo*10+1);
	}

	public Item getComboOffhandItem(int combo) {
		return getItems().get(Inventory.COMBOS+combo*10+2);
	}

	public void setComboMainItem(int combo,Item item) {
		getItems().put(Inventory.COMBOS+combo*10+1,item);
	}

	public void setComboOffhandItem(int combo,Item item) {
		getItems().put(Inventory.COMBOS+combo*10+2,item);
	}

	public Item getSelfComboMainItem(int selfCombo) {
		return getItems().get(Inventory.COMBOSSELF+selfCombo+1);
	}

	public void setSelfComboMainItem(int selfCombo,Item item) {
		getItems().put(Inventory.COMBOSSELF+selfCombo+1,item);
	}

	public Item getItem(int id) {
		return getItems().get(id);
	}

	private Set<Integer> searchItemInSlots(Item item) {
		Set<Integer> ret = new HashSet<Integer>();
		for (Iterator<Entry<Integer,Item>> ite = getItems().entrySet().iterator();ite.hasNext();) {
			Entry<Integer,Item> entry= ite.next();
			if (entry.getValue()==item)
				ret.add(entry.getKey());
		}
		return ret;
	}

	private int searchItemInBasicSlot(Item item) {
		for (Iterator<Entry<Integer,Item>> ite = getItems().entrySet().iterator();ite.hasNext();) {
			Entry<Integer,Item> entry= ite.next();
			if (entry.getValue()==item && isBasicSlot(entry.getKey()))
				return entry.getKey();
		}
		return -1;
	}

	public void setItem(int id,Item item) {
		if (item == null)
			getItems().remove(id);
		else
			getItems().put(id,item);
	}

	public List<Item> getDrop() {
		return drop;
	}

	public List<Item> getNewItems() {
		return newItems;
	}

	public boolean hasAutomaticManager() {
		return automaticManager;
	}

	public void setAutomaticManager(boolean automaticManager) {
		this.automaticManager = automaticManager;
	}

	private int getCombos() {
		return combos;
	}

	public void setCombos(int combos) {
		this.combos = combos;
	}

	public boolean isAnyNewItem() {
		return getNewItems().size()>0;
	}

	public boolean isBasicSlot(int slot) {
		return slot>=INVARMOR;
	}

	public boolean isSpellsSlot(int slot) {
		return slot>=INVSPELLS && slot<INVMISC;
	}

	public boolean isComboSlot(int slot) {
		return slot>=COMBOS && slot<COMBOSSELF;
	}

	public boolean isComboSelfSlot(int slot) {
		return slot>=COMBOSSELF && slot<INVARMOR;
	}

	public boolean isEquipmentSlot(int slot) {
		return slot>=HELMET && slot<COMBOS;
	}
	
	public boolean isUsed(Item item) {
		Set<Integer> itemSet = searchItemInSlots(item);
		for (Iterator<Integer> ite = itemSet.iterator();ite.hasNext();) {
			int slot = ite.next();
			if (!isBasicSlot(slot) && item==getItem(slot))
				return true;
		}
		return false;
	}
	
	public ItemWithAmmo getSelectedItem() {
		ItemWithAmmo result = new ItemWithAmmo();
		ArrayList<Item> items = new ArrayList<Item>();
		Item item;
		item = getItem(ARMOR);
		if (item != null)
			items.add(item);
		item = getItem(HELMET);
		if (item != null)
			items.add(item);
		item = getItem(LEGS);
		if (item != null)
			items.add(item);
		item = getItem(BOOTS);
		if (item != null)
			items.add(item);
		item = getItem(ARMS);
		if (item != null)
			items.add(item);
		item = getItem(AMULET);
		if (item != null)
			items.add(item);
		item = getItem(RING1);
		if (item != null)
			items.add(item);
		item = getItem(RING2);
		if (item != null)
			items.add(item);
		if (getUseSelf()) {
			if (getSelfComboMainItem(getChosenComboSelf()) != null)
				items.add(getSelfComboMainItem(getChosenComboSelf()));
			else
				items.add(ItemFactory.generateItem(ItemID.noitem));
		}
		else {
			Item main = getComboMainItem(getChosenCombo());
			Item offhand = getComboOffhandItem(getChosenCombo());
			boolean useable = false;
			if (main != null)
				if (main.isUseable())
					useable = true;
			if (offhand != null)
				if (offhand.isUseable())
					useable = true;

			if (main != null)
				items.add(main);
			if (offhand != null)
				items.add(offhand);
			if (!useable)
				items.add(ItemFactory.generateItem(ItemID.fists));
		}
		Stats s = new Stats();
		result.setStats(s);
		
		for (int i = 0;i < items.size();i++) {
			item = items.get(i);
			result.setAttacks(result.attacks()|item.attacks());
			result.setHeals(result.heals()|item.heals());
			result.setRecoveryTime(result.getRecoveryTime()+item.getRecoveryTime());
			result.setHeal(result.getHeal()+item.getHeal());
			result.setManaCost(result.getManaCost()+item.getManaCost());
			s.add(item.getStats());
			result.setAttackHorizontalAngleVariation(result.getAttackHorizontalAngleVariation()+item.getAttackHorizontalAngleVariation());
			result.setAttackVerticalAngleVariation(result.getAttackVerticalAngleVariation()+item.getAttackVerticalAngleVariation());
			result.setAttackMinVerticalAngle(result.getAttackMinVerticalAngle()+item.getAttackMinVerticalAngle());
			result.setAttackMaxVerticalAngle(result.getAttackMaxVerticalAngle()+item.getAttackMaxVerticalAngle());
			result.setAttackSpeed(result.getAttackSpeed()+item.getAttackSpeed());
			result.setAttackSpeedVariation(result.getAttackSpeedVariation()+item.getAttackSpeedVariation());
			result.setAttackGravity(result.getAttackGravity()+item.getAttackGravity());
			result.setAttackRange(result.getAttackRange()+item.getAttackRange());
			result.setAttackRange(result.getAttackRange()+item.getAttackRange());
			result.setAttackBurstTime(result.getAttackBurstTime()+item.getAttackBurstTime());
			result.setAttackBurst(result.getAttackBurst() || item.getAttackBurst());
			result.setAttackBurstInterval(result.getAttackBurstInterval()+item.getAttackBurstInterval());
			result.setAttackBurstScanning(result.getAttackBurstScanning() || item.getAttackBurstScanning());
			result.setAttackBurstScanningMin(result.getAttackBurstScanningMin()+item.getAttackBurstScanningMin());
			result.setAttackBurstScanningMax(result.getAttackBurstScanningMax()+item.getAttackBurstScanningMax());
			result.setAttackShadow(result.getAttackShadow()+item.getAttackShadow());
			result.setAttackRadius(result.getAttackRadius()+item.getAttackRadius());
			result.setAttackHeight(result.getAttackHeight()+item.getAttackHeight());
			result.setIsStackable(result.isStackable()|item.isStackable());
			result.setRequiresTarget(result.requiresTarget()|item.requiresTarget());
			result.setAutoFire(result.hasAutoFire()|item.hasAutoFire());
			result.setAttackHitCeiling(result.getAttackHitCeiling()&item.getAttackHitCeiling());
			if (item.isUseable())
				result.setItemType(item.getItemType());
			if (item.getRequiresAmmoID() != null)
				result.setRequiresAmmoID(item.getRequiresAmmoID());
			if (item.getProvidesAmmoID() != null) {
				result.setProvidesAmmoID(item.getProvidesAmmoID());
				result.setAmmo(item);
			}
			if (item.getAttackSprite() != null)
				result.setAttackSprite(item.getAttackSprite());
			if (item.isStackable())
				result.setAmmo(item);
		}

		if (result.getAttackHorizontalAngleVariation() < 0)
			result.setAttackHorizontalAngleVariation(0);
		if (result.getAttackVerticalAngleVariation() < 0)
			result.setAttackVerticalAngleVariation(0);
		if (result.getAttackRadius() < Constants.minimumAttackRadius)
			result.setAttackRadius(Constants.minimumAttackRadius);
		if (result.getAttackSpeed() < Constants.minimumAttackSpeed)
			result.setAttackSpeed(Constants.minimumAttackSpeed);
		if (result.getAttackSpeedVariation() < 0)
			result.setAttackSpeedVariation(0);
		if (result.getManaCost() < 0)
			result.setManaCost(0);
		if (result.getAttackShadow() < 0)
			result.setAttackShadow(0);
		if (result.getAttackShadow() > 1)
			result.setAttackShadow(1f);
		if (result.getRecoveryTime() < Constants.minimumRecovery)
			result.setRecoveryTime(Constants.minimumRecovery);
		if (result.getAttackBurstInterval() < Constants.minimumBurstInterval)
			result.setAttackBurstInterval(Constants.minimumBurstInterval);
		if (result.getAttackBurstTime() < Constants.minimumBurst)
			result.setAttackBurstTime(Constants.minimumBurst);

		return result;
	}

	public int slotType(int slot) {
		if (slot == TRASH || slot == NONE || slot == ARMOR || slot == HELMET || slot == ARMS || slot == AMULET|| slot == LEGS || slot == BOOTS || slot == RING1 || slot == RING2)
			return slot;
		else if (slot >=INVARMOR && slot<INVWEAPONS)
			return INVARMOR;
		else if (slot >=INVWEAPONS && slot<INVSPELLS)
			return INVWEAPONS;
		else if (slot >=INVSPELLS && slot<INVMISC)
			return INVSPELLS;
		else if (slot >=INVMISC)
			return INVMISC;
		else if (slot >= COMBOS && slot < COMBOSSELF)
			return COMBOS;
		else if (slot >= COMBOSSELF && slot < INVARMOR)
			return COMBOSSELF;
		return 0;
	}
	
	public int slotTypeOfItemType(ItemType type) {
		if (type == ItemType.ARMOR ||
			type == ItemType.AMULET || 
			type == ItemType.HELMET || 
			type == ItemType.ARMS || 
			type == ItemType.LEGS || 
			type == ItemType.BOOTS || 
			type == ItemType.RING)
			return INVARMOR;
		if (type == ItemType.WEAPONDOESNOTNEEDAMMO ||
				type == ItemType.WEAPONNEEDSAMMO || 
				type == ItemType.AMMO || 
				type == ItemType.SHIELD || 
				type == ItemType.LEGS || 
				type == ItemType.BOOTS || 
				type == ItemType.RING)
				return INVWEAPONS;
		if (type == ItemType.SPELL)
			return INVSPELLS;
		if (type == ItemType.USEABLE || type == ItemType.MISC)
			return INVMISC;
		return NONE;
	}
	
	public int accompanyingSlot(int slot) {
		if (slot%2==0)
			return slot-1;
		else
			return slot+1;
	}
	
	public Set<Integer> fitsIn(Item item) {
		Set<Integer> ret = new HashSet<Integer>();
		List<Integer> candidates = new ArrayList<Integer>();
		for (int i = 1;i<=Constants.inventorySize;i++) {
			candidates.add(Inventory.INVARMOR+i);
			candidates.add(Inventory.INVWEAPONS+i);
			candidates.add(Inventory.INVSPELLS+i);
			candidates.add(Inventory.INVMISC+i);
		}
		for (int i = 0;i<4;i++)
			candidates.add(Inventory.COMBOSSELF+i+1);
		for (int i = 0;i<getCombos();i++) {
			candidates.add(Inventory.COMBOS+10*i+1);
			candidates.add(Inventory.COMBOS+10*i+2);
		}

		candidates.add(Inventory.ARMOR);
		candidates.add(Inventory.HELMET);
		candidates.add(Inventory.ARMS);
		candidates.add(Inventory.AMULET);
		candidates.add(Inventory.LEGS);
		candidates.add(Inventory.BOOTS);
		candidates.add(Inventory.RING1);
		candidates.add(Inventory.RING2);

		for (int i = 0;i < candidates.size();i++) {
			if (fits(candidates.get(i),item))
				ret.add(candidates.get(i));
		}
		return ret;
	}

	public Set<Integer> compatibleIn(Item item) {
		Set<Integer> ret = new HashSet<Integer>();
		List<Integer> candidates = new ArrayList<Integer>();
		for (int i = 1;i<=Constants.inventorySize;i++) {
			ret.add(Inventory.INVARMOR+i);
			ret.add(Inventory.INVWEAPONS+i);
			ret.add(Inventory.INVSPELLS+i);
			ret.add(Inventory.INVMISC+i);
		}
		for (int i = 0;i<4;i++)
			ret.add(Inventory.COMBOSSELF+i+1);
		for (int i = 0;i<getCombos();i++) {
			candidates.add(Inventory.COMBOS+10*i+1);
			candidates.add(Inventory.COMBOS+10*i+2);
		}

		ret.add(Inventory.ARMOR);
		ret.add(Inventory.HELMET);
		ret.add(Inventory.ARMS);
		ret.add(Inventory.AMULET);
		ret.add(Inventory.LEGS);
		ret.add(Inventory.BOOTS);
		ret.add(Inventory.RING1);
		ret.add(Inventory.RING2);

		for (int i = 0;i < candidates.size();i++) {
			if (isCompatible(candidates.get(i),item))
				ret.add(candidates.get(i));
		}
		return ret;
	}

	public boolean isCompatible(int slot,Item item) {
		if (!isComboSlot(slot))
			return true;
		if (item == null)
			return true;
		int accompanying = accompanyingSlot(slot);
		Item that = getItem(slot);
		Item other = getItem(accompanying);
		if (item.equals(that))
			return true;
		if (that != null)
			if (item.getProvidesAmmoID()!=null && item.getProvidesAmmoID()==that.getProvidesAmmoID())
				return true;
		if (item.isCompatibleWith(that) && other==null)
			return true;
		return (item.isCompatibleWith(other)&&item.isCompatibleWith(that))&&that==null;
	}

	public boolean fits(int slot,Item item) {
		if (item == null)
			return true;
		int type = slotType(slot);
		if (type == AMULET && item.getItemType()==ItemType.AMULET)
			return true;
		if (type == HELMET && item.getItemType()==ItemType.HELMET)
			return true;
		if (type == ARMOR && item.getItemType()==ItemType.ARMOR)
			return true;
		if (type == ARMS && item.getItemType()==ItemType.ARMS)
			return true;
		if (type == LEGS && item.getItemType()==ItemType.LEGS)
			return true;
		if (type == BOOTS && item.getItemType()==ItemType.BOOTS)
			return true;
		if ((type == RING1 || type == RING2) && item.getItemType()==ItemType.RING)
			return true;
		if (type == INVARMOR && (
				item.getItemType()==ItemType.AMULET ||
				item.getItemType()==ItemType.HELMET ||
				item.getItemType()==ItemType.ARMOR ||
				item.getItemType()==ItemType.ARMS ||
				item.getItemType()==ItemType.LEGS ||
				item.getItemType()==ItemType.BOOTS ||
				item.getItemType()==ItemType.RING))
			return true;
		
		if (type == INVWEAPONS && (
				item.getItemType()==ItemType.WEAPONDOESNOTNEEDAMMO ||
				item.getItemType()==ItemType.WEAPONNEEDSAMMO ||
				item.getItemType()==ItemType.AMMO ||
				item.getItemType()==ItemType.SHIELD))
			return true;

		if (type == INVSPELLS && (
				item.getItemType()==ItemType.SPELL))
			return true;

		if (type == INVMISC && (
				item.getItemType()==ItemType.USEABLE ||
				item.getItemType()==ItemType.MISC))
			return true;
		
		if (type == COMBOS && (
				item.getItemType()==ItemType.AMMO ||
				item.getItemType()==ItemType.SHIELD ||
				((item.getItemType()==ItemType.SPELL ||
				item.getItemType()==ItemType.USEABLE ||
				item.getItemType()==ItemType.WEAPONDOESNOTNEEDAMMO ||
				item.getItemType()==ItemType.WEAPONNEEDSAMMO))))
			return true;

		if (type == COMBOSSELF && (
				item.getItemType()==ItemType.SPELL ||
				item.getItemType()==ItemType.USEABLE ||
				item.getItemType()==ItemType.WEAPONDOESNOTNEEDAMMO ||
				item.getItemType()==ItemType.WEAPONNEEDSAMMO) && !item.requiresTarget())
			return true;
			
		return false;		
	}

	public void move(int originSlot, int selSlot) {
		Item dragItem = getItem(originSlot);
		Set<Integer> dragItemSet = searchItemInSlots(dragItem);
		Item old = getItem(selSlot);

		if (dragItem == null)
			return;

		else if (!isBasicSlot(originSlot) && selSlot==Inventory.NONE) {
			setItem(originSlot,null);
		}

		else if (selSlot==Inventory.TRASH) {
			if (!isBasicSlot(originSlot))
				setItem(originSlot,null);
			if (dragItem.isTrashable() && (!dragItem.isStackable() || dragItem.getQuantity()>0)) {
				setItem(originSlot,null);
				drop(dragItem);
			}
		}
		
		else if (isBasicSlot(originSlot) && isBasicSlot(selSlot)) {
			if (dragItem != getItem(selSlot)) {
				if (fits(selSlot,dragItem) && fits(originSlot,old)) {
					setItem(selSlot,dragItem);
					setItem(originSlot,old);
				}
			}
		}

		else if (isBasicSlot(originSlot) && !isBasicSlot(selSlot)) {
			if (dragItem != getItem(selSlot)) {
				if (fits(selSlot,dragItem)) {
					if (!isCompatible(selSlot,dragItem))
						setItem(accompanyingSlot(selSlot),null);
					int other = accompanyingSlot(selSlot);
					if (isComboSlot(selSlot) && isCompatible(selSlot,dragItem) && getItem(selSlot) != null && getItem(other) == null)
						setItem(other,getItem(selSlot));
					setItem(selSlot,dragItem);
					if (isEquipmentSlot(selSlot)) {
						for (Iterator<Integer> ite = dragItemSet.iterator();ite.hasNext();) {
							int slot = ite.next();
							if (isEquipmentSlot(slot) && dragItem==getItem(slot))
								setItem(slot,null);
						}
					}
				}
			}
		}
		
		else if (!isBasicSlot(originSlot) && !isBasicSlot(selSlot)) {
			if (dragItem != getItem(selSlot)) {
				if (isComboSlot(selSlot) && isComboSlot(originSlot) && accompanyingSlot(selSlot)==originSlot) {
						setItem(selSlot,dragItem);
						setItem(originSlot,old);
				}
				else if (isEquipmentSlot(selSlot) && isEquipmentSlot(originSlot)) {
					if (fits(selSlot,dragItem) && fits(originSlot,old)) {
						setItem(selSlot,dragItem);
						setItem(originSlot,old);
					}
				}
				else if ((isComboSlot(selSlot) || isComboSelfSlot(selSlot) &&
					(isComboSlot(originSlot) || isComboSelfSlot(originSlot)))) {
					setItem(originSlot,null);
					if (fits(originSlot,old)) {
						if (isCompatible(originSlot,old)) {
							setItem(originSlot,old);
						}
					}
					if (fits(selSlot,dragItem)) {
						if (!isCompatible(selSlot,dragItem) && isComboSlot(selSlot)) {
							setItem(accompanyingSlot(selSlot),null);
						}
						int other = accompanyingSlot(selSlot);
						if (isComboSlot(selSlot) && isCompatible(selSlot,dragItem) && getItem(selSlot) != null && getItem(other) == null) {
							setItem(other,getItem(selSlot));
							setItem(originSlot,null);
						}
						setItem(selSlot,dragItem);
					}
				}
			}
		}

		else if (!isBasicSlot(originSlot) && isBasicSlot(selSlot)) {
			int oldpos = -1;
			setItem(originSlot,null);
			if (fits(selSlot,dragItem)) {
				for (Iterator<Integer> ite = dragItemSet.iterator();ite.hasNext();) {
					int slot = ite.next();
					if (isBasicSlot(slot) && dragItem==getItem(slot)) {
						oldpos = slot;
						setItem(slot,null);
					}
				}
				if (oldpos != -1)
					setItem(oldpos,getItem(selSlot));
				if (!dragItem.isStackable() || dragItem.getQuantity()>0)
					setItem(selSlot,dragItem);
			}
		}
	}
	
	private void drop(Item item) {
		Set<Integer> itemSet = searchItemInSlots(item);
		for (Iterator<Integer> ite = itemSet.iterator();ite.hasNext();) {
			int slot = ite.next();
			if (item==getItem(slot))
				setItem(slot,null);
		}
		getDrop().add(item);			
		getNewItems().remove(item);
	}
	
	public int freeCount(int slotType) {
		int count = 0;
		for (int i = 0;i<Constants.inventorySize;i++) {
			if (getItem(slotType+1+i)==null)
				count++;
		}
		return count;
	}

	public int nextFreeSlot(int slotType) {
		for (int i = 0;i<Constants.inventorySize;i++) {
			if (getItem(slotType+1+i)==null)
				return slotType+1+i;
		}
		return -1;
	}
	
	public boolean canPickup(Item item) {
		if (item.isStackable()) {
			Item already = searchEqualItem(item);
			if (already != null)
				return true;
		}
		int slotType = slotTypeOfItemType(item.getItemType());
		if (slotType==NONE)
			return false;
		if (freeCount(slotType)>0)
			return true;
		return false;
	}
	
	public void pickup(Item item) {
		if (wantToPickUp(item)) {
			if (item.isStackable()) {
				Item already = searchEqualItem(item);
				if (already != null) {
					int oldQuantity = already.getQuantity();
					already.setQuantity(already.getQuantity()+item.getQuantity());
					item = already;
					if (oldQuantity>0)
						return;
				}
			}
			int slotType = slotTypeOfItemType(item.getItemType());
			if (!isUsed(item))
				getNewItems().add(item);
			if (hasAutomaticManager() || !item.isTrashable()) {
				manageDomination(item);
			}
			int freeSlot = nextFreeSlot(slotType);
			setItem(freeSlot,item);
			if (hasAutomaticManager() && !isUsed(item))
				equipItem(item);
		}
		else
			drop(item);
	}
	
	private void equipItem(Item item) {
		List<Integer> candidates = new ArrayList<Integer>();
		for (int i = 0;i<4;i++)
			candidates.add(Inventory.COMBOSSELF+i+1);
		candidates.add(Inventory.ARMOR);
		candidates.add(Inventory.HELMET);
		candidates.add(Inventory.ARMS);
		candidates.add(Inventory.AMULET);
		candidates.add(Inventory.LEGS);
		candidates.add(Inventory.BOOTS);
		candidates.add(Inventory.RING1);
		candidates.add(Inventory.RING2);
		for (int i = 0;i < candidates.size();i++) {
			if (equipItemInSlot(item,candidates.get(i)))
				return;
		}

		if (fits(Inventory.COMBOS+10*0+1,item)) {
			boolean done = false;
			for (int i = 0;i<getCombos();i++) {
				int count = 0;
				int cItem = 0;
				int cOther = 0;
				if (getItem(Inventory.COMBOS+10*i+1) != null) {
					count++;
					cItem = Inventory.COMBOS+10*i+1;
					cOther = Inventory.COMBOS+10*i+2;
				}
				if (getItem(Inventory.COMBOS+10*i+2) != null) {
					count++;
					cItem = Inventory.COMBOS+10*i+2;
					cOther = Inventory.COMBOS+10*i+1;
				}
				if (count==1) {
					if (isCompatible(cItem,item)) {
						setItem(cOther,item);
						done = true;
						getNewItems().remove(item);
					}
				}
			}
			if (done)
				return;
			for (int i = 0;i<getCombos();i++) {
				if (getItem(Inventory.COMBOS+10*i+1) == null && getItem(Inventory.COMBOS+10*i+2) == null) {
					setItem(Inventory.COMBOS+10*i+1,item);
					List<Integer> candidates2 = new ArrayList<Integer>();
					for (int j = 1;j<=Constants.inventorySize;j++) {
						candidates2.add(Inventory.INVWEAPONS+j);
						candidates2.add(Inventory.INVSPELLS+j);
						candidates2.add(Inventory.INVMISC+j);
					}
					for (int j = 0;j < candidates2.size();j++) {
						int slot = candidates2.get(j);
						Item item2 = getItem(slot);
						if (item2 != null) {
							if (isCompatible(Inventory.COMBOS+10*i+2,item2) && item!=item2)
								setItem(Inventory.COMBOS+10*i+2,item2);
						}
					}
					getNewItems().remove(item);
					return;
				}
			}
		}
	}

	private boolean equipItemInSlot(Item item, int slot) {
		if (fits(slot,item) && getItem(slot)==null) {
			setItem(slot,item);
			getNewItems().remove(item);
			return true;
		}
		return false;
	}

	private void manageDomination(Item item) {
		List<Integer> candidates = new ArrayList<Integer>();
		for (int i = 1;i<=Constants.inventorySize;i++) {
			candidates.add(Inventory.INVARMOR+i);
			candidates.add(Inventory.INVWEAPONS+i);
			candidates.add(Inventory.INVSPELLS+i);
			candidates.add(Inventory.INVMISC+i);
		}
		for (int i = 0;i < candidates.size();i++) {
			int slot = candidates.get(i);
			Item has = getItem(slot);
			if (has != null) {
				if (isDominated(has,item)) {
					replace(has,item);
					drop(has);
				}
			}
		}
	}

	private void replace(Item has, Item item) {
		List<Integer> candidates = new ArrayList<Integer>();
		for (int i = 0;i<4;i++)
			candidates.add(Inventory.COMBOSSELF+i+1);
		for (int i = 0;i<getCombos();i++) {
			candidates.add(Inventory.COMBOS+10*i+1);
			candidates.add(Inventory.COMBOS+10*i+2);
		}

		candidates.add(Inventory.ARMOR);
		candidates.add(Inventory.HELMET);
		candidates.add(Inventory.ARMS);
		candidates.add(Inventory.AMULET);
		candidates.add(Inventory.LEGS);
		candidates.add(Inventory.BOOTS);
		candidates.add(Inventory.RING1);
		candidates.add(Inventory.RING2);
		for (int i = 0;i < candidates.size();i++) {
			int slot = candidates.get(i);
			Item check = getItem(slot);
			if (check != null)
				if (check == has) {
					setItem(slot,item);
					getNewItems().remove(item);
				}
		}
	}

	private boolean isDominated(Item item) {
		return isDominated(item,null);
	}
	
	private boolean isDominated(Item item,Item newItem) {
		if (item.isStackable())
			return false;
		int betters = getBetters(item,newItem);
		if (item.getItemType()==ItemType.RING) {
			return betters>=2;
		}
		else {
			return betters>=1;
		}
	}

	private boolean dominatesAny(Item item) {
		return getWorses(item)>=1;
	}

	public int getWorses(Item item) {
		List<Integer> candidates = new ArrayList<Integer>();
		for (int i = 1;i<=Constants.inventorySize;i++) {
			candidates.add(Inventory.INVARMOR+i);
			candidates.add(Inventory.INVWEAPONS+i);
			candidates.add(Inventory.INVSPELLS+i);
			candidates.add(Inventory.INVMISC+i);
		}
		int worses = 0;
		for (int i = 0;i < candidates.size();i++) {
			int slot = candidates.get(i);
			Item has = getItem(slot);
			if (has != null)
				if (has != item)
					if (item.isBetterOrEqualThan(has))
						worses++;
		}
		return worses;
	}

	public int getBetters(Item item) {
		return getBetters(item,null);
	}
	
	public int getBetters(Item item,Item newItem) {
		List<Integer> candidates = new ArrayList<Integer>();
		for (int i = 1;i<=Constants.inventorySize;i++) {
			candidates.add(Inventory.INVARMOR+i);
			candidates.add(Inventory.INVWEAPONS+i);
			candidates.add(Inventory.INVSPELLS+i);
			candidates.add(Inventory.INVMISC+i);
		}
		int betters = 0;
		for (int i = 0;i < candidates.size();i++) {
			int slot = candidates.get(i);
			Item has = getItem(slot);
			if (has != null)
				if (has != item)
					if (has.isBetterOrEqualThan(item))
						betters++;
		}
		if (newItem != null)
			if (newItem.isBetterOrEqualThan(item))
				betters++;
		return betters;
	}
	
	public void touchAll() {
		getNewItems().clear();
	}

	public void touch(int slot) {
		Item it = getItem(slot);
		getNewItems().remove(it);
	}
	
	private Item searchEqualItem(Item item) {
		for (Iterator<Item> ite = getItems().values().iterator();ite.hasNext();) {
			Item it = ite.next();
			if (it.getItemID().equals(item.getItemID()))
				return it;
		}
		return null;
	}

	public boolean isNewItem(Item it) {
		return getNewItems().contains(it);
	}
	
	public void destroy() {
		for (int i = 1;i<=Constants.inventorySize;i++) {
			if (getItem(Inventory.INVARMOR+i) != null)
				drop(getItem(Inventory.INVARMOR+i));
			if (getItem(Inventory.INVWEAPONS+i) != null)
				drop(getItem(Inventory.INVWEAPONS+i));
			if (getItem(Inventory.INVMISC+i) != null)
				drop(getItem(Inventory.INVMISC+i));
		}
	}

	public Set<Item> getUsedItems() {
		Set<Item> ret = new HashSet<Item>();
		List<Integer> candidates = new ArrayList<Integer>();
		candidates.add(Inventory.ARMOR);
		candidates.add(Inventory.HELMET);
		candidates.add(Inventory.ARMS);
		candidates.add(Inventory.AMULET);
		candidates.add(Inventory.LEGS);
		candidates.add(Inventory.BOOTS);
		candidates.add(Inventory.RING1);
		candidates.add(Inventory.RING2);

		for (int i = 0;i < candidates.size();i++) {
			Item it = getItem(candidates.get(i));
			if (it != null)
				ret.add(it);
		}
			

		if (getComboMainItem(getChosenCombo()) != null)
			ret.add(getComboMainItem(getChosenCombo()));
		if (getComboOffhandItem(getChosenCombo()) != null)
			ret.add(getComboOffhandItem(getChosenCombo()));

		return ret;
	}
	
	public Map<SpriteSlotID, SingleSprite> getEquipmentSprites() {
		Map<SpriteSlotID,SingleSprite> ret = new HashMap<SpriteSlotID, SingleSprite>();
		Map<SpriteSlotID,Integer> inventorySprites = InventoryDrawInfo.getInventorySprites();
		
		for (Iterator<Entry<SpriteSlotID,Integer>> ite = inventorySprites.entrySet().iterator();ite.hasNext();) {
			Entry<SpriteSlotID,Integer> entry = ite.next();
			Item item = getItem(entry.getValue());
			SpriteSlotID slot = entry.getKey();
			if (item != null) {
				SingleSprite spr = item.getEquipmentSprite().get(slot);
				if (spr != null)
					ret.put(slot,spr);
			}
		}

		return ret;

	}
	
	public Map<SpriteSlotID, SingleSprite> getComboSprites() {
		Map<SpriteSlotID,SingleSprite> ret = new HashMap<SpriteSlotID, SingleSprite>();

		if (getUseSelf()) {
			if (getSelfComboMainItem(getChosenComboSelf()) != null)
				ret.putAll(getSelfComboMainItem(getChosenComboSelf()).getEquipmentSprite());
		}
		else {
			if (getComboMainItem(getChosenCombo()) != null)
				ret.putAll(getComboMainItem(getChosenCombo()).getEquipmentSprite());
			if (getComboOffhandItem(getChosenCombo()) != null)
				ret.putAll(getComboOffhandItem(getChosenCombo()).getEquipmentSprite());
		}
		return ret;
	}

	public boolean wantToPickUp(Item item) {
		return (hasAutomaticManager() && !isDominated(item) && (canPickup(item) || dominatesAny(item))) || (!hasAutomaticManager() && canPickup(item));
	}

	public int getReplaceSlot(Item item) {
		ItemType type = item.getItemType();
		if (type == ItemType.AMULET) {
			if (!isUsed(item))
				return AMULET;
		}
		else if (type == ItemType.HELMET) {
			if (!isUsed(item))
				return HELMET;
		}
		else if (type == ItemType.ARMOR) {
			if (!isUsed(item))
				return ARMOR;
		}
		else if (type == ItemType.ARMS) {
			if (!isUsed(item))
				return ARMS;
		}
		else if (type == ItemType.LEGS) {
			if (!isUsed(item))
				return LEGS;
		}
		else if (type == ItemType.BOOTS) {
			if (!isUsed(item))
				return BOOTS;
		}
		else if (type == ItemType.RING) {
			if (!isUsed(item)) {
				if (getItem(RING1) == null) {
					altRing = 1;
					return RING1;
				}
				else if (getItem(RING2) == null) {
					altRing = 0;
					return RING2;
				}
				else if (hasAutomaticManager() && getItem(RING1).isBetterOrEqualThan(getItem(RING2))) {
					altRing = 0;
					return RING2;
				}
				else if (hasAutomaticManager() && getItem(RING2).isBetterOrEqualThan(getItem(RING1))) {
					altRing = 1;
					return RING1;
				}
				else {
					if (altRing == 0) {
						altRing = 1;
						return RING1;
					}
					else {
						altRing = 0;
						return RING2;
					}
				}
			}
		}
		else if (type == ItemType.WEAPONDOESNOTNEEDAMMO || type == ItemType.WEAPONNEEDSAMMO || type == ItemType.SPELL || type == ItemType.AMMO || type == ItemType.USEABLE || type == ItemType.SHIELD) {
			Item main = getComboMainItem(getChosenCombo());
			Item offhand = getComboMainItem(getChosenCombo());
			if (main == null && offhand == null)
				return COMBOS+getChosenCombo()*10+1;
			if (item.isCompatibleWith(main))
				return COMBOS+getChosenCombo()*10+2;
			if (item.isCompatibleWith(offhand))
				return COMBOS+getChosenCombo()*10+1;
			return COMBOS+getChosenCombo()*10+1;
		}
		return -1;
	}
	
	public void replaceItem(Item item) {
		int slot = getReplaceSlot(item);
		int orig = searchItemInBasicSlot(item);
		if (slot != -1 && orig != -1)
			move(orig,slot);
	}

	public Stats getStats() {
		Stats s = new Stats();
		s.clear();
		Set<Item> items = getUsedItems();
		for (Iterator<Item> it = items.iterator();it.hasNext();) {
			Item item = it.next();
			s.add(item.getStats());
		}
		return s;
	}

	public Stats getStatsWithReplace(Item item) {
		Map<Integer,Item> itemsC = new HashMap<Integer,Item>();
		itemsC.putAll(getItems());
		replaceItem(item);
		Stats s = getStats();
		setItems(itemsC);
		return s;
	}

	public Stats getStatsWithReplace(Item item,Item item2) {
		Map<Integer,Item> itemsC = new HashMap<Integer,Item>();
		itemsC.putAll(getItems());
		if (item != null)
			replaceItem(item);
		if (item2 != null)
			replaceItem(item2);
		Stats s = getStats();
		setItems(itemsC);
		return s;
	}

	public Stats getStatsWithRemove(int slot) {
		Map<Integer,Item> itemsC = new HashMap<Integer,Item>();
		itemsC.putAll(getItems());
		move(slot,Inventory.NONE);
		Stats s = getStats();
		setItems(itemsC);
		return s;
	}
	private void setItems(Map<Integer,Item> items) {
		this.items = items;
	}

	public void consume(Item ammo) {
		ammo.setQuantity(ammo.getQuantity()-1);
		if (ammo.getQuantity()==0)
			setItem(searchItemInBasicSlot(ammo),null);
			
		
	}

}