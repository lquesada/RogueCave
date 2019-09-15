package test.com.elezeta.roguecave.gui.internationalization;

import static org.junit.Assert.*;

import org.junit.Test;

import com.elezeta.roguecave.data.AmmoID;
import com.elezeta.roguecave.data.GameID;
import com.elezeta.roguecave.data.ItemID;
import com.elezeta.roguecave.data.UpgradeID;
import com.elezeta.roguecave.data.UpgradeCategoryID;
import com.elezeta.roguecave.data.ProfessionID;
import com.elezeta.roguecave.gui.ControlID;
import com.elezeta.roguecave.gui.StringID;
import com.elezeta.roguecave.gui.ValidKeys;
import com.elezeta.roguecave.gui.internationalization.Language;
import com.elezeta.roguecave.gui.internationalization.LanguageFactory;
import com.elezeta.roguecave.gui.internationalization.LanguageID;
import com.elezeta.roguecave.inventory.ItemType;

public class LanguagesTest {
	
	@Test
	public void languagesTest() {
		LanguageID values[] = LanguageID.values();
		for (int i = 0;i < values.length;i++) {
			LanguageID lId = values[i];
			Language l = LanguageFactory.generateLanguage(lId);
			assertNotNull(l);
		}
		
	}

	@Test
	public void languageStringsTest() {
		LanguageID values[] = LanguageID.values();
		boolean error = false;
		for (int i = 0;i < values.length;i++) {
			LanguageID lId = values[i];
			Language l = LanguageFactory.generateLanguage(lId);

			{
				ItemID v[] = ItemID.values();
				for (int j = 0;j < v.length;j++) {
					if (l.getString(v[j]) == null) {
						System.out.println("MISSING "+lId+" ItemID "+v[j]);
						error = true;
					}
				}
			}
			{
				ProfessionID v[] = ProfessionID.values();
				for (int j = 0;j < v.length;j++) {
					if (l.getString(v[j]) == null) {
						System.out.println("MISSING "+lId+" ProfessionID "+v[j]);
						error = true;
					}
				}
			}
			{
				AmmoID v[] = AmmoID.values();
				for (int j = 0;j < v.length;j++)
					if (l.getString(v[j]) == null) {
						System.out.println("MISSING "+lId+" AmmoID "+v[j]);
						error = true;
					}
			}
			{
				AmmoID v[] = AmmoID.values();
				for (int j = 0;j < v.length;j++)
					if (l.getString(v[j],true) == null) {
						System.out.println("MISSING "+lId+" Plural AmmoID "+v[j]);
						error = true;
					}
			}
			{
				StringID v[] = StringID.values();
				for (int j = 0;j < v.length;j++)
					if (l.getString(v[j]) == null) {
						System.out.println("MISSING "+lId+" String "+v[j]);
						error = true;
					}
			}
			{
				ControlID v[] = ControlID.values();
				for (int j = 0;j < v.length;j++)
					if (l.getString(v[j]) == null) {
						System.out.println("MISSING "+lId+" ControlID "+v[j]);
						error = true;
					}
			}
			{
				ItemType v[] = ItemType.values();
				for (int j = 0;j < v.length;j++)
					if (l.getString(v[j]) == null) {
						System.out.println("MISSING "+lId+" ItemType "+v[j]);
						error = true;
					}
			}
			{
				UpgradeID v[] = UpgradeID.values();
				for (int j = 0;j < v.length;j++)
					if (l.getString(v[j]) == null) {
						System.out.println("MISSING "+lId+" Upgrade "+v[j]);
						error = true;
					}
			}
			{
				UpgradeCategoryID v[] = UpgradeCategoryID.values();
				for (int j = 0;j < v.length;j++)
					if (l.getString(v[j]) == null) {
						System.out.println("MISSING "+lId+" UpgradeCategory "+v[j]);
						error = true;
					}
			}

			{
				Object v[] = ValidKeys.validKeys.toArray();
				for (int j = 0;j < v.length;j++)
					if (l.getKeyString((Integer)v[j]) == null) {
						System.out.println("MISSING "+lId+" KeyString "+v[j]);
						error = true;
					}
			}
			{
				GameID v[] = GameID.values();
				for (int j = 0;j < v.length;j++)
					if (l.getString(v[j]) == null) {
						System.out.println("MISSING "+lId+" GameID "+v[j]);
						error = true;
					}
			}

		}
		assertFalse(error);
	}

	
}
