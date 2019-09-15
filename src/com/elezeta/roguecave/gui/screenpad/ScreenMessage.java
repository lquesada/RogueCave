package com.elezeta.roguecave.gui.screenpad;

import org.newdawn.slick.Color;

import com.elezeta.roguecave.gui.RogueCaveGUI;

public class ScreenMessage {

	private String text;
	
	private float time;

	private Color color;

	private Color shadowColor;
	
	private Color backgroundColor;

	public ScreenMessage(String text) {
		this(text,Color.white,Color.black);
	}

	public ScreenMessage(String text,Color color,Color shadowColor) {
		this(text,Color.white,Color.black,null);
	}

	public ScreenMessage(String text,Color color,Color shadowColor,Color backgroundColor) {
		this.text = text;
		this.time = RogueCaveGUI.config.messageShowTime+RogueCaveGUI.config.messageDisappearTime;
		this.color = color;
		this.shadowColor = shadowColor;
		if (this.color == null)
			this.color = Color.white;
		if (this.shadowColor == null)
			this.shadowColor = Color.black;
		this.backgroundColor = backgroundColor;
	}

	public String getText() {
		return text;
	}

	public float getTime() {
		return time;
	}

	public void setTime(float time) {
		this.time = time;		
	}
	
	public Color getColor() {
		return color;
	}

	public Color getShadowColor() {
		return shadowColor;
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}
	public void pass(float delta) {
		if (time > 0f)
			time -= delta;
	}


}
