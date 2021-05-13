package sprites;
/**
 * 
 * @author Taya Williams
 *
 */

public class Character extends Sprite{
	
	public double height = 50.0;
	public double width = 25.0;
	public double vx, vy, gravity, friction;
	public double jump;
	
	public Character(PImage img, double x, double y) {
		super(img, x, y, width, height);
		vx = 0;
		vy = 0;
		gravity = 0.2; 
		friction = 0.95;
		jump = 15;
	}
	
	public void walk(int dir) {
		vx += dir;
	}
	
	public void jump() {
		vy -= jump;
	}
	
	public void stop() {
		vx = 0;
		vy = 0;
	}
	
	public void act() {
		
		double x = s.getX();
		x += vx;
		this.setX(x);
		
		double y = s.getY();
		y += vy;
		this.setY(y);
		
		vy *= friction;
		vx *= friction;
		
		vy += gravity;
		
	}
	
	
}
