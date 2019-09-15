package com.elezeta.roguecave.gui.resources;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import com.elezeta.roguecave.data.SpriteID;
import com.elezeta.roguecave.gui.GraphicID;
import de.matthiasmann.twl.utils.PNGDecoder;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.opengl.GL11.*;

public abstract class Resources {

	@SuppressWarnings("deprecation")
	private static org.newdawn.slick.TrueTypeFont smallFont;
	@SuppressWarnings("deprecation")
	private static org.newdawn.slick.TrueTypeFont infoFont;
	@SuppressWarnings("deprecation")
	private static org.newdawn.slick.TrueTypeFont boldFont;
	@SuppressWarnings("deprecation")
	private static org.newdawn.slick.TrueTypeFont bigFont;
	@SuppressWarnings("deprecation")
	private static org.newdawn.slick.TrueTypeFont menuFont;

    
    private static Map<SpriteID,Sprite> sprites;
    private static Map<GraphicID,Graphic> graphics;
    private static ByteBuffer icon32;
    private static ByteBuffer icon16;
    
    private static Sprite defaultSprite;
    private static Graphic defaultGraphic;
    
    private static Color lightGrayColor;
    private static Color transparentWhiteColor;
    private static Color transparentYellowColor;
    
    public static void preLoad1() throws IOException {
    	String resPath = "com/elezeta/roguecave/gui/content/";
        setIcon32(loadIcon(resPath+"icon32.png"));
        setIcon16(loadIcon(resPath+"icon16.png"));
    }

    private static String splashText;
    
	@SuppressWarnings("deprecation")
	private static void splashScreen() {
		int width = Display.getWidth();
		int height = Display.getHeight();
		float heightf = (float)height;
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0,width,height,0, 1.5, -1.5);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		glDisable(GL_DEPTH_TEST);
		glViewport(0, 0, width, height);
		
		menuFont.drawString(width/2-menuFont.getWidth(splashText)/2,height/2-menuFont.getHeight(splashText)/2,splashText,Color.white);

		glColor4f(1f,1f,1f,1f);

		Graphic g = getGraphics().get(GraphicID.GUILogo);
		g.getTexture().bind();
		glBegin(GL_QUADS);
		glTexCoord2f(g.getX1(), g.getY2());
		float x = (float)((int)(width/2f));
		float y = heightf/2f+180f+15f;
		glVertex3f(x - g.getXStanding(),
				heightf - y - g.getYStanding() + g.getHeight(), 0);
		glTexCoord2f(g.getX1(), g.getY1());
		glVertex3f(x - g.getXStanding(), heightf - y - g.getYStanding(), 0);
		glTexCoord2f(g.getX2(), g.getY1());
		glVertex3f(x - g.getXStanding() + g.getWidth(),
				heightf - y - g.getYStanding(), 0);
		glTexCoord2f(g.getX2(), g.getY2());
		glVertex3f(x - g.getXStanding() + g.getWidth(),
				heightf - y - g.getYStanding() + g.getHeight(), 0);
		glEnd();
				
		Display.update();
		
	}

    public static void load(String text) throws FontFormatException, IOException{

    	splashText = text;
		String resPath = "com/elezeta/roguecave/gui/content/";

		setMenuFont(loadFont(resPath+"LucidaTypewriterRegular.ttf",false,21));
        setGraphics(new HashMap<GraphicID,Graphic>());

        org.newdawn.slick.opengl.Texture logoTextureAtlas = loadTexturePre(resPath+"logo.png");
        graphics.put(GraphicID.GUILogo,generateGraphic(logoTextureAtlas,0,0,512,128,184,43));
        splashScreen();

		setTransparentWhiteColor(new Color(1f,1f,1f,0.9f));
		setTransparentYellowColor(new Color(1f,1f,0f,0.5f));
		setLightGrayColor(new Color(0.7f,0.7f,0.7f,1f));
		
		setSmallFont(loadFont(resPath+"LucidaTypewriterRegular.ttf",false,10));
        setInfoFont(loadFont(resPath+"LucidaTypewriterRegular.ttf",false,12));
        setBoldFont(loadFont(resPath+"LucidaTypewriterBold.ttf",false,12));
        setBigFont(loadFont(resPath+"LucidaTypewriterBold.ttf",false,14));
        
        org.newdawn.slick.opengl.Texture terrainTextureAtlas = loadTexture(resPath+"terrain.png");
        org.newdawn.slick.opengl.Texture wallTextureAtlas = loadTexture(resPath+"walls.png");
        org.newdawn.slick.opengl.Texture guiTextureAtlas = loadTexture(resPath+"gui.png");
        org.newdawn.slick.opengl.Texture attackTextureAtlas = loadTexture(resPath+"attacks.png");
        org.newdawn.slick.opengl.Texture mobRabbitTextureAtlas = loadTexture(resPath+"mobrabbit.png");
        org.newdawn.slick.opengl.Texture meleeWeaponTextureAtlas = loadTexture(resPath+"meleeweapons.png");
        org.newdawn.slick.opengl.Texture spellTextureAtlas = loadTexture(resPath+"spells.png");
        org.newdawn.slick.opengl.Texture upgradesTextureAtlas = loadTexture(resPath+"upgrades.png");
        org.newdawn.slick.opengl.Texture armorTextureAtlas = loadTexture(resPath+"armor.png");
        org.newdawn.slick.opengl.Texture shieldTextureAtlas = loadTexture(resPath+"shields.png");
        org.newdawn.slick.opengl.Texture rangedWeaponTextureAtlas = loadTexture(resPath+"rangedweapons.png");
        org.newdawn.slick.opengl.Texture fireTextureAtlas = loadTexture(resPath+"fire.png");
        org.newdawn.slick.opengl.Texture waterTextureAtlas = loadTexture(resPath+"water.png");
        org.newdawn.slick.opengl.Texture potionTextureAtlas = loadTexture(resPath+"potions.png");
        org.newdawn.slick.opengl.Texture shadowTextureAtlas = loadTexture(resPath+"shadow.png");
        org.newdawn.slick.opengl.Texture pickupsTextureAtlas = loadTexture(resPath+"pickups.png");
        org.newdawn.slick.opengl.Texture miscTextureAtlas = loadTexture(resPath+"misc.png");

        setSprites(new HashMap<SpriteID,Sprite>());
        
        setDefaultGraphic(generateGraphicFix(guiTextureAtlas,0,64,32,32,16,16));
        setDefaultSprite(new Sprite(getDefaultGraphic()));
        
        sprites.put(SpriteID.naught,new Sprite(generateGraphic(guiTextureAtlas,0,64,32,32,16,16)));

        graphics.put(GraphicID.shadow,generateGraphic(shadowTextureAtlas,0,0,256,256,128,128));
        
        sprites.put(SpriteID.humanbody1,loadHumanSprite(resPath+"human/body1.png"));
        sprites.put(SpriteID.humanbody2,loadHumanSprite(resPath+"human/body2.png"));
        sprites.put(SpriteID.humanbody3,loadHumanSprite(resPath+"human/body3.png"));
        sprites.put(SpriteID.humanbody4,loadHumanSprite(resPath+"human/body4.png"));
        sprites.put(SpriteID.humanbody5,loadHumanSprite(resPath+"human/body5.png"));
        sprites.put(SpriteID.humanbody6,loadHumanSprite(resPath+"human/body6.png"));
        sprites.put(SpriteID.humanbody7,loadHumanSprite(resPath+"human/body7.png"));
        sprites.put(SpriteID.humanbody8,loadHumanSprite(resPath+"human/body8.png"));
        sprites.put(SpriteID.humanbody9,loadHumanSprite(resPath+"human/body9.png"));
        sprites.put(SpriteID.humanshortsfemale,loadHumanSprite(resPath+"human/shortsfemale.png"));
        sprites.put(SpriteID.humanshortsmale,loadHumanSprite(resPath+"human/shortsmale.png"));
        sprites.put(SpriteID.humantopfemale,loadHumanSprite(resPath+"human/topfemale.png"));
        sprites.put(SpriteID.humanbeardlong1,loadHumanSprite(resPath+"human/beardlong1.png"));
        sprites.put(SpriteID.humanbeardlong2,loadHumanSprite(resPath+"human/beardlong2.png"));
        sprites.put(SpriteID.humanbeardlong3,loadHumanSprite(resPath+"human/beardlong3.png"));
        sprites.put(SpriteID.humanbeardshort1,loadHumanSprite(resPath+"human/beardshort1.png"));
        sprites.put(SpriteID.humanbeardshort2,loadHumanSprite(resPath+"human/beardshort2.png"));
        sprites.put(SpriteID.humanbeardshort3,loadHumanSprite(resPath+"human/beardshort3.png"));
        sprites.put(SpriteID.humanbeardshort4,loadHumanSprite(resPath+"human/beardshort4.png"));
        sprites.put(SpriteID.humanbeardshort5,loadHumanSprite(resPath+"human/beardshort5.png"));
        sprites.put(SpriteID.humanbeardshort6,loadHumanSprite(resPath+"human/beardshort6.png"));
        sprites.put(SpriteID.humaneyebrows1,loadHumanSprite(resPath+"human/eyebrows1.png"));
        sprites.put(SpriteID.humaneyebrows2,loadHumanSprite(resPath+"human/eyebrows2.png"));
        sprites.put(SpriteID.humaneyebrows3,loadHumanSprite(resPath+"human/eyebrows3.png"));
        sprites.put(SpriteID.humaneyecolor1,loadHumanSprite(resPath+"human/eyecolor1.png"));
        sprites.put(SpriteID.humaneyecolor2,loadHumanSprite(resPath+"human/eyecolor2.png"));
        sprites.put(SpriteID.humaneyecolor3,loadHumanSprite(resPath+"human/eyecolor3.png"));
        sprites.put(SpriteID.humaneyecolor4,loadHumanSprite(resPath+"human/eyecolor4.png"));
        sprites.put(SpriteID.humaneyecolor5,loadHumanSprite(resPath+"human/eyecolor5.png"));
        sprites.put(SpriteID.humaneyecolor6,loadHumanSprite(resPath+"human/eyecolor6.png"));
        sprites.put(SpriteID.humaneyecolor7,loadHumanSprite(resPath+"human/eyecolor7.png"));
        sprites.put(SpriteID.humaneyeskin1,loadHumanSprite(resPath+"human/eyeskin1.png"));
        sprites.put(SpriteID.humaneyeskin2,loadHumanSprite(resPath+"human/eyeskin2.png"));
        sprites.put(SpriteID.humaneyeskin3,loadHumanSprite(resPath+"human/eyeskin3.png"));
        sprites.put(SpriteID.humaneyewhite1,loadHumanSprite(resPath+"human/eyewhite1.png"));
        sprites.put(SpriteID.humaneyewhite2,loadHumanSprite(resPath+"human/eyewhite2.png"));
        sprites.put(SpriteID.humaneyewhite3,loadHumanSprite(resPath+"human/eyewhite3.png"));
        sprites.put(SpriteID.humaneyewhite4,loadHumanSprite(resPath+"human/eyewhite4.png"));
        sprites.put(SpriteID.humanhairlong1,loadHumanSprite(resPath+"human/hairlong1.png"));
        sprites.put(SpriteID.humanhairlong2,loadHumanSprite(resPath+"human/hairlong2.png"));
        sprites.put(SpriteID.humanhairlong3,loadHumanSprite(resPath+"human/hairlong3.png"));
        sprites.put(SpriteID.humanhairlong4,loadHumanSprite(resPath+"human/hairlong4.png"));
        sprites.put(SpriteID.humanhairdropback1,loadHumanSprite(resPath+"human/hairdropback1.png"));
        sprites.put(SpriteID.humanhairdropback2,loadHumanSprite(resPath+"human/hairdropback2.png"));
        sprites.put(SpriteID.humanhairdropfront1,loadHumanSprite(resPath+"human/hairdropfront1.png"));
        sprites.put(SpriteID.humanhairdropfront2,loadHumanSprite(resPath+"human/hairdropfront2.png"));
        sprites.put(SpriteID.humanhairshort1,loadHumanSprite(resPath+"human/hairshort1.png"));
        sprites.put(SpriteID.humanhairshort2,loadHumanSprite(resPath+"human/hairshort2.png"));
        sprites.put(SpriteID.humanhairshort3,loadHumanSprite(resPath+"human/hairshort3.png"));
        sprites.put(SpriteID.humanhairshort4,loadHumanSprite(resPath+"human/hairshort4.png"));
        sprites.put(SpriteID.humanhairshort5,loadHumanSprite(resPath+"human/hairshort5.png"));
        sprites.put(SpriteID.humanhairshort6,loadHumanSprite(resPath+"human/hairshort6.png"));
        sprites.put(SpriteID.humanhairshort7,loadHumanSprite(resPath+"human/hairshort7.png"));
        sprites.put(SpriteID.humanarmor1,loadHumanSprite(resPath+"human/armor1.png"));
        sprites.put(SpriteID.humanarmsback1,loadHumanSprite(resPath+"human/armsback1.png"));
        sprites.put(SpriteID.humanarmsfront1,loadHumanSprite(resPath+"human/armsfront1.png"));
        sprites.put(SpriteID.humanboots1,loadHumanSprite(resPath+"human/boots1.png"));
        sprites.put(SpriteID.humanhelmetfull1,loadHumanSprite(resPath+"human/helmetfull1.png"));
        sprites.put(SpriteID.humanhelmethalf1,loadHumanSprite(resPath+"human/helmethalf1.png"));
        sprites.put(SpriteID.humanhelmethalf2,loadHumanSprite(resPath+"human/helmethalf2.png"));
        sprites.put(SpriteID.humanlegs1,loadHumanSprite(resPath+"human/legs1.png"));
        sprites.put(SpriteID.humanamulet1,loadHumanSprite(resPath+"human/amulet1.png"));
        sprites.put(SpriteID.humanringleft1,loadHumanSprite(resPath+"human/ringleft1.png"));
        sprites.put(SpriteID.humanringright1,loadHumanSprite(resPath+"human/ringright1.png"));

        sprites.put(SpriteID.humanarrowsfront1,loadHumanSprite(resPath+"human/arrowsfront1.png"));
        sprites.put(SpriteID.humanarrowsback1,loadHumanSprite(resPath+"human/arrowsback1.png"));

        sprites.put(SpriteID.humanshieldfront1,loadHumanSprite(resPath+"human/shieldfront1.png"));
        sprites.put(SpriteID.humanshieldback1,loadHumanSprite(resPath+"human/shieldback1.png"));

        sprites.put(SpriteID.humanbowfront1,loadHumanSprite(resPath+"human/bowfront1.png"));
        sprites.put(SpriteID.humanbowback1,loadHumanSprite(resPath+"human/bowback1.png"));

        sprites.put(SpriteID.humandaggerfront1,loadHumanSprite(resPath+"human/daggerfront1.png"));
        sprites.put(SpriteID.humandaggerback1,loadHumanSprite(resPath+"human/daggerback1.png"));

        sprites.put(SpriteID.humanswordfront1,loadHumanSprite(resPath+"human/swordfront1.png"));
        sprites.put(SpriteID.humanswordback1,loadHumanSprite(resPath+"human/swordback1.png"));

        sprites.put(SpriteID.humanwandfront1,loadHumanSprite(resPath+"human/wandfront1.png"));
        sprites.put(SpriteID.humanwandback1,loadHumanSprite(resPath+"human/wandback1.png"));

        sprites.put(SpriteID.humanmacefront1,loadHumanSprite(resPath+"human/macefront1.png"));
        sprites.put(SpriteID.humanmaceback1,loadHumanSprite(resPath+"human/maceback1.png"));

        sprites.put(SpriteID.humanaxefront1,loadHumanSprite(resPath+"human/axefront1.png"));
        sprites.put(SpriteID.humanaxeback1,loadHumanSprite(resPath+"human/axeback1.png"));

        sprites.put(SpriteID.mobRabbit,new Sprite(new Graphic[] { // N
                generateGraphic(mobRabbitTextureAtlas,0,192,64,64,32,32),
                generateGraphic(mobRabbitTextureAtlas,64,192,64,64,32,32),
                generateGraphic(mobRabbitTextureAtlas,128,192,64,64,32,32),
                generateGraphic(mobRabbitTextureAtlas,192,192,64,64,32,32),
            },new Graphic[] { // E
                generateGraphic(mobRabbitTextureAtlas,0,128,64,64,32,32),
                generateGraphic(mobRabbitTextureAtlas,64,128,64,64,32,32),
                generateGraphic(mobRabbitTextureAtlas,128,128,64,64,32,32),
                generateGraphic(mobRabbitTextureAtlas,192,128,64,64,32,32),
            },new Graphic[] { // S
                generateGraphic(mobRabbitTextureAtlas,0,0,64,64,32,32),
                generateGraphic(mobRabbitTextureAtlas,64,0,64,64,32,32),
                generateGraphic(mobRabbitTextureAtlas,128,0,64,64,32,32),
                generateGraphic(mobRabbitTextureAtlas,192,0,64,64,32,32),
            },new Graphic[] { // W
                generateGraphic(mobRabbitTextureAtlas,0,64,64,64,32,32),
                generateGraphic(mobRabbitTextureAtlas,64,64,64,64,32,32),
                generateGraphic(mobRabbitTextureAtlas,128,64,64,64,32,32),
                generateGraphic(mobRabbitTextureAtlas,192,64,64,64,32,32),
        	}));

        sprites.put(SpriteID.key,new Sprite(generateGraphic(miscTextureAtlas,0,0,32,32,16,16)));

        sprites.put(SpriteID.rangedWeapon1,new Sprite(generateGraphic(rangedWeaponTextureAtlas,0,0,32,32,15,26)));
        sprites.put(SpriteID.rangedAmmo1,new Sprite(generateGraphic(rangedWeaponTextureAtlas,0,32,32,32,17,22)));
        
        sprites.put(SpriteID.potion1,new Sprite(generateGraphic(potionTextureAtlas,0,0,32,32,16,27)));

        sprites.put(SpriteID.amulet1,new Sprite(generateGraphic(armorTextureAtlas,0,0,32,32,16,25)));
        sprites.put(SpriteID.helmet1,new Sprite(generateGraphic(armorTextureAtlas,32,0,32,32,16,26)));
        sprites.put(SpriteID.armor1,new Sprite(generateGraphic(armorTextureAtlas,64,0,32,32,17,25)));
        sprites.put(SpriteID.legs1,new Sprite(generateGraphic(armorTextureAtlas,96,0,32,32,16,25)));
        sprites.put(SpriteID.boots1,new Sprite(generateGraphic(armorTextureAtlas,128,0,32,32,16,23)));
        sprites.put(SpriteID.arms1,new Sprite(generateGraphic(armorTextureAtlas,160,0,32,32,16,21)));
        sprites.put(SpriteID.ring1,new Sprite(generateGraphic(armorTextureAtlas,192,0,32,32,16,20)));
        sprites.put(SpriteID.ring2,new Sprite(generateGraphic(armorTextureAtlas,192,32,32,32,16,20)));

        sprites.put(SpriteID.spell1,new Sprite(generateGraphic(spellTextureAtlas,0,0,32,32,16,16)));
        sprites.put(SpriteID.spell2,new Sprite(generateGraphic(spellTextureAtlas,32,0,32,32,16,16)));

        sprites.put(SpriteID.upgradehuman,new Sprite(generateGraphic(upgradesTextureAtlas,0,0,32,32,16,16)));
        sprites.put(SpriteID.upgradebody,new Sprite(generateGraphic(upgradesTextureAtlas,32,0,32,32,16,16)));
        sprites.put(SpriteID.upgrademaxhp,new Sprite(generateGraphic(upgradesTextureAtlas,64,0,32,32,16,16)));

        sprites.put(SpriteID.pickuparmor1,new Sprite(generateGraphic(pickupsTextureAtlas,0,0,32,32,17,30)));
        sprites.put(SpriteID.pickuplegs1,new Sprite(generateGraphic(pickupsTextureAtlas,32,0,32,32,16,30)));
        sprites.put(SpriteID.pickupboots1,new Sprite(generateGraphic(pickupsTextureAtlas,64,0,32,32,16,30)));
        sprites.put(SpriteID.pickuparms1,new Sprite(generateGraphic(pickupsTextureAtlas,96,0,32,32,16,30)));
        sprites.put(SpriteID.pickupamulet1,new Sprite(generateGraphic(pickupsTextureAtlas,128,0,32,32,13,30)));
        sprites.put(SpriteID.pickuphelmet1,new Sprite(generateGraphic(pickupsTextureAtlas,160,0,32,32,16,29)));
        sprites.put(SpriteID.pickuphelmet2,new Sprite(generateGraphic(pickupsTextureAtlas,192,0,32,32,16,29)));
        sprites.put(SpriteID.pickupring1,new Sprite(generateGraphic(pickupsTextureAtlas,224,0,32,32,16,30)));

        sprites.put(SpriteID.pickupbow1,new Sprite(generateGraphic(pickupsTextureAtlas,0,32,32,32,16,30)));
        sprites.put(SpriteID.pickuparrow1,new Sprite(generateGraphic(pickupsTextureAtlas,32,32,32,32,16,28)));
        sprites.put(SpriteID.pickupmace1,new Sprite(generateGraphic(pickupsTextureAtlas,64,32,32,32,16,30)));
        sprites.put(SpriteID.pickupwand1,new Sprite(generateGraphic(pickupsTextureAtlas,96,32,32,32,16,30)));
        sprites.put(SpriteID.pickupaxe1,new Sprite(generateGraphic(pickupsTextureAtlas,128,32,32,32,16,30)));
        sprites.put(SpriteID.pickupsword1,new Sprite(generateGraphic(pickupsTextureAtlas,160,32,32,32,16,30)));
        sprites.put(SpriteID.pickupshield1,new Sprite(generateGraphic(pickupsTextureAtlas,192,32,32,32,16,30)));
        sprites.put(SpriteID.pickupdagger1,new Sprite(generateGraphic(pickupsTextureAtlas,224,32,32,32,16,30)));

        sprites.put(SpriteID.pickupkey1,new Sprite(generateGraphic(pickupsTextureAtlas,0,64,32,32,16,30)));
        sprites.put(SpriteID.pickuppotion1,new Sprite(generateGraphic(pickupsTextureAtlas,32,64,32,32,16,30)));

        sprites.put(SpriteID.meleeWeapon1,new Sprite(generateGraphic(meleeWeaponTextureAtlas,0,0,32,32,16,26)));
        sprites.put(SpriteID.meleeWeapon2,new Sprite(generateGraphic(meleeWeaponTextureAtlas,32,0,32,32,16,26)));
        sprites.put(SpriteID.meleeWeapon3,new Sprite(generateGraphic(meleeWeaponTextureAtlas,64,0,32,32,16,26)));
        sprites.put(SpriteID.meleeWeapon4,new Sprite(generateGraphic(meleeWeaponTextureAtlas,96,0,32,32,16,29)));
        sprites.put(SpriteID.meleeWeapon5,new Sprite(generateGraphic(meleeWeaponTextureAtlas,128,0,32,32,16,29)));
        sprites.put(SpriteID.meleeWeapon6,new Sprite(generateGraphic(meleeWeaponTextureAtlas,160,0,32,32,16,25)));
        sprites.put(SpriteID.meleeWeapon7,new Sprite(generateGraphic(meleeWeaponTextureAtlas,192,0,32,32,16,29)));
        sprites.put(SpriteID.meleeWeapon8,new Sprite(generateGraphic(meleeWeaponTextureAtlas,224,0,32,32,16,25)));

        sprites.put(SpriteID.meleeWeapon9,new Sprite(generateGraphic(meleeWeaponTextureAtlas,0,32,32,32,16,29)));
        sprites.put(SpriteID.meleeWeapon10,new Sprite(generateGraphic(meleeWeaponTextureAtlas,32,32,32,32,16,29)));
        sprites.put(SpriteID.meleeWeapon11,new Sprite(generateGraphic(meleeWeaponTextureAtlas,64,32,32,32,16,30)));
        sprites.put(SpriteID.meleeWeapon12,new Sprite(generateGraphic(meleeWeaponTextureAtlas,96,32,32,32,16,29)));
        sprites.put(SpriteID.meleeWeapon13,new Sprite(generateGraphic(meleeWeaponTextureAtlas,128,32,32,32,16,30)));
        sprites.put(SpriteID.meleeWeapon14,new Sprite(generateGraphic(meleeWeaponTextureAtlas,160,32,32,32,16,30)));
        sprites.put(SpriteID.meleeWeapon15,new Sprite(generateGraphic(meleeWeaponTextureAtlas,192,32,32,32,16,30)));
        sprites.put(SpriteID.meleeWeapon16,new Sprite(generateGraphic(meleeWeaponTextureAtlas,224,32,32,32,16,29)));

        sprites.put(SpriteID.meleeWeapon17,new Sprite(generateGraphic(meleeWeaponTextureAtlas,0,64,32,32,16,29)));
        sprites.put(SpriteID.meleeWeapon18,new Sprite(generateGraphic(meleeWeaponTextureAtlas,32,64,32,32,16,27)));
        sprites.put(SpriteID.meleeWeapon19,new Sprite(generateGraphic(meleeWeaponTextureAtlas,64,64,32,32,16,29)));
        sprites.put(SpriteID.meleeWeapon20,new Sprite(generateGraphic(meleeWeaponTextureAtlas,96,64,32,32,16,29)));
        sprites.put(SpriteID.meleeWeapon21,new Sprite(generateGraphic(meleeWeaponTextureAtlas,128,64,32,32,16,29)));
        sprites.put(SpriteID.meleeWeapon22,new Sprite(generateGraphic(meleeWeaponTextureAtlas,160,64,32,32,16,28)));
        sprites.put(SpriteID.meleeWeapon23,new Sprite(generateGraphic(meleeWeaponTextureAtlas,192,64,32,32,16,28)));
        sprites.put(SpriteID.meleeWeapon24,new Sprite(generateGraphic(meleeWeaponTextureAtlas,224,64,32,32,16,26)));

        sprites.put(SpriteID.meleeWeapon25,new Sprite(generateGraphic(meleeWeaponTextureAtlas,0,96,32,32,16,25)));
        sprites.put(SpriteID.meleeWeapon26,new Sprite(generateGraphic(meleeWeaponTextureAtlas,32,96,32,32,16,26)));
        sprites.put(SpriteID.meleeWeapon27,new Sprite(generateGraphic(meleeWeaponTextureAtlas,64,96,32,32,16,29)));
        sprites.put(SpriteID.meleeWeapon28,new Sprite(generateGraphic(meleeWeaponTextureAtlas,96,96,32,32,16,26)));
        sprites.put(SpriteID.meleeWeapon29,new Sprite(generateGraphic(meleeWeaponTextureAtlas,128,96,32,32,16,30)));
        sprites.put(SpriteID.meleeWeapon30,new Sprite(generateGraphic(meleeWeaponTextureAtlas,160,96,32,32,16,30)));
        sprites.put(SpriteID.meleeWeapon31,new Sprite(generateGraphic(meleeWeaponTextureAtlas,192,96,32,32,16,30)));
        sprites.put(SpriteID.meleeWeapon32,new Sprite(generateGraphic(meleeWeaponTextureAtlas,224,96,32,32,16,29)));

        sprites.put(SpriteID.meleeWeapon33,new Sprite(generateGraphic(meleeWeaponTextureAtlas,0,128,32,32,16,29)));
        sprites.put(SpriteID.meleeWeapon34,new Sprite(generateGraphic(meleeWeaponTextureAtlas,32,128,32,32,16,29)));
        sprites.put(SpriteID.meleeWeapon35,new Sprite(generateGraphic(meleeWeaponTextureAtlas,64,128,32,32,16,29)));
        sprites.put(SpriteID.meleeWeapon36,new Sprite(generateGraphic(meleeWeaponTextureAtlas,96,128,32,32,16,28)));
        sprites.put(SpriteID.meleeWeapon37,new Sprite(generateGraphic(meleeWeaponTextureAtlas,128,128,32,32,16,28)));
        sprites.put(SpriteID.meleeWeapon38,new Sprite(generateGraphic(meleeWeaponTextureAtlas,160,128,32,32,16,29)));


        sprites.put(SpriteID.shield1,new Sprite(generateGraphic(shieldTextureAtlas,0,0,32,32,16,27)));
        sprites.put(SpriteID.shield2,new Sprite(generateGraphic(shieldTextureAtlas,32,0,32,32,16,27)));
        sprites.put(SpriteID.shield3,new Sprite(generateGraphic(shieldTextureAtlas,64,0,32,32,16,27)));
        sprites.put(SpriteID.shield4,new Sprite(generateGraphic(shieldTextureAtlas,96,0,32,32,16,27)));
        sprites.put(SpriteID.shield5,new Sprite(generateGraphic(shieldTextureAtlas,128,0,32,32,16,27)));
        sprites.put(SpriteID.shield6,new Sprite(generateGraphic(shieldTextureAtlas,160,0,32,32,16,27)));
        sprites.put(SpriteID.shield7,new Sprite(generateGraphic(shieldTextureAtlas,192,0,32,32,16,27)));
        sprites.put(SpriteID.shield8,new Sprite(generateGraphic(shieldTextureAtlas,224,0,32,32,16,27)));

        sprites.put(SpriteID.shield9,new Sprite(generateGraphic(shieldTextureAtlas,0,32,32,32,16,27)));
        sprites.put(SpriteID.shield10,new Sprite(generateGraphic(shieldTextureAtlas,32,32,32,32,16,27)));
        sprites.put(SpriteID.shield11,new Sprite(generateGraphic(shieldTextureAtlas,64,32,32,32,16,27)));
        sprites.put(SpriteID.shield12,new Sprite(generateGraphic(shieldTextureAtlas,96,32,32,32,16,27)));
        sprites.put(SpriteID.shield13,new Sprite(generateGraphic(shieldTextureAtlas,128,32,32,32,16,27)));
        sprites.put(SpriteID.shield14,new Sprite(generateGraphic(shieldTextureAtlas,160,32,32,32,16,27)));
        
        
        sprites.put(SpriteID.voidTile,new Sprite(generateGraphicFix(terrainTextureAtlas,64,0,64,64,0,0)));
        sprites.put(SpriteID.stoneTile,new Sprite(generateGraphicFix(terrainTextureAtlas,0,0,64,64,0,0)));
        sprites.put(SpriteID.stoneWall,new Sprite(generateGraphicFix(wallTextureAtlas,0,0,64,64,0,0)));

        sprites.put(SpriteID.flyingArrow1,new Sprite(new Graphic[] { // E
                generateGraphic(attackTextureAtlas,0,16,16,16,16,10),
            },false,true,10f));
        sprites.put(SpriteID.flyingSlash1,new Sprite(new Graphic[] { // E
                generateGraphic(attackTextureAtlas,16,16,16,16,16,10),
            },false,true,10f));
        sprites.put(SpriteID.flyingHit1,new Sprite(new Graphic[] { // E
                generateGraphic(attackTextureAtlas,32,16,16,16,16,10),
            },false,true,10f));

        sprites.put(SpriteID.attack1,new Sprite(generateGraphic(attackTextureAtlas,0,0,16,16,8,16)));
        sprites.put(SpriteID.attack2,new Sprite(generateGraphic(attackTextureAtlas,16,0,16,16,8,16)));
        sprites.put(SpriteID.attack3,new Sprite(generateGraphic(attackTextureAtlas,32,0,16,16,8,16)));
        sprites.put(SpriteID.attack4,new Sprite(generateGraphic(attackTextureAtlas,48,0,16,16,8,16)));
        sprites.put(SpriteID.attack5,new Sprite(generateGraphic(attackTextureAtlas,64,0,16,16,8,16)));
        sprites.put(SpriteID.attack6,new Sprite(generateGraphic(attackTextureAtlas,80,0,16,16,8,16)));

        sprites.put(SpriteID.fire,new Sprite(new Graphic[] {
                generateGraphic(fireTextureAtlas,0,0,16,16,8,16),
                generateGraphic(fireTextureAtlas,16,0,16,16,8,16),
                generateGraphic(fireTextureAtlas,32,0,16,16,8,16),
                generateGraphic(fireTextureAtlas,48,0,16,16,8,16),
                generateGraphic(fireTextureAtlas,64,0,16,16,8,16),
                generateGraphic(fireTextureAtlas,80,0,16,16,8,16),
                generateGraphic(fireTextureAtlas,96,0,16,16,8,16),
                generateGraphic(fireTextureAtlas,112,0,16,16,8,16),

                generateGraphic(fireTextureAtlas,0,16,16,16,8,16),
                generateGraphic(fireTextureAtlas,16,16,16,16,8,16),
                generateGraphic(fireTextureAtlas,32,16,16,16,8,16),
                generateGraphic(fireTextureAtlas,48,16,16,16,8,16),
                generateGraphic(fireTextureAtlas,64,16,16,16,8,16),
                generateGraphic(fireTextureAtlas,80,16,16,16,8,16),
                generateGraphic(fireTextureAtlas,96,16,16,16,8,16),
                generateGraphic(fireTextureAtlas,112,16,16,16,8,16),

                generateGraphic(fireTextureAtlas,0,32,16,16,8,16),
                generateGraphic(fireTextureAtlas,16,32,16,16,8,16),
                generateGraphic(fireTextureAtlas,32,32,16,16,8,16),
                generateGraphic(fireTextureAtlas,48,32,16,16,8,16),
                generateGraphic(fireTextureAtlas,64,32,16,16,8,16),
                generateGraphic(fireTextureAtlas,80,32,16,16,8,16),
                generateGraphic(fireTextureAtlas,96,32,16,16,8,16),
                generateGraphic(fireTextureAtlas,112,32,16,16,8,16),
                
                generateGraphic(fireTextureAtlas,0,48,16,16,8,16),
                generateGraphic(fireTextureAtlas,16,48,16,16,8,16),
                generateGraphic(fireTextureAtlas,32,48,16,16,8,16),
                generateGraphic(fireTextureAtlas,48,48,16,16,8,16),
                generateGraphic(fireTextureAtlas,64,48,16,16,8,16),
                generateGraphic(fireTextureAtlas,80,48,16,16,8,16),
                generateGraphic(fireTextureAtlas,96,48,16,16,8,16),
                generateGraphic(fireTextureAtlas,112,48,16,16,8,16),
                
                generateGraphic(fireTextureAtlas,0,64,16,16,8,16),
                generateGraphic(fireTextureAtlas,16,64,16,16,8,16),
                generateGraphic(fireTextureAtlas,32,64,16,16,8,16),
                generateGraphic(fireTextureAtlas,48,64,16,16,8,16),
                generateGraphic(fireTextureAtlas,64,64,16,16,8,16),
                generateGraphic(fireTextureAtlas,80,64,16,16,8,16),
                generateGraphic(fireTextureAtlas,96,64,16,16,8,16),
                generateGraphic(fireTextureAtlas,112,64,16,16,8,16),
        },true));

        sprites.put(SpriteID.water,new Sprite(new Graphic[] {
                generateGraphic(waterTextureAtlas,0,0,16,16,8,16),
                generateGraphic(waterTextureAtlas,16,0,16,16,8,16),
                generateGraphic(waterTextureAtlas,32,0,16,16,8,16),
                generateGraphic(waterTextureAtlas,48,0,16,16,8,16),
                generateGraphic(waterTextureAtlas,64,0,16,16,8,16),
                generateGraphic(waterTextureAtlas,80,0,16,16,8,16),
                generateGraphic(waterTextureAtlas,96,0,16,16,8,16),
                generateGraphic(waterTextureAtlas,112,0,16,16,8,16),

                generateGraphic(waterTextureAtlas,0,16,16,16,8,16),
                generateGraphic(waterTextureAtlas,16,16,16,16,8,16),
                generateGraphic(waterTextureAtlas,32,16,16,16,8,16),
                generateGraphic(waterTextureAtlas,48,16,16,16,8,16),
                generateGraphic(waterTextureAtlas,64,16,16,16,8,16),
                generateGraphic(waterTextureAtlas,80,16,16,16,8,16),
                generateGraphic(waterTextureAtlas,96,16,16,16,8,16),
                generateGraphic(waterTextureAtlas,112,16,16,16,8,16),

                generateGraphic(waterTextureAtlas,0,32,16,16,8,16),
                generateGraphic(waterTextureAtlas,16,32,16,16,8,16),
                generateGraphic(waterTextureAtlas,32,32,16,16,8,16),
                generateGraphic(waterTextureAtlas,48,32,16,16,8,16),
                generateGraphic(waterTextureAtlas,64,32,16,16,8,16),
                generateGraphic(waterTextureAtlas,80,32,16,16,8,16),
                generateGraphic(waterTextureAtlas,96,32,16,16,8,16),
                generateGraphic(waterTextureAtlas,112,32,16,16,8,16),
                
                generateGraphic(waterTextureAtlas,0,48,16,16,8,16),
                generateGraphic(waterTextureAtlas,16,48,16,16,8,16),
                generateGraphic(waterTextureAtlas,32,48,16,16,8,16),
                generateGraphic(waterTextureAtlas,48,48,16,16,8,16),
                generateGraphic(waterTextureAtlas,64,48,16,16,8,16),
                generateGraphic(waterTextureAtlas,80,48,16,16,8,16),
                generateGraphic(waterTextureAtlas,96,48,16,16,8,16),
                generateGraphic(waterTextureAtlas,112,48,16,16,8,16),
                
                generateGraphic(waterTextureAtlas,0,64,16,16,8,16),
                generateGraphic(waterTextureAtlas,16,64,16,16,8,16),
                generateGraphic(waterTextureAtlas,32,64,16,16,8,16),
                generateGraphic(waterTextureAtlas,48,64,16,16,8,16),
                generateGraphic(waterTextureAtlas,64,64,16,16,8,16),
                generateGraphic(waterTextureAtlas,80,64,16,16,8,16),
                generateGraphic(waterTextureAtlas,96,64,16,16,8,16),
                generateGraphic(waterTextureAtlas,112,64,16,16,8,16),
        },true));

        graphics.put(GraphicID.GUIPointer,generateGraphic(guiTextureAtlas,0,0,32,32,11,7));
        graphics.put(GraphicID.GUICrosshairFull,generateGraphic(guiTextureAtlas,0,32,32,32,16,16));
        graphics.put(GraphicID.GUICrosshairMid,generateGraphic(guiTextureAtlas,0,64,32,32,16,16));
        graphics.put(GraphicID.GUICrosshairOff,generateGraphic(guiTextureAtlas,0,96,32,32,16,16));
        graphics.put(GraphicID.GUIStatusBackground,generateGraphic(guiTextureAtlas,32,0,128,256,0,0));
        graphics.put(GraphicID.GUIEquipmentBackground,generateGraphic(guiTextureAtlas,320,0,128,256,0,0));
        graphics.put(GraphicID.GUIPressedSlit,generateGraphic(guiTextureAtlas,160,0,64,64,0,0));
        graphics.put(GraphicID.GUISlit,generateGraphic(guiTextureAtlas,256,168,64,64,0,0));
        graphics.put(GraphicID.GUIButton,generateGraphic(guiTextureAtlas,160,168,64,64,0,0));
        graphics.put(GraphicID.GUIExpBar,generateGraphic(guiTextureAtlas,160,96,32,8,0,0));
        graphics.put(GraphicID.GUIBlackColor,generateGraphic(guiTextureAtlas,16,128,16,16,0,0));
        graphics.put(GraphicID.GUIGrayColor,generateGraphic(guiTextureAtlas,0,128,16,16,0,0));
        graphics.put(GraphicID.GUIRedColor,generateGraphic(guiTextureAtlas,0,144,16,16,0,0));
        graphics.put(GraphicID.GUIBlueColor,generateGraphic(guiTextureAtlas,16,144,16,16,0,0));
        graphics.put(GraphicID.GUIGreenColor,generateGraphic(guiTextureAtlas,480,16,16,16,0,0));
        graphics.put(GraphicID.GUIYellowColor,generateGraphic(guiTextureAtlas,496,16,16,16,0,0));
        graphics.put(GraphicID.GUIEmptyLeftHand,generateGraphic(guiTextureAtlas,0,160,32,32,16,16));
        graphics.put(GraphicID.GUIEmptyRightHand,generateGraphic(guiTextureAtlas,0,192,32,32,16,16));
        graphics.put(GraphicID.GUIEmptyLeftHandSelected,generateGraphic(guiTextureAtlas,224,160,32,32,16,16));
        graphics.put(GraphicID.GUIEmptyRightHandSelected,generateGraphic(guiTextureAtlas,224,192,32,32,16,16));
        graphics.put(GraphicID.GUIHpBar,generateGraphic(guiTextureAtlas,160,104,64,16,0,0));
        graphics.put(GraphicID.GUIMpBar,generateGraphic(guiTextureAtlas,160,120,64,16,0,0));
        graphics.put(GraphicID.GUIPostHpBar,generateGraphic(guiTextureAtlas,160,136,64,16,0,0));
        graphics.put(GraphicID.GUIPostMpBar,generateGraphic(guiTextureAtlas,160,152,64,16,0,0));
        graphics.put(GraphicID.GUIBackpack,generateGraphic(guiTextureAtlas,0,224,32,32,0,0));
        graphics.put(GraphicID.GUIBackpackOn,generateGraphic(guiTextureAtlas,0,256,32,32,0,0));
        graphics.put(GraphicID.GUIUpgradesOn1,generateGraphic(guiTextureAtlas,0,288,32,32,0,0));
        graphics.put(GraphicID.GUIUpgradesOn2,generateGraphic(guiTextureAtlas,0,320,32,32,0,0));
        graphics.put(GraphicID.GUIUpgradesOff,generateGraphic(guiTextureAtlas,0,352,32,32,0,0));
        graphics.put(GraphicID.GUITab,generateGraphic(guiTextureAtlas,32,256,64,64,0,0));
        graphics.put(GraphicID.GUITabSelected,generateGraphic(guiTextureAtlas,96,256,64,64,0,0));
        graphics.put(GraphicID.GUITabArmor,generateGraphic(guiTextureAtlas,32,320,64,64,0,0));
        graphics.put(GraphicID.GUITabWeapons,generateGraphic(guiTextureAtlas,96,320,64,64,0,0));
        graphics.put(GraphicID.GUITabSpells,generateGraphic(guiTextureAtlas,32,384,64,64,0,0));
        graphics.put(GraphicID.GUITabMisc,generateGraphic(guiTextureAtlas,96,384,64,64,0,0));
        graphics.put(GraphicID.GUITabBasic,generateGraphic(guiTextureAtlas,160,320,64,64,0,0));
        graphics.put(GraphicID.GUITabBody,generateGraphic(guiTextureAtlas,224,320,64,64,0,0));
        graphics.put(GraphicID.GUITrash,generateGraphic(guiTextureAtlas,0,384,32,64,0,0));
        graphics.put(GraphicID.GUIBigSlit,generateGraphic(guiTextureAtlas,224,0,128,64,0,0));
        graphics.put(GraphicID.GUIBigButton,generateGraphic(guiTextureAtlas,224,64,128,64,0,0));
        graphics.put(GraphicID.naught,generateGraphic(guiTextureAtlas,448,0,32,32,16,16));
        graphics.put(GraphicID.GUIEmptyHelmet,generateGraphic(guiTextureAtlas,448,32,32,32,16,16));
        graphics.put(GraphicID.GUIEmptyArmor,generateGraphic(guiTextureAtlas,448,64,32,32,16,16));
        graphics.put(GraphicID.GUIEmptyArms,generateGraphic(guiTextureAtlas,448,96,32,32,16,16));
        graphics.put(GraphicID.GUIEmptyLegs,generateGraphic(guiTextureAtlas,448,128,32,32,16,16));
        graphics.put(GraphicID.GUIEmptyBoots,generateGraphic(guiTextureAtlas,448,160,32,32,16,16));
        graphics.put(GraphicID.GUIEmptyRing,generateGraphic(guiTextureAtlas,448,192,32,32,16,16));
        graphics.put(GraphicID.GUIEmptyAmulet,generateGraphic(guiTextureAtlas,448,224,32,32,16,16));
        graphics.put(GraphicID.GUIMenuButton,generateGraphic(guiTextureAtlas,0,448,512,64,0,0));
        graphics.put(GraphicID.GUIDisabledMenuButton,generateGraphic(guiTextureAtlas,0,512,512,64,0,0));
        graphics.put(GraphicID.GUISelectedMenuButton,generateGraphic(guiTextureAtlas,0,576,512,64,0,0));
        graphics.put(GraphicID.GUIStatsBox,generateGraphic(guiTextureAtlas,0,640,256,256,0,0));
        graphics.put(GraphicID.GUILongStatsBox,generateGraphic(guiTextureAtlas,0,896,512,256,0,0));

        graphics.put(GraphicID.GUIScrollUp,generateGraphic(guiTextureAtlas,320,160,24,24,0,0));
        graphics.put(GraphicID.GUIScrollDown,generateGraphic(guiTextureAtlas,320,184,24,24,0,0));
        graphics.put(GraphicID.GUIScrollUpLight,generateGraphic(guiTextureAtlas,344,160,24,24,0,0));
        graphics.put(GraphicID.GUIScrollDownLight,generateGraphic(guiTextureAtlas,344,184,24,24,0,0));
        graphics.put(GraphicID.GUIScrollBackground,generateGraphic(guiTextureAtlas,368,160,24,256,0,0));
        graphics.put(GraphicID.GUIScrollBar,generateGraphic(guiTextureAtlas,392,160,24,256,0,0));
        graphics.put(GraphicID.GUIScrollBarLight,generateGraphic(guiTextureAtlas,416,160,24,256,0,0));
        graphics.put(GraphicID.GUIScrollTop,generateGraphic(guiTextureAtlas,344,208,24,1,0,0));
        graphics.put(GraphicID.GUIScrollBottom,generateGraphic(guiTextureAtlas,344,209,24,1,0,0));
        graphics.put(GraphicID.GUIScrollLineLight,generateGraphic(guiTextureAtlas,344,210,24,1,0,0));

        graphics.put(GraphicID.GUIPlayerHP,generateGraphic(guiTextureAtlas,480,32,16,16,0,0));
        graphics.put(GraphicID.GUIPlayerHPRegen,generateGraphic(guiTextureAtlas,480,64,16,16,0,0));
        graphics.put(GraphicID.GUIPlayerHPSteal,generateGraphic(guiTextureAtlas,480,48,16,16,0,0));
        graphics.put(GraphicID.GUIPlayerMP,generateGraphic(guiTextureAtlas,496,32,16,16,0,0));
        graphics.put(GraphicID.GUIPlayerMPRegen,generateGraphic(guiTextureAtlas,496,64,16,16,0,0));
        graphics.put(GraphicID.GUIPlayerMPSteal,generateGraphic(guiTextureAtlas,496,48,16,16,0,0));
        graphics.put(GraphicID.GUIPlayerAttack,generateGraphic(guiTextureAtlas,480,80,16,16,0,0));
        graphics.put(GraphicID.GUIPlayerDefense,generateGraphic(guiTextureAtlas,496,80,16,16,0,0));
        graphics.put(GraphicID.GUIPlayerRange,generateGraphic(guiTextureAtlas,480,96,16,16,0,0));
        graphics.put(GraphicID.GUIPlayerAccuracy,generateGraphic(guiTextureAtlas,496,96,16,16,0,0));
        graphics.put(GraphicID.GUIPlayerRecovery,generateGraphic(guiTextureAtlas,480,112,16,16,0,0));
        graphics.put(GraphicID.GUIPlayerSpeed,generateGraphic(guiTextureAtlas,496,112,16,16,0,0));
        graphics.put(GraphicID.GUIWhiteColor,generateGraphic(guiTextureAtlas,480,128,16,16,0,0));

    }
    
	private static Sprite loadHumanSprite(String string) throws IOException  {
		org.newdawn.slick.opengl.Texture atlas = loadTexture(string);
        return new Sprite(new Graphic[] { // N
                generateGraphic(atlas,0,192,64,64,32,62),
                generateGraphic(atlas,64,192,64,64,32,62),
                generateGraphic(atlas,128,192,64,64,32,62),
                generateGraphic(atlas,192,192,64,64,32,62),
            },new Graphic[] { // E
                generateGraphic(atlas,0,128,64,64,32,62),
                generateGraphic(atlas,64,128,64,64,32,62),
                generateGraphic(atlas,128,128,64,64,31,62),
                generateGraphic(atlas,192,128,64,64,31,62),
            },new Graphic[] { // S
                generateGraphic(atlas,0,0,64,64,32,62),
                generateGraphic(atlas,64,0,64,64,32,62),
                generateGraphic(atlas,128,0,64,64,32,62),
                generateGraphic(atlas,192,0,64,64,32,62),
            },new Graphic[] { // W
                generateGraphic(atlas,0,64,64,64,32,62),
                generateGraphic(atlas,64,64,64,64,32,62),
                generateGraphic(atlas,128,64,64,64,31,62),
                generateGraphic(atlas,192,64,64,64,31,62),
            });
	}

	@SuppressWarnings("deprecation")
	public static org.newdawn.slick.TrueTypeFont getSmallFont() {
        return smallFont;
    }

	@SuppressWarnings("deprecation")
	private static void setSmallFont(org.newdawn.slick.TrueTypeFont smallFont) {
		Resources.smallFont = smallFont;
	}

	@SuppressWarnings("deprecation")
	public static org.newdawn.slick.TrueTypeFont getInfoFont() {
        return infoFont;
    }
	
	@SuppressWarnings("deprecation")
	private static void setInfoFont(org.newdawn.slick.TrueTypeFont infoFont) {
		Resources.infoFont = infoFont;
	}

	@SuppressWarnings("deprecation")
	public static org.newdawn.slick.TrueTypeFont getBigFont() {
        return bigFont;
    }

	@SuppressWarnings("deprecation")
	public static org.newdawn.slick.TrueTypeFont getMenuFont() {
        return menuFont;
    }

	@SuppressWarnings("deprecation")
	private static void setBigFont(org.newdawn.slick.TrueTypeFont bigFont) {
		Resources.bigFont = bigFont;
	}

	@SuppressWarnings("deprecation")
	private static void setMenuFont(org.newdawn.slick.TrueTypeFont menuFont) {
		Resources.menuFont = menuFont;
	}
	
	@SuppressWarnings("deprecation")
	public static org.newdawn.slick.TrueTypeFont getBoldFont() {
        return boldFont;
    }

	@SuppressWarnings("deprecation")
	private static void setBoldFont(org.newdawn.slick.TrueTypeFont boldFont) {
		Resources.boldFont = boldFont;
	}

	private static Map<SpriteID,Sprite> getSprites() {
		return sprites;
	}

	private static void setSprites(Map<SpriteID,Sprite> sprites) {
		Resources.sprites = sprites;
	}
	
	public static Sprite getDefaultSprite() {
		return defaultSprite;
	}

	private static void setDefaultSprite(Sprite defaultSprite) {
		Resources.defaultSprite = defaultSprite;
	}

	public static Graphic getDefaultGraphic() {
		return defaultGraphic;
	}

	private static void setDefaultGraphic(Graphic defaultGraphic) {
		Resources.defaultGraphic = defaultGraphic;
	}


	private static Map<GraphicID,Graphic> getGraphics() {
		return graphics;
	}

	private static void setGraphics(Map<GraphicID,Graphic> graphics) {
		Resources.graphics = graphics;
	}

	public static ByteBuffer getIcon32() {
        return icon32;
    }

	private static void setIcon32(ByteBuffer icon32) {
		Resources.icon32 = icon32;
	}

	public static ByteBuffer getIcon16() {
        return icon16;
    }

	private static void setIcon16(ByteBuffer icon16) {
		Resources.icon16 = icon16;
	}

	public static Color getTransparentWhiteColor() {
		return transparentWhiteColor;
	}

	private static void setTransparentWhiteColor(Color transparentWhiteColor) {
		Resources.transparentWhiteColor = transparentWhiteColor;
	}

	public static Color getTransparentYellowColor() {
		return transparentYellowColor;
	}

	private static void setTransparentYellowColor(Color transparentYellowColor) {
		Resources.transparentYellowColor = transparentYellowColor;
	}

	public static Color getLightGrayColor() {
		return lightGrayColor;
	}

	private static void setLightGrayColor(Color lightGrayColor) {
		Resources.lightGrayColor = lightGrayColor;
	}

	private static Graphic generateGraphic(Texture textureAtlas,int x,int y,int w,int h,int xc,int yc) {
		float xi = (float)x+0.01f;
		float yi = (float)y+0.01f;
		float xf = (float)x+w-0.01f;
		float yf = (float)y+h-0.01f;
		return new Graphic(textureAtlas,xi/(float)textureAtlas.getImageWidth(),yi/(float)textureAtlas.getImageHeight(),xf/(float)textureAtlas.getImageWidth(),yf/(float)textureAtlas.getImageHeight(),xc,yc,w,h);
	}

	private static Graphic generateGraphicFix(Texture textureAtlas,int x,int y,int w,int h,int xc,int yc) {
		float xi = (float)x+0.3f;
		float yi = (float)y+0.3f;
		float xf = (float)x+w-0.3f;
		float yf = (float)y+h-0.3f;
		return new Graphic(textureAtlas,xi/(float)textureAtlas.getImageWidth(),yi/(float)textureAtlas.getImageHeight(),xf/(float)textureAtlas.getImageWidth(),yf/(float)textureAtlas.getImageHeight(),xc,yc,w,h);
	}
	
    @SuppressWarnings("deprecation")
	private static org.newdawn.slick.TrueTypeFont loadFont(String resourceName,boolean antialias,float size) throws FontFormatException, IOException {
        return new org.newdawn.slick.TrueTypeFont(Font.createFont(Font.TRUETYPE_FONT,org.newdawn.slick.util.ResourceLoader.getResourceAsStream(resourceName)).deriveFont(size),antialias);
    }

    private static org.newdawn.slick.opengl.Texture loadTexturePre(String resourceName) throws IOException {
        return TextureLoader.getTexture("PNG",org.newdawn.slick.util.ResourceLoader.getResourceAsStream(resourceName),GL_NEAREST);
    }
    
    private static org.newdawn.slick.opengl.Texture loadTexture(String resourceName) throws IOException {
        return TextureLoader.getTexture("PNG",org.newdawn.slick.util.ResourceLoader.getResourceAsStream(resourceName),GL_NEAREST);
    }
    
    private static ByteBuffer loadIcon(String iconPath) throws IOException {
        InputStream is = org.newdawn.slick.util.ResourceLoader.getResourceAsStream(iconPath);
        try {
            PNGDecoder decoder = new PNGDecoder(is);
            ByteBuffer bb = ByteBuffer.allocateDirect(decoder.getWidth()*decoder.getHeight()*4);
            decoder.decode(bb, decoder.getWidth()*4, PNGDecoder.Format.RGBA);
            bb.flip();
            return bb;
        } finally {
            is.close();
        }
    }
    
    public static Sprite getSprite(SpriteID sprite) {
    	Sprite ret = getSprites().get(sprite);
    	if (ret == null) {
			System.err.println("--------------");
			System.err.println("CRITICAL ERROR");
    		System.err.println("Invalid sprite.");
			System.err.println("spriteID "+sprite);
			System.err.println("--------------");
    		ret = getDefaultSprite(); 
    	}
    	return ret;
    }

    public static Graphic getGraphic(GraphicID graphic) {
    	Graphic ret = getGraphics().get(graphic);
    	if (ret == null) {
			System.err.println("--------------");
			System.err.println("CRITICAL ERROR");
    		System.err.println("Invalid graphic.");
			System.err.println("graphicID "+graphic);
			System.err.println("--------------");
    		ret = getDefaultGraphic(); 
    	}    	
        return ret;
    }

}
