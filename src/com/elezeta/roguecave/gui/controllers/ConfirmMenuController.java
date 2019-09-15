package com.elezeta.roguecave.gui.controllers;

import com.elezeta.roguecave.gui.ControlID;
import com.elezeta.roguecave.gui.RogueCaveGUI;
import com.elezeta.roguecave.gui.StringID;
import com.elezeta.roguecave.gui.controllers.program.ExitingController;
import com.elezeta.roguecave.gui.menu.MenuActuator;
import com.elezeta.roguecave.gui.menu.MenuText;
import com.elezeta.roguecave.gui.menu.MenuTextButton;
import com.elezeta.roguecave.gui.resources.Resources;


//TODO put into right package

public class ConfirmMenuController extends MenuController {

	private static ConfirmMenuController singleton;
	public static Controller get() {
		if (singleton==null)
			singleton = new ConfirmMenuController();
		return singleton;
	}

	protected ConfirmMenuController() { }

	protected Controller oldController = null;
	protected Controller target = null;
	int closeCount = 0;
	
	@Override
	public void activate(Controller oldController) {
		super.activate(oldController);
		this.oldController = oldController;
		closeCount = 0;

		MenuText text1 = new MenuText();
		text1.setFont(Resources.getMenuFont());
		text1.setX(0);
		text1.setY(-180-15);
		text1.setString(RogueCaveGUI.language.getString(StringID.CURRENTLOST));
		menu.getComponents().add(text1);

		MenuText text2 = new MenuText();
		text2.setFont(Resources.getMenuFont());
		text2.setX(0);
		text2.setY(-150-15);
		text2.setString(RogueCaveGUI.language.getString(StringID.AREYOUSURE));
		menu.getComponents().add(text2);

		MenuTextButton button1 = new MenuTextButton();
		button1.setX(-183);
		button1.setY(-120-15);
		button1.setW(366);
		button1.setH(48);
		button1.setString(RogueCaveGUI.language.getString(StringID.NO));
		button1.setNumber(0);
		button1.setEnabled(true);
		final Controller con = this.oldController;
		button1.setActuator(new MenuActuator(){
			@Override
			public void run() {
				RogueCaveGUI.newController = con;
			}
		});
		menu.getComponents().add(button1);

		MenuTextButton button2 = new MenuTextButton();
		button2.setX(-183);
		button2.setY(-60-15);
		button2.setW(366);
		button2.setH(48);
		button2.setString(RogueCaveGUI.language.getString(StringID.YES));
		button2.setNumber(1);
		button2.setEnabled(true);
		button2.setActuator(new MenuActuator(){
			@Override
			public void run() {
				RogueCaveGUI.newController = target;
			}
		});
		menu.getComponents().add(button2);
	}
	
	@Override
	public void requestClose() {
		super.requestClose();
		closeCount++;
		if (closeCount>=RogueCaveGUI.config.closeForce) {
			RogueCaveGUI.saveGame(RogueCaveGUI.game,true);
			RogueCaveGUI.newController = ExitingController.get();
		}
	}

	@Override
	public void keyPress(int eventKey) {
		super.keyPress(eventKey);
		if (eventKey==RogueCaveGUI.config.getKey(ControlID.QUITFIXED)) { RogueCaveGUI.newController = oldController; }
	}

	@Override
	public String getName() { return "ConfirmMenu"; }

}


