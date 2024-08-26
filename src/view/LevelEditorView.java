package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.GameConstants;

public class LevelEditorView extends StateScreenView {
	
	public static LevelEditorView instance;
	private Image choseTile;
	private Image levelCreated;
	private boolean tileSelected = false;
	private BufferedImage tile;
	private int tileNum;
	private char[][] levelFile;
	private int x, y;
	private boolean addTile = false;
	
	public static LevelEditorView getInstance() {
		if (instance == null) instance = new LevelEditorView();
		return instance;
	}

	private LevelEditorView() {
		try {
			choseTile = ImageIO.read(getClass().getResourceAsStream("/screens/level-editor.png")).getScaledInstance(GameConstants.SCREEN_WIDTH, GameConstants.SCREEN_HEIGHT, Image.SCALE_DEFAULT);
			levelCreated = ImageIO.read(getClass().getResourceAsStream("/screens/custom-level-confirmation.png")).getScaledInstance(GameConstants.SCREEN_WIDTH, GameConstants.SCREEN_HEIGHT, Image.SCALE_DEFAULT);
		} catch (IOException e) {
			e.printStackTrace();
		}
		resetEditor();
	}
	
	@Override
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        if (!tileSelected) {
        	g2.drawImage(choseTile, 0, 0, this.getWidth(), this.getHeight(), null);
        } else {
        	if (x == 1 && y == 0) {
        		g2.setColor(Color.BLACK);
        		g2.fillRect(0, 0, getWidth(), getHeight());
        		for (int i = 0; i < GameConstants.ROWS; i++) {
        			g2.drawImage(tile, 0, i*GameConstants.TILE_SIZE, GameConstants.TILE_SIZE, GameConstants.TILE_SIZE, null);
        			g2.drawImage(tile, GameConstants.SCREEN_WIDTH - GameConstants.TILE_SIZE, i*GameConstants.TILE_SIZE, GameConstants.TILE_SIZE, GameConstants.TILE_SIZE, null);
        		}
        	}
        	if (addTile) { 
        		g2.drawImage(tile, x*GameConstants.TILE_SIZE, y*GameConstants.TILE_SIZE, GameConstants.TILE_SIZE, GameConstants.TILE_SIZE, null);
        		levelFile[y][x] = '1';
        	} else {
        		levelFile[y][x] = ' ';
        	}
        	if (x < GameConstants.COLS - 2) {
        		g2.setColor(Color.YELLOW);
        		g2.drawRect((x+1)*GameConstants.TILE_SIZE, y*GameConstants.TILE_SIZE, GameConstants.TILE_SIZE, GameConstants.TILE_SIZE);
        	} else {
        		g2.setColor(Color.YELLOW);
        		g2.drawRect(GameConstants.TILE_SIZE, (y+1)*GameConstants.TILE_SIZE, GameConstants.TILE_SIZE, GameConstants.TILE_SIZE);
        	}
        	x += 1;
        	if (x == GameConstants.COLS - 1) {
        		x = 1;
        		y += 1;
        	}
        	if (y == GameConstants.ROWS) {
        		addEnemies();
        		saveLevel();
        		g2.drawImage(levelCreated, 0, 0, this.getWidth(), this.getHeight(), null);
        	}
        }
    }
	
	public void saveLevel() {
		levelFile[GameConstants.ROWS-2][1] = ' ';		//lascio 2 spazi liberi allo spawn del player
		levelFile[GameConstants.ROWS-2][2] = ' ';
		levelFile[GameConstants.ROWS-1][1] = '1';        //mi assicuro che ci sia un blocco per spawnare il player
		StringBuilder sb = new StringBuilder();
        for (char[] row : levelFile) {
            for (char c : row) {
                sb.append(c);
            }
            sb.append('\n');
        }
        String levelString = sb.toString();
        String projectPath = System.getProperty("user.dir");
        String path = projectPath + "/custom-level/custom-level.txt";
        File file = new File(path);
        if(file.exists()) {
        	file.delete();
        }
		try {
			file.createNewFile();
			FileWriter fileWriter = new FileWriter(file, false);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(levelString);
			
			bufferedWriter.close();
            fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void addEnemies() {
		for (int y = 0; y < GameConstants.ROWS; y++) {
			for (int x = 0; x < GameConstants.COLS; x++) {
				if (levelFile[y][x] == ' ' && levelFile[y+1][x] == '1' && Math.random() < 0.06) {		//5% probabilita di spawnare un nemico
					int random = (int) (Math.random() * 5);
					switch (random) {
					case 0 -> {
						levelFile[y][x] = 'P';
					}
					case 1 -> {
                        levelFile[y][x] = 'R';
                    }
					case 2 -> {
                        levelFile[y][x] = 'B';
                    }
					case 3 -> {
                        levelFile[y][x] = 'H';
                    }
					case 4 -> {
                        levelFile[y][x] = 'Z';
                    }
					
					default ->
					throw new IllegalArgumentException("Unexpected value: " + random);
					}
				}
			}
		}
	}
	
	public void resetEditor() {
		levelFile = new char[GameConstants.ROWS][GameConstants.COLS];
		for (int i = 0; i < GameConstants.ROWS; i++) {
			levelFile[i][0] = '1';
			levelFile[i][GameConstants.COLS - 1] = '1';
		}
		x = 1;
		y = 0;
        tileSelected = false;
        addTile = false;
	}
	
	public void setTileSelected(boolean tileSelected) {
		this.tileSelected = tileSelected;
	}
	
	public void setTile(int num) {
		tileNum = num;
		switch (num) {
		case 0 -> {
			try {
				tile = ImageIO.read(getClass().getResource("/sprites/Tiles/LevelTiles-60.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		case 1 -> {
			try {
				tile = ImageIO.read(getClass().getResource("/sprites/Tiles/LevelTiles-134.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
		case 2 -> {
			try {
				tile = ImageIO.read(getClass().getResource("/sprites/Tiles/LevelTiles-40.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		case 3 -> {
			try {
				tile = ImageIO.read(getClass().getResource("/sprites/Tiles/LevelTiles-229.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		case 4 -> {
			try {
				tile = ImageIO.read(getClass().getResource("/sprites/Tiles/LevelTiles-39.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
		case 5 -> {
			try {
				tile = ImageIO.read(getClass().getResource("/sprites/Tiles/LevelTiles-55.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
		case 6 -> {
			try {
				tile = ImageIO.read(getClass().getResource("/sprites/Tiles/LevelTiles-106.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
		case 7 -> {
			try {
				tile = ImageIO.read(getClass().getResource("/sprites/Tiles/LevelTiles-88.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
		case 8 -> {
			try {
				tile = ImageIO.read(getClass().getResource("/sprites/Tiles/LevelTiles-67.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
		case 9 -> {
			try {
				tile = ImageIO.read(getClass().getResource("/sprites/Tiles/LevelTiles-65.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
        }

		default ->
		throw new IllegalArgumentException("Unexpected value: " + num);
		}
	}
	
	public void setAddTile(boolean addTile) {
		this.addTile = addTile;
	}
	
	public BufferedImage getTile() {
		return tile;
	}
	
	public int getTileNum() {
		return tileNum;
	}
}
