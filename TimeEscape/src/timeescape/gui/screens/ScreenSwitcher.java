package timeescape.gui.screens;

/**
 * 
 * @author Ethan Chang
 * @version 5/22/2021
 * 
 *  An interface representing the ability to switch Screens. Based on Mr. Shelby's Screen Switching demo.
 */
public interface ScreenSwitcher {
	public static final int MAINMENU = 0;
	public static final int PAUSEMENU = 1;
	public static final int GAME = 2;
	public static final int INSTRUCTIONS = 3;
	public static final int EXIT = 4;
	public static final int START = 5;
	
	/**
	 * 
	 * @param index - switches the screen to the specified index.
	 */
	public void switchScreen(int index);
	
	/**
	 * Switches to the previous screen.
	 */
	public void switchToPreviousScreen();
}
