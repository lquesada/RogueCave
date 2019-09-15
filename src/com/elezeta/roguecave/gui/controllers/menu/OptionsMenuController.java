package com.elezeta.roguecave.gui.controllers.menu;

import com.elezeta.roguecave.gui.ControlID;
import com.elezeta.roguecave.gui.RogueCaveGUI;
import com.elezeta.roguecave.gui.StringID;
import com.elezeta.roguecave.gui.controllers.Controller;
import com.elezeta.roguecave.gui.controllers.MenuController;
import com.elezeta.roguecave.gui.controllers.ingame.PauseMenuController;
import com.elezeta.roguecave.gui.controllers.program.ExitingController;
import com.elezeta.roguecave.gui.menu.MenuActuator;
import com.elezeta.roguecave.gui.menu.MenuText;
import com.elezeta.roguecave.gui.menu.MenuTextButton;
import com.elezeta.roguecave.gui.resources.Resources;


public class OptionsMenuController extends MenuController {

	private static OptionsMenuController singleton;
	public static Controller get() {
		if (singleton==null)
			singleton = new OptionsMenuController();
		return singleton;
	}

	private OptionsMenuController() { }

	Controller oldController = null;
	
	@Override
	public void activate(Controller oldController) {
		super.activate(oldController);
		if (oldController==PauseMenuController.get() || oldController==MainMenuController.get())
			this.oldController = oldController;

		if (this.oldController==MainMenuController.get())
			hasBar = false;
		else
			hasBar = true;

		MenuText text1 = new MenuText();
		text1.setFont(Resources.getMenuFont());
		text1.setX(0);
		text1.setY(-165-15);
		text1.setString(RogueCaveGUI.language.getString(StringID.OPTIONS));
		menu.getComponents().add(text1);

		MenuTextButton button1 = new MenuTextButton();
		button1.setX(-183);
		button1.setY(-120-15);
		button1.setW(366);
		button1.setH(48);
		button1.setString(RogueCaveGUI.language.getString(StringID.LANGUAGES,RogueCaveGUI.language.getString(StringID.LANGUAGENAME)));
		button1.setNumber(0);
		button1.setEnabled(true);
		button1.setActuator(new MenuActuator(){
			@Override
			public void run() {
				RogueCaveGUI.newController = LanguageMenuController.get();
			}
		});
		menu.getComponents().add(button1);

		MenuTextButton button2 = new MenuTextButton();
		button2.setX(-183);
		button2.setY(-60-15);
		button2.setW(366);
		button2.setH(48);
		button2.setString(RogueCaveGUI.language.getString(StringID.CONFIGURECONTROLS));
		button2.setNumber(1);
		button2.setEnabled(true);
		button2.setActuator(new MenuActuator(){
			@Override
			public void run() {
				RogueCaveGUI.newController = ControlMenuController.get();
			}
		});
		menu.getComponents().add(button2);

		MenuTextButton button3 = new MenuTextButton();
		button3.setX(-183);
		button3.setY(0-15);
		button3.setW(366);
		button3.setH(48);
		button3.setString(RogueCaveGUI.language.getString(StringID.AUTOMATICINVENTORY,getOption(RogueCaveGUI.config.automaticInventoryManager,RogueCaveGUI.language.getString(StringID.YES),RogueCaveGUI.language.getString(StringID.NO))));
		button3.setNumber(2);
		button3.setEnabled(true);
		button3.setActuator(new MenuActuator(){
			@Override
			public void run() {
				RogueCaveGUI.config.automaticInventoryManager = !RogueCaveGUI.config.automaticInventoryManager;
				RogueCaveGUI.updateController();
			}
		});
		menu.getComponents().add(button3);

		MenuTextButton button4 = new MenuTextButton();
		button4.setX(-183);
		button4.setY(60-15);
		button4.setW(366);
		button4.setH(48);
		button4.setString(RogueCaveGUI.language.getString(StringID.HIGHQUALITY,getOption(RogueCaveGUI.config.highQuality,RogueCaveGUI.language.getString(StringID.YES),RogueCaveGUI.language.getString(StringID.NO))));
		button4.setNumber(3);
		button4.setEnabled(true);
		button4.setActuator(new MenuActuator(){
			@Override
			public void run() {
				RogueCaveGUI.config.highQuality = !RogueCaveGUI.config.highQuality;
				RogueCaveGUI.updateController();
			}
		});
		menu.getComponents().add(button4);

		MenuTextButton button5 = new MenuTextButton();
		button5.setX(-183);
		button5.setY(120-15);
		button5.setW(366);
		button5.setH(48);
		button5.setString(RogueCaveGUI.language.getString(StringID.MAXFPS,""+RogueCaveGUI.config.gameFPS));
		button5.setNumber(4);
		button5.setEnabled(true);
		button5.setActuator(new MenuActuator(){
			@Override
			public void run() {
				if (RogueCaveGUI.config.gameFPS==75)
					RogueCaveGUI.config.gameFPS=100;
				else if (RogueCaveGUI.config.gameFPS==100)
					RogueCaveGUI.config.gameFPS = 30;
				else if (RogueCaveGUI.config.gameFPS==30)
					RogueCaveGUI.config.gameFPS = 45;
				else if (RogueCaveGUI.config.gameFPS==45)
					RogueCaveGUI.config.gameFPS = 60;
				else // if (RogueCavePlaneGUI.config.maxFPS==60)
					RogueCaveGUI.config.gameFPS = 75;
				RogueCaveGUI.updateController();
			}
		});
		menu.getComponents().add(button5);

		MenuTextButton buttonb = new MenuTextButton();
		buttonb.setX(-183);
		buttonb.setY(200-15);
		buttonb.setW(366);
		buttonb.setH(48);
		if (this.oldController==MainMenuController.get())
			buttonb.setString(RogueCaveGUI.language.getString(StringID.BACK));
		else
			buttonb.setString(RogueCaveGUI.language.getString(StringID.BACK));
		buttonb.setNumber(5);
		buttonb.setEnabled(true);
		final Controller con = this.oldController;
		buttonb.setActuator(new MenuActuator(){
			@Override
			public void run() {
				RogueCaveGUI.newController = con;
			}
		});
		menu.getComponents().add(buttonb);
	}
	
	@Override
	public void deactivate() {
		RogueCaveGUI.saveConfig();
	}

	@Override
	public void requestClose() {
		super.requestClose();
		if (oldController == MainMenuController.get())
			RogueCaveGUI.newController = ExitingController.get();
		else
			RogueCaveGUI.newController = ConfirmCloseMenuController.get();
	}

	@Override
	public void keyPress(int eventKey) {
		super.keyPress(eventKey);
		if (eventKey==RogueCaveGUI.config.getKey(ControlID.QUITFIXED)) { RogueCaveGUI.newController = oldController; }
	}
	
	@Override
	public String getName() { return "OptionsMenu"; }

}
