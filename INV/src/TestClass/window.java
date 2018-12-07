/**
 * Main window for the Inventory program.
 * Loads up the current inventory file and displays it's contents
 * 
 * @author <A HREF="mailto:acreeves72@gmail.com">Andy Reeves</A>
 * @version 2018-12-05
 *
 * @instructions
 *	Click the new button to bring up the ItemCreate window
 *	Fill in the details of the Inventory item you would like to add.
 *	Submitting the inventory item adds it to the inventory list
 *	Exiting closes the ItemCreate window
 *
 *	Selecting an item on the window screen and clicking the edit item button will open up a filled instance of the ItemCreate window
 *	
 *	Delete Item will remove the item from the Inventory file
 *
 *	
 *  TODO
 *		Implement a file system
 *		GUI item to allow selection of row and change of quantity from main window screen
 *		
 **/

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
import java.awt.Frame;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.ListSelectionModel;
import javax.swing.Timer;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JList;
import java.awt.Choice;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.AbstractListModel;
import java.awt.Label;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class window extends JFrame 
{
	private final String VERSION_ID = "2018-12-05";
	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;

	/*
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					window frame = new window();
					frame.setVisible(true);
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public window() throws IOException 
	{
		setTitle("Inventory v." + VERSION_ID );
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1092, 523);
		
		//Creates the working Inventory file
		DataListReadWrite inv = new DataListReadWrite("testFile.txt");
		
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmNewFile = new JMenuItem("New File");
		mnFile.add(mntmNewFile);
		
		JMenuItem mntmOpenFile = new JMenuItem("Open File...");
		mnFile.add(mntmOpenFile);
		
		JSeparator separator = new JSeparator();
		mnFile.add(separator);
		
		Label label = new Label("    Recent Files...");
		mnFile.add(label);
		
		JList list = new JList(inv.getRecents());
		list.addListSelectionListener(new ListSelectionListener() 
		{
			/**
			 * Trying to get this to close the current window and open a new one containing file contents.
			 * 
			 * Will work on after school project
			 * 
			 */
			public void valueChanged(ListSelectionEvent e) 
			{
				String[] str = inv.getRecents();
				inv.setFileName(str[list.getSelectedIndex()]);
					
			}
		}
		);
		mnFile.add(list);

		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		JMenuItem HelpMenuItem = new JMenuItem("Help");
		HelpMenuItem.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				String message = "New Inventory Item:\nBring up window to create a new inventory item."
						+ "\nSubmit button will save the item to the file."
						+ "\n\nEdit Item:\nBring up window to edit the selected Inventory Item"
						+ "\n\nDelete Item:\nDeletes the selected item."
						+ "\n\nThe Inventory is saved after every item.";
				JOptionPane.showMessageDialog(HelpMenuItem, 
						message);
			}
		});
		menuBar.add(HelpMenuItem);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 69, 1068, 356);
		contentPane.add(scrollPane);
		
		/**
		 * Create the inventoryItem array and send it an ArrayList
		 */

		table = new JTable(inv.getTableArray(), inv.getColumnNames());
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowSelectionAllowed(false);
		table.setColumnSelectionAllowed(false);
		table.setCellSelectionEnabled(false);
		
		/**
		 * Gets the last selected index every 10 seconds and refreshes the JTable
		 */
		Timer timer=new Timer(10000, new ActionListener()
		{
	         public void actionPerformed(ActionEvent e)
	         {
	        	 int row = table.getSelectedRow();
	        	 refresh(inv); // Call to refresh JTable
	        	 if(row != -1) 
	        	 {
	        		 table.setRowSelectionInterval(row, row);
	        	 }
	         }
	     }
		);
	     timer.start();
		
		
		scrollPane.setViewportView(table);
		
		JButton NewItemButton = new JButton("New Inventory Item");
		NewItemButton.setFont(new Font("Beirut", Font.BOLD, 15));
		NewItemButton.setToolTipText("Create new inventory item");
		NewItemButton.addActionListener(new ActionListener() {
			/**
			 * Creates an Instance of the ItemCreate window
			 */
			public void actionPerformed(ActionEvent e) 
			{
				EventQueue.invokeLater(new Runnable() 
				{
					public void run()
					{
						try
						{
							ItemCreate form = new ItemCreate(inv);
							form.frmItemCreation.setVisible(true);
						} 
						catch (Exception e)
						{
							e.printStackTrace();
						}
					}
				}
				);
			}
		}
		);
		NewItemButton.setBounds(12, 16, 267, 41);
		contentPane.add(NewItemButton);

		JButton EditItemButton = new JButton("Edit Item");
		EditItemButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				EventQueue.invokeLater(new Runnable() 
				{
					public void run() 
					{
						try 
						{
							if(table.getSelectedRow() != -1) 
							{
							ItemCreate window = new ItemCreate(inv, table.getSelectedRow());
							window.frmItemCreation.setVisible(true);
							}
						}
						catch (Exception e) 
						{
							e.printStackTrace();
						}
					}
				}
				);
				
			}
		}
		);
		
		EditItemButton.setToolTipText("Edit selected inventory item");
		EditItemButton.setFont(new Font("Beirut", Font.BOLD, 15));
		EditItemButton.setBounds(396, 16, 299, 41);
		contentPane.add(EditItemButton);
		
		//Calls removeItem method to get rid of the object in the array
		JButton deleteButton = new JButton("Delete Item");
		deleteButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(table.getSelectedRow() != -1) 
				{
				inv.removeItem(table.getSelectedRow());
				refresh(inv);
				}
			}
		}
		);
		
		deleteButton.setToolTipText("Delete Item");
		deleteButton.setFont(new Font("Beirut", Font.BOLD, 15));
		deleteButton.setBounds(813, 16, 267, 41);
		contentPane.add(deleteButton);
		
			
	}
	
	/**
	 * Creates a new JTable will the current data in the DataListReadWrite inv file
	 * @param inv Current DataListReadWrite object
	 */
	public void refresh(DataListReadWrite inv) 
	{
		try 
		{
			table = new JTable(inv.getTableArray(), inv.getColumnNames());
			scrollPane.setViewportView(table);
		}
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}


