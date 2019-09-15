package com.elezeta.roguecave.gui.internationalization;

import org.lwjgl.input.Keyboard;

import com.elezeta.roguecave.data.AmmoID;
import com.elezeta.roguecave.data.GameID;
import com.elezeta.roguecave.data.ItemID;
import com.elezeta.roguecave.data.ProfessionID;
import com.elezeta.roguecave.data.UpgradeCategoryID;
import com.elezeta.roguecave.data.UpgradeID;
import com.elezeta.roguecave.gui.ControlID;
import com.elezeta.roguecave.gui.StringID;
import com.elezeta.roguecave.inventory.ItemType;

public abstract class LanguageFactory {

	private LanguageFactory() {
	}

	public static Language generateLanguage(LanguageID lId) {
		Language l;
		l = new Language();
		switch (lId) {

			case ENGLISH:
				l.getGUIStrings().put(StringID.LANGUAGENAME,"English");
				
				l.getGUIStrings().put(StringID.LOADING,"Loading");
				l.getGUIStrings().put(StringID.ERRORLOADING,"Error loading %1% at %2%");
				l.getGUIStrings().put(StringID.ERRORSAVING,"Error saving %1% at %2%");

				l.getGUIStrings().put(StringID.CREDITSUSES,"%1% uses open fragments of:");
				l.getGUIStrings().put(StringID.CREDITSINSPIRED,"%1% is inspired in:");

				l.getGUIStrings().put(StringID.FPS,"FPS");
				l.getGUIStrings().put(StringID.ENTITIES,"Entities");
				l.getGUIStrings().put(StringID.X,"x");
				l.getGUIStrings().put(StringID.Y,"y");
				l.getGUIStrings().put(StringID.I,"i");
				l.getGUIStrings().put(StringID.J,"j");
				l.getGUIStrings().put(StringID.CONTROLLER,"Controller");
				l.getGUIStrings().put(StringID.LEVEL,"LEVEL");

				l.getGUIStrings().put(StringID.TEXT,"%1%");

				l.getGUIStrings().put(StringID.SINGLEPLAYER,"Single Player");
				l.getGUIStrings().put(StringID.MULTIPLAYER,"Multiplayer");
				l.getGUIStrings().put(StringID.SERVER,"Create Server");
				l.getGUIStrings().put(StringID.CONNECT,"Connect to Server");
				l.getGUIStrings().put(StringID.NEWGAME,"Start New Game");
				l.getGUIStrings().put(StringID.RESUMEGAME,"Resume Saved Game");
				l.getGUIStrings().put(StringID.OPTIONS,"Configuration");
				l.getGUIStrings().put(StringID.ACHIEVEMENTS,"Achievements");
				l.getGUIStrings().put(StringID.CREDITS,"Credits");
				l.getGUIStrings().put(StringID.EXIT,"Quit Game");
				l.getGUIStrings().put(StringID.START,"Start");
				l.getGUIStrings().put(StringID.CHOOSELANGUAGE,"Choose Language");

				l.getGUIStrings().put(StringID.PAUSED,"Game paused");
				l.getGUIStrings().put(StringID.CONTINUEGAME,"Back to Game");
				l.getGUIStrings().put(StringID.LATERGAME,"Save and Quit to Title");
				l.getGUIStrings().put(StringID.ABANDONGAME,"Abandon Game");

				l.getGUIStrings().put(StringID.CURRENTLOST,"Current game will be lost");
				l.getGUIStrings().put(StringID.CURRENTSAVE,"Current game will be saved for later resuming");
				l.getGUIStrings().put(StringID.AREYOUSURE,"Are you sure?");

				l.getGUIStrings().put(StringID.PLAYERSTRING,"%1% the %2%");
				l.getGUIStrings().put(StringID.PLAYERLEVEL,"Level %1%");
				l.getGUIStrings().put(StringID.WORLDNUMBER,"World %1%");

				l.getGUIStrings().put(StringID.PROVIDESAMMO,"Ammo");
				l.getGUIStrings().put(StringID.REQUIRESAMMO,"Requires %1%");
				l.getGUIStrings().put(StringID.USEABLEASAMMO,"%1%-like ammo");

				l.getGUIStrings().put(StringID.PLAYERDEFENSE,"(defense)");
				l.getGUIStrings().put(StringID.PLAYERATTACK,"(attack)");
				l.getGUIStrings().put(StringID.PLAYERRANGE,"(range)");
				l.getGUIStrings().put(StringID.PLAYERHP,"(health)");
				l.getGUIStrings().put(StringID.PLAYERHPREGEN,"(health regen)");
				l.getGUIStrings().put(StringID.PLAYERHPSTEAL,"(health steal)");
				l.getGUIStrings().put(StringID.PLAYERMP,"(mana)");
				l.getGUIStrings().put(StringID.PLAYERMPREGEN,"(mana regen)");
				l.getGUIStrings().put(StringID.PLAYERMPSTEAL,"(mana steal)");
				l.getGUIStrings().put(StringID.PLAYERSPEED,"(speed)");
				l.getGUIStrings().put(StringID.PLAYERRECOVERY,"(recovery)");
				l.getGUIStrings().put(StringID.PLAYERACCURACY,"(accuracy)");
				
				l.getGUIStrings().put(StringID.CONFIGURECONTROLS,"Configure Controls");
				l.getGUIStrings().put(StringID.LANGUAGES,"Language: %1%");
				l.getGUIStrings().put(StringID.AUTOMATICINVENTORY,"Inventory Manager: %1%");
				l.getGUIStrings().put(StringID.MOUSEGRAB,"Grab Mouse: %1%");
				l.getGUIStrings().put(StringID.HIGHQUALITY,"High Quality: %1%");
				l.getGUIStrings().put(StringID.MAXFPS,"Maximum FPS: %1%");
				l.getGUIStrings().put(StringID.AUTODETECTCONTROLS,"Autodetect Controls");
				l.getGUIStrings().put(StringID.OVERRIDENKEY,"Overriden key %1% of action %2%");
				l.getGUIStrings().put(StringID.NAME,"Name: %1%");
				l.getGUIStrings().put(StringID.AUTODETECT,"Controls autodetected: %1%");
				l.getGUIStrings().put(StringID.QWERTY,"QWERTY keyboard");
				l.getGUIStrings().put(StringID.AZERTY,"AZERTY keyboard");

				l.getGUIStrings().put(StringID.BACK,"Back");
				l.getGUIStrings().put(StringID.NO,"No");
				l.getGUIStrings().put(StringID.YES,"Yes");
				l.getGUIStrings().put(StringID.ACCEPT,"Accept");

				l.getGUIStrings().put(StringID.LEFTMOUSE,"LEFT CLICK");
				l.getGUIStrings().put(StringID.RIGHTMOUSE,"RIGHT CLICK");
				l.getGUIStrings().put(StringID.MOUSEWHEEL,"MOUSE WHEEL");

				l.getControlStrings().put(ControlID.ENTERFIXED,"Enter");
				l.getControlStrings().put(ControlID.QUITFIXED,"Quit");
				l.getControlStrings().put(ControlID.HOMEFIXED,"Home");
				l.getControlStrings().put(ControlID.ENDFIXED,"End");
				l.getControlStrings().put(ControlID.PAUSE,"Pause");
				l.getControlStrings().put(ControlID.SWITCHWEAPON1,"Weapon 1");
				l.getControlStrings().put(ControlID.SWITCHWEAPON2,"Weapon 2");
				l.getControlStrings().put(ControlID.SWITCHWEAPON3,"Weapon 3");
				l.getControlStrings().put(ControlID.SWITCHWEAPON4,"Weapon 4");
				l.getControlStrings().put(ControlID.SWITCHWEAPON5,"Weapon 5");
				l.getControlStrings().put(ControlID.SWITCHWEAPON6,"Weapon 6");
				l.getControlStrings().put(ControlID.SWITCHWEAPON7,"Weapon 7");
				l.getControlStrings().put(ControlID.SWITCHWEAPON8,"Weapon 8");
				l.getControlStrings().put(ControlID.SWITCHWEAPON9,"Weapon 9");
				l.getControlStrings().put(ControlID.SWITCHWEAPON0,"Weapon 10");
				l.getControlStrings().put(ControlID.SELFCOMBO1,"Special 1");
				l.getControlStrings().put(ControlID.SELFCOMBO2,"Special 2");
				l.getControlStrings().put(ControlID.SELFCOMBO3,"Special 3");
				l.getControlStrings().put(ControlID.SELFCOMBO4,"Special 4");
				l.getControlStrings().put(ControlID.ATTACKUP,"Attack up");
				l.getControlStrings().put(ControlID.ATTACKDOWN,"Attack down");
				l.getControlStrings().put(ControlID.ATTACKLEFT,"Attack left");
				l.getControlStrings().put(ControlID.ATTACKRIGHT,"Attack right");
				l.getControlStrings().put(ControlID.ATTACKLOOKING,"Attack forward");
				l.getControlStrings().put(ControlID.ATTACKSTICKY,"Sticky attack");
				l.getControlStrings().put(ControlID.INVENTORY,"Inventory");
				l.getControlStrings().put(ControlID.UPGRADES,"Upgrades");
				l.getControlStrings().put(ControlID.UP,"Up");
				l.getControlStrings().put(ControlID.UPFIXED,"Up");
				l.getControlStrings().put(ControlID.DOWN,"Down");
				l.getControlStrings().put(ControlID.DOWNFIXED,"Down");
				l.getControlStrings().put(ControlID.LEFT,"Left");
				l.getControlStrings().put(ControlID.LEFTFIXED,"Left");
				l.getControlStrings().put(ControlID.RIGHT,"Right");
				l.getControlStrings().put(ControlID.RIGHTFIXED,"Right");

				l.getControlStrings().put(ControlID.MOUSEMOVE,"Move to");
				l.getControlStrings().put(ControlID.MOUSEATTACK,"Attack");
				l.getControlStrings().put(ControlID.MOUSECHANGEWEAPON,"Change weapon");
				l.getControlStrings().put(ControlID.VIEWCONSOLE,"Show console");
				l.getControlStrings().put(ControlID.TALK,"Talk");
				l.getControlStrings().put(ControlID.SHOWFPS,"Show FPS");
				l.getControlStrings().put(ControlID.SHOWDEBUG,"Show debug");

				l.getItemTypeStrings().put(ItemType.HELMET,"helmet");
				l.getItemTypeStrings().put(ItemType.ARMOR,"armor");
				l.getItemTypeStrings().put(ItemType.ARMS,"arms");
				l.getItemTypeStrings().put(ItemType.LEGS,"legs");
				l.getItemTypeStrings().put(ItemType.BOOTS,"boots");
				l.getItemTypeStrings().put(ItemType.RING,"ring");
				l.getItemTypeStrings().put(ItemType.AMULET,"amulet");
				l.getItemTypeStrings().put(ItemType.WEAPONDOESNOTNEEDAMMO,"weapon");
				l.getItemTypeStrings().put(ItemType.WEAPONNEEDSAMMO,"weapon");
				l.getItemTypeStrings().put(ItemType.SPELL,"spell");
				l.getItemTypeStrings().put(ItemType.SHIELD,"shield");
				l.getItemTypeStrings().put(ItemType.AMMO,"ammo");
				l.getItemTypeStrings().put(ItemType.USEABLE,"useable");
				l.getItemTypeStrings().put(ItemType.MISC,"miscelaneous");

				l.getAmmoStrings().put(AmmoID.arrow,"arrow");
				l.getAmmoStrings().put(AmmoID.bolt,"bolt");
				l.getAmmoPluralStrings().put(AmmoID.arrow,"arrows");
				l.getAmmoPluralStrings().put(AmmoID.bolt,"bolts");

				l.getProfessionStrings().put(ProfessionID.naught,"naught");
				l.getProfessionStrings().put(ProfessionID.knight,"knight");

				l.getItemStrings().put(ItemID.noitem,"nothing");
				l.getItemStrings().put(ItemID.fists,"Fists");
				l.getItemStrings().put(ItemID.knife,"Knife");
				l.getItemStrings().put(ItemID.bow,"Bow");
				l.getItemStrings().put(ItemID.arrows,"Arrows");
				l.getItemStrings().put(ItemID.crossbow,"Crossbow");
				l.getItemStrings().put(ItemID.bolts,"Bolts");
				l.getItemStrings().put(ItemID.shield,"Iron Shield");
				l.getItemStrings().put(ItemID.explosionWand,"Explosion Wand");
				l.getItemStrings().put(ItemID.firewallWand,"Firewall Wand");
				l.getItemStrings().put(ItemID.fireSlashWand,"Fireslash Wand");
				l.getItemStrings().put(ItemID.fireWand,"Fire Breath Wand");
				l.getItemStrings().put(ItemID.waterWand,"Water Wand");
				l.getItemStrings().put(ItemID.fireSpell,"Fire Breath");
				l.getItemStrings().put(ItemID.fireExplosionSpell,"Fire Explosion");
				l.getItemStrings().put(ItemID.healWand,"Heal Wand");
				l.getItemStrings().put(ItemID.healPotion,"Heal Potion");
				l.getItemStrings().put(ItemID.potion,"Misc. Potion");
				l.getItemStrings().put(ItemID.potion2,"Misc. Potion");
				l.getItemStrings().put(ItemID.potion3,"Misc. Potion");
				l.getItemStrings().put(ItemID.potion4,"Misc. Potion");
				l.getItemStrings().put(ItemID.potion5,"Misc. Potion");
				l.getItemStrings().put(ItemID.armor1,"Leather Armor");
				l.getItemStrings().put(ItemID.armor2,"Copper Armor");
				l.getItemStrings().put(ItemID.helmet1,"Leather Helmet");
				l.getItemStrings().put(ItemID.amulet1,"Healing Amulet");
				l.getItemStrings().put(ItemID.ring1,"Golden Ring");
				l.getItemStrings().put(ItemID.ring2,"Brass Ring");
				l.getItemStrings().put(ItemID.ring3,"Ebony Ring");
				l.getItemStrings().put(ItemID.boots1,"Leather Boots");
				l.getItemStrings().put(ItemID.legs1,"Leather Legs");
				l.getItemStrings().put(ItemID.arms1,"Leather Arms");
				l.getItemStrings().put(ItemID.key,"Golden Key");

				l.getUpgradeCategoryStrings().put(UpgradeCategoryID.BASIC,"Basic");
				l.getUpgradeCategoryStrings().put(UpgradeCategoryID.BODY,"Body");

				l.getUpgradeStrings().put(UpgradeID.HUMAN,"Human");
				l.getUpgradeStrings().put(UpgradeID.FIGHTER,"Fighter");
				l.getUpgradeStrings().put(UpgradeID.MAXHP,"Max HP");

				l.getGameStrings().put(GameID.level1,"The quiet forest");


				l.getKeyStrings().put(Keyboard.KEY_NONE," ");
				l.getKeyStrings().put(Keyboard.KEY_ESCAPE,"ESC");
				l.getKeyStrings().put(Keyboard.KEY_1,"1");
				l.getKeyStrings().put(Keyboard.KEY_2,"2");
				l.getKeyStrings().put(Keyboard.KEY_3,"3");
				l.getKeyStrings().put(Keyboard.KEY_4,"4");
				l.getKeyStrings().put(Keyboard.KEY_5,"5");
				l.getKeyStrings().put(Keyboard.KEY_6,"6");
				l.getKeyStrings().put(Keyboard.KEY_7,"7");
				l.getKeyStrings().put(Keyboard.KEY_8,"8");
				l.getKeyStrings().put(Keyboard.KEY_9,"9");
				l.getKeyStrings().put(Keyboard.KEY_0,"0");
				l.getKeyStrings().put(Keyboard.KEY_MINUS,"-");
				l.getKeyStrings().put(Keyboard.KEY_EQUALS,"=");
				l.getKeyStrings().put(Keyboard.KEY_BACK,"BACKSPACE");
				l.getKeyStrings().put(Keyboard.KEY_TAB,"TAB");
				l.getKeyStrings().put(Keyboard.KEY_Q,"Q");
				l.getKeyStrings().put(Keyboard.KEY_W,"W");
				l.getKeyStrings().put(Keyboard.KEY_E,"E");
				l.getKeyStrings().put(Keyboard.KEY_R,"R");
				l.getKeyStrings().put(Keyboard.KEY_T,"T");
				l.getKeyStrings().put(Keyboard.KEY_Y,"Y");
				l.getKeyStrings().put(Keyboard.KEY_U,"U");
				l.getKeyStrings().put(Keyboard.KEY_I,"I");
				l.getKeyStrings().put(Keyboard.KEY_O,"O");
				l.getKeyStrings().put(Keyboard.KEY_P,"P");
				l.getKeyStrings().put(Keyboard.KEY_LBRACKET,"[");
				l.getKeyStrings().put(Keyboard.KEY_RBRACKET,"]");
				l.getKeyStrings().put(Keyboard.KEY_RETURN,"RETURN");
				l.getKeyStrings().put(Keyboard.KEY_LCONTROL,"L_CTRL");
				l.getKeyStrings().put(Keyboard.KEY_A,"A");
				l.getKeyStrings().put(Keyboard.KEY_S,"S");
				l.getKeyStrings().put(Keyboard.KEY_D,"D");
				l.getKeyStrings().put(Keyboard.KEY_F,"F");
				l.getKeyStrings().put(Keyboard.KEY_G,"G");
				l.getKeyStrings().put(Keyboard.KEY_H,"H");
				l.getKeyStrings().put(Keyboard.KEY_J,"J");
				l.getKeyStrings().put(Keyboard.KEY_K,"K");
				l.getKeyStrings().put(Keyboard.KEY_L,"L");
				l.getKeyStrings().put(Keyboard.KEY_SEMICOLON,";");
				l.getKeyStrings().put(Keyboard.KEY_APOSTROPHE,"'");
				l.getKeyStrings().put(Keyboard.KEY_GRAVE,"`");
				l.getKeyStrings().put(Keyboard.KEY_LSHIFT,"L_SHIFT");
				l.getKeyStrings().put(Keyboard.KEY_BACKSLASH,"\\");
				l.getKeyStrings().put(Keyboard.KEY_Z,"Z");
				l.getKeyStrings().put(Keyboard.KEY_X,"X");
				l.getKeyStrings().put(Keyboard.KEY_C,"C");
				l.getKeyStrings().put(Keyboard.KEY_V,"V");
				l.getKeyStrings().put(Keyboard.KEY_B,"B");
				l.getKeyStrings().put(Keyboard.KEY_N,"N");
				l.getKeyStrings().put(Keyboard.KEY_M,"M");
				l.getKeyStrings().put(Keyboard.KEY_COMMA,",");
				l.getKeyStrings().put(Keyboard.KEY_PERIOD,".");
				l.getKeyStrings().put(Keyboard.KEY_SLASH,"/");
				l.getKeyStrings().put(Keyboard.KEY_MULTIPLY,"NUM*");
				l.getKeyStrings().put(Keyboard.KEY_LMENU,"L_ALT");
				l.getKeyStrings().put(Keyboard.KEY_SPACE,"SPACEBAR");
				l.getKeyStrings().put(Keyboard.KEY_CAPITAL,"CAPS LOCK");
				l.getKeyStrings().put(Keyboard.KEY_F1,"F1");
				l.getKeyStrings().put(Keyboard.KEY_F2,"F2");
				l.getKeyStrings().put(Keyboard.KEY_F3,"F3");
				l.getKeyStrings().put(Keyboard.KEY_F4,"F4");
				l.getKeyStrings().put(Keyboard.KEY_F5,"F5");
				l.getKeyStrings().put(Keyboard.KEY_F6,"F6");
				l.getKeyStrings().put(Keyboard.KEY_F7,"F7");
				l.getKeyStrings().put(Keyboard.KEY_F8,"F8");
				l.getKeyStrings().put(Keyboard.KEY_F9,"F9");
				l.getKeyStrings().put(Keyboard.KEY_F10,"F10");
				l.getKeyStrings().put(Keyboard.KEY_NUMLOCK,"NUM LOCK");
				l.getKeyStrings().put(Keyboard.KEY_SCROLL,"SCR LOCK");
				l.getKeyStrings().put(Keyboard.KEY_NUMPAD7,"NUM7");
				l.getKeyStrings().put(Keyboard.KEY_NUMPAD8,"NUM8");
				l.getKeyStrings().put(Keyboard.KEY_NUMPAD9,"NUM9");
				l.getKeyStrings().put(Keyboard.KEY_SUBTRACT,"NUM-");
				l.getKeyStrings().put(Keyboard.KEY_NUMPAD4,"NUM4");
				l.getKeyStrings().put(Keyboard.KEY_NUMPAD5,"NUM5");
				l.getKeyStrings().put(Keyboard.KEY_NUMPAD6,"NUM6");
				l.getKeyStrings().put(Keyboard.KEY_ADD,"NUM+");
				l.getKeyStrings().put(Keyboard.KEY_NUMPAD1,"NUM1");
				l.getKeyStrings().put(Keyboard.KEY_NUMPAD2,"NUM2");
				l.getKeyStrings().put(Keyboard.KEY_NUMPAD3,"NUM3");
				l.getKeyStrings().put(Keyboard.KEY_NUMPAD0,"NUM0");
				l.getKeyStrings().put(Keyboard.KEY_DECIMAL,"NUM.");
				l.getKeyStrings().put(Keyboard.KEY_F11,"F11");
				l.getKeyStrings().put(Keyboard.KEY_F12,"F12");
				l.getKeyStrings().put(Keyboard.KEY_F13,"F13");
				l.getKeyStrings().put(Keyboard.KEY_F14,"F14");
				l.getKeyStrings().put(Keyboard.KEY_F15,"F15");
				l.getKeyStrings().put(Keyboard.KEY_F16,"F16");
				l.getKeyStrings().put(Keyboard.KEY_F17,"F17");
				l.getKeyStrings().put(Keyboard.KEY_F18,"F18");
				l.getKeyStrings().put(Keyboard.KEY_KANA,"KANA");
				l.getKeyStrings().put(Keyboard.KEY_F19,"F19");
				l.getKeyStrings().put(Keyboard.KEY_CONVERT,"CONVERT");
				l.getKeyStrings().put(Keyboard.KEY_NOCONVERT,"NOCONVERT");
				l.getKeyStrings().put(Keyboard.KEY_YEN,"YEN");
				l.getKeyStrings().put(Keyboard.KEY_NUMPADEQUALS,"NUM=");
				l.getKeyStrings().put(Keyboard.KEY_CIRCUMFLEX,"^");
				l.getKeyStrings().put(Keyboard.KEY_AT,"@");
				l.getKeyStrings().put(Keyboard.KEY_COLON,":");
				l.getKeyStrings().put(Keyboard.KEY_UNDERLINE,"_");
				l.getKeyStrings().put(Keyboard.KEY_KANJI,"KANJI");
				l.getKeyStrings().put(Keyboard.KEY_STOP,"STOP");
				l.getKeyStrings().put(Keyboard.KEY_AX,"AX");
				l.getKeyStrings().put(Keyboard.KEY_UNLABELED,"UNLABELED");
				l.getKeyStrings().put(Keyboard.KEY_NUMPADENTER,"NUM ENTER");
				l.getKeyStrings().put(Keyboard.KEY_RCONTROL,"R_CTRL");
				l.getKeyStrings().put(Keyboard.KEY_SECTION,"SECTION");
				l.getKeyStrings().put(Keyboard.KEY_NUMPADCOMMA,"NUM ,");
				l.getKeyStrings().put(Keyboard.KEY_DIVIDE,"NUM /");
				l.getKeyStrings().put(Keyboard.KEY_SYSRQ,"SYSRQ");
				l.getKeyStrings().put(Keyboard.KEY_RMENU,"R_ALT");
				l.getKeyStrings().put(Keyboard.KEY_FUNCTION,"FUNCTION");
				l.getKeyStrings().put(Keyboard.KEY_PAUSE,"PAUSE");
				l.getKeyStrings().put(Keyboard.KEY_HOME,"HOME");
				l.getKeyStrings().put(Keyboard.KEY_UP,"UP");
				l.getKeyStrings().put(Keyboard.KEY_PRIOR,"PRIOR");
				l.getKeyStrings().put(Keyboard.KEY_LEFT,"LEFT");
				l.getKeyStrings().put(Keyboard.KEY_RIGHT,"RIGHT");
				l.getKeyStrings().put(Keyboard.KEY_END,"END");
				l.getKeyStrings().put(Keyboard.KEY_DOWN,"DOWN");
				l.getKeyStrings().put(Keyboard.KEY_NEXT,"NEXT");
				l.getKeyStrings().put(Keyboard.KEY_INSERT,"INSERT");
				l.getKeyStrings().put(Keyboard.KEY_DELETE,"DELETE");
				l.getKeyStrings().put(Keyboard.KEY_CLEAR,"CLEAR");
				l.getKeyStrings().put(Keyboard.KEY_LMETA,"L_META");
				l.getKeyStrings().put(Keyboard.KEY_RMETA,"R_META");
				l.getKeyStrings().put(Keyboard.KEY_RSHIFT,"R_SHIFT");
				l.getKeyStrings().put(Keyboard.KEY_APPS,"APPS");
				l.getKeyStrings().put(Keyboard.KEY_POWER,"POWER");
				l.getKeyStrings().put(Keyboard.KEY_SLEEP,"SLEEP");
				
				return l;
				

			case ESPANOL:
				l.getGUIStrings().put(StringID.LANGUAGENAME,"Español");

				l.getGUIStrings().put(StringID.LOADING,"Cargando");
				l.getGUIStrings().put(StringID.ERRORLOADING,"Error cargando %1% en %2%");
				l.getGUIStrings().put(StringID.ERRORSAVING,"Error guardando %1% en %2%");

				l.getGUIStrings().put(StringID.CREDITSUSES,"%1% utiliza partes libres de:");
				l.getGUIStrings().put(StringID.CREDITSINSPIRED,"%1% está inspirado en:");

				l.getGUIStrings().put(StringID.FPS,"FPS");
				l.getGUIStrings().put(StringID.ENTITIES,"Entidades");
				l.getGUIStrings().put(StringID.X,"x");
				l.getGUIStrings().put(StringID.Y,"y");
				l.getGUIStrings().put(StringID.I,"i");
				l.getGUIStrings().put(StringID.J,"j");
				l.getGUIStrings().put(StringID.CONTROLLER,"Controlador");
				l.getGUIStrings().put(StringID.LEVEL,"NIVEL");

				l.getGUIStrings().put(StringID.TEXT,"%1%");

				l.getGUIStrings().put(StringID.SINGLEPLAYER,"Un Jugador");
				l.getGUIStrings().put(StringID.MULTIPLAYER,"Multijugador");
				l.getGUIStrings().put(StringID.SERVER,"Crear Servidor");
				l.getGUIStrings().put(StringID.CONNECT,"Conectar a Servidor");
				l.getGUIStrings().put(StringID.NEWGAME,"Partida Nueva");
				l.getGUIStrings().put(StringID.RESUMEGAME,"Continuar Partida");
				l.getGUIStrings().put(StringID.OPTIONS,"Configuración");
				l.getGUIStrings().put(StringID.ACHIEVEMENTS,"Logros");
				l.getGUIStrings().put(StringID.CREDITS,"Créditos");
				l.getGUIStrings().put(StringID.EXIT,"Salir");
				l.getGUIStrings().put(StringID.START,"Empezar");

				l.getGUIStrings().put(StringID.PAUSED,"Partida en Pausa");
				l.getGUIStrings().put(StringID.CONTINUEGAME,"Continuar Partida ");
				l.getGUIStrings().put(StringID.LATERGAME,"Guardar Partida y Salir");
				l.getGUIStrings().put(StringID.ABANDONGAME,"Abandonar Partida");

				l.getGUIStrings().put(StringID.CURRENTLOST,"La partida actual se perderá");
				l.getGUIStrings().put(StringID.CURRENTSAVE,"La partida actual se guardará para después");
				l.getGUIStrings().put(StringID.AREYOUSURE,"¿Estás seguro?");

				l.getGUIStrings().put(StringID.PLAYERSTRING,"%1% el %2%");
				l.getGUIStrings().put(StringID.PLAYERLEVEL,"Nivel %1%");
				l.getGUIStrings().put(StringID.WORLDNUMBER,"Mundo %1%");

				l.getGUIStrings().put(StringID.PROVIDESAMMO,"Munición");
				l.getGUIStrings().put(StringID.REQUIRESAMMO,"Requiere %1%");
				l.getGUIStrings().put(StringID.USEABLEASAMMO,"Munición tipo %1%");

				l.getGUIStrings().put(StringID.PLAYERDEFENSE,"(defensa)");
				l.getGUIStrings().put(StringID.PLAYERATTACK,"(ataque)");
				l.getGUIStrings().put(StringID.PLAYERRANGE,"(alcance)");
				l.getGUIStrings().put(StringID.PLAYERHP,"(vida)");
				l.getGUIStrings().put(StringID.PLAYERHPREGEN,"(regeneración de vida)");
				l.getGUIStrings().put(StringID.PLAYERHPSTEAL,"(robo de vida)");
				l.getGUIStrings().put(StringID.PLAYERMP,"(maná)");
				l.getGUIStrings().put(StringID.PLAYERMPREGEN,"(regeneración de maná)");
				l.getGUIStrings().put(StringID.PLAYERMPSTEAL,"(robo de maná)");
				l.getGUIStrings().put(StringID.PLAYERSPEED,"(velocidad)");
				l.getGUIStrings().put(StringID.PLAYERRECOVERY,"(recuperación)");
				l.getGUIStrings().put(StringID.PLAYERACCURACY,"(puntería)");
				
				l.getGUIStrings().put(StringID.CONFIGURECONTROLS,"Configurar Controles");
				l.getGUIStrings().put(StringID.LANGUAGES,"Idioma: %1%");
				l.getGUIStrings().put(StringID.AUTOMATICINVENTORY,"Gestor del Inventario: %1%");
				l.getGUIStrings().put(StringID.MOUSEGRAB,"Bloquear Ratón: %1%");
				l.getGUIStrings().put(StringID.HIGHQUALITY,"Alta Calidad: %1%");
				l.getGUIStrings().put(StringID.MAXFPS,"FPS Máximas: %1%");
				l.getGUIStrings().put(StringID.AUTODETECTCONTROLS,"Autodetectar Controles");
				l.getGUIStrings().put(StringID.OVERRIDENKEY,"Sobreescrita tecla %1% de acción %2%");
				l.getGUIStrings().put(StringID.NAME,"Nombre: %1%");
				l.getGUIStrings().put(StringID.CHOOSELANGUAGE,"Escoger Idioma");
				l.getGUIStrings().put(StringID.AUTODETECT,"Controles autodetectados: %1%");
				l.getGUIStrings().put(StringID.QWERTY,"Teclado QWERTY");
				l.getGUIStrings().put(StringID.AZERTY,"Teclado AZERTY");

				l.getGUIStrings().put(StringID.BACK,"Atrás");
				l.getGUIStrings().put(StringID.NO,"No");
				l.getGUIStrings().put(StringID.YES,"Sí");
				l.getGUIStrings().put(StringID.ACCEPT,"Aceptar");

				l.getGUIStrings().put(StringID.LEFTMOUSE,"CLICK IZQ");
				l.getGUIStrings().put(StringID.RIGHTMOUSE,"CLICK DER");
				l.getGUIStrings().put(StringID.MOUSEWHEEL,"RUEDA RATÓN");

				l.getControlStrings().put(ControlID.ENTERFIXED,"Entrar");
				l.getControlStrings().put(ControlID.QUITFIXED,"Salir");
				l.getControlStrings().put(ControlID.HOMEFIXED,"Inicio");
				l.getControlStrings().put(ControlID.ENDFIXED,"Fin");
				l.getControlStrings().put(ControlID.PAUSE,"Pausa");
				l.getControlStrings().put(ControlID.SWITCHWEAPON1,"Arma 1");
				l.getControlStrings().put(ControlID.SWITCHWEAPON2,"Arma 2");
				l.getControlStrings().put(ControlID.SWITCHWEAPON3,"Arma 3");
				l.getControlStrings().put(ControlID.SWITCHWEAPON4,"Arma 4");
				l.getControlStrings().put(ControlID.SWITCHWEAPON5,"Arma 5");
				l.getControlStrings().put(ControlID.SWITCHWEAPON6,"Arma 6");
				l.getControlStrings().put(ControlID.SWITCHWEAPON7,"Arma 7");
				l.getControlStrings().put(ControlID.SWITCHWEAPON8,"Arma 8");
				l.getControlStrings().put(ControlID.SWITCHWEAPON9,"Arma 9");
				l.getControlStrings().put(ControlID.SWITCHWEAPON0,"Arma 10");
				l.getControlStrings().put(ControlID.SELFCOMBO1,"Especial 1");
				l.getControlStrings().put(ControlID.SELFCOMBO2,"Especial 2");
				l.getControlStrings().put(ControlID.SELFCOMBO3,"Especial 3");
				l.getControlStrings().put(ControlID.SELFCOMBO4,"Especial 4");
				l.getControlStrings().put(ControlID.ATTACKUP,"Ataque arriba");
				l.getControlStrings().put(ControlID.ATTACKDOWN,"Ataque aba");
				l.getControlStrings().put(ControlID.ATTACKLEFT,"Ataque izq");
				l.getControlStrings().put(ControlID.ATTACKRIGHT,"Ataque der");
				l.getControlStrings().put(ControlID.ATTACKLOOKING,"Ataque frente");
				l.getControlStrings().put(ControlID.ATTACKSTICKY,"Ataque fijo");
				l.getControlStrings().put(ControlID.INVENTORY,"Inventario");
				l.getControlStrings().put(ControlID.UPGRADES,"Mejoras");
				l.getControlStrings().put(ControlID.UP,"Arriba");
				l.getControlStrings().put(ControlID.UPFIXED,"Arriba");
				l.getControlStrings().put(ControlID.DOWN,"Abajo");
				l.getControlStrings().put(ControlID.DOWNFIXED,"Abajo");
				l.getControlStrings().put(ControlID.LEFT,"Izquierda");
				l.getControlStrings().put(ControlID.LEFTFIXED,"Izquierda");
				l.getControlStrings().put(ControlID.RIGHT,"Derecha");
				l.getControlStrings().put(ControlID.RIGHTFIXED,"Derecha");
				l.getControlStrings().put(ControlID.MOUSEMOVE,"Mover hacia");
				l.getControlStrings().put(ControlID.MOUSEATTACK,"Atacar");
				l.getControlStrings().put(ControlID.MOUSECHANGEWEAPON,"Cambiar arma");
				l.getControlStrings().put(ControlID.VIEWCONSOLE,"Ver consola");
				l.getControlStrings().put(ControlID.TALK,"Hablar");
				l.getControlStrings().put(ControlID.SHOWFPS,"Ver FPS");
				l.getControlStrings().put(ControlID.SHOWDEBUG,"Ver debug");

				
				
				l.getItemTypeStrings().put(ItemType.HELMET,"casco");
				l.getItemTypeStrings().put(ItemType.ARMOR,"armadura");
				l.getItemTypeStrings().put(ItemType.ARMS,"brazos");
				l.getItemTypeStrings().put(ItemType.LEGS,"perneras");
				l.getItemTypeStrings().put(ItemType.BOOTS,"botas");
				l.getItemTypeStrings().put(ItemType.RING,"anillo");
				l.getItemTypeStrings().put(ItemType.AMULET,"amuleto");
				l.getItemTypeStrings().put(ItemType.WEAPONDOESNOTNEEDAMMO,"arma");
				l.getItemTypeStrings().put(ItemType.WEAPONNEEDSAMMO,"arma");
				l.getItemTypeStrings().put(ItemType.SPELL,"hechizo");
				l.getItemTypeStrings().put(ItemType.SHIELD,"escudo");
				l.getItemTypeStrings().put(ItemType.AMMO,"munición");
				l.getItemTypeStrings().put(ItemType.USEABLE,"utilizable");
				l.getItemTypeStrings().put(ItemType.MISC,"misceláneo");

				l.getAmmoStrings().put(AmmoID.arrow,"flecha");
				l.getAmmoStrings().put(AmmoID.bolt,"saeta");
				l.getAmmoPluralStrings().put(AmmoID.arrow,"flechas");
				l.getAmmoPluralStrings().put(AmmoID.bolt,"saetas");

				l.getProfessionStrings().put(ProfessionID.naught,"inútil");
				l.getProfessionStrings().put(ProfessionID.knight,"guerrero");

				l.getItemStrings().put(ItemID.noitem,"nada");
				l.getItemStrings().put(ItemID.fists,"Puños");
				l.getItemStrings().put(ItemID.knife,"Cuchillo");
				l.getItemStrings().put(ItemID.bow,"Arco");
				l.getItemStrings().put(ItemID.arrows,"Flechas");
				l.getItemStrings().put(ItemID.crossbow,"Ballesta");
				l.getItemStrings().put(ItemID.bolts,"Saetas");
				l.getItemStrings().put(ItemID.shield,"Escudo Metálico");
				l.getItemStrings().put(ItemID.explosionWand,"Vara de Explosión");
				l.getItemStrings().put(ItemID.firewallWand,"Vara de Muro de Fuego");
				l.getItemStrings().put(ItemID.fireSlashWand,"Vara de Hélice de Fuego");
				l.getItemStrings().put(ItemID.fireWand,"Vara de Aliento de Fuego");
				l.getItemStrings().put(ItemID.waterWand,"Vara de Chorro de Agua");
				l.getItemStrings().put(ItemID.fireSpell,"Aliento de Fuego");
				l.getItemStrings().put(ItemID.fireExplosionSpell,"Explosión");
				l.getItemStrings().put(ItemID.healWand,"Vara de Curación");
				l.getItemStrings().put(ItemID.healPotion,"Poción de Curación");
				l.getItemStrings().put(ItemID.potion,"Poción Misc.");
				l.getItemStrings().put(ItemID.potion2,"Poción Misc.");
				l.getItemStrings().put(ItemID.potion3,"Poción Misc.");
				l.getItemStrings().put(ItemID.potion4,"Poción Misc.");
				l.getItemStrings().put(ItemID.potion5,"Poción Misc.");
				l.getItemStrings().put(ItemID.armor1,"Armadura de Cuero");
				l.getItemStrings().put(ItemID.armor2,"Armadura de Cobre");
				l.getItemStrings().put(ItemID.helmet1,"Casco de Cuero");
				l.getItemStrings().put(ItemID.amulet1,"Amuleto de Curación");
				l.getItemStrings().put(ItemID.ring1,"Anillo de Oro");
				l.getItemStrings().put(ItemID.ring2,"Anillo de Aluminio");
				l.getItemStrings().put(ItemID.ring3,"Anillo de Ébano");
				l.getItemStrings().put(ItemID.boots1,"Botas de Cuero");
				l.getItemStrings().put(ItemID.legs1,"Perneras de Cuero");
				l.getItemStrings().put(ItemID.arms1,"Brazos de Cuero");
				l.getItemStrings().put(ItemID.key,"Llave de Oro");

				l.getUpgradeCategoryStrings().put(UpgradeCategoryID.BASIC,"Básico");
				l.getUpgradeCategoryStrings().put(UpgradeCategoryID.BODY,"Cuerpo");

				l.getUpgradeStrings().put(UpgradeID.HUMAN,"Humano");
				l.getUpgradeStrings().put(UpgradeID.FIGHTER,"Luchador");
				l.getUpgradeStrings().put(UpgradeID.MAXHP,"Vida máxima");

				l.getGameStrings().put(GameID.level1,"El bosque silencioso");

				l.getKeyStrings().put(Keyboard.KEY_NONE," ");
				l.getKeyStrings().put(Keyboard.KEY_ESCAPE,"ESCAPE");
				l.getKeyStrings().put(Keyboard.KEY_1,"1");
				l.getKeyStrings().put(Keyboard.KEY_2,"2");
				l.getKeyStrings().put(Keyboard.KEY_3,"3");
				l.getKeyStrings().put(Keyboard.KEY_4,"4");
				l.getKeyStrings().put(Keyboard.KEY_5,"5");
				l.getKeyStrings().put(Keyboard.KEY_6,"6");
				l.getKeyStrings().put(Keyboard.KEY_7,"7");
				l.getKeyStrings().put(Keyboard.KEY_8,"8");
				l.getKeyStrings().put(Keyboard.KEY_9,"9");
				l.getKeyStrings().put(Keyboard.KEY_0,"0");
				l.getKeyStrings().put(Keyboard.KEY_MINUS,"-");
				l.getKeyStrings().put(Keyboard.KEY_EQUALS,"=");
				l.getKeyStrings().put(Keyboard.KEY_BACK,"RETROCESO");
				l.getKeyStrings().put(Keyboard.KEY_TAB,"TAB");
				l.getKeyStrings().put(Keyboard.KEY_Q,"Q");
				l.getKeyStrings().put(Keyboard.KEY_W,"W");
				l.getKeyStrings().put(Keyboard.KEY_E,"E");
				l.getKeyStrings().put(Keyboard.KEY_R,"R");
				l.getKeyStrings().put(Keyboard.KEY_T,"T");
				l.getKeyStrings().put(Keyboard.KEY_Y,"Y");
				l.getKeyStrings().put(Keyboard.KEY_U,"U");
				l.getKeyStrings().put(Keyboard.KEY_I,"I");
				l.getKeyStrings().put(Keyboard.KEY_O,"O");
				l.getKeyStrings().put(Keyboard.KEY_P,"P");
				l.getKeyStrings().put(Keyboard.KEY_LBRACKET,"[");
				l.getKeyStrings().put(Keyboard.KEY_RBRACKET,"]");
				l.getKeyStrings().put(Keyboard.KEY_RETURN,"ENTER");
				l.getKeyStrings().put(Keyboard.KEY_LCONTROL,"CTRL_I");
				l.getKeyStrings().put(Keyboard.KEY_A,"A");
				l.getKeyStrings().put(Keyboard.KEY_S,"S");
				l.getKeyStrings().put(Keyboard.KEY_D,"D");
				l.getKeyStrings().put(Keyboard.KEY_F,"F");
				l.getKeyStrings().put(Keyboard.KEY_G,"G");
				l.getKeyStrings().put(Keyboard.KEY_H,"H");
				l.getKeyStrings().put(Keyboard.KEY_J,"J");
				l.getKeyStrings().put(Keyboard.KEY_K,"K");
				l.getKeyStrings().put(Keyboard.KEY_L,"L");
				l.getKeyStrings().put(Keyboard.KEY_SEMICOLON,";");
				l.getKeyStrings().put(Keyboard.KEY_APOSTROPHE,"'");
				l.getKeyStrings().put(Keyboard.KEY_GRAVE,"`");
				l.getKeyStrings().put(Keyboard.KEY_LSHIFT,"SHIFT_I");
				l.getKeyStrings().put(Keyboard.KEY_BACKSLASH,"\\");
				l.getKeyStrings().put(Keyboard.KEY_Z,"Z");
				l.getKeyStrings().put(Keyboard.KEY_X,"X");
				l.getKeyStrings().put(Keyboard.KEY_C,"C");
				l.getKeyStrings().put(Keyboard.KEY_V,"V");
				l.getKeyStrings().put(Keyboard.KEY_B,"B");
				l.getKeyStrings().put(Keyboard.KEY_N,"N");
				l.getKeyStrings().put(Keyboard.KEY_M,"M");
				l.getKeyStrings().put(Keyboard.KEY_COMMA,",");
				l.getKeyStrings().put(Keyboard.KEY_PERIOD,".");
				l.getKeyStrings().put(Keyboard.KEY_SLASH,"/");
				l.getKeyStrings().put(Keyboard.KEY_MULTIPLY,"NUM*");
				l.getKeyStrings().put(Keyboard.KEY_LMENU,"ALT_I");
				l.getKeyStrings().put(Keyboard.KEY_SPACE,"ESPACIO");
				l.getKeyStrings().put(Keyboard.KEY_CAPITAL,"BLOQ MAYUS");
				l.getKeyStrings().put(Keyboard.KEY_F1,"F1");
				l.getKeyStrings().put(Keyboard.KEY_F2,"F2");
				l.getKeyStrings().put(Keyboard.KEY_F3,"F3");
				l.getKeyStrings().put(Keyboard.KEY_F4,"F4");
				l.getKeyStrings().put(Keyboard.KEY_F5,"F5");
				l.getKeyStrings().put(Keyboard.KEY_F6,"F6");
				l.getKeyStrings().put(Keyboard.KEY_F7,"F7");
				l.getKeyStrings().put(Keyboard.KEY_F8,"F8");
				l.getKeyStrings().put(Keyboard.KEY_F9,"F9");
				l.getKeyStrings().put(Keyboard.KEY_F10,"F10");
				l.getKeyStrings().put(Keyboard.KEY_NUMLOCK,"BLOQ NUM");
				l.getKeyStrings().put(Keyboard.KEY_SCROLL,"BLOQ DESPL");
				l.getKeyStrings().put(Keyboard.KEY_NUMPAD7,"NUM7");
				l.getKeyStrings().put(Keyboard.KEY_NUMPAD8,"NUM8");
				l.getKeyStrings().put(Keyboard.KEY_NUMPAD9,"NUM9");
				l.getKeyStrings().put(Keyboard.KEY_SUBTRACT,"NUM-");
				l.getKeyStrings().put(Keyboard.KEY_NUMPAD4,"NUM4");
				l.getKeyStrings().put(Keyboard.KEY_NUMPAD5,"NUM5");
				l.getKeyStrings().put(Keyboard.KEY_NUMPAD6,"NUM6");
				l.getKeyStrings().put(Keyboard.KEY_ADD,"NUM+");
				l.getKeyStrings().put(Keyboard.KEY_NUMPAD1,"NUM1");
				l.getKeyStrings().put(Keyboard.KEY_NUMPAD2,"NUM2");
				l.getKeyStrings().put(Keyboard.KEY_NUMPAD3,"NUM3");
				l.getKeyStrings().put(Keyboard.KEY_NUMPAD0,"NUM0");
				l.getKeyStrings().put(Keyboard.KEY_DECIMAL,"NUM.");
				l.getKeyStrings().put(Keyboard.KEY_F11,"F11");
				l.getKeyStrings().put(Keyboard.KEY_F12,"F12");
				l.getKeyStrings().put(Keyboard.KEY_F13,"F13");
				l.getKeyStrings().put(Keyboard.KEY_F14,"F14");
				l.getKeyStrings().put(Keyboard.KEY_F15,"F15");
				l.getKeyStrings().put(Keyboard.KEY_F16,"F16");
				l.getKeyStrings().put(Keyboard.KEY_F17,"F17");
				l.getKeyStrings().put(Keyboard.KEY_F18,"F18");
				l.getKeyStrings().put(Keyboard.KEY_KANA,"KANA");
				l.getKeyStrings().put(Keyboard.KEY_F19,"F19");
				l.getKeyStrings().put(Keyboard.KEY_CONVERT,"CONV");
				l.getKeyStrings().put(Keyboard.KEY_NOCONVERT,"NOCONV");
				l.getKeyStrings().put(Keyboard.KEY_YEN,"YEN");
				l.getKeyStrings().put(Keyboard.KEY_NUMPADEQUALS,"NUM=");
				l.getKeyStrings().put(Keyboard.KEY_CIRCUMFLEX,"^");
				l.getKeyStrings().put(Keyboard.KEY_AT,"@");
				l.getKeyStrings().put(Keyboard.KEY_COLON,":");
				l.getKeyStrings().put(Keyboard.KEY_UNDERLINE,"_");
				l.getKeyStrings().put(Keyboard.KEY_KANJI,"KANJI");
				l.getKeyStrings().put(Keyboard.KEY_STOP,"STOP");
				l.getKeyStrings().put(Keyboard.KEY_AX,"AX");
				l.getKeyStrings().put(Keyboard.KEY_UNLABELED,"SIN ETIQ");
				l.getKeyStrings().put(Keyboard.KEY_NUMPADENTER,"NUMINTRO");
				l.getKeyStrings().put(Keyboard.KEY_RCONTROL,"CTRL_D");
				l.getKeyStrings().put(Keyboard.KEY_SECTION,"SECCION");
				l.getKeyStrings().put(Keyboard.KEY_NUMPADCOMMA,"NUM ,");
				l.getKeyStrings().put(Keyboard.KEY_DIVIDE,"NUM /");
				l.getKeyStrings().put(Keyboard.KEY_SYSRQ,"SYSRQ");
				l.getKeyStrings().put(Keyboard.KEY_RMENU,"ALT_D");
				l.getKeyStrings().put(Keyboard.KEY_FUNCTION,"FUNCION");
				l.getKeyStrings().put(Keyboard.KEY_PAUSE,"PAUSA");
				l.getKeyStrings().put(Keyboard.KEY_HOME,"INICIO");
				l.getKeyStrings().put(Keyboard.KEY_UP,"ARRIBA");
				l.getKeyStrings().put(Keyboard.KEY_PRIOR,"REPAG");
				l.getKeyStrings().put(Keyboard.KEY_LEFT,"IZQUIERDA");
				l.getKeyStrings().put(Keyboard.KEY_RIGHT,"DERECHA");
				l.getKeyStrings().put(Keyboard.KEY_END,"FIN");
				l.getKeyStrings().put(Keyboard.KEY_DOWN,"ABAJO");
				l.getKeyStrings().put(Keyboard.KEY_NEXT,"AVPAG");
				l.getKeyStrings().put(Keyboard.KEY_INSERT,"INSERTAR");
				l.getKeyStrings().put(Keyboard.KEY_DELETE,"SUPRIMIR");
				l.getKeyStrings().put(Keyboard.KEY_CLEAR,"VACIAR");
				l.getKeyStrings().put(Keyboard.KEY_LMETA,"META_I");
				l.getKeyStrings().put(Keyboard.KEY_RMETA,"META_D");
				l.getKeyStrings().put(Keyboard.KEY_RSHIFT,"SHIFT_D");
				l.getKeyStrings().put(Keyboard.KEY_APPS,"APPS");
				l.getKeyStrings().put(Keyboard.KEY_POWER,"POWER");
				l.getKeyStrings().put(Keyboard.KEY_SLEEP,"SLEEP");
				
				return l;
				
			default:
				System.err.println("--------------");
				System.err.println("CRITICAL ERROR");
	    		System.err.println("Invalid language.");
				System.err.println("LanguageID "+lId);
				System.err.println("--------------");
				return null;
		}
	}

}
