package com.elezeta.roguecave.gui;

import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.Color;

import com.elezeta.roguecave.Game;
import com.elezeta.roguecave.gui.controllers.*;
import com.elezeta.roguecave.gui.controllers.ingame.IngameController;
import com.elezeta.roguecave.gui.controllers.ingame.PauseMenuController;
import com.elezeta.roguecave.gui.controllers.program.ExitingController;
import com.elezeta.roguecave.gui.controllers.program.StartingController;
import com.elezeta.roguecave.gui.gamestate.GameState;
import com.elezeta.roguecave.gui.internationalization.Language;
import com.elezeta.roguecave.gui.internationalization.LanguageFactory;
import com.elezeta.roguecave.gui.resources.Resources;
import com.elezeta.roguecave.gui.screenpad.MessagePad;
import com.elezeta.roguecave.gui.screenpad.ScreenMessage;
import com.elezeta.roguecave.gui.screenshots.ScreenshotFactory;
import com.elezeta.roguecave.inventory.Inventory;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.Sys.*;

public final class RogueCaveGUI {

	/* CONFIG */
	public static GUIConfig config = new GUIConfig();
	
	/* GAMESTATE */
	public static GameState gameState = new GameState();

	/* LANGUAGE */
	public static Language language;

	/* GAME */
	public static Game game;

	public static Controller currentController = StartingController.get();
	public static Controller newController = StartingController.get();
	
	/* COUNTERS */
	public static long lastFrame = 0;
	public static float delta = 0f;
	public static float fps = config.maxMenuFPS;
	public static float fpsSoft = config.maxMenuFPS;
	public static float deltaCarry = 0f;
	public static float timeCounter = 0f;
	public static float absoluteTimeCounter = 0f;
	public static float moveAnimationCounter = 0f;

	/* WINDOW */
	public static int width;
	public static int height;
	public static float heightf;
	public static int barWidth;
	public static int screenWidth;
	public static float screenWidthf;
	public static int mouseX = 0;
	public static int mouseY = 0;
	public static boolean clickingAttack = false;
	public static boolean clickingMove = false;
	public static boolean showMouse = false;
	public static int showCombos = 1;

	/* MESSAGEPAD */
	public static MessagePad messagePad = new MessagePad();

	/* MENU */
	public static int menuRightMargin = 20;
	public static int menuTopMargin = 20;

	private RogueCaveGUI() {
	}
	
	public static void start() {
		initConfig();
		loadLanguage();
		initDisplay();
		loadGameState();
		changeController(); 
		calculateScreenComponentSize();
		lastFrame = getTime();

		while (currentController!=ExitingController.get()) {
			calculateDelta();
			calculateFPS();
			if (Display.isCloseRequested())
				currentController.requestClose();
			if ((!Display.isActive() || !Display.isVisible()) && currentController == IngameController.get())
				newController = PauseMenuController.get();
			imposeSize();
			messagePad.pass(delta);
			currentController.run();
			changeController();
			Display.sync(getMaxFPS());
		}
		Display.destroy();
	}

	public static void addMessage(ScreenMessage sm) {
		messagePad.addMessage(sm);
	}
	
	public static void initConfig() {
		try {
			config.load("config.cfg");
		} catch (IOException e) {
			try {
				config.load("config.cfg.bak");
			} catch (IOException ex) {
				if (language == null)
					language = LanguageFactory.generateLanguage(GUIConfig.autodetectLanguage());
				addMessage(new ScreenMessage(language.getString(StringID.ERRORLOADING,"config.cfg",currentPath())));
			}
		} 

		config.autodetect();
		config.fix();
		saveConfig();
		width = config.width;
		height = config.height;
		heightf = config.height;
	}

	private static String currentPath() {
		File file = new File(".");
		try {
			return file.getCanonicalPath();
		} catch (IOException e) {
			return "<\".\" cannot be found in path>";
		}
	}

	public static void saveConfig() {
		try {
			config.save("config.cfg.tmp");
			removeFile("config.cfg.bak");
			renameFile("config.cfg","config.cfg.bak");
			renameFile("config.cfg.tmp","config.cfg");
			removeFile("config.cfg.bak");
		} catch (IOException e) {
			if (language == null)
				language = LanguageFactory.generateLanguage(GUIConfig.autodetectLanguage());
			addMessage(new ScreenMessage(language.getString(StringID.ERRORSAVING,"config.cfg",currentPath()),Color.white,Color.red,Color.red));
		} 
	}
	
	private static void removeFile(String file) {
		try {
			File f = new File(file);
			f.delete();
		} catch (Exception e) {
		}
	}

	private static void renameFile(String file,String fileNew) throws IOException {
	    File f = new File(file);
	    File f2 = new File(fileNew);
	    if (f2.exists()) throw new IOException("file exists");
	    f.renameTo(f2);
	}

	private static int getMaxFPS() {
		if (currentController==IngameController.get())
			return config.gameFPS;
		else {
			return config.maxMenuFPS;
		}
	}

	private static void initDisplay() {
		PrintStream outStream = System.out;
		PrintStream dummyStream = new PrintStream(new OutputStream() {
			@Override
			public void write(int b) {
				// NO-OP
			}
		});
		System.setOut(dummyStream);
		try {
			Display.setTitle("RogueCave");
			if (config.resizable)
				Display.setResizable(true);
			setSize();
			Resources.preLoad1(); //icon workaround
			Display.setIcon(new ByteBuffer[] { Resources.getIcon32(),
					Resources.getIcon16() });
			Display.create();
			

			glShadeModel(GL_SMOOTH);
			glEnable(GL_BLEND);
			glEnable(GL_TEXTURE_2D);
			glDisable(GL_LIGHTING);
			glEnable(GL_ALPHA_TEST);
			glAlphaFunc(GL_GREATER, 0f);
			glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
			glDepthFunc(GL_LEQUAL);
			glClearColor(0f,0f,0f,1f);

			Resources.load(language.getString(StringID.LOADING));
			
		} catch (LWJGLException ex) {
			ex.printStackTrace();
			//TODO make user friendly
			System.exit(-1);
		} catch (FontFormatException e) {
			e.printStackTrace();
			//TODO make user friendly
			System.exit(-1);
		} catch (IOException e) {
			e.printStackTrace();
			//TODO make user friendly
			System.exit(-1);
		}
		System.setOut(outStream);
	}

	public static void updateController() {
		newController = currentController;
	}

	private static void changeController() {
		if (newController != null) {
			Controller oldController = currentController;
			currentController.deactivate();
			currentController = newController;
			newController = null;
			currentController.activate(oldController);
			currentController.fix();
			calculateScreenComponentSize();
		}
	}

	private static void loadGameState() {
		//gameState.getSinglePlayerSavedGames().add(new Game());
		// TODO meter en GameState
		//TODO añadir metadatos a cada juego: última vez jugado, iniciado, tiempo de juego.
	}
	
	public static void saveGame(Game game,boolean extremis) {
		gameState.getSinglePlayerSavedGames().addFirst(game);
		//TODO extremis
		//TODO extremis si excepción, forzar y probar
	}

	public static void loadLanguage() {
		language = LanguageFactory.generateLanguage(config.language);
	}
	
	private static void imposeSize() {
		Display.update();
		if (Display.wasResized()) {
			width = Display.getWidth();
			height = Display.getHeight();
			boolean resize = false;
			if (width < config.minWidth) {
				width = config.minWidth;
				resize = true;
			}
			if (width > config.maxWidth) {
				width = config.maxWidth;
				resize = true;
			}
			if (height < config.minHeight) {
				height = config.minHeight;
				resize = true;
			}
			if (height > config.maxHeight) {
				height = config.maxHeight;
				resize = true;
			}
			heightf = height;
			if (resize) {
				try {
					setSize();
				} catch (LWJGLException e) {
					e.printStackTrace();
					//TODO user-friendly error
					System.exit(-1);
				}
			}
			if (width != config.width || height != config.height) {
				config.width = width;
				config.height = height;
				saveConfig();
			}
			calculateScreenComponentSize();
		}
	}
	
	private static void setSize() throws LWJGLException {
		if (height>Display.getDesktopDisplayMode().getHeight()) {
			height = Display.getDesktopDisplayMode().getHeight();
			heightf = height;
		}
		if (width>Display.getDesktopDisplayMode().getWidth())
			width = Display.getDesktopDisplayMode().getWidth();

		Display.setDisplayMode(new DisplayMode(width, height));
	}

	private static void calculateScreenComponentSize() {
		if (!currentController.hasBar())
			barWidth = 0;
		else
			barWidth = 80;
		screenWidth = width - barWidth;
		
		if (width>=800)
			menuRightMargin = 20;
		else if (width<=780)
			menuRightMargin = 0;
		else
			menuRightMargin = width+20-800;
		
		if (height>=597)
			menuTopMargin = 47;
		else if (height<=550)
			menuTopMargin = 0;
		else
			menuTopMargin = height+47-597;
		
		showCombos = (int)Math.min(1+(height-267)/36,10);
		if (game.getPlayer().getInventory().getChosenCombo()>=showCombos) {
			game.getPlayer().getInventory().setChosenCombo(showCombos-1);
		}
		for (int i = showCombos;i<10;i++) {
			game.getPlayer().getInventory().move(Inventory.COMBOS+i*10+1,Inventory.NONE);
			game.getPlayer().getInventory().move(Inventory.COMBOS+i*10+2,Inventory.NONE);
		}
		screenWidthf = (float)screenWidth;
	}

	private static void calculateDelta() {
		long time = getTime();
		delta = (time - lastFrame) / 1000f;
		lastFrame = time;
	}

	private static void calculateFPS() {
		if (delta == 0f)
			fps = getMaxFPS();
		else
			fps = 1f / delta;
		deltaCarry += delta;
		if (deltaCarry >= 0.5) {
			deltaCarry %= 0.5;
			fpsSoft = fps;
		}
	}

	public static void changeBackground() {
		game = ScreenshotFactory.generateScreenshot();
	}

}
