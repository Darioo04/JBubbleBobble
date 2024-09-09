//package view;
//
//import java.awt.Image;
//import java.awt.Point;
//import java.awt.image.BufferedImage;
//import java.io.IOException;
//import java.util.Observable;
//import java.util.Observer;
//
//import javax.imageio.ImageIO;
//import javax.swing.ImageIcon;
//import javax.swing.JLabel;
//
//import controller.GameController;
//import model.GameConstants;
//
//@SuppressWarnings("deprecation")
//
//public class PointsView extends JLabel implements Observer {
//	
//	private boolean isDeleted;
//	private int point;
//	private static final String path = "/sprites/Points/";
//	private BufferedImage sprite;
//	private ImageIcon resizedIcon;
//	
//	public PointsView(int point) {
//		String fullPath = path + point;
//		loadSprite(fullPath);
//		resizeIcon(sprite);
//		setIcon(resizedIcon);
//	}
//	
//	private void loadSprite(String fullPath) {
//		try {
//			sprite = ImageIO.read(getClass().getResource(fullPath));
//		} catch(IOException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	private void resizeIcon(BufferedImage originalImage) {
//		Image resizedImage = originalImage.getScaledInstance(this.getWidth(), this.getHeight(),Image.SCALE_FAST);
//        resizedIcon = new ImageIcon(resizedImage);
//	}
//	
//	@Override
//	public void update(Observable o, Object arg) {
//		if (o instanceof Points) {
//			Points points = (Points) o;
//			setBounds(points.getX(), points.getY(), GameConstants.POINT_ICON_WHIDTH, GameConstants.POINT_ICON_HEIGHT);
//			if (point.canBeDeleted() && !isDeleted) {
//				isDeleted = true;
//				GameController.getInstance().removePointsView();
//			}
//		}
//	}
//}
