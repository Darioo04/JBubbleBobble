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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import model.GameConstants;

public class FullScreenFrame extends JFrame{
	private static FullScreenFrame instance;
	private JPanel cardpanel;
	
	public static FullScreenFrame getInstance() {
		if (instance==null) instance = new FullScreenFrame();
		return instance;
	}
	
	private FullScreenFrame() {
		super("JBubble Bobble");
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
        add(new JPanel(new BorderLayout()) {
			{
				setBackground(Color.BLACK);
				add(new JPanel() {
					{
						try {
							BufferedImage image = ImageIO.read(getClass().getResource("/sprites/Title/NES-BubbleBobble-Title-JBubbleBobble-0.png"));
							ImageIcon newImage = new ImageIcon(image.getScaledInstance(image.getWidth()*2, image.getHeight()*2,0));
							JLabel displayField = new JLabel(newImage);
							add(displayField);
						} catch (IOException e) {
							System.out.println("Image cannot be found");
						}
						setBorder(BorderFactory.createEmptyBorder(50,0,0,0));
						setBackground(Color.BLACK);
			        }
				}, BorderLayout.NORTH);
				add(new JPanel(new CardLayout()) {
					{
						setBackground(Color.BLACK);
						add(new JPanel(new CardLayout()) {
							{
							JButton gioca = new JButton("Gioca");
							gioca.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									
								}
							});
							gioca.setBackground(Color.BLACK);
							gioca.setForeground(Color.WHITE);
							gioca.setBorderPainted(false);
							gioca.setFocusPainted(false);
							add(gioca);
							setBorder(BorderFactory.createEmptyBorder(350,350,300,350));
							setBackground(Color.BLACK);
							}
						});
						add(new JLabel("Realizzato da  Meridiani Angelo  Ojog Dario  Scafetta Giovanni") {
							{
								setBackground(Color.BLACK);
								setForeground(Color.DARK_GRAY);
								setOpaque(true);
							}
						}, BorderLayout.SOUTH);
					}
				}, BorderLayout.SOUTH);
			}
        });
        setBackground(Color.BLACK);
        setVisible(true);
    }
}

        
 
