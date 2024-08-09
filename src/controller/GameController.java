package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import model.Banebou;
import model.BubbleBullet;
import model.CollisionChecker;
import model.Enemy;
import model.EnemyFactory;
import model.GameConstants;
import model.GameModel;
import model.GameState;
import model.Hidegons;
import model.Invader;
import model.MenuScreen;
import model.PauseScreen;
import model.Player;
import model.PulPul;
import model.SelectLevelScreen;
import model.StateScreen;
import model.Tile;
import view.BubbleBulletView;
import view.EnemyView;
import view.GamePanel;
import view.MainFrame;
import view.MenuScreenView;
import view.PauseScreenView;
import view.PlayerView;
import view.ProfileView;
import view.SelectLevelView;
import view.StateScreenView;
import view.StatusBar;

import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("deprecation")

public class GameController {
	
    private KeyController keyController;
    private GameModel gameModel;
    private Player player;
    private PlayerView playerView;
    private MenuScreen menuScreen;
    private MenuScreenView menuScreenView;
    private SelectLevelScreen selectLevelScreen;
    private SelectLevelView selectLevelView;
    private ProfileView profileView;
    private PauseScreen pauseScreen;
    private PauseScreenView pauseScreenView;
    private GamePanel gamePanel;
    private CollisionChecker collisionChecker;
    public static int frames = 1;
    private Timer timer;
    private ArrayList<Enemy> enemies;
    private ArrayList<EnemyView> enemyViews;
    private ArrayList<BubbleBullet> bullets;
    private ArrayList<BubbleBulletView> bulletsViews;
    private GameState gameState;
    private MainFrame mainFrame;
    private LevelCreator levelCreator;
    private AudioManager audioManager;
    public static int level;
    private int animationCycle = 0;
    
    
    
    private static GameController instance;
    
    public static GameController getInstance() {
    	if (instance==null) instance = new GameController();
    	return instance;
    }
    
    private GameController() {

        gameState = GameState.MENU;
        gameModel = GameModel.getInstance();
        gameModel.addObserver(StatusBar.getInstance());
        
        levelCreator = LevelCreator.getInstance();
        mainFrame = MainFrame.getInstance();
        selectLevelScreen = SelectLevelScreen.getInstance();
        selectLevelView = (SelectLevelView) selectLevelScreen.getStateScreenView();
        profileView = ProfileView.getInstance();
        gameModel.addObserver(profileView);
        
        playerView = PlayerView.getInstance(); 
        
        menuScreen = MenuScreen.getInstance();
        menuScreenView = (MenuScreenView) menuScreen.getStateScreenView();
        pauseScreen = PauseScreen.getInstance();
        pauseScreenView = (PauseScreenView) pauseScreen.getStateScreenView();
        keyController = KeyController.getInstance();
        audioManager = AudioManager.getInstance();
        collisionChecker = CollisionChecker.getInstance();
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
				if (gameState == GameState.GAME) {
					if(frames == 60) frames = 1;
					frames++;
                    if(frames % 5 == 0) {
                        playerView.updateAnimation(animationCycle);
                        animationCycle++;
                    }
                    if (animationCycle == 3) animationCycle = 0;
				}
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
				enemies.stream().forEach(Enemy::update);
				bullets.stream().forEach(BubbleBullet::update);
				collisionChecker.checkPlayerEnemeyCollision(player, enemies);
				if (player.getLostLife()) {			//se perde una vita respawno il player
					player.spawnPlayer();
					player.setLostLife(false);
				}
			}
			
            case PAUSE -> {
            	pauseScreen.update();
            }
            
            case SELECT_PROFILE -> {
            	profileView.repaint();
            }
		
			default -> {
				throw new IllegalArgumentException("Unexpected value: " + gameState);
			}	
		}
    }
    
    public void startGame() {
        timer.start(); // Inizia il game loop
        audioManager.playBackgroundMusic("theme");
    }

    public void stopGame() {
        timer.stop(); 			// Ferma il game loop
    }
    

	public void startLevel() {
		levelCreator.loadLevel();
    	player = Player.getInstance();
    	playerView = PlayerView.getInstance();
    	gamePanel = GamePanel.getInstance();
    	player.addObserver(playerView);
    	
    	
    	mainFrame.add(gamePanel);
    	gamePanel.addKeyListener(keyController);
    	gamePanel.setIsThereKeyController(true);
    	
    	gamePanel.add(playerView);
    	gamePanel.setPlayer(player);
    	enemies = new ArrayList<Enemy>();
    	enemyViews = new ArrayList<EnemyView>();
    	bullets = new ArrayList<BubbleBullet>();
    	bulletsViews = new ArrayList<BubbleBulletView>();
    	player.spawnPlayer();
    	spawnEnemies();
    	gamePanel.setFocusable(true);
    	gamePanel.grabFocus();
    	
    	mainFrame.revalidate();
        mainFrame.repaint();
        
        audioManager.pauseBackgroundMusic();
        
//        removeDisplayedScreen(menuScreenView);
    }
	
	public void clearLevel() {
		player.setDefaultValues();
		gamePanel.remove(playerView);
		enemies.clear();
		enemyViews.stream().forEach(gamePanel::remove);
		bullets.clear();
		bulletsViews.stream().forEach(gamePanel::remove);
//		for(EnemyView eView : enemyViews) {
//			gamePanel.remove(eView);
//		}
	}
	
	public void resumeLevel() {
		
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
    
    public void setLevel(int level) {
    	this.level=level;
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
    
    public void removeDisplayedScreen(JPanel displayedScreen) {
    	mainFrame.remove(displayedScreen);
        mainFrame.revalidate();
        mainFrame.repaint();
        removeKeyController(displayedScreen);
    }
    
    public void removeKeyController(JPanel screen) {
    	screen.setFocusable(false);
    	screen.removeKeyListener(keyController);
        StateScreenView stateScreenView = (StateScreenView) screen;
        stateScreenView.setIsThereKeyController(false);
    }
    
    public void spawnEnemies() {
    	EnemyFactory enemyFactory = EnemyFactory.getInstance();
    	char [][] levelFile = LevelCreator.getInstance().getLevel();
    	for (int i = 0; i < levelFile.length; i++) {
    		for (int j = 0; j < levelFile[i].length; j++) {
    			Enemy enemy = enemyFactory.createEnemy(levelFile[i][j], i, j);
                if (enemy!=null) {
                	EnemyView enemyView = new EnemyView(enemy);
    				enemy.addObserver(enemyView);
    				gamePanel.add(enemyView);
    				enemies.add(enemy);
    				enemyViews.add(enemyView);
                }
                
            }
    	}
    }
    
    public void bubbleShooted() {
    	BubbleBullet bullet = player.shot();
		BubbleBulletView bulletView = new BubbleBulletView(bullet);
		bullet.addObserver(bulletView);
		gamePanel.add(bulletView);
		addBullet(bullet);
		addBulletView(bulletView);
    }
    
    public void addBullet(BubbleBullet bullet) {
    	bullets.add(bullet);
    }
    
    public void addBulletView (BubbleBulletView bulletView) {
    	bulletsViews.add(bulletView);
    }
}
