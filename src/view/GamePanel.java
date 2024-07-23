package view;

import java.awt.CardLayout;
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
	private static GamePanel instance;
	
	
	public static GamePanel getInstance() {
		if (instance==null) instance = new GamePanel();
		return instance;
	}
	
	private GamePanel() {
		super(new GridLayout(14, 16));
		setBackground(Color.BLACK);
		setVisible(true);
		
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
