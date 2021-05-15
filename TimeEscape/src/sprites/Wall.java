package sprites;

import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * 
 * @author Taya Williams
 *
 */

public class Wall extends Sprite {
	
//	public static PImage img = new PImage(); 
	
	public Wall(double x, double y, double width, double height) {
		super(null, x, y, width, height);
		
	}
	
	@Override
	public void draw(PApplet marker) {
		marker.pushStyle();
//		marker.fill(r,g,b);
		marker.rect((float)super.getX(), (float)super.getY(), (float)super.getWidth(), (float)super.getHeight());
		marker.popStyle();
	}
}
