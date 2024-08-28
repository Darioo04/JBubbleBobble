package view;

import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import model.GameConstants;
import model.ObjModel;

@SuppressWarnings("deprecation")

public class ObjView extends JLabel implements Observer {
	
	private BufferedImage defaultSprite;
	private ImageIcon resizedIcon;
	
	
	public ObjView() {
		
	}
	
	
	@Override
	public void update(Observable o,Object arg) {
		ObjModel om = (ObjModel) o;
		this.setBounds(om.getX(), om.getY(), GameConstants.ITEM_SIZE, GameConstants.ITEM_SIZE);
	}
}
