package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("deprecation")

public class StatusBar extends JPanel implements Observer {
	private static StatusBar instance;
	private String hp;
	private String score;
	
	public static StatusBar getInstance() {
		if (instance==null) instance = new StatusBar();
		return instance;
	}
	
	private StatusBar() {
		add(new JPanel(new GridLayout(1,2,0,0)) { //num righe,num colonne, distanza tra le componenti
			{
				
		        JPanel panel1 = new JPanel(new GridBagLayout());
		        panel1.setBackground(Color.BLACK);
		        GridBagConstraints gbc1 = new GridBagConstraints();
		        gbc1.fill = GridBagConstraints.BOTH;
		        gbc1.weightx = 1;
		        gbc1.weighty = 1;
		        
		        gbc1.gridy = 0;
		        panel1.add(new FontView("HP"), gbc1);
		        
		        gbc1.gridy = 1;
//		        panel1.add(new FontView(hp), gbc1);
		        
		        add(panel1);

		        
		        JPanel panel2 = new JPanel(new GridBagLayout());
		        panel2.setBackground(Color.BLACK);
		        GridBagConstraints gbc2 = new GridBagConstraints();
		        gbc2.fill = GridBagConstraints.BOTH;
		        gbc2.weightx = 1;
		        gbc2.weighty = 1;
		        
		        gbc2.gridy = 0;
		        panel2.add(new FontView("High Score"), gbc2);
		        
		        gbc2.gridy = 1;
//		        panel2.add(new FontView(score), gbc2);
		        
		        add(panel2);
//				setBackground(Color.BLACK);
//				add(new JPanel(new GridLayout(2,1,0,0)) {
//					{
//						setBackground(Color.BLACK);
//						add(new FontView("HP"));
//						add(new JLabel());
//					}
//				});
//				add(new JPanel(new GridLayout(2,1,0,0)) {
//					{
//						setBackground(Color.BLACK);
//						add(new FontView("Score"));
//						add(new JLabel());
//					}
//				});
			}
		});
	}
	
	public void update(Observable o,Object arg) {
		
	}
}
