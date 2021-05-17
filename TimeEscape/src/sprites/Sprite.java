package sprites;

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
	
	public double getX() {
		return super.x;
	}
	
	public double getY() {
		return super.y;
	}
	
	public void setX(double x) {
		super.x = x;
	}
	public void setY(double y) {
		super.y = y;
	}
	
	public void draw(PApplet g) {
		g.image(image,(int)x,(int)y,(int)width,(int)height);
	}
}
