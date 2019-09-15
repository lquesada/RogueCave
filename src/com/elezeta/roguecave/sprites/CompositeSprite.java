package com.elezeta.roguecave.sprites;

import java.util.HashMap;
import java.util.Map;

public class CompositeSprite extends SpriteData {

	private Map<SpriteSlotID,SingleSprite> sprites = new HashMap<SpriteSlotID,SingleSprite>();

	public Map<SpriteSlotID, SingleSprite> getSprites() {
		return sprites;
	}
	
}