package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import model.Banebou;
import model.Bubble;
import model.BubbleBullet;
import model.CollisionChecker;
import model.Enemy;
import model.EnemyFactory;
import model.GameConstants;
import model.GameModel;
import model.GameOverScreen;
import model.GameState;
import model.Hidegons;
import model.Invader;
import model.Food;
import model.FoodFactory;
import model.MenuScreen;
import model.ObjModel;
import model.PauseScreen;
import model.Player;
import model.PulPul;
import model.SelectLevelScreen;
import model.StateScreen;
import model.Tile;
import view.BubbleView;
import view.EnemyView;
import view.GamePanel;
import view.LevelEditorView;
import view.MainFrame;
import view.MenuScreenView;
import view.PauseScreenView;
import view.PlayerView;
import view.ProfileView;
import view.SelectLevelView;
import view.StateScreenView;
import view.StatusBar;
import view.FoodView;
import view.GameOverView;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("deprecation")

public class GameController {
	
    private KeyController keyController;
    private GameModel gameModel;
    private Player player;
    private PlayerView playerView;
    private StatusBar statusBar;
    private MenuScreen menuScreen;
    private MenuScreenView menuScreenView;
    private SelectLevelScreen selectLevelScreen;
    private SelectLevelView selectLevelView;
    private ProfileView profileView;
    private PauseScreen pauseScreen;
    private PauseScreenView pauseScreenView;
    private GameOverScreen gameOverScreen;
    private GamePanel gamePanel;
    private CollisionChecker collisionChecker;
    public static int frames = 0;
    private Timer timer;
    private int[] topScores;
    private boolean firstTimePlaying;
    private String playerName;
    
    private List<Enemy> enemies;
    private List<EnemyView> enemyViews;
    private List<Bubble> bullets;
    private List<Bubble> removedBubbles;
    private List<BubbleView> bulletViews;
    private List<Food> items;
    private List<FoodView> itemViews;
    private List<EnemyAnimationController> eControllers;
    private List<ObjModel> objs;
    private PlayerAnimationController playerAnimationController;
    
    private GameState gameState;
    private MainFrame mainFrame;
    private LevelCreator levelCreator;
    private AudioManager audioManager;
    
    private int level;
    private int animationCycle = 0;

    
    private static GameController instance;
    
    public static GameController getInstance() {
    	if (instance==null) instance = new GameController();
    	return instance;
    }
    
    private GameController() {
    	loadGameData();
    	if (firstTimePlaying) {
    		playerName = JOptionPane.showInputDialog(null, "Inserisci il tuo nome:", "Benvenuto", JOptionPane.PLAIN_MESSAGE);
    		if (playerName == null) {System.exit(0);}
    		while (playerName.trim().isEmpty()) {
    			JOptionPane.showMessageDialog(null, "Nome non valido. Riprova.");
                playerName = JOptionPane.showInputDialog(null, "Inserisci il tuo nome:", "Benvenuto", JOptionPane.PLAIN_MESSAGE);
                if (playerName == null) {
                	System.exit(0);
                }
            }
    		firstTimePlaying = false;
    	}
        gameState = GameState.MENU;
        gameModel = GameModel.getInstance();
        gameModel.addObserver(StatusBar.getInstance());
        
        levelCreator = LevelCreator.getInstance();
        mainFrame = MainFrame.getInstance();
        selectLevelScreen = SelectLevelScreen.getInstance();
        selectLevelView = (SelectLevelView) selectLevelScreen.getStateScreenView();
        profileView = ProfileView.getInstance();
        gameModel.addObserver(profileView);
                
        
        menuScreen = MenuScreen.getInstance();
        menuScreenView = (MenuScreenView) menuScreen.getStateScreenView();
        pauseScreen = PauseScreen.getInstance();
        pauseScreenView = (PauseScreenView) pauseScreen.getStateScreenView();
        gameOverScreen = GameOverScreen.getInstance();
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
                        playerAnimationController.updateAnimation(animationCycle);
                        eControllers.stream().forEach(eController -> eController.updateAnimation(animationCycle));
//                        enemyViews.stream().forEach( eView -> eView.getEnemyAnimationController().updateAnimation(animationCycle));
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
				bullets.stream().forEach(Bubble::update);
				bullets.stream().forEach(bubble -> collisionChecker.checkBubbleEnemyCollision(bubble, enemies));
				removeBubble();
				objs.stream().forEach(ObjModel::update);
//				enemies.stream().forEach( enemy -> {
//					bullets.stream().forEach(bubble -> collisionChecker.checkBubbleEnemyCollision(bubble, enemies));
//				});
//				enemies = enemies.stream()
//						.filter(enemy -> !enemy.isDead())
//						.collect(Collectors.toList());
//				
				
//				bullets.stream()
//					.filter(bullet -> collisionChecker.checkBubblePlayerCollision(bullet, player))
//					.forEach(bullet -> { bullets.remove(bullet); 
//										bulletViews.remove(bullet.getBubbleBulletView()); });
				
				collisionChecker.checkPlayerEnemyCollision(player, enemies);
				
				if (player.getLostLife() && !player.isDead()) {	//se perde una vita respawno il player
					resetLevel();
				}
//				else if (player.isDead()) {
//					changeDisplayedScreen(gamePanel,gameOverScreen.getStateScreenView());
//					setGameState(GameState.GAME_OVER);
//					gameOverScreen.update();
//				}
			}
			
            case PAUSE -> {
            	pauseScreen.update();
            }
            
            case SELECT_PROFILE -> {
            	profileView.repaint();
            }
            
            case LOGIN -> {
            	
            }
            
            case LEVEL_EDITOR -> {
            	
            }
            
            case GAME_OVER -> {
				gameOverScreen.update();
            }
		
			default -> {
				throw new IllegalArgumentException("Unexpected value: " + gameState);
			}	
		}
    }
    
    public void loadGameData() {
    	String projectPath = System.getProperty("user.dir");
        String path = projectPath + "/data/game-data.txt";
        
        try (BufferedReader reader = new BufferedReader(new FileReader(path))){
			String line;
			while ((line = reader.readLine())!= null) {
				String parts[] = line.split(": ");
				switch (parts[0]) {
				case "first time playing" -> {
					firstTimePlaying = Boolean.parseBoolean(parts[1]);
				}
				case "name" -> {
					playerName = parts[1];
				}
				case "top scores" -> {
					topScores = new int[3];
					String[] scores = parts[1].split(" ");
					topScores[0] = Integer.parseInt(scores[0]);
					topScores[1] = Integer.parseInt(scores[1]);
					topScores[2] = Integer.parseInt(scores[2]);
				}
				case "custom tile" -> {
					LevelEditorView.getInstance().setTile(Integer.parseInt(parts[1]));
				}
				
				default ->
				throw new IllegalArgumentException("Unexpected value: " + parts[0]);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
    }
    
    public void saveGameData() {
    	String projectPath = System.getProperty("user.dir");
        String path = projectPath + "/data/game-data.txt";
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))){
			writer.write("first time playing: " + firstTimePlaying + "\n");
			writer.write("name: " + playerName + "\n");
			writer.write("top scores: " + topScores[0] + " " + topScores[1] + " " + topScores[2] + "\n");
			writer.write("custom tile: " + LevelEditorView.getInstance().getTileNum() + "\n");
		} catch (Exception e) {
			// TODO: handle exception
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
    	playerView = PlayerView.getInstance(player.getNumIdleSprites(),player.getNumRunningSprites(),player.getNumJumpingSprites(), player.getNumFallingSprites());
    	gamePanel = GamePanel.getInstance();
    	player.addObserver(playerView);
    	statusBar = StatusBar.getInstance();
    	statusBar.setHP(player.getHP());
    	player.addObserver(statusBar);
    	playerAnimationController = PlayerAnimationController.getInstance();
    	mainFrame.add(gamePanel);
    	gamePanel.addKeyListener(keyController);
    	gamePanel.setIsThereKeyController(true);
    	
    	gamePanel.add(playerView);
    	gamePanel.setPlayer(player);
    	enemies = new ArrayList<>();
    	enemyViews = new ArrayList<>();
    	bullets = new ArrayList<>();
    	removedBubbles = new ArrayList<>();
    	bulletViews = new ArrayList<>();
    	items = new ArrayList<>();
    	itemViews = new ArrayList<>();
    	eControllers = new ArrayList<>();
    	objs = new ArrayList<>();
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
	
	public void resetLevel() {
		player.spawnPlayer();
		enemies.stream().forEach(enemy -> enemy.setInBubble(false));
		respawnEnemies();
		player.setLostLife(false);
		removeBubbles();
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
                	EnemyView enemyView = new EnemyView(enemy,enemy.getNumIdleSprites(),enemy.getNumRunningSprites(),enemy.getNumJumpingSprites(), enemy.getNumFallingSprites());
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
    		Food item1 = FoodFactory.getInstance().createItem(new Random().nextInt(101));
    		Food item2 = FoodFactory.getInstance().createItem(new Random().nextInt(101));
    		FoodView itemView1 = new FoodView(item1);
    		item1.addObserver(itemView1);
    		FoodView itemView2 = new FoodView(item2);
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
    
    public void removeBubble(Bubble bubble) {
    	removedBubbles.add(bubble);
    }
    
    public void removeBubble() {
    	removedBubbles.stream().forEach( bubble -> {
			bullets.remove(bubble);
			bulletViews.remove(bubble.getBubbleBulletView());
			gamePanel.remove(bubble.getBubbleBulletView());
		});
		removedBubbles.clear();
    }
    
    public void addEnemyAnimationController(EnemyAnimationController eController) {
    	eControllers.add(eController);
    }
    
    public void setPlayerAnimationController(PlayerAnimationController playerAnimationController) {
    	this.playerAnimationController=playerAnimationController;
    }
    
    public PlayerAnimationController getPlayerAnimationController() {
    	return playerAnimationController;
    }
    
    public void addObj(ObjModel obj) {
    	objs.add(obj);
    }
    
    public void setPlayerName(String name) {
    	playerName = name;
    }
}
