package com.elezeta.roguecave.gui.menu;

public class MenuButton extends MenuComponent {
	private boolean Enabled = false;

	private int number = 0;
	private int w = 0;
	
	private int h = 0;
	
	private MenuActuator actuator = null;
	
	public boolean isEnabled() {
		return Enabled;
	}

	public void setEnabled(boolean enabled) {
		Enabled = enabled;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public MenuActuator getActuator() {
		return actuator;
	}

	public void setActuator(MenuActuator actuator) {
		this.actuator = actuator;
	}
	
}
