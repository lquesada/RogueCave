package com.elezeta.roguecave.sprites;

import com.elezeta.roguecave.data.SpriteID;

public class SingleSprite extends SpriteData {
	private SpriteID spriteID;
	
	public SingleSprite(SpriteID spriteID) {
		this.spriteID = spriteID; 
	}

	public SpriteID getSpriteID() {
		return spriteID;
	}
	
	public void setSpriteID(SpriteID spriteID) {
		this.spriteID = spriteID;
	}
	
}
