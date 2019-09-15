package com.elezeta.roguecave.gui.controllers.ingame.multiplayer;

import com.elezeta.roguecave.gui.ControlID;
import com.elezeta.roguecave.gui.RogueCaveGUI;
import com.elezeta.roguecave.gui.controllers.Controller;
import com.elezeta.roguecave.gui.controllers.ingame.UpgradesController;


public class UpgradesMultiplayerController extends UpgradesController {

	private static UpgradesMultiplayerController singleton;
	public static Controller get() {
		if (singleton==null)
			singleton = new UpgradesMultiplayerController();
		return singleton;
	}

	private UpgradesMultiplayerController() { }
	
	@Override
	public void keyPress(int eventKey) {
		super.keyPress(eventKey);
		//TODO esto
		if (eventKey==RogueCaveGUI.config.getKey(ControlID.UPGRADES)) { RogueCaveGUI.newController = UpgradesMultiplayerController.get(); }

	}

	//TODO do not return to ingame
	//TODO not pause

	@Override
	public String getName() { return "UpgradesMultiplayer"; }

}