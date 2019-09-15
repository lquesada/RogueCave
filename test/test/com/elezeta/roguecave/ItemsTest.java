package test.com.elezeta.roguecave;

import static org.junit.Assert.*;

import java.io.OutputStream;
import java.io.PrintStream;

import org.junit.Test;

import com.elezeta.roguecave.data.ItemID;
import com.elezeta.roguecave.data.ItemFactory;
import com.elezeta.roguecave.inventory.Item;

public class ItemsTest {

	@Test
	public void testItems() { 
		PrintStream dummyStream = new PrintStream(new OutputStream() {
			@Override
			public void write(int b) {
				// NO-OP
			}
		});
		System.setErr(dummyStream);
		ItemID values[] = ItemID.values();
		boolean error = false;
		for (int i = 0;i < values.length;i++) {
			ItemID iId = values[i];
			Item item = ItemFactory.generateItem(iId);
			if (item.getItemID()==null) {
				System.out.println("UNDECLARED ITEM "+iId);
				error = true;
			}
			else if (item.getSprite()==new Item().getSprite()) {
				System.out.println("ITEM WITHOUT SPRITE "+iId);
				error = true;
			}
			else if (item.getInventorySprite()==new Item().getInventorySprite()) {
				System.out.println("ITEM WITHOUT INVENTORY SPRITE "+iId);
				error = true;
			}
		}
		assertFalse(error);
	}
	
}
