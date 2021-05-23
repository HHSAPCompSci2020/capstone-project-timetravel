import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import processing.core.PApplet;


/**
 * This class represents the GUI and draws the screens and detects peripheral input.
 * 
 * @author Ethan Chang
 * @version 5/7/2021
 */
public class DrawingSurface extends PApplet implements ScreenSwitcher {

	public float ratioX, ratioY;
	
//	private Rectangle screenRect;

	private Screen activeScreen;
	private ArrayList<Screen> screens;
	
	
	private ArrayList<Integer> keys;

	
	public DrawingSurface() {
		screens = new ArrayList<Screen>();
		
		keys = new ArrayList<Integer>();
		
		screens.add(new MainMenu(this));
		screens.add(new PauseMenu(this));
		screens.add(new GameScreen(this));
		
		activeScreen = screens.get(0);
	}



	public void settings() {
		size(activeScreen.DRAWING_WIDTH, activeScreen.DRAWING_HEIGHT);
	}
	
	public void setup() {
		surface.setResizable(true);
		for (Screen s : screens)
			s.setup();
		
//		spawnNewCharacter();
	}
	

	public void draw() {
		ratioX = (float)width/activeScreen.DRAWING_WIDTH;
		ratioY = (float)height/activeScreen.DRAWING_HEIGHT;

		pushMatrix();
		
		scale(ratioX, ratioY);
		
		activeScreen.draw();
		
		popMatrix();
		
	}

	public void keyPressed() {
		if (keyCode == KeyEvent.VK_ESCAPE) key = 0; //override automatic game exit
		keys.add(keyCode);
	}

	public void keyReleased() {
		while(keys.contains(keyCode))
			keys.remove(new Integer(keyCode));
		
		if(keyCode == KeyEvent.VK_ESCAPE) {
			if (screens.indexOf(activeScreen) == ScreenSwitcher.GAME)
				switchScreen(ScreenSwitcher.PAUSEMENU);
			else if (screens.indexOf(activeScreen) == ScreenSwitcher.PAUSEMENU)
				switchScreen(ScreenSwitcher.GAME);
		}
	}

	public boolean isPressed(Integer code) {
		return keys.contains(code);
	}

	public void mousePressed() {
		activeScreen.mousePressed();
	}
	
	public void mouseMoved() {
		activeScreen.mouseMoved();
	}
	
	public void mouseDragged() {
		activeScreen.mouseDragged();
	}
	
	public void mouseReleased() {
		activeScreen.mouseReleased();
	}
	
	public Point assumedCoordinatesToActual(Point assumed) {
		return new Point((int)(assumed.getX()*ratioX), (int)(assumed.getY()*ratioY));
	}

	public Point actualCoordinatesToAssumed(Point actual) {
		return new Point((int)(actual.getX()/ratioX) , (int)(actual.getY()/ratioY));
	}

	@Override
	public void switchScreen(int i) {
		activeScreen = screens.get(i);
	}
	
}
