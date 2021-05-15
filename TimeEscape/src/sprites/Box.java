package sprites;

import processing.core.PImage;

/**
 * 
 * @author Taya Williams
 *
 */

public class Box extends Sprite {
	
	public static final int boxSize = 25;
	
	public Box(PImage img, double x, double y) {
		super(img, x, y, boxSize, boxSize);
	}
	
	public void boxMoved(double x, double y) {
		
		this.setX(getX() + x);
		
		this.setY(getY() + y);
	}
}
