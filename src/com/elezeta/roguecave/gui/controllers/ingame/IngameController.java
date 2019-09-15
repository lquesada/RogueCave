package com.elezeta.roguecave.gui.controllers.ingame;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import com.elezeta.roguecave.Constants;
import com.elezeta.roguecave.Game;
import com.elezeta.roguecave.data.GameFactory;
import com.elezeta.roguecave.data.MobFactory;
import com.elezeta.roguecave.data.MobID;
import com.elezeta.roguecave.entities.Being;
import com.elezeta.roguecave.entities.Entity;
import com.elezeta.roguecave.entities.Player;
import com.elezeta.roguecave.gui.ControlID;
import com.elezeta.roguecave.gui.RogueCaveGUI;
import com.elezeta.roguecave.gui.controllers.Controller;
import com.elezeta.roguecave.gui.controllers.menu.ConfirmCloseMenuController;
import com.elezeta.roguecave.gui.screenpad.ScreenMessage;


public class IngameController extends Controller {

	private static IngameController singleton;
	public static Controller get() {
		if (singleton==null)
			singleton = new IngameController();
		return singleton;
	}
	
	protected IngameController() { }

	/* INPUT */
	public static boolean startedAttacking = false;

	public static boolean keyboardMove = false;
	public static float keyboardMoveAngle = 0f;
	public static float keyboardMoveX = 0f;
	public static float keyboardMoveY = 0f;

	public static boolean keyboardAttack = false;
	public static float keyboardAttackAngle = 0f;

	public static boolean mouseMove = false;
	public static float mouseMoveAngle = 0f;
	public static float mouseMoveX = 0f;
	public static float mouseMoveY = 0f;
	public static float mouseMoveAttackAngle = 0f;

	public static boolean mouseMoved = false;
	public static float mouseLookAngle = 0f;

	public static boolean mouseAttack = false;
	public static float mouseAttackAngle = 0f;
	public static float mouseAttackDistance = 0f;

	public static boolean selfAttack = false;

	public static boolean notStickyAttack = false;

	public static boolean stickyAttack = false;
	public static float stickyAttackAngle = 0f;
	public static float autoFireAttackTime = 0f;

	boolean entryLock = false;
	
	@Override
	public void activate(Controller oldController) {
		super.activate(oldController);
		entryLock = true;
	}

	@Override
	public void run() {
		super.run();
		mouseGrab(RogueCaveGUI.config.grabMouse);
		manageInputIngame();
		RogueCaveGUI.game.reset();
		updateAutoFireAttack(RogueCaveGUI.delta);
		updatePlayerAngles(RogueCaveGUI.game.getPlayer());
		updatePlayerMoveIntention(RogueCaveGUI.game.getPlayer());
		updatePlayerAttackIntention(RogueCaveGUI.game.getPlayer());
		updatePlayerCombos(RogueCaveGUI.game.getPlayer());
		updatePlayerAutomaticInventoryManager(RogueCaveGUI.game.getPlayer());
		RogueCaveGUI.game.tick(RogueCaveGUI.delta);
		updateAnimationCounter(RogueCaveGUI.delta);
		updateTimeCounter(RogueCaveGUI.delta);
		updateCenter();
		drawWorld();
		drawMainBar();		
		drawMouseCursor();
		drawInfo();
		drawMessagePad();
	}

	private void updatePlayerAutomaticInventoryManager(Player player) {
		player.getInventory().setAutomaticManager(RogueCaveGUI.config.automaticInventoryManager);
	}

	@Override
	public void requestClose() {
		super.requestClose();
		RogueCaveGUI.newController = ConfirmCloseMenuController.get();
	}

	@Override
	public void deactivate() {
		super.deactivate();

	}

	@Override
	public void keyPress(int eventKey) {
		super.keyPress(eventKey);
		if (eventKey==RogueCaveGUI.config.getKey(ControlID.INVENTORY)) { RogueCaveGUI.newController = InventoryController.get(); }
		if (eventKey==RogueCaveGUI.config.getKey(ControlID.UPGRADES)) { RogueCaveGUI.newController = UpgradesController.get(); }
		if (eventKey==RogueCaveGUI.config.getKey(ControlID.QUITFIXED)) { RogueCaveGUI.newController = PauseMenuController.get(); }
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
		if (eventKey == RogueCaveGUI.config.getKey(ControlID.SELFCOMBO1)) {
			RogueCaveGUI.game.getPlayer().getInventory().setChosenComboSelf(0);
			if (RogueCaveGUI.game.getPlayer().getBusyTime()<=0) {
				RogueCaveGUI.game.getPlayer().getInventory().setUseSelf(true);
				selfAttack = true;
				startedAttacking = true;
			}
		}
		if (eventKey == RogueCaveGUI.config.getKey(ControlID.SELFCOMBO2)) {
			RogueCaveGUI.game.getPlayer().getInventory().setChosenComboSelf(1);
			if (RogueCaveGUI.game.getPlayer().getBusyTime()<=0) {
				RogueCaveGUI.game.getPlayer().getInventory().setUseSelf(true);
				selfAttack = true;
				startedAttacking = true;
			}
		}
		if (eventKey == RogueCaveGUI.config.getKey(ControlID.SELFCOMBO3)) {
			RogueCaveGUI.game.getPlayer().getInventory().setChosenComboSelf(2);
			if (RogueCaveGUI.game.getPlayer().getBusyTime()<=0) {
				RogueCaveGUI.game.getPlayer().getInventory().setUseSelf(true);
				selfAttack = true;
				startedAttacking = true;
			}
		}
		if (eventKey == RogueCaveGUI.config.getKey(ControlID.SELFCOMBO4)) {
			RogueCaveGUI.game.getPlayer().getInventory().setChosenComboSelf(3);
			if (RogueCaveGUI.game.getPlayer().getBusyTime()<=0) {
				RogueCaveGUI.game.getPlayer().getInventory().setUseSelf(true);
				selfAttack = true;
				startedAttacking = true;
			}
		}

		if (eventKey == RogueCaveGUI.config.getKey(ControlID.ATTACKUP)) {
			startedAttacking = true;
		}
		if (eventKey == RogueCaveGUI.config.getKey(ControlID.ATTACKDOWN)) {
			startedAttacking = true;
		}
		if (eventKey == RogueCaveGUI.config.getKey(ControlID.ATTACKLEFT)) {
			startedAttacking = true;
		}
		if (eventKey == RogueCaveGUI.config.getKey(ControlID.ATTACKRIGHT)) {
			startedAttacking = true;
		}
		if (eventKey == RogueCaveGUI.config.getKey(ControlID.ATTACKSTICKY)) {
			startedAttacking = true;
		}
		if (eventKey == RogueCaveGUI.config.getKey(ControlID.ATTACKLOOKING)) {
			startedAttacking = true;
		}
		
		//TODO QUITA DEBUG
		float tx = ((RogueCaveGUI.mouseX) - RogueCaveGUI.screenWidthf / 2f + centerX) / isoCos;
		float ty = ((RogueCaveGUI.mouseY) - RogueCaveGUI.heightf / 2f + centerY) / isoSin;


		if (eventKey == Keyboard.KEY_F1) {
			RogueCaveGUI.addMessage(new ScreenMessage("F1 - DEBUG HELP"));
			RogueCaveGUI.addMessage(new ScreenMessage("F2 - SHOW FPS"));
			RogueCaveGUI.addMessage(new ScreenMessage("F3 - SHOW DEBUG INFO"));
			RogueCaveGUI.addMessage(new ScreenMessage("F4 - TOGGLE FPS TO 10 (IMITATES LAG)"));
			RogueCaveGUI.addMessage(new ScreenMessage("F5 - TELEPORT TO MOUSE"));
			RogueCaveGUI.addMessage(new ScreenMessage("F6 - CREATE 50 WHITE RABBITS IN MOUSE"));
			RogueCaveGUI.addMessage(new ScreenMessage("F7 - CREATE 1 WHITE RABBIT IN MOUSE"));
			RogueCaveGUI.addMessage(new ScreenMessage("F8 - CREATE 1 TRANSPARENT RABBIT IN MOUSE"));
			RogueCaveGUI.addMessage(new ScreenMessage("F9 - CREATE 1 BLUE RABBIT IN MOUSE"));
			RogueCaveGUI.addMessage(new ScreenMessage("F10 - LEVEL UP"));
			RogueCaveGUI.addMessage(new ScreenMessage("F11 - 5x LEVEL UP"));
			RogueCaveGUI.addMessage(new ScreenMessage("F12 - RESET LEVEL"));
		}
		if (eventKey == Keyboard.KEY_F4) {
			if (RogueCaveGUI.config.gameFPS == 10)
				RogueCaveGUI.config.gameFPS = 75;
			else
				RogueCaveGUI.config.gameFPS = 10;
		}
		if (eventKey == Keyboard.KEY_F5) {
			RogueCaveGUI.game.getPlayer().setX(tx);
			RogueCaveGUI.game.getPlayer().setY(ty);
		}
		if (eventKey == Keyboard.KEY_F6)
			for (int i = 0;i < 50;i++)
				if (RogueCaveGUI.mouseX < RogueCaveGUI.screenWidth)
					RogueCaveGUI.game.getBadBeings().add(MobFactory.generateMob(MobID.rabbit1, tx, ty));
		if (eventKey == Keyboard.KEY_F7)
			if (RogueCaveGUI.mouseX < RogueCaveGUI.screenWidth)
				RogueCaveGUI.game.getBadBeings().add(MobFactory.generateMob(MobID.rabbit1, tx, ty));
		if (eventKey == Keyboard.KEY_F8)
			if (RogueCaveGUI.mouseX < RogueCaveGUI.screenWidth)
				RogueCaveGUI.game.getBadBeings().add(MobFactory.generateMob(MobID.rabbit2, tx, ty));
		if (eventKey == Keyboard.KEY_F9)
			if (RogueCaveGUI.mouseX < RogueCaveGUI.screenWidth)
				RogueCaveGUI.game.getBadBeings().add(MobFactory.generateMob(MobID.rabbit3, tx, ty));
		if (eventKey == Keyboard.KEY_F10)
			RogueCaveGUI.game.getPlayer().gainExp(RogueCaveGUI.game.getPlayer().getExpForLevelUp());
		if (eventKey == Keyboard.KEY_F11)
			for (int i = 0; i < 5; i++)
				RogueCaveGUI.game.getPlayer().gainExp(RogueCaveGUI.game.getPlayer().getExpForLevelUp());
		if (eventKey == Keyboard.KEY_F12)
			RogueCaveGUI.game = GameFactory.generateGame(RogueCaveGUI.game.getGameID(),RogueCaveGUI.game.getPlayer().getName());
	}

	@Override
	public void wheel(int delta) {
		super.wheel(delta);
		RogueCaveGUI.game.getPlayer().getInventory().setChosenCombo((RogueCaveGUI.showCombos+(RogueCaveGUI.game.getPlayer().getInventory().getChosenCombo() - delta))%RogueCaveGUI.showCombos);
	}

	public void manageInputIngame() {
		notStickyAttack = false;
		keyboardAttack = false;
		keyboardMove = false;
		mouseAttack = false;
		mouseMove = false;
		mouseMoved = false;
		selfAttack = false;
		startedAttacking = false;
		
		while (Keyboard.next()) {
			if (Keyboard.getEventKeyState()) {
				RogueCaveGUI.currentController.keyPressFilter(Keyboard.getEventKey(),Keyboard.getEventCharacter());
			}
			else
				RogueCaveGUI.currentController.keyReleaseFilter(Keyboard.getEventKey(),Keyboard.getEventCharacter());
		}
		if (!RogueCaveGUI.messagePad.isTalkMode()) {
			{
				float fx = 0;
				float fy = 0;
				if (isKeyDown(RogueCaveGUI.config.getKey(ControlID.ATTACKUP))) {
					fy += 1f;
					keyboardAttack = true;
				}
				if (isKeyDown(RogueCaveGUI.config.getKey(ControlID.ATTACKDOWN))) {
					fy -= 1f;
					keyboardAttack = true;
				}
				if (isKeyDown(RogueCaveGUI.config.getKey(ControlID.ATTACKLEFT))) {
					fx -= 1f;
					keyboardAttack = true;
				}
				if (isKeyDown(RogueCaveGUI.config.getKey(ControlID.ATTACKRIGHT))) {
					fx += 1f;
					keyboardAttack = true;
				}
				if ((isKeyDown(RogueCaveGUI.config.getKey(ControlID.ATTACKUP)) || 
					isKeyDown(RogueCaveGUI.config.getKey(ControlID.ATTACKDOWN)) ||
					isKeyDown(RogueCaveGUI.config.getKey(ControlID.ATTACKLEFT)) ||
					isKeyDown(RogueCaveGUI.config.getKey(ControlID.ATTACKRIGHT)) ||
					isKeyDown(RogueCaveGUI.config.getKey(ControlID.ATTACKLOOKING)) ||
					isKeyDown(RogueCaveGUI.config.getKey(ControlID.ATTACKSTICKY)) || RogueCaveGUI.clickingAttack) && autoFireAttackTime <= 0f) {
					autoFireAttackTime = RogueCaveGUI.config.autoFireRepeatTime;
					startedAttacking = true;
				}
	
				if (fx != 0 || fy != 0)
					keyboardAttackAngle = (360f + (float) Math.toDegrees(Math.atan2(fy, fx))) % 360f;
			}
	
			{
				float fx = 0;
				float fy = 0;
				keyboardMove = false;
				if (isKeyDown(RogueCaveGUI.config.getKey(ControlID.UP))
						|| isKeyDown(RogueCaveGUI.config.getKey(ControlID.UPFIXED))) {
					fy += 1f;
					keyboardMove = true;
				}
				if (isKeyDown(RogueCaveGUI.config.getKey(ControlID.DOWN))
						|| isKeyDown(RogueCaveGUI.config.getKey(ControlID.DOWNFIXED))) {
					fy -= 1f;
					keyboardMove = true;
				}
				if (isKeyDown(RogueCaveGUI.config.getKey(ControlID.LEFT))
						|| isKeyDown(RogueCaveGUI.config.getKey(ControlID.LEFTFIXED))) {
					fx -= 1f;
					keyboardMove = true;
				}
				if (isKeyDown(RogueCaveGUI.config.getKey(ControlID.RIGHT))
						|| isKeyDown(RogueCaveGUI.config.getKey(ControlID.RIGHTFIXED))) {
					fx += 1f;
					keyboardMove = true;
				}
	
				keyboardMoveX = fx;
				keyboardMoveY = fy;
	
				if (fx != 0 || fy != 0)
					keyboardMoveAngle = (360f + (float) Math.toDegrees(Math.atan2(fy, fx))) % 360f;
			}
		}
		
		if (!Mouse.isButtonDown(RogueCaveGUI.config.getKey(ControlID.MOUSEATTACK)) && !Mouse.isButtonDown(RogueCaveGUI.config.getKey(ControlID.MOUSEMOVE)))
			entryLock = false;
	
		while (Mouse.next()) {
			if (!entryLock) {
				RogueCaveGUI.showMouse = true;
				if (Mouse.getEventButtonState()) {
					if (RogueCaveGUI.config.grabMouse && !Mouse.isGrabbed() && RogueCaveGUI.currentController==IngameController.get())
						mouseGrab(RogueCaveGUI.config.grabMouse);
					if (Mouse.getEventX() > RogueCaveGUI.screenWidth)
						barClick(Mouse.getEventX() - RogueCaveGUI.screenWidth,RogueCaveGUI.height - Mouse.getEventY(), Mouse.getEventButton());
					else if (Mouse.getEventButton()==RogueCaveGUI.config.getKey(ControlID.MOUSEATTACK))
						startedAttacking = true;
				}
				if (Mouse.getEventDWheel() < 0)
					RogueCaveGUI.currentController.wheel(-1);
				else if (Mouse.getEventDWheel() > 0)
					RogueCaveGUI.currentController.wheel(1);
			}
			mouseMoved = true;
		}

		if (!(RogueCaveGUI.mouseX < RogueCaveGUI.screenWidth
				&& (Mouse.isButtonDown(0) || Mouse.isButtonDown(1)) && Mouse
				.getX() > RogueCaveGUI.screenWidth))
			RogueCaveGUI.mouseX = Mouse.getX();
		else
			RogueCaveGUI.mouseX = RogueCaveGUI.screenWidth - 1;
		RogueCaveGUI.mouseY = Mouse.getY();
		if (!entryLock) {
			RogueCaveGUI.clickingAttack = Mouse.isButtonDown(RogueCaveGUI.config.getKey(ControlID.MOUSEATTACK))&&RogueCaveGUI.mouseX<RogueCaveGUI.screenWidth;
			RogueCaveGUI.clickingMove = Mouse.isButtonDown(RogueCaveGUI.config.getKey(ControlID.MOUSEMOVE))&&RogueCaveGUI.mouseX<RogueCaveGUI.screenWidth;
		}
	
		{
			
			float tx = ((RogueCaveGUI.mouseX) - RogueCaveGUI.screenWidthf / 2f) / isoCos;
			float ty = ((RogueCaveGUI.mouseY) - RogueCaveGUI.heightf / 2f) / isoSin;
			
			mouseLookAngle = (360f + (float) Math.toDegrees(Math.atan2(ty, tx))) % 360f;

			if (RogueCaveGUI.mouseX < RogueCaveGUI.screenWidth && RogueCaveGUI.clickingAttack && !keyboardAttack) {
				mouseAttack = true;
				mouseAttackAngle = mouseLookAngle;
				mouseMoveAttackAngle = mouseAttackAngle;
				float txc = ((RogueCaveGUI.mouseX) - RogueCaveGUI.screenWidthf / 2f + centerX);
				float tyc = ((RogueCaveGUI.mouseY) - RogueCaveGUI.heightf / 2f + centerY);
				mouseAttackDistance = Entity.distance(txc/isoCos,tyc/isoSin,centerX/isoCos,centerY/isoSin);
			}
		}

		{

			float tx = ((RogueCaveGUI.mouseX) - RogueCaveGUI.screenWidthf / 2f + centerX)
					/ isoCos;
			float ty = ((RogueCaveGUI.mouseY) - RogueCaveGUI.heightf / 2f + centerY) / isoSin;

			float tx2 = ((RogueCaveGUI.mouseX) - RogueCaveGUI.screenWidthf / 2f) / isoCos;
			float ty2 = ((RogueCaveGUI.mouseY) - RogueCaveGUI.heightf / 2f) / isoSin;

			if (RogueCaveGUI.mouseX < RogueCaveGUI.screenWidth && RogueCaveGUI.clickingMove && !keyboardMove) {
				mouseMove = true;
				mouseMoveX = tx;
				mouseMoveY = ty;
				mouseMoveAngle = (360f + (float) Math.toDegrees(Math.atan2(ty,
						tx))) % 360f;
				mouseMoveAttackAngle = (360f + (float) Math.toDegrees(Math
						.atan2(ty2, tx2))) % 360f;
			}
		}

		if (keyboardMove || mouseAttack || keyboardAttack)
			mouseMove = false;

		if (isKeyDown(RogueCaveGUI.config.getKey(ControlID.ATTACKLOOKING)))
			notStickyAttack = true;
		if (isKeyDown(RogueCaveGUI.config.getKey(ControlID.ATTACKSTICKY))) {
			if (!stickyAttack) {
				stickyAttack = true;
				if (keyboardAttack) {
					stickyAttackAngle = keyboardAttackAngle;
				} else if (mouseAttack) {
					stickyAttackAngle = mouseMoveAttackAngle;
				} else if (mouseMove) {
					stickyAttackAngle = mouseMoveAttackAngle;
				} else {
					stickyAttackAngle = keyboardMoveAngle;
				}
			}
		} else {
			stickyAttack = false;
			stickyAttackAngle = 0f;
		}
	}
	
	private void updateAutoFireAttack(float delta) {
		if (!keyboardAttack && !mouseAttack && !selfAttack && !notStickyAttack && !stickyAttack)
			autoFireAttackTime = 0f;
		autoFireAttackTime -= delta;
		if (autoFireAttackTime<0f)
			autoFireAttackTime = 0f;
	}

	private void updateAnimationCounter(float delta) {
		RogueCaveGUI.moveAnimationCounter = (((RogueCaveGUI.moveAnimationCounter + delta
				* Constants.worldAnimationSpeedFactor) + 1000) % 1000);
	}

	private void updatePlayerAngles(Player player) {
		if (stickyAttack)
			player.setAttackAngle(stickyAttackAngle);
		else if (keyboardAttack)
			player.setAttackAngle(keyboardAttackAngle);
		else if (mouseAttack)
			player.setAttackAngle(mouseAttackAngle);
		if (keyboardMove)
			player.setLookAngle(keyboardMoveAngle);
		else if (mouseMove)
			player.setLookAngle(mouseMoveAngle);
		else if (notStickyAttack)
			player.setAttackAngle(player.getLookAngle());
		else if (RogueCaveGUI.game.getPlayer().getSpeedXY()<Constants.EPS && mouseMoved)
			player.setLookAngle(mouseLookAngle);
	}

	private void updatePlayerMoveIntention(Player player) {
		if (keyboardMove) {
			player.setForceX(player.getForceX() + keyboardMoveX
					* Constants.forceKeyboardFactor);
			player.setForceY(player.getForceY() + keyboardMoveY
					* Constants.forceKeyboardFactor);
			player.unsetTarget();
		} else if (mouseMove) {
			float dist = RogueCaveGUI.game.getPlayer().distanceTo(mouseMoveX,mouseMoveY);
			RogueCaveGUI.game.getPlayer().setTarget(mouseMoveX, mouseMoveY,0,null,Being.MOVE_EXACT,dist,dist,RogueCaveGUI.game.getTime());
		}
	}

	private void updatePlayerAttackIntention(Player player) {
		player.setStartedAttacking(startedAttacking); 
		if (mouseAttack) {
			player.setAttackingDistance(mouseAttackDistance);
			player.setAttackingHeight(0);
		}
		else if (keyboardAttack)
			player.setAttackingDistance(1000f);
			
		player.setAttacking((selfAttack || keyboardAttack || mouseAttack || notStickyAttack || stickyAttack));
	}

	@Override
	public String getName() { return "Ingame"; }

	@Override
	public void console(String string) {
		singlePlayerTalk(string);
	}

}
