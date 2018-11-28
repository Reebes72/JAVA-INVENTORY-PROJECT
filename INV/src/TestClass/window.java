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
import javax.swing.JSeparator;
import javax.swing.JList;
import java.awt.Choice;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.AbstractListModel;
import java.awt.Label;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

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

					window frame = new window(null);
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
	public window(String fileName) throws IOException {
		setTitle("FabRee Inventory");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1092, 747);
		DataListReadWrite inv = new DataListReadWrite(fileName);
		
		
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
		list.addListSelectionListener(new ListSelectionListener() {
			/**
			 * Trying to get this to close the current window and open a new one containing file contents.
			 * 
			 * 
			 * 
			 */
			public void valueChanged(ListSelectionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							
							window frame = new window(inv.getRecentAt(list.getSelectedIndex()));
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}		
		});
		mnFile.add(list);

		
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
		scrollPane.setBounds(12, 69, 1068, 623);
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
		 * 
		 * TODO
		 * 
		 * RESELECT CELL AFTER REFRESH
		 * 
		 * 
		 * 
		 */
		Timer  timer=new Timer(10000,new ActionListener(){
	         public void actionPerformed(ActionEvent e)
	     {
	       int row = table.getSelectedRow();
	        refresh(inv);
	        if(row != -1) {
	        table.setRowSelectionInterval(row, row);
	        }
	     }
	     });
	     timer.start();
		
		
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
							ItemCreate form = new ItemCreate(inv);
							form.frmItemCreation.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		NewItemButton.setBounds(12, 16, 205, 41);
		contentPane.add(NewItemButton);
		
		/**
		 * TODO
		 * Edit Item button should open up an instance of ItemCreate with the text fields already filled in with current values.
		 * Create new constructor for ItemCreate that accepts an array containing the values of the selected row.
		 * The constructor should set the text of the fields to the values currently in the row
		 * 
		 * DONE!
		 */
		JButton EditItemButton = new JButton("Edit Item");
		EditItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							if(table.getSelectedRow() != -1) {
							ItemCreate window = new ItemCreate(inv, table.getSelectedRow());
							//Note the passing of the object
							window.frmItemCreation.setVisible(true);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
			}
		});
		EditItemButton.setToolTipText("Edit selected inventory item");
		EditItemButton.setFont(new Font("Beirut", Font.BOLD, 15));
		EditItemButton.setBounds(294, 16, 205, 41);
		contentPane.add(EditItemButton);
		
		JButton saveButton = new JButton("Save");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				inv.saveList();
				refresh(inv);
			}
		});
		saveButton.setToolTipText("Save Inventory to file.");
		saveButton.setFont(new Font("Beirut", Font.BOLD, 15));
		saveButton.setBounds(594, 16, 205, 41);
		contentPane.add(saveButton);
		
		//Calls removeItem method to get rid of the object in the array
		JButton deleteButton = new JButton("Delete Item");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() != -1) {
				inv.removeItem(table.getSelectedRow());
				refresh(inv);
				}}
		});
		deleteButton.setToolTipText("Delete Item");
		deleteButton.setFont(new Font("Beirut", Font.BOLD, 15));
		deleteButton.setBounds(875, 16, 205, 41);
		contentPane.add(deleteButton);
		
			
	}	
	public void refresh(DataListReadWrite inv) {
		try {
			table = new JTable(inv.getTableArray(), inv.getColumnNames());
			scrollPane.setViewportView(table);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}


