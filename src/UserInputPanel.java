/* Will Wilson
 * Final project - a program to visualize linear and binary search
 * Oracle's Java Documentation was consulted throughout the creation of this project 
 * and I exercised the Gilligan's Island pledge when using this assistance
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UserInputPanel extends JPanel implements ActionListener {
	// instance variables
	private JComboBox linearOrBinary;
	private JButton runButton;
	private JTextField sizeField;
	private JTextField targetField;
	private JLabel sizeLabel;
	private JLabel targetLabel;
	public double arraySize;
	public double target;
	public  JFrame frame = new JFrame("Visual Search");
	
	// constructor
	public UserInputPanel() {
		// set screen size to 1000 by 750
		super.setPreferredSize(new Dimension(1000, 750));
		
		// set choices to linear or binary search
		String[] choices = {"Linear Search", "Binary Search"};
		
		// create a new combobox with the choices
		this.linearOrBinary = new JComboBox(choices);
		
		// add action listener to combobox
		this.linearOrBinary.addActionListener(this);
		
		// create a run button
		this.runButton = new JButton("Run");
		
		// add action listener to run button
		this.runButton.addActionListener(this);
		
		// create input text fields
		this.sizeField = new JTextField();
		this.targetField = new JTextField();

		// create labels that will go next to text fields
		this.sizeLabel = new JLabel("   Size of the sorted array (<= 150)");
		this.targetLabel = new JLabel("   Target integer");

		// set layout manager
		super.setLayout(new BorderLayout());
		
		// add combobox to top of super panel
		super.add(this.linearOrBinary, BorderLayout.PAGE_START);
		
		// create a new panel for the leftside
		JPanel leftPanel = new JPanel();
		
		// set layout for the panel
		leftPanel.setLayout(new GridLayout(2, 1));
		
		// add labels to left panel
		leftPanel.add(this.sizeLabel);
		leftPanel.add(this.targetLabel);
		
		// add left panel to super panel
		super.add(leftPanel, BorderLayout.WEST);
		
		// create a right panel
		JPanel rightPanel = new JPanel();
		
		// set layout of right panel
		rightPanel.setLayout(new GridLayout(2, 1));
		
		// add textfields to right panel
		rightPanel.add(this.sizeField);
		rightPanel.add(this.targetField);
		
		// add right panel to super panel
		super.add(rightPanel, BorderLayout.CENTER);

		
		// add the run button to the end of the panel
		super.add(this.runButton, BorderLayout.PAGE_END);	
	}
	
	// convet arraySize from double to int
	public int getArraySize() {
		return (int)arraySize;
	}
	
	// convert target from double to int
	public int getTarget() {
		return (int)target;
	}
	
	public void actionPerformed(ActionEvent event) {
		
		
		
		// was the run button activated?
		if (event.getSource() == this.runButton) {
			// hide frame as we will transition to search frames
			frame.setVisible(false);
			
			// parse inputs from text fields
			arraySize = Double.parseDouble(this.sizeField.getText());
			target = Double.parseDouble(this.targetField.getText());
			
			// depending on which search the user selected create those frames
			if (this.linearOrBinary.getSelectedIndex() == 0) {
				LinearVisual lFrame = new LinearVisual(getArraySize(), getTarget());
				lFrame.createFrame();
				
				
			} else {
				BinaryVisual bFrame = new BinaryVisual(getArraySize(), getTarget());
				bFrame.createFrame();
			}

		}

		
	}
	
	// method to create this panels frame
	public void createFrame() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(this);
		frame.pack();
		frame.setVisible(true);
		
	}
	
}





