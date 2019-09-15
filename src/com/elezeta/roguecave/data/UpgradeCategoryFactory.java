package com.elezeta.roguecave.data;


import java.util.ArrayList;
import java.util.List;

import com.elezeta.roguecave.gui.GraphicID;
import com.elezeta.roguecave.upgrades.UpgradeCategory;

public abstract class UpgradeCategoryFactory {

	private UpgradeCategoryFactory() {
	}

	public static UpgradeCategory generateUpgradeCategory(UpgradeCategoryID uId) {
		UpgradeCategory upc;
		upc = new UpgradeCategory();
		upc.setUpgradeCategoryID(uId);
		List<UpgradeID> contents = new ArrayList<UpgradeID>();
		switch (uId) {

		////////////////////////////////////
		//DEFAULT
	 	////////////////////////////////////

			case BASIC:
				upc.setContents(contents);
				upc.setGraphic(GraphicID.GUITabBasic);
				//TODO contents
				upc.setShow(false);
				return upc;
				
			case BODY:
				upc.setContents(contents);
				upc.setGraphic(GraphicID.GUITabBody);
				//TODO contents
				upc.setShow(true);
				return upc;

			default:
				System.err.println("--------------");
				System.err.println("CRITICAL ERROR");
	    		System.err.println("Invalid upgrade category.");
				System.err.println("UpgradeCategoryID "+uId);
				System.err.println("--------------");
				upc.setUpgradeCategoryID(null);
				return upc;
		}
	}

}
