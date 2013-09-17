package sinius.maze.gameEngine;

import java.awt.Point;
import java.awt.Rectangle;


public class Camera {

	private Rectangle rec;
	
	public Camera(){
		rec = new Rectangle(0, 0, 800, 800);
	}
	
	public Point getP1(){
		Point p = new Point();
		p.setLocation(rec.getX(), rec.getY());
		return p;
	}
	
	public Point getP2(){
		Point p = new Point();
		p.setLocation(rec.getX() + rec.getWidth(), rec.getY() + rec.getHeight());
		return p;
	}
	
	/**
	 * if you want to change the location and the size, make sure you set the size first!
	 * @param x
	 * @param y
	 */
	public void setLocation(int x, int y){
		rec.setLocation(x-rec.width/2, y-rec.height/2);
		if(getP1().x < 0)
			rec.setLocation(0, (int) rec.getY());
		if(getP1().y < 0)
			rec.setLocation((int) rec.getX() , 0);
		if(getP2().x > 800)
			rec.setLocation(800-(int)rec.getWidth(), (int)rec.getY());
		if(getP2().y > 800)
			rec.setLocation((int)rec.getX(), 800-(int)rec.getHeight());
			
	}
	
	public void setSize(int w, int h){
		rec.setSize(w, h);
	}
	
	public int getX(){
		return rec.x + rec.width/2;
	}
	
	public int getY(){
		return rec.y + rec.height/2;
	}
}
