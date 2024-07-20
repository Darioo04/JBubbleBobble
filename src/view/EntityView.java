package view;

import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;

import model.Entity;

@SuppressWarnings("deprecation")
public class EntityView extends JLabel implements Observer {
	
	private Entity entity;
	private String path;
	private BufferedImage[] deathSprites;
	private BufferedImage[] walkingSpritesUp;
	private BufferedImage[] walkingSpritesDown;
	private BufferedImage[] walkingSpritesLeft;
	private BufferedImage[] walkingSpritesRight;

	
	public EntityView(Entity entity) {
        this.entity = entity;
        this.path=entity.getPath();
    }

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
