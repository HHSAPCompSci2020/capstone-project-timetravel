package timeescape.gui.screens;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import timeescape.gui.DrawingSurface;

/**
 * Represents the main menu screen.
 * 
 * @author Ethan Chang
 * @version 5/22/2021
 */

public class MainMenu extends Screen {

	public final String title = "Time Escape";
	
	private DrawingSurface surface;
	
	private Rectangle startButton, instructButton, exitButton;
	
	public MainMenu(DrawingSurface surface) {
//		super(surface.width, surface.height);
		super(DrawingSurface.ASSUMED_WIDTH, DrawingSurface.ASSUMED_HEIGHT);
		this.surface = surface;
		
		startButton = new Rectangle(DRAWING_WIDTH/2-100,DRAWING_HEIGHT/2-25,200,50);
		instructButton = new Rectangle(startButton.x, startButton.y + startButton.height + 5, 200, 50);
		exitButton = new Rectangle(instructButton.x, instructButton.y + instructButton.height + 5, 200, 50);
	}
	
	@Override
	public void draw() {
		surface.background(255,255,255);
		
		{
			surface.pushStyle();
			surface.fill(0);
			float w = surface.textWidth(title);
			surface.textSize(80);
			surface.text(title, DRAWING_WIDTH/2 - w/2, DRAWING_HEIGHT/4 + 20);
			surface.popStyle();
		}
		{
			surface.pushStyle();
			surface.rect(startButton.x, startButton.y, startButton.width, startButton.height, 10, 10, 10, 10);
			surface.fill(0);
			surface.textSize(12);
			String str = "Play";
			float w = surface.textWidth(str);
			surface.text(str, startButton.x+startButton.width/2-w/2, startButton.y+startButton.height/2);
			surface.popStyle();
		}
		{
			surface.pushStyle();
			surface.rect(instructButton.x, instructButton.y, instructButton.width, instructButton.height, 10, 10, 10, 10);
			surface.fill(0);
			surface.textSize(12);
			String str = "Instructions";
			float w = surface.textWidth(str);
			surface.text(str, instructButton.x+instructButton.width/2-w/2, instructButton.y+instructButton.height/2);
			surface.popStyle();
		}
		{
			surface.pushStyle();
			surface.rect(exitButton.x, exitButton.y, exitButton.width, exitButton.height, 10, 10, 10, 10);
			surface.fill(0);
			surface.textSize(12);
			String str = "Exit";
			float w = surface.textWidth(str);
			surface.text(str, exitButton.x+exitButton.width/2-w/2, exitButton.y+exitButton.height/2);
			surface.popStyle();
		}
		
	}
	
	@Override
	public void mousePressed() {
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		if (startButton.contains(p)) surface.switchScreen(ScreenSwitcher.START);
		else if (instructButton.contains(p)) surface.switchScreen(ScreenSwitcher.INSTRUCTIONS);
		else if (exitButton.contains(p)) surface.switchScreen(ScreenSwitcher.EXIT);
	}

}
