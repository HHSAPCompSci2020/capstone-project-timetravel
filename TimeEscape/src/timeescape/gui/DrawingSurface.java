package timeescape.gui;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import processing.core.PApplet;
import timeescape.gui.screens.ExitScreen;
import timeescape.gui.screens.GameScreen;
import timeescape.gui.screens.InstructionScreen;
import timeescape.gui.screens.MainMenu;
import timeescape.gui.screens.PauseMenu;
import timeescape.gui.screens.Screen;
import timeescape.gui.screens.ScreenSwitcher;
import timeescape.gui.screens.StartScreen;


/**
 * This class represents the GUI and draws the screens and detects peripheral input.
 * 
 * @author Ethan Chang
 * @version 5/7/2021
 */
public class DrawingSurface extends PApplet implements ScreenSwitcher {

	public static final int ASSUMED_WIDTH = 800;
	public static final int ASSUMED_HEIGHT = 600;
	
	public float ratioX, ratioY;
	
//	private Rectangle screenRect;

	private Screen activeScreen;
	private Screen prevScreen;
	private ArrayList<Screen> screens;
	
	
	private ArrayList<Integer> keys;

	
	public DrawingSurface() {
		screens = new ArrayList<Screen>();
		
		keys = new ArrayList<Integer>();
		
		screens.add(new MainMenu(this));
		screens.add(new PauseMenu(this));
		screens.add(new GameScreen(this));
		screens.add(new InstructionScreen(this));
		screens.add(new ExitScreen(this));
		screens.add(new StartScreen(this));
		
		prevScreen = activeScreen; 
		activeScreen = screens.get(0);
	}



	/**
	 * The PApplet settings method.
	 */
	public void settings() {
		size(activeScreen.DRAWING_WIDTH, activeScreen.DRAWING_HEIGHT);
	}
	
	/**
	 * The PApplet setup method.
	 */
	public void setup() {
		surface.setResizable(true);
		for (Screen s : screens)
			s.setup();
		
//		spawnNewCharacter();
	}
	
	/**
	 * The PApplet draw method.
	 * Used to draw the Sprites onto the GUI.
	 */
	public void draw() {
		ratioX = (float)width/activeScreen.DRAWING_WIDTH;
		ratioY = (float)height/activeScreen.DRAWING_HEIGHT;

		pushMatrix();
		
		scale(ratioX, ratioY);
		
		pushStyle();
		activeScreen.draw();
		popStyle();
		
		popMatrix();
		
	}

	/**
	 * The PApplet keyPressed method.
	 */
	public void keyPressed() {
		if (keyCode == KeyEvent.VK_ESCAPE) key = 0; //override automatic game exit
		keys.add(keyCode);
	}

	/**
	 * The PApplet keyReleased method.
	 */
	public void keyReleased() {
		if(keyCode == KeyEvent.VK_ESCAPE) {
			System.out.println(screens.indexOf(activeScreen));
			
			switch(screens.indexOf(activeScreen)) {
			case ScreenSwitcher.GAME: 
				switchScreen(ScreenSwitcher.PAUSEMENU);
				break;
			case ScreenSwitcher.PAUSEMENU:
				switchScreen(ScreenSwitcher.GAME);
				break;
			case ScreenSwitcher.INSTRUCTIONS:
			case ScreenSwitcher.EXIT:
			case ScreenSwitcher.START:
				switchToPreviousScreen();
				break;
			}
			
		}
		
		while(keys.contains(keyCode))
			keys.remove(new Integer(keyCode));
	}
	
	public void keyTyped() {
		
	}

	/**
	 * 
	 * @param keyCode - the ascii code of the specified key.
	 * @return Returns true when the specified key is being pressed; returns false otherwise.
	 */
	public boolean isPressed(Integer keyCode) {
		return keys.contains(keyCode);
	}

	/**
	 * The PApplet mousePressed method.
	 */
	public void mousePressed() {
		activeScreen.mousePressed();
	}
	
	/**
	 * The PApplet mouseMoved method.
	 */
	public void mouseMoved() {
		activeScreen.mouseMoved();
	}
	
	/**
	 * The PApplet mouseDragged method.
	 */
	public void mouseDragged() {
		activeScreen.mouseDragged();
	}
	
	/**
	 * The PApplet mouseReleased method.
	 */
	public void mouseReleased() {
		activeScreen.mouseReleased();
	}
	
	/**
	 * Finds the true point on the DrawingSurface given the assumed point based on the original size of the DrawingSurface.
	 * 
	 * @param assumed - the assumed Point.
	 * @return the actual point of the assumed point.
	 */
	public Point assumedCoordinatesToActual(Point assumed) {
		return new Point((int)(assumed.getX()*ratioX), (int)(assumed.getY()*ratioY));
	}

	/**
	 * Finds the assumed point on the DrawingSurface given the actual point based on the original size of the DrawingSurface.
	 * 
	 * @param actual - the actual Point.
	 * @return the assumed point of the actual point.
	 */
	public Point actualCoordinatesToAssumed(Point actual) {
		return new Point((int)(actual.getX()/ratioX) , (int)(actual.getY()/ratioY));
	}

	@Override
	public void switchScreen(int i) {
		System.out.println("switching to " + i);
		prevScreen = activeScreen;
		activeScreen = screens.get(i);
	}
	
	@Override
	public void switchToPreviousScreen() {
		Screen tempScreen = prevScreen;
		prevScreen = activeScreen;
		activeScreen = tempScreen;
	}
	
}
