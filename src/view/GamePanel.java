package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import model.Player;


@SuppressWarnings("deprecation")

public class GamePanel extends JPanel implements Observer{
	
    private Player player;
    private PlayerView playerView;
	

	public GamePanel() {
		this.setBackground(Color.BLACK);
		
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
