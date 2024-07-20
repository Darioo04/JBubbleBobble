package model;

public class GameModel {
	
	private Player player;

    public GameModel() {
        this.player = Player.getInstance();
    }

    public Player getPlayer() {
        return player;
    }
}
