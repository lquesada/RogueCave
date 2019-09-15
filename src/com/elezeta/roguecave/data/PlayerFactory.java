package com.elezeta.roguecave.data;

import com.elezeta.roguecave.entities.Player;
import com.elezeta.roguecave.entities.Sex;
import com.elezeta.roguecave.entities.Stats;
import com.elezeta.roguecave.sprites.ColorMask;
import com.elezeta.roguecave.sprites.ColorSprite;
import com.elezeta.roguecave.sprites.CompositeSprite;
import com.elezeta.roguecave.sprites.SingleSprite;
import com.elezeta.roguecave.sprites.SpriteSlotID;

public abstract class PlayerFactory {

	private PlayerFactory() {
	}
		

	public static Player generatePlayer(ProfessionID profession,String name) {
		return generatePlayer(profession,name,Sex.MALE);
	}
	
	public static Player generatePlayer(ProfessionID profession,String name,Sex sex) {
		Player p = new Player();
		p.setName(name);
		p.setProfession(profession);
		p.setHeight(48f);
		p.setHandsHeight(16f);
		p.setRadius(16f);
		CompositeSprite cs = new CompositeSprite();
		p.setSprite(cs);
		p.setShadow(0.6f);
		p.setHp(20f);
		Stats stats = new Stats();
		stats.setMaxHp(20f);
		stats.setHpRegen(1);
		stats.setMpRegen(1);
		stats.setSpeed(10);
		stats.setRecovery(10);
		stats.setAccuracy(10);
		p.setSelfStats(stats);
		
		p.setUpgradePoints(100);

		switch (profession) {
			case knight:
				cs.getSprites().put(SpriteSlotID.BODY,new SingleSprite(SpriteID.humanbody1));
				cs.getSprites().put(SpriteSlotID.EYESKIN,new SingleSprite(SpriteID.humaneyeskin1));
				cs.getSprites().put(SpriteSlotID.EYEWHITE,new SingleSprite(SpriteID.humaneyewhite1));
				cs.getSprites().put(SpriteSlotID.EYECOLOR,new SingleSprite(SpriteID.humaneyecolor1));
				cs.getSprites().put(SpriteSlotID.EYEBROWS,new SingleSprite(SpriteID.humaneyebrows1));
				if (sex==Sex.MALE) {
					cs.getSprites().put(SpriteSlotID.HAIRSHORT,new SingleSprite(SpriteID.humanhairshort1));
					cs.getSprites().put(SpriteSlotID.HAIRLONG,new SingleSprite(SpriteID.humanhairlong1));
				}
				if (sex==Sex.FEMALE) {
					cs.getSprites().put(SpriteSlotID.HAIRSHORT,new SingleSprite(SpriteID.humanhairshort6));
					cs.getSprites().put(SpriteSlotID.HAIRLONG,new SingleSprite(SpriteID.humanhairlong4));
					cs.getSprites().put(SpriteSlotID.HAIRDROPBACK,new SingleSprite(SpriteID.humanhairdropback2));
					cs.getSprites().put(SpriteSlotID.HAIRDROPFRONT,new SingleSprite(SpriteID.humanhairdropfront2));
				}
				underWear(cs,sex);
				stats.setSpeed(20);
				UpgradeFactory.generateUpgrade(UpgradeID.HUMAN).apply(p);
			case naught:
			default:
		}
		return p;
	}
	private static void underWear(CompositeSprite cs,Sex sex) {
		underWear(cs,sex,new ColorMask(1f,1f,1f,1f));
	}
	

	private static void underWear(CompositeSprite cs,Sex sex,ColorMask color) {
		if (sex == Sex.MALE) {
			cs.getSprites().put(SpriteSlotID.SHORTS,new ColorSprite(SpriteID.humanshortsmale,color));
		}
		if (sex == Sex.FEMALE) {
			cs.getSprites().put(SpriteSlotID.SHORTS,new ColorSprite(SpriteID.humanshortsfemale,color));
			cs.getSprites().put(SpriteSlotID.TOP,new ColorSprite(SpriteID.humantopfemale,color));
		}
	}
	
	//TODO tests
	
}
