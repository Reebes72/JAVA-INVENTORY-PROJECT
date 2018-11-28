package TestClass;

import java.io.IOException;
import java.util.ArrayList;
/** 
 * 
 * @author kingreebes
 *Test file to check if values that have already been saved to a file can be read to the console.
 *Successful so far.
 */
public class testRead {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String fileName = "src/TestClass/testFile.bin";
		System.out.println("Test the reading of file contents.");
		try 
		{
			DataListReadWrite test = new DataListReadWrite(fileName);
			ArrayList<InventoryItem> hold = test.getList();
			for(InventoryItem item: hold) {
				System.out.println(item.getDescription());
				System.out.println(item.getCategory());
			}
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Exception");
		}
		System.out.println("End of program");
		
	}

}
