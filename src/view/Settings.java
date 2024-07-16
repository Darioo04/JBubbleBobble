package view;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.*;
public class Settings extends JPanel {
	
	private JButton backButton = new JButton("back to menu");
	
	public Settings() {
		this.setSize(900,600);
		this.setBackground(Color.GRAY);
		this.setLayout(new BorderLayout());
		this.add(backButton,BorderLayout.SOUTH);

	}
	
	public JButton getBackButton() {
		return backButton;
	}
	 
}
