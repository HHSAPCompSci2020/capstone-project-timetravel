package gui;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Represents the main menu screen.
 * 
 * @author Ethan Chang
 * @version 5/22/2021
 */

public class MainMenu extends Screen {

	private DrawingSurface surface;
	
	private Rectangle button;
	
	public MainMenu(DrawingSurface surface) {
//		super(surface.width, surface.height);
		super(800, 600);
		this.surface = surface;
		
		button = new Rectangle(800/2-100,600/2-50,200,100);
	}
	
	@Override
	public void draw() {

		surface.pushStyle();
		
		surface.background(255,255,255);
		
		surface.rect(button.x, button.y, button.width, button.height, 10, 10, 10, 10);
		surface.fill(0);
		String str = "Start";
		float w = surface.textWidth(str);
		surface.text(str, button.x+button.width/2-w/2, button.y+button.height/2);
		
		surface.popStyle();
	}
	
	@Override
	public void mousePressed() {
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		if (button.contains(p))
			surface.switchScreen(ScreenSwitcher.GAME);
	}

}
