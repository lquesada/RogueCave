package com.elezeta.roguecave.world;

import com.elezeta.roguecave.data.SpriteID;


public final class Tile {

	private SpriteID groundSprite;
	
	private SpriteID wallSprite;

	private boolean walkable;

	private boolean hasWalls;

	private boolean hasWallsAgainst;

	public Tile(SpriteID ground,boolean walkable) {
		this(ground,SpriteID.naught,walkable);
	}
	
	public Tile(SpriteID ground,SpriteID walls,boolean walkable) {
		this(ground,walls,walkable,walkable,!walkable);
	}

	public Tile(SpriteID ground,SpriteID walls,boolean walkable,boolean hasWalls,boolean hasWallsAgainst) {
		setGround(ground);
		setWallSprite(walls);
		setWalkable(walkable);
		setHasWalls(hasWalls);
		setHasWallsAgainst(hasWallsAgainst);
	}

	public SpriteID getGroundSprite() {
		return groundSprite;
	}

	private void setGround(SpriteID groundSprite) {
		this.groundSprite = groundSprite;
	}

	public SpriteID getWallSprite() {
		return wallSprite;
	}

	private void setWallSprite(SpriteID wallSprite) {
		this.wallSprite = wallSprite;
	}

	public boolean isWalkable() {
		return walkable;
	}

	private void setWalkable(boolean walkable) {
		this.walkable = walkable;
	}

	public boolean hasWalls() {
		return hasWalls;
	}

	private void setHasWalls(boolean hasWalls) {
		this.hasWalls = hasWalls;
	}

	public boolean hasWallsAgainst() {
		return hasWallsAgainst;
	}

	private void setHasWallsAgainst(boolean hasWallsAgainst) {
		this.hasWallsAgainst = hasWallsAgainst;
	}
	
}
