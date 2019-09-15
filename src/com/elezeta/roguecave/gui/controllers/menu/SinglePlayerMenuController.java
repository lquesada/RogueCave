package com.elezeta.roguecave.gui.controllers.menu;

import com.elezeta.roguecave.gui.ControlID;
import com.elezeta.roguecave.gui.RogueCaveGUI;
import com.elezeta.roguecave.gui.StringID;
import com.elezeta.roguecave.gui.controllers.Controller;
import com.elezeta.roguecave.gui.controllers.MenuController;
import com.elezeta.roguecave.gui.controllers.program.ExitingController;
import com.elezeta.roguecave.gui.menu.MenuActuator;
import com.elezeta.roguecave.gui.menu.MenuText;
import com.elezeta.roguecave.gui.menu.MenuTextButton;
import com.elezeta.roguecave.gui.resources.Resources;

public class SinglePlayerMenuController extends MenuController {

	private static SinglePlayerMenuController singleton;
	public static Controller get() {
		if (singleton==null)
			singleton = new SinglePlayerMenuController();
		return singleton;
	}

	private SinglePlayerMenuController() { }

	@Override
	public void activate(Controller oldController) {
		super.activate(oldController);

		hasBar = false;
		
		MenuText text1 = new MenuText();
		text1.setFont(Resources.getMenuFont());
		text1.setX(0);
		text1.setY(-165-15);
		text1.setString(RogueCaveGUI.language.getString(StringID.SINGLEPLAYER));
		menu.getComponents().add(text1);

		MenuTextButton button1 = new MenuTextButton();
		button1.setX(-183);
		button1.setY(-120-15);
		button1.setW(366);
		button1.setH(48);
		button1.setString(RogueCaveGUI.language.getString(StringID.NEWGAME));
		button1.setNumber(0);
		button1.setEnabled(true);
		button1.setActuator(new MenuActuator(){
			@Override
			public void run() {
				RogueCaveGUI.newController = SinglePlayerLevelMenuController.get();
			}
		});
		menu.getComponents().add(button1);

		MenuTextButton button2 = new MenuTextButton();
		button2.setX(-183);
		button2.setY(-60-15);
		button2.setW(366);
		button2.setH(48);
		button2.setString(RogueCaveGUI.language.getString(StringID.RESUMEGAME));
		button2.setNumber(1);
		if (RogueCaveGUI.gameState.getSinglePlayerSavedGames().size()>0)
			button2.setEnabled(true);
		button2.setActuator(new MenuActuator(){
			@Override
			public void run() {
				RogueCaveGUI.newController = SinglePlayerResumeGameMenuController.get();
			}
		});
		menu.getComponents().add(button2);

		MenuTextButton buttonq = new MenuTextButton();
		buttonq.setX(-183);
		buttonq.setY(200-15);
		buttonq.setW(366);
		buttonq.setH(48);
		buttonq.setString(RogueCaveGUI.language.getString(StringID.BACK));
		buttonq.setNumber(2);
		buttonq.setEnabled(true);
		buttonq.setActuator(new MenuActuator(){
			@Override
			public void run() {
				RogueCaveGUI.newController = MainMenuController.get();
			}
		});
		menu.getComponents().add(buttonq);

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
	public String getName() { return "SinglePlayerMenu"; }

}
