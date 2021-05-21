package sprites;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Point2D;
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
	
	public void setPosition(Point2D.Double p) {
		x = p.getX();
		y = p.getY();
	}

	public void walk(int dir) {
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

	public void act(ArrayList<Shape> walls) {

//		Y-axis calculations:

		double y = getY();
		y += vy;
		

		vy *= friction;
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
//					System.out.println(s + "---stopped");
				}
			}
		}
		
//		if (yVelocity > 0) {
//			Shape standingSurface = null;
//			for (Shape s : obstacles) {
//				if (s.intersects(strechY)) {
//					onASurface = true;
//					standingSurface = s;
//					yVelocity = 0;
//				}
//			}
//			if (standingSurface != null) {
//				Rectangle r = standingSurface.getBounds();
//				yCoord2 = r.getY()-height;
//			}
//		} else if (yVelocity < 0) {
//			Shape headSurface = null;
//			for (Shape s : obstacles) {
//				if (s.intersects(strechY)) {
//					headSurface = s;
//					yVelocity = 0;
//				}
//			}
//			if (headSurface != null) {
//				Rectangle r = headSurface.getBounds();
//				yCoord2 = r.getY()+r.getHeight();
//			}
//		}
//
//		if (Math.abs(yVelocity) < .5)
//			yVelocity = 0;

		this.setY(y);
		
//		X-axis calculations:
		
		double x = getX();
		x += vx;
		
		
//		double xCoord2 = xCoord + xVelocity;
//
//		Rectangle2D.Double strechX = new Rectangle2D.Double(Math.min(xCoord,xCoord2),yCoord2,width+Math.abs(xVelocity),height);
//
//		if (xVelocity > 0) {
//			Shape rightSurface = null;
//			for (Shape s : obstacles) {
//				if (s.intersects(strechX)) {
//					rightSurface = s;
//					xVelocity = 0;
//				}
//			}
//			if (rightSurface != null) {
//				Rectangle r = rightSurface.getBounds();
//				xCoord2 = r.getX()-width;
//			}
//		} else if (xVelocity < 0) {
//			Shape leftSurface = null;
//			for (Shape s : obstacles) {
//				if (s.intersects(strechX)) {
//					leftSurface = s;
//					xVelocity = 0;
//				}
//			}
//			if (leftSurface != null) {
//				Rectangle r = leftSurface.getBounds();
//				xCoord2 = r.getX()+r.getWidth();
//			}
//		}
//
//
//		if (Math.abs(xVelocity) < .5)
//			xVelocity = 0;
//
		this.setX(x);
	}
	
	public Character getCharacterCopy() {
		return new Character(image, x, y, vx, vy, onASurface);
	}
}