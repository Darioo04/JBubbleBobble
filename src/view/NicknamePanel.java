package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.FontCreator;

public class NicknamePanel extends StateScreenView {
	private static NicknamePanel instance;
	private String nickname;
	
	public static NicknamePanel getInstance() {
		if (instance==null) instance = new NicknamePanel();
		return instance;
	}
	
	private NicknamePanel() {
		setVisible(true);
		Dimension dimension = new Dimension(400,300);
		setPreferredSize(dimension);
		setMinimumSize(dimension);
		setMaximumSize(dimension);
		Font font = FontCreator.getInstance().getFont();
		add(new JPanel(new GridLayout(2,1,0,0)) {
			{
				add(new JPanel(new BorderLayout()) {
					{
						setBackground(Color.BLACK);
						
						JLabel label = new JLabel("choose your nickname!!!");
						label.setFont(font);
						add(label);
						
					}
				},BorderLayout.CENTER);
				add(new JTextField() {
					{
						setFont(font);
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
				},BorderLayout.SOUTH);
			}
		}, BorderLayout.CENTER);
		
		
	}
	
	public String getNickname() {
		return nickname;
	}
}
