package sinius.maze.drawing;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;

import sinius.maze.Block;
import sinius.maze.Game;
import sinius.maze.Level;
import sinius.maze.MainProgram;
import sinius.maze.entitys.Player;

public class Drawer {

	public static Graphics2D graphics;
	
	public static void draw(Level l){
		l.editEntitys("draw", graphics, null, null, 0, 0, null);
	}
	
	public static void drawPlayer(Level l) {
		int ppb_x = 800 / l.getWidth();
		int ppb_y = 800 / l.getHeight();
		
		Player p = MainProgram.game.getPlayer();
		try {
			graphics.drawImage(p.getFont(), p.getX()-ppb_x/2, p.getY()-ppb_y/2, ppb_x, ppb_y, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void drawMaze(Level l){
		graphics.setColor(l.getBackgroundColor());
		graphics.fillRect(0, 0, 800, 800);
		for(int x = 0; x < l.getWidth(); x++){
			for(int y = 0; y< l.getHeight(); y++){
				if(l.getBlock(x, y).getType() == Block.WALL){
					graphics.setColor(l.getBlock(x, y).getColor());
					graphics.fillRect(x * Game.ppb_x, y * Game.ppb_y, Game.ppb_x, Game.ppb_y);
					
				}
				
			}
		}
	}
	
	public static void drawTimer(){
		graphics.setColor(Color.black);
		graphics.drawString(Game.timer.getTime(), 50, 50);
	}
	
}
