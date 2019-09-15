package com.elezeta.roguecave.gui.controllers.menu;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.elezeta.roguecave.gui.ControlID;
import com.elezeta.roguecave.gui.RogueCaveGUI;
import com.elezeta.roguecave.gui.StringID;
import com.elezeta.roguecave.gui.ValidKeys;
import com.elezeta.roguecave.gui.controllers.Controller;
import com.elezeta.roguecave.gui.controllers.MenuController;
import com.elezeta.roguecave.gui.controllers.program.ExitingController;
import com.elezeta.roguecave.gui.menu.Menu;
import com.elezeta.roguecave.gui.menu.MenuActuator;
import com.elezeta.roguecave.gui.menu.MenuScroll;
import com.elezeta.roguecave.gui.menu.MenuText;
import com.elezeta.roguecave.gui.menu.MenuTextButton;
import com.elezeta.roguecave.gui.resources.Resources;
import com.elezeta.roguecave.gui.screenpad.ScreenMessage;


public class ControlMenuController extends MenuController {

	private static ControlMenuController singleton;
	public static Controller get() {
		if (singleton==null)
			singleton = new ControlMenuController();
		return singleton;
	}

	private ControlMenuController() { }

	private Map<Integer,ControlID> keyMap;
	private Map<ControlID,Integer> keyMapReverse;
	
	private MenuScroll scroll1;
	private int mouseMove;
	private int mouseAttack;
	private int mouseGrab;
	
	@Override
	public void activate(Controller oldController) {
		super.activate(oldController);

		hasBar = true;
		if (oldController != null) {
			if (OptionsMenuController.class.isAssignableFrom(oldController.getClass()))
				if (((OptionsMenuController)oldController).oldController != null)
					if (((OptionsMenuController)oldController).oldController == MainMenuController.get())
						hasBar = false;
		}
		keyMap = new HashMap<Integer,ControlID>();
		keyMapReverse = new HashMap<ControlID,Integer>();

		MenuText text1 = new MenuText();
		text1.setFont(Resources.getMenuFont());
		text1.setX(0);
		text1.setY(-165-15);
		text1.setString(RogueCaveGUI.language.getString(StringID.CONFIGURECONTROLS));
		menu.getComponents().add(text1);

		scroll1 = new MenuScroll();
		scroll1.setX(-183);
		scroll1.setY(-120-15);
		scroll1.setW(366+6+24);
		scroll1.setH(228);
		scroll1.setPadding(6);
		scroll1.setComponentHeight(48);
		scroll1.setComponentSeparation(2);
		
		
		final ControlID keys[] = {
				ControlID.UPFIXED,ControlID.DOWNFIXED,ControlID.LEFTFIXED,ControlID.RIGHTFIXED,
				null,
				ControlID.MOUSEMOVE,
				ControlID.MOUSEATTACK,
				ControlID.MOUSECHANGEWEAPON,
				ControlID.INVENTORY,ControlID.UPGRADES,
				ControlID.TALK,
				ControlID.PAUSE,
				ControlID.VIEWCONSOLE,
				ControlID.UP,ControlID.DOWN,ControlID.LEFT,ControlID.RIGHT,
				ControlID.ENTERFIXED,ControlID.QUITFIXED,
				ControlID.SWITCHWEAPON1,
				ControlID.SWITCHWEAPON2,
				ControlID.SWITCHWEAPON3,
				ControlID.SWITCHWEAPON4,
				ControlID.SWITCHWEAPON5,
				ControlID.SWITCHWEAPON6,
				ControlID.SWITCHWEAPON7,
				ControlID.SWITCHWEAPON8,
				ControlID.SWITCHWEAPON9,
				ControlID.SWITCHWEAPON0,
				ControlID.SELFCOMBO1,
				ControlID.SELFCOMBO2,
				ControlID.SELFCOMBO3,
				ControlID.SELFCOMBO4,
				ControlID.ATTACKUP,
				ControlID.ATTACKDOWN,
				ControlID.ATTACKLEFT,
				ControlID.ATTACKRIGHT,
				ControlID.ATTACKLOOKING,
				ControlID.ATTACKSTICKY,
				ControlID.SHOWFPS,
				ControlID.SHOWDEBUG,

		};

		Set<ControlID> disableds = new HashSet<ControlID>();
		disableds.add(ControlID.UPFIXED);
		disableds.add(ControlID.DOWNFIXED);
		disableds.add(ControlID.LEFTFIXED);
		disableds.add(ControlID.RIGHTFIXED);
		disableds.add(ControlID.ENTERFIXED);
		disableds.add(ControlID.QUITFIXED);
		disableds.add(ControlID.MOUSECHANGEWEAPON);

		int i = 0;
		for (i = 0;i < keys.length;i++) {
			MenuTextButton b = new MenuTextButton();
			b.setX(0);
			b.setY(0+i*50);
			b.setW(366);
			b.setH(48);
			b.setNumber(i);
			if (keys[i]==null) {
				mouseGrab = i;
				setText(b,keys[i]);
				b.setEnabled(true);
				final int index = i;
				b.setActuator(new MenuActuator(){
					@Override
					public void run() {
						RogueCaveGUI.config.grabMouse = !RogueCaveGUI.config.grabMouse;
						setText((MenuTextButton)menu.getButton(mouseGrab),keys[index]);
						selectedButton = 0;
						RogueCaveGUI.saveConfig();
					}
				});
			}
			else {
				if (!disableds.contains(keys[i]))
					b.setEnabled(true);
				if (keys[i].name().equals("MOUSEMOVE"))
					mouseMove = i;
				if (keys[i].name().equals("MOUSEATTACK"))
					mouseAttack = i;
				setText(b,keys[i]);
				if (b.isEnabled() && keys[i] != ControlID.MOUSEMOVE && keys[i] != ControlID.MOUSEATTACK && keys[i] != ControlID.MOUSECHANGEWEAPON) {
					keyMap.put(i,keys[i]);
					keyMapReverse.put(keys[i],i);
				}
				final int index = i;
				b.setActuator(new MenuActuator(){
					@Override
					public void run() {
						if (keys[index].name().equals("MOUSEMOVE") || keys[index].name().equals("MOUSEATTACK")) {
							if (RogueCaveGUI.config.getKey(ControlID.MOUSEMOVE)==0) {
								RogueCaveGUI.config.setKey(ControlID.MOUSEMOVE,1);
								RogueCaveGUI.config.setKey(ControlID.MOUSEATTACK,0);
								selectedButton = 0;
							}
							else {
								RogueCaveGUI.config.setKey(ControlID.MOUSEMOVE,0);
								RogueCaveGUI.config.setKey(ControlID.MOUSEATTACK,1);
								selectedButton = 0;
							}
						}					
						setText((MenuTextButton)menu.getButton(mouseMove),ControlID.MOUSEMOVE);
						setText((MenuTextButton)menu.getButton(mouseAttack),ControlID.MOUSEATTACK);
						RogueCaveGUI.saveConfig();
					}
	
				});
			}
			scroll1.getComponents().add(b);			
		}
			
		
		menu.getComponents().add(scroll1);

		MenuTextButton button1 = new MenuTextButton();
		button1.setX(-183);
		button1.setY(140-15);
		button1.setW(366);
		button1.setH(48);
		button1.setString(RogueCaveGUI.language.getString(StringID.AUTODETECTCONTROLS));
		button1.setNumber(i);
		button1.setEnabled(true);
		button1.setActuator(new MenuActuator(){
			@Override
			public void run() {
				RogueCaveGUI.config.setBaseControls();
				RogueCaveGUI.config.autodetectControls();
				for (int i = 0;i < keys.length;i++) {
					ControlID controlID = keyMap.get(i);
					if (controlID != null)
						setText((MenuTextButton)menu.getButton(i),controlID);
				}
				if (RogueCaveGUI.config.isQwerty())
					RogueCaveGUI.addMessage(new ScreenMessage(RogueCaveGUI.language.getString(StringID.AUTODETECT,RogueCaveGUI.language.getString(StringID.QWERTY))));
				else
					RogueCaveGUI.addMessage(new ScreenMessage(RogueCaveGUI.language.getString(StringID.AUTODETECT,RogueCaveGUI.language.getString(StringID.AZERTY))));
				RogueCaveGUI.saveConfig();
			}
		});
		menu.getComponents().add(button1);
		i++;
		
		MenuTextButton buttonq = new MenuTextButton();
		buttonq.setX(-183);
		buttonq.setY(200-15);
		buttonq.setW(366);
		buttonq.setH(48);
		buttonq.setString(RogueCaveGUI.language.getString(StringID.BACK));
		buttonq.setNumber(i);
		buttonq.setEnabled(true);
		buttonq.setActuator(new MenuActuator(){
			@Override
			public void run() {
				RogueCaveGUI.newController = OptionsMenuController.get();
			}
		});
		menu.getComponents().add(buttonq);
	}

	private void setText(MenuTextButton b, ControlID controlID) {
		if (controlID==null) {
			b.setString(RogueCaveGUI.language.getString(StringID.MOUSEGRAB,getOption(RogueCaveGUI.config.grabMouse,RogueCaveGUI.language.getString(StringID.YES),RogueCaveGUI.language.getString(StringID.NO))));
		}
		else {
			String text = RogueCaveGUI.language.getKeyString(RogueCaveGUI.config.getKey(controlID));
			if (controlID==ControlID.MOUSEMOVE || controlID==ControlID.MOUSEATTACK) {
				if (RogueCaveGUI.config.getKey(controlID)==0)
					text =  RogueCaveGUI.language.getString(StringID.LEFTMOUSE);
				else //if (RogueCaveGUI.config.getKey(keys[i])==1)
				text =  RogueCaveGUI.language.getString(StringID.RIGHTMOUSE);
			}
			if (controlID==ControlID.MOUSECHANGEWEAPON) {
				text = RogueCaveGUI.language.getString(StringID.MOUSEWHEEL);
			}
			b.setString(RogueCaveGUI.language.getString(controlID)+": "+text);
		}
	}

	@Override
	public void deactivate() {
		RogueCaveGUI.saveConfig();
	}
	
	@Override
	public void requestClose() {
		super.requestClose();
		RogueCaveGUI.newController = ExitingController.get();
	}

	@Override
	public void keyPressFilter(int eventKey,char character) {
		menuKeysSoft(menu,eventKey);
		if (eventKey==RogueCaveGUI.config.getKey(ControlID.QUITFIXED)) { RogueCaveGUI.newController = OptionsMenuController.get(); }
		else if (ValidKeys.validKeys.contains(eventKey)) {
			ControlID controlID = keyMap.get(selectedButton);
			if (controlID != null) {
				ControlID old = RogueCaveGUI.config.findKey(eventKey);
				RogueCaveGUI.config.setKey(controlID,eventKey);
				RogueCaveGUI.saveConfig();
				setText((MenuTextButton)menu.getButton(selectedButton),controlID);
				if (old != null) {
					Integer index = keyMapReverse.get(old);
					if (index != null)
						setText((MenuTextButton)menu.getButton(index),old);
					if (old != controlID)
						RogueCaveGUI.addMessage(new ScreenMessage(RogueCaveGUI.language.getString(StringID.OVERRIDENKEY,RogueCaveGUI.language.getKeyString(eventKey),RogueCaveGUI.language.getString(old))));
				}
			}
		}
	}
	
	@Override
	public String getName() { return "ControlMenu"; }

	@Override
	public void menuHover(Menu menu,int x,int y) {
		if (!menuOver(x,y,RogueCaveGUI.width/2+scroll1.getX(),RogueCaveGUI.height/2+scroll1.getY(),scroll1.getW(),scroll1.getH()))
			super.menuHover(menu,x,y);
	}

	@Override
	public void menuClick(Menu menu,int x,int y) {
		if (menuOver(x,y,RogueCaveGUI.width/2+scroll1.getX(),RogueCaveGUI.height/2+scroll1.getY(),scroll1.getW(),scroll1.getH()))
			super.menuHover(menu,x,y);
		super.menuClick(menu,x,y);
	}

}
