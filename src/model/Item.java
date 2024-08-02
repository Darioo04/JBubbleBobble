package model;

public enum Item {
	
	APPLE(500,"");
	
	
	private int point;
	private String path;
	
	Item(int point,String path) {
		this.point=point;
		this.path=path;
	}
	
	public int getPoint() {
		return point;
	}
	
	public String getPath() {
		return path;
	}
}
