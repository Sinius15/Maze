package sinius.maze;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import sinius.maze.entitys.Spawn;

public class Level {

	public static final int SIZE_SMALL = 25;
	public static final int SIZE_NORMAL = 50;
	public static final int SIZE_LARGE = 100;
	
	private int width, height;
	private String name;
	private BufferedImage logo;
	private Color backgroundColor = Color.black;
	private Color standardBlockColor = Color.white;
	
	private Block[][] blocks;
	private ArrayList<Entity> entitys = new ArrayList<Entity>();
	private ArrayList<Entity> removeEntitys = new ArrayList<Entity>();
	private Spawn spawn;
	
	
	public Level(int width, int height, String name){
		this.width = width;
		this.height = height;
		this.name = name;
		blocks = new Block[width][height];
		for(int w = 0; w < width; w++){
			for(int h = 0; h < height; h++){
				blocks[w][h] = new Block(Block.AIR, w, h, 800/width, 800/height);
			}
		}
		spawn = new Spawn();
		spawn.Create(12, 12);
	}
	
	public Block getBlock(int x, int y){
		try{
			return this.blocks[x][y];
		}catch(ArrayIndexOutOfBoundsException e){
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean editEntityReturner;
	
	@SuppressWarnings("rawtypes")
	public synchronized void editEntitys(String what, Graphics2D graphics, Entity e, Runnable r, int x, int y, Class s){
		if(what.equals("draw")){
			try {
				
				for(Entity f : entitys){
						graphics.drawImage(f.getFont(), f.getX()*Game.ppb_x, f.getY()*Game.ppb_y, Game.ppb_x, Game.ppb_y, null);
				}
				
				graphics.drawImage(getSpawn().getFont(), getSpawn().getX()*Game.ppb_x, getSpawn().getY()*Game.ppb_y,Game.ppb_x, Game.ppb_y, null);
				
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		else if(what.equals("add")){
			entitys.add(e);
		}else if(what.equals("remove")){
			for(Entity f : entitys){
				if(f.getX() == x && f.getY() == y && f.getClass().equals(s)){
					removeEntitys.add(f);
				}	
			}
		}else if(what.equals("tick")){
			for(Entity f : entitys){
				f.onTick(this);
			}
		}else if(what.equals("playerTouch")){
			for(Entity f: entitys){
				if(f.getCollisionBox().intersects(e.getCollisionBox()))
					f.onPlayerTouch();
				
			}
			
		}else if(what.equals("isEntityOnCoord")){
			for(Entity f: entitys){
				if(f.getX() == x && f.getY() == y)
					editEntityReturner = true;
				
			}
		}
		
		for(Entity f: removeEntitys){
			entitys.remove(f);
		}
		removeEntitys.clear();
	}
	
	public boolean isPixelWall(int x, int y){
		if(getBlock(x/(800/getWidth()), y/(800/getHeight())).getType() == Block.WALL)
			return true;
		
		return false;
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BufferedImage getLogo() {
		return logo;
	}

	public void setLogo(BufferedImage logo) {
		this.logo = logo;
	}

	public Color getStandardBlockColor() {
		return standardBlockColor;
	}

	public void setStandardBlockColor(Color standardBlockColor) {
		this.standardBlockColor = standardBlockColor;
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public Spawn getSpawn() {
		return spawn;
	}

	public void setSpawn(Spawn spawn) {
		this.spawn = spawn;
	}

	public ArrayList<Entity> getEntitys() {
		return entitys;
	}
}
