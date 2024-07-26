package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.security.KeyStore.PrivateKeyEntry;
import java.util.ArrayList;

import model.Enemy;
import model.GameState;
import model.MenuScreen;
import model.Player;
import model.SelectLevelScreen;
import model.StateScreen;
import view.MainFrame;
import view.MenuScreenView;
import view.PlayerView;
import view.SelectLevelView;
import view.StateScreenView;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GameController {
	
    private KeyController keyController;
    private Player player;
    private PlayerView playerView;
    private MenuScreen menuScreen;
    private MenuScreenView menuScreenView;
    private SelectLevelScreen selectLevelScreen;
    private SelectLevelView selectLevelView;
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
    	if(gameState == GameState.MENU) {
    		menuScreen.update();
    	}
    	else if (gameState == GameState.SELECT_LEVEL) {
    		selectLevelScreen.update();
    	}
    }
    
    public void startGame() {
        timer.start(); 			// Inizia il game loop
    }

    public void stopGame() {
        timer.stop(); 			// Ferma il game loop
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
    
    public void setDisplayedScreen(JPanel newScreen) {
    	mainFrame.setContentPane(newScreen);
        mainFrame.revalidate();
        mainFrame.repaint();
    }
    
    public void changeDisplayedScreen(JPanel oldScreen, JPanel newScreen) {
    	mainFrame.remove(oldScreen);
    	mainFrame.getContentPane().removeAll();
        mainFrame.add(newScreen);
        newScreen.setFocusable(true);
        newScreen.requestFocusInWindow();
        
        StateScreenView stateScreenView = (StateScreenView) newScreen;
        if (!stateScreenView.isThereKeyController()) {
        	newScreen.addKeyListener(keyController);
            stateScreenView.setThereKeyController(true);
        }
        
        setDisplayedScreen(newScreen);
    }
}
