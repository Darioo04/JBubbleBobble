package view;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import model.GameConstants;
import model.PowerUp;
import model.PowerUpType;

@SuppressWarnings("deprecation") 

public class PowerUpView extends JLabel implements Observer {

	private BufferedImage sprite;
	private ImageIcon resizedIcon;
	private static final String path = "/sprites/PowerUp/";
	
	public PowerUpView(PowerUp powerUp) {
		try {
			sprite = switch (powerUp.getType()) {
				case PINK_CANDY -> ImageIO.read(getClass().getResource(path+"pinkCandy.png"));
				case BLUE_CANDY -> ImageIO.read(getClass().getResource(path+"blueCandy.png"));
				case YELLOW_CANDY -> ImageIO.read(getClass().getResource(path+"yellowCandy.png"));
				case SHOES -> ImageIO.read(getClass().getResource(path+"shoes.png"));
				case CLOCK -> ImageIO.read(getClass().getResource(path+"clock.png"));
				case DYNAMITE -> ImageIO.read(getClass().getResource(path+"dynamite.png"));
				case CHACK_HEART -> ImageIO.read(getClass().getResource(path+"chackHeart.png"));
				case CRYSTAL_RING -> ImageIO.read(getClass().getResource(path+"crystalRing.png"));
				case AMETHYST_RING -> ImageIO.read(getClass().getResource(path+"amethystRing.png"));
				case RUBY_RING -> ImageIO.read(getClass().getResource(path+"rubyRing.png"));
				case CROSS_OF_THUNDER -> ImageIO.read(getClass().getResource(path+"crossOfThunder.png"));
				case BLUE_LAMP -> ImageIO.read(getClass().getResource(path+"blueLamp.png"));
			};
		} catch (IOException e) {
			e.printStackTrace();
		}
		setBounds(powerUp.getX(), powerUp.getY(), GameConstants.ITEM_SIZE, GameConstants.ITEM_SIZE);
		resizeIcon(sprite);
		setIcon(resizedIcon);
		setVisible(true);
	}
	
	public void resizeIcon(BufferedImage originalImage) {
		Image resizedImage = originalImage.getScaledInstance( getWidth(), getHeight(),Image.SCALE_FAST);
        resizedIcon = new ImageIcon(resizedImage);
    }
	
	public void update(Observable o,Object arg) {
		if (o instanceof PowerUp) {
			PowerUp powerUp = (PowerUp) o;
			setBounds(powerUp.getX(), powerUp.getY(), GameConstants.ITEM_SIZE, GameConstants.ITEM_SIZE);
		}
	}
	
}
