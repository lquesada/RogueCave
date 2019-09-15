package com.elezeta.roguecave.gui.internationalization;

import java.util.HashMap;
import java.util.Map;

import com.elezeta.roguecave.data.AmmoID;
import com.elezeta.roguecave.data.GameID;
import com.elezeta.roguecave.data.ItemID;
import com.elezeta.roguecave.data.ProfessionID;
import com.elezeta.roguecave.data.UpgradeCategoryID;
import com.elezeta.roguecave.data.UpgradeID;
import com.elezeta.roguecave.gui.ControlID;
import com.elezeta.roguecave.gui.StringID;
import com.elezeta.roguecave.inventory.ItemType;

public class Language {

	private Map<ItemID,String> itemStrings = new HashMap<ItemID,String>();
	
	private Map<ControlID,String> controlStrings = new HashMap<ControlID,String>();
	
	private Map<ProfessionID,String> professionStrings = new HashMap<ProfessionID,String>();
	
	private Map<AmmoID,String> ammoStrings = new HashMap<AmmoID,String>();

	private Map<AmmoID,String> ammoPluralStrings = new HashMap<AmmoID,String>();
	
	private Map<StringID,String> GUIStrings = new HashMap<StringID,String>();
	
	private Map<ItemType,String> itemTypeStrings = new HashMap<ItemType,String>();

	private Map<UpgradeID,String> upgradeStrings = new HashMap<UpgradeID,String>();

	private Map<UpgradeCategoryID,String> upgradeCategoryStrings = new HashMap<UpgradeCategoryID,String>();

	private Map<GameID,String> gameStrings = new HashMap<GameID,String>();

	private Map<Integer,String> keyStrings = new HashMap<Integer,String>();

	public Map<ItemID,String> getItemStrings() {
		return itemStrings;
	}

	public Map<ControlID,String> getControlStrings() {
		return controlStrings;
	}
	
	public Map<ProfessionID,String> getProfessionStrings() {
		return professionStrings;
	}

	public Map<AmmoID,String> getAmmoStrings() {
		return ammoStrings;
	}

	public Map<AmmoID,String> getAmmoPluralStrings() {
		return ammoPluralStrings;
	}

	public Map<StringID,String> getGUIStrings() {
		return GUIStrings;
	}

	public Map<ItemType,String> getItemTypeStrings() {
		return itemTypeStrings;
	}
	
	public Map<UpgradeID,String> getUpgradeStrings() {
		return upgradeStrings;
	}

	public Map<GameID,String> getGameStrings() {
		return gameStrings;
	}

	public Map<UpgradeCategoryID,String> getUpgradeCategoryStrings() {
		return upgradeCategoryStrings;
	}
	
	public Map<Integer,String> getKeyStrings() {
		return keyStrings;
	}

	public String getString(ItemID itemID) {
		return getItemStrings().get(itemID);
	}

	public String getString(GameID gameID) {
		return getGameStrings().get(gameID);
	}

	public String getString(ControlID keyID) {
		return getControlStrings().get(keyID);
	}

	public String getString(ProfessionID professionID) {
		return getProfessionStrings().get(professionID);
	}

	public String getString(AmmoID ammoID) {
		return getAmmoStrings().get(ammoID);
	}
	
	public String getString(AmmoID ammoID,boolean plural) {
		if (plural==false)
			return getString(ammoID);
		else
			return getAmmoPluralStrings().get(ammoID);
	}

	public String getString(StringID stringID) {
		return getGUIStrings().get(stringID);
	}

	public String getString(StringID stringID,String v1) {
		return getGUIStrings().get(stringID).replace("%1%",v1);
	}

	public String getString(StringID stringID,String v1,String v2) {
		return getGUIStrings().get(stringID).replace("%1%",v1).replace("%2%",v2);
	}

	public String getString(ItemType itemType) {
		return getItemTypeStrings().get(itemType);
	}

	public String getString(UpgradeID upgradeID) {
		return getUpgradeStrings().get(upgradeID);
	}

	public String getString(UpgradeCategoryID upgradeCategoryID) {
		return getUpgradeCategoryStrings().get(upgradeCategoryID);
	}

	public String getKeyString(Integer integer) {
		return getKeyStrings().get(integer);
	}

}
