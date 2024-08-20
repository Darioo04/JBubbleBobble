package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import model.GameConstants;

public class NicknamePanel extends StateScreenView {
	private static NicknamePanel instance;
	private String nickname;
	
	public static NicknamePanel getInstance() {
		if (instance==null) instance = new NicknamePanel();
		return instance;
	}
	
	private NicknamePanel() {
		setVisible(true);
		Dimension dimension = new Dimension(GameConstants.SCREEN_WIDTH,GameConstants.SCREEN_HEIGHT);
		setPreferredSize(dimension);
		setMinimumSize(dimension);
		setMaximumSize(dimension);
		Font font = FontCreator.getInstance().getFont();
		UIManager.put("Label.font", font);
		add(new JPanel(new GridLayout(2,1,0,0)) {
			{
				setBackground(Color.BLACK);
				JLabel label = new JLabel("choose your nickname!!!");
				label.setHorizontalAlignment(SwingConstants.CENTER);
				add(label);
				
				add(new JTextField() {
					{
						setCaretPosition(0);
						setBackground(Color.BLACK);
						setForeground(Color.WHITE);
						addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								nickname = getText();
							}
						});	
					}
				});
			}
		});	
	}
	
	public String getNickname() {
		return nickname;
	}
}
