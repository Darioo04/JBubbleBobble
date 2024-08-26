package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import model.GameConstants;
import model.Player;

@SuppressWarnings("deprecation")

public class StatusBar extends JPanel implements Observer {
	private static StatusBar instance;
	private Font font = FontCreator.getInstance().getFont();
	private JLabel hpLabel;
	private JLabel scoreLabel;
	private int hp;
	private long score;
	
	public static StatusBar getInstance() {
		if (instance==null) instance = new StatusBar();
		return instance;
	}
	
	private StatusBar() {
		setPreferredSize(new Dimension(GameConstants.SCREEN_WIDTH, GameConstants.TILE_SIZE/4*3));
		setVisible(true);
		setLayout(new GridLayout(1, 3));
		UIManager.put("Label.font", font);
		UIManager.put("Label.foreground", Color.WHITE);
		
		hpLabel = new JLabel();
		hpLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		scoreLabel = new JLabel();
		scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);

		add(hpLabel, BorderLayout.CENTER);
		add(scoreLabel, BorderLayout.CENTER);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.decode("#7700c8"));
		g2.fillRect(0, 0, getWidth(), getHeight());
		hpLabel.setText("hp " + hp);
		scoreLabel.setText("score " + score);
	}
	
	
	public void update(Observable o,Object arg) {
		if (o instanceof Player) {
			Player p = (Player) o;
			setHP(p.getHP());
			setScore(p.getScore());
			repaint();
		}
	}
	
	public void setHP(int hp) {
		this.hp = hp;
	}
	
	public void setScore(long score) {
		this.score = score;
	}
}
