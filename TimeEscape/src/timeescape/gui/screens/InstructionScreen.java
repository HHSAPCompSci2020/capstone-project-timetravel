package timeescape.gui.screens;

import java.awt.Point;
import java.awt.Rectangle;

import timeescape.gui.DrawingSurface;

/**
 * Represents the instructions screen. Based on Mr. Shelby's Screen Switching demo.
 * 
 * @author Ethan Chang
 * @version 5/23/2021
 */
public class InstructionScreen extends Screen{
	
	private final String instructions = 
			"Instructions:\n\n" +
			"‘a’ and ‘d’ - move left and right respectively\r\n" + 
			"‘w’ or space - jump\n" + 
			"‘f’ - set a time travel set point\n" + 
			"‘t’ - time travel to the set point\n" + 
			"esc - pause and resume";
	
	private DrawingSurface surface;
	private Rectangle button;
	
	public InstructionScreen(DrawingSurface surface) {
			super(DrawingSurface.ASSUMED_WIDTH, DrawingSurface.ASSUMED_HEIGHT);
			
			this.surface = surface;
			button = new Rectangle(0, 0, 25, 25);
	}
	
	@Override
	public void draw() {
		surface.background(0);
		
		{
			surface.fill(0);
			surface.rect(button.x, button.y, button.width, button.height, 10, 10, 10, 10);
			surface.fill(255,255,255);
			surface.textSize(12);
			String str = "x";
			float w = surface.textWidth(str);
			surface.text(str, button.x+button.width/2-w/2, button.y+button.height/2);
		}
		{
			surface.fill(255,255,255);
			surface.textSize(30);
			float w = surface.textWidth(instructions);
			surface.text(instructions, 100, 100, DRAWING_WIDTH - 200, DRAWING_HEIGHT - 200);
		}
		
	}
	
	@Override
	public void mousePressed() {
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		if (button.contains(p)) surface.switchToPreviousScreen();
	}
}
