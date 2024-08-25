package view;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import model.Bubble;
import model.BubbleBullet;
import model.GameConstants;

@SuppressWarnings("deprecation")

public class BubbleView extends JLabel implements Observer {

	private ImageIcon resizedIcon;
	private BufferedImage defaultSprite;
	private BufferedImage[] explodedSprites;
	private BufferedImage[] extendsBubbles;
	private Bubble bubble;
	private static final String path = "/sprites/Bubbles/bubble-";
	
	private BufferedImage defaultBubble;
	
	public BubbleView(Bubble bubble) {
		this.bubble = bubble;
		
		loadDefaultSprite();
		loadSprites();
		
		this.setBounds(bubble.getX(), bubble.getY(), GameConstants.BUBBLE_SHOT_SIZE, GameConstants.BUBBLE_SHOT_SIZE);
		resizeIcon(defaultSprite);
        setIcon(resizedIcon);
        setVisible(true);
	}
	
	@Override
	public void update(Observable o, Object arg) {
//		if (o instanceof Bubble && arg instanceof BufferedImage) {
//			defaultSprite = (BufferedImage) arg;
//		}
		if(bubble.isExpanded()) {
			this.resizeIcon(defaultSprite);
	        this.setIcon(resizedIcon);
			this.setBounds(bubble.getX(), bubble.getY(), GameConstants.BUBBLE_EXPANDED_SIZE, GameConstants.BUBBLE_EXPANDED_SIZE);
		}else {
			this.setBounds(bubble.getX(), bubble.getY(), GameConstants.BUBBLE_SHOT_SIZE, GameConstants.BUBBLE_SHOT_SIZE);
		}
		
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
			explodedSprites = new BufferedImage[2];
			for (int i=0; i<2; i++) {
				explodedSprites[i]=ImageIO.read(getClass().getResource(path+"exploded"+(i+1)+".png"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public Bubble getBubbleBullet() {
		return bubble;
	}

}
