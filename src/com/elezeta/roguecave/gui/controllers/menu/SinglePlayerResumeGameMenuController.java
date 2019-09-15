package com.elezeta.roguecave.gui.controllers.menu;

import com.elezeta.roguecave.gui.ControlID;
import com.elezeta.roguecave.gui.RogueCaveGUI;
import com.elezeta.roguecave.gui.StringID;
import com.elezeta.roguecave.gui.controllers.Controller;
import com.elezeta.roguecave.gui.controllers.MenuController;
import com.elezeta.roguecave.gui.controllers.ingame.IngameController;
import com.elezeta.roguecave.gui.controllers.program.ExitingController;
import com.elezeta.roguecave.gui.menu.MenuActuator;
import com.elezeta.roguecave.gui.menu.MenuGameButton;
import com.elezeta.roguecave.gui.menu.MenuScroll;
import com.elezeta.roguecave.gui.menu.MenuText;
import com.elezeta.roguecave.gui.menu.MenuTextButton;
import com.elezeta.roguecave.gui.resources.Resources;


public class SinglePlayerResumeGameMenuController extends MenuController {

	private static SinglePlayerResumeGameMenuController singleton;
	public static Controller get() {
		if (singleton==null)
			singleton = new SinglePlayerResumeGameMenuController();
		return singleton;
	}

	private SinglePlayerResumeGameMenuController() { }

	@Override
	public void activate(Controller oldController) {
		super.activate(oldController);

		hasBar = false;

		MenuText text1 = new MenuText();
		text1.setFont(Resources.getMenuFont());
		text1.setX(0);
		text1.setY(-165-15);
		text1.setString(RogueCaveGUI.language.getString(StringID.RESUMEGAME));
		menu.getComponents().add(text1);

		MenuScroll scroll1 = new MenuScroll();
		scroll1.setX(-183);
		scroll1.setY(-120-15);
		scroll1.setW(366+6+24);
		scroll1.setH(288);
		scroll1.setPadding(6);
		scroll1.setComponentHeight(48);
		scroll1.setComponentSeparation(2);
		
		int i = 0;
		for (i = 0;i < RogueCaveGUI.gameState.getSinglePlayerSavedGames().size();i++) {
			MenuGameButton b = new MenuGameButton();
			b.setX(0);
			b.setY(0+i*50);
			b.setW(366);
			b.setH(48);
			b.setNumber(i);
			b.setEnabled(true);
			b.setGame(RogueCaveGUI.gameState.getSinglePlayerSavedGames().get(i));
			final int iPass = i;
			b.setActuator(new MenuActuator(){
				@Override
				public void run() {
					RogueCaveGUI.game = RogueCaveGUI.gameState.getSinglePlayerSavedGames().get(iPass);
					RogueCaveGUI.gameState.getSinglePlayerSavedGames().remove(iPass);
					RogueCaveGUI.newController = IngameController.get();
				}

			});
			scroll1.getComponents().add(b);				
		}
		menu.getComponents().add(scroll1);
		
		MenuTextButton button1 = new MenuTextButton();
		button1.setX(-183);
		button1.setY(200-15);
		button1.setW(366);
		button1.setH(48);
		button1.setString(RogueCaveGUI.language.getString(StringID.BACK));
		button1.setNumber(i);
		button1.setEnabled(true);
		button1.setActuator(new MenuActuator(){
			@Override
			public void run() {
				RogueCaveGUI.newController = SinglePlayerMenuController.get();
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
		if (eventKey==RogueCaveGUI.config.getKey(ControlID.QUITFIXED)) { RogueCaveGUI.newController = SinglePlayerMenuController.get(); }
	}
	
	@Override
	public String getName() { return "ResumeGameMenu"; }

}


