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

public class Teleporter implements Entity{

	int x, y, toX, toY;
	Image img;
	Rectangle rec;
	
	@Override
	public void Create(int x, int y, String saveData) {
		this.x = x;
		this.y = y;
		try {
			img = ImageIO.read(new File(MainProgram.SAVEMAP + "/res/Teleporter.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		rec = new Rectangle(x * Game.ppb_x , y * Game.ppb_y, Game.ppb_x, Game.ppb_y);
		toX = Integer.parseInt(saveData.split(",")[0]);
		toY = Integer.parseInt(saveData.split(",")[1]);
	}

	@Override
	public String getName() {
		return "Teleporter";
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
	public Image getFont(){
		return img;
	}

	@Override
	public void onPlayerTouch() {
		MainProgram.game.getPlayer().setX(toX);
		MainProgram.game.getPlayer().setY(toY);
	}

	@Override
	public void onTick(Level l) {}

	@Override
	public Rectangle getCollisionBox() {
		return rec;
	}

	@Override
	public String getSaveData() {
		return toX + "," + toY;
	}

	
	
}
