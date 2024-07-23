package view;

import java.util.Observer;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.Navigator;
import model.Panels;

@SuppressWarnings("deprecation")


public class MenuPanel extends JPanel {
	private static MenuPanel instance;
//	private int gamesWon;
//	private int gamesPlayed;
	
	public static MenuPanel getInstance() {
		if (instance==null) instance = new MenuPanel();
		return instance;
	}
	
	private MenuPanel() {
		super(new GridLayout(14, 16));
		setBackground(Color.BLACK);
		setVisible(true);
				
		add(new JPanel(new GridLayout(1,3)) {
			{
				setBackground(Color.BLACK);
				setBorder(BorderFactory.createEmptyBorder(50, 30, 50, 30));
				add(new JPanel(new GridBagLayout()) {
					{
						
//						add(new JLabel() {
//							{
//								String text = "Testo di prova";
//								FontView fontText = new FontView(text);
//								fontText.drawFont(getGraphics());
//							}
//						});
						
						GridBagConstraints constraints = new GridBagConstraints();
				        	constraints.weightx = 1;
				        	constraints.weighty = 1;
				            constraints.anchor = GridBagConstraints.NORTHEAST;
				            constraints.gridx = 0;
				            constraints.gridy = 0;
				            constraints.gridwidth = 1;
				            constraints.gridheight = 3;
				         
//				         add(new JLabel("Games won: " + gamesWon),constraints);
//				         add(new JLabel("Games Played: " + gamesPlayed),constraints);
				         
				         add(new JButton("Play"),constraints);				
					}
				}, BorderLayout.SOUTH);
			}
		}, BorderLayout.CENTER);
	}
	
	
}
