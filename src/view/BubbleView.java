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

@SuppressWarnings("deprecation")

public class BubbleView extends JLabel implements Observer {

	private ImageIcon resizedIcon;
	private BufferedImage defaultSprite;
	private BufferedImage[] explodedBubbles;
	private BufferedImage[] extendsBubbles;
	private BubbleBullet bubbleBullet;
	private static final String path = "/sprites/Bubbles/bubble-";
	
	private BufferedImage defaultBubble;
	
	public BubbleView(BubbleBullet bubbleBullet) {
		this.bubbleBullet = bubbleBullet;
		
		loadDefaultSprite();
		loadSprites();
		
		setBounds(bubbleBullet.getX(), bubbleBullet.getY(), GameConstants.BUBBLE_EXPANDED_SIZE, GameConstants.BUBBLE_EXPANDED_SIZE);
		resizeIcon(defaultSprite);
        setIcon(resizedIcon);
        setVisible(true);
	}
	
	@Override
	public void update(Observable o, Object arg) {
//		if(bubbleBullet.isExpanded()) {
		resizeIcon(defaultSprite);
	    setIcon(resizedIcon);
		setBounds(bubbleBullet.getX(), bubbleBullet.getY(), GameConstants.BUBBLE_EXPANDED_SIZE, GameConstants.BUBBLE_EXPANDED_SIZE);
		
		
		
	}
	
	public void resizeIcon(BufferedImage originalImage) {
		Image resizedImage = originalImage.getScaledInstance(this.getWidth(), this.getHeight(),Image.SCALE_FAST);
        resizedIcon = new ImageIcon(resizedImage);
    }
	
	private void loadDefaultSprite() {
		try {
			
			this.defaultSprite =  ImageIO.read(getClass().getResource(path + "default.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void loadSprites() {
		try {
			
			this.defaultBubble =  ImageIO.read(getClass().getResource(path + "default.png"));
			explodedBubbles = new BufferedImage[2];
			for (int i=0; i<2; i++) {
				explodedBubbles[i]=ImageIO.read(getClass().getResource(path+"exploded"+(i+1)+".png"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public BubbleBullet getBubbleBullet() {
		return bubbleBullet;
	}

}
