package TestClass;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import java.awt.ScrollPane;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.ListSelectionModel;

public class window extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		/**
		 * TODO
		 * Open up the file and send it to the JTable - DONE
		 * Button for add quantity?
		 * Perhaps better to replace the edit button with a series of labeled textFields and a Submit button off to the right?
		 * Need another Design discussion
		 * 
		 */
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					window frame = new window();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public window() throws IOException {
		setTitle("FabRee Inventory");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 968, 568);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		JMenu mnTodo = new JMenu("todo...");
		menuBar.add(mnTodo);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 47, 727, 451);
		contentPane.add(scrollPane);
		
		/**
		 * Create the inventoryItem array and send it an ArrayList
		 */
		DataListReadWrite inv = new DataListReadWrite("../INV/src/TestClass/testFile.txt");
		ArrayList<InventoryItem> arrayList =  inv.getList();
		table = new JTable(inv.getTableArray(), inv.getColumnNames());
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowSelectionAllowed(false);
		table.setColumnSelectionAllowed(false);
		table.setCellSelectionEnabled(false);
		
		/**
		 * This is not working as I had hoped. 
		 * The goal is to be able to change individual cells, but may just have to circumvent that to the edit button
		 * Actually yes, this is what will be done.
		 * Will clean this up on next Commit
		 */
		table.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				if(table.getSelectedRow() != -1) {
					Object[] obj = {table.getValueAt(table.getSelectedRow(), 1),
							table.getValueAt(table.getSelectedRow(), 2),
							table.getValueAt(table.getSelectedRow(), 3),
							table.getValueAt(table.getSelectedRow(), 4),
							table.getValueAt(table.getSelectedRow(), 6),};
					inv.editListItem(obj, table.getSelectedRow());
					inv.saveList();
					try {
						table = new JTable(inv.getTableArray(), inv.getColumnNames());
						
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		scrollPane.setViewportView(table);
		
		JButton NewItemButton = new JButton("New Inventory Item");
		NewItemButton.setFont(new Font("Beirut", Font.BOLD, 15));
		NewItemButton.setToolTipText("Create new inventory item");
		NewItemButton.addActionListener(new ActionListener() {
			/**
			 * Creates an Instance of the ItemCreate window
			 */
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							ItemCreate window = new ItemCreate(inv);
							//Note the passing of the object
							window.frmItemCreation.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		NewItemButton.setBounds(751, 47, 205, 41);
		contentPane.add(NewItemButton);
		
		/**
		 * TODO
		 * Edit Item button should open up an instance of ItemCreate with the text fields already filled in with current values.
		 * Create new constructor for ItemCreate that accepts an array containing the values of the selected row.
		 * The constructor should set the text of the fields to the values currently in the row
		 */
		JButton EditItemButton = new JButton("Edit Item");
		EditItemButton.setToolTipText("Create new inventory item");
		EditItemButton.setFont(new Font("Beirut", Font.BOLD, 15));
		EditItemButton.setBounds(751, 100, 205, 41);
		contentPane.add(EditItemButton);
		
	}
}
/**
 * Depreciated Methods 
 * Moved to DataListReadWrite class
 * Didn't make sense for them to be here	
 */
/*
	public Object[][] getTableArray() throws FileNotFoundException, IOException
	{
		DataListReadWrite inventoryData = new DataListReadWrite("../INV/src/TestClass/testFile.txt");
		ArrayList<InventoryItem> arrayList =  inventoryData.getList();
		Object[][] tableData = new Object[arrayList.size()][8];
		for(int i = 0; i < arrayList.size(); i++) {
			tableData[i][0] = arrayList.get(i).getProdNum();
			tableData[i][1] = arrayList.get(i).getDescription();
			tableData[i][2] = arrayList.get(i).getCategory();
			tableData[i][3] = arrayList.get(i).getWholesalePrice();
			tableData[i][4] = arrayList.get(i).getRetailPrice();
			tableData[i][5] = arrayList.get(i).getProfitMargin();
			tableData[i][6] = arrayList.get(i).getQuantity();
			tableData[i][7] = arrayList.get(i).getAssetValue();
		}
		return tableData;	
		
	}
	public Object[][] getTableArray(DataListReadWrite inventoryData) throws FileNotFoundException, IOException
	{
		ArrayList<InventoryItem> arrayList =  inventoryData.getList();
		Object[][] tableData = new Object[arrayList.size()][8];
		for(int i = 0; i < arrayList.size(); i++) {
			tableData[i][0] = arrayList.get(i).getProdNum();
			tableData[i][1] = arrayList.get(i).getDescription();
			tableData[i][2] = arrayList.get(i).getCategory();
			tableData[i][3] = arrayList.get(i).getWholesalePrice();
			tableData[i][4] = arrayList.get(i).getRetailPrice();
			tableData[i][5] = arrayList.get(i).getProfitMargin();
			tableData[i][6] = arrayList.get(i).getQuantity();
			tableData[i][7] = arrayList.get(i).getAssetValue();
		}
		return tableData;	
		
	}
}

	public Object[][] getTableArray(ArrayList<InventoryItem> arrayList) throws FileNotFoundException, IOException
	{
		Object[][] tableData = new Object[arrayList.size()][8];
		for(int i = 0; i < arrayList.size(); i++) {
			tableData[i][0] = arrayList.get(i).getProdNum();
			tableData[i][1] = arrayList.get(i).getDescription();
			tableData[i][2] = arrayList.get(i).getCategory();
			tableData[i][3] = arrayList.get(i).getWholesalePrice();
			tableData[i][4] = arrayList.get(i).getRetailPrice();
			tableData[i][5] = arrayList.get(i).getProfitMargin();
			tableData[i][6] = arrayList.get(i).getQuantity();
			tableData[i][7] = arrayList.get(i).getAssetValue();
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
	}
*/

