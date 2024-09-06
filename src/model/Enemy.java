package model;

import java.awt.Rectangle;
import java.util.Timer;
import java.util.TimerTask;

import view.EnemyView;

@SuppressWarnings("deprecation")

public abstract class Enemy extends Entity {
	
	protected Player player;
	protected CollisionChecker collisionChecker;
	private boolean inBubble;
	private boolean isJumping;
	private boolean isChasingPlayer = false;
	private boolean bubbleExploded;
	private EnemyView enemyView;
	private boolean canBeDeleted;
	private boolean isFrozen;
	protected int scoreWhenKilled;
	
	private int rebornCounter;
	private final int REBORN_DELAY = 600;
	
	public Enemy(int x, int y) {
		this.x = x;
		this.y = y;
		setSpawnX(x);
		setSpawnY(y);
		player = Player.getInstance();
		collisionChecker = CollisionChecker.getInstance();
		hitboxOffsetX = GameConstants.SCALE;
		hitboxOffsetY = GameConstants.SCALE;
		hitboxHeight = GameConstants.ENEMY_SIZE - 2*GameConstants.SCALE;
		hitboxWidth = GameConstants.ENEMY_SIZE - 2*GameConstants.SCALE;
		setHitbox(new Rectangle(x + hitboxOffsetX, y + hitboxOffsetY, hitboxWidth, hitboxHeight));	
		
		setMoving(true);
	}
	
	public void setInBubble(boolean inBubble) {
		this.inBubble=inBubble;
	}
	
	public boolean isInBubble() {
		return inBubble;
	}
	
	public void setJumping(boolean isJumping) {
		this.isJumping = isJumping;
	}
	
	public boolean isJumping() {
		return isJumping;
	}
	
	public void setIsChasingPlayer(boolean isChasingPlayer) {
		this.isChasingPlayer=isChasingPlayer;
	}
	
	public boolean isChasingPlayer() {
		return isChasingPlayer;
	}
	
	public void setBubbleExploded(boolean bubbleExploded) {
		this.bubbleExploded = bubbleExploded;
	}
	
	public boolean getBubbleExploded() {
		return bubbleExploded;
	}
	
	public void setFrozen() {
		isFrozen = true;
	}
	
	public void setUnfrozen() {
		isFrozen = false;
	}
	
	public boolean isFrozen() {
		return isFrozen;
	}
	
	public EnemyView getEnemyView() {
        return enemyView;
    }
	
	public void setEnemyView(EnemyView enemyView) {
        this.enemyView = enemyView;
    }
	
	public void setCanBeDeleted(boolean canBeDeleted) {
        this.canBeDeleted = canBeDeleted;
    }
	
	public boolean getCanBeDeleted() {
        return canBeDeleted;
    }
	
	public int getScoreWhenKilled() {
        return scoreWhenKilled;
    }
	
	@Override
	public void update() {
		CollisionChecker.getInstance().checkTileCollision(this);
		if (inBubble) {
			setY((int)(getY()-GameConstants.BUBBLE_FLOATING_SPEED));
			if (y < GameConstants.TILE_SIZE) y = GameConstants.TILE_SIZE;
			setIsChasingPlayer(false);
			setMoving(false);
			if (rebornCounter == 0) {
				rebornCounter = 1;
			}
		}
		else if (bubbleExploded) {
			if (!getCollisionDown()) y += GameConstants.OBJECT_SPEED;
			if (getCollisionDown()) {
				setDead(true);
				
				if (deathCounter == 0) {
	                deathCounter = 1;
	            }
	        }
	    }
	    
	    if (deathCounter > 0) {
	        deathCounter++;
	        if (deathCounter >= DEATH_DELAY) {
	            setCanBeDeleted(true);
	        }
	    }
	    
	    if (rebornCounter > 0) {
	    	rebornCounter++;
	    	if (rebornCounter >= REBORN_DELAY && !bubbleExploded) {
	    		rebornCounter = 0;
	    		setInBubble(false);
	    		setBubbleExploded(false);
	    		setMoving(true);
	    	}
	    }
	}
	
}
