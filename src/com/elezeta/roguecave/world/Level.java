package com.elezeta.roguecave.world;

import java.util.Map;


public class Level {

	private Tile[][] map;
	private int iDrift;
	private int jDrift;
	private float tileFactor;
	private float wallHeight;
	private float ceilingHeight;

	private Tile defaultTile;

	private Map<Character,Tile> tiles;
	
	public Level(char [][] map,int iDrift,int jDrift,Map<Character,Tile> tiles,Tile defaultTile,float tileFactor,float wallHeight,float ceilingHeight) {
		setIDrift(iDrift);
		setJDrift(jDrift);
		setTiles(tiles);
		setMap(map);
		setDefaultTile(defaultTile);
		setTileFactor(tileFactor);	
		setWallHeight(wallHeight);
		setCeilingHeight(ceilingHeight);
	}	
	
	public Tile[][] getMap() {
		return map;
	}


	private void setMap(char [][] map) {
		this.map = new Tile[map.length][];
		for (int j = 0;j < map.length;j++) {
			this.map[j] = new Tile[map[j].length];
			for (int i = 0;i < map[j].length;i++) {
				Tile tile = getTiles().get(map[j][i]);
				if (tile == null)
					tile = defaultTile;
				this.map[j][i] = tile;
			}
		}
	}

	private Map<Character,Tile> getTiles() {
		return tiles;
	}


	private void setTiles(Map<Character,Tile> tiles) {
		this.tiles = tiles;
	}

	public int getIDrift() {
		return iDrift;
	}


	private void setIDrift(int iDrift) {
		this.iDrift = iDrift;
	}


	public int getJDrift() {
		return jDrift;
	}


	private void setJDrift(int jDrift) {
		this.jDrift = jDrift;
	}

	public Tile getDefaultTile() {
		return defaultTile;
	}

	private void setDefaultTile(Tile defaultTile) {
		this.defaultTile = defaultTile;
	}

	public float getTileFactor() {
		return tileFactor;
	}

	private void setTileFactor(float tileFactor) {
		this.tileFactor = tileFactor;
	}

	public float getWallHeight() {
		return wallHeight;
	}

	private void setWallHeight(float wallHeight) {
		this.wallHeight = wallHeight;
	}

	public float getCeilingHeight() {
		return ceilingHeight;
	}

	private void setCeilingHeight(float ceilingHeight) {
		this.ceilingHeight = ceilingHeight;
	}

	public Tile getTile(int i,int j) {
		int ai = i+getIDrift();
		int aj = j+getJDrift();
		if (ai<0 || ai>=getMap().length)
			return getDefaultTile();
		if (aj<0 || aj>=getMap()[ai].length)
			return getDefaultTile();
		Tile tile = getMap()[aj][ai];
		if (tile == null)
			return defaultTile;
		return tile;
	}

}
