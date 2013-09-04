package sinius.maze.entitys;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import sinius.maze.Entity;
import sinius.maze.Level;
import sinius.maze.MainProgram;

public class Spawn implements Entity{

	private int x, y;
	
	@Override
	public void Create(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public String getName() {
		return "End Point";
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
	public Image getFont() throws IOException {
		return ImageIO.read(new File(MainProgram.SAVEMAP + "/rec/Spawn.png"));
	}

	@Override
	public void onPlayerTouch() {}

	@Override
	public void onTick(Level l) {}

	@Override
	public Rectangle getCollisionBox() {
		return null;
	}

	


}
