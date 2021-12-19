/* Will Wilson
 * Final project - a program to visualize linear and binary search
 * Oracle's Java Documentation was consulted throughout the creation of this project 
 * and I exercised the Gilligan's Island pledge when using this assistance
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Rectangle {
	// instance variables
	private Point position;
	private int arraySize;
	public int gap;
	public int width;
	public int height;
	public int target;
	public Color rectangleRed = new Color(196, 61, 61);
	public Color targetYellow = new Color(230, 230, 62);

	
	// constructor
	public Rectangle(int arraySize, int target, int x, int y) {
		this.position = new Point(x, y);
		this.arraySize = arraySize;
		// set a gap between each rectangle
		this.gap = 3;
		// calculate the width based on the size of the screen, size of array, and size of gap
		this.width = (1000 / arraySize) - gap;
		this.height = 20;
		this.target = target;
	}
	
	
	public void draw(Graphics page) {
		// for the amount of integers in the array, draw rectangles next to each other
		for (int i = 0; i < arraySize; i++) {
			if ((i + 1) == target) {
				page.setColor(targetYellow);
			} else {
				page.setColor(rectangleRed);
			}
			page.drawRect(this.position.x + (int)(i * (gap + width)) + gap, this.position.y, width, height);
			page.fillRect(this.position.x + (int)(i * (gap + width)) + gap, this.position.y, width, height);
		}
		
	}
	
	
	public int getX() {
		return this.position.x;
	}
	
	public int getY() {
		return this.position.y;
	}
	

}
