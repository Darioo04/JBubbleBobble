package view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import controller.GameController;

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
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setResizable(false);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                GameController.getInstance().saveGameData();
                System.exit(0);
            }
        });
    }
	 
}