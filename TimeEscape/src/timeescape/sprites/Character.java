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
 * @version 5/24/2021
 */

public class Character extends Sprite{

	//these values are universal for all characters
	
	public static final int height = 50;
	public static final int width = 25;
	private static final double gravity = 0.75; //0.2
	private static final double friction = 0.95;
	private static final double jump = 15;
//	private static final double bounce = 1.2;
	
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
	
	/**
	 * sets the position of the character from a point
	 * @param p - Point where the Character will be set to.
	 */
	public void setPosition(Point2D.Double p) {
		x = p.getX();
		y = p.getY();
	}
	
	/**
	 * sets the position of the character from a point
	 * @param x - the x position where the Character will be set to.
	 * @param y - the y position where the Character will be set to.
	 */
	public void setPosition(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * moves the character in a certain horizontal direction
	 * @param dir the direction of the desired character movement
	 */
	
	public void walk(int dir) {
		vx += dir/2.0; //1 is too fast, so divided by 2 and is perfect
	}
	
	/**
	 * moves the character upwards in the vertical direction
	 */
	
	public void jump() {
		if (onASurface)
			vy -= jump;
	}

	private void standing(Shape s) {
		y = s.getBounds().getY() - height;
		vy = 0;
	}
	
	private void verticallyBlocked(Shape s) {
		y = s.getBounds().getHeight();
		vy = 0;
	}
	
	private void leftBlocked(Shape s) {
		x = s.getBounds().getX() + s.getBounds().getWidth();
		vx = 0;
	}
	
	private void rightBlocked(Shape s) {
		x = s.getBounds().getX() - width;
		vx = 0;
	}
	/**
	 * gets the x-velocity of the character
	 * @return the x-velocity of the character
	 */
	public double getvx() {
		return vx;
	}
	/**
	 * gets the y-velocity of the character
	 * @return the x-velocity of the character
	 */
	public double getvy() {
		return vy;
	}
	/**
	 * states whether the character is standing on a surface or not
	 * @return if the character is on a surface
	 */
	public boolean getonASurface() {
		return onASurface;
	}

	/**
	 * allows the character to speed up and slow down based on its velocity, gravity, and friction. also contains code for character collision with walls.
	 * @param walls - the obstacles the Character can be blocked by.
	 */
	public void act(ArrayList<Shape> walls) {

		double y = getY();
		double x = getX();
		

		Rectangle2D.Double strechY = new Rectangle2D.Double(getX(),Math.min(getY(),getY() + vy),width,height+Math.abs(vy));
		//Rectangle2D.Double strechY = new Rectangle2D.Double(xCoord,Math.min(yCoord,yCoord2),width,height+Math.abs(yVelocity));
		
		Rectangle2D.Double strechX = new Rectangle2D.Double(Math.min(getX(),getX() + vx), getY(),width+Math.abs(vx),height);
		onASurface = false;
		
		//FLOOR
		if (vy > 0) {
			for (Shape s : walls) {
				if (s.intersects(strechY)) {
					onASurface = true;
					standing(s);
//					System.out.println(s + "---stopped");
				}
			}
		}
		
		//RIGHT WALL
		if(vx > 0) {
			for (Shape s: walls) {
				if(s.intersects(strechX)) {
					rightBlocked(s);
				}
			}
		}
		
		//LEFT WALL
		if(vx < 0) {
			for(Shape s: walls) {
				if(s.intersects(strechX)) {
					leftBlocked(s);
				}
			}
		}
		
		//ROOF
		if(vy < 0) {
			for(Shape s: walls) {
				if(s.intersects(strechY)) {
					verticallyBlocked(s);
				}
			}
		}
		
		y += vy;
		x += vx;
		
		
		vy += gravity;
		vy *= friction;
		vx *= friction;

		setPosition(x, y);
		
	}
	/**
	 * gets a new character that is an exact copy of a already created character
	 * @return a copy of an already created character.
	 */
	public Character getCharacterCopy() {
		return new Character(image, x, y, vx, vy, onASurface);
	}
}