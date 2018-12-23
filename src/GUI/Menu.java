/*
 * Menu.java
 * This is the interface for the menu
 * @Author: Andrew Wu
 * Date: Dec. 22, 2018
 * Version 1.0
 */

package GUI;

// Import awt components
import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

// Import swing components
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFileChooser;
import javax.swing.JButton;
import javax.swing.JTextArea;


// Import io components
import java.io.File;

public class Menu {

	// GUI declaration
	private JFrame frame;
	private JLabel titleLabel;
	private GroupLayout groupLayout;
	private JButton selectFileButton;
	private JTextArea instructionTextArea;
	private JButton encodeButton;
	private JButton decodeButton;
	private JTextArea messageTextArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu window = new Menu();
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
	public Menu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// Initialize frame
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Initialize labels
	    titleLabel = new JLabel("Huffman Coder");
		titleLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 40));
	    
	    // Initialize text area
	    instructionTextArea = new JTextArea();
	    instructionTextArea.setEditable(false);
	    instructionTextArea.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 24));
	    instructionTextArea.setLineWrap(true);
	    instructionTextArea.setText("Hello! Welcome to Huffman Coder!\n"
	    		+ "This program can encode or decode any file using Huffman\n"
	    		+ "compression. To begin, please select a file.");
	    
	    messageTextArea = new JTextArea();
	    messageTextArea.setLineWrap(true);
	    messageTextArea.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 24));
	    messageTextArea.setEditable(false);
		
		// Initialize buttons
	    encodeButton = new JButton("ENCODE");
	    encodeButton.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 24));
	    encodeButton.setVisible(false);
	    encodeButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		
	    	}
	    });
	    
	    decodeButton = new JButton("DECODE");
	    decodeButton.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 24));
	    decodeButton.setVisible(false);
	    decodeButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		
	    	}
	    });
	    
	    selectFileButton = new JButton("Select a file...");
	    selectFileButton.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 18));
	    selectFileButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		JFileChooser fc = new JFileChooser();
	    		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
	    		int returnVal = fc.showOpenDialog(frame);
	    		
	    		if(returnVal == JFileChooser.APPROVE_OPTION) {
	    			File file = fc.getSelectedFile();
	    			messageTextArea.setText("File chosen: " + file.getName());
	    			encodeButton.setVisible(true);
	    			decodeButton.setVisible(true);
	    		} else {
	    			messageTextArea.setText("");
	    		}
	    	}
	    });
		
		// Initialize and set group layout 
	    groupLayout = new GroupLayout(frame.getContentPane());
	    groupLayout.setHorizontalGroup(
	    	groupLayout.createParallelGroup(Alignment.LEADING)
	    		.addGroup(groupLayout.createSequentialGroup()
	    			.addGap(234)
	    			.addComponent(titleLabel, GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
	    			.addGap(227))
	    		.addGroup(groupLayout.createSequentialGroup()
	    			.addGap(46)
	    			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
	    				.addComponent(instructionTextArea, GroupLayout.PREFERRED_SIZE, 683, GroupLayout.PREFERRED_SIZE)
	    				.addGroup(groupLayout.createSequentialGroup()
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
	    						.addComponent(messageTextArea, GroupLayout.PREFERRED_SIZE, 683, GroupLayout.PREFERRED_SIZE)
	    						.addGroup(groupLayout.createSequentialGroup()
	    							.addComponent(selectFileButton)
	    							.addGap(18)
	    							.addComponent(encodeButton)
	    							.addGap(18)
	    							.addComponent(decodeButton)))))
	    			.addContainerGap(53, Short.MAX_VALUE))
	    );
	    groupLayout.setVerticalGroup(
	    	groupLayout.createParallelGroup(Alignment.LEADING)
	    		.addGroup(groupLayout.createSequentialGroup()
	    			.addContainerGap()
	    			.addComponent(titleLabel, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
	    			.addPreferredGap(ComponentPlacement.RELATED)
	    			.addComponent(instructionTextArea, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    			.addPreferredGap(ComponentPlacement.UNRELATED)
	    			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
	    				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
	    					.addComponent(encodeButton, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
	    					.addComponent(decodeButton, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
	    				.addComponent(selectFileButton, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
	    			.addGap(44)
	    			.addComponent(messageTextArea, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
	    			.addGap(164))
	    );
		frame.getContentPane().setLayout(groupLayout);
		
	}
}
