package com.elezeta.roguecave.gui.controllers.menu;

import com.elezeta.roguecave.gui.controllers.ConfirmMenuController;
import com.elezeta.roguecave.gui.controllers.Controller;
import com.elezeta.roguecave.gui.controllers.program.ExitingController;


public class ConfirmCloseMenuController extends ConfirmMenuController {

	private static ConfirmCloseMenuController singleton;
	public static Controller get() {
		if (singleton==null)
			singleton = new ConfirmCloseMenuController();
		return singleton;
	}

	private ConfirmCloseMenuController() { }

	@Override
	public void activate(Controller oldController) {
		super.activate(oldController);
		target = ExitingController.get();
	}
	
	@Override
	public String getName() { return "ConfirmCloseMenu"; }


}