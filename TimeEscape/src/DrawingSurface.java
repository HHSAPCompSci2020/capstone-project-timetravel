import java.awt.Rectangle;
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

	}
	
	
	public void setup() {
		
	}
	
	public void draw() {
		
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
