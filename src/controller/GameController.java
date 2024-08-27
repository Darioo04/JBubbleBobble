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
import model.WinScreen;
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
import view.WinScreenView;
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
    private WinScreen winScreen;
    private GamePanel gamePanel;
    private CollisionChecker collisionChecker;
    public static int frames = 0;
    private Timer timer;
    private long[] topScores;
    private long score;
    private boolean firstTimePlaying;
    private String playerName;
    
    private List<Enemy> enemies;
    private List<EnemyView> enemyViews;
    private List<Bubble> bullets;
    private List<Bubble> removedBubbles;
    private List<BubbleView> bulletViews;
    private List<Food> items;
    private List<Food> collectedItems;
    private List<FoodView> itemViews;
    private List<EnemyAnimationController> eControllers;
    private List<BubbleAnimationController> bControllers;
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
    	score = 0;
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
        winScreen = WinScreen.getInstance();
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
                    	updateAnimation();
//                        enemyViews.stream().forEach( eView -> eView.getEnemyAnimationController().updateAnimation(animationCycle));
                        animationCycle++;
                    }
                    if (animationCycle == 3) animationCycle = 0;
                    
				}
				
			}
		});
    }
    
    public void updateAnimation() {
    	playerAnimationController.updateAnimation(animationCycle);
        eControllers.stream().forEach(eController -> eController.updateAnimation(animationCycle));
        bControllers.stream().forEach(bController -> bController.updateAnimation(animationCycle));
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
				removeDeadEnemies();
				
				bullets.stream().forEach(bubble -> collisionChecker.checkBubbleEnemyCollision(bubble, enemies));
				bullets.stream().forEach(bubble -> collisionChecker.checkBubblePlayerCollision(bubble, player));
				bullets.stream().forEach(Bubble::update);
				removeExplodedBubbles();
				
				objs.stream().forEach(ObjModel::update);
				
				if(enemies.isEmpty() && items.isEmpty() && collectedItems.isEmpty()) {
					spawnFood();
				}
				if(!items.isEmpty()) { 
					for (Food item : items) {
						if (collisionChecker.checkFoodPlayerCollision(item, player)) {
							score += item.getPoints();
							audioManager.play("points");
							collectedItems.add(item);
							itemViews.remove(item.getFoodView());
						}
					}
				}
				if (collectedItems.size() == 2) {
					changeDisplayedScreen(gamePanel, winScreen.getStateScreenView());
                    setGameState(GameState.WIN);
                    winScreen.update();
				}
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
            
            case LEVEL_EDITOR -> {
            	
            }
            
            case WIN -> {
            	winScreen.update();
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
        topScores = new long[3];
        
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
					profileView.setPlayerName(playerName);
				}
				case "top scores" -> {
					topScores = new long[3];
					String[] scores = parts[1].split(" ");
					topScores[0] = Integer.parseInt(scores[0]);
					topScores[1] = Integer.parseInt(scores[1]);
					topScores[2] = Integer.parseInt(scores[2]);
					profileView.setTopScores(topScores);
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
			if(score > topScores[0]) {
				topScores[2] = topScores[1];
                topScores[1] = topScores[0];
                topScores[0] = score;
			}
			else if(score > topScores[1]) {
                topScores[2] = topScores[1];
                topScores[1] = score;
            }
			else if(score > topScores[2]) {
                topScores[2] = score;
            }
			writer.write("top scores: " + topScores[0] + " " + topScores[1] + " " + topScores[2] + "\n");
			writer.write("custom tile: " + LevelEditorView.getInstance().getTileNum() + "\n");
		} catch (Exception e) {
			e.printStackTrace();
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
    	collectedItems = new ArrayList<>();
    	eControllers = new ArrayList<>();
    	bControllers = new ArrayList<>();
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
		items.clear();
		collectedItems.clear();
		enemyViews.stream().forEach(eView -> gamePanel.remove(eView));
		itemViews.stream().forEach(item -> gamePanel.remove(item));
		itemViews.clear();
		enemyViews.clear();
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
    				enemy.setEnemyView(enemyView);
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
    
    public void spawnFood() {
//    	int localx = 0;
//    	int localy = 0;
//    	char[][] levelFile = levelCreator.getLevel();
//    	
//    	outerloop:
//    	for (int y = 0; y < GameConstants.ROWS; y++) {
//			for (int x = 0; x < GameConstants.COLS; x++) {
//				if (levelFile[y][x] == ' ' && levelFile[y+1][x] == '1' && Math.random() < 0.06) {		//5% probabilita 
//					localx = x * GameConstants.TILE_SIZE;
//					localy = y * GameConstants.TILE_SIZE;
//					break outerloop;
//				}
//			}
//		}
//    	Food item1 = FoodFactory.getInstance().createItem(Math.random(), localx, localy);
//    	
//    	outerloop2:
//        	for (int y = 0; y < GameConstants.ROWS; y++) {
//    			for (int x = 0; x < GameConstants.COLS; x++) {
//    				if (levelFile[y][x] == ' ' && levelFile[y+1][x] == '1' && Math.random() < 0.06) {		
//    					if (localx != x * GameConstants.TILE_SIZE || localy != y * GameConstants.TILE_SIZE) {
//    						localx = x * GameConstants.TILE_SIZE;
//    						localy = y * GameConstants.TILE_SIZE;
//    					}
//    					else {
//    						for (int i = 1; i< GameConstants.COLS; i++) {
//    							if (levelFile[y][i] == ' ' && levelFile[y+1][i] == '1') {
//    								localx = x * GameConstants.TILE_SIZE;
//    	    						localy = y * GameConstants.TILE_SIZE;
//    							}
//    						}
//    					}
//    					break outerloop2;
//    				}
//    			}
//    		}
//    	Food item2 = FoodFactory.getInstance().createItem(Math.random(), localx, localy);
    	Food item1 = FoodFactory.getInstance().createItem(Math.random(), 3*GameConstants.TILE_SIZE, 11*GameConstants.TILE_SIZE);
    	Food item2 = FoodFactory.getInstance().createItem(Math.random(), 7*GameConstants.TILE_SIZE, 11*GameConstants.TILE_SIZE);
    	
    	FoodView itemView1 = new FoodView(item1);
    	item1.setFoodView(itemView1);
    	FoodView itemView2 = new FoodView(item2);
    	item2.setFoodView(itemView2);
    		
    	items.add(item1);
    	items.add(item2);
    		
    	itemViews.add(itemView1);
    	itemViews.add(itemView2);
    	gamePanel.add(itemView1);
    	gamePanel.add(itemView2);
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
    
    public void removeExplodedBubbles() {
    	removedBubbles.stream().forEach( bubble -> {
			bullets.remove(bubble);
			bulletViews.remove(bubble.getBubbleBulletView());
			gamePanel.remove(bubble.getBubbleBulletView());
		});
//    	bullets = bullets.stream().filter(bubble -> !bubble.canBeDeleted()).collect(Collectors.toList());
		removedBubbles.clear();
    }
    
    public void removeDeadEnemies() {
    	for (Enemy e : enemies.stream().filter(Enemy::isDead).collect(Collectors.toList())) {
    		if (e.getCanBeDeleted()) {
    			score += e.getScoreWhenKilled();
    			audioManager.play("points");
    			enemies.remove(e);
    			enemyViews.remove(e.getEnemyView());
    			gamePanel.remove(e.getEnemyView());
    		}
    	}
    }
    
    public void addEnemyAnimationController(EnemyAnimationController eController) {
    	eControllers.add(eController);
    }
    
    public void addBubbleAnimationController(BubbleAnimationController bController) {
    	bControllers.add(bController);
    }
    
    public void addObj(ObjModel obj) {
    	objs.add(obj);
    }
    
    public void setPlayerName(String name) {
    	playerName = name;
    }
    
    public long getScore() {
    	return score;
    }
}
