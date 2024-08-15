package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import model.Banebou;
import model.Bubble;
import model.BubbleBullet;
import model.CollisionChecker;
import model.Enemy;
import model.EnemyFactory;
import model.GameConstants;
import model.GameModel;
import model.GameState;
import model.Hidegons;
import model.Invader;
import model.Item;
import model.ItemFactory;
import model.MenuScreen;
import model.PauseScreen;
import model.Player;
import model.PulPul;
import model.SelectLevelScreen;
import model.StateScreen;
import model.Tile;
import view.BubbleView;
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
import view.ItemView;
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
    public static int frames = 0;
    private Timer timer;
    
    private ArrayList<Enemy> enemies;
    private ArrayList<EnemyView> enemyViews;
    private ArrayList<Bubble> bullets;
    private ArrayList<BubbleView> bulletViews;
    private ArrayList<Item> items;
    private ArrayList<ItemView> itemViews;
    
    private GameState gameState;
    private MainFrame mainFrame;
    private LevelCreator levelCreator;
    private AudioManager audioManager;
    public int level;
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
					if(frames == 60) frames = 0;
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
//				enemies.stream().filter(Enemy::isDead).forEach(enemy -> enemies.remove(enemy));
				
				bullets.stream().forEach(Bubble::update);
//				bullets.stream()
//					.filter(bullet -> collisionChecker.checkBubblePlayerCollision(bullet, player))
//					.forEach(bullet -> { bullets.remove(bullet); 
//										bulletViews.remove(bullet.getBubbleBulletView()); });
//				
				collisionChecker.checkPlayerEnemyCollision(player, enemies);
				enemies.stream().forEach( enemy -> {
				bullets.stream().forEach(bubble -> collisionChecker.checkBubbleEnemyCollision(bubble, enemies));
				});
				if (player.getLostLife()) {	//se perde una vita respawno il player
					player.spawnPlayer();
					respawnEnemies();
					player.setLostLife(false);
					removeBubbles();
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
    	enemies = new ArrayList<>();
    	enemyViews = new ArrayList<>();
    	bullets = new ArrayList<>();
    	bulletViews = new ArrayList<>();
    	items = new ArrayList<>();
    	itemViews = new ArrayList<>();
    	player.spawnPlayer();
    	spawnEnemies();
    	gamePanel.setFocusable(true);
    	gamePanel.grabFocus();
    	
    	mainFrame.revalidate();
        mainFrame.repaint();
        
        audioManager.pauseBackgroundMusic();
        audioManager.playLevelMusic("level");       
//        removeDisplayedScreen(menuScreenView);
    }
	
	public void clearLevel() {
		player.setDefaultValues();
		gamePanel.remove(playerView);
		enemies.clear();
		enemyViews.stream().forEach(eView -> gamePanel.remove(eView));
		removeBubbles();
		audioManager.pauseLevelMusic();
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
    public int getLevel() {
    	return level;
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
    
    public void respawnEnemies() {
    	enemies.stream().forEach(enemy -> {
    		enemy.setX(enemy.getSpawnX());
    		enemy.setY(enemy.getSpawnY());
    	});;
    }
    
    public void respawnPlayer() {
    	player.setX(player.getSpawnX());
    	player.setY(player.getSpawnY());
    }
    
    public void spawnItems() {
    	if (enemies.stream().count()==0) {
    		Item item1 = ItemFactory.getInstance().createItem(new Random().nextInt(101));
    		Item item2 = ItemFactory.getInstance().createItem(new Random().nextInt(101));
    		ItemView itemView1 = new ItemView(item1);
    		item1.addObserver(itemView1);
    		ItemView itemView2 = new ItemView(item2);
    		item2.addObserver(itemView2);
    		
    		items.add(item1);
    		items.add(item2);
    		
    		itemViews.add(itemView1);
    		itemViews.add(itemView2);
    	}
    }
    
    public void bubbleShooted() {
    	BubbleBullet bullet = player.shot();
		BubbleView bulletView = new BubbleView(bullet);
//		bullet.addObserver(bulletView);
		bullet.setBubbleBulletView(bulletView);
		gamePanel.add(bulletView);
		bullets.add(bullet);
    	bulletViews.add(bulletView);
//		addBulletView(bulletView);
    }
    
//    public void addBullet(BubbleBullet bullet) {
//    	bullets.add(bullet);
//    }
//    
//    public void addBulletView (BubbleBulletView bulletView) {
//    	bulletsViews.add(bulletView);
//    }
    
    public void setNickname(String nickname) {
    	gameModel.setNickname(nickname);
    }
    
    public void removeBubbles() {
    	bullets.clear();
		bulletViews.stream().forEach(bView -> gamePanel.remove(bView));
    }
}
