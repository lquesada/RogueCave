package com.elezeta.roguecave.gui.controllers.program;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import com.elezeta.roguecave.gui.RogueCaveGUI;
import com.elezeta.roguecave.gui.controllers.Controller;
import com.elezeta.roguecave.gui.controllers.menu.MainMenuController;


public class StartingController extends Controller {

	private static StartingController singleton;
	public static Controller get() {
		if (singleton==null)
			singleton = new StartingController();
		return singleton;
	}

	private StartingController() { }

	private int skip = 0;
	
	@Override
	public void run() {
		super.run();
		mouseGrab(false);
		if (skip == 0)
			skip++;
		else {
			emptyInput();
			RogueCaveGUI.newController = MainMenuController.get();
		}
	}
	
	@Override
	public void deactivate() {
		emptyInput();
		RogueCaveGUI.changeBackground();
	}
	
	private void emptyInput() {
		while (Keyboard.next());
		while (Mouse.next());
	}

	@Override
	public String getName() { return "Starting"; }

}


