import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import processing.core.PApplet;

/**
 * The surface which draws the sprites onto the GUI.
 * 
 * @author Ethan Chang
 * @version 5/7/2021
 */
public class DrawingSurface extends PApplet {

	private Rectangle screenRect;
	
	private ArrayList<Integer> keys;

	public DrawingSurface() {
		super();
		keys = new ArrayList<Integer>();
		spawnNewCharacter();
	}
	
	public void spawnNewCharacter() {
		
		character = new Character(loadImage("character.png"), 50,50);
	}
	
	public void setup() {
		
	}
	
	public void draw() {
		character.draw(this);
		
		
		
		if (isPressed(KeyEvent.VK_A))
			character.walk(-1);
		if (isPressed(KeyEvent.VK_D))
			character.walk(1);
		if (isPressed(KeyEvent.VK_SPACE))
			character.jump();
		if (isPressed(KeyEvent.VK_W))

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
