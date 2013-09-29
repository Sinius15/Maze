package sinius.maze.plugin;

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
import sinius.maze.state.finish.FinishState;
import sinius.maze.state.playMode.PlayState;

public class Exit implements Entity{

	private int x, y;
	private Rectangle r;
	private Image img;
		
	@Override
	public void Create(int x, int y, String saveData) {
		this.x = x;
		this.y = y;
		r = new Rectangle(x*Game.get().ppb_x, y*Game.get().ppb_y, Game.get().ppb_x, Game.get().ppb_y);
		try {
			img = ImageIO.read(new File(Folders.RES.getAbsolutePath() + "/Exit.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String getName() {
		return "Exit";
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
	public void onPlayerTouch(Player p) {
		
		Game.get().display.setGameState(new FinishState(((PlayState)Game.get().display.gameState).getTimer().getTime()));
	}

	@Override
	public void onTick(Level l) {
	}

	@Override
	public Rectangle getCollisionBox() {
		return r;
	}

	@Override
	public String getSaveData() {
		return null;
	}

	@Override
	public void advancedRender(Graphics2D graphics) {
	}

	@Override
	public boolean onGrid() {
		return true;
	}

	@Override
	public Dimension getSize() {
		return null;
	}


	


}
