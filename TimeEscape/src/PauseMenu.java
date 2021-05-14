import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
/**
 * Represents the pause menu.
 * 
 * @author Ethan Chang
 * @version 5/7/2021
 */

public class PauseMenu extends JPanel implements ActionListener {

	Main m;
	
	public PauseMenu(Main m) {
		this.m = m;
		JButton button = new JButton("Resume");
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
