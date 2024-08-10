package view;

import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

@SuppressWarnings("deprecation")

public class ObjView extends JLabel implements Observer {
	
	private BufferedImage defaultSprite;
	private ImageIcon resizedIcon;

	
	public ObjView() {
		
	}
	
	
	@Override
	public void update(Observable o,Object arg) {
		
	}
}
