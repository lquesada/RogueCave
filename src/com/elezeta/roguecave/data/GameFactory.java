package com.elezeta.roguecave.data;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.elezeta.roguecave.Game;
import com.elezeta.roguecave.entities.Entity;
import com.elezeta.roguecave.entities.Pickup;
import com.elezeta.roguecave.entities.Player;
import com.elezeta.roguecave.entities.Sex;
import com.elezeta.roguecave.world.Level;
import com.elezeta.roguecave.world.Tile;

public abstract class GameFactory {

	private GameFactory() {
	}

	public static Game generateGame(GameID gId,String name) {
		Game game;
		game = new Game();
		game.setGameID(gId);
		List<Entity> pickups = game.getPickups();
		game.getGoodAttacks();
		game.getBadAttacks();
		game.getBadBeings();
		List<Entity> goodBeings = game.getGoodBeings();

		switch (gId) {

			case level1:
				Player p = PlayerFactory.generatePlayer(ProfessionID.knight,name,Sex.MALE);
				goodBeings.add(p);
				game.setPlayer(p);

				p.getSelfStats().setMaxHp(200);
				p.heal(100);
				p.getSelfStats().setMaxMp(100);
				p.recoverMp(50);
				p.getSelfStats().setHpRegen(1f);
				p.getSelfStats().setMpRegen(1f);
				

			new ArrayList<Entity>();

			
            char[][] map = new char[32][10];
            map[0] = "·WWWWWWWWWWWWWWWWW·WWWWWWW·WWWWW".toCharArray();
            map[1] = "WWWWWWWWWWWWWW·····W·····W·WWWW·".toCharArray();
            map[2] = "WWWWWWWWWWWWWWWWWWWW·WWWWWWWWWW·".toCharArray();
            map[3] = "WWWWWWWWWWWWWW·······WWWWWWWWWW·".toCharArray();
            map[4] = "WWWWWW·WWWWWWWWWWWWWWWWWWWWWWWW·".toCharArray();
            map[5] = "WWWWWW·WWWWWWWWWWWWWWWWWWWWWWWW·".toCharArray();
            map[6] = "WWWWWW·WWWWWWWWWWWWWWWWWWWWWWW··".toCharArray();
            map[7] = "WWWWWW·WWWWWWWWWWWWWWWWWWWWWW·W·".toCharArray();
            map[8] = "WWWWWW·WWWWWWWWWWWWWWWWWWWWWWW··".toCharArray();
            map[9] = "·WWWWW·WWWWWWWWWWWW·············".toCharArray();
            Map<Character,Tile> tiles = new HashMap<Character,Tile>();
            tiles.put('·',new Tile(SpriteID.voidTile,false));
            tiles.put('W',new Tile(SpriteID.stoneTile,SpriteID.stoneWall,true));
            game.setLevel(new Level(map,10,5,tiles,tiles.get('·'),60f,60f,60f));

			
			
			
			
				//TODO QUITA

				Pickup pi;
				
				pi = new Pickup();
				pi.setItem(ItemFactory.generateItem(ItemID.ring1));
				pi.setStartX(100);
				pi.setStartY(100);
				pi.setStartZ(0);
				pickups.add(pi);

				pi = new Pickup();
				pi.setItem(ItemFactory.generateItem(ItemID.ring1));
				pi.setStartX(100);
				pi.setStartY(-100);
				pi.setStartZ(0);
				pickups.add(pi);

				pi = new Pickup();
				pi.setItem(ItemFactory.generateItem(ItemID.ring2));
				pi.setStartX(-100);
				pi.setStartY(100);
				pi.setStartZ(0);
				pickups.add(pi);

				pi = new Pickup();
				pi.setItem(ItemFactory.generateItem(ItemID.ring2));
				pi.setStartX(-100);
				pi.setStartY(-100);
				pi.setStartZ(0);
				pickups.add(pi);

				pi = new Pickup();
				pi.setItem(ItemFactory.generateItem(ItemID.ring3));
				pi.setStartX(100);
				pi.setStartY(0);
				pi.setStartZ(0);
				pickups.add(pi);

				pi = new Pickup();
				pi.setItem(ItemFactory.generateItem(ItemID.ring3));
				pi.setStartX(-100);
				pi.setStartY(0);
				pi.setStartZ(0);
				pickups.add(pi);

				pi = new Pickup();
				pi.setItem(ItemFactory.generateItem(ItemID.amulet1));
				pi.setStartX(-150);
				pi.setStartY(0);
				pi.setStartZ(0);
				pickups.add(pi);

				pi = new Pickup();
				pi.setItem(ItemFactory.generateItem(ItemID.armor1));
				pi.setStartX(150);
				pi.setStartY(0);
				pi.setStartZ(0);
				pickups.add(pi);

				pi = new Pickup();
				pi.setItem(ItemFactory.generateItem(ItemID.helmet1));
				pi.setStartX(-100);
				pi.setStartY(-150);
				pi.setStartZ(0);
				pickups.add(pi);

				pi = new Pickup();
				pi.setItem(ItemFactory.generateItem(ItemID.armor2));
				pi.setStartX(-150);
				pi.setStartY(-100);
				pi.setStartZ(0);
				pickups.add(pi);

				pi = new Pickup();
				pi.setItem(ItemFactory.generateItem(ItemID.legs1));
				pi.setStartX(0);
				pi.setStartY(-200);
				pi.setStartZ(0);
				pickups.add(pi);

				pi = new Pickup();
				pi.setItem(ItemFactory.generateItem(ItemID.boots1));
				pi.setStartX(0);
				pi.setStartY(+150);
				pi.setStartZ(0);
				pickups.add(pi);

				pi = new Pickup();
				pi.setItem(ItemFactory.generateItem(ItemID.arms1));
				pi.setStartX(0);
				pi.setStartY(-150);
				pi.setStartZ(0);
				pickups.add(pi);

				pi = new Pickup();
				pi.setItem(ItemFactory.generateItem(ItemID.arms1));
				pi.setStartX(0);
				pi.setStartY(-350);
				pi.setStartZ(0);
				pickups.add(pi);

				pi = new Pickup();
				pi.setItem(ItemFactory.generateItem(ItemID.armor2));
				pi.setStartX(0);
				pi.setStartY(-250);
				pi.setStartZ(0);
				pickups.add(pi);

				pi = new Pickup();
				pi.setItem(ItemFactory.generateItem(ItemID.legs1));
				pi.setStartX(0);
				pi.setStartY(200);
				pi.setStartZ(0);
				pickups.add(pi);
				
				pi = new Pickup();
				pi.setItem(ItemFactory.generateItem(ItemID.helmet1));
				pi.setStartX(200);
				pi.setStartY(0);
				pi.setStartZ(0);
				pickups.add(pi);


				pi = new Pickup();
				pi.setItem(ItemFactory.generateItem(ItemID.knife));
				pi.setStartX(200);
				pi.setStartY(100);
				pi.setStartZ(0);
				pickups.add(pi);

				pi = new Pickup();
				pi.setItem(ItemFactory.generateItem(ItemID.bow));
				pi.setStartX(200);
				pi.setStartY(130);
				pi.setStartZ(0);
				pickups.add(pi);

				pi = new Pickup();
				pi.setItem(ItemFactory.generateItem(ItemID.arrows,3));
				pi.setStartX(200);
				pi.setStartY(160);
				pi.setStartZ(0);
				pickups.add(pi);

				pi = new Pickup();
				pi.setItem(ItemFactory.generateItem(ItemID.arrows,20));
				pi.setStartX(200);
				pi.setStartY(190);
				pi.setStartZ(0);
				pickups.add(pi);

				pi = new Pickup();
				pi.setItem(ItemFactory.generateItem(ItemID.shield));
				pi.setStartX(230);
				pi.setStartY(100);
				pi.setStartZ(0);
				pickups.add(pi);

				pi = new Pickup();
				pi.setItem(ItemFactory.generateItem(ItemID.explosionWand));
				pi.setStartX(230);
				pi.setStartY(130);
				pi.setStartZ(0);
				pickups.add(pi);

				pi = new Pickup();
				pi.setItem(ItemFactory.generateItem(ItemID.firewallWand));
				pi.setStartX(230);
				pi.setStartY(160);
				pi.setStartZ(0);
				pickups.add(pi);

				pi = new Pickup();
				pi.setItem(ItemFactory.generateItem(ItemID.fireSlashWand));
				pi.setStartX(230);
				pi.setStartY(190);
				pi.setStartZ(0);
				pickups.add(pi);

				pi = new Pickup();
				pi.setItem(ItemFactory.generateItem(ItemID.fireWand));
				pi.setStartX(260);
				pi.setStartY(100);
				pi.setStartZ(0);
				pickups.add(pi);

				pi = new Pickup();
				pi.setItem(ItemFactory.generateItem(ItemID.waterWand));
				pi.setStartX(260);
				pi.setStartY(130);
				pi.setStartZ(0);
				pickups.add(pi);

				/*
				pi = new Pickup();
				pi.setItem(ItemFactory.generateItem(ItemID.healWand));
				pi.setStartX(290);
				pi.setStartY(100);
				pi.setStartZ(0);
				pickups.add(pi);

				pi = new Pickup();
				pi.setItem(ItemFactory.generateItem(ItemID.healPotion));
				pi.setStartX(290);
				pi.setStartY(130);
				pi.setStartZ(0);
				pickups.add(pi);
				*/
				
				

				/*
				pi = new Pickup();
				pi.setItem(ItemFactory.generateItem(ItemID.fireSpell));
				pi.setStartX(260);
				pi.setStartY(160);
				pi.setStartZ(0);
				pickups.add(pi);

				pi = new Pickup();
				pi.setItem(ItemFactory.generateItem(ItemID.fireExplosionSpell));
				pi.setStartX(260);
				pi.setStartY(190);
				pi.setStartZ(0);
				pickups.add(pi);
		*/			
				return game;
				
			default:
				System.err.println("--------------");
				System.err.println("CRITICAL ERROR");
	    		System.err.println("Invalid game.");
				System.err.println("GameID "+gId);
				System.err.println("--------------");
				game.setGameID(null);
				return game;
		}
	}
}
