package view;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import controller.BubbleAnimationController;
import controller.GameController;
import model.Bubble;
import model.GameConstants;

@SuppressWarnings("deprecation")

public class BubbleView extends JLabel implements Observer {

	private static final String path = "/sprites/Bubbles/bubble-";
	private String fullPath;
	private ImageIcon resizedIcon;
	private BufferedImage defaultSprite;
	private BufferedImage[] floatingSprites;
	private static BufferedImage[] explodedSprites;
	private Bubble bubble;
	
	public BubbleView(Bubble bubble, String path, int numFloatingSprites) {
		this.bubble = bubble;
		fullPath = BubbleView.path + path;
		
		loadSprites(numFloatingSprites);
		loadDefaultSprite();

		setBounds(bubble.getX(), bubble.getY(), GameConstants.BUBBLE_SHOT_SIZE, GameConstants.BUBBLE_SHOT_SIZE);
		resizeIcon(defaultSprite);
		setIcon(resizedIcon);
        setVisible(true);
	}
	
//	public static BufferedImage getSprite(String path) {
//		
//	}
	

	
	public void resizeIcon(BufferedImage originalImage) {
		Image resizedImage = originalImage.getScaledInstance(this.getWidth(), this.getHeight(),Image.SCALE_FAST);
        resizedIcon = new ImageIcon(resizedImage);
    }
	
//	private void updateIcon(int size) {
//        if (!resizedIconsCache.containsKey(size)) {
//            Image resizedImage = defaultSprite.getScaledInstance(size, size, Image.SCALE_FAST);
//            resizedIconsCache.put(size, new ImageIcon(resizedImage));
//        }
//        setIcon(resizedIconsCache.get(size));
//    }
	
	private void loadDefaultSprite() {
		defaultSprite =  floatingSprites[0];
	}
	
	private void loadSprites(int numFloatingSprites) {
		floatingSprites = new BufferedImage[numFloatingSprites];
		try {
			for (int i=0; i<numFloatingSprites; i++) {
				floatingSprites[i] = ImageIO.read(getClass().getResource(fullPath + (i+1) + ".png"));
			}
			if (explodedSprites==null) {
				explodedSprites = new BufferedImage[4];
				for (int i=0; i<4; i++) {
				explodedSprites[i] = ImageIO.read(getClass().getResource(path+"exploded"+(i+1)+".png"));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		inizializeAnimationController();
	}

	
	private void inizializeAnimationController() {
		BubbleAnimationController bubbleAnimationController = new BubbleAnimationController.Builder()
				.setBubble(bubble)
				.setActualSprite(defaultSprite)
				.setExplodedSprites(explodedSprites)
				.setFloatingSprites(floatingSprites)
				.build();
		GameController.getInstance().addBubbleAnimationController(bubbleAnimationController);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof Bubble) {
			Bubble b = (Bubble) o;
			int size = b.isExpanded() ? GameConstants.BUBBLE_EXPANDED_SIZE : GameConstants.BUBBLE_SHOT_SIZE;
			setBounds(b.getX(), b.getY(), size, size);
			if (arg instanceof BufferedImage) {
				defaultSprite = (BufferedImage) arg;
				resizeIcon(defaultSprite);
				setIcon(resizedIcon);
			}
		}
	}
}

