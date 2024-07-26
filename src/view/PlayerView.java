package view;


import model.Player;

public class PlayerView extends EntityView {
	
	private static PlayerView instance;
	
	private PlayerView(Player player) {
		super(player);
    }
	
	public static PlayerView getInstance(Player player) {
		if (instance==null) instance = new PlayerView(player);
		return instance;
	}
	
}
