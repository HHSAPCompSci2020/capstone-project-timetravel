package sprites;
/**
 * 
 * @author Taya Williams
 *
 */
public class Sprite extends Rectangle2D.Double{
	private PImage image;
	
	public Sprite(PImage img, double x, double y, double w, double h) {
		super(x, y, w, h);
		image = img;
	}
	
	public void getX() {
		return super.x;
	}
	
	public void getY() {
		return super.y
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
