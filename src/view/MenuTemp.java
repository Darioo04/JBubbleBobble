//package view;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//import javax.swing.*;
//public class MenuTemp extends JFrame{
//	private CardLayout cardLayout;
//	private JFrame frame;
//	public MenuTemp() {
//		super("JBubbleBobble");
//		setSize(900,600);
//		setBackground(Color.BLACK);
//		cardLayout = new CardLayout();
//		this.add(new JPanel(new BorderLayout() {
//			{
//				
//				add(new JPanel() {
//					{
//						setBackground(Color.BLACK);
//						try {
//							ImageIcon image = new ImageIcon("JBubbleBobble/res/sprites/Title/NES - Bubble Bobble - Title - JBubbleBobble-1.png");
//							JLabel displayField = new JLabel(image);
//							add(displayField,BorderLayout.SOUTH);
//						} catch (Exception e) {
//							System.out.println("Image not found");
//						}
//						
//					}
//				}, BorderLayout.NORTH);
//				add(new JPanel() {
//					{
//						setBackground(Color.BLACK);
//						add(new JPanel() {
//							{
//								var gioca= new JButton("Gioca");
//								gioca.addActionListener(new ActionListener() {
//										@Override
//										public void actionPerformed(ActionEvent e) {
////											cardLayout.show(new GamePanel(),"Gioca");
//										}
//									
//								});
//								add(gioca);
//							}
//						});
//						
//					}
//				}, BorderLayout.SOUTH);
//			}
//		}));
//
//		this.setBackground(Color.BLACK);
//		this.setLocationRelativeTo(null);
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		this.setVisible(true);
//	
//}
//	public static void main(String[] args) {
//		 new MenuTemp();
//	}
//}
//	
