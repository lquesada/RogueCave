package com.elezeta.roguecave.gui.menu;

public class MenuText extends MenuComponent {

	private String string = null;
	
	@SuppressWarnings("deprecation")
	private org.newdawn.slick.TrueTypeFont font;
	
	public String getString() {
		return string;
	}
	
	public void setString(String string) {
		this.string = string;
	}

	@SuppressWarnings("deprecation")
	public org.newdawn.slick.TrueTypeFont getFont() {
		return font;
	}

	@SuppressWarnings("deprecation")
	public void setFont(org.newdawn.slick.TrueTypeFont font) {
		this.font = font;
	}
	
}
