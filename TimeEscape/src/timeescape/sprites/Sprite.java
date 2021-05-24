package timeescape.sprites;

import java.awt.geom.Rectangle2D;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * 
 * @author Taya Williams
 *
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
	 * @return x coordinate
	 */
	public void setX(double x) {
		super.x = x;
	}
	/**
	 * sets y coordinate
	 * @return y coordinate
	 */
	public void setY(double y) {
		super.y = y;
	}
	/**
	 * draws the sprite
	 * @param g is the PApplet
	 * @pre 
	 */
	public void draw(PApplet g) {
		g.image(image,(int)x,(int)y,(int)width,(int)height);
	}
}
