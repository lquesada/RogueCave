package test.com.elezeta.roguecave;

import static org.junit.Assert.*;

import java.io.OutputStream;
import java.io.PrintStream;

import org.junit.Test;

import com.elezeta.roguecave.Game;
import com.elezeta.roguecave.data.GameID;
import com.elezeta.roguecave.data.GameFactory;
import com.elezeta.roguecave.gui.RogueCaveGUI;

public class GamesTest {

	@Test
	public void testGames() { 
		PrintStream dummyStream = new PrintStream(new OutputStream() {
			@Override
			public void write(int b) {
				// NO-OP
			}
		});
		System.setErr(dummyStream);
		GameID values[] = GameID.values();
		boolean error = false;
		for (int i = 0;i < values.length;i++) {
			GameID gId = values[i];
			Game game = GameFactory.generateGame(gId,RogueCaveGUI.config.getAutoName());
			if (game.getGameID()==null) {
				System.out.println("UNDECLARED GAME "+gId);
				error = true;
			}
		}
		assertFalse(error);
	}
	
}
