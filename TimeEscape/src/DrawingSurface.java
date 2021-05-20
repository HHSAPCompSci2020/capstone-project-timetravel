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
	private ArrayList<Rectangle2D> walls;
	private Character character;
	
	private TimeRecordedActions timeRecordedActions;
	private TimeCharacter tc;
	private boolean timeTraveling;
	private boolean recording;

	public DrawingSurface() {
		super();
		
		keys = new ArrayList<Integer>();
	
		timeRecordedActions = new TimeRecordedActions(character);
		timeTraveling = false;
		recording = false;
		tc = null;
		
		walls = new ArrayList<>();
		walls.add(new Wall(0, 300, 1000, 50));
		spawnNewCharacter();
	}
	
	public void spawnNewCharacter() {
		
		character = new Character(loadImage("images/character.jpg"), 50.0,50.0);
	}
	
	private void spawnTimeCharacter(Character characterSnapshot) {
		tc = new TimeCharacter(characterSnapshot);
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

//		character act
		character.act(walls);
		
		if (recording) {
			super.noLoop();
			try {
				Thread.sleep(10);
			} catch (Throwable t) {}
			super.loop();
			
			timeRecordedActions.add(keyCode);
		} else if (timeTraveling) {
			//replaying if timeTraveling
			
			super.noLoop();
			try {
				Thread.sleep(10);
			} catch (Throwable t) {}
			super.loop();
			
			tc.draw(this);
			System.out.println("XXX currentFrame=" + timeRecordedActions.currentFrame + ", action=" + (char)(int)timeRecordedActions.keys.get(timeRecordedActions.currentFrame));
			int action = timeRecordedActions.replayActions();
			
			if (action == KeyEvent.VK_A)
				tc.walk(-1);
			if (action == KeyEvent.VK_D)
				tc.walk(1);
			if (action == KeyEvent.VK_SPACE || action == KeyEvent.VK_W)
				tc.jump();
			if (action == KeyEvent.VK_T || action == -1) {
				timeTraveling = false;
				
			}
//			time character act
			tc.act(walls);
		}
	
		

		
	}
	
	//Recording and replaying
	
	private boolean setTimeTravelPoint() {
		if (!recording) {
			recording = true;
			timeRecordedActions.setCharacterSnapshot(character.getCharacterCopy());
			System.out.println("set");
			return true;
		} else return false;
	}
	
	private boolean timeTravel() {
		if(recording) {
			recording = false;
			timeTraveling = true;
			spawnTimeCharacter(timeRecordedActions.getInitialCharacterSnapshot());
			System.out.println("travel");
			return true;
		} else return false;
	}
	
	//key methods
	
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
	
	
//	RECORD POSITION RATHER THAN ACTION, MORE RELIABLE!!!!!!!!
	private class TimeRecordedActions {
		private ArrayList<Integer> keys;
		private int currentFrame;
		private Character character;
		
		public TimeRecordedActions(Character c) {
			currentFrame = 0;
			keys = new ArrayList<>();
			character = c;
		}
		
		public void setCharacterSnapshot(Character c) {
			character = c;
		}
		
		public int replayActions() {
			if (updateFrame()) return getKeyAtFrame();
			else return -1;
		}
		
		private int getKeyAtFrame() {
			return keys.get(currentFrame);
		}
		
		private boolean updateFrame() {
			if (currentFrame < keys.size() - 1) {
				currentFrame++;
				return true;
			} else {
				reset();
				return false;
			}
			
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
		
		public void reset() {
			keys.clear();
			currentFrame = 0;
			System.out.println("actions cleared");
		}
	}
}
