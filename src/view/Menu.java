//package view;
//
//import java.awt.CardLayout;
//import java.awt.Color;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//import javax.swing.*;
//
//public class Menu extends JFrame {
//	private JPanel panelsContainer = new JPanel();
//	private JPanel menuPanel = new JPanel();
//	private Settings settingsPanel = new Settings();
//	private GamePanel gamePanel = new GamePanel();
//	private JButton playButton = new JButton("play");
//	private JButton settingsButton = new JButton("settings");
////	private JButton backButton = new JButton("back to menu");
//	private CardLayout cl = new CardLayout();
//	
//	public Menu() {
//		super("JBubble Bobble");
//		
//		panelsContainer.setLayout(cl);
//		menuPanel.add(playButton);
//		menuPanel.add(settingsButton);
////		settingsPanel.add(backButton);
//		
//		panelsContainer.add(menuPanel, "menu");
//		panelsContainer.add(gamePanel, "game");
//		panelsContainer.add(settingsPanel, "settings");
//		cl.show(panelsContainer, "menu");
//		
//		playButton.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				cl.show(panelsContainer, "game");
//			}
//		});
//		
//		settingsButton.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				cl.show(panelsContainer, "settings");
//			}
//		});
//		
//		settingsPanel.getBackButton().addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				cl.show(panelsContainer, "menu");
//			}
//		});
//		
//		this.add(panelsContainer);
//		this.setLocationRelativeTo(null);
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		this.setSize(900,600);
//		this.setBackground(Color.BLACK);
//		
//	}
//}