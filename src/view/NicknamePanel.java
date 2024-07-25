package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NicknamePanel extends JFrame {
	private static NicknamePanel instance;
	
	public static NicknamePanel getInstance() {
		if (instance==null) instance = new NicknamePanel();
		return instance;
	}
	
	private NicknamePanel() {
		super("Nickname Panel");
		setVisible(true);
		Dimension dimension = new Dimension(400,300);
		setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
		setPreferredSize(dimension);
		setMinimumSize(dimension);
		setMaximumSize(dimension);
		add(new JPanel(new GridLayout(2,1,0,0)) {
			{
				add(new JPanel(new BorderLayout()) {
					{
						setBackground(Color.BLACK);
						FontView font = new FontView("Choose your Nickname!!!");
						
						add(font);
						
					}
				},BorderLayout.CENTER);
				add(new JTextField() {
					{
						setBackground(Color.BLACK);
						setForeground(Color.WHITE);
						
					}
				},BorderLayout.SOUTH);
			}
		}, BorderLayout.CENTER);
		pack();
		setResizable(false);
	}
	
	public static void main(String[] args) {
		new NicknamePanel();
	}
}
