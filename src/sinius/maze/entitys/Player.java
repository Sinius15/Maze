package sinius.maze.entitys;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;

import javax.imageio.ImageIO;

import sinius.maze.Entity;
import sinius.maze.Game;
import sinius.maze.Level;
import sinius.maze.MainProgram;

public class Player implements Entity{

	private int x, y;
	int speed;
	private String lastMove = "up";
	
	Image upImg, downImg, leftImg, rightImg;
	
	@Override
	public void Create(int x, int y, String saveData) {
		this.x = x;
		this.y = y;
		speed =  3;
		try {
			upImg	= ImageIO.read(new File(MainProgram.SAVEMAP + "/res/Player_Up.png"));
			downImg = ImageIO.read(new File(MainProgram.SAVEMAP + "/res/Player_Down.png"));
			leftImg = ImageIO.read(new File(MainProgram.SAVEMAP + "/res/Player_Left.png"));
			rightImg= ImageIO.read(new File(MainProgram.SAVEMAP + "/res/Player_Right.png"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getName() {
		return "player";
	}

	public void up(Level l){
		lastMove = "up";
		int x1 = (x - Game.ppb_x/2)+2;
		int y0 = ((y-speed) - Game.ppb_y/2)+2;
		int x2 = (x + Game.ppb_x/2)-2;
		boolean wall = false;
		if(y0<=0)
			return;
		for(int i = x1; i < x2; i++)
			if(l.isPixelWall(i, y0))
				wall = true;
		if(!wall)
			y = y-speed;
	}
	
	public void down(Level l){
		lastMove = "down";
		int x1 = (x - Game.ppb_x/2)+2;
		int y0 = ((y+speed) + Game.ppb_y/2)-2;
		int x2 = (x + Game.ppb_x/2)-2;
		boolean wall = false;
		if(y0 >= 800)
			return;
		for(int i = x1; i < x2; i++)
			if(l.isPixelWall(i, y0))
				wall = true;
		if(!wall)
			y = y + speed;
	}
	
	public void left(Level l){
		lastMove = "left";
		int y1 = (y - Game.ppb_y/2)+2;
		int y2 = (y + Game.ppb_y/2)-2;
		int x0 = ((x -speed) - Game.ppb_x/2)+2;
		boolean wall = false;
		if(x0<=0)
			return;
		for(int i = y1; i < y2; i++)
			if(l.isPixelWall(x0, i))
				wall = true;
		if(!wall)
			x = x - speed;
	}
	
	public void right(Level l){
		lastMove = "right";
		int y1 = (y - Game.ppb_y/2)+2;
		int y2 = (y + Game.ppb_y/2)-2;
		int x0 = ((x +speed) + Game.ppb_x/2)-2;
		boolean wall = false;
		if(x0 >=800)
			return;
		for(int i = y1; i < y2; i++)
			if(l.isPixelWall(x0, i))
				wall = true;
		if(!wall)
			x = x + speed;
	}
	
	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public void onPlayerTouch(Player p) {
	}

	@Override
	public void onTick(Level l) {
	}

	@Override
	public boolean onGrid() {
		return false;
	}

	@Override
	public Dimension getSize() {
		return null;
	}

	@Override
	public Image getFont() {
		if(lastMove.equals("up"))
			return upImg;
		if(lastMove.equals("down"))
			return downImg;
		if(lastMove.equals("left"))
			return leftImg;
		else
			return rightImg;
	}

	@Override
	public Rectangle getCollisionBox() {
		return new Rectangle(x-Game.ppb_x/2, y-Game.ppb_y/2, Game.ppb_x, Game.ppb_y);
	}

	@Override
	public String getSaveData() {
		return null;
	}

	public void setX(int x){
		this.x = x;
	}
	public void setY(int y){
		this.y = y;
	}

	@Override
	public void advancedRender(Graphics2D graphics) {
	}

}
