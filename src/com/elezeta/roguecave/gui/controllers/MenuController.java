package com.elezeta.roguecave.gui.controllers;

import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glViewport;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

import com.elezeta.roguecave.Constants;
import com.elezeta.roguecave.gui.ControlID;
import com.elezeta.roguecave.gui.GraphicID;
import com.elezeta.roguecave.gui.RogueCaveGUI;
import com.elezeta.roguecave.gui.StringID;
import com.elezeta.roguecave.gui.menu.Menu;
import com.elezeta.roguecave.gui.menu.MenuButton;
import com.elezeta.roguecave.gui.menu.MenuComponent;
import com.elezeta.roguecave.gui.menu.MenuGraphic;
import com.elezeta.roguecave.gui.menu.MenuGameButton;
import com.elezeta.roguecave.gui.menu.MenuScroll;
import com.elezeta.roguecave.gui.menu.MenuText;
import com.elezeta.roguecave.gui.menu.MenuTextButton;
import com.elezeta.roguecave.gui.resources.Resources;

public abstract class MenuController extends Controller {

	public Menu menu = new Menu();
	
	private int NONE = 0;
	private int SCROLLUP = 1;
	private int SCROLLDOWN = 2;
	private int SCROLLBAR = 3;
	protected int selectedButton = 0;
	private MenuScroll selectedScroll = null;
	private int selectedScrollItem = NONE;

	@Override
	public void activate(Controller oldController) {
		super.activate(oldController);
		menu = new Menu();
		selectedButton = 0;
		menuRotate(menu,-1);
		menuRotate(menu,1);
		dragging = false;
	}

	@Override
	public void keyPress(int eventKey) {
		super.keyPress(eventKey);
		menuKeys(menu,eventKey);
	}

	@Override
	public void mousePress(int x, int y) {
		super.mousePress(x,y);
		menuClick(menu,x,y);
	}

	@Override
	public void mouseDrag(int x, int y) {
		super.mouseDrag(x,y);
		if (Math.abs(x-originX)+Math.abs(y-originY)>RogueCaveGUI.config.dragDistance) {
			dragging = true;
		}
		menuScrollClick(menu,x,y);
	}
	
	@Override
	public void mouseMove(int x, int y) {
		super.mouseMove(x,y);
		menuHover(menu,x,y);
	}

	@Override
	public void run() {
		super.run();
		mouseGrab(false);
		manageInputMenu();
		if (Display.isVisible()) {
			updateCenter();
			updatePlayerCombos(RogueCaveGUI.game.getPlayer());
			drawWorld();
			drawMainBar();		
			drawGrayScreen();
			drawMenu(menu);
			drawInfo();
			drawMessagePad();
		}
	}

	@Override
	public void fix() {
		super.fix();
		manageInputMenu();
		menuHover(menu,RogueCaveGUI.mouseX,RogueCaveGUI.height-RogueCaveGUI.mouseY);
	}

	@Override
	public void wheel(int delta) {
		super.wheel(delta);

		if (!dragging) {
			for (int i = 0;i < menu.getComponents().size();i++) {
				MenuComponent mc = menu.getComponents().get(i);
				if (MenuScroll.class.isInstance(mc)) {
					MenuScroll ms = (MenuScroll)mc;
	
					if (menuOver((RogueCaveGUI.mouseX-RogueCaveGUI.width/2),(RogueCaveGUI.mouseY-RogueCaveGUI.height/2),ms.getX(),ms.getY(),ms.getW(),ms.getH())) {
						if (delta>0)
							ms.scroll(-30);
						else
							ms.scroll(30);
					}
				}
			}
		}

	}		

	public void drawMenu(Menu menu) {
		for (int i = 0;i < menu.getComponents().size();i++) {
			MenuComponent mc = menu.getComponents().get(i);
			glMatrixMode(GL_PROJECTION);
			glLoadIdentity();
			glOrtho(0,RogueCaveGUI.width,RogueCaveGUI.height,0, 1.5, -1.5);
			glMatrixMode(GL_MODELVIEW);
			glLoadIdentity();
			glDisable(GL_DEPTH_TEST);
			glViewport(0, 0, RogueCaveGUI.width, RogueCaveGUI.height);
			drawMenuComponent(mc,0,0);
		}
	}

	public void drawMenuComponent(MenuComponent mc,int offX,int offY) {
		if (MenuText.class.isInstance(mc)) {
			MenuText mt = (MenuText)mc;
			drawTextCenterV(mt.getFont(),offX+RogueCaveGUI.width/2+mc.getX()-1,RogueCaveGUI.height-(offY+RogueCaveGUI.height/2+mc.getY()+1),mt.getString(),Color.black);
			drawTextCenterV(mt.getFont(),offX+RogueCaveGUI.width/2+mc.getX(),RogueCaveGUI.height-(offY+RogueCaveGUI.height/2+mc.getY()),mt.getString(),Color.white);
		}
		if (MenuButton.class.isInstance(mc)) {
			MenuButton button = (MenuButton)mc;
			int x = offX+RogueCaveGUI.width/2+button.getX();
			int y = RogueCaveGUI.height-(offY+RogueCaveGUI.height/2+button.getY());
			if (!button.isEnabled())
				drawSpriteGraphic(Resources.getGraphic(GraphicID.GUIDisabledMenuButton), x, y, 0);
			else if (selectedButton == button.getNumber())
				drawSpriteGraphic(Resources.getGraphic(GraphicID.GUISelectedMenuButton), x, y, 0);
			else
				drawSpriteGraphic(Resources.getGraphic(GraphicID.GUIMenuButton), x, y, 0);
			if (MenuTextButton.class.isInstance(mc)) {
				String text = ((MenuTextButton)button).getString();
				if (!button.isEnabled())
					drawTextCenterV(Resources.getMenuFont(), x+183, y-23,text,Resources.getLightGrayColor());
				else {
					drawTextCenterV(Resources.getMenuFont(), x+182, y-22,text,Color.black);
					drawTextCenterV(Resources.getMenuFont(), x+183, y-21,text,Color.white);
				}
			}
			if (MenuGameButton.class.isInstance(mc)) {
				MenuGameButton mr = (MenuGameButton)mc;
				drawEntityFront(mr.getGame().getPlayer(),offX+RogueCaveGUI.width/2+mc.getX()+20,RogueCaveGUI.height-(offY+RogueCaveGUI.height/2+mc.getY()+41));
				
				drawTextLeft(Resources.getInfoFont(),offX+RogueCaveGUI.width/2+mc.getX()+41,RogueCaveGUI.height-(offY+RogueCaveGUI.height/2+mc.getY()+7),RogueCaveGUI.language.getString(StringID.PLAYERSTRING,mr.getGame().getPlayer().getName(),RogueCaveGUI.language.getString(RogueCaveGUI.game.getPlayer().getProfession())), Color.black);
				drawTextLeft(Resources.getInfoFont(),offX+RogueCaveGUI.width/2+mc.getX()+42,RogueCaveGUI.height-(offY+RogueCaveGUI.height/2+mc.getY()+6),RogueCaveGUI.language.getString(StringID.PLAYERSTRING,mr.getGame().getPlayer().getName(),RogueCaveGUI.language.getString(RogueCaveGUI.game.getPlayer().getProfession())), Color.white);
				drawTextLeft(Resources.getInfoFont(),offX+RogueCaveGUI.width/2+mc.getX()+41,RogueCaveGUI.height-(offY+RogueCaveGUI.height/2+mc.getY()+20),RogueCaveGUI.language.getString(StringID.PLAYERLEVEL,""+mr.getGame().getPlayer().getLevel()), Color.black);
				drawTextLeft(Resources.getInfoFont(),offX+RogueCaveGUI.width/2+mc.getX()+42,RogueCaveGUI.height-(offY+RogueCaveGUI.height/2+mc.getY()+19),RogueCaveGUI.language.getString(StringID.PLAYERLEVEL,""+mr.getGame().getPlayer().getLevel()), Color.white);

				drawTextRight(Resources.getInfoFont(),offX+RogueCaveGUI.width/2+mc.getX()+361,RogueCaveGUI.height-(offY+RogueCaveGUI.height/2+mc.getY()+7),RogueCaveGUI.language.getString(StringID.WORLDNUMBER,""+mr.getGame().getNumber()), Color.black);
				drawTextRight(Resources.getInfoFont(),offX+RogueCaveGUI.width/2+mc.getX()+362,RogueCaveGUI.height-(offY+RogueCaveGUI.height/2+mc.getY()+6),RogueCaveGUI.language.getString(StringID.WORLDNUMBER,""+mr.getGame().getNumber()), Color.white);
				drawTextRight(Resources.getInfoFont(),offX+RogueCaveGUI.width/2+mc.getX()+361,RogueCaveGUI.height-(offY+RogueCaveGUI.height/2+mc.getY()+20),RogueCaveGUI.language.getString(mr.getGame().getGameID()), Color.black);
				drawTextRight(Resources.getInfoFont(),offX+RogueCaveGUI.width/2+mc.getX()+362,RogueCaveGUI.height-(offY+RogueCaveGUI.height/2+mc.getY()+19),RogueCaveGUI.language.getString(mr.getGame().getGameID()), Color.white);
			}
		}
		if (MenuGraphic.class.isInstance(mc)) {
			MenuGraphic mg = (MenuGraphic)mc;
			drawSpriteGraphic(Resources.getGraphic(mg.getGraphic()),offX+RogueCaveGUI.width/2+mc.getX(), RogueCaveGUI.height-(offY+RogueCaveGUI.height/2+mc.getY()),0);
		}
		if (MenuScroll.class.isInstance(mc)) {
			MenuScroll ms = (MenuScroll)mc;
			glMatrixMode(GL_PROJECTION);
			glLoadIdentity();
			glOrtho(RogueCaveGUI.width/2+ms.getX(),RogueCaveGUI.width/2+ms.getX()+ms.getW(),RogueCaveGUI.height/2+ms.getH()+ms.getY(),RogueCaveGUI.height/2+ms.getY(),1.5,-1.5);
			glMatrixMode(GL_MODELVIEW);
			glViewport(RogueCaveGUI.width/2+ms.getX(),RogueCaveGUI.height/2-ms.getH()-ms.getY(),ms.getW(),ms.getH());
				
			glColor4f(1f,1f,1f,0.4f);
			drawSquare(Resources.getGraphic(GraphicID.GUIBlackColor), RogueCaveGUI.width/2+ms.getX(), RogueCaveGUI.height/2+ms.getY(), ms.getW()-RogueCaveGUI.config.scrollSize-ms.getPadding(),ms.getH());
			glColor4f(1f,1f,1f,1f);
			
			if (Math.abs(menuScrollBarShowing(ms)-1f)>=Constants.EPS) {

				if (selectedScroll==ms && selectedScrollItem==SCROLLUP)
					drawSpriteGraphic(Resources.getGraphic(GraphicID.GUIScrollUpLight),offX+RogueCaveGUI.width/2+mc.getX()+ms.getW()-RogueCaveGUI.config.scrollSize,RogueCaveGUI.height-(offY+RogueCaveGUI.height/2+mc.getY()),0);
				else
					drawSpriteGraphic(Resources.getGraphic(GraphicID.GUIScrollUp),offX+RogueCaveGUI.width/2+mc.getX()+ms.getW()-RogueCaveGUI.config.scrollSize,RogueCaveGUI.height-(offY+RogueCaveGUI.height/2+mc.getY()),0);
				if (selectedScroll==ms && selectedScrollItem==SCROLLDOWN)
					drawSpriteGraphic(Resources.getGraphic(GraphicID.GUIScrollDownLight),offX+RogueCaveGUI.width/2+mc.getX()+ms.getW()-RogueCaveGUI.config.scrollSize,RogueCaveGUI.height-(offY+RogueCaveGUI.height/2+mc.getY()+ms.getH()-RogueCaveGUI.config.scrollSize),0);
				else
					drawSpriteGraphic(Resources.getGraphic(GraphicID.GUIScrollDown),offX+RogueCaveGUI.width/2+mc.getX()+ms.getW()-RogueCaveGUI.config.scrollSize,RogueCaveGUI.height-(offY+RogueCaveGUI.height/2+mc.getY()+ms.getH()-RogueCaveGUI.config.scrollSize),0);
				drawVBar(Resources.getGraphic(GraphicID.GUIScrollBackground),offX+RogueCaveGUI.width/2+mc.getX()+ms.getW()-RogueCaveGUI.config.scrollSize,RogueCaveGUI.height-(offY+RogueCaveGUI.height/2+mc.getY()+RogueCaveGUI.config.scrollSize),RogueCaveGUI.config.scrollSize,ms.getH()-RogueCaveGUI.config.scrollSize*2,100,100);


				int barHeight = menuScrollBarHeight(ms);
				int barPos = menuScrollBarPos(ms);
				
				if (selectedScroll==ms && selectedScrollItem==SCROLLBAR) {
					drawVBar(Resources.getGraphic(GraphicID.GUIScrollBarLight),offX+RogueCaveGUI.width/2+mc.getX()+ms.getW()-RogueCaveGUI.config.scrollSize,RogueCaveGUI.height-(offY+RogueCaveGUI.height/2+mc.getY()+barPos),RogueCaveGUI.config.scrollSize,barHeight,100,100);
					drawSpriteGraphic(Resources.getGraphic(GraphicID.GUIScrollLineLight),offX+RogueCaveGUI.width/2+mc.getX()+ms.getW()-RogueCaveGUI.config.scrollSize,RogueCaveGUI.height-(offY+RogueCaveGUI.height/2+mc.getY()+barPos),0);
					drawSpriteGraphic(Resources.getGraphic(GraphicID.GUIScrollLineLight),offX+RogueCaveGUI.width/2+mc.getX()+ms.getW()-RogueCaveGUI.config.scrollSize,RogueCaveGUI.height-(offY+RogueCaveGUI.height/2+mc.getY()+barPos+barHeight),0);
				}
				else {
					drawVBar(Resources.getGraphic(GraphicID.GUIScrollBar),offX+RogueCaveGUI.width/2+mc.getX()+ms.getW()-RogueCaveGUI.config.scrollSize,RogueCaveGUI.height-(offY+RogueCaveGUI.height/2+mc.getY()+barPos),RogueCaveGUI.config.scrollSize,barHeight,100,100);
					drawSpriteGraphic(Resources.getGraphic(GraphicID.GUIScrollTop),offX+RogueCaveGUI.width/2+mc.getX()+ms.getW()-RogueCaveGUI.config.scrollSize,RogueCaveGUI.height-(offY+RogueCaveGUI.height/2+mc.getY()+barPos),0);
					drawSpriteGraphic(Resources.getGraphic(GraphicID.GUIScrollBottom),offX+RogueCaveGUI.width/2+mc.getX()+ms.getW()-RogueCaveGUI.config.scrollSize,RogueCaveGUI.height-(offY+RogueCaveGUI.height/2+mc.getY()+barPos+barHeight),0);
				}
				
			}


			for (int i = 0;i < ms.getComponents().size();i++) {
				MenuComponent mc2 = ms.getComponents().get(i);
				drawMenuComponent(mc2,ms.getX(),ms.getY()-ms.getCurrentPosition());
			}
		}
	}

	public void menuRotate(Menu menu,int delta) {
		int n = menu.getButtonSize();
		if (n==0)
			return;
		do {
			selectedButton=(selectedButton+delta+n)%n;
			if (menu.getButton(selectedButton) == null)
				return;
		} while (!menu.getButton(selectedButton).isEnabled());
		menu.show(menu.getButton(selectedButton));
	}
	
	public void menuKeys(Menu menu,int eventKey) {
		if (eventKey==RogueCaveGUI.config.getKey(ControlID.UP) || eventKey==RogueCaveGUI.config.getKey(ControlID.UPFIXED)) { menuRotate(menu,-1); }
		if (eventKey==RogueCaveGUI.config.getKey(ControlID.DOWN) || eventKey==RogueCaveGUI.config.getKey(ControlID.DOWNFIXED)) { menuRotate(menu,1); }
		if (eventKey==RogueCaveGUI.config.getKey(ControlID.ENTERFIXED)) { menu.getButton(selectedButton).getActuator().run(); }
	}

	public void menuKeysSoft(Menu menu,int eventKey) {
		if (eventKey==RogueCaveGUI.config.getKey(ControlID.UPFIXED)) { menuRotate(menu,-1); }
		if (eventKey==RogueCaveGUI.config.getKey(ControlID.DOWNFIXED)) { menuRotate(menu,1); }
		if (eventKey==RogueCaveGUI.config.getKey(ControlID.ENTERFIXED)) { menu.getButton(selectedButton).getActuator().run(); }
	}
	
	public void menuClick(Menu menu,int x,int y) {
		for (int i = 0;i < menu.getComponents().size();i++) {
			MenuComponent mc = menu.getComponents().get(i);
			if (MenuButton.class.isInstance(mc)) {
				MenuButton mb = (MenuButton)mc;
				if (mb.isEnabled()) {
					if (menuOver(x,y,RogueCaveGUI.width/2+mc.getX(),(RogueCaveGUI.height/2+mc.getY()),mb.getW(),mb.getH()))
						mb.getActuator().run();
				}
			}
			if (MenuScroll.class.isInstance(mc)) {
				MenuScroll ms = (MenuScroll)mc;
				for (int j = 0;j < ms.getComponents().size();j++) {
					MenuComponent mc2 = ms.getComponents().get(j);
					if (MenuButton.class.isInstance(mc2)) {
						MenuButton mb = (MenuButton)mc2;
						if (mb.isEnabled()) {
							float insideY = (y-(RogueCaveGUI.height/2+mc.getY()));
							if (insideY>=0 && insideY<ms.getH())
								if (menuOver(x,y,mc.getX()+RogueCaveGUI.width/2+mc2.getX(),(mc.getY()+RogueCaveGUI.height/2+mc2.getY()-ms.getCurrentPosition()),366,48))
									mb.getActuator().run();
						}
					}
				}

				int barHeight = menuScrollBarHeight(ms);
				int barPos = menuScrollBarPos(ms);

				if (Math.abs(menuScrollBarShowing(ms)-1f)>=Constants.EPS) {
					if (menuOver(x,y,RogueCaveGUI.width/2+mc.getX()+ms.getW()-RogueCaveGUI.config.scrollSize,RogueCaveGUI.height/2+mc.getY()+RogueCaveGUI.config.scrollSize,RogueCaveGUI.config.scrollSize,barPos-RogueCaveGUI.config.scrollSize-1)) {
						ms.scroll(-ms.getH()+ms.getComponentHeight());
					}
						
					if (menuOver(x,y,RogueCaveGUI.width/2+mc.getX()+ms.getW()-RogueCaveGUI.config.scrollSize,RogueCaveGUI.height/2+mc.getY()+barPos+barHeight+1,RogueCaveGUI.config.scrollSize,ms.getH()-RogueCaveGUI.config.scrollSize-barHeight-barPos-1)) {
						ms.scroll(ms.getH()-ms.getComponentHeight());
					}
				}
				

				menuScrollClick(menu,x,y);
			}
		}
	}

	public void menuScrollClick(Menu menu,int x,int y) {
		for (int i = 0;i < menu.getComponents().size();i++) {
			MenuComponent mc = menu.getComponents().get(i);
			if (MenuScroll.class.isInstance(mc)) {
				MenuScroll ms = (MenuScroll)mc;

				if (Math.abs(menuScrollBarShowing(ms)-1f)>=Constants.EPS) {
					if (!dragging) {
						if (menuOver(x,y,RogueCaveGUI.width/2+ms.getX()+ms.getW()-RogueCaveGUI.config.scrollSize,RogueCaveGUI.height/2+ms.getY(),RogueCaveGUI.config.scrollSize,RogueCaveGUI.config.scrollSize)) {
							ms.scroll(-1f*RogueCaveGUI.delta*RogueCaveGUI.config.scrollSpeed);
						}
						if (menuOver(x,y,RogueCaveGUI.width/2+ms.getX()+ms.getW()-RogueCaveGUI.config.scrollSize,RogueCaveGUI.height/2+ms.getY()+ms.getH()-RogueCaveGUI.config.scrollSize,RogueCaveGUI.config.scrollSize,RogueCaveGUI.config.scrollSize)) {
							ms.scroll(1f*RogueCaveGUI.delta*RogueCaveGUI.config.scrollSpeed);
						}
					}

					int barHeight = menuScrollBarHeight(ms);
					int barPos = menuScrollBarPos(ms);

					if (menuOver(originX,originY,RogueCaveGUI.width/2+mc.getX()+ms.getW()-RogueCaveGUI.config.scrollSize,RogueCaveGUI.height/2+mc.getY()+barPos,RogueCaveGUI.config.scrollSize,barHeight)) {
							if (dragging) {
								ms.scroll((y-originY)*((float)ms.getMaxPosition()/(float)(ms.getH()-RogueCaveGUI.config.scrollSize*2)));
								barPos = menuScrollBarPos(ms);
								originY = y;
								if (originY<=(RogueCaveGUI.height/2+mc.getY()+barPos))
									originY = (RogueCaveGUI.height/2+mc.getY()+barPos)+1;
								if (originY>=(RogueCaveGUI.height/2+mc.getY()+barPos+barHeight))
									originY = (RogueCaveGUI.height/2+mc.getY()+barPos+barHeight)-1;
							}
					}
				}
			}
		}
	}

	public void menuHover(Menu menu,int x,int y) {
		selectedScroll = null;
		selectedScrollItem = NONE;
		for (int i = 0;i < menu.getComponents().size();i++) {
			MenuComponent mc = menu.getComponents().get(i);
			if (MenuButton.class.isInstance(mc)) {
				MenuButton mb = (MenuButton)mc;
				if (mb.isEnabled()) {
					if (menuOver(x,y,RogueCaveGUI.width/2+mc.getX(),(RogueCaveGUI.height/2+mc.getY()),366,48))
						selectedButton=mb.getNumber();
				}
			}
			if (MenuScroll.class.isInstance(mc)) {
				MenuScroll ms = (MenuScroll)mc;
				for (int j = 0;j < ms.getComponents().size();j++) {
					MenuComponent mc2 = ms.getComponents().get(j);
					if (MenuButton.class.isInstance(mc2)) {
						MenuButton mb = (MenuButton)mc2;
						if (mb.isEnabled()) {
							float insideY = (y-(RogueCaveGUI.height/2+mc.getY()));
							if (insideY>=0 && insideY<ms.getH())
								if (menuOver(x,y,mc.getX()+RogueCaveGUI.width/2+mc2.getX(),(mc.getY()+RogueCaveGUI.height/2+mc2.getY()-ms.getCurrentPosition()),366,48))
									selectedButton=mb.getNumber();
						}
					}
				}
				
				if (Math.abs(menuScrollBarShowing(ms)-1f)>=Constants.EPS) {
					
					if (menuOver(x,y,RogueCaveGUI.width/2+ms.getX()+ms.getW()-RogueCaveGUI.config.scrollSize,RogueCaveGUI.height/2+ms.getY(),RogueCaveGUI.config.scrollSize,RogueCaveGUI.config.scrollSize)) {
						selectedScroll = ms;
						selectedScrollItem = SCROLLUP;
					}
					if (menuOver(x,y,RogueCaveGUI.width/2+ms.getX()+ms.getW()-RogueCaveGUI.config.scrollSize,RogueCaveGUI.height/2+ms.getY()+ms.getH()-RogueCaveGUI.config.scrollSize,RogueCaveGUI.config.scrollSize,RogueCaveGUI.config.scrollSize)) {
						selectedScroll = ms;
						selectedScrollItem = SCROLLDOWN;
					}

					int barHeight = menuScrollBarHeight(ms);
					int barPos = menuScrollBarPos(ms);


					if (menuOver(x,y,RogueCaveGUI.width/2+mc.getX()+ms.getW()-RogueCaveGUI.config.scrollSize,RogueCaveGUI.height/2+mc.getY()+barPos,RogueCaveGUI.config.scrollSize,barHeight)) {
						selectedScroll = ms;
						selectedScrollItem = SCROLLBAR;
					}

					if (menuOver(x,y,RogueCaveGUI.width/2+mc.getX()+ms.getW()-RogueCaveGUI.config.scrollSize,RogueCaveGUI.height/2+mc.getY()+RogueCaveGUI.config.scrollSize,RogueCaveGUI.config.scrollSize,barPos-RogueCaveGUI.config.scrollSize)) {
						selectedScroll = ms;
						selectedScrollItem = SCROLLBAR;
					}
						
					if (menuOver(x,y,RogueCaveGUI.width/2+mc.getX()+ms.getW()-RogueCaveGUI.config.scrollSize,RogueCaveGUI.height/2+mc.getY()+barPos+barHeight,RogueCaveGUI.config.scrollSize,ms.getH()-RogueCaveGUI.config.scrollSize-barHeight-barPos)) {
						selectedScroll = ms;
						selectedScrollItem = SCROLLBAR;
					}
				}
			}
		}
	}
	public boolean menuOver(int mouseX1, int mouseY1, int x, int y, int w, int h) {
		return (mouseX1 > x && mouseX1 <= x+w && mouseY1 > y && mouseY1 <= y+h+1);
	}
	
	private float menuScrollBarShowing(MenuScroll ms) {
		if (ms.getMaxPosition()==0)
			return 1;
		else
			return Math.min((float)ms.getH()/(float)ms.getMaxPosition(),1);
	}
	
	private int menuScrollBarHeight(MenuScroll ms) {
		return (int)(menuScrollBarShowing(ms)*(ms.getH()-RogueCaveGUI.config.scrollSize*2));
	}
	
	private int menuScrollBarPos(MenuScroll ms) {
		if (ms.getMaxPosition()==0)
			return RogueCaveGUI.config.scrollSize;
		else
			return (int)(RogueCaveGUI.config.scrollSize+(float)ms.getCurrentPosition()/(float)ms.getMaxPosition()*(ms.getH()-RogueCaveGUI.config.scrollSize*2));
	}
	
	public String getOption(boolean val, String stringYes,String stringNo) {
		if (val)
			return stringYes;
		else
			return stringNo;
	}

}