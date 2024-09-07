package view;

import java.awt.Font;
import java.util.Observable;

import javax.swing.JLabel;

import model.GameConstants;
import model.StateScreen;

public class LastLevelWinScreenView extends StateScreenView {
	private static LastLevelWinScreenView instance;
	private long score;
	private JLabel scoreLabel;
	private Font font;
	
	public static LastLevelWinScreenView getInstance() {
		if (instance==null) instance = new LastLevelWinScreenView();
		return instance;
	}
	
	private LastLevelWinScreenView() {
		scoreLabel = new JLabel();
		font = FontCreator.getInstance().getFont();
		font = font.deriveFont(44f);
		this.setLayout(null);
		scoreLabel.setBounds(GameConstants.SCREEN_WIDTH/2, GameConstants.SCREEN_HEIGHT/2 - GameConstants.TILE_SIZE - GameConstants.SCALE, 5*GameConstants.TILE_SIZE, 2*GameConstants.TILE_SIZE);
		scoreLabel.setText(score + "");
		scoreLabel.setHorizontalAlignment(JLabel.CENTER);
		scoreLabel.setFont(font);
		add(scoreLabel);
		setVisible(true);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof StateScreen) {
			StateScreen stateScreen = (StateScreen) o;
			this.setImage(stateScreen.getScreens()[stateScreen.getPointer()]);
	        this.repaint();
		}
		scoreLabel.setText(score + "");
	}
	
	public void setScore(long score) {
		this.score = score;
	}
}
