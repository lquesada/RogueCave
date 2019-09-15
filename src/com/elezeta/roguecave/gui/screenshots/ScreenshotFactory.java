package com.elezeta.roguecave.gui.screenshots;

import com.elezeta.roguecave.Game;
import com.elezeta.roguecave.data.GameFactory;
import com.elezeta.roguecave.data.GameID;

public class ScreenshotFactory {

	private ScreenshotFactory() {
	}

	public static Game generateScreenshot() {
		//TODO uno de los desbloqueados
		Game game = GameFactory.generateGame(GameID.level1,"");
		for (int i = 0;i < 1000;i++) {
			game.getPlayer().setForceX((float)Math.random()*2f-1f);
			game.getPlayer().setForceY((float)Math.random()*2f-1f);
			game.tick((float)Math.random()*1f);
		}
		return game;
	}

}
