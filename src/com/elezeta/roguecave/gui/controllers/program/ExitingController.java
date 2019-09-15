package com.elezeta.roguecave.gui.controllers.program;

import com.elezeta.roguecave.gui.controllers.Controller;


public class ExitingController extends Controller {

	private static ExitingController singleton;
	public static Controller get() {
		if (singleton==null)
			singleton = new ExitingController();
		return singleton;
	}

	private ExitingController() { }

	@Override
	public String getName() { return "Exiting"; }

}
