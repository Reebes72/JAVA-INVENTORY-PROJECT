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
	private ArrayList<InventoryItem> list = new ArrayList<>();
	private String file;
	private Boolean saved = true;
	private ArrayList<String> recents = new ArrayList<>();
	
	//Default constructor upon program open
	public DataListReadWrite(String fileName) throws IOException 
	{
		if(fileName == null) {
			File file = new File("../INV/src/TestClass/recents.ini");
			DataInputStream in = new DataInputStream(new FileInputStream(file));
			
			String line = null;
			while((line = in.readLine()) != null) {
				recents.add(0, line);
			}
			in.close();
			setFileName(recents.get(0));
			populateList();
		}
		setFileName(fileName);
		populateList();
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
			this.saved = true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public void updateList(InventoryItem item) {
		this.list.add(item);
		this.saved = false;
	}
	public void updateList(Object[] array) {
		this.list.add(new InventoryItem((String)array[0], (String)array[1], (Double)array[2], (Double)array[3], (int)array[4], list.size() + 1));
		this.saved = false;
	}
	public void editListItem(Object[] array, int index) {
		this.list.remove(index);
		this.list.add(index, new InventoryItem((String)array[0], (String)array[1], (Double)array[2], (Double)array[3], (int)array[4], index + 1));
		this.list.get(index).setProdNum(index + 1);
		this.saved = false;
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
			this.saved = true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public void removeItem(int index)
	{
		list.remove(index);
		this.saved = false;
	}
	
	public ArrayList<InventoryItem> getList(){
		return this.list;
	}
	public Object[][] getTableArray() throws FileNotFoundException, IOException
	{
		Object[][] tableData = new Object[this.list.size()][8];
		for(int i = 0; i < this.list.size(); i++) {
			tableData[i][0] = this.list.get(i).getProdNum();
			tableData[i][1] = this.list.get(i).getDescription();
			tableData[i][2] = this.list.get(i).getCategory();
			tableData[i][3] = this.list.get(i).getWholesalePrice();
			tableData[i][4] = this.list.get(i).getRetailPrice();
			tableData[i][5] = this.list.get(i).getProfitMargin();
			tableData[i][6] = this.list.get(i).getQuantity();
			tableData[i][7] = this.list.get(i).getAssetValue();
		}
		return tableData;	
		
	}
	public String[] getColumnNames() {
		String[] colNames = {
				"Product Number",
				"Description",
				"Category",
				"Wholesale Price",
				"Retail Price",
				"Profit Margin",
				"Current Quantity",
				"Asset Value"};
		return colNames;
		}
	
	public void setFileName(String fileName) {
		this.file = "../INV/src/TestClass/invFiles/" + fileName;
	}
	public String[] getRecents(){
		String[] array = new String[recents.size()];
		for(int i = 0; i < array.length; i ++) {
			array[i] = recents.get(i);
		}
		return array;
	}
	public String getRecentAt(int index) {
		return recents.get(index);
	}
	/**
	 * Checks for the fileName in the current List
	 * @param fileName
	 */
	public void addRecent(String fileName) {
		for(int i = 0; i < this.recents.size(); i++) {
			if(this.recents.get(i).equals(fileName)) {
				this.recents.remove(i);
				this.recents.add(fileName);
				i = this.recents.size();
			}
			while(this.recents.size() > 5) {
				this.recents.remove(5);
			}
		}
		saveList();
	}
}

