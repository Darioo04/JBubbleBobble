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

	public FoodView(Food item) {
		try {
			sprite = switch (item.getType()) {
				case PEPPER -> ImageIO.read(getClass().getResource(path+"pepper.png"));
				case EGGPLANT -> ImageIO.read(getClass().getResource(path+"eggplant.png"));
				case RED_TURNIP -> ImageIO.read(getClass().getResource(path+"turnip.png"));
				case CORN -> ImageIO.read(getClass().getResource(path+"corn.png"));
				case BANANA -> ImageIO.read(getClass().getResource(path+"banana.png"));
				case WATERMELON -> ImageIO.read(getClass().getResource(path+"watermelon.png"));
				case ICE_CREAM -> ImageIO.read(getClass().getResource(path+"iceCream.png"));
				case HAMBURGER -> ImageIO.read(getClass().getResource(path+"hamburger.png"));
				case COCKTAIL -> ImageIO.read(getClass().getResource(path+"cocktail.png"));
				case CURRY_RICE -> ImageIO.read(getClass().getResource(path+"curryRice.png"));
				case GOLD_CROWN -> ImageIO.read(getClass().getResource(path+"goldCrown.png"));
			};
			setBounds(item.getX(),item.getY(),GameConstants.ITEM_SIZE,GameConstants.ITEM_SIZE);
			resizeIcon(sprite);
		    setIcon(resizedIcon); 
		    setVisible(true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void resizeIcon(BufferedImage originalImage) {
		Image resizedImage = originalImage.getScaledInstance(this.getWidth(), this.getHeight(),Image.SCALE_FAST);
        resizedIcon = new ImageIcon(resizedImage);
    }
	
	public BufferedImage getSprite() {
		return sprite;
	}
	
	@Override
	public void update(Observable o,Object arg) {
		if (o instanceof Food) {
			Food item = (Food) o;
			setBounds(item.getX(),item.getY(),GameConstants.ITEM_SIZE,GameConstants.ITEM_SIZE);
		}
		
	}
}
