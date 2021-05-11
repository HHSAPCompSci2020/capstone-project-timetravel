import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Represents the main menu.
 * 
 * @author Ethan Chang
 * @version 5/7/2021
 */

public class MainMenu extends JPanel implements ActionListener {

	Main m;
	
	public MainMenu(Main m) {
		this.m = m;
		JButton button = new JButton("Start");
		button.addActionListener(this);
		add(button);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.toString());
		m.changePanel();
	}

}
