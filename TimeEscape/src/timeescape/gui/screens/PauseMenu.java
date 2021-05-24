package timeescape.gui.screens;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import timeescape.gui.DrawingSurface;
/**
 * Represents the pause menu screen. Based on Mr. Shelby's Screen Switching demo.
 * 
 * @author Ethan Chang
 * @version 5/23/2021
 */

public class PauseMenu extends Screen {
	
	private DrawingSurface surface;
	private Rectangle resumeButton, instructButton, exitButton;
	
	public PauseMenu(DrawingSurface surface) {
		super(DrawingSurface.ASSUMED_WIDTH, DrawingSurface.ASSUMED_HEIGHT);
		this.surface = surface;
		
		resumeButton = new Rectangle(DRAWING_WIDTH/2-100, DRAWING_HEIGHT/2-50,200,50);
		instructButton = new Rectangle(resumeButton.x, resumeButton.y + resumeButton.height + 5, 200, 50);
		exitButton = new Rectangle(instructButton.x, instructButton.y + instructButton.height + 5, 200, 50);
	}
	
	@Override
	public void draw() {
		
		
		surface.fill(0, 0, 0, 5);
		surface.rect(0, 0, DRAWING_WIDTH, DRAWING_HEIGHT);
		
		{
			surface.pushStyle();
			surface.fill(255, 255, 255);
			surface.rect(resumeButton.x, resumeButton.y, resumeButton.width, resumeButton.height, 10, 10, 10, 10);
			
			surface.fill(0);
			surface.textSize(12);
			String str = "Resume Game";
			float w = surface.textWidth(str);
			surface.text(str, resumeButton.x+resumeButton.width/2-w/2, resumeButton.y+resumeButton.height/2);
			surface.popStyle();
		}
		{
			surface.pushStyle();
			surface.fill(255, 255, 255);
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
			surface.fill(255, 255, 255);
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
		if (resumeButton.contains(p)) surface.switchScreen(ScreenSwitcher.GAME);
		else if (instructButton.contains(p)) surface.switchScreen(ScreenSwitcher.INSTRUCTIONS);
		else if (exitButton.contains(p)) surface.switchScreen(ScreenSwitcher.EXIT);
	}
	
	
}
