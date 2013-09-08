package sinius.maze.graphicsLayer;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import sinius.maze.Block;
import sinius.maze.Game;
import sinius.maze.GraphicsLayer;

public class Maze implements GraphicsLayer{

	@Override
	public String getName() {
		return "play";
	}

	@Override
	public void Draw(Graphics2D graphics) {
		graphics.setColor(Game.level.getBackgroundColor());
		graphics.fillRect(0, 0, 800, 800);
		for(int x = 0; x < Game.level.getWidth(); x++){
			for(int y = 0; y< Game.level.getHeight(); y++){
				if(Game.level.getBlock(x, y).getType() == Block.WALL){
					graphics.setColor(Game.level.getBlock(x, y).getColor());
					graphics.fillRect(x * Game.ppb_x, y * Game.ppb_y, Game.ppb_x, Game.ppb_y);
					
				}
				
			}
		}

	}

	@Override
	public void mouseClick(MouseEvent e) {
	}
	
	@Override
	public int priority() {
		return 0;
	}

}
