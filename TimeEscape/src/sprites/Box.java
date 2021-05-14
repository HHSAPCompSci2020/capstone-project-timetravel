package sprites;
/**
 * 
 * @author Taya Williams
 *
 */

public class Box extends Sprite{
	
	double boxSize = 25.0;
	
	public box(PImage img, double x, double y) {
		super(img, x, y, boxSize, boxSize);
	}
	
	public void boxMoved(double x, double y) {
		
		this.setX(s.getX() + x);
		
		this.setY(s.getY() + y);
	}
}
