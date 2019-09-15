package com.elezeta.roguecave.gui;

import java.awt.im.InputContext;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.lwjgl.input.Keyboard;

import com.elezeta.roguecave.data.NameFactory;
import com.elezeta.roguecave.gui.internationalization.LanguageID;

public class GUIConfig {

	/* SCREEN */
	public int width = 800;
	public int height = 600;
	public int gameFPS = 75;
	public boolean grabMouse = true;
	public LanguageID language = null;
	public boolean showDebug = true;
	public boolean showFPS = true;
	public boolean highQuality = true;

	/* CONTROLS */
	private Map<ControlID,Integer> keys = new HashMap<ControlID,Integer>();

	/* INVENTORY */
	public boolean automaticInventoryManager = true;

	/* PLAYER */
	public String name = "";
	
	/* FINAL SCREEN */
	final public boolean resizable = true;
	final public int maxWidth = 3000;
	final public int maxHeight = 3000;
	final public int minWidth = 780;
	final public int minHeight = 550;

	final public int maxMenuFPS = 30;
	final public int maxGameFPS = 9999;
	final public int minGameFPS = 10;
	
	final public float wallTransparence = 0.6667f;
	
	final public float usedItemTransparence = 0.6f;
	final public int equipmentBoxX = -598; 
	final public int equipmentBoxY = +83; 
	final public int playerStatsBoxX = -470; 
	final public int playerStatsBoxY = +83; 
	final public int playerUpgradeStatsBoxX = -598; 
	final public int playerUpgradeStatsBoxY = +83; 
	final public int itemStatsBoxX = -242; 
	final public int itemStatsBoxY = +83; 
	final public int inventoryBoxX = -598; 
	final public int inventoryBoxY = +249; 
	final public int trashX = -693; 
	final public int trashY = +553; 
	final public int dragDistance = 4;
	final public float disabledPickupTransparence = 0.4f;
	final public int scrollSize = 24;
	final public float scrollSpeed = 500f;
	
	/* SCREEN PAD */
	final public float messageShowTime = 3f;
	final public int softMessageLimit = 12;
	final public int mediumMessageLimit = 20;
	final public int hardMessageLimit = 100;
	final public float messageAppearTime = 0.5f;
	final public float messageDisappearTime = 1f;
	final public int historyLimit = 100;

	/* FINAL CONTROLS */
	final public float autoFireRepeatTime = 1.5f;
	final public int closeForce = 5;

	/* FINAL PLAYER */
	final public int maxName = 10;
	public GUIConfig() {
		setBaseControls();
	}

	public static LanguageID autodetectLanguage() {
		LanguageID languag;
		String lang = System.getProperty("user.language");
    	languag = LanguageID.ENGLISH;
	    if (lang.startsWith("es"))
	    	languag = LanguageID.ESPANOL;
//		    if (lang.startsWith("fr"))
//		    	language = LanguageID.FRANCAIS;
//		    if (lang.startsWith("en"))
	    //TODO other languages
		return languag;
	}
	
	public void autodetect() {
		if (language == null)
			language = autodetectLanguage();
		autodetectControls();
	}

	public String getAutoName() {
		if (name.equals("") || !NameFactory.checkName(name)) {
			return NameFactory.generateName();
		}
		return name;
	}
		
	public void autodetectControls() {
		boolean azerty = false;
		if (InputContext.getInstance().getLocale().toString().startsWith("fr_FR"))
			azerty=true;
		if (getKey(ControlID.UP) == 0) {
			if (!azerty)
				setKey(ControlID.UP,Keyboard.KEY_W);
			else
				setKey(ControlID.UP,Keyboard.KEY_Z);
		}
		if (getKey(ControlID.DOWN) == 0) {
			setKey(ControlID.DOWN,Keyboard.KEY_S);
		}
		if (getKey(ControlID.LEFT) == 0) {
			if (!azerty)
				setKey(ControlID.LEFT,Keyboard.KEY_A);
			else
				setKey(ControlID.LEFT,Keyboard.KEY_Q);
		}
		if (getKey(ControlID.RIGHT) == 0) {
			setKey(ControlID.RIGHT,Keyboard.KEY_D);
		}
		if (getKey(ControlID.SELFCOMBO1) == 0) {
			if (!azerty)
				setKey(ControlID.SELFCOMBO1,Keyboard.KEY_Q);
			else
				setKey(ControlID.SELFCOMBO1,Keyboard.KEY_A);
		}
		if (getKey(ControlID.SELFCOMBO2) == 0) {
			setKey(ControlID.SELFCOMBO2,Keyboard.KEY_E);
		}
		if (getKey(ControlID.SELFCOMBO3) == 0) {
			if (!azerty)
				setKey(ControlID.SELFCOMBO3,Keyboard.KEY_Z);
			else
				setKey(ControlID.SELFCOMBO3,Keyboard.KEY_W);
		}
		if (getKey(ControlID.SELFCOMBO4) == 0) {
			setKey(ControlID.SELFCOMBO4,Keyboard.KEY_C);
		}
		grabMouse = true;
	}
	
	public boolean isQwerty() {
		return getKey(ControlID.UP)==Keyboard.KEY_W;

	}

	public void setBaseControls() {
		keys.put(ControlID.ENTERFIXED,Keyboard.KEY_RETURN);
		keys.put(ControlID.SWITCHWEAPON1,Keyboard.KEY_1);
		keys.put(ControlID.SWITCHWEAPON2,Keyboard.KEY_2);
		keys.put(ControlID.SWITCHWEAPON3,Keyboard.KEY_3);
		keys.put(ControlID.SWITCHWEAPON4,Keyboard.KEY_4);
		keys.put(ControlID.SWITCHWEAPON5,Keyboard.KEY_5);
		keys.put(ControlID.SWITCHWEAPON6,Keyboard.KEY_6);
		keys.put(ControlID.SWITCHWEAPON7,Keyboard.KEY_7);
		keys.put(ControlID.SWITCHWEAPON8,Keyboard.KEY_8);
		keys.put(ControlID.SWITCHWEAPON9,Keyboard.KEY_9);
		keys.put(ControlID.SWITCHWEAPON0,Keyboard.KEY_0);
		keys.put(ControlID.SELFCOMBO1,0);
		keys.put(ControlID.SELFCOMBO2,0);
		keys.put(ControlID.SELFCOMBO3,0);
		keys.put(ControlID.SELFCOMBO4,0);
		keys.put(ControlID.ATTACKUP,Keyboard.KEY_I);
		keys.put(ControlID.ATTACKDOWN,Keyboard.KEY_K);
		keys.put(ControlID.ATTACKLEFT,Keyboard.KEY_J);
		keys.put(ControlID.ATTACKRIGHT,Keyboard.KEY_L);
		keys.put(ControlID.ATTACKLOOKING,Keyboard.KEY_M);
		keys.put(ControlID.ATTACKSTICKY,Keyboard.KEY_N);
		keys.put(ControlID.INVENTORY,Keyboard.KEY_R);
		keys.put(ControlID.UPGRADES,Keyboard.KEY_F);
		keys.put(ControlID.QUITFIXED,Keyboard.KEY_ESCAPE);
		keys.put(ControlID.UP,0);
		keys.put(ControlID.DOWN,0);
		keys.put(ControlID.LEFT,0);
		keys.put(ControlID.RIGHT,0);
		keys.put(ControlID.UPFIXED,Keyboard.KEY_UP);
		keys.put(ControlID.DOWNFIXED,Keyboard.KEY_DOWN);
		keys.put(ControlID.LEFTFIXED,Keyboard.KEY_LEFT);
		keys.put(ControlID.RIGHTFIXED,Keyboard.KEY_RIGHT);
		keys.put(ControlID.MOUSEATTACK,0);
		keys.put(ControlID.MOUSEMOVE,1);
		keys.put(ControlID.VIEWCONSOLE,Keyboard.KEY_TAB);
		keys.put(ControlID.TALK,Keyboard.KEY_T);
		keys.put(ControlID.SHOWFPS,Keyboard.KEY_F2);
		keys.put(ControlID.SHOWDEBUG,Keyboard.KEY_F3);
		keys.put(ControlID.PAUSE,Keyboard.KEY_P);
		keys.put(ControlID.HOMEFIXED,Keyboard.KEY_HOME);
		keys.put(ControlID.ENDFIXED,Keyboard.KEY_END);
	}
	
	public void load(String file) throws IOException {
		load(new FileReader(new File(file)));
	}

	public void load(Reader input) throws IOException {
    	String inputs = "";
        int n;
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            while ((n = input.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        }
        catch (Exception e) {

        }
        inputs = writer.toString();
        String[] lines = inputs.replace('\r','\n').split("\n");
        for (int i = 0; i < lines.length;i++) {
        	String line = lines[i].trim();
        	try { if (line.startsWith("width=")) width = readInteger(line); } catch (Exception e) { }
        	try { if (line.startsWith("height=")) height = readInteger(line); } catch (Exception e) { }
        	try { if (line.startsWith("maxgamefps=")) gameFPS = readInteger(line); } catch (Exception e) { }
        	try { if (line.startsWith("grabmouse=")) grabMouse = readBoolean(line); } catch (Exception e) { }
        	try {
	        	if (line.startsWith("language=")) {
	        		String value = getValue(line);
	        		for (int j = 0;j < LanguageID.values().length;j++) {
	        			if (value.equals(LanguageID.values()[j].name().toLowerCase()))
	                		language = LanguageID.values()[j];
	        		}
	        	}
        	} catch (Exception e) { }
        	try { if (line.startsWith("showfps=")) showFPS = readBoolean(line); } catch (Exception e) { }
        	try { if (line.startsWith("showdebug=")) showDebug = readBoolean(line); } catch (Exception e) { }
        	try { if (line.startsWith("highquality=")) highQuality = readBoolean(line); } catch (Exception e) { }
    		try {
    			for (Iterator<ControlID> ite = keys.keySet().iterator();ite.hasNext();) {
	    			ControlID id = ite.next();
	    			if (line.startsWith("control"+id.name().toLowerCase()+"=")) {
	    				int val = readInteger(line);
    					setKey(id,val);
	    			}
	    		}
        	} catch (Exception e) { }
        	try { if (line.startsWith("automaticinventorymanager=")) automaticInventoryManager = readBoolean(line); } catch (Exception e) { }
        	try {
        		if (line.startsWith("name=")) {
        			String value = getValue(line);
        			if (NameFactory.checkName(value))
        				name = value;
        		}
        	} catch (Exception e) { }
        }
        input.close();
		keys.put(ControlID.UPFIXED,Keyboard.KEY_UP);
		keys.put(ControlID.DOWNFIXED,Keyboard.KEY_DOWN);
		keys.put(ControlID.LEFTFIXED,Keyboard.KEY_LEFT);
		keys.put(ControlID.RIGHTFIXED,Keyboard.KEY_RIGHT);
		keys.put(ControlID.ENTERFIXED,Keyboard.KEY_RETURN);
		keys.put(ControlID.QUITFIXED,Keyboard.KEY_ESCAPE);
		keys.put(ControlID.HOMEFIXED,Keyboard.KEY_HOME);
		keys.put(ControlID.ENDFIXED,Keyboard.KEY_END);
	}
	
	private boolean readBoolean(String string) {
		String value = getValue(string);
		return value.equals("true");
	}

	private int readInteger(String string) {
		String value = getValue(string);
		return Integer.parseInt(value);
	}
	
	private String getValue(String line) {
		return line.split("=",2)[1].trim();
	}

	public void save(String file) throws IOException {
		save(new FileWriter(new File(file)));
	}
	
	public void save(Writer writer) throws IOException {
		writer.write("# RogueCave Config File\n");
		writer.write("\n");
		writer.write("# language\n");
		writer.write("language="+language.name().toLowerCase()+"\n");
		writer.write("\n");
		writer.write("# display\n");
		writer.write("width="+width+"\n");
		writer.write("height="+height+"\n");
		writer.write("maxgamefps="+gameFPS+"\n");
		writer.write("showfps="+showFPS+"\n");
		writer.write("showdebug="+showDebug+"\n");
		writer.write("highquality="+highQuality+"\n");
		writer.write("\n");
		writer.write("# mouse\n");
		writer.write("grabmouse="+grabMouse+"\n");
		writer.write("\n");
		writer.write("# inventory\n");
		writer.write("automaticinventorymanager="+automaticInventoryManager+"\n");
		writer.write("\n");
		writer.write("# controls\n");
		Set<ControlID> keySet = keys.keySet();
		List<ControlID> sortedKeySet = (List<ControlID>)new ArrayList(keySet);
		Collections.sort((java.util.List<ControlID>) sortedKeySet);
		for (int j = 0;j < sortedKeySet.size();j++) {
			ControlID id = sortedKeySet.get(j);
			if (!id.name().toLowerCase().endsWith("fixed"))
				writer.write("control"+id.name().toLowerCase()+"="+getKey(id)+"\n");
		}
		writer.write("\n");
		writer.write("# player\n");
		writer.write("name="+name+"\n");
		writer.write("\n");
		writer.write("# EOF\n");
		writer.flush();
		writer.close();
	}


	public static void writeDefault(String file) throws IOException {
		writeDefault(new FileWriter(new File(file)));
	}
	public static void writeDefault(Writer writer) throws IOException {
		GUIConfig newConfig = new GUIConfig();
		writer.write("# RogueCave Config File\n");
		writer.write("\n");
		writer.write("# language\n");
		writer.write("language=<autodetect>\n");
		writer.write("\n");
		writer.write("# display\n");
		writer.write("width="+newConfig.width+"\n");
		writer.write("height="+newConfig.height+"\n");
		writer.write("maxgamefps="+newConfig.gameFPS+"\n");
		writer.write("showfps="+newConfig.showFPS+"\n");
		writer.write("showdebug="+newConfig.showDebug+"\n");
		writer.write("highquality="+newConfig.highQuality+"\n");
		writer.write("\n");
		writer.write("# mouse\n");
		writer.write("grabmouse="+newConfig.grabMouse+"\n");
		writer.write("\n");
		writer.write("# inventory\n");
		writer.write("automaticinventorymanager="+newConfig.automaticInventoryManager+"\n");
		writer.write("\n");
		writer.write("# controls\n");
		GUIConfig c = new GUIConfig();
		Set<ControlID> keySet = c.keys.keySet();
		List<ControlID> sortedKeySet = (List<ControlID>)new ArrayList(keySet);
		Collections.sort((java.util.List<ControlID>) sortedKeySet);
		for (int j = 0;j < sortedKeySet.size();j++) {
			ControlID id = sortedKeySet.get(j);
			if (!id.name().toLowerCase().endsWith("fixed"))
				writer.write("control"+id.name().toLowerCase()+"=<autodetect>\n");
		}
		writer.write("\n");
		writer.write("# player\n");
		writer.write("name=<random>\n");
		writer.write("\n");
		writer.write("# EOF\n");
		writer.flush();
		writer.close();

	}

	public void fix() {
		if (width<minWidth)
			width = minWidth;
		if (width>maxWidth)
			width = maxWidth;
		if (height<minHeight)
			height = minHeight;
		if (height>maxHeight)
			height = maxHeight;
		if (gameFPS<minGameFPS)
			gameFPS = minGameFPS;
		if (gameFPS>maxGameFPS)
			gameFPS = maxGameFPS;
		if (language==null)
			language = LanguageID.ENGLISH;
	}
	
	public ControlID findKey(Integer val) {
		for (Iterator<Entry<ControlID, Integer>> ite = keys.entrySet().iterator();ite.hasNext();) {
			Entry<ControlID,Integer> entry = ite.next();
			if (entry.getValue().equals(val))
				return entry.getKey();
		}
		return null;
	}

	public Integer getKey(ControlID controlID) {
		return keys.get(controlID);
	}
	
	public void setKey(ControlID controlID,Integer val) {
		if (controlID==ControlID.MOUSEMOVE) {
			if (val == 0) {
				keys.put(ControlID.MOUSEMOVE,0);
				keys.put(ControlID.MOUSEATTACK,1);
			}
			else {
				keys.put(ControlID.MOUSEMOVE,1);
				keys.put(ControlID.MOUSEATTACK,0);
			}
			return;
		}
		if (controlID==ControlID.MOUSEATTACK) {
			if (val == 1) {
				keys.put(ControlID.MOUSEMOVE,0);
				keys.put(ControlID.MOUSEATTACK,1);
			}
			else {
				keys.put(ControlID.MOUSEMOVE,1);
				keys.put(ControlID.MOUSEATTACK,0);
			}
			return;
		}
		if (val==Keyboard.KEY_NONE) {
			keys.put(controlID,val);
			return;
		}
		ControlID old = findKey(new Integer(val));
		if (old == null || old==controlID) {
			keys.put(controlID,val);
		}
		else {
			if (old.name().endsWith("FIXED"))
				return;
			else { 
				keys.put(controlID,val);
				keys.put(old,Keyboard.KEY_NONE);
			}
		}
	}
}
