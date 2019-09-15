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


public class CreditsMenuController extends MenuController {

	private static CreditsMenuController singleton;
	public static Controller get() {
		if (singleton==null)
			singleton = new CreditsMenuController();
		return singleton;
	}

	private CreditsMenuController() { }

	@Override
	public void activate(Controller oldController) {
		super.activate(oldController);
		hasBar = false;

		String credits[] = {
				"RogueCave, Copyright (c) 2014, Luis Quesada Torres (luis@elezeta.com)",
				"",
				RogueCaveGUI.language.getString(StringID.CREDITSUSES,"RogueCave"),
				"",
				"- JarSplice (http://ninjacave.com/jarsplice)                               ",
				"- Java Runtime Environment (http://www.java.com)                           ",
				"- KryoNet (http://code.google.com/p/kryonet/)                              ",
				"- Lightweight Java Game Library (http://www.lwjgl.org)                     ",
				"- PixelFarm (http://bitbucket.org/tebruno99/pixelfarm)                     ",
				"- PNGDecoder (http://l33tlabs.org)                                         ",
				"- ProGuard (http://proguard.sourceforge.net)                               ",
				"- Slick 2D (http://www.slick2d.org)                                        ",
				"",
				RogueCaveGUI.language.getString(StringID.CREDITSINSPIRED,"RogueCave"),
				"",
				"- Crossfire (http://crossfire.real-time.com/)                               ",
				"- Cube World (http://picroma.com/cubeworld)                                 ",
				"- Gaunlet (http://www.atari.com)                                            ",
				"- Hack, Slash, and Crawl (http://www.mochigames.com/games/hack-slash-crawl/)",
				"- Hack, Slash, and Loot (http://hackslashloot.com/)                         ",
				"- Minecraft (http://minecraft.net/)                                         ",
				"- NetHack (http://www.nethack.org/)                                         ",
				"- Questopia (http://armorgames.com/play/15473/questopia)                    ",
				"- Rogue (http://rogue.rogueforge.net/rogue-5-4/)                            ",
				"- Terraria Online (http://www.terrariaonline.com/)                          ",
		};
		int y = -235;
		for (int i = 0;i < credits.length;i++) {
			MenuText text = new MenuText();
			text.setFont(Resources.getInfoFont());
			text.setX(0);
			text.setY(y);
			text.setString(credits[i]);
			y += 16;
			menu.getComponents().add(text);
		}

		

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
	public String getName() { return "CreditsMenu"; }

}


