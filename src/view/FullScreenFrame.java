package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;

import model.GameConstants;

public class FullScreenFrame extends JFrame {
	private static FullScreenFrame instance;
	
	public static FullScreenFrame getInstance() {
		if (instance==null) instance = new FullScreenFrame();
		return instance;
	}
	
	enum Panels {
		MAIN,MENU,GAME
	}
	
	private FullScreenFrame() {
		super("JBubble Bobble");
		
		JPanel cardPanel;
		add(cardPanel = new JPanel(new CardLayout()) {
			{
				add(new MainPanel(), Panels.MAIN.name());
				add(new MenuPanel(), Panels.MENU.name());
				add(new GamePanel(), Panels.GAME.name());
			}
		});
		
		((CardLayout) cardPanel.getLayout()).show(cardPanel, Panels.MAIN.name());
		
		try { 
			BufferedImage image = ImageIO.read(getClass().getResource("/sprites/Title/NES-BubbleBobble-Title-JBubbleBobble-0.png"));
			setIconImage(image); 
		}
		catch ( IOException e ) { System.out.println("Image cannot be found"); }
        // scaled dimensions
        int scaledWidth = (int) (GameConstants.ORIGINAL_WIDTH * GameConstants.SCALE);
        int scaledHeight = (int) (GameConstants.ORIGINAL_HEIGHT * GameConstants.SCALE);
        setSize(scaledWidth, scaledHeight);
		setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        setBackground(Color.BLACK);
        setVisible(true);
    }
}

        
 
