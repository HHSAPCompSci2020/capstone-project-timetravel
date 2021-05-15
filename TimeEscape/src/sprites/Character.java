package sprites;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;

import processing.core.PImage;

/**
 * 
 * @author Taya Williams
 *
 */

public class Character extends Sprite{

	public static final int height = 50;
	public static final int width = 25;
	
	public double vx, vy, gravity, friction;
	public double jump;

	public Character(PImage img, double x, double y) {
		super(img, x, y, width, height);
		vx = 0;
		vy = 0;
		gravity = 0.2; 
		friction = 0.95;
		jump = 15;
	}

	public void walk(int dir) {
		vx += dir;
	}

	public void jump() {
		vy -= jump;
	}

	public void standing() {
		vy = 0;
	}

	public void blocked() {
		vx = 0;
	}

	public void act() {

		double x = getX();
		x += vx;
		this.setX(x);

		double y = getY();
		y += vy;
		this.setY(y);

		vy *= friction;
		vx *= friction;

		vy += gravity;

		Rectangle2D.Double strechY = new Rectangle2D.Double(getX(),Math.min(getY(),getY() + vy),width,height+Math.abs(vy));

		if (vy > 0) {
			Shape standingSurface = null;
//			for (Shape s : walls) {
//				if (s.intersects(strechY)) {
//					standingSurface = s;
//					standing();
//				}
//
//			}


		}

	
	}
}