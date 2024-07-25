package view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.GameConstants;
import model.Navigator;
import model.Panels;

@SuppressWarnings("deprecation")

public class MainFrame extends JFrame {
	private static MainFrame instance;
	private JPanel cardPanel;
	
	public static MainFrame getInstance() {
		if (instance==null) instance = new MainFrame();
		return instance;
	}
	
	private MainFrame() {
//		super("JBubble Bobble");
//		try { 
//			BufferedImage image = ImageIO.read(getClass().getResource("/sprites/Title/NES-BubbleBobble-Title-JBubbleBobble-0.png"));
//			setIconImage(image); 
//		}
//		catch ( IOException e ) { System.out.println("Image cannot be found"); }
//        // scaled dimensions
//        int scaledWidth = (int) (GameConstants.ORIGINAL_WIDTH * GameConstants.SCALE);
//        int scaledHeight = (int) (GameConstants.ORIGINAL_HEIGHT * GameConstants.SCALE);
//        setSize(scaledWidth, scaledHeight);
//		setLocationRelativeTo(null);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        
//        Navigator.getInstance().addObserver(this);
//        
//		add(cardPanel = new JPanel(new CardLayout()) {
//			{
//				add(MainPanel.getInstance(), Panels.MAIN.name());
//				add(MenuPanel.getInstance(), Panels.MENU.name());
//				add(new GamePanel(), Panels.GAME.name());
//			}
//		});
//        
//		
//		
//        setBackground(Color.BLACK);
//        setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
    }
	
//	 @Override
//	 public void update(Observable o, Object arg) {
//		 if (o instanceof Navigator && arg instanceof Panels) {
//			 ((CardLayout) cardPanel.getLayout()).show(cardPanel, ((Panels) arg).name());
//		 }
//	 }
	 
}

        
 
