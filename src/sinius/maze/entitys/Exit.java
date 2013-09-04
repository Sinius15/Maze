package sinius.maze.entitys;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import sinius.maze.Entity;
import sinius.maze.Game;
import sinius.maze.Level;
import sinius.maze.MainProgram;

public class Exit implements Entity{

	private int x, y;
	private Rectangle r;
	private Image img;
		
	@Override
	public void Create(int x, int y) {
		this.x = x;
		this.y = y;
		r = new Rectangle(x*Game.ppb_x, y*Game.ppb_y, Game.ppb_x, Game.ppb_y);
		try {
			img = ImageIO.read(new File(MainProgram.SAVEMAP + "/res/Exit.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String getName() {
		return "EndPoint";
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
		return img;
	}

	@Override
	public void onPlayerTouch() {
		Game.isFinihed = true;
		Game.timer.Stop();
		
	}

	@Override
	public void onTick(Level l) {}

	@Override
	public Rectangle getCollisionBox() {
		return r;
	}


	


}
