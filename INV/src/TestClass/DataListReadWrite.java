package TestClass;

import java.util.*;
import java.io.*;

public class DataListReadWrite
{
	private ArrayList<InventoryItem> list;
	private String file;
	
	public DataListReadWrite(String fileName) throws IOException 
	{
		this.file = fileName;
		populateList();
	}
	
	public void populateList() throws IOException
	{
		try {
			FileInputStream fileIn = new FileInputStream(file);
			ObjectInputStream objIn = new ObjectInputStream(fileIn);
		
		
			while(objIn.readObject() != null)
			{
				list.add((InventoryItem)objIn.readObject());
			}
			objIn.close();
			fileIn.close();
		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void writeList()
	{
		try
		{
			FileOutputStream fileOut = new FileOutputStream(file);
			ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
			
			for(InventoryItem item: list)
			{
				objOut.writeObject(item);
			}
			objOut.close();
			fileOut.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public void updateList(InventoryItem item) {
		list.add(item);
		appendList(item);
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
		writeList();
	}
}
