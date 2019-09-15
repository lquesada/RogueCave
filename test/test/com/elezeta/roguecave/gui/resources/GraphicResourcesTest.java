package test.com.elezeta.roguecave.gui.resources;

import static org.junit.Assert.*;

import java.io.OutputStream;
import java.io.PrintStream;

import org.lwjgl.opengl.DisplayMode;

import org.junit.Test;
import org.lwjgl.opengl.Display;

import com.elezeta.roguecave.data.SpriteID;
import com.elezeta.roguecave.entities.Player;
import com.elezeta.roguecave.gui.GraphicID;
import com.elezeta.roguecave.gui.resources.Graphic;
import com.elezeta.roguecave.gui.resources.Resources;
import com.elezeta.roguecave.gui.resources.Sprite;
import com.elezeta.roguecave.sprites.SingleSprite;

public class GraphicResourcesTest {

	PrintStream outStream = System.out;
	PrintStream dummyStream = new PrintStream(new OutputStream() {
		@Override
		public void write(int b) {
			// NO-OP
		}
	});

	@Test
	public void loadResourcesTest() {
		try {
			System.setOut(dummyStream);
			Display.setDisplayMode(new DisplayMode(1,1));
			Display.create();
			Resources.load("");
			System.setOut(outStream);
			Display.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			assertFalse(true);
		}
	}

	@Test
	public void testSprites() { 
		try {
			System.setOut(dummyStream);
			Display.setDisplayMode(new DisplayMode(1,1));
			Display.create();
			Resources.load("");
			System.setOut(outStream);
			SpriteID values[] = SpriteID.values();
			for (int i = 0;i < values.length;i++) {
				SpriteID sid = values[i];
				for (int angle = 0;angle < 360;angle+=5) {
					Player e = new Player();
					e.setSprite(new SingleSprite(sid));
					e.setLookAngle(angle);
					Sprite sprite = Resources.getSprite(((SingleSprite)e.getSprite()).getSpriteID());
					assertFalse(sprite==Resources.getDefaultSprite());
					for (int x = 0;x < 1000;x++) {
						e.setMoveAnimationCounter(x);
						Graphic g = sprite.getGraphic(e);
						assertFalse(g==Resources.getDefaultGraphic());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			assertFalse(true);
		}
		Display.destroy();
	}

	@Test
	public void testGraphics() { 
		try {
			System.setOut(dummyStream);
			Display.setDisplayMode(new DisplayMode(1,1));
			Display.create();
			Resources.load("");
			System.setOut(outStream);
			GraphicID values[] = GraphicID.values();
			for (int i = 0;i < values.length;i++) {
				GraphicID gid = values[i];
				Graphic graphic = Resources.getGraphic(gid);
				assertFalse(graphic==Resources.getDefaultGraphic());
			}
		} catch (Exception e) {
			e.printStackTrace();
			assertFalse(true);
		}
		Display.destroy();
	}
	
}
