package test.com.elezeta.roguecave.gui;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import org.junit.Test;

import com.elezeta.roguecave.gui.ControlID;
import com.elezeta.roguecave.gui.GUIConfig;
import com.elezeta.roguecave.gui.internationalization.LanguageID;

public class GUIConfigTest {
	
	@Test
	public void configLoadAndSaveTest1() {
		GUIConfig a = new GUIConfig();
		a.width = 1501;
		a.height = 1501;
		a.gameFPS = 100;
		a.grabMouse = false;
		a.language = LanguageID.ENGLISH;
		a.showDebug = false;
		a.highQuality = false;
		a.setKey(ControlID.ATTACKLOOKING,10);
		a.name="Test";
		a.automaticInventoryManager = false;
		
		StringWriter sw = new StringWriter();
		try {
			a.save(sw);
		} catch (IOException e) {
			assertTrue(false);
		}

		GUIConfig b = new GUIConfig();
		try {
			b.load(new StringReader(sw.toString()));
		} catch (IOException e) {
			assertTrue(false);
		}

		assertEquals(a.width,b.width);
		assertEquals(a.height,b.height);
		assertEquals(a.gameFPS,b.gameFPS);
		assertEquals(a.grabMouse,b.grabMouse);
		assertEquals(a.language,b.language);
		assertEquals(a.showDebug,b.showDebug);
		assertEquals(a.highQuality,b.highQuality);
		assertEquals(a.getKey(ControlID.ATTACKLOOKING),b.getKey(ControlID.ATTACKLOOKING));
		assertEquals(a.name,b.name);
		assertEquals(a.automaticInventoryManager,b.automaticInventoryManager);

	}

	@Test
	public void configLoadAndSaveTest2() {
		GUIConfig a = new GUIConfig();
		a.width = 120;
		a.height = 120;
		a.gameFPS = 30;
		a.grabMouse = true;
		a.language = LanguageID.ESPANOL;
		a.showDebug = true;
		a.highQuality = true;
		a.setKey(ControlID.ATTACKLEFT,51);
		a.name="Test@bee";
		a.automaticInventoryManager = true;
		
		StringWriter sw = new StringWriter();
		try {
			a.save(sw);
		} catch (IOException e) {
			assertTrue(false);
		}

		GUIConfig b = new GUIConfig();
		try {
			b.load(new StringReader(sw.toString()));
		} catch (IOException e) {
			assertTrue(false);
		}

		assertEquals(a.width,b.width);
		assertEquals(a.height,b.height);
		assertEquals(a.gameFPS,b.gameFPS);
		assertEquals(a.grabMouse,b.grabMouse);
		assertEquals(a.language,b.language);
		assertEquals(a.showDebug,b.showDebug);
		assertEquals(a.highQuality,b.highQuality);
		assertEquals(a.getKey(ControlID.ATTACKLEFT),b.getKey(ControlID.ATTACKLEFT));
		assertEquals("",b.name);
		assertEquals(a.automaticInventoryManager,b.automaticInventoryManager);

	}

	@Test
	public void configLoadAndSaveTest3() {
		GUIConfig a = new GUIConfig();
		a.width = 120;
		a.height = 120;
		a.gameFPS = 30;
		a.grabMouse = true;
		a.language = LanguageID.ESPANOL;
		a.showDebug = true;
		a.highQuality = true;
		a.setKey(ControlID.ATTACKLEFT,51);
		a.setKey(ControlID.ATTACKRIGHT,51);
		a.name="Test@bee";
		a.automaticInventoryManager = true;
		
		StringWriter sw = new StringWriter();
		try {
			a.save(sw);
		} catch (IOException e) {
			assertTrue(false);
		}

		GUIConfig b = new GUIConfig();
		try {
			b.load(new StringReader(sw.toString()));
		} catch (IOException e) {
			assertTrue(false);
		}

		assertEquals(a.width,b.width);
		assertEquals(a.height,b.height);
		assertEquals(a.gameFPS,b.gameFPS);
		assertEquals(a.grabMouse,b.grabMouse);
		assertEquals(a.language,b.language);
		assertEquals(a.showDebug,b.showDebug);
		assertEquals(a.highQuality,b.highQuality);
		assertEquals("",b.name);
		assertNotSame(b.getKey(ControlID.ATTACKLEFT),b.getKey(ControlID.ATTACKRIGHT));
	}

	@Test
	public void configDefaultTest() {
		StringWriter sw = new StringWriter();
		try {
			GUIConfig.writeDefault(sw);
		} catch (IOException e) {
			assertTrue(false);
		}

		GUIConfig c = new GUIConfig();
		
		GUIConfig b = new GUIConfig();
		try {
			b.load(new StringReader(sw.toString()));
		} catch (IOException e) {
			assertTrue(false);
		}

		assertEquals(c.width,b.width);
		assertEquals(c.height,b.height);
		assertEquals(c.gameFPS,b.gameFPS);
		assertEquals(c.grabMouse,b.grabMouse);
		assertEquals(c.language,b.language);
		assertEquals(c.showDebug,b.showDebug);
		assertEquals(c.highQuality,b.highQuality);
		assertEquals(c.getKey(ControlID.ATTACKLEFT),b.getKey(ControlID.ATTACKLEFT));
		assertEquals(c.automaticInventoryManager,b.automaticInventoryManager);

	}
}
