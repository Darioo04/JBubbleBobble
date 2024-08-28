package model;

public class Wall {
	
	private String path;
	
	public Wall(int level) {
        path = "/sprites/Tiles/LevelTiles-"+(level-1)+".png";
	}
	
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	} 
}
