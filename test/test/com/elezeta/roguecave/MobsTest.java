package test.com.elezeta.roguecave;

import static org.junit.Assert.*;

import org.junit.Test;

import com.elezeta.roguecave.data.MobID;
import com.elezeta.roguecave.data.MobFactory;
import com.elezeta.roguecave.entities.Mob;

public class MobsTest {

	@Test
	public void testMobs() { 
		MobID values[] = MobID.values();
		for (int i = 0;i < values.length;i++) {
			MobID iId = values[i];
			Mob mob = MobFactory.getMob(iId);
			assertFalse(mob.equals(new Mob()));
		}
	}
	
}
