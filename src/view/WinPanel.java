package view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.GameConstants;

public class WinPanel extends JFrame {
	
	public WinPanel() {
		super("WIN PANEL");
		int scaledWidth = (int) (GameConstants.ORIGINAL_WIDTH * GameConstants.SCALE);
        int scaledHeight = (int) (GameConstants.ORIGINAL_HEIGHT * GameConstants.SCALE);
        setSize(scaledWidth, scaledHeight);
		setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBackground(Color.BLACK);
        setVisible(true);
        
		add(new JPanel(new BorderLayout()) {
			{
				setBackground(Color.BLACK);
				FontView font = new FontView("You Win!!!");
				add(font,BorderLayout.CENTER);
			}
		},BorderLayout.CENTER);
		
	}
	
	public static void main(String[] args) {
		new WinPanel();
	}
}
