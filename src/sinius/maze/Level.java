package sinius.maze;

import java.awt.Color;
import java.awt.image.BufferedImage;

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
	private SynchroniezedList entitys = new SynchroniezedList();
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
		spawn.Create(12, 12, "");
	}
	
	public Block getBlock(int x, int y){
		try{
			return this.blocks[x][y];
		}catch(ArrayIndexOutOfBoundsException e){
			e.printStackTrace();
		}
		return null;
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

	public SynchroniezedList getEntitys() {
		return entitys;
	}
}
