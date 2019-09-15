package com.elezeta.roguecave.gui.controllers.ingame.multiplayer;

import com.elezeta.roguecave.gui.ControlID;
import com.elezeta.roguecave.gui.RogueCaveGUI;
import com.elezeta.roguecave.gui.controllers.Controller;
import com.elezeta.roguecave.gui.controllers.ingame.IngameController;


public class IngameServerController extends IngameController {

	private static IngameServerController singleton;
	public static Controller get() {
		if (singleton==null)
			singleton = new IngameServerController();
		return singleton;
	}

	private IngameServerController() { }

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
		if (eventKey==RogueCaveGUI.config.getKey(ControlID.QUITFIXED)) { RogueCaveGUI.newController = PauseServerMenuController.get(); }
		if (eventKey==RogueCaveGUI.config.getKey(ControlID.PAUSE)) { RogueCaveGUI.newController = PauseServerMenuController.get(); }
	}
	@Override
	public String getName() { return "IngameServer"; }


}
