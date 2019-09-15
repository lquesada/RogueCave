package com.elezeta.roguecave.gui.gamestate;

import java.util.LinkedList;
import com.elezeta.roguecave.Game;

public class GameState {
	
	private LinkedList<Game> singlePlayerSavedGames = new LinkedList<Game>();
	
	private LinkedList<Game> multiplayerSavedGames = new LinkedList<Game>();

	public LinkedList<Game> getSinglePlayerSavedGames() {
		return singlePlayerSavedGames;
	}

	public LinkedList<Game> getMultiplayerSavedGames() {
		return multiplayerSavedGames;
	}

}
