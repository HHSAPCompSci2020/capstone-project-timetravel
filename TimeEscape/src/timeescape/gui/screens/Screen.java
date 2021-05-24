package timeescape.gui.screens;

/**
 * 
 * @author Ethan Chang
 *
 * Represents a screen to be drawn on a GUI. Based on Mr. Shelby's Screen Switching demo.
 * 
 */
public abstract class Screen {

	public final int DRAWING_WIDTH, DRAWING_HEIGHT;
	
	public Screen(int width, int height) {
		this.DRAWING_WIDTH = width;
		this.DRAWING_HEIGHT = height;
	}
	
	/**
	 * Represents the Processing setup method.
	 * This method should be called in the PApplet GUI respective to what it represents.
	 */
	public void setup() {
		
	}
	
	/**
	 * Represents the Processing draw method.
	 * This method should be called in the PApplet GUI respective to what it represents.
	 * @post May affect subsequent drawn items.
	 */
	public void draw() {
		
	}
	
	/**
	 * Represents the Processing mousePressed method.
	 * This method should be called in the PApplet GUI respective to what it represents.
	 */
	public void mousePressed() {
		
	}
	
	/**
	 * Represents the Processing mouseMoved method.
	 * This method should be called in the PApplet GUI respective to what it represents.
	 */
	public void mouseMoved() {
		
	}
	
	/**
	 * Represents the Processing mouseDragged method.
	 */
	public void mouseDragged() {
		
	}
	
	/**
	 * Represents the Processing mouseReleased method.
	 * This method should be called in the PApplet GUI respective to what it represents.
	 */
	public void mouseReleased() {
		
	}
	
	
	
}
