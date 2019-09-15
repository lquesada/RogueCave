package com.elezeta.roguecave.sprites;

import com.elezeta.roguecave.data.SpriteID;

public class ColorSprite extends SingleSprite {

	private ColorMask color;

	public ColorSprite(SpriteID spriteID,ColorMask color) {
		super(spriteID);
		this.color = color;
	}
	
	public ColorMask getColor() {
		return color;
	}
	
	public void setColor(ColorMask color) {
		this.color = color;
	}
}
