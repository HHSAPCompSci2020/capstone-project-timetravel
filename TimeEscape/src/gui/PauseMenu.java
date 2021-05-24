package gui;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JPanel;
/**
 * Represents the pause menu screen.
 * 
 * @author Ethan Chang
 * @version 5/22/2021
 */

public class PauseMenu extends Screen {

	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	
	private DrawingSurface surface;
	private Rectangle button;
	
	public PauseMenu(DrawingSurface surface) {
		super(WIDTH, HEIGHT);
		this.surface = surface;
		
		button = new Rectangle(WIDTH/2-100,HEIGHT/2-50,200,100);
	}
	
	@Override
	public void draw() {

		surface.pushStyle();
		
		surface.rect(button.x, button.y, button.width, button.height, 10, 10, 10, 10);
		surface.fill(255,255,255);
		String str = "Resume";
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
