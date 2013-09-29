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

public class Teleporter implements Entity {

	public int x, y, toX=-1, toY=-1;
	Image img1, img2;
	public String type;
	
	Rectangle r;
	
	@Override
	public void Create(int x, int y, String saveData) {
		this.x = x;
		this.y = y;
		if(saveData.equals("")){
			this.type = "in";
		}else{
			this.toX = Integer.parseInt(saveData.split(",")[0]);
			this.toY = Integer.parseInt(saveData.split(",")[1]);
			this.type = saveData.split(",")[2];
		}
		
		try {
			img1 = ImageIO.read(new File(Folders.RES.getAbsolutePath() + "/Teleporter_1.png"));
			img2 = ImageIO.read(new File(Folders.RES.getAbsolutePath() + "/Teleporter_2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		r = new Rectangle(x*Game.get().ppb_x, y*Game.get().ppb_y, Game.get().ppb_x, Game.get().ppb_y);
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
	public void onPlayerTouch(Player p) {
		Game.get().display.camera.setLocation(p.getX(), p.getY());
		if(type.equals("in")){
			p.setX(toX*Game.get().ppb_x+Game.get().ppb_x/2);
			p.setY(toY*Game.get().ppb_y+Game.get().ppb_y/2);
		}
	}

	@Override
	public void onTick(Level l) {
	}

	@Override
	public void advancedRender(Graphics2D graphics) {
		if(Game.get().display.gameState.getName().equals("editor") && type.equals("in")){
			graphics.drawLine(x*Game.get().ppb_x+Game.get().ppb_x/2, y*Game.get().ppb_y+Game.get().ppb_y/2, toX*Game.get().ppb_x+Game.get().ppb_x/2, toY*Game.get().ppb_y+Game.get().ppb_y/2);
		}
	}

	@Override
	public boolean onGrid() {
		return true;
	}

	@Override
	public Dimension getSize() {
		return null;
	}

	@Override
	public Image getFont() {
		if(type.equals("in"))
			return img1;
		return img2;
	}

	@Override
	public Rectangle getCollisionBox() {
		return r;
	}

	@Override
	public String getSaveData() {
		return toX + "," + toY + "," + type;
	}

	

}
