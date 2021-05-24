package gui;

public interface ScreenSwitcher {
	public static final int MAINMENU = 0;
	public static final int PAUSEMENU = 1;
	public static final int GAME = 2;
	
	public void switchScreen(int i);
}
