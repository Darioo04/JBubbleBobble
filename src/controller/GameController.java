package controller;

import java.awt.event.KeyListener;

import model.Player;
import view.PlayerView;

public class GameController {
	
    private PlayerController playerController;
    private Player player;
//    private PlayerView playerView;

    public GameController() {
        
        this.player = Player.getInstance();
        this.playerController = new PlayerController(player);
//        this.playerView = new PlayerView(player);
    }

    public KeyListener getPlayerController() {
        return playerController;
    }
    
    public Player getPlayer() {
        return player;
    }
}
