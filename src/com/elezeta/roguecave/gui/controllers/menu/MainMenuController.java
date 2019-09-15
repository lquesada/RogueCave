package com.elezeta.roguecave.gui.controllers.menu;

import com.elezeta.roguecave.gui.ControlID;
import com.elezeta.roguecave.gui.GraphicID;
import com.elezeta.roguecave.gui.RogueCaveGUI;
import com.elezeta.roguecave.gui.StringID;
import com.elezeta.roguecave.gui.controllers.Controller;
import com.elezeta.roguecave.gui.controllers.MenuController;
import com.elezeta.roguecave.gui.controllers.program.ExitingController;
import com.elezeta.roguecave.gui.menu.MenuActuator;
import com.elezeta.roguecave.gui.menu.MenuGraphic;
import com.elezeta.roguecave.gui.menu.MenuTextButton;


public class MainMenuController extends MenuController {

	private static MainMenuController singleton;
	public static Controller get() {
		if (singleton==null)
			singleton = new MainMenuController();
		return singleton;
	}

	private MainMenuController() { }

	@Override
	public void activate(Controller oldController) {
		super.activate(oldController);

		hasBar = false;
		
		MenuGraphic graphic1 = new MenuGraphic();
		graphic1.setX(0);
		graphic1.setY(-180-15);
		graphic1.setGraphic(GraphicID.GUILogo);
		menu.getComponents().add(graphic1);


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

		/*TODO
		MenuTextButton button1 = new MenuTextButton();
		button1.setX(-183);
		button1.setY(-120-15);
		button1.setW(366);
		button1.setH(48);
		button1.setString(RogueCaveGUI.language.getString(StringID.SINGLEPLAYER));
		button1.setNumber(0);
		button1.setEnabled(true);
		button1.setActuator(new MenuActuator(){
			@Override
			public void run() {
				RogueCaveGUI.newController = SinglePlayerMenuController.get();
			}
		});
		menu.getComponents().add(button1);

		MenuTextButton button2 = new MenuTextButton();
		button2.setX(-183);
		button2.setY(-60-15);
		button2.setW(366);
		button2.setH(48);
		button2.setString(RogueCaveGUI.language.getString(StringID.MULTIPLAYER));
		button2.setNumber(1);
		button2.setEnabled(false); //TODO
		button2.setActuator(new MenuActuator(){
			@Override
			public void run() {
				RogueCaveGUI.newController = MultiplayerMenuController.get();
			}
		});
		menu.getComponents().add(button2);
		*/

		MenuTextButton button3 = new MenuTextButton();
		button3.setX(-183);
		button3.setY(0-15);
		button3.setW(366);
		button3.setH(48);
		button3.setString(RogueCaveGUI.language.getString(StringID.OPTIONS));
		button3.setNumber(2);
		button3.setEnabled(true);
		button3.setActuator(new MenuActuator(){
			@Override
			public void run() {
				RogueCaveGUI.newController = OptionsMenuController.get();
			}
		});
		menu.getComponents().add(button3);

		MenuTextButton button4 = new MenuTextButton();
		button4.setX(-183);
		button4.setY(60-15);
		button4.setW(366);
		button4.setH(48);
		button4.setString(RogueCaveGUI.language.getString(StringID.ACHIEVEMENTS));
		button4.setNumber(3);
		button4.setEnabled(false); //TODO
		button4.setActuator(new MenuActuator(){
			@Override
			public void run() {
				RogueCaveGUI.newController = AchievementsMenuController.get();
			}
		});
		menu.getComponents().add(button4);

		MenuTextButton button5 = new MenuTextButton();
		button5.setX(-183);
		button5.setY(120-15);
		button5.setW(366);
		button5.setH(48);
		button5.setString(RogueCaveGUI.language.getString(StringID.CREDITS));
		button5.setNumber(4);
		button5.setEnabled(true);
		button5.setActuator(new MenuActuator(){
			@Override
			public void run() {
				RogueCaveGUI.newController = CreditsMenuController.get();
			}
		});
		menu.getComponents().add(button5);

		MenuTextButton buttonq = new MenuTextButton();
		buttonq.setX(-183);
		buttonq.setY(200-15);
		buttonq.setW(366);
		buttonq.setH(48);
		buttonq.setString(RogueCaveGUI.language.getString(StringID.EXIT));
		buttonq.setNumber(5);
		buttonq.setEnabled(true);
		buttonq.setActuator(new MenuActuator(){
			@Override
			public void run() {
				RogueCaveGUI.newController = ExitingController.get();
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
		if (eventKey==RogueCaveGUI.config.getKey(ControlID.QUITFIXED)) { RogueCaveGUI.newController = ExitingController.get(); }
	}

	@Override
	public String getName() { return "MainMenu"; }

}
