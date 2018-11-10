package TestClass;

import java.util.*;
import java.io.*;
/**
 * 
 * @author kingreebes
 * Stores inventoryItem objects
 * reads/writes them to file.
 * Format of objects being saved is ArrayList<InventoryItem>
 */
public class DataListReadWrite
{
	private ArrayList<InventoryItem> list;
	private String file;
	
	public DataListReadWrite(String fileName) throws IOException 
	{
		this.file = fileName;
		this.list = new ArrayList<InventoryItem>();
		this.populateList();
	}

	public void populateList() throws IOException
	{
		try {
			
			FileInputStream fileIn = new FileInputStream(file);
			ObjectInputStream objIn = new ObjectInputStream(fileIn);
			this.list = (ArrayList<InventoryItem>)objIn.readObject();
			objIn.close();
			fileIn.close();
		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
			catch(FileNotFoundException e) {
				System.out.println(e.getMessage());
				File createFile = new File(this.file);
			}

	}
	public void saveList()
	{
		try
		{
			FileOutputStream fileOut = new FileOutputStream(file);
			ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
			
			objOut.writeObject(list);
			
			objOut.close();
			fileOut.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public void updateList(InventoryItem item) {
		this.list.add(item);
	}
	public void appendList(InventoryItem item)
	{
		try
		{
			FileOutputStream fileOut = new FileOutputStream(file, true);
			ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
			
			objOut.writeObject(item);
			
			objOut.close();
			fileOut.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public void removeItem(int index)
	{
		list.remove(index);
	}
	
	public ArrayList<InventoryItem> getList(){
		return this.list;
	}
}
