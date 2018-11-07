package TestClass;

import java.awt.*;

import javax.swing.JFrame;
import java.awt.TextArea;
import java.awt.Panel;
import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class javaproject {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					javaproject window = new javaproject();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public javaproject() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 799, 511);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		TextArea CurrentInventoryForm = new TextArea();
		CurrentInventoryForm.setBounds(10, 58, 391, 404);
		frame.getContentPane().add(CurrentInventoryForm);
		
		Panel Search = new Panel();
		Search.setBackground(Color.WHITE);
		Search.setBounds(236, 23, 165, 27);
		frame.getContentPane().add(Search);
		
		JTextPane DetailsForm = new JTextPane();
		DetailsForm.setBounds(425, 58, 348, 370);
		frame.getContentPane().add(DetailsForm);
		
		JButton Sumbit = new JButton("Sumbit");
		Sumbit.setBounds(547, 439, 89, 23);
		frame.getContentPane().add(Sumbit);
		
		JButton Add = new JButton("Add");
		Add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		Add.setBounds(452, 27, 89, 23);
		frame.getContentPane().add(Add);
		
		JButton Delete = new JButton("Delete");
		Delete.setBounds(568, 27, 89, 23);
		frame.getContentPane().add(Delete);
		
		JButton Cancel = new JButton("Cancel");
		Cancel.setBounds(684, 27, 89, 23);
		frame.getContentPane().add(Cancel);
	}
}
