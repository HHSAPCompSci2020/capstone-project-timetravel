package timeescape.gui.screens;

import java.awt.Point;
import java.awt.Rectangle;

import timeescape.gui.DrawingSurface;

/**
 * Represents the starting screen before playing the game. Based on Mr. Shelby's Screen Switching demo.
 * 
 * @author Ethan Chang
 * @version 5/21/2021
 * 
 */
public class StartScreen extends Screen {
	
	private DrawingSurface surface;
	private Rectangle button, backButton;
	
	public StartScreen(DrawingSurface surface) {
		super(DrawingSurface.ASSUMED_WIDTH, DrawingSurface.ASSUMED_HEIGHT);
		this.surface = surface;
		
		button = new Rectangle(DRAWING_WIDTH/2 - 100, DRAWING_HEIGHT/2 - 50, 200, 100);
		backButton = new Rectangle(0, 0, 25, 25);
	}
	
	public void draw() {
		surface.background(0);
		
		//start button
		{
			surface.pushStyle();
			surface.fill(0,128,0);
			surface.rect(button.x, button.y, button.width, button.height, 10, 10, 10, 10);
			surface.fill(0);
			surface.textSize(12);
			String str = "Start";
			float w = surface.textWidth(str);
			surface.text(str, button.x+button.width/2-w/2, button.y+button.height/2);
			surface.popStyle();
		}
		
		//back button
		{
			surface.fill(0);
			surface.rect(backButton.x, backButton.y, backButton.width, backButton.height, 10, 10, 10, 10);
			surface.fill(255,255,255);
			surface.textSize(12);
			String str = "x";
			float w = surface.textWidth(str);
			surface.text(str, backButton.x+backButton.width/2-w/2, backButton.y+backButton.height/2);
		}
	}
	
	@Override
	public void mousePressed() {
		
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		if (button.contains(p)) surface.switchScreen(ScreenSwitcher.GAME);
		else if (backButton.contains(p)) surface.switchToPreviousScreen();
	}
}
