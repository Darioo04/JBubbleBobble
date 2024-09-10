package controller;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

import model.Bubble;
import model.BubbleBullet;
import model.BubbleFactory;
import model.CollisionChecker;
import model.Direction;
import model.Enemy;
import model.EnemyFactory;
import model.Fire;
import model.GameConstants;
import model.GameModel;
import model.GameOverScreen;
import model.GameState;
import model.LastLevelWinScreen;
import model.Food;
import model.FoodFactory;
import model.MenuScreen;
import model.ObjModel;
import model.PauseScreen;
import model.Player;
import model.PowerUp;
import model.PowerUpFactory;
import model.SelectLevelScreen;
import model.StateScreen;
import model.SuperDrunk;
import model.Thunder;
import model.Water;
import model.WinScreen;
import view.BubbleView;
import view.EnemyView;
import view.GamePanel;
import view.LastLevelWinScreenView;
import view.LevelEditorView;
import view.MainFrame;
import view.MenuScreenView;
import view.ObjView;
import view.PauseScreenView;
import view.PlayerView;
import view.PowerUpView;
import view.ProfileView;
import view.SelectLevelView;
import view.StateScreenView;
import view.StatusBar;
import view.WaterView;
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
    private LastLevelWinScreen lastLevelWinScreen;
    private GamePanel gamePanel;
    private CollisionChecker collisionChecker;
    public static int frames = 0;
    private Timer timer;
    private long[] topScores;
    private long score;
    private boolean firstTimePlaying;
    private String playerName;
    private int gamesPlayed;
	private int gamesWon;
	private int gamesLost;
	private boolean waterfallCreating;
    
    private List<Enemy> enemies;
    private List<EnemyView> enemyViews;
    private List<Bubble> bubbles;
    private List<BubbleView> bubbleViews;
    private List<Food> foods;
    private List<FoodView> foodViews;
    private List<Food> collectedItems;
    private List<FoodView> itemViews;
    private List<ObjModel> objs;
    private List<ObjView> objViews;
    private List<PowerUp> powerUps;
    private List<PowerUpView> powerUpViews;
    private List<Water> waterParticles;
    private List<ObjView> waterParticleViews;
    
    private PlayerAnimationController playerAnimationController;
    private List<EnemyAnimationController> eControllers;
    private List<BubbleAnimationController> bControllers;
    private List<ObjAnimationController> objControllers;
    
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
        gameModel = GameModel.getInstance();
    	loadGameData();
    	score = 0;
    	if (gameModel.getFirstTimePlaying()) {
    		playerName = JOptionPane.showInputDialog(null, "Inserisci il tuo nome:", "Benvenuto", JOptionPane.PLAIN_MESSAGE);
    		if (playerName == null) System.exit(0);
    		while (playerName.trim().isEmpty()) {
    			JOptionPane.showMessageDialog(null, "Nome non valido. Riprova.");
                playerName = JOptionPane.showInputDialog(null, "Inserisci il tuo nome:", "Benvenuto", JOptionPane.PLAIN_MESSAGE);
                if (playerName == null) {
                	System.exit(0);
                }
            }
    		gameModel.setPlayerName(playerName);
    		gameModel.setFirstTimePlaying(false);
    	}
        gameState = GameState.MENU;
        
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
        lastLevelWinScreen = LastLevelWinScreen.getInstance();
        
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
                        animationCycle++;
                    }
                    if (animationCycle == 3) animationCycle = 0;
                    
				}
				
			}
		});
    }
    
    public void updateAnimation() {
    	playerAnimationController.updateAnimation(animationCycle);
        eControllers.parallelStream().forEach(eController -> eController.updateAnimation(animationCycle));
        bControllers.parallelStream().forEach(bController -> bController.updateAnimation(animationCycle));
        objControllers.parallelStream().forEach(objController -> objController.updateAnimation(animationCycle));
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
				
				enemies.parallelStream().forEach(Enemy::update);
				
				bubbles.parallelStream().forEach(Bubble::update);
				bubbles.parallelStream().forEach(bubble -> collisionChecker.checkBubbleEnemyCollision(bubble, enemies));
				collisionChecker.checkBubblePlayerCollision(bubbles, player);
		    	spawnSpecialBubbles();
				
				if (waterfallCreating && frames % 5 == 0) {
					int index = waterParticles.size() - 1;
					createWaterParticle(waterParticles.get(index).getX(), waterParticles.get(index).getY(), waterParticles.get(index).getDirection());
					if (waterParticles.get(index).getY() + GameConstants.WATER_SIZE >= GameConstants.SCREEN_HEIGHT - GameConstants.TILE_SIZE) {
						deleteWaterfall();
					}
				}
				enemies.stream().forEach(e -> collisionChecker.checkEntityWaterCollision(e, waterParticles));
				
				waterParticles.parallelStream().forEach(Water::update);
				objs.parallelStream().forEach(ObjModel::update);
				objs.stream()
					.filter(obj -> obj instanceof Thunder)
					.map(obj -> (Thunder) obj)
					.forEach(thunder -> collisionChecker.checkThunderEnemyCollision(thunder, enemies));
				objs.stream()
					.filter(obj -> obj instanceof Fire)
					.map(obj -> (Fire) obj)
					.forEach(fire -> collisionChecker.checkFireEnemyCollision(fire,enemies));
					
				if (level == 24) {
					SuperDrunk boss = null;
					for (Enemy e : enemies) {
						if(e instanceof SuperDrunk) {
							boss = (SuperDrunk) e;
							break;
						}
					}
					if (boss.isDead()) {
						killAllEnemies();
						score += 50000;
					}
				}
				
				if(enemies.isEmpty() && foods.isEmpty() && collectedItems.isEmpty()) {
					spawnFood();
				}
				foods.stream().forEach(Food::update);
				if (collectedItems.size() == 2) {
					if (level == 24) {
						LastLevelWinScreenView lastLevelWinScreenView = (LastLevelWinScreenView) lastLevelWinScreen.getStateScreenView();
						lastLevelWinScreenView.setScore(score);
						changeDisplayedScreen(gamePanel, lastLevelWinScreen.getStateScreenView());
	                    setGameState(GameState.LAST_WIN);
	                    lastLevelWinScreen.update();
                    }
					else {
						changeDisplayedScreen(gamePanel, winScreen.getStateScreenView());
	                    setGameState(GameState.WIN);
	                    winScreen.update();
					}
				}	
				
				spawnPowerUp();
				powerUps.parallelStream().forEach(PowerUp::update);
				collisionChecker.checkPlayerEnemyCollision(player, enemies);
				
				if (player.getLostLife() && !player.isDead()) {	//se perde una vita respawno il player
					resetLevel();
				}
			}
			
            case PAUSE -> {
            	pauseScreen.update();
            }
            
            case SELECT_PROFILE -> {
            	gameModel.update();
            }
            
            case LEVEL_EDITOR -> {
            	
            }
            
            case WIN -> {
            	winScreen.update();
            }
            
            case LAST_WIN -> {
                lastLevelWinScreen.update();
            }
            
            case GAME_OVER -> {
				gameOverScreen.update();
            }
		
			default -> {
				throw new IllegalArgumentException("Unexpected value: " + gameState);
			}	
		}
    }
    
    //da spostare possibilmente nel game model (il loadGameData e il saveGameData)
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
					gameModel.setFirstTimePlaying(firstTimePlaying);
				}
				case "name" -> {
					playerName = parts[1];
					gameModel.setPlayerName(playerName);
				}
				case "top scores" -> {
					topScores = new long[3];
					String[] scores = parts[1].split(" ");
					topScores[0] = Integer.parseInt(scores[0]);
					topScores[1] = Integer.parseInt(scores[1]);
					topScores[2] = Integer.parseInt(scores[2]);
					gameModel.setTopScores(topScores);
				}
				case "custom tile" -> {
					LevelEditorView.getInstance().setTile(Integer.parseInt(parts[1]));
				}
				case "games played" -> {
					gamesPlayed = Integer.parseInt(parts[1]);
					gameModel.setGamesPlayed(gamesPlayed);
				}
				case "games won" -> {
                    gamesWon = Integer.parseInt(parts[1]);
                    gameModel.setGamesWon(gamesWon);
                }
				case "games lost" -> {
                    gamesLost = Integer.parseInt(parts[1]);
                    gameModel.setGamesLost(gamesLost);
                }
				
				default ->
				throw new IllegalArgumentException("Unexpected value: " + parts[0]);
				}
			}
		} catch (Exception e) {
			
		}
    }
    
    public void saveGameData() {
    	String projectPath = System.getProperty("user.dir");
        String path = projectPath + "/data/game-data.txt";
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))){
			writer.write("first time playing: " + firstTimePlaying + "\n");
			writer.write("name: " + gameModel.getPlayerName() + "\n");
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
			writer.write("games played: " + gameModel.getGamesPlayed() + "\n");
			writer.write("games won: " + gameModel.getGamesWon() + "\n");
			writer.write("games lost: " + gameModel.getGamesLost() + "\n");
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
    	player.setInvicibility(false);
    	playerView = PlayerView.getInstance();
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
    	enemies = new CopyOnWriteArrayList<>();
    	enemyViews = new ArrayList<>();
    	bubbles = new CopyOnWriteArrayList<>();
    	bubbleViews = new ArrayList<>();
    	foods = new ArrayList<>();
    	foodViews = new ArrayList<>();
    	itemViews = new ArrayList<>();
    	collectedItems = new ArrayList<>();
    	objs = new CopyOnWriteArrayList<>();
    	objViews = new ArrayList<>();
    	powerUps = new ArrayList<>();
    	powerUpViews = new ArrayList<>();
    	eControllers = new ArrayList<>();
    	bControllers = new ArrayList<>();
    	objControllers = new ArrayList<>();
    	waterParticles = new ArrayList<>();
    	waterParticleViews = new ArrayList<>();
    	waterfallCreating = false;
    	
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
		foods.clear();
		foodViews.stream().forEach(fView -> gamePanel.remove(fView));
		collectedItems.clear();
		enemyViews.stream().forEach(eView -> gamePanel.remove(eView));
		itemViews.stream().forEach(item -> gamePanel.remove(item));
		itemViews.clear();
		enemyViews.clear();
		objViews.stream().forEach(objView -> gamePanel.remove(objView));
		objs.clear();
		objViews.clear();
		powerUps.parallelStream().forEach(PowerUp::removePowerUp);
		powerUps.clear();
		powerUpViews.stream().forEach(pView -> gamePanel.remove(pView));
		powerUpViews.clear();
		removeBubbles();
		audioManager.pauseLevelMusic();
	}
	
	public void resetLevel() {
		player.spawnPlayer();
		enemies.parallelStream().forEach(enemy -> enemy.setInBubble(false));
		respawnEnemies();
		player.setLostLife(false);
		removeBubbles();
		removeObjects();
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
                	EnemyView enemyView = new EnemyView(enemy, enemy.getNumIdleSprites(),enemy.getNumRunningSprites(),enemy.getNumJumpingSprites(), enemy.getNumFallingSprites());
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
    
    public void spawnFood() {;
    	char[][] levelFile = levelCreator.getLevel();
    	FoodFactory foodFactory = FoodFactory.getInstance();
    	
    	int randomY = new Random().nextInt(GameConstants.ROWS-1)+1;
    	int randomX = new Random().nextInt(GameConstants.COLS-1)+1;
    	
    	while (levelFile[randomY][randomX] != ' ') {
    		randomX = new Random().nextInt(GameConstants.COLS-1)+1;
    		randomY = new Random().nextInt(GameConstants.ROWS-1)+1;
    	}
    	Food food1 = foodFactory.createItem(Math.random(), randomX*GameConstants.TILE_SIZE, randomY*GameConstants.TILE_SIZE);
//    	System.out.println("x: " + randomX + "   y: " + randomY);
    	
    	randomY = new Random().nextInt(GameConstants.ROWS-1)+1;
    	randomX = new Random().nextInt(GameConstants.COLS-1)+1;
    	while (levelFile[randomY][randomX] != ' ') {
    		randomX = new Random().nextInt(GameConstants.COLS-1)+1;
    		randomY = new Random().nextInt(GameConstants.ROWS-1)+1;
    	}
    	Food food2 = foodFactory.createItem(Math.random(), randomX*GameConstants.TILE_SIZE, randomY*GameConstants.TILE_SIZE);
//    	System.out.println("x: " + randomX + "   y: " + randomY);
    	
    	FoodView foodView1 = new FoodView(food1);
    	FoodView foodView2 = new FoodView(food2);
    	food1.addObserver(foodView1);
    	food2.addObserver(foodView2);
    	
    	foods.add(food1);
    	foods.add(food2);
    		
    	foodViews.add(foodView1);
    	foodViews.add(foodView2);
    	gamePanel.add(foodView1);
    	gamePanel.add(foodView2);
//    	gamePanel.add(new JLabel());		//ce un bug per cui alcuni sprite vengono renderizazati a sinistra della mappa in un tile rendendo impossibile nel caso del food poter completare il livello, aggiungendo un jlabel vuoto si renderizza bene il food
    	//update: bug risolto, era un problema del layout del gamePanel
    }
    
    public void spawnPowerUp() {
    	PowerUpFactory powerUpFactory = PowerUpFactory.getInstance();
    	List<PowerUp> newPowerUps = powerUpFactory.createPowerUp();
    	for(PowerUp powerUp : newPowerUps) {
        	PowerUpView powerUpView = new PowerUpView(powerUp);
        	powerUp.addObserver(powerUpView);
        	powerUpViews.add(powerUpView);
        	gamePanel.add(powerUpView);	
    	}
        powerUps.addAll(newPowerUps);	
    }
    
    public void spawnSpecialBubbles() {
    	Bubble bubble = BubbleFactory.getInstance().createBubble();
    	if (bubble!=null) {
    		bubbles.add(bubble);
    		BubbleView bubbleView = new BubbleView(bubble, bubble.getPath(), bubble.getNumSprites());
    		bubble.addObserver(bubbleView);
    		gamePanel.add(bubbleView);
    		bubbleViews.add(bubbleView);
    		
    	}
    }
    
    public void bubbleShooted() {
    	BubbleBullet bullet = player.shot();
    	bubbles.add(bullet);
		BubbleView bulletView = new BubbleView(bullet,bullet.getPath(),bullet.getNumSprites());
		bullet.addObserver(bulletView);
		gamePanel.add(bulletView);
		
//		System.out.println("bolla");
    	bubbleViews.add(bulletView);
    }
    
    public void setNickname(String nickname) {
    	gameModel.setPlayerName(nickname);
    }
    
    public void removeBubbles() {
    	bubbles.clear();
		bubbleViews.stream().forEach(bView -> gamePanel.remove(bView));
    }
    
    public void removeBubble(Bubble bubble, BubbleView bView) {
    	bubble.deleteObserver(bView);
    	bubbles.remove(bubble);
    	score += 20;
        audioManager.play("points");
        bubbleViews.remove(bView);
        gamePanel.remove(bView);
    	
    }
    
    public void removeObjects() {
    	objViews.parallelStream().forEach(oView -> gamePanel.remove(oView));
    	objs.clear();
    	objViews.clear();
    }
    
    public void removeObject(ObjModel obj, ObjView oView) {
    	obj.deleteObservers();
    	objs.remove(obj);
    	objViews.remove(oView);
    	gamePanel.remove(oView);
    }
    
    public void removeDeadEnemy(Enemy enemy, EnemyView eView) {
    	enemy.deleteObserver(eView);
    	enemies.remove(enemy);
    	score+=enemy.getScoreWhenKilled();
    	audioManager.play("points");
    	enemyViews.remove(eView);
    	gamePanel.remove(eView);
    }
    
    public void collectFood(Food food, FoodView fView) {
    	food.deleteObserver(fView);
    	food.setHitbox(new Rectangle(0, 0, 1, 1));
		score += food.getPoints();
		audioManager.play("points");
    	collectedItems.add(food);
    	foodViews.remove(fView);
    	gamePanel.remove(fView);
    }
    
    public void removePowerUp(PowerUp powerUp, PowerUpView pView) {
    	powerUp.deleteObserver(pView);
    	audioManager.play("points");
    	powerUpViews.remove(pView);
    	gamePanel.remove(pView);
    }
    
//    public void addEnemy(Enemy enemy) {
//    	enemies.add(enemy);
//    	EnemyView eView = new EnemyView();
//    }
    
    
    public void addEnemyAnimationController(EnemyAnimationController eController) {
    	eControllers.add(eController);
    }
    
    public void addBubbleAnimationController(BubbleAnimationController bController) {
    	bControllers.add(bController);
    }
    
    public void addObjAnimationController(ObjAnimationController objController) {
    	objControllers.add(objController);
    }
    
    public void addObj(ObjModel obj) {
    	objs.add(obj);
    	ObjView objView = new ObjView(obj, obj.getPath(), obj.getNumSprites());
    	obj.addObserver(objView);
    	gamePanel.add(objView);
    	objViews.add(objView);
    	
    }
    
    public void createWaterfall(int x, int y, Direction direction) {
    	waterfallCreating = true;
    	Water waterParticle = new Water(x, y, direction);
    	waterParticles.add(waterParticle);
    	ObjView waterView = new ObjView(waterParticle, waterParticle.getPath(), waterParticle.getNumSprites());
    	waterParticle.setWaterView(waterView);
    	waterParticleViews.add(waterView);
    }
    
    public void createWaterParticle(int x, int y, Direction direction) {
    	char[][] levelFile = LevelCreator.getInstance().getLevel();
    	if (levelFile[(y+GameConstants.WATER_SIZE)/GameConstants.TILE_SIZE][x/GameConstants.TILE_SIZE] == '1') {
    		if(direction == Direction.RIGHT) {
        		x += GameConstants.WATER_SIZE;
        	}
        	else {
        		x -= GameConstants.WATER_SIZE;
        	}
    		if (levelFile[y/GameConstants.TILE_SIZE][x/GameConstants.TILE_SIZE] == '1') deleteWaterfall();
    	}else {
    		y += GameConstants.WATER_SIZE;
    	}
    	Water waterParticle = new Water(x, y, direction);
    	waterParticles.add(waterParticle);
    	ObjView waterView = new ObjView(waterParticle, waterParticle.getPath(), waterParticle.getNumSprites());
    	waterParticle.setWaterView(waterView);
    	waterParticleViews.add(waterView);
    }
    
    public void deleteWaterfall() {
    	waterfallCreating = false;
    	for (ObjView w : waterParticleViews) {
            gamePanel.remove(w);
    	}
        waterParticles.clear();
        waterParticleViews.clear();
    }
    
    public void freezeEnemies() {
    	enemies.parallelStream().forEach(enemy -> enemy.setFrozen());;
    }
    
    public void unfreezeEnemies() {
    	enemies.parallelStream().forEach(enemy -> enemy.setUnfrozen());
    }
    
    public void killAllEnemies() {
    	enemies.stream().filter(enemy -> !(enemy instanceof SuperDrunk)).forEach(enemy -> enemy.setDead(true));
    }
    
    public void setPlayerName(String name) {
    	playerName = name;
    }
    
    public long getScore() {
    	return score;
    }
    
    public List<PowerUp> getPowerups() {
    	return powerUps;
    }
    
    public void addScore(long score) {
    	this.score+=score;
    }
    
    public void setGameOver() {
    	changeDisplayedScreen(gamePanel,gameOverScreen.getStateScreenView());
    	setGameState(GameState.GAME_OVER);
    	gameOverScreen.update();
    }
    
    public String getPlayerName() {
    	return playerName;
    }
    
    
    public void setScore(long score) {
    	this.score = score;
    }
}
