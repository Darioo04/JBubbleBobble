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


public class FoodView extends JLabel {
	
	private BufferedImage sprite;
	private ImageIcon resizedIcon;
	private static final String path = "/sprites/items/";

	public FoodView(Food item) {
		try {
			switch (item.getType()) {
				case PEPPER -> sprite = ImageIO.read(getClass().getResource(path+"pepper.png"));
				case EGGPLANT -> sprite = ImageIO.read(getClass().getResource(path+"eggplant.png"));
				case RED_TURNIP -> sprite = ImageIO.read(getClass().getResource(path+"turnip.png"));
				case CORN -> sprite = ImageIO.read(getClass().getResource(path+"corn.png"));
				case BANANA -> sprite = ImageIO.read(getClass().getResource(path+"banana.png"));
				case WATERMELON -> sprite = ImageIO.read(getClass().getResource(path+"watermelon.png"));
				case ICE_CREAM -> sprite = ImageIO.read(getClass().getResource(path+"iceCream.png"));
				case HAMBURGER -> sprite = ImageIO.read(getClass().getResource(path+"hamburger.png"));
				case COCKTAIL -> sprite = ImageIO.read(getClass().getResource(path+"cocktail.png"));
				case CURRY_RICE -> sprite = ImageIO.read(getClass().getResource(path+"curryRice.png"));
				case GOLD_CROWN -> sprite = ImageIO.read(getClass().getResource(path+"goldCrown.png"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		setBounds(item.getX(),item.getY(),GameConstants.ITEM_SIZE,GameConstants.ITEM_SIZE);
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
	
}
