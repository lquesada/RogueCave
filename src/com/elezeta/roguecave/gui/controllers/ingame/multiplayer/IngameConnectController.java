package com.elezeta.roguecave.gui.controllers.ingame.multiplayer;

import com.elezeta.roguecave.gui.ControlID;
import com.elezeta.roguecave.gui.RogueCaveGUI;
import com.elezeta.roguecave.gui.controllers.Controller;
import com.elezeta.roguecave.gui.controllers.ingame.IngameController;


public class IngameConnectController extends IngameController {

	private static IngameConnectController singleton;
	public static Controller get() {
		if (singleton==null)
			singleton = new IngameConnectController();
		return singleton;
	}
	
	private IngameConnectController() { }

	boolean entryLock = false;
	
	@Override
	public void run() {
		super.run();
		//TODO send
	}

	@Override
	public void keyPress(int eventKey) {
		super.keyPress(eventKey);
		if (eventKey==RogueCaveGUI.config.getKey(ControlID.INVENTORY)) { RogueCaveGUI.newController = InventoryMultiplayerController.get(); }
		if (eventKey==RogueCaveGUI.config.getKey(ControlID.UPGRADES)) { RogueCaveGUI.newController = UpgradesMultiplayerController.get(); }
		if (eventKey==RogueCaveGUI.config.getKey(ControlID.QUITFIXED)) { RogueCaveGUI.newController = PauseConnectMenuController.get(); }
	}
	@Override
	public String getName() { return "IngameConnect"; }

}