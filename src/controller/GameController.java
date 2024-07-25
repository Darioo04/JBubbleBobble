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
import model.StateScreen;
import view.MainFrame;
import view.MenuScreenPanel;
import view.PlayerView;

import javax.swing.Timer;

public class GameController {
	
    private KeyController playerController;
    private ScreenController screenController;
    private Player player;
    private PlayerView playerView;
    private MenuScreen menuScreen;
    private MenuScreenPanel menuScreenPanel;
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
        menuScreen = MenuScreen.getInstance();
        menuScreenPanel = (MenuScreenPanel) menuScreen.getStateScreenPanel();
        screenController = ScreenController.getInstance();
        mainFrame.add(menuScreenPanel);
        menuScreenPanel.addKeyListener(screenController);
        mainFrame.setFocusable(true);
        mainFrame.addKeyListener(screenController);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
        
    	this.timer = new Timer(16, new ActionListener() {		//16ms per avere 60FPS
			
			@Override
			public void actionPerformed(ActionEvent e) {
				update();
				
			}
		});
        this.player = Player.getInstance();
        this.playerController = new KeyController(player);
        
        this.screenController = ScreenController.getInstance();
//        this.playerView = new PlayerView(player);
    }
    
    public void update() {
    	if(gameState == GameState.MENU) {
    		menuScreen.update();
    	}
    }
    
    public void startGame() {
        timer.start(); 			// Inizia il game loop
    }

    public void stopGame() {
        timer.stop(); 			// Ferma il game loop
    }

    public KeyListener getPlayerController() {
        return playerController;
    }
    
    public Player getPlayer() {
        return player;
    }

}
