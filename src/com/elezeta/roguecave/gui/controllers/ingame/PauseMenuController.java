package com.elezeta.roguecave.gui.controllers.ingame;

import com.elezeta.roguecave.gui.ControlID;
import com.elezeta.roguecave.gui.RogueCaveGUI;
import com.elezeta.roguecave.gui.StringID;
import com.elezeta.roguecave.gui.controllers.ConfirmLeaveMenuController;
import com.elezeta.roguecave.gui.controllers.ConfirmSaveMenuController;
import com.elezeta.roguecave.gui.controllers.Controller;
import com.elezeta.roguecave.gui.controllers.MenuController;
import com.elezeta.roguecave.gui.controllers.menu.ConfirmCloseMenuController;
import com.elezeta.roguecave.gui.controllers.menu.OptionsMenuController;
import com.elezeta.roguecave.gui.menu.MenuActuator;
import com.elezeta.roguecave.gui.menu.MenuText;
import com.elezeta.roguecave.gui.menu.MenuTextButton;
import com.elezeta.roguecave.gui.resources.Resources;


public class PauseMenuController extends MenuController {

	private static PauseMenuController singleton;
	public static Controller get() {
		if (singleton==null)
			singleton = new PauseMenuController();
		return singleton;
	}
	
	protected PauseMenuController() { }

	@Override
	public void activate(Controller oldController) {
		super.activate(oldController);
		MenuText text1 = new MenuText();
		text1.setFont(Resources.getMenuFont());
		text1.setX(0);
		text1.setY(-165-15);
		text1.setString(RogueCaveGUI.language.getString(StringID.PAUSED));
		menu.getComponents().add(text1);
		
		MenuTextButton button1 = new MenuTextButton();
		button1.setX(-183);
		button1.setY(-120-15);
		button1.setW(366);
		button1.setH(48);
		button1.setString(RogueCaveGUI.language.getString(StringID.LATERGAME));
		button1.setNumber(0);
		button1.setEnabled(true);
		button1.setActuator(new MenuActuator(){
			@Override
			public void run() {
				RogueCaveGUI.newController = ConfirmSaveMenuController.get();
			}
		});
		menu.getComponents().add(button1);

		MenuTextButton button2 = new MenuTextButton();
		button2.setX(-183);
		button2.setY(-60-15);
		button2.setW(366);
		button2.setH(48);
		button2.setString(RogueCaveGUI.language.getString(StringID.OPTIONS));
		button2.setNumber(1);
		button2.setEnabled(true);
		button2.setActuator(new MenuActuator(){
			@Override
			public void run() {
				RogueCaveGUI.newController = OptionsMenuController.get();
			}
		});
		menu.getComponents().add(button2);

		MenuTextButton button3 = new MenuTextButton();
		button3.setX(-183);
		button3.setY(0-15);
		button3.setW(366);
		button3.setH(48);
		button3.setString(RogueCaveGUI.language.getString(StringID.ABANDONGAME));
		button3.setNumber(2);
		button3.setEnabled(true);
		button3.setActuator(new MenuActuator(){
			@Override
			public void run() {
				RogueCaveGUI.newController = ConfirmLeaveMenuController.get();
			}
		});
		menu.getComponents().add(button3);

		MenuTextButton button4 = new MenuTextButton();
		button4.setX(-183);
		button4.setY(200-15);
		button4.setW(366);
		button4.setH(48);
		button4.setString(RogueCaveGUI.language.getString(StringID.CONTINUEGAME));
		button4.setNumber(3);
		button4.setEnabled(true);
		button4.setActuator(new MenuActuator(){
			@Override
			public void run() {
				RogueCaveGUI.newController = IngameController.get();
			}
		});
		menu.getComponents().add(button4);
		
	}

	@Override
	public void requestClose() {
		super.requestClose();
		RogueCaveGUI.newController = ConfirmCloseMenuController.get();
	}

	@Override
	public void keyPress(int eventKey) {
		super.keyPress(eventKey);
		if (eventKey==RogueCaveGUI.config.getKey(ControlID.QUITFIXED)) { RogueCaveGUI.newController = IngameController.get(); }
		if (eventKey==RogueCaveGUI.config.getKey(ControlID.PAUSE)) { RogueCaveGUI.newController = IngameController.get(); }
	}
	
	@Override
	public String getName() { return "Pause"; }

	@Override
	public void console(String string) {
		singlePlayerTalk(string);
	}

}


