package sinius.maze.state.playMode;

import java.awt.Graphics2D;

import sinius.maze.Block;
import sinius.maze.Game;
import sinius.maze.entitys.Spawn;
import sinius.maze.state.GrapicsLayer;

public class Maze implements GrapicsLayer{

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
		
		Spawn s = Game.level.getSpawn();
		graphics.drawImage(s.getFont(), s.getX()*Game.ppb_x, s.getY()*Game.ppb_y, Game.ppb_x, Game.ppb_y, null);

	}

	@Override
	public int priority() {
		return 0;
	}

}
