package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import model.GameModel;

@SuppressWarnings("deprecation")

public class GamePanel extends JPanel implements Observer{
	
	private GameModel model;
    private PlayerView playerView;
	

	public GamePanel(GameModel gameModel) {
		super(new GridLayout(14, 16));
		this.model = gameModel;
		this.model.getPlayer().addObserver(this);
		this.setBackground(Color.BLACK);
		
		playerView = new PlayerView(model.getPlayer());
		
		this.add(playerView);
		
	}
	
	@Override
    public void update(Observable o, Object arg) {
        playerView.repaint();
    }
	
}
