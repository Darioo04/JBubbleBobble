package view;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import model.BubbleBullet;
import model.GameConstants;

public class BubbleBulletView extends JLabel implements Observer {

	private ImageIcon resizedIcon;
	private BufferedImage defaultSprite;
	private BubbleBullet bubbleBullet;
	private String path;
	
	private BufferedImage defaultBubble;
	
	public BubbleBulletView(BubbleBullet bubbleBullet) {
		path = "/sprites/Bubbles/";
		this.bubbleBullet = bubbleBullet;
		
		loadDefaultSprite();
		loadSprites();
		
		this.setBounds(bubbleBullet.getX(), bubbleBullet.getY(), GameConstants.BUBBLE_SHOT_SIZE, GameConstants.BUBBLE_SHOT_SIZE);
		this.resizeIcon(defaultSprite);
        this.setIcon(resizedIcon);
        setVisible(true);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if(bubbleBullet.isExpanded()) {
			this.resizeIcon(defaultSprite);
	        this.setIcon(resizedIcon);
			this.setBounds(bubbleBullet.getX(), bubbleBullet.getY(), GameConstants.BUBBLE_EXPANDED_SIZE, GameConstants.BUBBLE_EXPANDED_SIZE);
		}else {
			this.setBounds(bubbleBullet.getX(), bubbleBullet.getY(), GameConstants.BUBBLE_SHOT_SIZE, GameConstants.BUBBLE_SHOT_SIZE);
		}
		
	}
	
	public void resizeIcon(BufferedImage originalImage) {
		Image resizedImage = originalImage.getScaledInstance(this.getWidth(), this.getHeight(),Image.SCALE_FAST);
        resizedIcon = new ImageIcon(resizedImage);
    }
	
	private void loadDefaultSprite() {
		try {
			this.defaultSprite =  ImageIO.read(getClass().getResource(path + "default bubble.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void loadSprites() {
		try {
			
			this.defaultBubble =  ImageIO.read(getClass().getResource(path + "default bubble.png"));
            
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public BubbleBullet getBubbleBullet() {
		return bubbleBullet;
	}

}
