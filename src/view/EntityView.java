package view;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import model.Entity;
import model.GameConstants;

@SuppressWarnings("deprecation")
public abstract class EntityView extends JLabel implements Observer {
	
	protected Entity entity;
	protected String path;
	private Image resizedImage;
	private ImageIcon resizedIcon;
	private BufferedImage sprite;
//	private BufferedImage[] standingSprites;
//	private BufferedImage[] deathSprites;
//	private BufferedImage[] walkingSpritesUp;
//	private BufferedImage[] walkingSpritesDown;
//	private BufferedImage[] walkingSpritesLeft;
//	private BufferedImage[] walkingSpritesRight;

	
	public EntityView(Entity entity) {
        this.entity = entity;
        this.path = entity.getPath();
        try {
			sprite = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        this.setBounds(entity.getX(), entity.getY(), (int)(GameConstants.TILE_SIZE * 0.8), (int)(GameConstants.TILE_SIZE * 0.8));
//        initSprites();
        
        this.resizeIcon(sprite);
        this.setIcon(resizedIcon);
        
        setVisible(true);
    }
	
//	public void setPath(String path) {
//		this.path = path;
//	}

	@Override
	public void update(Observable o, Object arg) {
		this.setBounds(entity.getX(), entity.getY(),(int)(GameConstants.TILE_SIZE * 0.8), (int)(GameConstants.TILE_SIZE * 0.8));
	}
	
	public void resizeIcon(BufferedImage originalImage) {
        resizedImage = originalImage.getScaledInstance(this.getWidth(), this.getHeight(),Image.SCALE_FAST);
        resizedIcon = new ImageIcon(resizedImage);
    }

	
	
//	public void initSprites() {
//		standingSprites= new BufferedImage[2];
//		deathSprites = new BufferedImage[4];
//		walkingSpritesUp = new BufferedImage[4];
//		walkingSpritesDown = new BufferedImage[4];
//		walkingSpritesLeft = new BufferedImage[4];
//		walkingSpritesRight = new BufferedImage[4];
//		try {
//			//carica le animazioni da fermo
//			standingSprites[0] = ImageIO.read(getClass().getResource(path+"standingToLeft.png"));
//			standingSprites[1] = ImageIO.read(getClass().getResource(path+"/standingToRight.png"));
//			
//			//carica le animazioni di movimento
//			for (int i=0; i<entity.getWalkingSpritesUpNum(); i++) {
//				walkingSpritesUp[i] = ImageIO.read(getClass().getResource(path+"/walkingUp-"+i+".png"));
//			}
//			for (int i=0; i<entity.getWalkingSpritesDownNum(); i++) {
//				walkingSpritesDown[i] = ImageIO.read(getClass().getResource(path+"/walkingDown-"+i+".png"));
//			}
//			for (int i=0; i<entity.getWalkingSpritesLeftNum(); i++) {
//				walkingSpritesLeft[i] = ImageIO.read(getClass().getResource(path+"/walkingLeft-"+i+".png"));
//			}
//			for (int i=0; i<entity.getWalkingSpritesRightNum(); i++) {
//				walkingSpritesRight[i] = ImageIO.read(getClass().getResource(path+"/walkingRight-"+i+".png"));
//			}
//			
//			//carica le animazioni di morte
//			for (int i=0; i<entity.getDeathSpritesNum(); i++) {
//				deathSprites[i] = ImageIO.read(getClass().getResource(path+"death-"+i+".png"));
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

}
