import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import processing.core.PApplet;
import sprites.*;
import sprites.Character;

/**
 * The surface which draws the sprites onto the GUI.
 * 
 * @author Ethan Chang
 * @version 5/7/2021
 */
public class DrawingSurface extends PApplet {

	private Rectangle screenRect;
	
	private ArrayList<Integer> keys;
	private ArrayList<Wall> walls;
	private Character character;

	public DrawingSurface() {
		super();
		keys = new ArrayList<>();
		walls = new ArrayList<>();
		walls.add(new Wall(0, 300, 1000, 50));
		spawnNewCharacter();
	}
	
	public void spawnNewCharacter() {
		
		character = new Character(loadImage("character.png"), 50.0,50.0);
	}
	
//	public void spawnNewWall(double x, double y, double width, double height) {
//		walls.add(new Wall(x, y, width, height));
//	}
	public void setup() {
		
	}
	
	public void draw() {
		
		fill(100);
		for (Rectangle2D r : walls) {
			if (r instanceof Wall) {
				rect((float)r.getX(),(float)r.getY(), (float)r.getWidth(), (float)r.getHeight());
			}
		}
		
		character.draw(this);
		
		
		if (isPressed(KeyEvent.VK_A))
			character.walk(-1);
		if (isPressed(KeyEvent.VK_D))
			character.walk(1);
		if (isPressed(KeyEvent.VK_SPACE) || isPressed(KeyEvent.VK_W))
			character.jump();
		
		character.act();
	}
	
	public void keyPressed() {
		keys.add(keyCode);
	}

	public void keyReleased() {
		while(keys.contains(keyCode))
			keys.remove(new Integer(keyCode));
	}

	public boolean isPressed(Integer code) {
		return keys.contains(code);
	}
	
}
