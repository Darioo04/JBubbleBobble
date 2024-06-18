package view;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class Menu extends JFrame{
	private CardLayout cardLayout;
	private JFrame frame;
	public Menu() {
		frame=new JFrame("JBubble Bobble");
		frame.setSize(900,600);
		frame.setBackground(Color.BLACK);
		cardLayout = new CardLayout();
		frame.add(new JPanel(new BorderLayout()) {
			{
				add(new JPanel() {
					{
						ImageIcon image = new ImageIcon("JBubbleBobble\\Sprites\\NES - Bubble Bobble - Title - JBubbleBobble.gif");
						JLabel displayField = new JLabel(image);
						frame.add(displayField,BorderLayout.NORTH);
						setBackground(Color.BLACK);
			        }
					
				});
				add(new JPanel() {
					{
						setBackground(Color.BLACK);
						add(new JPanel() {
							{
								var gioca= new JButton("Gioca");
								gioca.addActionListener(new ActionListener() {
										@Override
										public void actionPerformed(ActionEvent e) {
											cardLayout.show(new Play(),"Gioca");
										}
									
								});
								add(gioca);
							}
						}, BorderLayout.EAST);
						add (new JPanel() {
							{
								var profilo=new JButton("Impostazioni");
								add(profilo);
								profilo.addActionListener(new ActionListener() {
									@Override
									public void actionPerformed(ActionEvent e) {
										cardLayout.show(new Impostazioni(), "Gioca");
									}
								});	
								
							}
						}, BorderLayout.WEST);
						setBorder(BorderFactory.createEmptyBorder(10,10,70,10));
					}
				}, BorderLayout.SOUTH);
			}
		});


		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	
}
	public static void main(String[] args) {
		new Menu();
	}
}
	
