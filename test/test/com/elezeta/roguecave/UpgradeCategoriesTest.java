package test.com.elezeta.roguecave;

import static org.junit.Assert.*;

import java.io.OutputStream;
import java.io.PrintStream;

import org.junit.Test;

import com.elezeta.roguecave.data.UpgradeCategoryFactory;
import com.elezeta.roguecave.data.UpgradeCategoryID;
import com.elezeta.roguecave.upgrades.UpgradeCategory;

public class UpgradeCategoriesTest {

	@Test
	public void testUpgradeCategories() { 
		PrintStream dummyStream = new PrintStream(new OutputStream() {
			@Override
			public void write(int b) {
				// NO-OP
			}
		});
		System.setErr(dummyStream);
		UpgradeCategoryID values[] = UpgradeCategoryID.values();
		boolean error = false;
		for (int i = 0;i < values.length;i++) {
			UpgradeCategoryID uId = values[i];
			UpgradeCategory upc = UpgradeCategoryFactory.generateUpgradeCategory(uId);
			if (upc.getUpgradeCategoryID()==null) {
				System.out.println("UNDECLARED UPGRADE CATEGORY "+uId);
				error = true;
			}
			else if (upc.getGraphic()==new UpgradeCategory().getGraphic()) {
				System.out.println("UPGRADE CATEGORY WITHOUT GRAPHIC "+uId);
				error = true;
			}
		}
		assertFalse(error);
	}
	
}
