package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import model.Enemy;
import model.GameState;
import model.MenuScreen;
import model.Player;
import model.SelectLevelScreen;
import model.StateScreen;
import view.GamePanel;
import view.LevelPanel;
import view.MainFrame;
import view.MenuScreenView;
import view.PlayerView;
import view.SelectLevelView;
import view.StateScreenView;

import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("deprecation")

public class GameController {
	
    private KeyController keyController;
    private Player player;
    private PlayerView playerView;
    private MenuScreen menuScreen;
    private MenuScreenView menuScreenView;
    private SelectLevelScreen selectLevelScreen;
    private SelectLevelView selectLevelView;
    private GamePanel gamePanel;
    private final int FPS = 60;
    private Timer timer;
    private ArrayList<Enemy> enemies;
    private AudioManager audioManager;
    private GameState gameState;
    private MainFrame mainFrame;
    
    
    
    private static GameController instance;
    
    public static GameController getInstance() {
    	if (instance==null) instance = new GameController();
    	return instance;
    }
    
    private GameController() {

        gameState = GameState.MENU;
        mainFrame = MainFrame.getInstance();
        selectLevelScreen = SelectLevelScreen.getInstance();
        selectLevelView = (SelectLevelView) selectLevelScreen.getStateScreenView();
        menuScreen = MenuScreen.getInstance();
        menuScreenView = (MenuScreenView) menuScreen.getStateScreenView();
        keyController = KeyController.getInstance();
        mainFrame.add(menuScreenView);
        menuScreenView.addKeyListener(keyController);
        menuScreenView.setIsThereKeyController(true);
        mainFrame.setFocusable(true);
        mainFrame.addKeyListener(keyController);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
        
    	this.timer = new Timer(16, new ActionListener() {		//16ms per avere 60FPS
			
			@Override
			public void actionPerformed(ActionEvent e) {
				update();
				
			}
		});
    }
    
    public void update() {
    	switch (gameState){
    	
			case MENU -> {
				menuScreen.update();
			}
			
			case SELECT_LEVEL -> {
				selectLevelScreen.update();
			}
			
			case GAME -> {
				player.update();
				gamePanel.repaint();
			}
		
			default -> {
				throw new IllegalArgumentException("Unexpected value: " + gameState);
			}	
		}
    }
    
    public void startGame() {
        timer.start(); // Inizia il game loop
//        AudioManager.getInstance().play("/Original Sound Track/03_Room Theme.wav");
    }

    public void stopGame() {
        timer.stop(); 			// Ferma il game loop
    }
    

	public void startLevel() {
    	player = Player.getInstance();
    	playerView = PlayerView.getInstance(player);
    	gamePanel = GamePanel.getInstance();
    	player.addObserver(playerView);
    	mainFrame.getContentPane().removeAll();
    	mainFrame.add(gamePanel);
    	
    	gamePanel.add(playerView);
    	gamePanel.setPlayer(player);
    	gamePanel.setFocusable(true);
    	gamePanel.grabFocus();
    	
    	mainFrame.revalidate();
        mainFrame.repaint();
    }
    
    public Player getPlayer() {
        return player;
    }
    
    public GameState getGameState() {
    	return gameState;
    }
    
    public void setGameState(GameState gameState) {
    	this.gameState = gameState;
    }
    
//    public void setDisplayedScreen(JPanel newScreen) {
//    	mainFrame.setContentPane(newScreen);
//        mainFrame.revalidate();
//        mainFrame.repaint();
//    }
    
    public void changeDisplayedScreen(JPanel oldScreen, JPanel newScreen) {
    	mainFrame.remove(oldScreen);
    	mainFrame.getContentPane().removeAll();
        mainFrame.add(newScreen);
        newScreen.setFocusable(true);
        newScreen.grabFocus();
        
        StateScreenView stateScreenView = (StateScreenView) newScreen;
        if (!stateScreenView.isThereKeyController()) {
        	newScreen.addKeyListener(keyController);
            stateScreenView.setIsThereKeyController(true);
        }
        
        mainFrame.revalidate();
        mainFrame.repaint();
//        setDisplayedScreen(newScreen);
    }
}
