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
	protected String path;   //percorso cartella con tutti gli sprites
	protected ImageIcon resizedIcon;
	protected BufferedImage defaultSprite;
	protected int entitySize;
	
	
	public EntityView(Entity entity, int entitySize) {
        this.entity = entity;
        this.path = entity.getPath();
        this.entitySize = entitySize;
        
        loadSprites();
        loadDefaultSprite();
        
        this.setBounds(entity.getX(), entity.getY(), entitySize, entitySize);
        
        this.resizeIcon(defaultSprite);
        this.setIcon(resizedIcon);
        
        setVisible(true);
    }

	@Override
	public void update(Observable o, Object arg) {
		this.setBounds(entity.getX(), entity.getY(), entitySize, entitySize);
		
	}
	
	public void resizeIcon(BufferedImage originalImage) {
		Image resizedImage = originalImage.getScaledInstance(this.getWidth(), this.getHeight(),Image.SCALE_FAST);
        resizedIcon = new ImageIcon(resizedImage);
    }
	
	protected abstract void loadSprites();
	
	protected abstract void loadDefaultSprite();


}
