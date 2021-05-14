import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
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
				
		cardPanel.add(mMenuPanel, "m");
		cardPanel.add(pMenuPanel, "p");
		cardPanel.add(drawPanel, "d");
		
		add(cardPanel);
		
		setVisible(true);
		
		
	}
	
	public static void main(String[] args) {
		Main m = new Main("Time Escape");
	}
	
	public void changePanel() {
		((CardLayout)cardPanel.getLayout()).next(cardPanel);
		drawPanel.requestFocus();
	}
}
