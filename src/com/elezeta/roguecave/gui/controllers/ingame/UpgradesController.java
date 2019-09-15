package com.elezeta.roguecave.gui.controllers.ingame;

import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glViewport;

import org.newdawn.slick.Color;

import com.elezeta.roguecave.entities.Stats;
import com.elezeta.roguecave.gui.ControlID;
import com.elezeta.roguecave.gui.GraphicID;
import com.elezeta.roguecave.gui.RogueCaveGUI;
import com.elezeta.roguecave.gui.StringID;
import com.elezeta.roguecave.gui.controllers.Controller;
import com.elezeta.roguecave.gui.controllers.menu.ConfirmCloseMenuController;
import com.elezeta.roguecave.gui.resources.Resources;
import com.elezeta.roguecave.inventory.Inventory;
import com.elezeta.roguecave.sprites.ColorMask;
import com.elezeta.roguecave.sprites.ColorSprite;
import com.elezeta.roguecave.sprites.SingleSprite;


public class UpgradesController extends Controller {

	private static UpgradesController singleton;
	public static Controller get() {
		if (singleton==null)
			singleton = new UpgradesController();
		return singleton;
	}
	
	protected UpgradesController() { }

	boolean oldUseSelf;
	
	@Override
	public void activate(Controller oldController) {
		super.activate(oldController);
		dragItem = null;
		overItem = null;
		originSlot = 0;
		dragging = false;
		oldUseSelf = RogueCaveGUI.game.getPlayer().getInventory().getUseSelf();
		RogueCaveGUI.game.getPlayer().getInventory().setUseSelf(false);
	}

	@Override
	public void run() {
		super.run();
		mouseGrab(false);
		manageInputMenu();
		updateTimeCounter(RogueCaveGUI.delta);
		updateCenter();
		updatePlayerCombos(RogueCaveGUI.game.getPlayer());
		updateOverItem();
		drawWorld();
		drawGrayGameScreen();
		drawUpgrades();
		drawPlayerTypeStats();
		drawItemStats();
		drawMainBar();		
		drawDragItem();
		drawInfo();
		drawMessagePad();
	}

	private void updateOverItem() {
		if (!dragging)
			overItem = RogueCaveGUI.game.getPlayer().getInventory().getItem(selectedSlot(RogueCaveGUI.mouseX,RogueCaveGUI.height-RogueCaveGUI.mouseY)); //TODO arreglar ese RogueCavePlaneGUI.height-, hacerlo más limpio
		else
			overItem = dragItem;
	}

	@Override
	public void requestClose() {
		super.requestClose();
		RogueCaveGUI.newController = ConfirmCloseMenuController.get();
	}

	@Override
	public void deactivate() {
		super.deactivate();
		RogueCaveGUI.game.getPlayer().getInventory().touchAll();
		dragging = false;
		RogueCaveGUI.game.getPlayer().getInventory().setUseSelf(oldUseSelf);
	}

	@Override
	public void keyPress(int eventKey) {
		super.keyPress(eventKey);
		if (eventKey==RogueCaveGUI.config.getKey(ControlID.UPGRADES)) { RogueCaveGUI.newController = IngameController.get(); }
		if (eventKey==RogueCaveGUI.config.getKey(ControlID.INVENTORY)) { RogueCaveGUI.newController = InventoryController.get(); }
		if (eventKey==RogueCaveGUI.config.getKey(ControlID.QUITFIXED)) { RogueCaveGUI.newController = IngameController.get(); }
		if (eventKey==RogueCaveGUI.config.getKey(ControlID.PAUSE)) { RogueCaveGUI.newController = PauseMenuController.get(); }
		if (eventKey == RogueCaveGUI.config.getKey(ControlID.SWITCHWEAPON1)) RogueCaveGUI.game.getPlayer().getInventory().setChosenCombo(0);
		if (eventKey == RogueCaveGUI.config.getKey(ControlID.SWITCHWEAPON2) && RogueCaveGUI.showCombos>=2) RogueCaveGUI.game.getPlayer().getInventory().setChosenCombo(1);
		if (eventKey == RogueCaveGUI.config.getKey(ControlID.SWITCHWEAPON3) && RogueCaveGUI.showCombos>=3) RogueCaveGUI.game.getPlayer().getInventory().setChosenCombo(2);
		if (eventKey == RogueCaveGUI.config.getKey(ControlID.SWITCHWEAPON4) && RogueCaveGUI.showCombos>=4) RogueCaveGUI.game.getPlayer().getInventory().setChosenCombo(3);
		if (eventKey == RogueCaveGUI.config.getKey(ControlID.SWITCHWEAPON5) && RogueCaveGUI.showCombos>=5) RogueCaveGUI.game.getPlayer().getInventory().setChosenCombo(4);
		if (eventKey == RogueCaveGUI.config.getKey(ControlID.SWITCHWEAPON6) && RogueCaveGUI.showCombos>=6) RogueCaveGUI.game.getPlayer().getInventory().setChosenCombo(5);
		if (eventKey == RogueCaveGUI.config.getKey(ControlID.SWITCHWEAPON7) && RogueCaveGUI.showCombos>=7) RogueCaveGUI.game.getPlayer().getInventory().setChosenCombo(6);
		if (eventKey == RogueCaveGUI.config.getKey(ControlID.SWITCHWEAPON8) && RogueCaveGUI.showCombos>=8) RogueCaveGUI.game.getPlayer().getInventory().setChosenCombo(7);
		if (eventKey == RogueCaveGUI.config.getKey(ControlID.SWITCHWEAPON9) && RogueCaveGUI.showCombos>=9) RogueCaveGUI.game.getPlayer().getInventory().setChosenCombo(8);
		if (eventKey == RogueCaveGUI.config.getKey(ControlID.SWITCHWEAPON0) && RogueCaveGUI.showCombos>=10) RogueCaveGUI.game.getPlayer().getInventory().setChosenCombo(9);
	}

	@Override
	public void mousePress(int x, int y) {
		super.mousePress(x,y);
		int selSlot = selectedSlot(x,y);
		if (selSlot != 0) {
			originSlot = selSlot;
			dragItem = RogueCaveGUI.game.getPlayer().getInventory().getItem(selSlot);
			dragItemFits = RogueCaveGUI.game.getPlayer().getInventory().fitsIn(dragItem);
			dragItemCompatible = RogueCaveGUI.game.getPlayer().getInventory().compatibleIn(dragItem);
			if (dragItem == null) {
				originSlot = 0;
			}
		}
		RogueCaveGUI.game.getPlayer().getInventory().touch(selSlot);
	}

	@Override
	public void mouseDrag(int x, int y) {
		super.mouseDrag(x,y);
		if (Math.abs(x-originX)+Math.abs(y-originY)>RogueCaveGUI.config.dragDistance)
			dragging = true;
		RogueCaveGUI.game.getPlayer().getInventory().touch(originSlot);
	}
	
	@Override
	public void mouseRelease(int x, int y) {
		super.mouseRelease(x,y);
		int selSlot = selectedSlot(x,y);
		if (!dragging) {
			if (clickDrop(originX,originY,x,y,RogueCaveGUI.screenWidth+4,154,72,36))
				keyPress(RogueCaveGUI.config.getKey(ControlID.SWITCHWEAPON1));
			if (clickDrop(originX,originY,x,y,RogueCaveGUI.screenWidth+4,190,72,36))
				keyPress(RogueCaveGUI.config.getKey(ControlID.SWITCHWEAPON2));
			if (clickDrop(originX,originY,x,y,RogueCaveGUI.screenWidth+4,226,72,36))
				keyPress(RogueCaveGUI.config.getKey(ControlID.SWITCHWEAPON3));
			if (clickDrop(originX,originY,x,y,RogueCaveGUI.screenWidth+4,262,72,36))
				keyPress(RogueCaveGUI.config.getKey(ControlID.SWITCHWEAPON4));
			if (clickDrop(originX,originY,x,y,RogueCaveGUI.screenWidth+4,298,72,36))
				keyPress(RogueCaveGUI.config.getKey(ControlID.SWITCHWEAPON5));
			if (clickDrop(originX,originY,x,y,RogueCaveGUI.screenWidth+4,334,72,36))
				keyPress(RogueCaveGUI.config.getKey(ControlID.SWITCHWEAPON6));
			if (clickDrop(originX,originY,x,y,RogueCaveGUI.screenWidth+4,370,72,36))
				keyPress(RogueCaveGUI.config.getKey(ControlID.SWITCHWEAPON7));
			if (clickDrop(originX,originY,x,y,RogueCaveGUI.screenWidth+4,406,72,36))
				keyPress(RogueCaveGUI.config.getKey(ControlID.SWITCHWEAPON8));
			if (clickDrop(originX,originY,x,y,RogueCaveGUI.screenWidth+4,442,72,36))
				keyPress(RogueCaveGUI.config.getKey(ControlID.SWITCHWEAPON9));
			if (clickDrop(originX,originY,x,y,RogueCaveGUI.screenWidth+4,478,72,36))
				keyPress(RogueCaveGUI.config.getKey(ControlID.SWITCHWEAPON0));

			if (clickDrop(originX,originY,x,y,RogueCaveGUI.screenWidth+4,110,36,36))
				keyPress(RogueCaveGUI.config.getKey(ControlID.INVENTORY));
			if (clickDrop(originX,originY,x,y,RogueCaveGUI.screenWidth+41,110,36,36))
				keyPress(RogueCaveGUI.config.getKey(ControlID.UPGRADES));
			RogueCaveGUI.game.getPlayer().getInventory().touch(selSlot);
			if (overItem != null && originSlot==selSlot) {
				RogueCaveGUI.game.getPlayer().getInventory().replaceItem(overItem);
			}
		}	
		if (dragging && dragItem != null && originSlot != selSlot)
			RogueCaveGUI.game.getPlayer().getInventory().move(originSlot,selSlot);			
		originSlot = 0;
		dragging = false;
		dragItem = null;
		dragItemFits = null;
		dragItemCompatible = null;
	}
	
	
	@Override
	public void wheel(int delta) {
		super.wheel(delta);
		RogueCaveGUI.game.getPlayer().getInventory().setChosenCombo((RogueCaveGUI.showCombos+(RogueCaveGUI.game.getPlayer().getInventory().getChosenCombo() - delta))%RogueCaveGUI.showCombos);
	}
	
	private int selectedSlot(int cx,int cy) {
		{
			int x = cx;
			int y = cy;
			int stw = RogueCaveGUI.screenWidth+RogueCaveGUI.config.inventoryBoxX-RogueCaveGUI.menuRightMargin;
			int sth = RogueCaveGUI.config.inventoryBoxY-47+RogueCaveGUI.menuTopMargin;

			for (int i = 0;i < 2;i++)
				for (int j = 0;j < 16;j++)
					if (x>=stw+j*36 && x<stw+(j+1)*36 && y>=sth+i*36 && y<sth+(i+1)*36)
						return Inventory.INVARMOR+1+i*16+j;
			
			sth += 36*2+8;
			for (int i = 0;i < 2;i++)
				for (int j = 0;j < 16;j++)
					if (x>=stw+j*36 && x<stw+(j+1)*36 && y>=sth+i*36 && y<sth+(i+1)*36)
						return Inventory.INVWEAPONS+1+i*16+j;

			sth += 36*2+8;
			for (int i = 0;i < 2;i++)
				for (int j = 0;j < 16;j++)
					if (x>=stw+j*36 && x<stw+(j+1)*36 && y>=sth+i*36 && y<sth+(i+1)*36)
						return Inventory.INVSPELLS+1+i*16+j;

			sth += 36*2+8;
			for (int i = 0;i < 2;i++)
				for (int j = 0;j < 16;j++)
					if (x>=stw+j*36 && x<stw+(j+1)*36 && y>=sth+i*36 && y<sth+(i+1)*36)
						return Inventory.INVMISC+1+i*16+j;
		}
		
		{
			int x = cx-RogueCaveGUI.screenWidth;
			int y = cy;
			if (x >= 4 && x <= 76) {
				int pair = 0;
				if (x<40)
					pair = 1;
				else
					pair = 2;
				if (y >= 154 && y <= 154 + 35)
					return Inventory.COMBOS+0*10+pair;
				if (y >= 190 && y <= 190 + 35)
					return Inventory.COMBOS+1*10+pair;
				if (y >= 226 && y <= 226 + 35)
					return Inventory.COMBOS+2*10+pair;
				if (y >= 262 && y <= 262 + 35)
					return Inventory.COMBOS+3*10+pair;
				if (y >= 298 && y <= 298 + 35)
					return Inventory.COMBOS+4*10+pair;
				if (y >= 334 && y <= 334 + 35)
					return Inventory.COMBOS+5*10+pair;
				if (y >= 370 && y <= 370 + 35)
					return Inventory.COMBOS+6*10+pair;
				if (y >= 406 && y <= 406 + 35)
					return Inventory.COMBOS+7*10+pair;
				if (y >= 442 && y <= 442 + 35)
					return Inventory.COMBOS+8*10+pair;
				if (y >= 478 && y <= 478 + 35)
					return Inventory.COMBOS+9*10+pair;
			}
			
			if (x >= 4 && x <= 4 + 35 && y >= 519 && y <= 519 + 35) {
				return Inventory.COMBOSSELF+1;
			}
			if (x >= 41 && x <= 41 + 35 && y >= 519 && y <= 519 + 35) {
				return Inventory.COMBOSSELF+2;
			}
			if (x >= 4 && x <= 4 + 35 && y >= 556 && y <= 556 + 35) {
				return Inventory.COMBOSSELF+3;
			}
			if (x >= 41 && x <= 41 + 35 && y >= 556 && y <= 556 + 35) {
				return Inventory.COMBOSSELF+4;
			}	
		}
		
		{
			int stw = RogueCaveGUI.screenWidth+RogueCaveGUI.config.equipmentBoxX-RogueCaveGUI.menuRightMargin;
			int sth = RogueCaveGUI.config.equipmentBoxY-47+RogueCaveGUI.menuTopMargin;

			int x = cx-stw;
			int y = cy-sth;
			
			if (x >= 4 && x < 4+36 && y >= 4 && y < 4+36)
				return Inventory.AMULET;
			if (x >= 42 && x < 42+36 && y >= 4 && y < 4+36)
				return Inventory.HELMET;
			if (x >= 80 && x < 80+36 && y >= 42 && y < 42+36)
				return Inventory.ARMS;
			if (x >= 42 && x < 42+36 && y >= 42 && y < 42+36)
				return Inventory.ARMOR;
			if (x >= 42 && x < 42+36 && y >= 80 && y < 80+36)
				return Inventory.LEGS;
			if (x >= 42 && x < 42+36 && y >= 118 && y < 118+36)
				return Inventory.BOOTS;
			if (x >= 4 && x < 4+36 && y >= 80 && y < 80+36)
				return Inventory.RING1;
			if (x >= 80 && x < 80+36 && y >= 4 && y < 80+36)
				return Inventory.RING2;
		}
		
		{
			int stw = RogueCaveGUI.screenWidth+RogueCaveGUI.config.trashX-RogueCaveGUI.menuRightMargin;
			int sth = RogueCaveGUI.config.trashY-47+RogueCaveGUI.menuTopMargin;

			int x = cx-stw;
			int y = cy-sth;
			
			if (x >= 0 && x <= 25 && y >= 0 && y <= 40)
				return Inventory.TRASH;
		}
		return 0;
	}

	private void drawUpgrades() {
		glViewport(0, 0, RogueCaveGUI.screenWidth, RogueCaveGUI.height);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0,RogueCaveGUI.screenWidth,RogueCaveGUI.height,0, 1.5, -1.5);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		glDisable(GL_DEPTH_TEST);
		
		int stw = RogueCaveGUI.screenWidth+RogueCaveGUI.config.inventoryBoxX-RogueCaveGUI.menuRightMargin;
		int sth = RogueCaveGUI.config.inventoryBoxY-47+RogueCaveGUI.menuTopMargin;
		for (int i = 0;i < 2;i++) {
			for (int j = 0;j < 16;j++)
				drawInventorySlot(stw+j*36,sth+i*36,Inventory.INVARMOR+1+i*16+j,GraphicID.naught);
		}
		drawTab(stw-61,sth+11,GraphicID.GUITabArmor,1);
		
		sth += 36*2+8;
		for (int i = 0;i < 2;i++) {
			for (int j = 0;j < 16;j++)
				drawInventorySlot(stw+j*36,sth+i*36,Inventory.INVWEAPONS+1+i*16+j,GraphicID.naught);
		}
		drawTab(stw-61,sth+11,GraphicID.GUITabWeapons,1);

		sth += 36*2+8;
		for (int i = 0;i < 2;i++) {
			for (int j = 0;j < 16;j++)
				drawInventorySlot(stw+j*36,sth+i*36,Inventory.INVSPELLS+1+i*16+j,GraphicID.naught);
		}
		drawTab(stw-61,sth+11,GraphicID.GUITabSpells,1);

		sth += 36*2+8;
		for (int i = 0;i < 2;i++) {
			for (int j = 0;j < 16;j++)
				drawInventorySlot(stw+j*36,sth+i*36,Inventory.INVMISC+1+i*16+j,GraphicID.naught);
		}
		
		drawTab(stw-61,sth+11,GraphicID.GUITabMisc,1);

		drawSpriteGraphic(Resources.getGraphic(GraphicID.GUITrash),RogueCaveGUI.screenWidth+RogueCaveGUI.config.trashX-RogueCaveGUI.menuRightMargin,RogueCaveGUI.height-RogueCaveGUI.config.trashY+47-RogueCaveGUI.menuTopMargin,0);
	}

	private void drawInventorySlot(int x,int y,int slot,GraphicID def) {
		drawSpriteGraphic(Resources.getGraphic(GraphicID.GUIButton), x, RogueCaveGUI.height - y, 0);
		fillNewColor(slot, x, y);
		drawItem(RogueCaveGUI.game.getPlayer().getInventory().getItem(slot),x+18,y+18,def,slot);
	}

	private void drawPlayerTypeStats() {
		int stw = RogueCaveGUI.screenWidth+RogueCaveGUI.config.playerUpgradeStatsBoxX-RogueCaveGUI.menuRightMargin;
		int sth = RogueCaveGUI.config.playerUpgradeStatsBoxY-47+RogueCaveGUI.menuTopMargin;
		glViewport(stw,RogueCaveGUI.height-sth-158,349,158);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, 349,158, 0, 1.5, -1.5);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		drawSpriteGraphic(Resources.getGraphic(GraphicID.GUILongStatsBox), 0,RogueCaveGUI.height, 0);
		drawEntityFront(RogueCaveGUI.game.getPlayer(),20,RogueCaveGUI.height-43);
		drawTextLeft(Resources.getInfoFont(), 42,RogueCaveGUI.height-8,RogueCaveGUI.language.getString(StringID.PLAYERSTRING,RogueCaveGUI.game.getPlayer().getName(),RogueCaveGUI.language.getString(RogueCaveGUI.game.getPlayer().getProfession())), Color.white);
		drawTextLeft(Resources.getInfoFont(), 42,RogueCaveGUI.height-21,RogueCaveGUI.language.getString(StringID.PLAYERLEVEL,""+RogueCaveGUI.game.getPlayer().getLevel()), Color.white);
		
		Stats sNew = null;
		if (overItem != null)
			sNew = RogueCaveGUI.game.getPlayer().getStatsWithReplace(overItem);
		drawStats(RogueCaveGUI.game.getPlayer().getStats(),sNew,6,52,true,stw,sth);
		
		//TODOUP show basics
	}

	private void drawItemStats() {
		
		int stw = RogueCaveGUI.screenWidth+RogueCaveGUI.config.itemStatsBoxX-RogueCaveGUI.menuRightMargin;
		int sth = RogueCaveGUI.config.itemStatsBoxY-47+RogueCaveGUI.menuTopMargin;
		glViewport(stw,RogueCaveGUI.height-sth-158,220,158);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, 220,158, 0, 1.5, -1.5);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		drawSpriteGraphic(Resources.getGraphic(GraphicID.GUIStatsBox), 0,RogueCaveGUI.height, 0);

		if (overItem == null)
			return;

		drawItem(overItem,20,27,GraphicID.naught,-1);
		drawTextLeft(Resources.getInfoFont(), 42,RogueCaveGUI.height-12,RogueCaveGUI.language.getString(overItem.getItemID()), Color.white);
		StringID sId = null;
		String provides = RogueCaveGUI.language.getString(overItem.getProvidesAmmoID());
		String requiresP = RogueCaveGUI.language.getString(overItem.getRequiresAmmoID(),true);
		String itemName = RogueCaveGUI.language.getString(overItem.getItemID());
		
		if (overItem.getProvidesAmmoID()!=null && overItem.getRequiresAmmoID()==overItem.getProvidesAmmoID())
			sId = StringID.USEABLEASAMMO;
		else if (overItem.getProvidesAmmoID()!=null)
			sId = StringID.USEABLEASAMMO;
		else if (overItem.getRequiresAmmoID()!=null)
			sId = StringID.REQUIRESAMMO;

		if (sId==StringID.USEABLEASAMMO && (" "+itemName+" ").toLowerCase().contains(provides.toLowerCase()))
			sId=StringID.PROVIDESAMMO;
		
		if (sId == StringID.REQUIRESAMMO)
			drawTextLeft(Resources.getInfoFont(), 42,RogueCaveGUI.height-25,RogueCaveGUI.language.getString(sId,requiresP), Color.white);
		else if (sId == StringID.PROVIDESAMMO)
			drawTextLeft(Resources.getInfoFont(), 42,RogueCaveGUI.height-25,RogueCaveGUI.language.getString(sId), Color.white);
		else if (sId == StringID.USEABLEASAMMO)
			drawTextLeft(Resources.getInfoFont(), 42,RogueCaveGUI.height-25,RogueCaveGUI.language.getString(sId,provides), Color.white);
		
		drawStats(overItem.getStats(),null,6,52,false,stw,sth);

	}
	
	private void drawStats(Stats s,Stats sNew,int x,int y,boolean showZeros,float stw,float sth) {

		if (sNew == null)
			sNew = s;
		drawStat(GraphicID.GUIPlayerDefense,x,y,(int)s.getDefense(),(int)sNew.getDefense(),showZeros,stw,sth,RogueCaveGUI.language.getString(StringID.PLAYERDEFENSE));
		drawStat(GraphicID.GUIPlayerAttack,x+71,y,(int)s.getAttack(),(int)sNew.getAttack(),showZeros,stw,sth,RogueCaveGUI.language.getString(StringID.PLAYERATTACK));
		drawStat(GraphicID.GUIPlayerRange,x+142,y,(int)s.getRange(),(int)sNew.getRange(),showZeros,stw,sth,RogueCaveGUI.language.getString(StringID.PLAYERRANGE));

		drawStat(GraphicID.GUIPlayerHP,x,y+18,(int)s.getMaxHp(),(int)sNew.getMaxHp(),showZeros,stw,sth,RogueCaveGUI.language.getString(StringID.PLAYERHP));
		drawStat(GraphicID.GUIPlayerHPRegen,x+71,y+18,(int)s.getHpRegen(),(int)sNew.getHpRegen(),showZeros,stw,sth,RogueCaveGUI.language.getString(StringID.PLAYERHPREGEN));
		drawStat(GraphicID.GUIPlayerHPSteal,x+142,y+18,(int)s.getHpSteal(),(int)sNew.getHpSteal(),showZeros,stw,sth,RogueCaveGUI.language.getString(StringID.PLAYERHPSTEAL));

		drawStat(GraphicID.GUIPlayerMP,x,y+36,(int)s.getMaxMp(),(int)sNew.getMaxMp(),showZeros,stw,sth,RogueCaveGUI.language.getString(StringID.PLAYERMP));
		drawStat(GraphicID.GUIPlayerMPRegen,x+71,y+36,(int)s.getMpRegen(),(int)sNew.getMpRegen(),showZeros,stw,sth,RogueCaveGUI.language.getString(StringID.PLAYERMPREGEN));
		drawStat(GraphicID.GUIPlayerMPSteal,x+142,y+36,(int)s.getMpSteal(),(int)sNew.getMpSteal(),showZeros,stw,sth,RogueCaveGUI.language.getString(StringID.PLAYERMPSTEAL));

		
		drawStat(GraphicID.GUIPlayerSpeed,x,y+54,(int)s.getSpeed(),(int)sNew.getSpeed(),showZeros,stw,sth,RogueCaveGUI.language.getString(StringID.PLAYERSPEED));
		drawStat(GraphicID.GUIPlayerRecovery,x+71,y+54,(int)s.getRecovery(),(int)sNew.getRecovery(),showZeros,stw,sth,RogueCaveGUI.language.getString(StringID.PLAYERRECOVERY));
		drawStat(GraphicID.GUIPlayerAccuracy,x+142,y+54,(int)s.getAccuracy(),(int)sNew.getAccuracy(),showZeros,stw,sth,RogueCaveGUI.language.getString(StringID.PLAYERACCURACY));

	}

	private void drawStat(GraphicID graphic, int x, int y, int curVal,int newVal,boolean showZeros,float stw,float sth,String descString) {
		int difVal = newVal-curVal;
		if (!showZeros && curVal == 0 && difVal == 0)
			glColor4f(1f,1f,1f,0.3f);

		drawSpriteGraphic(Resources.getGraphic(graphic), x,RogueCaveGUI.height-y, 0);
		glColor4f(1f,1f,1f,1f);
		
		if (showZeros || curVal != 0 || difVal != 0) {
			if (difVal > 0)
				drawTextLeft(Resources.getInfoFont(), x+18,RogueCaveGUI.height-y,""+curVal+"+"+difVal,new Color(0.5f,1f,0.5f,1f));
			else if (difVal < 0)
				drawTextLeft(Resources.getInfoFont(), x+18,RogueCaveGUI.height-y,""+curVal+"-"+(-difVal),new Color(1f,0.5f,0.5f,1f));
			drawTextLeft(Resources.getInfoFont(), x+18,RogueCaveGUI.height-y,""+curVal,Color.white);
		}
		if (RogueCaveGUI.mouseX>stw+x && RogueCaveGUI.mouseX<stw+x+60 && RogueCaveGUI.height-RogueCaveGUI.mouseY>sth+y+1 && RogueCaveGUI.height-RogueCaveGUI.mouseY<sth+y+15) {
			drawTextRight(Resources.getInfoFont(), 214,RogueCaveGUI.height-34,descString, Color.white);
		}

	}

	private void drawTab(int x,int y,GraphicID graphic,int n) {
		drawSpriteGraphic(Resources.getGraphic(GraphicID.GUITab), x, RogueCaveGUI.height - y, 0);
		drawSpriteGraphic(Resources.getGraphic(graphic), x, RogueCaveGUI.height - y, 0);
	}

	private void drawDragItem() {
		glViewport(0, 0, RogueCaveGUI.width, RogueCaveGUI.height);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, RogueCaveGUI.width, RogueCaveGUI.height, 0, 1.5, -1.5);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		glDisable(GL_DEPTH_TEST);
		if (dragItem != null && dragging) {
			glColor4f(1f,1f,1f,1f);
			if (ColorSprite.class.isInstance(dragItem.getInventorySprite())) {
				ColorMask cm = ((ColorSprite) dragItem.getInventorySprite()).getColor();
				glColor4f(cm.getRed(),cm.getGreen(),cm.getBlue(),cm.getAlpha());
			}
			if (SingleSprite.class.isInstance(dragItem.getInventorySprite()))
				drawSpriteGraphicCenter(Resources.getSprite(((SingleSprite)dragItem.getInventorySprite()).getSpriteID()).getGraphic(RogueCaveGUI.moveAnimationCounter),RogueCaveGUI.mouseX, RogueCaveGUI.mouseY, -1f);
			if (ColorSprite.class.isInstance(dragItem.getInventorySprite())) {
				glColor4f(1f,1f,1f,1f);
			}
			//no-composite
			if (dragItem.isStackable()) {
				drawTextRight(Resources.getSmallFont(), RogueCaveGUI.mouseX+16, RogueCaveGUI.mouseY - 6,"x" + dragItem.getQuantity(), Color.black);
				drawTextRight(Resources.getSmallFont(), RogueCaveGUI.mouseX+17, RogueCaveGUI.mouseY - 5,"x" + dragItem.getQuantity(),Resources.getTransparentWhiteColor());
			}
		}
	}
	
	@Override
	public String getName() { return "Upgrades"; }

	@Override
	public void console(String string) {
		singlePlayerTalk(string);
	}

}

