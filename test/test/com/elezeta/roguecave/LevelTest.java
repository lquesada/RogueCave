package test.com.elezeta.roguecave;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.elezeta.roguecave.data.SpriteID;
import com.elezeta.roguecave.world.Level;
import com.elezeta.roguecave.world.Tile;

public class LevelTest {

	Level level1;

	Level level2;
	
	Map<Character,Tile> tiles1;
	
	Map<Character,Tile> tiles2;
	public LevelTest() {

		char[][] map1 = new char[10][10];
		         //0123456789
		map1[0] = "····WWWWWW".toCharArray();
		map1[1] = "····W··WWW".toCharArray();
		map1[2] = "····W·····".toCharArray();
		map1[3] = "··WWWWW···".toCharArray();
		map1[4] = "··WWWWWW··".toCharArray();
		map1[5] = "··WWWWWW··".toCharArray();
		map1[6] = "··WWWWWW··".toCharArray();
		map1[7] = "···WW·····".toCharArray();
		map1[8] = "WWWWW·····".toCharArray();
		map1[9] = "WWWWW·····".toCharArray();
		tiles1 = new HashMap<Character,Tile>();
		tiles1.put('·',new Tile(SpriteID.voidTile,false));
		tiles1.put('W',new Tile(SpriteID.stoneTile,SpriteID.stoneWall,true));
		level1 = new Level(map1,0,0,tiles1,tiles1.get('·'),64f,64f,64f);

		char[][] map2 = new char[0][0];
		tiles2 = new HashMap<Character,Tile>();
		tiles2.put('W',new Tile(SpriteID.stoneTile,SpriteID.stoneWall,true));

		level2 = new Level(map2,0,0,tiles2,tiles2.get('W'),64f,64f,64f);		
	}
	@Test
	public void levelTest1() {
		assertEquals(tiles1.get('W'),level1.getTile(4,0));
		assertEquals(tiles1.get('·'),level1.getTile(3,0));
		assertEquals(tiles1.get('·'),level1.getTile(1,5));
		assertEquals(tiles1.get('W'),level1.getTile(2,5));
		assertEquals(tiles2.get('W'),level2.getTile(0,5));
		assertEquals(tiles2.get('W'),level2.getTile(0,4));
		assertEquals(tiles2.get('W'),level2.getTile(5,1));
		assertEquals(tiles2.get('W'),level2.getTile(5,2));
	}
	
}
