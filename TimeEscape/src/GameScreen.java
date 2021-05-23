import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import processing.core.PImage;
import sprites.Character;
import sprites.TimeCharacter;
import sprites.Wall;

public class GameScreen extends Screen {


	private DrawingSurface surface;
	
	private ArrayList<Shape> walls;
	private Character character;

	//	private TimeRecordedActions timeRecordedActions;
	private PositionRecord recordedPositions;
	private TimeCharacter tc;
	private boolean timeTraveling;
	private boolean recording;
	
	public GameScreen(DrawingSurface surface) {
		super(800, 600);
		this.surface = surface;


		//		timeRecordedActions = new TimeRecordedActions(character);
		recordedPositions = new PositionRecord();
		timeTraveling = false;
		recording = false;
		tc = null;

		walls = new ArrayList<>();
		walls.add(new Wall(0, 300, 1000, 50));
		walls.add(new Wall(500, 0, 50, 1000));

	}
	
	@Override
	public void setup() {
		spawnNewCharacter();
	}
	
	
	@Override
	public void draw() {
		surface.background(0,255,255);  
		surface.fill(100);
		for (Shape w : walls) {
			if (w instanceof Wall) ((Wall)w).draw(surface);
			//			if (r instanceof Wall) {
			//				rect((float)r.getX(),(float)r.getY(), (float)r.getWidth(), (float)r.getHeight());
			//			}
		}

		character.draw(surface);


		if (surface.isPressed(KeyEvent.VK_A))
			character.walk(-1);
		if (surface.isPressed(KeyEvent.VK_D))
			character.walk(1);
		if (surface.isPressed(KeyEvent.VK_SPACE) || surface.isPressed(KeyEvent.VK_W))
			character.jump();
		if (surface.isPressed(KeyEvent.VK_F))
			setTimeTravelPoint();
		if (surface.isPressed(KeyEvent.VK_T))
			timeTravel();
		//		character act
		character.act(walls);

		if (recording) {
			recordedPositions.add(character.getX(), character.getY());
			//			timeRecordedActions.add(keyCode);
		} else if (timeTraveling) {
			//replaying if timeTraveling

			tc.draw(surface);
			Point2D.Double p = recordedPositions.replayPositions();
			
			if (p != null) tc.setPosition(p);
			else {
				timeTraveling = false;
				walls.remove(tc);
			}

		}

	}
	
	
	public void spawnNewCharacter() {
		PImage img = surface.loadImage("images/stickman.png");
		if (img == null) System.out.println("image loading error");
		character = new Character(img, 50.0,50.0);
	}

	private void spawnTimeCharacter(Character characterSnapshot) {
		tc = new TimeCharacter(characterSnapshot);
	}
	
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
