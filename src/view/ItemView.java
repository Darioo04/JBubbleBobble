package view;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import model.GameConstants;
import model.Item;

@SuppressWarnings("deprecation")

public class ItemView extends JLabel implements Observer {
	
	private BufferedImage sprite;
	private ImageIcon resizedIcon;
	private Item item;
	private static final String path = "/sprites/Obj/Items-";

	public ItemView(Item item) {
		this.item=item;
		loadSprite();
		setBounds(item.getX(),item.getY(),GameConstants.ITEM_SIZE,GameConstants.ITEM_SIZE);
		resizeIcon(sprite);
	    setIcon(resizedIcon); 
	    setVisible(true);
		
	}
	
	public void resizeIcon(BufferedImage originalImage) {
		Image resizedImage = originalImage.getScaledInstance(this.getWidth(), this.getHeight(),Image.SCALE_FAST);
        resizedIcon = new ImageIcon(resizedImage);
    }
	
	private void loadSprite() {
		try {
			this.sprite =  ImageIO.read(getClass().getResource(path + item.getName() +".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public BufferedImage getSprite() {
		return sprite;
	}
	
	@Override
	public void update(Observable o,Object arg) {
		setBounds(item.getX(),item.getY(),GameConstants.ITEM_SIZE,GameConstants.ITEM_SIZE);
	}
}
