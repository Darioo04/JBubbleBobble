package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import model.Enemy;
import model.Player;
import javax.swing.Timer;

public class GameController {
	
    private PlayerController playerController;
    private Player player;
    private final int FPS = 60;
    private Timer timer;
    private ArrayList<Enemy> enemies;
//    private PlayerView playerView;
    
    private static GameController instance;
    
    public static GameController getInstance() {
    	if (instance==null) instance = new GameController();
    	return instance;
    }
    
    private GameController() {
        
    	this.timer = new Timer(16, new ActionListener() {		//16ms per avere 60FPS
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
        this.player = Player.getInstance();
        this.playerController = new PlayerController(player);
//        this.playerView = new PlayerView(player);
    }
    
    public void startGame() {
        timer.start(); 			// Inizia il game loop
    }

    public void stopGame() {
        timer.stop(); 			// Ferma il game loop
    }

    public KeyListener getPlayerController() {
        return playerController;
    }
    
    public Player getPlayer() {
        return player;
    }

}
