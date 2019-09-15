package com.elezeta.roguecave.gui.controllers.menu;

import org.lwjgl.input.Keyboard;

import com.elezeta.roguecave.data.GameFactory;
import com.elezeta.roguecave.data.GameID;
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


public class SinglePlayerLevelMenuController extends MenuController {

	private static SinglePlayerLevelMenuController singleton;
	public static Controller get() {
		if (singleton==null)
			singleton = new SinglePlayerLevelMenuController();
		return singleton;
	}

	protected SinglePlayerLevelMenuController() { }
	
	protected MenuTextButton buttonb;

	MenuTextButton button1;
	
	@Override
	public void activate(Controller oldController) {
		super.activate(oldController);
		hasBar = false;

		MenuText text1 = new MenuText();
		text1.setFont(Resources.getMenuFont());
		text1.setX(0);
		text1.setY(-165-15);
		text1.setString(RogueCaveGUI.language.getString(StringID.NEWGAME));
		menu.getComponents().add(text1);

		MenuScroll scroll1 = new MenuScroll();
		scroll1.setX(-183);
		scroll1.setY(-120-15);
		scroll1.setW(366+6+24);
		scroll1.setH(288);
		scroll1.setPadding(6);
		scroll1.setComponentHeight(48);
		scroll1.setComponentSeparation(2);
		
		final GameID[] games = GameID.values();

		final String name = RogueCaveGUI.config.getAutoName();
		
		int i = 0;
		for (i = 0;i < games.length;i++) {
			MenuGameButton b = new MenuGameButton();
			b.setX(0);
			b.setY(0+i*50);
			b.setW(366);
			b.setH(48);
			b.setNumber(i);
			b.setEnabled(true);
			b.setGame(GameFactory.generateGame(games[i],name));
			final int iPass = i;
			
			b.setActuator(new MenuActuator(){
				@Override
				public void run() {
					RogueCaveGUI.game = GameFactory.generateGame(games[iPass],name);
					RogueCaveGUI.newController = IngameController.get();
				}

			});
			scroll1.getComponents().add(b);				
		}
		menu.getComponents().add(scroll1);
		
		buttonb = new MenuTextButton();
		buttonb.setX(-183);
		buttonb.setY(200-15);
		buttonb.setW(366);
		buttonb.setH(48);
		buttonb.setString(RogueCaveGUI.language.getString(StringID.BACK));
		buttonb.setNumber(i);
		buttonb.setEnabled(true);
		buttonb.setActuator(new MenuActuator(){
			@Override
			public void run() {
				RogueCaveGUI.newController = MainMenuController.get();
			}
		});
		menu.getComponents().add(buttonb);
	}
	
	@Override
	public void requestClose() {
		super.requestClose();
		RogueCaveGUI.newController = ExitingController.get();
	}

	@Override
	public void keyPressFilter(int eventKey,char character) {
		menuKeysSoft(menu,eventKey);
		if (eventKey==RogueCaveGUI.config.getKey(ControlID.QUITFIXED)) { RogueCaveGUI.newController = SinglePlayerMenuController.get(); }
			Keyboard.enableRepeatEvents(false);
	}

	@Override
	public String getName() { return "SinglePlayerLevelMenu"; }

}
