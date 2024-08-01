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
//	protected Image resizedImage;
	protected ImageIcon resizedIcon;
	protected BufferedImage defaultSprite;
	
	
	public EntityView(Entity entity) {
        this.entity = entity;
        this.path = entity.getPath();
        
        loadDefaultSprite();
        loadSprites();
        
        this.setBounds(entity.getX(), entity.getY(), GameConstants.TILE_SIZE, GameConstants.TILE_SIZE);
        
        this.resizeIcon(defaultSprite);
        this.setIcon(resizedIcon);
        
        setVisible(true);
    }

	@Override
	public void update(Observable o, Object arg) {
		this.setBounds(entity.getX(), entity.getY(), GameConstants.TILE_SIZE, GameConstants.TILE_SIZE);
		//da aggiungere aggiornamento dello sprite
		
	}
	
	public void resizeIcon(BufferedImage originalImage) {
		Image resizedImage = originalImage.getScaledInstance(this.getWidth(), this.getHeight(),Image.SCALE_FAST);
        resizedIcon = new ImageIcon(resizedImage);
    }
	
	protected abstract void loadSprites();
	
	protected abstract void loadDefaultSprite();



}
