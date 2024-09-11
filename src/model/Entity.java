package model;

import java.awt.Rectangle;
import java.util.Observable;

import controller.LevelCreator;

@SuppressWarnings("deprecation")

public abstract class Entity extends Observable {
	
	protected Direction direction; //
	protected CollisionChecker collisionChecker;
	protected int x, y, col, row; //coordinate dell'entità
	private int spawnX; // coordinata x di spawn
	private int spawnY; // coordinata y di spawn
	protected String path; // percorso della cartella in cui sono presenti le animazioni dell'entità
	private boolean isDead; //indica se l'entità è morta
	private boolean isMoving; //indica che l'entità si sta muovendo
	protected int speed; //velocità dell'entità
	protected int fallingSpeed; //velocita di caduta
	public static final int GRAVITY = 1 * GameConstants.SCALE; // Gravità costante
	protected char[][] levelFile; //livello
	
	protected int deathCounter; //timer di morte dell'entità
	
	/*
	 * hitbox dell'entità
	 */
	protected Rectangle hitbox;
	protected int hitboxWidth;
	protected int hitboxHeight;
	protected int hitboxOffsetX;
    protected int hitboxOffsetY;
    
    /**
     * questi campi indicano se l'entità entra in collisione con un altro elemento di gioco
    */
	protected boolean collisionDown;
	protected boolean collisionLeft;
	protected boolean collisionRight;
	protected boolean collisionUp;
	
	private int numIdleSprites; //numero di sprite da fermo
	private int numRunningSprites; //numero di sprite in movimento
	private int numJumpingSprites; //numero di sprite mentre salta
	private int numFallingSprites; //numero di sprite mentre cade
	
	public Entity() {
		collisionChecker = CollisionChecker.getInstance();
		levelFile = LevelCreator.getInstance().getLevel();
	}
	
	public Direction getDirection() {
		return direction;
	}
	public void setDirection(Direction direction) {
		this.direction=direction;
	}
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path=path;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
        return y;
    }
	
	public void setX(int x) {
        this.x = x;
    }
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getSpawnX() {
		return spawnX;
	}
	
	public int getSpawnY() {
		return spawnY;
	}
	
	public void setSpawnX(int spawnX) {
		this.spawnX=spawnX;
	}
	
	public void setSpawnY(int spawnY) {
		this.spawnY=spawnY;
	}
	
	public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
        if (x < 0) x = 0;
        if (x > GameConstants.SCREEN_WIDTH) x = GameConstants.SCREEN_WIDTH-1;
        if (y < 0) y = 0;
        if (y > GameConstants.SCREEN_HEIGHT) y = GameConstants.SCREEN_HEIGHT-1;
        update();
    }
	
	public boolean isDead() {
        return isDead;
    }
	
	public void setDead(boolean isDead) {
        this.isDead = isDead;
        
    }
	
	public void update() {
		setChanged();
        notifyObservers();
	}
	
	public void update(Object arg) {
		setChanged();
		notifyObservers(arg);
	}
	
	public Rectangle getHitbox() {
        return hitbox;
    }
	
	public void setHitboxWidth(int hitboxWidth) {
        this.hitboxWidth = hitboxWidth;
    }
	
	public void updateHitbox() {
		setHitboxX(x + hitboxOffsetX);
		setHitboxY(y + hitboxOffsetY);
	}
	
	public int getHitboxWidth() {
		return hitboxWidth;
    }
	
	public void setHitboxHeight(int hitboxHeight) {
        this.hitboxHeight = hitboxHeight;
    }
	
	public int getHitboxHeight() {
        return hitboxHeight;
    }
	
	public void setHitboxOffsetX(int hitboxOffsetX) {
        this.hitboxOffsetX = hitboxOffsetX;
    }
	
	public void setHitboxOffsetY(int hitboxOffsetY) {
        this.hitboxOffsetY = hitboxOffsetY;
    }
	
	public void setHitbox(Rectangle hitBox) {
        this.hitbox = hitBox;
    }
	
	public void setHitboxX(int x){
        hitbox.x = x;
    }
	
    public void setHitboxY(int y){
        hitbox.y = y;
    }
    
    public int getHitboxX() {
    	return hitbox.x;
    }
    
    public int getHitboxY() {
        return hitbox.y;
    }
    
    public boolean getCollisionDown() {
    	return collisionDown;
    }
    
    public void setCollisionDown(boolean collisionDown) {
        this.collisionDown = collisionDown;
    }
    
    public boolean getCollisionLeft() {
        return collisionLeft;
    }
    
    public void setCollisionLeft(boolean collisionLeft) {
        this.collisionLeft = collisionLeft;
    }
    
    public boolean getCollisionRight() {
        return collisionRight;
    }
    
    public void setCollisionRight(boolean collisionRight) {
        this.collisionRight = collisionRight;
    }
    
    public boolean getCollisionUp() {
        return collisionUp;
    }
    
    public void setCollisionUp(boolean collisionUp) {
        this.collisionUp = collisionUp;
    }
    
    public boolean isMoving() {
        return isMoving;
    }
    
    public void setMoving(boolean isMoving) {
        this.isMoving = isMoving;
    }
    
    public int getSpeed() {
    	return speed;
    }
    public void setSpeed(int speed) {
    	this.speed=speed;
    }
    
    public int getFallingSpeed() {
    	return fallingSpeed;
    }
    public void setFallingSpeed(int fallingSpeed) {
    	this.fallingSpeed=fallingSpeed;
    }
	
	public void setNumIdleSprites(int numIdleSprites) {
		this.numIdleSprites=numIdleSprites;
	}
	
	public int getNumIdleSprites() {
		return numIdleSprites;
	}
	
	public void setNumRunningSprites(int numRunningSprites) {
		this.numRunningSprites = numRunningSprites;
	}
	
	public int getNumRunningSprites() {
		return numRunningSprites;
	}
	
	public void setNumJumpingSprites(int numJumpingSprites) {
		this.numJumpingSprites = numJumpingSprites;
	}
	
	public int getNumJumpingSprites() {
		return numJumpingSprites;
	}
	
	public void setNumFallingSprites(int numFallingSprites) {
		this.numFallingSprites = numFallingSprites;
	}
	
	public int getNumFallingSprites() {
		return numFallingSprites;
	}

}