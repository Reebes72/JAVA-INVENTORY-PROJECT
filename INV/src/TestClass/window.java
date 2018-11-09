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
import java.awt.event.ActionEvent;
import java.awt.Font;

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
		 * Open up the file and send it to the JTable
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
	 */
	public window() {
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
		
		table = new JTable();
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
							ItemCreate window = new ItemCreate();
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
		 */
		JButton EditItemButton = new JButton("Edit Item");
		EditItemButton.setToolTipText("Create new inventory item");
		EditItemButton.setFont(new Font("Beirut", Font.BOLD, 15));
		EditItemButton.setBounds(751, 100, 205, 41);
		contentPane.add(EditItemButton);
		
	}
}
