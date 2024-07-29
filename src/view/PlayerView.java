package view;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.GameConstants;
import model.Player;

public class PlayerView extends EntityView {
	private static PlayerView instance;
	
	public static PlayerView getInstance(Player player) {
		if (instance == null) instance = new PlayerView(player);
		return instance;
	}
	
	private PlayerView (Player player) {
		super(player);
	}

	
}
