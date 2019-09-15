package com.elezeta.roguecave.gui.controllers.ingame.multiplayer;

import com.elezeta.roguecave.gui.ControlID;
import com.elezeta.roguecave.gui.RogueCaveGUI;
import com.elezeta.roguecave.gui.controllers.Controller;
import com.elezeta.roguecave.gui.controllers.ingame.InventoryController;

public class InventoryMultiplayerController extends InventoryController {

	private static InventoryMultiplayerController singleton;
	public static Controller get() {
		if (singleton==null)
			singleton = new InventoryMultiplayerController();
		return singleton;
	}

	private InventoryMultiplayerController() { }

	//TODO do not return to ingame
	//TODO no pause

	@Override
	public void keyPress(int eventKey) {
		super.keyPress(eventKey);
		//TODO esto
		if (eventKey==RogueCaveGUI.config.getKey(ControlID.UPGRADES)) { RogueCaveGUI.newController = UpgradesMultiplayerController.get(); }

	}

	@Override
	public String getName() { return "InventoryMultiplayer"; }

}
