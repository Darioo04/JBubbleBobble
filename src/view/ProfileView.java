package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Observable;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.GameConstants;
import model.GameModel;

public class ProfileView extends StateScreenView {
	private static ProfileView instance;
	private GameModel gameModel = GameModel.getInstance();
	private int gamesPlayed;
	private int gamesWon;
	private int gamesLost;

	
	public static ProfileView getInstance() {
		if (instance==null) instance = new ProfileView();
		return instance;
	}
	
	private ProfileView() {
		this.setVisible(true);
		this.setLayout(null);
		this.setBackground(Color.BLACK);
		this.setPreferredSize(new Dimension(GameConstants.SCREEN_WIDTH, GameConstants.SCREEN_HEIGHT));
		add(new JPanel(new GridLayout(3,1)) {
			{
				add(new JLabel() {
					{
						add(new FontView("Games Played: "+gamesPlayed));
					}
				});
				add(new JLabel() {
					{
						add(new FontView("Games Won: "+gamesWon));
					}
				});
				add(new JLabel() {
					{
						add(new FontView("Games Lost: "+gamesLost));
					}
				});
			}
		});
	}
	
	@Override
	public void update(Observable o,Object arg) {
		super.update(o, arg);
		this.gamesPlayed=gameModel.getGamesPlayed();
		this.gamesWon=gameModel.getGamesWon();
		this.gamesLost=gameModel.getGamesLost();
	}
}	
