package com.elezeta.roguecave.gui.controllers;

import com.elezeta.roguecave.gui.controllers.menu.MainMenuController;

//TODO put into right package
public class ConfirmLeaveMenuController extends ConfirmMenuController {

	private static ConfirmLeaveMenuController singleton;
	public static Controller get() {
		if (singleton==null)
			singleton = new ConfirmLeaveMenuController();
		return singleton;
	}
	
	private ConfirmLeaveMenuController() { }

	@Override
	public void activate(Controller oldController) {
		super.activate(oldController);
		target = MainMenuController.get();
	}
	
	@Override
	public String getName() { return "ConfirmLeaveMenu"; }

}
