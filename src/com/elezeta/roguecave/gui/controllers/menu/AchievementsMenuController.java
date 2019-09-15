package com.elezeta.roguecave.gui.controllers.menu;

import com.elezeta.roguecave.gui.ControlID;
import com.elezeta.roguecave.gui.RogueCaveGUI;
import com.elezeta.roguecave.gui.StringID;
import com.elezeta.roguecave.gui.controllers.Controller;
import com.elezeta.roguecave.gui.controllers.MenuController;
import com.elezeta.roguecave.gui.controllers.program.ExitingController;
import com.elezeta.roguecave.gui.menu.MenuActuator;
import com.elezeta.roguecave.gui.menu.MenuTextButton;


public class AchievementsMenuController extends MenuController {

	private static AchievementsMenuController singleton;
	public static Controller get() {
		if (singleton==null)
			singleton = new AchievementsMenuController();
		return singleton;
	}

	private AchievementsMenuController() { }

	//TODO rellenar
	
	@Override
	public void activate(Controller oldController) {
		super.activate(oldController);

		hasBar = false;

		MenuTextButton button1 = new MenuTextButton();
		button1.setX(-183);
		button1.setY(200-15);
		button1.setW(366);
		button1.setH(48);
		button1.setString(RogueCaveGUI.language.getString(StringID.BACK));
		button1.setNumber(0);
		button1.setEnabled(true);
		button1.setActuator(new MenuActuator(){
			@Override
			public void run() {
				RogueCaveGUI.newController = MainMenuController.get();
			}
		});
		menu.getComponents().add(button1);
	}

	@Override
	public void requestClose() {
		super.requestClose();
		RogueCaveGUI.newController = ExitingController.get();
	}

	@Override
	public void keyPress(int eventKey) {
		super.keyPress(eventKey);
		if (eventKey==RogueCaveGUI.config.getKey(ControlID.QUITFIXED)) { RogueCaveGUI.newController = MainMenuController.get(); }
	}

	@Override
	public String getName() { return "AchievementsMenu"; }
	
}

