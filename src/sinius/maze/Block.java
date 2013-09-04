package sinius.maze;

import java.awt.Color;
import java.awt.Rectangle;

public class Block {
	
public static final int AIR = 0;
public static final int WALL = 1;

private int type;
private Color color = Color.white;
private int x, y;
private Rectangle r;
	
	public Block(int type, int x, int y, int ppb_x, int ppb_y){
		this.x = x;
		this.y = y;
		this.setType(type);
		this.r = new Rectangle(x * ppb_x, y*ppb_x, ppb_x, ppb_y);
	}
	
	public Rectangle getCollisionBox(){
		return r ;
	}
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
}
