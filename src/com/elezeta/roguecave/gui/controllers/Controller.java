package com.elezeta.roguecave.gui.controllers;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex3f;
import static org.lwjgl.opengl.GL11.glViewport;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;

import com.elezeta.roguecave.Constants;
import com.elezeta.roguecave.data.SpriteID;
import com.elezeta.roguecave.entities.Being;
import com.elezeta.roguecave.entities.Entity;
import com.elezeta.roguecave.entities.Pickup;
import com.elezeta.roguecave.entities.Player;
import com.elezeta.roguecave.gui.ControlID;
import com.elezeta.roguecave.gui.GraphicID;
import com.elezeta.roguecave.gui.RogueCaveGUI;
import com.elezeta.roguecave.gui.StringID;
import com.elezeta.roguecave.gui.controllers.ingame.IngameController;
import com.elezeta.roguecave.gui.controllers.ingame.InventoryController;
import com.elezeta.roguecave.gui.controllers.ingame.UpgradesController;
import com.elezeta.roguecave.gui.resources.Graphic;
import com.elezeta.roguecave.gui.resources.Resources;
import com.elezeta.roguecave.gui.resources.Sprite;
import com.elezeta.roguecave.gui.screenpad.ScreenMessage;
import com.elezeta.roguecave.inventory.Inventory;
import com.elezeta.roguecave.inventory.InventoryDrawInfo;
import com.elezeta.roguecave.inventory.Item;
import com.elezeta.roguecave.sprites.ColorMask;
import com.elezeta.roguecave.sprites.ColorSprite;
import com.elezeta.roguecave.sprites.CompositeSprite;
import com.elezeta.roguecave.sprites.SingleSprite;
import com.elezeta.roguecave.sprites.SpriteData;
import com.elezeta.roguecave.sprites.SpriteSlotID;
import com.elezeta.roguecave.world.Level;
import com.elezeta.roguecave.world.Tile;

public abstract class Controller {
	
	public boolean hasBar = true;
	
	public static Item dragItem;
	public static Item overItem;
	public static Set<Integer> dragItemFits;
	public static Set<Integer> dragItemCompatible;
	public static int originSlot = 0;
	public static int originX = 0;
	public static int originY = 0;
	public static boolean dragging = false;
	public static boolean mouseClicking = false;

	/* WORLD */
	public static float centerX;
	public static float centerY;
	public static int tileCenterI;
	public static int tileCenterJ;

	public static final float isometricAngle = (float) Math.toRadians(30);
	public static final float isoSin = (float) Math.sin(isometricAngle);
	public static final float isoCos = (float) Math.cos(isometricAngle);

	/* BUFFER */
	public static PriorityQueue<Entity> buffer = new PriorityQueue<Entity>(3000,new Comparator<Entity>() {
		@Override
		public int compare(Entity o1, Entity o2) {
			if (o1.getY()<o2.getY())
				return 1;
			if (Math.abs(o1.getY()-o2.getY())<Constants.EPS) {
				if (o1.getZ()>o2.getZ())
					return 1;
				if (Math.abs(o1.getZ()-o2.getZ())<Constants.EPS) {
					if (o1.hashCode()<o2.hashCode())
						return 1;
				}
			}
			return -1;
		}
	});
	
	public boolean hasBar() {
		return hasBar;
	}

	public void activate(Controller oldController) {
		originX = 0;
		originY = 0;
		mouseClicking = false;
	}
	
	public void run() {
		updateAbsoluteTimeCounter(RogueCaveGUI.delta);
	}

	public void fix() {
	}
	
	public void requestClose() {
	}

	public void deactivate() {
	}
	
	public boolean isKeyDown(Integer key) {
		return Keyboard.isKeyDown(key) && !RogueCaveGUI.messagePad.getTalkKeys().contains(key);
	}

	public void keyReleaseFilter(int eventKey,char character) {
		RogueCaveGUI.messagePad.getTalkKeys().remove(eventKey);
		keyRelease(eventKey);
	}
	
	public void keyPressFilter(int eventKey,char character) {
		if (RogueCaveGUI.messagePad.isTalkMode() == false) {
			if (eventKey==RogueCaveGUI.config.getKey(ControlID.TALK)) {
				Keyboard.enableRepeatEvents(true);
				RogueCaveGUI.messagePad.setTalkMode(true);
				RogueCaveGUI.messagePad.setTalkString("");
				RogueCaveGUI.messagePad.setTalkOriginal("");
				RogueCaveGUI.messagePad.setTalkHistoryIndex(-1);
				RogueCaveGUI.messagePad.setTalkStringIndex(0);
			}
			else
				keyPress(eventKey);
		}
		else {
			RogueCaveGUI.messagePad.getTalkKeys().add(eventKey);
			if (eventKey==RogueCaveGUI.config.getKey(ControlID.QUITFIXED)) {
				Keyboard.enableRepeatEvents(false);
				Keyboard.poll();
				if (!RogueCaveGUI.messagePad.getTalkString().equals(RogueCaveGUI.messagePad.getTalkOriginal())) {
					RogueCaveGUI.messagePad.getTalkHistory().addFirst(RogueCaveGUI.messagePad.getTalkString());
					while (RogueCaveGUI.messagePad.getTalkHistory().size()>RogueCaveGUI.config.historyLimit)
						RogueCaveGUI.messagePad.getTalkHistory().removeLast();
				}
				RogueCaveGUI.messagePad.setTalkMode(false);
				RogueCaveGUI.messagePad.setTalkString("");
				RogueCaveGUI.messagePad.setTalkOriginal("");
				RogueCaveGUI.messagePad.setTalkHistoryIndex(-1);
				RogueCaveGUI.messagePad.setTalkStringIndex(0);
			}
			else if (eventKey==RogueCaveGUI.config.getKey(ControlID.ENTERFIXED)) {
				Keyboard.enableRepeatEvents(false);
				Keyboard.poll();
				RogueCaveGUI.messagePad.setTalkMode(false);
				if (RogueCaveGUI.messagePad.getTalkString().length()>=1) {
					console(RogueCaveGUI.messagePad.getTalkString());
					if (!RogueCaveGUI.messagePad.getTalkString().equals(""))
						RogueCaveGUI.messagePad.getTalkHistory().addFirst(RogueCaveGUI.messagePad.getTalkString());
					while (RogueCaveGUI.messagePad.getTalkHistory().size()>RogueCaveGUI.config.historyLimit)
						RogueCaveGUI.messagePad.getTalkHistory().removeLast();
					RogueCaveGUI.messagePad.setTalkOriginal("");
				}
				RogueCaveGUI.messagePad.setTalkString("");
				RogueCaveGUI.messagePad.setTalkStringIndex(0);
			}
			else if (eventKey==RogueCaveGUI.config.getKey(ControlID.LEFTFIXED)) {
				if (RogueCaveGUI.messagePad.getTalkStringIndex()>0)
					RogueCaveGUI.messagePad.setTalkStringIndex(RogueCaveGUI.messagePad.getTalkStringIndex()-1);
			}
			else if (eventKey==RogueCaveGUI.config.getKey(ControlID.RIGHTFIXED)) {
				if (RogueCaveGUI.messagePad.getTalkStringIndex()<RogueCaveGUI.messagePad.getTalkString().length())
					RogueCaveGUI.messagePad.setTalkStringIndex(RogueCaveGUI.messagePad.getTalkStringIndex()+1);
			}
			else if (eventKey==RogueCaveGUI.config.getKey(ControlID.UPFIXED) || eventKey==RogueCaveGUI.config.getKey(ControlID.DOWNFIXED)) {
				if (!RogueCaveGUI.messagePad.getTalkString().equals(RogueCaveGUI.messagePad.getTalkOriginal())) {
					RogueCaveGUI.messagePad.getTalkHistory().add(RogueCaveGUI.messagePad.getTalkHistoryIndex(),RogueCaveGUI.messagePad.getTalkString());
					RogueCaveGUI.messagePad.setTalkHistoryIndex(RogueCaveGUI.messagePad.getTalkHistoryIndex()+1);
					while (RogueCaveGUI.messagePad.getTalkHistory().size()>RogueCaveGUI.config.historyLimit)
						RogueCaveGUI.messagePad.getTalkHistory().removeLast();
				}
				if (eventKey==RogueCaveGUI.config.getKey(ControlID.UPFIXED)) {
					if (RogueCaveGUI.messagePad.getTalkHistory().size()>RogueCaveGUI.messagePad.getTalkHistoryIndex()+1) {
						RogueCaveGUI.messagePad.setTalkHistoryIndex(RogueCaveGUI.messagePad.getTalkHistoryIndex()+1);
						RogueCaveGUI.messagePad.setTalkString(RogueCaveGUI.messagePad.getTalkHistory().get(RogueCaveGUI.messagePad.getTalkHistoryIndex()));
						RogueCaveGUI.messagePad.setTalkOriginal(RogueCaveGUI.messagePad.getTalkHistory().get(RogueCaveGUI.messagePad.getTalkHistoryIndex()));
						RogueCaveGUI.messagePad.setTalkStringIndex(RogueCaveGUI.messagePad.getTalkString().length());
					}
				}
				if (eventKey==RogueCaveGUI.config.getKey(ControlID.DOWNFIXED)) {
					if (RogueCaveGUI.messagePad.getTalkHistoryIndex()-1<0) {
						RogueCaveGUI.messagePad.setTalkHistoryIndex(-1);
						RogueCaveGUI.messagePad.setTalkString("");
						RogueCaveGUI.messagePad.setTalkOriginal("");
						RogueCaveGUI.messagePad.setTalkStringIndex(RogueCaveGUI.messagePad.getTalkString().length());
					}
					else {
						RogueCaveGUI.messagePad.setTalkHistoryIndex(RogueCaveGUI.messagePad.getTalkHistoryIndex()-1);
						RogueCaveGUI.messagePad.setTalkString(RogueCaveGUI.messagePad.getTalkHistory().get(RogueCaveGUI.messagePad.getTalkHistoryIndex()));
						RogueCaveGUI.messagePad.setTalkOriginal(RogueCaveGUI.messagePad.getTalkHistory().get(RogueCaveGUI.messagePad.getTalkHistoryIndex()));
					}
				}
			}
			else if (eventKey==RogueCaveGUI.config.getKey(ControlID.HOMEFIXED)) {
				RogueCaveGUI.messagePad.setTalkStringIndex(0);
			}
			else if (eventKey==RogueCaveGUI.config.getKey(ControlID.ENDFIXED)) {
				RogueCaveGUI.messagePad.setTalkStringIndex(RogueCaveGUI.messagePad.getTalkString().length());
			}

			else {
				if (character!=0) {
					if (character==8) {
						if (RogueCaveGUI.messagePad.getTalkString().length()>=1) {
							RogueCaveGUI.messagePad.setTalkString(RogueCaveGUI.messagePad.getTalkString().substring(0, RogueCaveGUI.messagePad.getTalkStringIndex()-1)+RogueCaveGUI.messagePad.getTalkString().substring(RogueCaveGUI.messagePad.getTalkStringIndex()));
							RogueCaveGUI.messagePad.setTalkStringIndex(RogueCaveGUI.messagePad.getTalkStringIndex()-1);
						}
					}
					else if (character==127) {
						if (RogueCaveGUI.messagePad.getTalkStringIndex()<RogueCaveGUI.messagePad.getTalkString().length()) {
							RogueCaveGUI.messagePad.setTalkString(RogueCaveGUI.messagePad.getTalkString().substring(0, RogueCaveGUI.messagePad.getTalkStringIndex())+RogueCaveGUI.messagePad.getTalkString().substring(RogueCaveGUI.messagePad.getTalkStringIndex()+1));
						}
					}
					else {
						if (character>=32 && character!=255 && character!=127) {
							RogueCaveGUI.messagePad.setTalkString(RogueCaveGUI.messagePad.getTalkString().substring(0, RogueCaveGUI.messagePad.getTalkStringIndex())+character+RogueCaveGUI.messagePad.getTalkString().substring(RogueCaveGUI.messagePad.getTalkStringIndex()));
							RogueCaveGUI.messagePad.setTalkStringIndex(RogueCaveGUI.messagePad.getTalkStringIndex()+1);
						}
					}
				}
			}
		}
	}
	
	public void console(String string) {
	}

	public void keyPress(int eventKey) {
		if (eventKey==RogueCaveGUI.config.getKey(ControlID.SHOWFPS)) { RogueCaveGUI.config.showFPS = !RogueCaveGUI.config.showFPS; }
		if (eventKey==RogueCaveGUI.config.getKey(ControlID.SHOWDEBUG)) { RogueCaveGUI.config.showDebug = !RogueCaveGUI.config.showDebug; }
	}

	public void keyRelease(int eventKey) {
	}
	
	public void mousePress(int x,int y) {
		originX = x;
		originY = y;
	}
	
	public void mouseDrag(int x,int y) {
	}

	public void mouseMove(int x,int y) {
	}
	
	public void mouseRelease(int x,int y) {
	}
	
	public void wheel(int delta) {
	}
	
	public void updateCenter() {
		centerX = Math.round(RogueCaveGUI.game.getPlayer().getX() * isoCos);
		centerY = Math.round(RogueCaveGUI.game.getPlayer().getY() * isoSin);
		tileCenterI = (int) Math.floor(RogueCaveGUI.game.getPlayer().getI(RogueCaveGUI.game.getLevel()));
		tileCenterJ = (int) Math.floor(RogueCaveGUI.game.getPlayer().getJ(RogueCaveGUI.game.getLevel()));
	}

	public void updateTimeCounter(float delta) {
		RogueCaveGUI.timeCounter += delta;
		RogueCaveGUI.timeCounter -= (int) Math.floor(RogueCaveGUI.timeCounter);
	}

	public void updateAbsoluteTimeCounter(float delta) {
		RogueCaveGUI.absoluteTimeCounter += delta;
		RogueCaveGUI.absoluteTimeCounter -= (int) Math.floor(RogueCaveGUI.absoluteTimeCounter);
	}
	
	public void mouseGrab(boolean grab) {
		boolean grabbed = Mouse.isGrabbed();
		if (grabbed == false && grab == true) {
			Mouse.setGrabbed(true);
		}
		if (grabbed == true && grab == false) {
			int preX = Mouse.getX();
			int preY = Mouse.getY();
			Mouse.setCursorPosition(preX,preY);
			Mouse.setGrabbed(false);
		}
	}
	
	public void manageInputMenu() {
		while (Keyboard.next()) {
			if (Keyboard.getEventKeyState()) {
				RogueCaveGUI.currentController.keyPressFilter(Keyboard.getEventKey(),Keyboard.getEventCharacter());
			}
			else
				RogueCaveGUI.currentController.keyReleaseFilter(Keyboard.getEventKey(),Keyboard.getEventCharacter());
		}
		while (Mouse.next()) {
			if (Mouse.getEventButtonState()) {
				if (mouseClicking == false) {
					mouseClicking = true;
					RogueCaveGUI.currentController.mousePress(Mouse.getEventX(),RogueCaveGUI.height - Mouse.getEventY());
				}
			}
			if (mouseClicking == true && !Mouse.isButtonDown(0) && !Mouse.isButtonDown(1) && !Mouse.isButtonDown(2)) {
				mouseClicking = false;
				RogueCaveGUI.currentController.mouseRelease(Mouse.getEventX(),RogueCaveGUI.height - Mouse.getEventY());
			}
			if (Mouse.getEventDWheel() < 0)
				RogueCaveGUI.currentController.wheel(-1);
			else if (Mouse.getEventDWheel() > 0)
				RogueCaveGUI.currentController.wheel(1);
		}
		if (mouseClicking)
			RogueCaveGUI.currentController.mouseDrag(Mouse.getEventX(),RogueCaveGUI.height - Mouse.getEventY());
		else
			if (RogueCaveGUI.mouseX!=Mouse.getX() || RogueCaveGUI.mouseY!=Mouse.getY())
				RogueCaveGUI.currentController.mouseMove(Mouse.getEventX(),RogueCaveGUI.height - Mouse.getEventY());
		RogueCaveGUI.mouseX = Mouse.getX();
		RogueCaveGUI.mouseY = Mouse.getY();
	}
	
	public void barClick(int x, int y, int button) {
		if (x >= 4 && x <= 76) {
			for (int i = 0;i < RogueCaveGUI.showCombos;i++) {
				drawComboSlot(i, 154+36*i);
			}

			if (y >= 154 && y <= 154 + 35)
				RogueCaveGUI.currentController.keyPress(RogueCaveGUI.config.getKey(ControlID.SWITCHWEAPON1));
			if (y >= 190 && y <= 190 + 35 && RogueCaveGUI.showCombos>=2)
				RogueCaveGUI.currentController.keyPress(RogueCaveGUI.config.getKey(ControlID.SWITCHWEAPON2));
			if (y >= 226 && y <= 226 + 35 && RogueCaveGUI.showCombos>=3)
				RogueCaveGUI.currentController.keyPress(RogueCaveGUI.config.getKey(ControlID.SWITCHWEAPON3));
			if (y >= 262 && y <= 262 + 35 && RogueCaveGUI.showCombos>=4)
				RogueCaveGUI.currentController.keyPress(RogueCaveGUI.config.getKey(ControlID.SWITCHWEAPON4));
			if (y >= 298 && y <= 298 + 35 && RogueCaveGUI.showCombos>=5)
				RogueCaveGUI.currentController.keyPress(RogueCaveGUI.config.getKey(ControlID.SWITCHWEAPON5));
			if (y >= 334 && y <= 334 + 35 && RogueCaveGUI.showCombos>=6)
				RogueCaveGUI.currentController.keyPress(RogueCaveGUI.config.getKey(ControlID.SWITCHWEAPON6));
			if (y >= 370 && y <= 370 + 35 && RogueCaveGUI.showCombos>=7)
				RogueCaveGUI.currentController.keyPress(RogueCaveGUI.config.getKey(ControlID.SWITCHWEAPON7));
			if (y >= 406 && y <= 406 + 35 && RogueCaveGUI.showCombos>=8)
				RogueCaveGUI.currentController.keyPress(RogueCaveGUI.config.getKey(ControlID.SWITCHWEAPON8));
			if (y >= 442 && y <= 442 + 35 && RogueCaveGUI.showCombos>=9)
				RogueCaveGUI.currentController.keyPress(RogueCaveGUI.config.getKey(ControlID.SWITCHWEAPON9));
			if (y >= 478 && y <= 478 + 35 && RogueCaveGUI.showCombos>=10)
				RogueCaveGUI.currentController.keyPress(RogueCaveGUI.config.getKey(ControlID.SWITCHWEAPON0));

			if (x >= 4 && x <= 4 + 35 && y >= 110 && y <= 110 + 35)
				RogueCaveGUI.currentController.keyPress(RogueCaveGUI.config.getKey(ControlID.INVENTORY));
			if (x >= 41 && x <= 41 + 35 && y >= 110 && y <= 110 + 35)
				RogueCaveGUI.currentController.keyPress(RogueCaveGUI.config.getKey(ControlID.UPGRADES));
			}
		if (RogueCaveGUI.currentController == IngameController.get()) {
			if (x >= 4 && x <= 4 + 35 && y >= 154+RogueCaveGUI.showCombos*36+5 && y <= 154+RogueCaveGUI.showCombos*36+5 + 35)
				RogueCaveGUI.currentController.keyPress(RogueCaveGUI.config.getKey(ControlID.SELFCOMBO1));
			if (x >= 41 && x <= 41 + 35 && y >= 154+RogueCaveGUI.showCombos*36+5 && y <= 154+RogueCaveGUI.showCombos*36+5 + 35)
				RogueCaveGUI.currentController.keyPress(RogueCaveGUI.config.getKey(ControlID.SELFCOMBO2));
			if (x >= 4 && x <= 4 + 35 && y >= 154+RogueCaveGUI.showCombos*36+5+36 && y <= 154+RogueCaveGUI.showCombos*36+5+36 + 35)
				RogueCaveGUI.currentController.keyPress(RogueCaveGUI.config.getKey(ControlID.SELFCOMBO3));
			if (x >= 41 && x <= 41 + 35 && y >= 154+RogueCaveGUI.showCombos*36+5+36 && y <= 154+RogueCaveGUI.showCombos*36+5+36 + 35)
				RogueCaveGUI.currentController.keyPress(RogueCaveGUI.config.getKey(ControlID.SELFCOMBO4));
		}
	}
	
	public boolean clickDrop(int mouseX1, int mouseY1, int mouseX2, int mouseY2,int x, int y, int w, int h) {
		return (mouseX1 >= x && mouseX1 <= x+w && mouseY1 >= y && mouseY1 <= y+h &&
				mouseX2 >= x && mouseX2 <= x+w && mouseY2 >= y && mouseY2 <= y+h);
	}

	public void updatePlayerCombos(Player player) {
		player.getInventory().setCombos(RogueCaveGUI.showCombos);
	}

	public void drawHBar(Graphic g, int x, int y, int w, int h, float has,
			float max) {
		g.getTexture().bind();

		float howMany = 0;
		if (max != 0)
			howMany = has / max;

		glBegin(GL_QUADS);
		glTexCoord2f(g.getX1(), g.getY2());
		glVertex3f(x, RogueCaveGUI.height - y, 0);
		glTexCoord2f(g.getX1(), g.getY1());
		glVertex3f(x, RogueCaveGUI.height - y + h, 0);
		glTexCoord2f(g.getX2(), g.getY1());
		glVertex3f(x + (w) * howMany, RogueCaveGUI.height - y + h, 0);
		glTexCoord2f(g.getX2(), g.getY2());
		glVertex3f(x + (w) * howMany, RogueCaveGUI.height - y, 0);
		glEnd();
	}

	public void drawVBar(Graphic g, int x, int y, int w, int h, float has,float max) {
		g.getTexture().bind();

		float howMany = 0;
		if (max != 0)
			howMany = has / max;

		glBegin(GL_QUADS);
		glTexCoord2f(g.getX1(), g.getY2());
		glVertex3f(x, RogueCaveGUI.height - y, 0);
		glTexCoord2f(g.getX1(), g.getY1());
		glVertex3f(x, RogueCaveGUI.height - y + h* howMany, 0);
		glTexCoord2f(g.getX2(), g.getY1());
		glVertex3f(x + w , RogueCaveGUI.height - y + h* howMany, 0);
		glTexCoord2f(g.getX2(), g.getY2());
		glVertex3f(x + w, RogueCaveGUI.height - y, 0);
		glEnd();
	}

	@SuppressWarnings("deprecation")
	public void drawTextLeft(org.newdawn.slick.TrueTypeFont font, int x,
			int y, String text, Color color) {
		font.drawString(x, RogueCaveGUI.height - y, text, color);
		glColor4f(1f, 1f, 1f, 1f);
	}

	@SuppressWarnings("deprecation")
	public void drawTextCenter(org.newdawn.slick.TrueTypeFont font, int x,
			int y, String text, Color color) {
		font.drawString(x - font.getWidth(text) / 2, RogueCaveGUI.height - y, text, color);
		glColor4f(1f, 1f, 1f, 1f);
	}

	@SuppressWarnings("deprecation")
	public void drawTextCenterV(org.newdawn.slick.TrueTypeFont font, int x,
			int y, String text, Color color) {
		font.drawString(x - font.getWidth(text) / 2, RogueCaveGUI.height - y- font.getHeight(text)/2, text, color);
		glColor4f(1f, 1f, 1f, 1f);
	}

	@SuppressWarnings("deprecation")
	public void drawTextRight(org.newdawn.slick.TrueTypeFont font, int x,
			int y, String text, Color color) {
		font.drawString(x - font.getWidth(text), RogueCaveGUI.height - y, text, color);
		glColor4f(1f, 1f, 1f, 1f);
	}

	public void drawWallsFirstPass(int i, int j, Level planeLevel) {
		Tile tile = planeLevel.getTile(i, j);
		Tile top = planeLevel.getTile(i - 1, j);
		Tile right = planeLevel.getTile(i, j + 1);
		Tile topRight = planeLevel.getTile(i - 1, j + 1);

		if (tile.hasWalls() && top.hasWallsAgainst()) {
			if (topRight.hasWallsAgainst())
				drawIsometricTopWall(i, j, tile);
		}
		if (tile.hasWalls() && right.hasWallsAgainst()) {
			if (topRight.hasWallsAgainst())
				drawIsometricRightWall(i, j, tile);
		}

	}

	public void drawWallsThirdPass(int i, int j, Level planeLevel) {
		Tile tile = planeLevel.getTile(i, j);
		Tile bottom = planeLevel.getTile(i + 1, j);
		Tile left = planeLevel.getTile(i, j - 1);

		glColor4f(1f, 1f, 1f, RogueCaveGUI.config.wallTransparence);
		if (tile.hasWalls() && bottom.hasWallsAgainst())
			drawIsometricBottomWall(i, j, tile);
		if (tile.hasWalls() && left.hasWallsAgainst())
			drawIsometricLeftWall(i, j, tile);
		glColor4f(1f, 1f, 1f, 1f);

	}

	public void drawWallsSecondPass(int i, int j, Level planeLevel) {
		Tile tile = planeLevel.getTile(i, j);
		Tile top = planeLevel.getTile(i - 1, j);
		Tile right = planeLevel.getTile(i, j + 1);
		Tile topRight = planeLevel.getTile(i - 1, j + 1);

		if (tile.hasWalls() && right.hasWallsAgainst()) {
			if (topRight.hasWalls())
				drawIsometricHalfRightWall(i, j, tile);
		}

		if (tile.hasWalls() && top.hasWallsAgainst()) {
			if (topRight.hasWalls())
				drawIsometricHalfTopWall(i, j, tile);
		}
	}

	public void drawWorld() {
		glClear(GL_DEPTH_BUFFER_BIT | GL_COLOR_BUFFER_BIT);
		glViewport(0, 0, RogueCaveGUI.screenWidth, RogueCaveGUI.height);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0,RogueCaveGUI.screenWidth,RogueCaveGUI.height,0, 1.5, -1.5);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		glEnable(GL_DEPTH_TEST);

		int extraTiles = 8;
		int tilesH = ((int) (RogueCaveGUI.screenWidth / RogueCaveGUI.game.getLevel().getTileFactor()))
				/ 2 + extraTiles;
		
		int tilesV = ((int) (RogueCaveGUI.height / (RogueCaveGUI.game.getLevel().getTileFactor()) * 1.65))
				/ 2 + extraTiles;

		for (int i = 0; i <= tilesH; i++) {
			for (int j = 0; j <= tilesV; j++) {
				int ti = tileCenterI - tilesH / 2 - tilesV / 2 + i + j;
				int tj = tileCenterJ - tilesH / 2 + tilesV / 2 + i - j;
				drawIsometric(ti, tj, RogueCaveGUI.game.getLevel().getTile(ti, tj));
			}
			for (int j = 0; j <= tilesV; j++) {
				int ti = tileCenterI - tilesH / 2 - tilesV / 2 + i + j
						- 1;
				int tj = tileCenterJ - tilesH / 2 + tilesV / 2 + i - j;
				drawIsometric(ti, tj, RogueCaveGUI.game.getLevel().getTile(ti, tj));
			}
		}

		glClear(GL_DEPTH_BUFFER_BIT);

		for (int i = 0; i <= tilesH; i++) {
			for (int j = 0; j <= tilesV; j++) {
				drawWallsFirstPass(tileCenterI - tilesH / 2 - tilesV / 2
						+ i + j, tileCenterJ - tilesH / 2 + tilesV / 2
						+ i - j, RogueCaveGUI.game.getLevel());
			}
			for (int j = 0; j <= tilesV; j++) {
				drawWallsFirstPass(tileCenterI - tilesH / 2 - tilesV / 2
						+ i + j - 1, tileCenterJ - tilesH / 2 + tilesV
						/ 2 + i - j, RogueCaveGUI.game.getLevel());
			}
		}

		
		for (int i = 0; i <= tilesH; i++) {
			for (int j = 0; j <= tilesV; j++) {
				drawWallsSecondPass(tileCenterI - tilesH / 2 - tilesV / 2
						+ i + j, tileCenterJ - tilesH / 2 + tilesV / 2
						+ i - j, RogueCaveGUI.game.getLevel());
			}
			for (int j = 0; j <= tilesV; j++) {
				drawWallsSecondPass(tileCenterI - tilesH / 2 - tilesV / 2
						+ i + j - 1, tileCenterJ - tilesH / 2 + tilesV
						/ 2 + i - j, RogueCaveGUI.game.getLevel());
			}
		}


		buffer.clear();
		
		buffer.addAll(RogueCaveGUI.game.getGoodAttacks());
		buffer.addAll(RogueCaveGUI.game.getGoodAttacks());
		buffer.addAll(RogueCaveGUI.game.getBadAttacks());
		buffer.addAll(RogueCaveGUI.game.getGoodBeings());
		buffer.addAll(RogueCaveGUI.game.getBadBeings());
		buffer.addAll(RogueCaveGUI.game.getPickups());
		if (RogueCaveGUI.config.highQuality) { 
			drawShadows(buffer);
		}

		
		drawEntities(buffer);

		for (int i = 0; i <= tilesH; i++) {
			for (int j = 0; j <= tilesV; j++) {
				drawWallsThirdPass(tileCenterI - tilesH / 2 - tilesV / 2
						+ i + j, tileCenterJ - tilesH / 2 + tilesV / 2
						+ i - j, RogueCaveGUI.game.getLevel());
			}
			for (int j = 0; j <= tilesV; j++) {
				drawWallsThirdPass(tileCenterI - tilesH / 2 - tilesV / 2
						+ i + j - 1, tileCenterJ - tilesH / 2 + tilesV
						/ 2 + i - j, RogueCaveGUI.game.getLevel());
			}
		}


		glDisable(GL_DEPTH_TEST);
	}

	public void drawInfo() {
		if (RogueCaveGUI.config.showDebug || RogueCaveGUI.config.showFPS) {
			glViewport(0, 0, RogueCaveGUI.screenWidth, RogueCaveGUI.height);
			glMatrixMode(GL_PROJECTION);
			glLoadIdentity();
			glOrtho(0,RogueCaveGUI.screenWidth,RogueCaveGUI.height,0, 1.5, -1.5);
			glMatrixMode(GL_MODELVIEW);
			glLoadIdentity();
			glDisable(GL_DEPTH_TEST);
			int pos = 4;
			if (RogueCaveGUI.config.showFPS) {
				drawTextLeft(Resources.getInfoFont(), 4, RogueCaveGUI.height - 5, RogueCaveGUI.language.getString(StringID.FPS)+": " + (int) RogueCaveGUI.fpsSoft, Color.black);
				drawTextLeft(Resources.getInfoFont(), 3, RogueCaveGUI.height - 4, RogueCaveGUI.language.getString(StringID.FPS)+": " + (int) RogueCaveGUI.fpsSoft, Color.white);
				pos += 12;
			}
			if (RogueCaveGUI.config.showDebug) {
				drawTextLeft(Resources.getInfoFont(), 4, RogueCaveGUI.height - pos -1, RogueCaveGUI.language.getString(StringID.ENTITIES)+": " + RogueCaveGUI.game.getEntityNumber(), Color.black);
				drawTextLeft(Resources.getInfoFont(), 3, RogueCaveGUI.height - pos -0, RogueCaveGUI.language.getString(StringID.ENTITIES)+": " + RogueCaveGUI.game.getEntityNumber(), Color.white);
				drawTextLeft(Resources.getInfoFont(), 4, RogueCaveGUI.height - pos -13, RogueCaveGUI.language.getString(StringID.X)+": " + (int) RogueCaveGUI.game.getPlayer().getX(), Color.black);
				drawTextLeft(Resources.getInfoFont(), 3, RogueCaveGUI.height - pos -12, RogueCaveGUI.language.getString(StringID.X)+": " + (int) RogueCaveGUI.game.getPlayer().getX(), Color.white);
				drawTextLeft(Resources.getInfoFont(), 4, RogueCaveGUI.height - pos -25, RogueCaveGUI.language.getString(StringID.Y)+": " + (int) RogueCaveGUI.game.getPlayer().getY(), Color.black);
				drawTextLeft(Resources.getInfoFont(), 3, RogueCaveGUI.height - pos -24, RogueCaveGUI.language.getString(StringID.Y)+": " + (int) RogueCaveGUI.game.getPlayer().getY(), Color.white);
				drawTextLeft(Resources.getInfoFont(), 4, RogueCaveGUI.height - pos -37, RogueCaveGUI.language.getString(StringID.I)+": " + RogueCaveGUI.game.getPlayer().getI(RogueCaveGUI.game.getLevel()), Color.black);
				drawTextLeft(Resources.getInfoFont(), 3, RogueCaveGUI.height - pos -36, RogueCaveGUI.language.getString(StringID.I)+": " + RogueCaveGUI.game.getPlayer().getI(RogueCaveGUI.game.getLevel()), Color.white);
				drawTextLeft(Resources.getInfoFont(), 4, RogueCaveGUI.height - pos -49, RogueCaveGUI.language.getString(StringID.J)+": " + RogueCaveGUI.game.getPlayer().getJ(RogueCaveGUI.game.getLevel()), Color.black);
				drawTextLeft(Resources.getInfoFont(), 3, RogueCaveGUI.height - pos -48, RogueCaveGUI.language.getString(StringID.J)+": " + RogueCaveGUI.game.getPlayer().getJ(RogueCaveGUI.game.getLevel()), Color.white);
				drawTextLeft(Resources.getInfoFont(), 4, RogueCaveGUI.height - pos -61, RogueCaveGUI.language.getString(StringID.CONTROLLER)+": " + RogueCaveGUI.currentController.getName(), Color.black);
				drawTextLeft(Resources.getInfoFont(), 3, RogueCaveGUI.height - pos -60, RogueCaveGUI.language.getString(StringID.CONTROLLER)+": " + RogueCaveGUI.currentController.getName(), Color.white);
			}
		}
	}

	public void drawMessagePad() {
		glViewport(0, 13, RogueCaveGUI.screenWidth, RogueCaveGUI.height-13);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0,RogueCaveGUI.screenWidth,RogueCaveGUI.height-13,0, 1.5, -1.5);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		glDisable(GL_DEPTH_TEST);
		int offset = (int)(RogueCaveGUI.messagePad.offset()*13f);
		int pos = 15;
		boolean seeAll = isKeyDown(RogueCaveGUI.config.getKey(ControlID.VIEWCONSOLE)); 
		if (RogueCaveGUI.messagePad.getNextMessage() != null) {
			ScreenMessage current = RogueCaveGUI.messagePad.getNextMessage();
			drawMessage(current,pos,offset,seeAll);
			pos+=13;
		}
		for (int i = RogueCaveGUI.messagePad.getMessages().size()-1;i >= 0;i--) {
			ScreenMessage current = RogueCaveGUI.messagePad.getMessages().get(i);
			drawMessage(current,pos,offset,seeAll);
			pos+=13;
		}
		if (RogueCaveGUI.messagePad.isTalkMode()) {
			glViewport(0, 0, RogueCaveGUI.screenWidth, RogueCaveGUI.height);
			glMatrixMode(GL_PROJECTION);
			glLoadIdentity();
			glOrtho(0,RogueCaveGUI.screenWidth,RogueCaveGUI.height,0, 1.5, -1.5);
			glMatrixMode(GL_MODELVIEW);
			glLoadIdentity();
			glDisable(GL_DEPTH_TEST);
			String text = "> "+RogueCaveGUI.messagePad.getTalkString();
			String text2 = "  ";
			for (int i = 0;i < RogueCaveGUI.messagePad.getTalkStringIndex();i++)
				text2 += ' ';
			if (RogueCaveGUI.messagePad.getTalkStringIndex()==RogueCaveGUI.messagePad.getTalkString().length())
				text2 += '_';
			else
				text2 += ((char)003);
			drawTextLeft(Resources.getInfoFont(), 4, 15,text,Color.black);
			if (RogueCaveGUI.absoluteTimeCounter < 0.25 || (RogueCaveGUI.absoluteTimeCounter > 0.50 && RogueCaveGUI.absoluteTimeCounter < 0.75))
				drawTextLeft(Resources.getInfoFont(), 4, 15,text2,Color.black);
			drawTextLeft(Resources.getInfoFont(), 3, 16,text,Color.white);
			if (RogueCaveGUI.absoluteTimeCounter < 0.25 || (RogueCaveGUI.absoluteTimeCounter > 0.50 && RogueCaveGUI.absoluteTimeCounter < 0.75))
				drawTextLeft(Resources.getInfoFont(), 3, 16,text2,Color.green);
		}
	}

	
	@SuppressWarnings("deprecation")
	private void drawMessage(ScreenMessage current, int pos, int offset,boolean seeAll) {
		if (current.getTime()>0f || seeAll) {
			Color shadowColor = current.getShadowColor();
			Color color = current.getColor();
			Color backgroundColor = current.getBackgroundColor();
			float alpha = current.getTime()/RogueCaveGUI.config.messageDisappearTime;
			if (seeAll)
				alpha = 1f;
			if (backgroundColor != null) {
				glColor4f(backgroundColor.r,backgroundColor.g,backgroundColor.b,backgroundColor.a*alpha);
				drawSquare(Resources.getGraphic(GraphicID.GUIWhiteColor),3,RogueCaveGUI.height-(pos+offset-1),Resources.getInfoFont().getWidth(current.getText()),13);
				glColor4f(1.f,1.f,1.f,1f);
			}
			float oldShadowColorA = shadowColor.a;
			float oldColorA = color.a;
			if (current.getTime()<RogueCaveGUI.config.messageDisappearTime && !seeAll) {
				shadowColor.a *= alpha;
				color.a *= alpha;
			}
			if (backgroundColor == null)
				drawTextLeft(Resources.getInfoFont(), 4, pos+offset,current.getText(),shadowColor);
			drawTextLeft(Resources.getInfoFont(), 3, pos+1+offset,current.getText(),color);
			shadowColor.a = oldShadowColorA;
			color.a = oldColorA;
		}
	}

	public void drawMouseCursor() {
		glViewport(0, 0, RogueCaveGUI.width, RogueCaveGUI.height);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, RogueCaveGUI.width, RogueCaveGUI.height, 0, 1.5, -1.5);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		glDisable(GL_DEPTH_TEST);
		if (RogueCaveGUI.mouseX < RogueCaveGUI.screenWidth) {
			if (RogueCaveGUI.showMouse) {
				
				glColor4f(1f,1f,1f,1f);
				drawSpriteGraphic(Resources.getGraphic(GraphicID.GUICrosshairOff),RogueCaveGUI.mouseX, RogueCaveGUI.mouseY, -1f);
				
				float busy = RogueCaveGUI.game.getPlayer().getBusyTime();
				float solid;
				if (busy<=0f)
					solid = 1f;
				else if (busy>1f)
					solid = 0f;
				else {
					solid = 1f-busy;
				}
				glColor4f(1f,1f,1f,solid);
				drawSpriteGraphic(Resources.getGraphic(GraphicID.GUICrosshairMid),RogueCaveGUI.mouseX, RogueCaveGUI.mouseY, -1f);
				glColor4f(1f,1f,1f,1f);
				
				if (busy<=0f)
					drawSpriteGraphic(Resources.getGraphic(GraphicID.GUICrosshairFull),RogueCaveGUI.mouseX, RogueCaveGUI.mouseY, -1f);
			}
		} else
			drawSpriteGraphic(Resources.getGraphic(GraphicID.GUIPointer),
					RogueCaveGUI.mouseX, RogueCaveGUI.mouseY, -1f);		
	}
	
	public void drawGrayGameScreen() {
		glViewport(0, 0, RogueCaveGUI.screenWidth, RogueCaveGUI.height);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0,RogueCaveGUI.screenWidth,RogueCaveGUI.height,0, 1.5, -1.5);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		glDisable(GL_DEPTH_TEST);
		glColor4f(0.f,0.f,0.f,0.7f);
		drawSquare(Resources.getGraphic(GraphicID.GUIGrayColor),0,0,RogueCaveGUI.width,RogueCaveGUI.height);
		glColor4f(1f,1f,1f,1f);
	}
	
	public void drawGrayScreen() {
		glViewport(0, 0, RogueCaveGUI.width, RogueCaveGUI.height);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0,RogueCaveGUI.width,RogueCaveGUI.height,0, 1.5, -1.5);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		glDisable(GL_DEPTH_TEST);
		glColor4f(0.f,0.f,0.f,0.7f);
		drawSquare(Resources.getGraphic(GraphicID.GUIGrayColor),0,0,RogueCaveGUI.width,RogueCaveGUI.height);
		glColor4f(1f,1f,1f,1f);
	}
	
	public void drawMainBar() {
		glViewport(RogueCaveGUI.screenWidth, 0, RogueCaveGUI.barWidth, RogueCaveGUI.height);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, RogueCaveGUI.barWidth, RogueCaveGUI.height, 0, 1.5, -1.5);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		for (int i = 0; i < RogueCaveGUI.height; i += 240)
			drawSpriteGraphic(Resources.getGraphic(GraphicID.GUIStatusBackground), 0,i, 0);
		drawSpriteGraphic(Resources.getGraphic(GraphicID.GUIStatusBackground), 0,RogueCaveGUI.height, 0);

		drawTextCenter(Resources.getInfoFont(), RogueCaveGUI.barWidth / 2, RogueCaveGUI.height - 2, RogueCaveGUI.game.getPlayer().getName(), Color.white);
		drawTextCenter(Resources.getInfoFont(), RogueCaveGUI.barWidth / 2, RogueCaveGUI.height - 16, RogueCaveGUI.language.getString(RogueCaveGUI.game.getPlayer().getProfession()), Color.white);

		drawSpriteGraphic(Resources.getGraphic(GraphicID.GUIPressedSlit), 21,RogueCaveGUI.height - 33, 0); // LEVEL + UPGRADE
		drawTextCenter(Resources.getSmallFont(), 21 + 38 / 2, RogueCaveGUI.height - 35,RogueCaveGUI.language.getString(StringID.LEVEL), Color.white);

		drawTextCenter(Resources.getBigFont(), 20 + 38 / 2, RogueCaveGUI.height - 45, "" + RogueCaveGUI.game.getPlayer().getLevel(), Color.white);
		drawHBar(Resources.getGraphic(GraphicID.GUIGrayColor), 25, RogueCaveGUI.height - 63, 28, 4, RogueCaveGUI.game.getPlayer().getExp(), RogueCaveGUI.game.getPlayer().getExpForLevelUp());
		drawSpriteGraphic(Resources.getGraphic(GraphicID.GUIExpBar), 24,RogueCaveGUI.height - 62, 0);

		// 3
		drawSpriteGraphic(Resources.getGraphic(GraphicID.GUIHpBar), 10,RogueCaveGUI.height - 75, 0);
		drawHBar(Resources.getGraphic(GraphicID.GUIRedColor), 11, RogueCaveGUI.height - 76,58, 13, RogueCaveGUI.game.getPlayer().getHp(), RogueCaveGUI.game.getPlayer().getStats().getMaxHp());
		drawSpriteGraphic(Resources.getGraphic(GraphicID.GUIPostHpBar), 10,RogueCaveGUI.height - 75, 0);
		drawTextCenter(Resources.getBoldFont(), RogueCaveGUI.barWidth / 2, RogueCaveGUI.height - 75, ""+ RogueCaveGUI.game.getPlayer().getHpRound(),Resources.getTransparentWhiteColor());

		drawSpriteGraphic(Resources.getGraphic(GraphicID.GUIMpBar), 10,RogueCaveGUI.height - 90, 0);
		drawHBar(Resources.getGraphic(GraphicID.GUIBlueColor), 11, RogueCaveGUI.height - 91,58, 13, RogueCaveGUI.game.getPlayer().getMp(), RogueCaveGUI.game.getPlayer().getStats().getMaxMp());
		drawSpriteGraphic(Resources.getGraphic(GraphicID.GUIPostMpBar), 10,RogueCaveGUI.height - 90, 0);
		int mp = Math.round(RogueCaveGUI.game.getPlayer().getMp());
		if (RogueCaveGUI.game.getPlayer().getMp()>0 && mp==0)
			mp = 1;
		drawTextCenter(Resources.getBoldFont(), RogueCaveGUI.barWidth / 2, RogueCaveGUI.height - 90, ""+ mp,Resources.getTransparentWhiteColor());

		if (RogueCaveGUI.currentController == InventoryController.get()) //TODO multi
			drawSpriteGraphic(Resources.getGraphic(GraphicID.GUIPressedSlit), 4,RogueCaveGUI.height - 110, 0);
		else
			drawSpriteGraphic(Resources.getGraphic(GraphicID.GUIButton), 4,RogueCaveGUI.height - 110, 0);

		if (RogueCaveGUI.game.getPlayer().getInventory().isAnyNewItem()) {
			if (RogueCaveGUI.timeCounter < 0.5)					
				drawSpriteGraphic(Resources.getGraphic(GraphicID.GUIBackpack), 7,RogueCaveGUI.height - 112, 0);
			else
				drawSpriteGraphic(Resources.getGraphic(GraphicID.GUIBackpackOn), 7,RogueCaveGUI.height - 112, 0);
		}
		else
			drawSpriteGraphic(Resources.getGraphic(GraphicID.GUIBackpack), 7,RogueCaveGUI.height - 112, 0);
		drawTextLeft(Resources.getSmallFont(), 7 - 1, RogueCaveGUI.height - 110 - 1, keyText(ControlID.INVENTORY), Color.black); //TODOletter
		drawTextLeft(Resources.getSmallFont(), 7, RogueCaveGUI.height - 110, keyText(ControlID.INVENTORY), Resources.getTransparentWhiteColor());

		if (RogueCaveGUI.currentController == UpgradesController.get())
			drawSpriteGraphic(Resources.getGraphic(GraphicID.GUIPressedSlit), 41,RogueCaveGUI.height - 110, 0);
		else
			drawSpriteGraphic(Resources.getGraphic(GraphicID.GUIButton), 41,RogueCaveGUI.height - 110, 0);

		if (RogueCaveGUI.game.getPlayer().getUpgradePoints() > 0) {
			if (RogueCaveGUI.timeCounter < 0.5)					
				drawSpriteGraphic(Resources.getGraphic(GraphicID.GUIUpgradesOn1), 43,RogueCaveGUI.height - 112, 0);
			else
				drawSpriteGraphic(Resources.getGraphic(GraphicID.GUIUpgradesOn2), 43,RogueCaveGUI.height - 112, 0);
		}
		else
			drawSpriteGraphic(Resources.getGraphic(GraphicID.GUIUpgradesOff), 43,RogueCaveGUI.height - 112, 0);

		drawTextLeft(Resources.getSmallFont(), 44 - 1, RogueCaveGUI.height - 110 - 1, keyText(ControlID.UPGRADES), Color.black);
		drawTextLeft(Resources.getSmallFont(), 44, RogueCaveGUI.height - 110, keyText(ControlID.UPGRADES), Resources.getTransparentWhiteColor());

		for (int i = 0;i < RogueCaveGUI.showCombos;i++) {
			drawComboSlot(i, 154+36*i);
		}

		drawComboSelfSlot(0, 4, 154+RogueCaveGUI.showCombos*36+5);
		drawComboSelfSlot(1, 41, 154+RogueCaveGUI.showCombos*36+5);
		drawComboSelfSlot(2, 4, 154+RogueCaveGUI.showCombos*36+5+36);
		drawComboSelfSlot(3, 41, 154+RogueCaveGUI.showCombos*36+5+36);
	}
	
	public void drawShadows(PriorityQueue<Entity> buffer) {
		for (Iterator<Entity> ite = buffer.iterator();ite.hasNext();)
			drawShadow(ite.next());
	}

	public void drawEntities(PriorityQueue<Entity> buffer) {
		while (!buffer.isEmpty())
			drawEntity(buffer.poll());
	}
	public void drawComboSelfSlot(int slot, int x, int y) {
		int chosenComboSelf = RogueCaveGUI.game.getPlayer().getInventory()
				.getChosenComboSelf();
		if (!RogueCaveGUI.game.getPlayer().getInventory().getUseSelf())
			chosenComboSelf = -1;
		GraphicID resId;
		if (chosenComboSelf == slot)
			resId = GraphicID.GUISlit;
		else
			resId = GraphicID.GUIButton;
		drawSpriteGraphic(Resources.getGraphic(resId), x, RogueCaveGUI.height - y, 0);
		String key;
		if (slot == 0)
			key = keyText(ControlID.SELFCOMBO1);
		else if (slot == 1)
			key = keyText(ControlID.SELFCOMBO2);
		else if (slot == 2)
			key = keyText(ControlID.SELFCOMBO3);
		else
			key = keyText(ControlID.SELFCOMBO4);

		Item item = RogueCaveGUI.game.getPlayer().getInventory().getSelfComboMainItem(slot);
		drawItem(item,x+18,y+18,GraphicID.GUIEmptyRightHandSelected,Inventory.COMBOSSELF+1+slot);

		drawTextLeft(Resources.getSmallFont(), x + 3 - 1, RogueCaveGUI.height - y - 1,key, Color.black);
		drawTextLeft(Resources.getSmallFont(), x + 3, RogueCaveGUI.height - y, key,
				Resources.getTransparentWhiteColor());

		fillFitColor(Inventory.COMBOSSELF+1+slot,x,y);

	}
	
	private String keyText(ControlID id) {
		String text = RogueCaveGUI.language.getKeyString(RogueCaveGUI.config.getKey(id));
		int max = 5;
		if (text.length()>max)
			text = text.replace(" ","");
		text = text.trim();
		if (text.length()<max)
			max = text.length();
		return text.substring(0,max);
	}

	public void drawComboSlot(int slot, int y) {
		int chosenCombo = RogueCaveGUI.game.getPlayer().getInventory().getChosenCombo();
		GraphicID resId;
		if (chosenCombo == slot)
			resId = GraphicID.GUIBigSlit;
		else
			resId = GraphicID.GUIBigButton;
		drawSpriteGraphic(Resources.getGraphic(resId), 4, RogueCaveGUI.height - y, 0);

		Item item = RogueCaveGUI.game.getPlayer().getInventory().getComboMainItem(slot);
		Item item2 = RogueCaveGUI.game.getPlayer().getInventory().getComboOffhandItem(slot);

		boolean twohands = false;
		if (item != null)
			if (item.isTwoHanded())
				twohands = true;
		if (item2 != null)
			if (item2.isTwoHanded())
				twohands = true;
		
		GraphicID def;
		
		def = GraphicID.naught;
		if (!twohands)
			def = GraphicID.GUIEmptyRightHandSelected;
		drawItem(item,22,y+18,def,Inventory.COMBOS+slot*10+1);

		def = GraphicID.naught;
		if (!twohands)
			def = GraphicID.GUIEmptyLeftHandSelected;
		drawItem(item2,59,y+18,def,Inventory.COMBOS+slot*10+2);


		String key = "";
		if (slot == 0)
			key = keyText(ControlID.SWITCHWEAPON1);
		else if (slot == 1)
			key = keyText(ControlID.SWITCHWEAPON2);
		else if (slot == 2)
			key = keyText(ControlID.SWITCHWEAPON3);
		else if (slot == 3)
			key = keyText(ControlID.SWITCHWEAPON4);
		else if (slot == 4)
			key = keyText(ControlID.SWITCHWEAPON5);
		else if (slot == 5)
			key = keyText(ControlID.SWITCHWEAPON6);
		else if (slot == 6)
			key = keyText(ControlID.SWITCHWEAPON7);
		else if (slot == 7)
			key = keyText(ControlID.SWITCHWEAPON8);
		else if (slot == 8)
			key = keyText(ControlID.SWITCHWEAPON9);
		else
			key = keyText(ControlID.SWITCHWEAPON0);

				drawTextCenter(Resources.getSmallFont(), RogueCaveGUI.barWidth / 2, RogueCaveGUI.height - y - 11
				- 1,key, Color.black);
		drawTextCenter(Resources.getSmallFont(), RogueCaveGUI.barWidth / 2 + 1, RogueCaveGUI.height - y
				- 11,key,
				Resources.getTransparentWhiteColor());

		fillFitColor(Inventory.COMBOS+slot*10+1,4,y);
		fillFitColor(Inventory.COMBOS+slot*10+2,40,y);
	}

	public void drawSquare(Graphic g, int x, int y, int w, int h) {
		g.getTexture().bind();

		glBegin(GL_QUADS);
		glTexCoord2f(g.getX1(), g.getY2());
		glVertex3f(x, y, 0);
		glTexCoord2f(g.getX1(), g.getY1());
		glVertex3f(x, y + h, 0);
		glTexCoord2f(g.getX2(), g.getY1());
		glVertex3f(x+w, y + h, 0);
		glTexCoord2f(g.getX2(), g.getY2());
		glVertex3f(x+w,y, 0);
		glEnd();
	}
	
	public void fillNewColor(int slot,int x,int y) {
		Item it = RogueCaveGUI.game.getPlayer().getInventory().getItem(slot);
		if (it != null) {
			if (RogueCaveGUI.game.getPlayer().getInventory().isNewItem(it)) {
				glColor4f(1f,1f,1f,0.5f);
				drawSquare(Resources.getGraphic(GraphicID.GUIGreenColor),x,y,36,36);
				glColor4f(1f,1f,1f,1f);
			}
		}
	}
		
	public void fillFitColor(int slot,int x,int y) {
		if (dragItem != null && dragging) {
			if (!dragItem.isEquipable()) {
				if (!dragItemFits.contains(slot)) {
					glColor4f(1f,1f,1f,0.2f);
					drawSquare(Resources.getGraphic(GraphicID.GUIRedColor),x,y,36,36);
					glColor4f(1f,1f,1f,1f);
				}
				else if ((!dragItemCompatible.contains(slot) && slot != originSlot && RogueCaveGUI.game.getPlayer().getInventory().accompanyingSlot(slot) != originSlot) || (RogueCaveGUI.game.getPlayer().getInventory().isComboSelfSlot(slot) && RogueCaveGUI.game.getPlayer().getInventory().getItem(slot) != null)) {
					glColor4f(1f,1f,1f,0.2f);
					drawSquare(Resources.getGraphic(GraphicID.GUIYellowColor),x,y,36,36);
					glColor4f(1f,1f,1f,1f);
				}
			}
		}
	}
	
	public void drawItem(Item item,int x,int y,GraphicID def,int slot) {
		ColorMask cm;
		Graphic g = Resources.getGraphic(def);
		boolean transp = (slot==originSlot && dragging && dragItem != null) || (RogueCaveGUI.game.getPlayer().getInventory().isUsed(item) && RogueCaveGUI.game.getPlayer().getInventory().isBasicSlot(slot));

		if (item != null) {
			g = Resources.getSprite(((SingleSprite)item.getInventorySprite()).getSpriteID()).getGraphic(RogueCaveGUI.moveAnimationCounter);
			//no-composite
			if (ColorSprite.class.isInstance(item.getInventorySprite()))
				cm = ((ColorSprite) item.getInventorySprite()).getColor();
			else
				cm = new ColorMask(1f,1f,1f,1f);
		}
		else
			cm = new ColorMask(1f,1f,1f,1f);
		if (transp)
			glColor4f(cm.getRed(),cm.getGreen(),cm.getBlue(),RogueCaveGUI.config.usedItemTransparence*cm.getAlpha());
		else
			glColor4f(cm.getRed(),cm.getGreen(),cm.getBlue(),cm.getAlpha());
		if (g != null)
			drawItemGraphic(g, x, RogueCaveGUI.height - y, 0);

		if (item != null) {
			if (item.isStackable()) {
				Color bC;
				Color wC;
				if (transp) {
					bC = new Color(0f,0f,0f,RogueCaveGUI.config.usedItemTransparence);
					wC = new Color(1f,1f,1f,RogueCaveGUI.config.usedItemTransparence*0.9f);
				}
				else {
					bC = new Color(0f,0f,0f,1f);
					wC = new Color(1f,1f,1f,0.9f);
				}
				drawTextRight(Resources.getSmallFont(), x+16, RogueCaveGUI.height - y - 6,"x" + item.getQuantity(),bC);
				drawTextRight(Resources.getSmallFont(), x+17, RogueCaveGUI.height - y - 5,"x" + item.getQuantity(),wC);
			}
		}
		glColor4f(1f,1f,1f,1f);
	}

	public void drawIsometricTopWall(int i, int j, Tile tile) {
		SpriteID sprite = tile.getWallSprite();
		if (sprite == null)
			return;
		Graphic g = Resources.getSprite(sprite)
				.getGraphic(RogueCaveGUI.moveAnimationCounter);
		float halfWStretch = isoCos * RogueCaveGUI.game.getLevel().getTileFactor();
		float halfHStretch = isoSin * RogueCaveGUI.game.getLevel().getTileFactor();
		float x = (i + j) * halfWStretch;
		float y = (j - i) * halfHStretch;
		drawIsometricStandingGraphic(g, x - halfWStretch - centerX
				+ RogueCaveGUI.screenWidthf / 2f, y - centerY + RogueCaveGUI.heightf / 2f, x - centerX
				+ RogueCaveGUI.screenWidthf / 2f, y - centerY + RogueCaveGUI.heightf / 2f + halfHStretch,
				RogueCaveGUI.game.getLevel().getWallHeight(), (y - centerY + RogueCaveGUI.heightf / 2f)
						/ RogueCaveGUI.heightf);
	}

	public void drawIsometricHalfTopWall(int i, int j, Tile tile) {
		SpriteID sprite = tile.getWallSprite();
		if (sprite == null)
			return;
		Graphic g = Resources.getSprite(sprite)
				.getGraphic(RogueCaveGUI.moveAnimationCounter);
		float halfWStretch = isoCos * RogueCaveGUI.game.getLevel().getTileFactor();
		float halfHStretch = isoSin * RogueCaveGUI.game.getLevel().getTileFactor();
		float x = (i + j) * halfWStretch;
		float y = (j - i) * halfHStretch;
		drawIsometricHalfStandingGraphic(g, x - halfWStretch - centerX
				+ RogueCaveGUI.screenWidthf / 2f, y - centerY + RogueCaveGUI.heightf / 2f, x - centerX
				+ RogueCaveGUI.screenWidthf / 2f, y - centerY + RogueCaveGUI.heightf / 2f + halfHStretch,
				RogueCaveGUI.game.getLevel().getWallHeight(), (y - centerY + RogueCaveGUI.heightf / 2f)
						/ RogueCaveGUI.heightf);
		glColor4f(1f, 1f, 1f, RogueCaveGUI.config.wallTransparence);
		drawIsometricHalfFloatingGraphic(g, x - halfWStretch - centerX
				+ RogueCaveGUI.screenWidthf / 2f, y - centerY + RogueCaveGUI.heightf / 2f, x - centerX
				+ RogueCaveGUI.screenWidthf / 2f, y - centerY + RogueCaveGUI.heightf / 2f + halfHStretch,
				RogueCaveGUI.game.getLevel().getWallHeight(), (y - centerY + RogueCaveGUI.heightf / 2f)
						/ RogueCaveGUI.heightf);
		glColor4f(1f, 1f, 1f, 1f);
	}

	public void drawIsometricBottomWall(int i, int j, Tile tile) {
		SpriteID sprite = tile.getWallSprite();
		if (sprite == null)
			return;
		Graphic g = Resources.getSprite(sprite)
				.getGraphic(RogueCaveGUI.moveAnimationCounter);
		float halfWStretch = isoCos * RogueCaveGUI.game.getLevel().getTileFactor();
		float halfHStretch = isoSin * RogueCaveGUI.game.getLevel().getTileFactor();
		float x = (i + j) * halfWStretch;
		float y = (j - i) * halfHStretch;
		drawIsometricStandingGraphic(g, x - centerX + RogueCaveGUI.screenWidthf / 2f, y
				- halfHStretch - centerY + RogueCaveGUI.heightf / 2f, x + halfWStretch
				- centerX + RogueCaveGUI.screenWidthf / 2f, y - centerY + RogueCaveGUI.heightf / 2f, RogueCaveGUI.game
				.getLevel().getWallHeight(), (y - centerY + RogueCaveGUI.heightf / 2f)
				/ RogueCaveGUI.heightf);
	}

	public void drawIsometricLeftWall(int i, int j, Tile tile) {
		SpriteID sprite = tile.getWallSprite();
		if (sprite == null)
			return;
		Graphic g = Resources.getSprite(sprite)
				.getGraphic(RogueCaveGUI.moveAnimationCounter);
		float halfWStretch = isoCos * RogueCaveGUI.game.getLevel().getTileFactor();
		float halfHStretch = isoSin * RogueCaveGUI.game.getLevel().getTileFactor();
		float x = (i + j) * halfWStretch;
		float y = (j - i) * halfHStretch;
		drawIsometricStandingGraphic(g, x - halfWStretch - centerX
				+ RogueCaveGUI.screenWidthf / 2f, y - centerY + RogueCaveGUI.heightf / 2f, x - centerX
				+ RogueCaveGUI.screenWidthf / 2f, y - halfHStretch - centerY + RogueCaveGUI.heightf / 2f,
				RogueCaveGUI.game.getLevel().getWallHeight(), (y - centerY + RogueCaveGUI.heightf / 2f)
						/ RogueCaveGUI.heightf);
	}

	public void drawIsometricRightWall(int i, int j, Tile tile) {
		SpriteID sprite = tile.getWallSprite();
		if (sprite == null)
			return;
		Graphic g = Resources.getSprite(sprite)
				.getGraphic(RogueCaveGUI.moveAnimationCounter);
		float halfWStretch = isoCos * RogueCaveGUI.game.getLevel().getTileFactor();
		float halfHStretch = isoSin * RogueCaveGUI.game.getLevel().getTileFactor();
		float x = (i + j) * halfWStretch;
		float y = (j - i) * halfHStretch;
		drawIsometricStandingGraphic(g, x - centerX + RogueCaveGUI.screenWidthf / 2f, y
				- centerY + RogueCaveGUI.heightf / 2f + halfHStretch, x - centerX
				+ RogueCaveGUI.screenWidthf / 2f + halfWStretch, y - centerY + RogueCaveGUI.heightf / 2f,
				RogueCaveGUI.game.getLevel().getWallHeight(), (y - centerY + RogueCaveGUI.heightf / 2f)
						/ RogueCaveGUI.heightf);
	}

	public void drawIsometricHalfRightWall(int i, int j, Tile tile) {
		SpriteID sprite = tile.getWallSprite();
		if (sprite == null)
			return;
		Graphic g = Resources.getSprite(sprite)
				.getGraphic(RogueCaveGUI.moveAnimationCounter);
		float halfWStretch = isoCos * RogueCaveGUI.game.getLevel().getTileFactor();
		float halfHStretch = isoSin * RogueCaveGUI.game.getLevel().getTileFactor();
		float x = (i + j) * halfWStretch;
		float y = (j - i) * halfHStretch;
		drawIsometricHalfStandingGraphic(g, x - centerX + RogueCaveGUI.screenWidthf / 2f
				+ halfWStretch, y - centerY + RogueCaveGUI.heightf / 2f, x - centerX
				+ RogueCaveGUI.screenWidthf / 2f, y - centerY + RogueCaveGUI.heightf / 2f + halfHStretch,
				RogueCaveGUI.game.getLevel().getWallHeight(), (y - centerY + RogueCaveGUI.heightf / 2f)
						/ RogueCaveGUI.heightf);
		glColor4f(1f, 1f, 1f, RogueCaveGUI.config.wallTransparence);
		drawIsometricHalfFloatingGraphic(g, x - centerX + RogueCaveGUI.screenWidthf / 2f
				+ halfWStretch, y - centerY + RogueCaveGUI.heightf / 2f, x - centerX
				+ RogueCaveGUI.screenWidthf / 2f, y - centerY + RogueCaveGUI.heightf / 2f + halfHStretch,
				RogueCaveGUI.game.getLevel().getWallHeight(), (y - centerY + RogueCaveGUI.heightf / 2f)
						/ RogueCaveGUI.heightf);
		glColor4f(1f, 1f, 1f, 1f);
	}

	public void drawIsometric(int i, int j, Tile tile) {
		SpriteID sprite = tile.getGroundSprite();
		if (sprite == null)
			return;
		Graphic g = Resources.getSprite(sprite)
				.getGraphic(RogueCaveGUI.moveAnimationCounter);
		float halfWStretch = isoCos * RogueCaveGUI.game.getLevel().getTileFactor();
		float halfHStretch = isoSin * RogueCaveGUI.game.getLevel().getTileFactor();
		float x = (i + j) * halfWStretch;
		float y = (j - i) * halfHStretch;
		drawIsometricGraphic(g, x - centerX + RogueCaveGUI.screenWidthf / 2f, y - centerY
				+ RogueCaveGUI.heightf / 2f, halfWStretch, halfHStretch, (y
				+ RogueCaveGUI.game.getLevel().getTileFactor() - centerY + RogueCaveGUI.heightf / 2f)
				/ RogueCaveGUI.heightf);
	}

	public void drawShadow(Entity e) {
		if (e.getShadow()==0f)
			return;
		float transp = 1f;
		if (Pickup.class.isInstance(e)) {
			Float timer = RogueCaveGUI.game.getPlayer().getPickupTimer().get(e);
			Item item = ((Pickup)e).getItem();
			if (!RogueCaveGUI.game.getPlayer().getInventory().wantToPickUp(item)) {
				transp = 0f;
			}
			else if (timer != null) {
				float howMuch = timer/Constants.dropDoNotPickup;
				transp = (1f-howMuch)*(1f-RogueCaveGUI.config.disabledPickupTransparence)+RogueCaveGUI.config.disabledPickupTransparence;
			}
			else if (!RogueCaveGUI.game.getPlayer().getInventory().canPickup(item) && RogueCaveGUI.game.getPlayer().distanceTo(e) <= Constants.pickupMagnetDistance) {
				if (RogueCaveGUI.timeCounter < 0.5)
					transp = RogueCaveGUI.config.disabledPickupTransparence;
				else
					transp = 1f;
			}
		}
		Graphic g = Resources.getGraphic(GraphicID.shadow);
		float cx = e.getX();
		float cy = e.getY();
		float x = cx * isoCos;
		float y = cy * isoSin;
		float drawX = x - centerX + RogueCaveGUI.screenWidthf / 2f;
		float drawY = y - centerY + RogueCaveGUI.heightf / 2f;
		if (drawX + g.getWidth() > 0 && drawX - g.getWidth() < RogueCaveGUI.screenWidthf
				&& drawY + g.getHeight() > 0 && drawY - g.getHeight() < RogueCaveGUI.heightf) {
			glColor4f(1f, 1f, 1f, e.getShadow()*transp);
			drawIsometricGraphic(g, drawX, drawY, e.getRadius()*isoCos,e.getRadius()*isoSin,1);
			glColor4f(1f, 1f, 1f,1f);
		}

	}

	public Map<SpriteSlotID, SingleSprite> getSprites(Entity e) {
		SpriteData sd = e.getSprite();
		Map<SpriteSlotID, SingleSprite> h = new HashMap<SpriteSlotID, SingleSprite>();
		if (SingleSprite.class.isInstance(sd)) {
			h.put(SpriteSlotID.BODY,(SingleSprite)sd);
		}
		else if (CompositeSprite.class.isInstance(sd)) {
			CompositeSprite cs = (CompositeSprite)e.getSprite();
			h.putAll(cs.getSprites());
			if (Being.class.isInstance(e)) {
				Being b = (Being)e;
				h.putAll(b.getInventory().getEquipmentSprites());
				h.putAll(b.getInventory().getComboSprites());
			}
			InventoryDrawInfo.filter(h);
		}
		return h;
	}
	
	public void drawEntity(Entity e) {
		Map<SpriteSlotID, SingleSprite> h = getSprites(e);
		for (Iterator<SpriteSlotID> ite = InventoryDrawInfo.getInventoryDrawOrder().iterator();ite.hasNext();)
			drawEntitySprite(e,h.get(ite.next()));
	}

	public void drawEntityFront(Entity e,int x,int y) {
		Map<SpriteSlotID, SingleSprite> h = getSprites(e);
		for (Iterator<SpriteSlotID> ite = InventoryDrawInfo.getInventoryDrawOrder().iterator();ite.hasNext();) {
			SingleSprite sd = h.get(ite.next());
			if (sd != null) {
				SpriteID sprite = sd.getSpriteID();
				Sprite spr = Resources.getSprite(sprite);
				Graphic g = spr.getGraphicFront();
				if (ColorSprite.class.isInstance(sd)) {
					ColorMask cm = ((ColorSprite)sd).getColor();
					glColor4f(cm.getRed(),cm.getGreen(),cm.getBlue(),cm.getAlpha());
				}
				drawSprite(g, x, y,-1);
				glColor4f(1f,1f,1f,1f);
			}
		}
	}

	public void drawEntitySprite(Entity e,SingleSprite spriteData) {
		ColorMask cm;
		if (ColorSprite.class.isInstance(spriteData))
			cm = ((ColorSprite)spriteData).getColor();
		else
			cm = new ColorMask(1f,1f,1f,1f);
		SpriteID sprite = null;
		if (spriteData != null)
			sprite = spriteData.getSpriteID();
		if (sprite == null)
			return;
		Sprite spr = Resources.getSprite(sprite);
		if (spr.getTail()>e.getRunDistance())
			return;
		glColor4f(cm.getRed(),cm.getGreen(),cm.getBlue(),cm.getAlpha());
		if (Pickup.class.isInstance(e)) {
			Float timer = RogueCaveGUI.game.getPlayer().getPickupTimer().get(e);
			Item item = ((Pickup)e).getItem();
			if (!RogueCaveGUI.game.getPlayer().getInventory().wantToPickUp(item)) {
				glColor4f(cm.getRed(),cm.getGreen(),cm.getBlue(),cm.getAlpha()*RogueCaveGUI.config.disabledPickupTransparence);
			}
			else if (timer != null) {
				float howMuch = timer/Constants.dropDoNotPickup;
				glColor4f(cm.getRed(),cm.getGreen(),cm.getBlue(),cm.getAlpha()*((1f-howMuch)*(1f-RogueCaveGUI.config.disabledPickupTransparence)+RogueCaveGUI.config.disabledPickupTransparence));
			}
			else if (!RogueCaveGUI.game.getPlayer().getInventory().canPickup(item) && RogueCaveGUI.game.getPlayer().distanceTo(e) <= Constants.pickupMagnetDistance) {
				if (RogueCaveGUI.timeCounter < 0.5)
					glColor4f(cm.getRed(),cm.getGreen(),cm.getBlue(),cm.getAlpha()*RogueCaveGUI.config.disabledPickupTransparence);
				else
					glColor4f(cm.getRed(),cm.getGreen(),cm.getBlue(),cm.getAlpha());
			}
		}
		Graphic g; 
		g = spr.getGraphic(e);
		float cx = e.getX();
		float cy = e.getY();
		float x = cx * isoCos;
		float y = cy * isoSin;
		float drawX = x - centerX + RogueCaveGUI.screenWidthf / 2f;
		float drawY = y - centerY + RogueCaveGUI.heightf / 2f;
		if (Resources.getSprite(sprite).isRotate()) {
			float cos = (float)Math.cos(Math.toRadians(e.getAngle()));
			float finalAngle = e.getAngle()+cos*e.getVerticalAngle(); 
			glTranslatef(drawX,RogueCaveGUI.heightf-drawY-e.getZ(),drawY / RogueCaveGUI.heightf);
			glRotatef(finalAngle,0,0,-1);
			drawSpriteGraphic(g,0f,RogueCaveGUI.heightf,0);
			glRotatef(-finalAngle,0,0,-1);
			glTranslatef(-drawX,-RogueCaveGUI.heightf+drawY+e.getZ(),-drawY / RogueCaveGUI.heightf);
		}
		else {
			if (drawX + g.getWidth() > 0 && drawX - g.getWidth() < RogueCaveGUI.screenWidthf
					&& drawY + g.getHeight() > 0 && drawY - g.getHeight() < RogueCaveGUI.heightf) {
				drawSprite(g, drawX, drawY, e.getZ());
			}
		}
		glColor4f(1f,1f,1f,1f);
	}

	public void drawSprite(Graphic g, float x, float y, float z) {
		drawSpriteGraphic(g, x, y + z, y / RogueCaveGUI.heightf);
	}

	public void drawItemGraphic(Graphic g, float x, float y, float depth) {
		g.getTexture().bind();
		glBegin(GL_QUADS);
		glTexCoord2f(g.getX1(), g.getY2());
		glVertex3f(x - g.getXCenter(),
				RogueCaveGUI.heightf - y - g.getYCenter() + g.getHeight(), depth);
		glTexCoord2f(g.getX1(), g.getY1());
		glVertex3f(x - g.getXCenter(), RogueCaveGUI.heightf - y - g.getYCenter(), depth);
		glTexCoord2f(g.getX2(), g.getY1());
		glVertex3f(x - g.getXCenter() + g.getWidth(),
				RogueCaveGUI.heightf - y - g.getYCenter(), depth);
		glTexCoord2f(g.getX2(), g.getY2());
		glVertex3f(x - g.getXCenter() + g.getWidth(),
				RogueCaveGUI.heightf - y - g.getYCenter() + g.getHeight(), depth);
		glEnd();
	}


	public void drawIsometricStandingGraphic(Graphic g, float x1, float y1,
			float x2, float y2, float h, float depth) {
		drawIsometricHalfStandingGraphic(g, x1, y1, x2, y2, h, depth);
		drawIsometricHalfFloatingGraphic(g, x1, y1, x2, y2, h, depth);
	}

	public void drawIsometricHalfStandingGraphic(Graphic g, float x1,
			float y1, float x2, float y2, float h, float depth) {
		g.getTexture().bind();

		glBegin(GL_TRIANGLES);
		glTexCoord2f(g.getX1(), g.getY2());
		glVertex3f(x1, RogueCaveGUI.heightf - y1, (y1) / RogueCaveGUI.heightf);
		glTexCoord2f(g.getX1(), g.getY1());
		glVertex3f(x1, RogueCaveGUI.heightf - y1 - h, (y1) / RogueCaveGUI.heightf);
		glTexCoord2f(g.getX2(), g.getY2());
		glVertex3f(x2, RogueCaveGUI.heightf - y2, (y2) / RogueCaveGUI.heightf);
		glEnd();
	}

	public void drawIsometricHalfFloatingGraphic(Graphic g, float x1,
			float y1, float x2, float y2, float h, float depth) {
		g.getTexture().bind();

		glBegin(GL_TRIANGLES);
		glTexCoord2f(g.getX1(), g.getY1());
		glVertex3f(x1, RogueCaveGUI.heightf - y1 - h, (y1) / RogueCaveGUI.heightf);
		glTexCoord2f(g.getX2(), g.getY1());
		glVertex3f(x2, RogueCaveGUI.heightf - y2 - h, (y2) / RogueCaveGUI.heightf);
		glTexCoord2f(g.getX2(), g.getY2());
		glVertex3f(x2, RogueCaveGUI.heightf - y2, (y2) / RogueCaveGUI.heightf);
		glEnd();
	}

	public void drawSpriteGraphic(Graphic g, float x, float y, float depth) {
		g.getTexture().bind();
		glBegin(GL_QUADS);
		glTexCoord2f(g.getX1(), g.getY2());
		glVertex3f(x - g.getXStanding(),
				RogueCaveGUI.heightf - y - g.getYStanding() + g.getHeight(), depth);
		glTexCoord2f(g.getX1(), g.getY1());
		glVertex3f(x - g.getXStanding(), RogueCaveGUI.heightf - y - g.getYStanding(), depth);
		glTexCoord2f(g.getX2(), g.getY1());
		glVertex3f(x - g.getXStanding() + g.getWidth(),
				RogueCaveGUI.heightf - y - g.getYStanding(), depth);
		glTexCoord2f(g.getX2(), g.getY2());
		glVertex3f(x - g.getXStanding() + g.getWidth(),
				RogueCaveGUI.heightf - y - g.getYStanding() + g.getHeight(), depth);
		glEnd();
	}

	public void drawSpriteGraphicCenter(Graphic g, float x, float y, float depth) {
		g.getTexture().bind();
		glBegin(GL_QUADS);
		glTexCoord2f(g.getX1(), g.getY2());
		glVertex3f(x - g.getXCenter()-2,RogueCaveGUI.heightf - y - g.getYCenter()-2 + g.getHeight(), depth);
		glTexCoord2f(g.getX1(), g.getY1());
		glVertex3f(x - g.getXCenter()-2, RogueCaveGUI.heightf - y - g.getYCenter()-2, depth);
		glTexCoord2f(g.getX2(), g.getY1());
		glVertex3f(x - g.getXCenter()-2 + g.getWidth(),RogueCaveGUI.heightf - y - g.getYCenter()-2, depth);
		glTexCoord2f(g.getX2(), g.getY2());
		glVertex3f(x - g.getXCenter()-2 + g.getWidth(),RogueCaveGUI.heightf - y - g.getYCenter()-2 + g.getHeight(), depth);
		glEnd();
	}


	public void drawIsometricGraphic(Graphic g, float x, float y, float halfW,
			float halfH, float depth) {
		g.getTexture().bind();
		glBegin(GL_QUADS);
		glTexCoord2f(g.getX1(), g.getY2());
		glVertex3f(x - halfW, RogueCaveGUI.heightf - y, depth);
		glTexCoord2f(g.getX1(), g.getY1());
		glVertex3f(x, RogueCaveGUI.heightf - y + halfH, depth);
		glTexCoord2f(g.getX2(), g.getY1());
		glVertex3f(x + halfW, RogueCaveGUI.heightf - y, depth);
		glTexCoord2f(g.getX2(), g.getY2());
		glVertex3f(x, RogueCaveGUI.heightf - y - halfH, depth);
		glEnd();
	}

	public abstract String getName();
	
	public void singlePlayerTalk(String string) {
		RogueCaveGUI.addMessage(new ScreenMessage("<"+RogueCaveGUI.config.name+"> "+string));
	}

}

