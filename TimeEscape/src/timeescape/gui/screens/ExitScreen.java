package timeescape.gui.screens;

import java.awt.Point;
import java.awt.Rectangle;

import timeescape.gui.DrawingSurface;

/**
 * Represents the exit screen. Based on Mr. Shelby's Screen Switching demo.
 * @author Ethan Chang
 * @version 5/23/2021
 *
 */
public class ExitScreen extends Screen {

	private DrawingSurface surface;
	private Rectangle yesButton, noButton;

	public ExitScreen(DrawingSurface surface) {
		super(DrawingSurface.ASSUMED_WIDTH, DrawingSurface.ASSUMED_HEIGHT);
		this.surface = surface;

		yesButton = new Rectangle(DRAWING_WIDTH/2-205,DRAWING_HEIGHT/2-50,200,100);
		noButton = new Rectangle(DRAWING_WIDTH/2 + 5,DRAWING_HEIGHT/2-50,200,100);
	}

	@Override
	public void draw() {

		surface.background(0);
		//text
		{
			surface.pushStyle();
			surface.fill(255,255,255);
			surface.textSize(30);
			String str = "Are You Sure?";
			float w = surface.textWidth(str);
			surface.text(str, DRAWING_WIDTH/2 - w/2, 200);
			surface.popStyle();
		}
		
		// yes button
		{
			surface.pushStyle();
			surface.fill(255,0,0);
			surface.rect(yesButton.x, yesButton.y, yesButton.width,yesButton.height, 10, 10, 10, 10);
			surface.fill(0);
			surface.textSize(12);
			String str = "Yes";
			float w = surface.textWidth(str);
			surface.text(str, yesButton.x+yesButton.width/2-w/2, yesButton.y+yesButton.height/2);
			surface.popStyle();
		}

		// no button
		{
			surface.pushStyle();
			surface.fill(255,255,255);
			surface.rect(noButton.x, noButton.y, noButton.width,noButton.height, 10, 10, 10, 10);
			surface.fill(0);
			surface.textSize(12);
			String str = "No";
			float w = surface.textWidth(str);
			surface.text(str, noButton.x+noButton.width/2-w/2, noButton.y+noButton.height/2);
			surface.popStyle();
		}


	}
	
	@Override
	public void mousePressed() {
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		if (yesButton.contains(p)) surface.exit();
		else if (noButton.contains(p)) surface.switchToPreviousScreen();
	}
}
