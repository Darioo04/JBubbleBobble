package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
//        this.add(new JPanel(new BorderLayout()) {
//			{
//				add(new JPanel() {
//					{
//						String filePath = "\\JBubbleBobble\\res\\sprites\\NES - Bubble Bobble - Title - JBubbleBobble.gif";
//						ImageIcon image = new ImageIcon(filePath);
//						JLabel displayField = new JLabel(image);
//						add(displayField,BorderLayout.CENTER);
//						setBackground(Color.BLACK);
//			        }
//					
//				}, BorderLayout.CENTER);
//			}
//		});
        this.setBackground(Color.BLACK);
        setVisible(true);
    }
}
 
