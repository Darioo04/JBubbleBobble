package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import model.Player;


@SuppressWarnings("deprecation")

public class GamePanel extends StateScreenPanel implements Observer{
	private static GamePanel instance;
    private Player player;
    private PlayerView playerView;
	
    public static GamePanel getInstance() {
    	if (instance==null) instance = new GamePanel();
    	return instance;
    }

	private GamePanel() {
		this.setBackground(Color.BLACK);
		add(new JPanel(new GridLayout(1,3,5,5)) {
			{
				add(new JPanel());
				add(new JPanel());
				add(new JPanel());
			}
		},BorderLayout.PAGE_START);
	}
	
	public void setPlayer(Player player) {
		this.player = player;
        playerView = new PlayerView(player);
        this.add(playerView);
    }
	
	@Override
    public void update(Observable o, Object arg) {
        playerView.repaint();
    }
	
}
