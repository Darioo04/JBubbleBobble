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
import javax.swing.border.BevelBorder;

import model.GameConstants;

public class FullScreenFrame extends JFrame{
	private JPanel cardpanel;
	public FullScreenFrame() {
		super("JBubble Bobble");
		try { setIconImage(ImageIO.read(new File("JBubbleBobble\\res\\sprites\\Title\\NES - Bubble Bobble - Title - JBubbleBobble-1.png"))); }
		catch (IOException e) { System.out.println("Image cannot be found"); }
        // scaled dimensions
        int scaledWidth = (int) (GameConstants.ORIGINAL_WIDTH * GameConstants.SCALE);
        int scaledHeight = (int) (GameConstants.ORIGINAL_HEIGHT * GameConstants.SCALE);
        setSize(scaledWidth, scaledHeight);
		setUndecorated(false);
		setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBackground(Color.BLACK);
        add(new JPanel(new BorderLayout()) {
			{
				setBackground(Color.BLACK);
				add(new JPanel() {
					{
						try {
							String filePath = "JBubbleBobble"+"/"+"res"+"/"+"sprites"+"/"+"NES - Bubble Bobble - Title - JBubbleBobble.gif";
							BufferedImage image = ImageIO.read(new File(filePath));
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
							add(gioca);
							setBorder(BorderFactory.createEmptyBorder(300,350,300,350));
							setBackground(Color.BLACK);
							}
						});
//						add(new JLabel("Realizzato da  Meridiani Angelo  Ojog Dario  Scafetta Giovanni") {
//							{
//								setBackground(Color.BLACK);
//								setOpaque(true);
//								UIManager.put("Label.font", new Font("ComicSans", Font.LAYOUT_LEFT_TO_RIGHT, 20));
//							}
//						}, BorderLayout.SOUTH);
					}
				}, BorderLayout.PAGE_END);
			}
        });
        setBackground(Color.BLACK);
        setVisible(true);
    }
}

        
 
