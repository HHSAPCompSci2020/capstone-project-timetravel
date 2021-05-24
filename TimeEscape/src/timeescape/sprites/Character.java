package timeescape.sprites;

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
	private static final double bounce = 1.2;
	
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
		y = getY() - 5;
		vy = 0;
	}
	
	public void verticallyBlocked() {
		y = getY() + 5;
		vy = 0;
	}
	
	public void leftBlocked() {
		x = getX() + 5;
		vx = 0;
	}
	
	public void rightBlocked() {
		x = getX() - 5;
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

		double y = getY();
		y += vy;
		this.setY(y);
		
		double x = getX();
		x += vx;
		this.setX(x);
		

		vy *= friction;
		vx *= friction;

		vy += gravity;

		Rectangle2D.Double strechY = new Rectangle2D.Double(getX(),Math.min(getY(),getY() + vy),width,height+Math.abs(vy));
		//Rectangle2D.Double strechY = new Rectangle2D.Double(xCoord,Math.min(yCoord,yCoord2),width,height+Math.abs(yVelocity));
		
		Rectangle2D.Double strechX = new Rectangle2D.Double(Math.min(getX(),getX() + vx), getY(),width+Math.abs(vx),height);
		onASurface = false;
		
		//FLOOR
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
		
		//RIGHT WALL
		if(vx > 0) {
			Shape rightSurface = null;
			for (Shape s: walls) {
				if(s.intersects(strechX)) {
					rightSurface = s;
					rightBlocked();
				}
			}
		}
		
		//LEFT WALL
		if(vx < 0) {
			Shape leftSurface = null;
			for(Shape s: walls) {
				if(s.intersects(strechX)) {
					leftSurface = s;
					leftBlocked();
				}
			}
		}
		
		//ROOF
		if(vy < 0) {
			Shape roofSurface = null;
			for(Shape s: walls) {
				if(s.intersects(strechY)) {
					roofSurface = s;
					verticallyBlocked();
				}
			}
		}
	}
	
	public Character getCharacterCopy() {
		return new Character(image, x, y, vx, vy, onASurface);
	}
}