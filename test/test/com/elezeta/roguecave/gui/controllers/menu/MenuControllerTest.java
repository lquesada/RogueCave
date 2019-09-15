package test.com.elezeta.roguecave.gui.controllers.menu;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import org.junit.Test;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.TrueTypeFont;

import com.elezeta.roguecave.Game;
import com.elezeta.roguecave.gui.ControlID;
import com.elezeta.roguecave.gui.RogueCaveGUI;
import com.elezeta.roguecave.gui.StringID;
import com.elezeta.roguecave.gui.ValidKeys;
import com.elezeta.roguecave.gui.controllers.MenuController;
import com.elezeta.roguecave.gui.internationalization.Language;
import com.elezeta.roguecave.gui.internationalization.LanguageID;
import com.elezeta.roguecave.gui.menu.Menu;
import com.elezeta.roguecave.gui.menu.MenuButton;
import com.elezeta.roguecave.gui.menu.MenuComponent;
import com.elezeta.roguecave.gui.menu.MenuScroll;
import com.elezeta.roguecave.gui.resources.Resources;

import static org.junit.Assert.*;

@SuppressWarnings("deprecation")
public class MenuControllerTest {
    private Set<Class<?>> runTimeFindSubclasses(String pckgname, Class<?> tosubclass) throws ClassNotFoundException {

        Set<Class<?>> ret = new HashSet<Class<?>>();

        String name = pckgname;
		if (!name.startsWith("/"))
		    name = "/" + name;
		name = name.replace('.','/');
	
	  	URL url = MenuController.class.getResource(name);
        if (url == null) {
        	return ret;
        }
	
		File directory = new File(url.getFile());
        if (directory.exists()) { //class is in a directory
		    String [] files = directory.list();
		    for (int i=0;i<files.length;i++) {
				if (files[i].endsWith(".class") && !files[i].contains("<error>")) {
				    String classname = files[i].substring(0,files[i].length()-6);
	                Class<?> newClass = getClass(pckgname+"."+classname);
	                if (newClass != null) {
	                    if (tosubclass.isAssignableFrom(newClass) && !tosubclass.equals(newClass)) {
	                        ret.add(newClass);
	                    }
	                }
				}
                else {
                    ret.addAll(runTimeFindSubclasses(pckgname+"."+files[i],tosubclass));
                }
		    }
		} else { //class is in a jar file
			JarInputStream jarFile;
            try {
            	jarFile = new JarInputStream(new FileInputStream(tosubclass.getProtectionDomain().getCodeSource().getLocation().toString().substring(5)));
                JarEntry e;
                e = jarFile.getNextJarEntry();
                while (e != null) {
                    try {
                        String entryname = e.getName();
                        if (entryname.endsWith(".class") && !entryname.contains("<error>")) {
                            String classname = entryname.substring(0,entryname.length()-6);
                            if (classname.startsWith("/")) {
                                classname = classname.substring(1);
                            }
                            classname = classname.replace('/','.');
                            Class<?> newClass = getClass(classname);
                            if (newClass != null) {
                                if (tosubclass.isAssignableFrom(newClass) && !tosubclass.equals(newClass)) {
                                    ret.add(newClass);
                                }
                            }
                        }
                        e = jarFile.getNextJarEntry();
                    } catch (Exception ex) {
                        //ex.printStackTrace();
                    }                    
                }
                jarFile.close();
            } catch (Exception ex) {
                //ex.printStackTrace();
            }                        
		}
        return ret;
    }

    private Class getClass(String className) throws ClassNotFoundException {
        if (!className.contains(".")) {
            if("int" .equals(className)) return int.class;
            if("long".equals(className)) return long.class;
            if("byte".equals(className)) return byte.class;
            if("short".equals(className)) return short.class;
            if("float".equals(className)) return float.class;
            if("double".equals(className)) return double.class;
            if("boolean".equals(className)) return boolean.class;
            if("char".equals(className)) return char.class;
        }
        return Class.forName(className);
    }
    
    @Test
    public void buttonsTest() {
		try {
			System.setOut(dummyStream);
			Display.setDisplayMode(new DisplayMode(1,1));
			Display.create();
			Resources.load("");
			RogueCaveGUI.initConfig();
			RogueCaveGUI.loadLanguage();
			Set<Class<?>> classes = null;
			classes = runTimeFindSubclasses("com",MenuController.class);
	    	for (Iterator<Class<?>> ite = classes.iterator();ite.hasNext();) {
	    		Class<?> c = ite.next();
	    		if (!Modifier.isAbstract(c.getModifiers())) {
		    		Method m = c.getMethod("get");
		    		m.setAccessible(true);
					MenuController controller = (MenuController) m.invoke(null);
					controller.activate(controller);
		    		Menu menu = controller.menu;
		    		int min = 9999999;
		    		int max = -1;
		    		Set<Integer> values = new HashSet<Integer>();
		    		for (int i = 0;i < menu.getComponents().size();i++) {
		    			MenuComponent mc = menu.getComponents().get(i);
		    			if (MenuButton.class.isAssignableFrom(mc.getClass())) {
		    				MenuButton mb = (MenuButton)mc;
		    				int value = mb.getNumber();
		    				if (value<min)
		    					min=value;
		    				if (value>max)
		    					max=value;
		    				assertFalse(values.contains(value));
		    				values.add(value);
		    			}
		    			if (MenuScroll.class.isAssignableFrom(mc.getClass())) {
				    		for (int x = 0;x < ((MenuScroll)mc).getComponents().size();x++) {
				    			MenuComponent mc2 = ((MenuScroll)mc).getComponents().get(x);
				    			if (MenuButton.class.isAssignableFrom(mc2.getClass())) {
				    				MenuButton mb = (MenuButton)mc2;
				    				int value = mb.getNumber();
				    				if (value<min)
				    					min=value;
				    				if (value>max)
				    					max=value;
				    				assertFalse(values.contains(value));
				    				values.add(value);
				    			}
				    		}
		    			}
		    		}
		    		assertNotSame(9999999,min);
		    		assertNotSame(-1,max);
		    		for (int j = min;j <= max;j++) {
			    		boolean contains = false;
			    		for (int i = 0;i < menu.getComponents().size();i++) {
			    			MenuComponent mc = menu.getComponents().get(i);
			    			if (MenuButton.class.isAssignableFrom(mc.getClass())) {
			    				MenuButton mb = (MenuButton)mc;
			    				int value = mb.getNumber();
			    				if (value == j)
			    					contains = true;
			    			}
			    			if (MenuScroll.class.isAssignableFrom(mc.getClass())) {
					    		for (int x = 0;x < ((MenuScroll)mc).getComponents().size();x++) {
					    			MenuComponent mc2 = ((MenuScroll)mc).getComponents().get(x);
					    			if (MenuButton.class.isAssignableFrom(mc2.getClass())) {
					    				MenuButton mb = (MenuButton)mc2;
					    				int value = mb.getNumber();
					    				if (value == j)
					    					contains = true;
					    			}
					    		}
			    			}
			    			
			    		}
			    		assertTrue(contains);
		    		}
		    		assertEquals(0,min);
	    		}
	    	}
			Display.destroy();
			System.setOut(outStream);
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
			return;
		}
    }

	PrintStream outStream = System.out;
	PrintStream dummyStream = new PrintStream(new OutputStream() {
		@Override
		public void write(int b) {
			// NO-OP
		}
	});

    @Test
    public void menuDrawTest() {
		try {
			System.setOut(dummyStream);
			Display.setDisplayMode(new DisplayMode(1,1));
			Display.create();
			Resources.load("");
			System.out.println(Resources.getInfoFont());
			System.out.println(Resources.getMenuFont());
			RogueCaveGUI.initConfig();
			RogueCaveGUI.loadLanguage();
			RogueCaveGUI.game = new Game();
			Set<Class<?>> classes = null;
			classes = runTimeFindSubclasses("com",MenuController.class);
	    	for (Iterator<Class<?>> ite = classes.iterator();ite.hasNext();) {
	    		Class<?> c = ite.next();
	    		if (!Modifier.isAbstract(c.getModifiers())) {
		    		Method m = c.getMethod("get");
		    		m.setAccessible(true);
					MenuController controller = (MenuController) m.invoke(null);
					controller.activate(controller);
					controller.deactivate();
					controller.activate(controller);
					controller.run();
					Display.update();
				}
	    	}
			Display.destroy();
			System.setOut(outStream);
		} catch (Exception e) {
			e.printStackTrace();
			assertFalse(true);
		}
    }

    @Test
    public void menuTextFitTest() {
		try {
			System.setOut(dummyStream);
			Display.setDisplayMode(new DisplayMode(1,1));
			Display.create();
			Resources.load("");
			RogueCaveGUI.initConfig();
			RogueCaveGUI.game = new Game();
			int buttonLength = 366-6;
			TrueTypeFont font = Resources.getMenuFont();
			LanguageID values[] = LanguageID.values();
			for (int i = 0;i < values.length;i++) {
				RogueCaveGUI.config.language = values[i];
				RogueCaveGUI.loadLanguage();
				assertTrue(RogueCaveGUI.language.getString(StringID.LANGUAGENAME).length()<=10);			
				assertFits(RogueCaveGUI.language.getString(StringID.LANGUAGENAME),font,buttonLength);			
				assertFits(RogueCaveGUI.language.getString(StringID.SINGLEPLAYER),font,buttonLength);			
				assertFits(RogueCaveGUI.language.getString(StringID.MULTIPLAYER),font,buttonLength);			
				assertFits(RogueCaveGUI.language.getString(StringID.SERVER),font,buttonLength);			
				assertFits(RogueCaveGUI.language.getString(StringID.CONNECT),font,buttonLength);			
				assertFits(RogueCaveGUI.language.getString(StringID.NEWGAME),font,buttonLength);			
				assertFits(RogueCaveGUI.language.getString(StringID.RESUMEGAME),font,buttonLength);			
				assertFits(RogueCaveGUI.language.getString(StringID.OPTIONS),font,buttonLength);			
				assertFits(RogueCaveGUI.language.getString(StringID.ACHIEVEMENTS),font,buttonLength);			
				assertFits(RogueCaveGUI.language.getString(StringID.CREDITS),font,buttonLength);			
				assertFits(RogueCaveGUI.language.getString(StringID.EXIT),font,buttonLength);			
				assertFits(RogueCaveGUI.language.getString(StringID.START),font,buttonLength);			
				assertFits(RogueCaveGUI.language.getString(StringID.CHOOSELANGUAGE),font,buttonLength);			
				assertFits(RogueCaveGUI.language.getString(StringID.CONTINUEGAME),font,buttonLength);			
				assertFits(RogueCaveGUI.language.getString(StringID.LATERGAME),font,buttonLength);			
				assertFits(RogueCaveGUI.language.getString(StringID.ABANDONGAME),font,buttonLength);
				assertFits(RogueCaveGUI.language.getString(StringID.CONFIGURECONTROLS),font,buttonLength);
				String longestLanguage = "0123456789";
				assertFits(RogueCaveGUI.language.getString(StringID.LANGUAGES,longestLanguage),font,buttonLength);
				String[] yesNo = {RogueCaveGUI.language.getString(StringID.YES),RogueCaveGUI.language.getString(StringID.NO)};
				String longestYesNo = longest(yesNo);
				assertFits(RogueCaveGUI.language.getString(StringID.AUTOMATICINVENTORY,longestYesNo),font,buttonLength);			
				assertFits(RogueCaveGUI.language.getString(StringID.MOUSEGRAB,longestYesNo),font,buttonLength);			
				assertFits(RogueCaveGUI.language.getString(StringID.HIGHQUALITY,longestYesNo),font,buttonLength);			
				assertFits(RogueCaveGUI.language.getString(StringID.MAXFPS,"10000"),font,buttonLength);			
				assertFits(RogueCaveGUI.language.getString(StringID.AUTODETECTCONTROLS),font,buttonLength);
				String longestControl = longest(getControlStrings());
				String longestKey = longest(getKeyStrings());
				assertFits(longestControl+": "+longestKey,font,buttonLength);
				String longestName = "";
				for (int j = 0;j < RogueCaveGUI.config.maxName;j++)
					longestName = longestName+="0";
				assertFits(RogueCaveGUI.language.getString(StringID.NAME,longestName),font,buttonLength);
				assertFits(RogueCaveGUI.language.getString(StringID.BACK),font,buttonLength);
				assertFits(RogueCaveGUI.language.getString(StringID.NO),font,buttonLength);
				assertFits(RogueCaveGUI.language.getString(StringID.YES),font,buttonLength);
				assertFits(RogueCaveGUI.language.getString(StringID.ACCEPT),font,buttonLength);
			}
			Display.destroy();
			System.setOut(outStream);
		} catch (Exception e) {
			e.printStackTrace();
			assertFalse(true);
		}
	}

    private String[] getKeyStrings() {
    	Language l = RogueCaveGUI.language;
		Object v[] = ValidKeys.validKeys.toArray();
    	String[] ret = new String[v.length+3];
    	int j;
		for (j = 0;j < v.length;j++)
			ret[j] = l.getKeyString((Integer)v[j]);
		ret[j] = l.getString(StringID.LEFTMOUSE);
		j++;
		ret[j] = l.getString(StringID.RIGHTMOUSE);
		j++;
		ret[j] = l.getString(StringID.MOUSEWHEEL);
		return ret;
	}

    private String[] getControlStrings() {
    	Language l = RogueCaveGUI.language;
		ControlID v[] = ControlID.values();
    	String[] ret = new String[v.length];
		for (int j = 0;j < v.length;j++)
			ret[j] = l.getString(v[j]);
		return ret;
	}


	private String longest(String[] strings) {
    	String longest = "";
    	for (String string : strings) {
    		if (string != null) {
	    		if (string.length()>longest.length())
	    			longest = string;
    		}
    	}
    	return longest;    	
    }

	private void assertFits(String string, TrueTypeFont font, int buttonLength) {
		assertTrue(font.getWidth(string)<buttonLength);
	}
}
