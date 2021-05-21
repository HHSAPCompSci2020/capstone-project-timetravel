import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;
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
	private ArrayList<Shape> walls;
	private Character character;

	//	private TimeRecordedActions timeRecordedActions;
	private PositionRecord recordedPositions;
	private TimeCharacter tc;
	private boolean timeTraveling;
	private boolean recording;

	public DrawingSurface() {
		super();

		keys = new ArrayList<Integer>();

		//		timeRecordedActions = new TimeRecordedActions(character);
		recordedPositions = new PositionRecord();
		timeTraveling = false;
		recording = false;
		tc = null;

		walls = new ArrayList<>();
		walls.add(new Wall(0, 300, 1000, 50));
		walls.add(new Wall(500, 0, 50, 1000));
		
		
	}

	public void spawnNewCharacter() {
		PImage img = loadImage("images/stickman.png");
		if (img == null) System.out.println("image loading error");
		character = new Character(img, 50.0,50.0);
	}

	private void spawnTimeCharacter(Character characterSnapshot) {
		tc = new TimeCharacter(characterSnapshot);
	}
	//	public void spawnNewWall(double x, double y, double width, double height) {
	//		walls.add(new Wall(x, y, width, height));
	//	}

	public void setup() {
		spawnNewCharacter();
	}

	public void draw() {

		background(0,255,255);   

		fill(100);
		for (Shape w : walls) {
			if (w instanceof Wall) ((Wall)w).draw(this);
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
			recordedPositions.add(character.getX(), character.getY());
			//			timeRecordedActions.add(keyCode);
		} else if (timeTraveling) {
			//replaying if timeTraveling

			tc.draw(this);
			Point2D.Double p = recordedPositions.replayPositions();
			
			if (p != null) tc.setPosition(p);
			else {
				timeTraveling = false;
				walls.remove(tc);
			}

			//			System.out.println("XXX currentFrame=" + timeRecordedActions.currentFrame + ", action=" + (char)(int)timeRecordedActions.keys.get(timeRecordedActions.currentFrame));
			//			int action = timeRecordedActions.replayActions();
			//			
			//			if (action == KeyEvent.VK_A)
			//				tc.walk(-1);
			//			if (action == KeyEvent.VK_D)
			//				tc.walk(1);
			//			if (action == KeyEvent.VK_SPACE || action == KeyEvent.VK_W)
			//				tc.jump();
			//			if (action == KeyEvent.VK_T || action == -1) {
			//				timeTraveling = false;
			//				
			//			}
			//			time character act
			//			tc.act(walls);
		}

	}

	//Recording and replaying

	private boolean setTimeTravelPoint() {
		if (!recording) {
			recording = true;
			recordedPositions.setCharacterSnapshot(character.getCharacterCopy());
			System.out.println("set");
			return true;
		} else return false;
	}

	private boolean timeTravel() {
		if(recording) {
			recording = false;
			timeTraveling = true;
			spawnTimeCharacter(recordedPositions.getInitialCharacterSnapshot());
			System.out.println("travel");
			walls.add(tc);
			return true;
		} else return false;
	}

	//key methods

	public void keyPressed() {
		keys.add(keyCode);
	}

	public void keyReleased() {
		if (keyCode == KeyEvent.VK_0) {
			for (Shape s : walls) {
				System.out.println(s);
			}
		}
		while(keys.contains(keyCode))
			keys.remove(new Integer(keyCode));
	}

	public boolean isPressed(Integer code) {
		return keys.contains(code);
	}

	private class PositionRecord {
		
		private ArrayList<Point2D.Double> points;
		private int currentFrame;
		private Character character;

		public PositionRecord() {
			currentFrame = 0;
			points = new ArrayList<>();
		}

		public Point2D.Double replayPositions() {
			if (updateFrame()) return points.get(currentFrame);
			else return null;
		}

		private boolean updateFrame() {
			if (currentFrame < points.size() - 1) {
				currentFrame++;
				return true;
			} else {
				reset();
				return false;
			}
		}

		public void add(double x, double y) {
			points.add(new Point2D.Double(x, y));
		}

		public ArrayList<Point2D.Double> getPoints() {
			return points;
		}

		public void setCharacterSnapshot(Character c) {
			character = c;
		}

		public Character getInitialCharacterSnapshot() {
			return character;
		}

		public void reset() {
			points.clear();
			currentFrame = 0;
			System.out.println("actions cleared");
		}
	}


	//	unreliable replay

	//	private class TimeRecordedActions {
	//		private ArrayList<Integer> keys;
	//		private int currentFrame;
	//		private Character character;
	//		
	//		public TimeRecordedActions(Character c) {
	//			currentFrame = 0;
	//			keys = new ArrayList<>();


	//			character = c;
	//		}
	//		
	//		public void setCharacterSnapshot(Character c) {
	//			character = c;
	//		}
	//		
	//		public int replayActions() {
	//			if (updateFrame()) return getKeyAtFrame();
	//			else return -1;
	//		}
	//		
	//		private int getKeyAtFrame() {
	//			return keys.get(currentFrame);
	//		}
	//		
	//		private boolean updateFrame() {
	//			if (currentFrame < keys.size() - 1) {
	//				currentFrame++;
	//				return true;
	//			} else {
	//				reset();
	//				return false;
	//			}
	//			
	//		}
	//		
	//		public void add(int code) {
	//			keys.add(code);
	//		}
	//		
	//		public ArrayList<Integer> getKeys() {
	//			return keys;
	//		}
	//		
	//		public Character getInitialCharacterSnapshot() {
	//			return character;
	//		}
	//		
	//		public void reset() {
	//			keys.clear();
	//			currentFrame = 0;
	//			System.out.println("actions cleared");
	//		}
	//	}
}
