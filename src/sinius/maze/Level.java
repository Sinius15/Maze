package sinius.maze;

import java.awt.Color;
import java.awt.image.BufferedImage;

import sinius.maze.core.SynchroniezedList;
import sinius.maze.core.SynchroniezedList.editAction;
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
	private SynchroniezedList<Entity> entitys = new SynchroniezedList<Entity>();
	private Spawn spawn;
	
	private boolean out = false;
	
	
	public Level(int width, int height, String name){
		this.width = width;
		this.height = height;
		this.name = name;
		blocks = new Block[width][height];
		for(int w = 0; w < width; w++){
			for(int h = 0; h < height; h++){
				blocks[w][h] = new Block(Block.AIR, w, h);
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
	
	public Block[][] getBlocks(){
		return blocks;
	}
	
	public boolean isPixelWall(int x, int y){
		if(getBlock(x/(800/getWidth()), y/(800/getHeight())).getType() == Block.WALL)
			return true;
		
		return false;
	}
	
	public boolean isEntityOnCoord(final int x, final int y){
		out = false;
		Game.get().level.getEntitys().doForAll(new editAction<Entity>() { @Override public void action(Entity e) {
				if(e.onGrid())
					if(e.getX()*Game.get().ppb_x == x && e.getY()*Game.get().ppb_y == y)
						out = true;
				if(!e.onGrid())
					if(e.getX() == x && e.getY() == y)
						out = true;
			}
		});
		
		return out;
	}
	
	public boolean isEntityOnBlock(final int x, final int y){
		out = false;
		Game.get().level.getEntitys().doForAll(new editAction<Entity>() { @Override public void action(Entity e) {
				if(e.onGrid())
					if(x == e.getX() && y == e.getY())
						out = true;
				if(!e.onGrid())
					if(e.getX()/Game.get().ppb_x == x && e.getY()/Game.get().ppb_y == y)
						out = true;
			}
		});
		
		return out;
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

	public SynchroniezedList<Entity> getEntitys() {
		return entitys;
	}
}
