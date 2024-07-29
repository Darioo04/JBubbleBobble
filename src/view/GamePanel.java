package view;


import java.awt.Color;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import model.GameConstants;
import model.Player;


public class GamePanel extends StateScreenView {
	private static GamePanel instance;
    private Player player;
 
	
    public static GamePanel getInstance() {
    	if (instance==null) instance = new GamePanel();
    	return instance;
    }

	private GamePanel() {
//		super();
		this.setVisible(true);
		this.setBackground(Color.BLACK);
//		add(new JPanel(new GridBagLayout()) {
//			{
//				
//			}
//		});
		
		this.setPreferredSize(new Dimension(GameConstants.SCREEN_WIDTH, GameConstants.SCREEN_HEIGHT));
//		super();
//		setVisible(true);
//		setBackground(Color.BLACK);
//		add(new JPanel(new GridLayout(2,1,0,0)) {
//			{
//				add(new JPanel(new GridLayout(2,1,0,0)) {
//					{
//						add(new JPanel(new BorderLayout()) {
//							{
//								setBackground(Color.BLACK);
//								FontView font = new FontView("HP");
//								add(font,BorderLayout.CENTER);
//							}
//						});
//						add(new JPanel() {
//							{
//								setBackground(Color.BLACK);
//								add(new JLabel("0"));
//							}
//						});
//						
//					}
//				},BorderLayout.WEST);
//				add(new JPanel(new GridLayout(2,1,0,0)) {
//					{
//						add(new JPanel(new BorderLayout()) {
//							{
//								setBackground(Color.BLACK);
//								FontView font = new FontView("High score");
//								add(font,BorderLayout.CENTER);
//							}
//						});
//						add(new JPanel() {
//							{
//								setBackground(Color.BLACK);
//								add(new JLabel("0"));
//							}
//						});
//						
//					}
//				},BorderLayout.EAST);
//				
//			}
//		},BorderLayout.PAGE_START);
		
	}
	
	@Override
    public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
	
	public void setPlayer (Player player) {
		this.player = player;
    }
	
}
