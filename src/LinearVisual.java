/* Will Wilson
 * Final project - a program to visualize linear and binary search
 * Oracle's Java Documentation was consulted throughout the creation of this project 
 * and I exercised the Gilligan's Island pledge when using this assistance
 */


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class LinearVisual extends JPanel implements ActionListener {
	// instance variables
	private JButton backButton;
	private JLabel targetFoundText;
	private Rectangle rect;
	private Timer timer;
	private Timer beginTimer;
	private Timer outOfRangeTimer;
	public int arraySize;
	public int target;
	private Arrow arrow;
	
	// Initialize new frame
	public JFrame vframe = new JFrame("Linear Search");
	
	// constructor
	public LinearVisual(int arraySize, int target) {
		// set size of panel to 1000, 750
		super.setPreferredSize(new Dimension(1000, 750));
		
		// create a back button, hide it, add an action listener, and add it to panel
		this.backButton = new JButton("Run again!");
		this.backButton.setVisible(false);
		this.backButton.addActionListener(this);
		super.add(backButton);
		
		// create a target found label
		this.targetFoundText = new JLabel(" ");
		super.add(targetFoundText);
		targetFoundText.setFont(targetFoundText.getFont().deriveFont(50f));
		
		this.arraySize = arraySize;
		
		// Initialize all the timers
		this.timer = new Timer(50, this);
		this.outOfRangeTimer = new Timer(50, this);
		this.beginTimer = new Timer(50, this);
		this.beginTimer.start();
		 
		
		// create a new rectangle object
		rect = new Rectangle(arraySize, target, 0, 375);
		
		// set target to the same target as the rectangle object
		this.target = rect.target;
		
		// create a new arrow object calculating x position to start on first block
		arrow = new Arrow(((((rect.width + rect.gap) + rect.gap) / 2) - 8), 335);
		
	}
	
	protected void paintComponent(Graphics page) {
		super.paintComponent(page);
		
		// draw the rectangles
		rect.draw(page);
		
		//draw the arrow
		arrow.draw(page);

		
	}
	
	// method to check if target is not in range
	public void startTimer() {
		if (target < 1 || target > arraySize) {
			outOfRangeTimer.start();
		} else {
			timer.start();
		}
	}
	

	public void actionPerformed(ActionEvent event) {
		
		// run startTimer first
		if (event.getSource() == beginTimer) {
			startTimer();
		}
		
		// was timer activated?
		if (event.getSource() == timer) {
			
			// move arrow until it reaches the target
			if (arrow.getX() + 9 <= (((target * (rect.width + rect.gap)) - rect.width))) {
				arrow.moveRight(rect.width + rect.gap);
			} else {
				// stop the timer and display target found and the back button
				timer.stop();
				this.backButton.setVisible(true);
				targetFoundText.setText("Target found!");
	
			
			}	
			
		}
		
		// if startTimer causes outOfRangeTimer to be activated then display text indicating target is not in the array
		if (event.getSource() == outOfRangeTimer) {
			this.backButton.setVisible(true);
			targetFoundText.setText("Target is not in the array!");
		}
		
		
		// redraw the panel
					super.repaint();
		
		// was the back button activated?
		if (event.getSource() == this.backButton) {
			vframe.setVisible(false);
			UserInputPanel uPanel = new UserInputPanel();
			uPanel.createFrame();
			
		}

		
	}
	// create this panels frame
	public void createFrame() {
		vframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		vframe.getContentPane().add(this);
		vframe.pack();
		vframe.setVisible(true);
		
	}
	
}



