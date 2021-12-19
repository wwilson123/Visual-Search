/* Will Wilson
 * Final project - a program to visualize linear and binary search
 * Oracle's Java Documentation was consulted throughout the creation of this project 
 * and I exercised the Gilligan's Island pledge when using this assistance
 */

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

public class Arrow {
	public int xPosition;
	public int yPosition;
	
	Image arrow;
	public Arrow(int x, int y) {
		// get arrow image from resources
		try {
			this.arrow = ImageIO.read(new File("resources/arrow.png"));
		} catch (Exception exception) {
			System.err.println("Error loading the arrow image file");
			System.exit(1);
		}
		
		this.xPosition = x;
		this.yPosition = y;
	}
	
	// method to draw an arrow
	public void draw(Graphics page) {
		page.drawImage(arrow, xPosition, yPosition, 18, 36, null, null);
	}
	
	// move the arrow to the right
	public void moveRight(int x) {
		this.xPosition += x;
	}
	
	//move the arrow to the left
	public void moveLeft(int x) {
		this.xPosition -= x;
	}
	
	//get the x position of the arrow
	public int getX() {
		return xPosition;
	}
}
