package view;

import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class TileView extends JLabel {
	
	private String path;
	private BufferedImage sprite;
	private ImageIcon resizedIcon;
	private int x, y;
	
	public TileView(int tile, int x, int y) {
		path = "/sprites/Tiles/LevelTiles-" + tile + ".png";
		this.x = x;
		this.y = y;
	}
	
	public void resizeIcon(BufferedImage originalImage) {
		Image resizedImage = originalImage.getScaledInstance(this.getWidth(), this.getHeight(),Image.SCALE_FAST);
        resizedIcon = new ImageIcon(resizedImage);
    }

}
