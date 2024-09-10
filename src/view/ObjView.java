package view;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import controller.GameController;
import controller.ObjAnimationController;
import model.GameConstants;
import model.ObjModel;
import model.Water;

@SuppressWarnings("deprecation")

public class ObjView extends JLabel implements Observer {
	
	private boolean isDeleted;
	private ObjModel obj;
	private BufferedImage actualSprite;
	private BufferedImage[] idleSprites;
	private BufferedImage[] collisionSprites;
	private ImageIcon resizedIcon;
	private String path;
	
	public ObjView(ObjModel obj, String path , int numSprites) {
		this.obj=obj;
		this.path = path;
		setBounds(obj.getX(), obj.getY(), GameConstants.ITEM_SIZE, GameConstants.ITEM_SIZE);
		loadSprites(numSprites);
		loadActualSprite();
		
		resizeIcon(actualSprite);
        setIcon(resizedIcon);
        
		setVisible(true);
	}
	
	private void loadSprites(int numSprites) {
		idleSprites = new BufferedImage[numSprites];
		collisionSprites = new BufferedImage[numSprites];
		try {
			for (int i=0; i<numSprites; i++) {
				idleSprites[i] = ImageIO.read(getClass().getResource(path + (i+1) + ".png"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		inizializeAnimationController();
	}
	
	private void loadActualSprite() {
		actualSprite = idleSprites[0];
	}
	
	private void resizeIcon(BufferedImage actualSprite) {
		Image resizedImage = actualSprite.getScaledInstance(this.getWidth(), this.getHeight(),Image.SCALE_FAST);
        resizedIcon = new ImageIcon(resizedImage);	
    }
	
	private void inizializeAnimationController() {
		ObjAnimationController objAnimationController = new ObjAnimationController.Builder()
				.setObj(obj)
				.setActualSprite(actualSprite)
				.setIdleSprites(idleSprites)
				.build();
		GameController.getInstance().addObjAnimationController(objAnimationController);
	}
	
	@Override
	public void update(Observable o,Object arg) {
		if(o instanceof Water) {
			Water water = (Water) o;
			setBounds(water.getX(), water.getY(), GameConstants.WATER_SIZE, GameConstants.WATER_SIZE);
			if (arg instanceof BufferedImage) {
				actualSprite = (BufferedImage) arg;
				resizeIcon(actualSprite);
				setIcon(resizedIcon);
			}
		}
		else if (o instanceof ObjModel) {
			ObjModel om = (ObjModel) o;
			setBounds(om.getX(), om.getY(), GameConstants.ITEM_SIZE, GameConstants.ITEM_SIZE);
			if (arg instanceof BufferedImage) {
				actualSprite = (BufferedImage) arg;
				resizeIcon(actualSprite);
				setIcon(resizedIcon);
			}
			if (om.canBeDeleted() && !isDeleted) {
				isDeleted = true;
				GameController.getInstance().removeObject(om, this);
			}
		}
		
		
	}
}
