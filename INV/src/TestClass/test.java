package TestClass;

import java.io.IOException;
/**
 * 
 * @author kingreebes
 * Test class to see if the class can write the objects to the file.
 * Also, generates a file that can be worked with immediately. 
 */
public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InventoryItem one = new InventoryItem("Lemon", "Fruit", 0.69, 0.99, 100, 1);
		InventoryItem two = new InventoryItem("Harpoon", "Fishing Gear", 17.99, 26.99, 20, 2);
		InventoryItem three = new InventoryItem("Trenchcoat", "Outerwear", 34.74, 69.99, 15, 3);
		InventoryItem four = new InventoryItem("Apricot", "Fruit", 0.49, 0.99, 300, 4);
		InventoryItem five = new InventoryItem("Snickers", "Candy", 0.69, 1.49, 20, 5);
		
		try {
			DataListReadWrite holding = new DataListReadWrite("src/TestClass/testFile.txt");
			holding.updateList(one);
			holding.updateList(two);
			holding.updateList(three);
			holding.updateList(four);
			holding.updateList(five);
			holding.saveList();
			for(InventoryItem item: holding.getList()) {
				System.out.println(item.getDescription());
				System.out.println(item.toString());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
