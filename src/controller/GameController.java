package controller;

import java.awt.event.KeyListener;

import model.GameModel;

public class GameController {
	
	private GameModel model;
    private PlayerController playerController;

    public GameController(GameModel model) {
        this.model = model;
        this.playerController = new PlayerController(model.getPlayer());
    }

    public KeyListener getPlayerController() {
        return playerController;
    }
}
