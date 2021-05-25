package timeescape.sprites;

import java.awt.geom.Rectangle2D;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * 
 * @author Taya Williams
 * @version 5/24/2021
 */
public class Sprite extends Rectangle2D.Double{
	public PImage image;
	
	public Sprite(PImage img, double x, double y, double w, double h) {
		super(x, y, w, h);
		image = img;
	}
	/**
	 * returns x coordinate
	 * @return x coordinate
	 */
	public double getX() {
		return super.x;
	}
	/**
	 * returns y coordinate
	 * @return y coordinate
	 */
	public double getY() {
		return super.y;
	}
	/**
	 * sets x coordinate
	 * @param x - the x coordinate that the Sprite will be set to.
	 */
	public void setX(double x) {
		super.x = x;
	}
	/**
	 * sets y coordinate
	 * @param y - the y coordinate that the Sprite will be set to.
	 */
	public void setY(double y) {
		super.y = y;
	}
	/**
	 * draws the sprite
	 * @param g is the PApplet
	 * @pre g cannot be null.
	 * @pre The image may be changed be subsequent settings on the given PApplet.
	 */
	public void draw(PApplet g) {
		g.image(image,(int)x,(int)y,(int)width,(int)height);
	}
}
