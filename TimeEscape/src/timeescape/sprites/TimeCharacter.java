package timeescape.sprites;

import processing.core.PImage;

/**
 * 
 * @author Taya Williams
 *
 */

public class TimeCharacter extends Character {
	
	
	public TimeCharacter(PImage image, double x, double y) {
		super(image, x, y);
	}
	
	public TimeCharacter(Character c) {
		super(c.image, c.getX(), c.getY());
		vx = c.getvx();
		vy = c.getvy();
	}
}
