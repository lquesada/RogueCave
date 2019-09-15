package com.elezeta.roguecave.gui.controllers.ingame.multiplayer;

import com.elezeta.roguecave.gui.ControlID;
import com.elezeta.roguecave.gui.RogueCaveGUI;
import com.elezeta.roguecave.gui.StringID;
import com.elezeta.roguecave.gui.controllers.ConfirmLeaveMenuController;
import com.elezeta.roguecave.gui.controllers.Controller;
import com.elezeta.roguecave.gui.controllers.ingame.PauseMenuController;
import com.elezeta.roguecave.gui.controllers.menu.ConfirmCloseMenuController;
import com.elezeta.roguecave.gui.controllers.menu.OptionsMenuController;
import com.elezeta.roguecave.gui.menu.MenuActuator;
import com.elezeta.roguecave.gui.menu.MenuText;
import com.elezeta.roguecave.gui.menu.MenuTextButton;
import com.elezeta.roguecave.gui.resources.Resources;


public class PauseConnectMenuController extends PauseMenuController {

	private static PauseConnectMenuController singleton;
	public static Controller get() {
		if (singleton==null)
			singleton = new PauseConnectMenuController();
		return singleton;
	}

	private PauseConnectMenuController() { }

	//TODO not pause
	
	@Override
	public void activate(Controller oldController) {
		MenuText text1 = new MenuText();
		text1.setFont(Resources.getMenuFont());
		text1.setX(0);
		text1.setY(-165-15);
		text1.setString(RogueCaveGUI.language.getString(StringID.PAUSED));
		menu.getComponents().add(text1);
		
		//TODO switch player
		//TODO character RogueCavePlaneGUI.configuration

		MenuTextButton button2 = new MenuTextButton();
		button2.setX(-183);
		button2.setY(-60-15);
		button2.setW(366);
		button2.setH(48);
		button2.setString(RogueCaveGUI.language.getString(StringID.OPTIONS));
		button2.setNumber(0);
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
		button3.setNumber(1);
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
		button4.setNumber(2);
		button4.setEnabled(true);
		button4.setActuator(new MenuActuator(){
			@Override
			public void run() {
				RogueCaveGUI.newController = IngameServerController.get();
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
		if (eventKey==RogueCaveGUI.config.getKey(ControlID.QUITFIXED)) { RogueCaveGUI.newController = IngameServerController.get(); }
		if (eventKey==RogueCaveGUI.config.getKey(ControlID.PAUSE)) { RogueCaveGUI.newController = IngameServerController.get(); }
	}
	
	@Override
	public String getName() { return "PauseConnect"; }

}