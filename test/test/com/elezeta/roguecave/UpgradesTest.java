package test.com.elezeta.roguecave;

import static org.junit.Assert.*;

import java.io.OutputStream;
import java.io.PrintStream;

import org.junit.Test;

import com.elezeta.roguecave.data.PlayerFactory;
import com.elezeta.roguecave.data.ProfessionID;
import com.elezeta.roguecave.data.UpgradeFactory;
import com.elezeta.roguecave.data.UpgradeID;
import com.elezeta.roguecave.entities.Player;
import com.elezeta.roguecave.upgrades.Upgrade;

public class UpgradesTest {

	@Test
	public void testUpgrades() { 
		PrintStream dummyStream = new PrintStream(new OutputStream() {
			@Override
			public void write(int b) {
				// NO-OP
			}
		});
		System.setErr(dummyStream);
		UpgradeID values[] = UpgradeID.values();
		boolean error = false;
		for (int i = 0;i < values.length;i++) {
			UpgradeID uId = values[i];
			Upgrade up = UpgradeFactory.generateUpgrade(uId);
			if (up.getUpgradeID()==null) {
				System.out.println("UNDECLARED UPGRADE "+uId);
				error = true;
			}
			else if (up.getSprite()==new Upgrade().getSprite()) {
				System.out.println("UPGRADE WITHOUT SPRITE "+uId);
				error = true;
			}
		}
		assertFalse(error);
	}

	@Test
	public void humanUpgradeTest() {
		Player p = PlayerFactory.generatePlayer(ProfessionID.naught,"Test");
		assertNull(p.getAcquiredUpgrades().get(UpgradeID.HUMAN));
		UpgradeFactory.generateUpgrade(UpgradeID.HUMAN).apply(p);
		assertEquals((Integer)1,p.getAcquiredUpgrades().get(UpgradeID.HUMAN));
		UpgradeFactory.generateUpgrade(UpgradeID.HUMAN).apply(p);
		assertEquals((Integer)1,p.getAcquiredUpgrades().get(UpgradeID.HUMAN));
	}

	@Test
	public void humanUpgradeTest2() {
		Player p = PlayerFactory.generatePlayer(ProfessionID.naught,"Test");
		assertNull(p.getAcquiredUpgrades().get(UpgradeID.HUMAN));
		UpgradeFactory.generateUpgrade(UpgradeID.HUMAN).apply(p);
		assertEquals((Integer)1,p.getAcquiredUpgrades().get(UpgradeID.HUMAN));
		Upgrade maxhp =	UpgradeFactory.generateUpgrade(UpgradeID.MAXHP);
		p.setUpgradePoints(maxhp.getCost()*2);
		assertNull(p.getAcquiredUpgrades().get(UpgradeID.MAXHP));
		maxhp.apply(p);
		assertEquals((Integer)1,p.getAcquiredUpgrades().get(UpgradeID.MAXHP));
		maxhp.apply(p);
		assertEquals((Integer)2,p.getAcquiredUpgrades().get(UpgradeID.MAXHP));
		maxhp.apply(p);
		assertEquals((Integer)2,p.getAcquiredUpgrades().get(UpgradeID.MAXHP));
		
		
	}

}
