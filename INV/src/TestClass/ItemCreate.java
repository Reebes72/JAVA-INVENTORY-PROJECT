package TestClass;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Window.Type;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ItemCreate {

	JFrame frmItemCreation;
	private JTextField descriptionTextField;
	private JTextField categoryTextField;
	private JTextField wholesaleTextField;
	private JTextField retailTextField;
	private JTextField quantityTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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

	/**
	 * Create the application.
	 */
	public ItemCreate() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmItemCreation = createJFrame();
		frmItemCreation.setBounds(100, 100, 279, 262);
		frmItemCreation.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 18, 271, 216);
		frmItemCreation.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel descriptionLabel = new JLabel("Description");
		descriptionLabel.setBounds(6, 6, 80, 16);
		panel.add(descriptionLabel);
		
		JLabel categoryLabel = new JLabel("Category");
		categoryLabel.setBounds(6, 34, 80, 16);
		panel.add(categoryLabel);
		
		JLabel wholesaleLabel = new JLabel("Wholesale Price");
		wholesaleLabel.setBounds(6, 62, 97, 16);
		panel.add(wholesaleLabel);
		
		JLabel retailLabel = new JLabel("Retail Price");
		retailLabel.setBounds(6, 90, 97, 16);
		panel.add(retailLabel);
		
		JLabel quantityLabel = new JLabel("Quantity");
		quantityLabel.setBounds(6, 118, 97, 16);
		panel.add(quantityLabel);
		
		descriptionTextField = new JTextField();
		descriptionTextField.setBounds(132, 1, 130, 26);
		panel.add(descriptionTextField);
		descriptionTextField.setColumns(10);
		
		categoryTextField = new JTextField();
		categoryTextField.setColumns(10);
		categoryTextField.setBounds(132, 29, 130, 26);
		panel.add(categoryTextField);
		
		wholesaleTextField = new JTextField();
		wholesaleTextField.setColumns(10);
		wholesaleTextField.setBounds(132, 57, 130, 26);
		panel.add(wholesaleTextField);
		
		retailTextField = new JTextField();
		retailTextField.setColumns(10);
		retailTextField.setBounds(132, 85, 130, 26);
		panel.add(retailTextField);
		
		quantityTextField = new JTextField();
		quantityTextField.setColumns(10);
		quantityTextField.setBounds(132, 113, 130, 26);
		panel.add(quantityTextField);

		JButton submitButton = new JButton("Submit");
		submitButton.addActionListener(new ActionListener() {
			/**
			 * Grabs textField values and sends them to a constructor
			 * Clears the text fields
			 * 
			 * TO-DO
			 * 
			 * Add to the ArrayList
			 * Add to the JTable
			 */
			public void actionPerformed(ActionEvent e) {
				InventoryItem item = new InventoryItem(descriptionTextField.getText(), 
						categoryTextField.getText(), 
						Double.parseDouble(wholesaleTextField.getText()),
						Double.parseDouble(retailTextField.getText()),
						Integer.parseInt(quantityTextField.getText()));
				
				clearTextFields();
				
			}
		});
		submitButton.setBounds(6, 146, 117, 29);
		panel.add(submitButton);
		
		JButton clearButton = new JButton("Clear");
		clearButton.addActionListener(new ActionListener() {
			/**
			 * Clears all the text fields
			 */
			public void actionPerformed(ActionEvent e) {
				clearTextFields();
			}
		});
		clearButton.setBounds(145, 146, 117, 29);
		panel.add(clearButton);
		
		JButton exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener() {
			/**
			 * Exits the window, but not the program.
			 */
			public void actionPerformed(ActionEvent e) {
				frmItemCreation.dispose();
			}
		});
		exitButton.setBounds(6, 181, 256, 29);
		panel.add(exitButton);
	}
	/**
	 * @wbp.factory
	 */
	public static JFrame createJFrame() {
		JFrame frame = new JFrame();
		frame.setTitle("Item Creation");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		return frame;
	}
	/**
	 * Sets the values of all the text fields to null
	 */
	public void clearTextFields() {
		descriptionTextField.setText(null);
		categoryTextField.setText(null);
		wholesaleTextField.setText(null);
		retailTextField.setText(null);
		quantityTextField.setText(null);
	}
}
