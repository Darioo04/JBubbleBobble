package view;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

import model.Entity;

@SuppressWarnings("deprecation")
public class EntityView extends JLabel implements Observer {
	
	private Entity entity;
	private String path;
	private BufferedImage[] standingSprites;
	private BufferedImage[] deathSprites;
	private BufferedImage[] walkingSpritesUp;
	private BufferedImage[] walkingSpritesDown;
	private BufferedImage[] walkingSpritesLeft;
	private BufferedImage[] walkingSpritesRight;

	
	public EntityView(Entity entity) {
        this.entity = entity;
        this.path=entity.getPath();
        
        this.setBounds(entity.getX(),entity.getY(),20,20);
        initSprites();
        
        setVisible(true);
    }

	@Override
	public void update(Observable o, Object arg) {
		this.setBounds(entity.getX(),entity.getY(),20,20);
		//
	}
	
	
	public void initSprites() {
		standingSprites= new BufferedImage[2];
		deathSprites = new BufferedImage[4];
		walkingSpritesUp = new BufferedImage[4];
		walkingSpritesDown = new BufferedImage[4];
		walkingSpritesLeft = new BufferedImage[4];
		walkingSpritesRight = new BufferedImage[4];
		try {
			//carica le animazioni da fermo
			standingSprites[0] = ImageIO.read(getClass().getResourceAsStream(path+"standingToLeft.png"));
			standingSprites[1] = ImageIO.read(getClass().getResourceAsStream(path+"/standingToRight.png"));
			
			//carica le animazioni di movimento
			for (int i=0; i<entity.getWalkingSpritesUpNum(); i++) {
				walkingSpritesUp[i] = ImageIO.read(getClass().getResourceAsStream(path+"/walkingUp-"+i+".png"));
			}
			for (int i=0; i<entity.getWalkingSpritesDownNum(); i++) {
				walkingSpritesDown[i] = ImageIO.read(getClass().getResourceAsStream(path+"/walkingDown-"+i+".png"));
			}
			for (int i=0; i<entity.getWalkingSpritesLeftNum(); i++) {
				walkingSpritesLeft[i] = ImageIO.read(getClass().getResourceAsStream(path+"/walkingLeft-"+i+".png"));
			}
			for (int i=0; i<entity.getWalkingSpritesRightNum(); i++) {
				walkingSpritesRight[i] = ImageIO.read(getClass().getResourceAsStream(path+"/walkingRight-"+i+".png"));
			}
			
			//carica le animazioni di morte
			for (int i=0; i<entity.getDeathSpritesNum(); i++) {
				deathSprites[i] = ImageIO.read(getClass().getResourceAsStream(path+"death-"+i+".png"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
