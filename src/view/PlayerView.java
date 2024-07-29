package view;


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.GameConstants;
import model.Player;

public class PlayerView extends EntityView {
	
	
	public PlayerView (Player player) {
		super(player);
	}
	
}
