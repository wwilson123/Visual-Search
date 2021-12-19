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

public class BinaryVisual extends JPanel implements ActionListener {
	// instance variables
	private JButton backButton;
	private JLabel targetFoundText;
	private Rectangle rect;
	private Timer rightTimer;
	private Timer leftTimer;
	private Timer beginTimer;
	private Timer endTimer;
	private Timer outOfRangeTimer;
	public int arraySize;
	public int target;
	private Arrow leftArrow;
	private Arrow rightArrow;
	private int middleIndex;
	private int startRect;
	private int endRect;
	private int averageRect;
	private int leftArrowIndex;
	private int rightArrowIndex;
	private int count = 0;
	
	// Initialize new frame 
	public JFrame vframe = new JFrame("Binary Search");
	
	// constructor
	public BinaryVisual(int arraySize, int target) {
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
		
		// initialize all the timers 
		this.rightTimer = new Timer(50, this);
		this.leftTimer = new Timer(50, this);
		this.beginTimer = new Timer(50, this);
		this.endTimer = new Timer(50, this);
		this.outOfRangeTimer = new Timer(50, this);
		
		//only run these timers one time
		endTimer.setRepeats(false);
		outOfRangeTimer.setRepeats(false);
		
		// start the first timer
		beginTimer.start();
		
		// calculate the initial middle index
		this.middleIndex = this.arraySize / 2;
		
		// create a new rectangle
		rect = new Rectangle(arraySize, target, 0, 375);
		
		this.target = rect.target;
		
		// create two new arrows whose x positions are the first and last rectangle in the array
		leftArrow = new Arrow(centerArrow(1), 335);
		rightArrow = new Arrow(centerArrow(arraySize), 335);
		
		// set the index's of the arrows based on what block they are over
		rightArrowIndex = arraySize;
		leftArrowIndex = 1;		
		
	}
	
	
	protected void paintComponent(Graphics page) {
		
		super.paintComponent(page);
		
		// draw the rectangles
		rect.draw(page);
		
		//draw the arrows
		leftArrow.draw(page);
		rightArrow.draw(page);

		
	}
	
	public void startTimer() {
		// start arrow movements without any delay
		// implement basic binary search logic
		if (leftArrow.getX() - 5 < rightArrow.getX()) {
			if (count <= 1) {
				if (target < middleIndex) {
					rightTimer.start();
				} else if (target > middleIndex){
					leftTimer.start();
					rightTimer.stop();
				} else {
					rightTimer.stop();
					leftTimer.stop();
					endTimer.start();
				}

				// same logic, but add pauses to indicate when calculations are being done
			} else {
				if (target < middleIndex) {
					rightTimer.setInitialDelay(1000);
					rightTimer.start();
				} else if (target > middleIndex){
					leftTimer.setInitialDelay(1000);
					leftTimer.start();
					rightTimer.stop();
				} else {
					rightTimer.stop();
					leftTimer.stop();
					endTimer.start();
				}
			}
		} else {
			outOfRangeTimer.start();
		}
	}
	
	
	// calculate were to position arrows on start
	public int centerArrow(int rectToFind) {
		startRect = ((rectToFind - 1) * (rect.gap + rect.width)) + rect.gap;
		endRect = startRect + rect.width;
		averageRect = (startRect + endRect) / 2;
		return averageRect - 8;	
	}
	


	public void actionPerformed(ActionEvent event) {
		// increment count to know if this is the first time an action has occurred
		count++;
	
		// if beginTimer was activated call startTimer and stop beginTimer
		if (event.getSource() == beginTimer) {
			startTimer();
			beginTimer.stop();
		}
		
		// was the rightTimr activated?
		if (event.getSource() == rightTimer) {
			// move the right arrow until it reaches the rectangle that is one to the left of the current middle index
			if (rightArrow.getX() + 9 >= (((middleIndex * (rect.width + rect.gap)) - rect.width))) {
				rightArrow.moveLeft(rect.width + rect.gap);			
				

			}
			// once the right arrow is one rectangle past the middle index, update the rightArrowIndex, the middleIndex, stop the rightTimer, and call startTImer to continue the search
			if (rightArrow.getX() + 9 <= (((middleIndex * (rect.width + rect.gap)) - rect.width))) {
				rightArrowIndex = middleIndex - 1;
				middleIndex = (rightArrowIndex + leftArrowIndex) / 2;
				rightTimer.stop();
				startTimer();
			}
			
		}
		
		if (event.getSource() == leftTimer) {
			
			// move the left arrow until it reaches the rectangle that is one to the right of the current middle index
			if (leftArrow.getX() + 9 <= (((middleIndex * (rect.width + rect.gap)) - rect.width)) + rect.width + rect.gap) {
				leftArrow.moveRight(rect.width + rect.gap);
			}
			// once the left arrow is one rectangle to the right of the middle index, update the leftArrowIndex, the middleIndex, stop the leftTimer, and call startTimer to continue the search
			if(leftArrow.getX() + 9 >= (((middleIndex * (rect.width + rect.gap)) - rect.width)) + rect.width + rect.gap) {
				leftArrowIndex = middleIndex + 1;
				middleIndex = (rightArrowIndex + leftArrowIndex) / 2;
				leftTimer.stop();
				startTimer();
			}
			
		}
		
		// if startTimer results in the activation of endTimer then we know we found the target
		if (event.getSource() == endTimer) {
			// update the targetFoundText label with text indicating we found the target
			targetFoundText.setText("Target found at middle index!");
			// make the back button visible
			backButton.setVisible(true);
			
			
		}
		// if startTimer results in the activation of outOfRangeTimer then we know the target is not in the array
		if (event.getSource() == outOfRangeTimer) {
			// update the targetFoundText label with text indicating we did not find the target
			targetFoundText.setText("Target is not in the Array!");
			// make the back button visible
			backButton.setVisible(true);
			
			
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



