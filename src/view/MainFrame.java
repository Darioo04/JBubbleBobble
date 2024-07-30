package view;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("deprecation")

public class MainFrame extends JFrame {
	private static MainFrame instance;
	
	public static MainFrame getInstance() {
		if (instance==null) instance = new MainFrame();
		return instance;
	}
	
	private MainFrame() {
		super("JBubble Bobble");
		try { 
			BufferedImage image = ImageIO.read(getClass().getResource("/sprites/Title/NES-BubbleBobble-Title-JBubbleBobble-0.png"));
			setIconImage(image); 
		}
		catch ( IOException e ) { e.printStackTrace(); }
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
    }
	 
}