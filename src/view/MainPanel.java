package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Navigator;
import model.Panels;

@SuppressWarnings("deprecation")

public class MainPanel extends JPanel {
	private static MainPanel instance;

	public static MainPanel getInstance() {
		if (instance==null) instance = new MainPanel();
		return instance;
	}
	
	private MainPanel() {
		setBackground(Color.BLACK);
		
		add(new JPanel(new BorderLayout()) {
			{
				add(new JPanel() {
					{
						try {
							BufferedImage image = ImageIO.read(getClass().getResource("/sprites/Title/NES-BubbleBobble-Title-JBubbleBobble-0.png"));
							ImageIcon newImage = new ImageIcon(image.getScaledInstance(image.getWidth()*3, image.getHeight()*3,0));
							JLabel displayField = new JLabel(newImage);
							add(displayField);
						} catch (IOException e) {
							System.out.println("Image cannot be found");
						}
						setBorder(BorderFactory.createEmptyBorder(50,0,0,0));
						setBackground(Color.BLACK);
			        }
				}, BorderLayout.NORTH);
				
				add(new JPanel() {
					{
						setBackground(Color.BLACK);
						add(new JPanel() {
							{
							JButton start = new JButton("Start"); 
							start.setBackground(Color.BLACK);
							start.setForeground(Color.WHITE);
							start.setBorderPainted(false);
							start.setFocusPainted(false);
							start.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
//									Navigator.getInstance().navigate(Panels.MENU);
								}	
							});
							add(start);
							
							setBorder(BorderFactory.createEmptyBorder(150,300,300,300));
							setBackground(Color.BLACK); 
							}
						},BorderLayout.CENTER);
					}
				});
			}	
		});
		
	}
}
