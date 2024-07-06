package view;

import java.awt.Color;
import javax.swing.JFrame;

import model.GameConstants;

public class FullScreenFrame extends JFrame{
	
	public FullScreenFrame() {
		super("JBubble Bobble");

        // scaled dimensions
        int scaledWidth = (int) (GameConstants.ORIGINAL_WIDTH * GameConstants.SCALE);
        int scaledHeight = (int) (GameConstants.ORIGINAL_HEIGHT * GameConstants.SCALE);
        
        this.setSize(scaledWidth, scaledHeight);
		this.setUndecorated(false);
		this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBackground(Color.BLACK);
        setVisible(true);
    }
    
	
	
}
