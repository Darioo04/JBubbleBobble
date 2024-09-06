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
import model.Food;

@SuppressWarnings("deprecation")

public class FoodView extends JLabel implements Observer {
	
	private BufferedImage sprite;
	private ImageIcon resizedIcon;
	private static final String path = "/sprites/items/";

	public FoodView(Food food) {
		while (sprite == null) {
			try {
				sprite = switch (food.getType()) {
					case PEPPER -> ImageIO.read(getClass().getResource(path + "pepper.png"));
					case EGGPLANT -> ImageIO.read(getClass().getResource(path + "eggplant.png"));
					case RED_TURNIP -> ImageIO.read(getClass().getResource(path + "redTurnip.png"));
					case CORN -> ImageIO.read(getClass().getResource(path + "corn.png"));
					case BANANA -> ImageIO.read(getClass().getResource(path + "banana.png"));
					case WATERMELON -> ImageIO.read(getClass().getResource(path + "watermelon.png"));
					case POTATO -> ImageIO.read(getClass().getResource(path + "potato.png"));
					case HAMBURGER -> ImageIO.read(getClass().getResource(path + "hamburger.png"));
					case COCKTAIL -> ImageIO.read(getClass().getResource(path + "cocktail.png"));
					case CURRY_RICE -> ImageIO.read(getClass().getResource(path + "curryRice.png"));
					case YELLOW_DIAMOND -> ImageIO.read(getClass().getResource(path + "yellowDiamond.png"));
					case RED_DIAMOND -> ImageIO.read(getClass().getResource(path + "redDiamond.png"));
					case GOLD_CROWN -> ImageIO.read(getClass().getResource(path + "goldCrown.png"));
				};
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		setBounds(food.getX(),food.getY(),GameConstants.ITEM_SIZE,GameConstants.ITEM_SIZE);
		resizeIcon(sprite);
	    setIcon(resizedIcon); 
	    setVisible(true);
		
	}
	
	public void resizeIcon(BufferedImage originalImage) {
		Image resizedImage = originalImage.getScaledInstance(this.getWidth(), this.getHeight(),Image.SCALE_FAST);
        resizedIcon = new ImageIcon(resizedImage);
    }
	
	public BufferedImage getSprite() {
		return sprite;
	}
	
	public void update(Observable o,Object arg) {
		if (o instanceof Food) {
			Food food = (Food) o;
			setBounds(food.getX(), food.getY(), GameConstants.ITEM_SIZE, GameConstants.ITEM_SIZE);
		}
	}
	
}
