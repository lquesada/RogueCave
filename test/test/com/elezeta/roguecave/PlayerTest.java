package test.com.elezeta.roguecave;

import static org.junit.Assert.*;

import org.junit.Test;

import com.elezeta.roguecave.Constants;
import com.elezeta.roguecave.data.PlayerFactory;
import com.elezeta.roguecave.data.ProfessionID;
import com.elezeta.roguecave.entities.Player;

public class PlayerTest {

	@Test
	public void factoryTest() {
		Player p = PlayerFactory.generatePlayer(ProfessionID.naught,"Anonymous");
		assertEquals(true,p.isAlive());
		assertEquals(1,p.getLevel());
		assertEquals(0,p.getExp());
		assertEquals(0,p.getPoints());
		assertEquals(20,p.getStats().getMaxHp(),Constants.EPS);
		assertEquals(1,p.getStats().getHpRegen(),Constants.EPS);
		assertEquals(p.getStats().getMaxHp(),p.getHp(),Constants.EPS);
		assertEquals(10,p.getStats().getSpeed(),Constants.EPS);
		assertEquals(10,p.getStats().getRecovery(),Constants.EPS);
		assertEquals(10,p.getStats().getAccuracy(),Constants.EPS);
		assertEquals("Anonymous",p.getName());
		assertEquals(ProfessionID.naught,p.getProfession());
	}
	
	@Test
	public void levellingTest1() {
		Player p = PlayerFactory.generatePlayer(ProfessionID.naught,"Test");
		assertEquals(1,p.getLevel());
		assertEquals(0,p.getExp());
		assertEquals(100,p.getUpgradePoints());
		p.gainExp(1);
		assertEquals(1,p.getLevel());
		assertEquals(1,p.getExp());
		assertEquals(100,p.getUpgradePoints());
		p.gainExp(p.getExpForLevelUp()-1);
		assertEquals(2,p.getLevel());
		assertEquals(0,p.getExp());
		assertEquals(101,p.getUpgradePoints());
	}

	@Test
	public void levellingTest2() {
		Player p = PlayerFactory.generatePlayer(ProfessionID.naught,"Test");
		for (int i = 0;i < 9;i++) {
			p.gainExp(p.getExpForLevelUp());
			assertEquals(i+2,p.getLevel());
			assertEquals(100+i+1,p.getUpgradePoints());
			
		}
		p.gainExp(p.getExpForLevelUp());
		assertEquals(11,p.getLevel());
		assertEquals(111,p.getUpgradePoints());
		p.gainExp(p.getExpForLevelUp());
		assertEquals(12,p.getLevel());
		assertEquals(113,p.getUpgradePoints());
	}

	@Test
	public void hurtAndHealTest() {
		Player p = PlayerFactory.generatePlayer(ProfessionID.naught,"Test");
		assertEquals(20,p.getHp(),Constants.EPS);
		assertEquals(true,p.isAlive());
		p.heal(1);
		assertEquals(20,p.getHp(),Constants.EPS);
		assertEquals(true,p.isAlive());
		p.hurt(1);
		assertEquals(19,p.getHp(),Constants.EPS);
		assertEquals(true,p.isAlive());
		p.heal(1);
		assertEquals(20,p.getHp(),Constants.EPS);
		assertEquals(true,p.isAlive());
		p.hurt(10);
		assertEquals(10,p.getHp(),Constants.EPS);
		assertEquals(true,p.isAlive());
		p.hurt(9);
		assertEquals(1,p.getHp(),Constants.EPS);
		assertEquals(true,p.isAlive());
		p.heal(1);
		assertEquals(2,p.getHp(),Constants.EPS);
		assertEquals(true,p.isAlive());
		p.hurt(4);
		assertEquals(0,p.getHp(),Constants.EPS);
		assertEquals(false,p.isAlive());
		p.heal(1);
		assertEquals(0,p.getHp(),Constants.EPS);
		assertEquals(false,p.isAlive());		
	}

	@Test
	public void spendAndRecoverMPTest() {
		Player p = PlayerFactory.generatePlayer(ProfessionID.naught,"Test");
		p.getSelfStats().setMaxMp(100);
		p.setMp(20);
		assertEquals(20,p.getMp(),Constants.EPS);
		p.recoverMp(1);
		assertEquals(21,p.getMp(),Constants.EPS);
		p.spendMp(21);
		assertEquals(0,p.getMp(),Constants.EPS);
		p.spendMp(20);
		p.getSelfStats().setMaxMp(80);
		assertEquals(-20,p.getMp(),Constants.EPS);
		p.recoverMp(1);
		assertEquals(-19,p.getMp(),Constants.EPS);
		p.recoverMp(19);
		assertEquals(0,p.getMp(),Constants.EPS);
	}

	/*
	@Test
	public void acquireUpgradeHealthTest() {
		Player p = new Player();
		assertEquals(null,p.getAcquiredUpgrades().get("Health"));
		assertEquals(100,p.getUpgradePoints());
		assertEquals(20,p.getHp());
		assertEquals(20,p.getMaxHp());
		p.acquireUpgrade(p.getUpgradeList().get("Health"));
		assertEquals(new Integer(1),p.getAcquiredUpgrades().get("Health"));
		assertEquals(99,p.getUpgradePoints());
		assertEquals(21,p.getHp());
		assertEquals(21,p.getMaxHp());
		p.acquireUpgrade(p.getUpgradeList().get("Health"));
		assertEquals(new Integer(2),p.getAcquiredUpgrades().get("Health"));
		assertEquals(98,p.getUpgradePoints());
		assertEquals(22,p.getHp());
		assertEquals(22,p.getMaxHp());
		p.acquireUpgrade(p.getUpgradeList().get("Health"));
		assertEquals(new Integer(3),p.getAcquiredUpgrades().get("Health"));
		assertEquals(97,p.getUpgradePoints());
		assertEquals(23,p.getHp());
		assertEquals(23,p.getMaxHp());
	}
	
	@Test
	public void acquireUpgradeHealthGainTest() {
		Player p = new Player();
		assertEquals(null,p.getAcquiredUpgrades().get("Health gain per level"));
		assertEquals(100,p.getUpgradePoints());
		assertEquals(1,p.getHpLevel());
		p.acquireUpgrade(p.getUpgradeList().get("Health gain per level"));
		assertEquals(new Integer(1),p.getAcquiredUpgrades().get("Health gain per level"));
		assertEquals(90,p.getUpgradePoints());
		assertEquals(2,p.getHpLevel());
	}

	@Test
	public void acquireUpgradeHealthRegenTest() {
		Player p = new Player();
		assertEquals(null,p.getAcquiredUpgrades().get("Health regen"));
		assertEquals(100,p.getUpgradePoints());
		assertEquals(1,p.getHpRegen());
		p.acquireUpgrade(p.getUpgradeList().get("Health regen"));
		assertEquals(new Integer(1),p.getAcquiredUpgrades().get("Health regen"));
		assertEquals(90,p.getUpgradePoints());
		assertEquals(2,p.getHpRegen());
	}

	@Test
	public void acquireUpgradeManaTest() {
		Player p = new Player();
		assertEquals(null,p.getAcquiredUpgrades().get("Mana"));
		assertEquals(100,p.getUpgradePoints());
		assertEquals(0,p.getMp());
		assertEquals(0,p.getMaxMp());
		p.acquireUpgrade(p.getUpgradeList().get("Mana"));
		assertEquals(new Integer(1),p.getAcquiredUpgrades().get("Mana"));
		assertEquals(99,p.getUpgradePoints());
		assertEquals(2,p.getMp());
		assertEquals(2,p.getMaxMp());
		p.acquireUpgrade(p.getUpgradeList().get("Mana"));
		assertEquals(new Integer(2),p.getAcquiredUpgrades().get("Mana"));
		assertEquals(98,p.getUpgradePoints());
		assertEquals(4,p.getMp());
		assertEquals(4,p.getMaxMp());
		p.acquireUpgrade(p.getUpgradeList().get("Mana"));
		assertEquals(new Integer(3),p.getAcquiredUpgrades().get("Mana"));
		assertEquals(97,p.getUpgradePoints());
		assertEquals(6,p.getMp());
		assertEquals(6,p.getMaxMp());
	}
	
	@Test
	public void acquireUpgradeManaGainTest() {
		Player p = new Player();
		assertEquals(null,p.getAcquiredUpgrades().get("Mana gain per level"));
		assertEquals(100,p.getUpgradePoints());
		assertEquals(0,p.getMpLevel());
		p.acquireUpgrade(p.getUpgradeList().get("Mana gain per level"));
		assertEquals(new Integer(1),p.getAcquiredUpgrades().get("Mana gain per level"));
		assertEquals(90,p.getUpgradePoints());
		assertEquals(1,p.getMpLevel());
	}

	@Test
	public void acquireUpgradeManaRegenTest() {
		Player p = new Player();
		assertEquals(null,p.getAcquiredUpgrades().get("Mana regen"));
		assertEquals(100,p.getUpgradePoints());
		assertEquals(1,p.getMpRegen());
		p.acquireUpgrade(p.getUpgradeList().get("Mana regen"));
		assertEquals(new Integer(1),p.getAcquiredUpgrades().get("Mana regen"));
		assertEquals(90,p.getUpgradePoints());
		assertEquals(2,p.getMpRegen());
	}

	@Test
	public void acquireUpgradeMeleeTest() {
		Player p = new Player();
		assertEquals(null,p.getAcquiredUpgrades().get("Melee"));
		assertEquals(100,p.getUpgradePoints());
		assertEquals(10,p.getMelee());
		p.acquireUpgrade(p.getUpgradeList().get("Melee"));
		assertEquals(new Integer(1),p.getAcquiredUpgrades().get("Melee"));
		assertEquals(97,p.getUpgradePoints());
		assertEquals(11,p.getMelee());
	}

	@Test
	public void acquireUpgradeRangedTest() {
		Player p = new Player();
		assertEquals(null,p.getAcquiredUpgrades().get("Ranged"));
		assertEquals(100,p.getUpgradePoints());
		assertEquals(10,p.getRanged());
		p.acquireUpgrade(p.getUpgradeList().get("Ranged"));
		assertEquals(new Integer(1),p.getAcquiredUpgrades().get("Ranged"));
		assertEquals(97,p.getUpgradePoints());
		assertEquals(11,p.getRanged());
	}

	@Test
	public void acquireUpgradeSpeedTest() {
		Player p = new Player();
		assertEquals(null,p.getAcquiredUpgrades().get("Speed"));
		assertEquals(100,p.getUpgradePoints());
		assertEquals(10,p.getSpeed());
		p.acquireUpgrade(p.getUpgradeList().get("Speed"));
		assertEquals(new Integer(1),p.getAcquiredUpgrades().get("Speed"));
		assertEquals(95,p.getUpgradePoints());
		assertEquals(11,p.getSpeed());
	}

	@Test
	public void acquireUpgradeRecoveryTest() {
		Player p = new Player();
		assertEquals(null,p.getAcquiredUpgrades().get("Recovery"));
		assertEquals(100,p.getUpgradePoints());
		assertEquals(10,p.getRecovery());
		p.acquireUpgrade(p.getUpgradeList().get("Recovery"));
		assertEquals(new Integer(1),p.getAcquiredUpgrades().get("Recovery"));
		assertEquals(95,p.getUpgradePoints());
		assertEquals(11,p.getRecovery());
	}

	@Test
	public void acquireUpgradeAccuracydTest() {
		Player p = new Player();
		assertEquals(null,p.getAcquiredUpgrades().get("Accuracy"));
		assertEquals(100,p.getUpgradePoints());
		assertEquals(10,p.getAccuracy());
		p.acquireUpgrade(p.getUpgradeList().get("Accuracy"));
		assertEquals(new Integer(1),p.getAcquiredUpgrades().get("Accuracy"));
		assertEquals(95,p.getUpgradePoints());
		assertEquals(11,p.getAccuracy());
	}
	
	@Test
	public void acquireUpgradeMagicTest() {
		Player p = new Player();
		assertEquals(null,p.getAcquiredUpgrades().get("Magic"));
		assertEquals(100,p.getUpgradePoints());
		assertEquals(10,p.getMagic());
		p.acquireUpgrade(p.getUpgradeList().get("Magic"));
		assertEquals(new Integer(1),p.getAcquiredUpgrades().get("Magic"));
		assertEquals(97,p.getUpgradePoints());
		assertEquals(11,p.getMagic());
	}

	@Test
	public void acquireUpgradeMagicMissileTest() {
		Player p = new Player();
		assertEquals(null,p.getAcquiredUpgrades().get("Spell: Magic missile"));
		assertEquals(100,p.getUpgradePoints());
		p.acquireUpgrade(p.getUpgradeList().get("Spell: Magic missile"));
		assertEquals(new Integer(1),p.getAcquiredUpgrades().get("Spell: Magic missile"));
		assertEquals(90,p.getUpgradePoints());
		p.acquireUpgrade(p.getUpgradeList().get("Spell: Magic missile"));
		assertEquals(new Integer(1),p.getAcquiredUpgrades().get("Spell: Magic missile"));
		assertEquals(90,p.getUpgradePoints());
		//TODO check
	}
	
	*/
	//TODO upgrade requisites
	
}
