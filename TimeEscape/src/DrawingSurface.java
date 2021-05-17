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
	private TimeRecordedActions timeRecordedActions;
	private ArrayList<Rectangle2D> walls;
	private Character character;
	private TimeCharacter tc;
//	private boolean timeTravelSet;
	private boolean timeTraveling;

	public DrawingSurface() {
		super();
//		timeTravelSet = false;
		keys = new ArrayList<Integer>();
		timeRecordedActions = null;
		walls = new ArrayList<>();
		walls.add(new Wall(0, 300, 1000, 50));
		spawnNewCharacter();
	}
	
	public void spawnNewCharacter() {
		
		character = new Character(loadImage("images/character.jpg"), 50.0,50.0);
	}
	
	private void spawnTimeCharacter() {
		tc = new TimeCharacter(timeRecordedActions.getInitialCharacterSnapshot());
	}
//	public void spawnNewWall(double x, double y, double width, double height) {
//		walls.add(new Wall(x, y, width, height));
//	}
	
	public void setup() {
		
	}
	
	public void draw() {
		
		background(0,255,255);   
		
		fill(100);
		for (Rectangle2D w : walls) {
			((Wall)w).draw(this);
//			if (r instanceof Wall) {
//				rect((float)r.getX(),(float)r.getY(), (float)r.getWidth(), (float)r.getHeight());
//			}
		}
		
		character.draw(this);
		
		
		if (isPressed(KeyEvent.VK_A))
			character.walk(-1);
		if (isPressed(KeyEvent.VK_D))
			character.walk(1);
		if (isPressed(KeyEvent.VK_SPACE) || isPressed(KeyEvent.VK_W))
			character.jump();
		if (isPressed(KeyEvent.VK_F))
			setTimeTravelPoint();
		if (isPressed(KeyEvent.VK_T))
			timeTravel();
		
		
		if (timeRecordedActions != null) {
			timeRecordedActions.add(keyCode);
		}
		
//		character act
		character.act(walls);
		
//		time character act
		
		
	}
	
	private void setTimeTravelPoint() {
		if (timeRecordedActions == null) {
			timeRecordedActions = new TimeRecordedActions(character);
			System.out.println("set");
		}
	}
	
	private void timeTravel() {
		if(timeRecordedActions != null) {
			timeTraveling = true;
			spawnTimeCharacter();
			timeRecordedActions = null;
			System.out.println("travel");
		}
	}
	
	public void keyPressed() {
		keys.add(keyCode);
	}

	public void keyReleased() {
		if (keyCode == KeyEvent.VK_0) {
			for (int k : timeRecordedActions.getKeys()) {
				System.out.println(k);
			}
		}
		while(keys.contains(keyCode))
			keys.remove(new Integer(keyCode));
	}
	
	

	public boolean isPressed(Integer code) {
		return keys.contains(code);
	}
	
	private class TimeRecordedActions {
		private ArrayList<Integer> keys;
		private int currentFrame;
		private Character character;
		
		public TimeRecordedActions(Character c) {
			currentFrame = 0;
			keys = new ArrayList<>();
			character = c;
		}
		
		public int getKeyAtFrame() {
			return keys.get(currentFrame);
		}
		
		public boolean updateFrame() {
			if (currentFrame < keys.size() - 1) {
				currentFrame++;
				return true;
			} else if (currentFrame == keys.size() - 1)
				keys.clear();
			
			return false;
		}
		
		public void add(int code) {
			keys.add(code);
		}
		
		public ArrayList<Integer> getKeys() {
			return keys;
		}
		
		public Character getInitialCharacterSnapshot() {
			return character;
		}
	}
}
