package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import model.Player;


@SuppressWarnings("deprecation")

public class GamePanel extends StateScreenView {
	private static GamePanel instance;
    private Player player;
 
	
    public static GamePanel getInstance() {
    	if (instance==null) instance = new GamePanel();
    	return instance;
    }

	private GamePanel() {
		super();
		setVisible(true);
		setBackground(Color.BLACK);
		add(new JPanel(new GridLayout(2,1,0,0)) {
			{
				add(new JPanel(new GridLayout(2,1,0,0)) {
					{
						add(new JPanel(new BorderLayout()) {
							{
								setBackground(Color.BLACK);
								FontView font = new FontView("HP");
								add(font,BorderLayout.CENTER);
							}
						});
						add(new JPanel() {
							{
								setBackground(Color.BLACK);
								add(new JLabel("0"));
							}
						});
						
					}
				},BorderLayout.WEST);
				add(new JPanel(new GridLayout(2,1,0,0)) {
					{
						add(new JPanel(new BorderLayout()) {
							{
								setBackground(Color.BLACK);
								FontView font = new FontView("High score");
								add(font,BorderLayout.CENTER);
							}
						});
						add(new JPanel() {
							{
								setBackground(Color.BLACK);
								add(new JLabel("0"));
							}
						});
						
					}
				},BorderLayout.EAST);
				
			}
		},BorderLayout.PAGE_START);
		
		
	}
	
	public void setPlayer(Player player) {
		this.player = player;
    }
	
}
