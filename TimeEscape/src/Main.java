import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import processing.core.PApplet;

public class Main extends JFrame{
	
	private JPanel cardPanel;
	
	private PauseMenu pMenuPanel;
	private MainMenu mMenuPanel;
	private DrawingSurface drawPanel;
	
	
	public Main(String title) {
		super(title);
		setBounds(100, 100, 800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		cardPanel = new JPanel();
		CardLayout cl = new CardLayout();
		cardPanel.setLayout(cl);
		
		pMenuPanel = new PauseMenu(this);
		mMenuPanel = new MainMenu(this);
		drawPanel = new DrawingSurface();
		drawPanel.init();
		
		cardPanel.add(mMenuPanel, "1");
		cardPanel.add(pMenuPanel, "2");
		cardPanel.add(drawPanel, "3");
		
		add(cardPanel);
		
		
		setVisible(true);
		setFocusable(true);
		requestFocus();
		
	}
	
	public static void main(String[] args) {
		Main m = new Main("Time Escape");
	}
	
	public void changePanel() {
		
//		((CardLayout)cardPanel.getLayout()).next(cardPanel);
		((CardLayout)cardPanel.getLayout()).next(cardPanel);
		
//		For some reason, requestFocus() causing problems for PApplet
		drawPanel.requestFocus();
	}

	
}
