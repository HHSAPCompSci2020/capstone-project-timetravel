
import java.awt.Dimension;

import javax.swing.JFrame;

import processing.awt.PSurfaceAWT;
import processing.core.PApplet;

public class Main {
//public class Main extends JFrame implements KeyListener {


//	private JPanel cardPanel;
//	
//	private PauseMenu pMenuPanel;
//	private MainMenu mMenuPanel;
//	private DrawingSurface drawPanel;
	
	
//	public Main(String title) {
//		super(title);
//		setBounds(100, 100, 800, 600);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		
//		cardPanel = new JPanel();
//		CardLayout cl = new CardLayout();
//		cardPanel.setLayout(cl);
//		
//		pMenuPanel = new PauseMenu(this);
//		mMenuPanel = new MainMenu(this);
//		drawPanel = new DrawingSurface();
//		drawPanel.init();
//		
//		cardPanel.add(mMenuPanel, "1");
//		cardPanel.add(pMenuPanel, "2");
//		cardPanel.add(drawPanel, "3");
//		
//		add(cardPanel);
//		
//		
//		setVisible(true);
//		setFocusable(true);
//		requestFocus();
//		
//	}
	
	public static void main(String[] args) {
		
		DrawingSurface drawing = new DrawingSurface();
		PApplet.runSketch(new String[]{""}, drawing);
		PSurfaceAWT surf = (PSurfaceAWT) drawing.getSurface();
		PSurfaceAWT.SmoothCanvas canvas = (PSurfaceAWT.SmoothCanvas) surf.getNative();
		JFrame window = (JFrame)canvas.getFrame();

		window.setSize(800, 600);
		window.setMinimumSize(new Dimension(100,100));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);

		window.setVisible(true);
		
		
		canvas.requestFocus();
	}
	
//	public void changePanel() {
//		
////		((CardLayout)cardPanel.getLayout()).next(cardPanel);
//		((CardLayout)cardPanel.getLayout()).next(cardPanel);
//		
//		drawPanel.requestFocus();
//	}


	
}
