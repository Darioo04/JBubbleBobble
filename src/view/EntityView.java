package view;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import model.Entity;

@SuppressWarnings("deprecation")

public abstract class EntityView extends JLabel implements Observer {
	
	protected Entity entity;
	protected String path;   //percorso cartella con tutti gli sprites
	protected ImageIcon resizedIcon;
	protected BufferedImage defaultSprite;
	protected int entitySize;
	
	
	public EntityView(Entity entity, int entitySize, int numIdleSprites, int numRunningSprites, int numJumpingSprites, int numFallingSprites) {
        this.entity = entity;
        this.path = entity.getPath();
        this.entitySize = entitySize;
        
        loadSprites(numIdleSprites,numRunningSprites,numJumpingSprites,numFallingSprites);
        loadDefaultSprite();
        
        this.setBounds(entity.getX(), entity.getY(), entitySize, entitySize);
        
        this.resizeIcon(defaultSprite);
        this.setIcon(resizedIcon);
        
        setVisible(true);
    }

	@Override
	public void update(Observable o, Object arg) {
		Entity e = (Entity) o;
		this.setBounds(e.getX(), e.getY(), entitySize, entitySize);
		
	}
	
	public void resizeIcon(BufferedImage originalImage) {
		Image resizedImage = originalImage.getScaledInstance(this.getWidth(), this.getHeight(),Image.SCALE_FAST);
        resizedIcon = new ImageIcon(resizedImage);
    }
	
	protected abstract void loadSprites(int numIdleSprites, int numRunningSprites, int numJumpingSprites, int numFallingSprites);
	
	protected abstract void loadDefaultSprite();

}
