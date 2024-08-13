package model;

public class Wall extends Tile  {
	
	public Wall(int level) {
		super(true);
        setPath("/sprites/Tiles/LevelTiles-"+(level-1)+".png");
	}
}
