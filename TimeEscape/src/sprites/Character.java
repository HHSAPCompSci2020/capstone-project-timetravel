package sprites;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import processing.core.PImage;

/**
 * 
 * @author Taya Williams
 *
 */

public class Character extends Sprite{

	//these values are universal for all characters
	
	public static final int height = 50;
	public static final int width = 25;
	private static final double gravity = 0.75; //0.2
	private static final double friction = 0.95;
	private static final double jump = 15;
	
	protected double vx;
	protected double vy;
	private boolean onASurface;
	
	
	
	public Character(PImage img, double x, double y) {
		super(img, x, y, width, height);
		vx = 0;
		vy = 0;
		onASurface = false;
	}
	
	public Character(PImage img, double x, double y, double vx, double vy, boolean onASurface) {
		super(img, x, y, width, height);
		this.vx = vx;
		this.vy = vy;
		this.onASurface = onASurface;
	}
	

	public void walk(int dir) {
		if (onASurface)
			vx += dir/2.0; //1 is too fast, so divided by 2 and is perfect
	}

	public void jump() {
		if (onASurface)
			vy -= jump;
	}

	public void standing() {
		vy = 0;
	}

	public void blocked() {
		vx = 0;
	}
	
	public double getvx() {
		return vx;
	}
	
	public double getvy() {
		return vy;
	}
	
	public boolean getonASurface() {
		return onASurface;
	}

	public void act(ArrayList<Rectangle2D> walls) {

		double x = getX();
		x += vx;
		this.setX(x);

		double y = getY();
		y += vy;
		this.setY(y);

		vy *= friction;
		if (onASurface)
			vx *= friction;

		vy += gravity;

		Rectangle2D.Double strechY = new Rectangle2D.Double(getX(),Math.min(getY(),getY() + vy),width,height+Math.abs(vy));

		onASurface = false;
		
		if (vy > 0) {
			Shape standingSurface = null;
			for (Shape s : walls) {
				if (s.intersects(strechY)) {
					onASurface = true;
					standingSurface = s;
					standing();
				}

			}


		}

	
	}
	
	public Character getCharacterCopy() {
		return new Character(image, x, y, vx, vy, onASurface);
	}
}