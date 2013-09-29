package sinius.maze.plugin.mob;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import sinius.maze.Entity;
import sinius.maze.Game;
import sinius.maze.Level;
import sinius.maze.entitys.Player;
import sinius.maze.lib.Folders;

public class KungfuPanda implements Entity{

	int x, y, ph;
	Rectangle boundery;
	Image img;
	
	@Override
	public void Create(int x, int y, String saveData) {
		this.x = x;
		this.y = y;
		if(saveData.equals(""))
			this.ph = 100;
		else
			this.ph = Integer.parseInt(saveData);
		boundery = new Rectangle(x, y, Game.get().ppb_x, Game.get().ppb_y);
		try {
			img = ImageIO.read(new File(Folders.RES.getAbsolutePath() + "/panda.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public String getName() {
		return "kung fu panda";
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
	public void advancedRender(Graphics2D graphics) {
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
		return img;
	}

	@Override
	public Rectangle getCollisionBox() {
		return boundery;
	}

	@Override
	public String getSaveData() {
		return String.valueOf(ph);
	}

}
