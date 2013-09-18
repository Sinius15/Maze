package sinius.maze.state.playMode;

import java.awt.Graphics2D;

import sinius.maze.Block;
import sinius.maze.Game;
import sinius.maze.entitys.Spawn;
import sinius.maze.state.GrapicsLayer;

public class Layer_Maze implements GrapicsLayer{

	@Override
	public String getName() {
		return "play";
	}

	@Override
	public void Draw(Graphics2D graphics) {
		graphics.setColor(Game.get().level.getBackgroundColor());
		graphics.fillRect(0, 0, 800, 800);
		for(int x = 0; x < Game.get().level.getWidth(); x++){
			for(int y = 0; y< Game.get().level.getHeight(); y++){
				if(Game.get().level.getBlock(x, y).getType() == Block.WALL){
					graphics.setColor(Game.get().level.getBlock(x, y).getColor());
					graphics.fillRect(x * Game.get().ppb_x, y * Game.get().ppb_y, Game.get().ppb_x, Game.get().ppb_y);
				}
				
			}
		}
		
		Spawn s = Game.get().level.getSpawn();
		graphics.drawImage(s.getFont(), s.getX()*Game.get().ppb_x, s.getY()*Game.get().ppb_y, Game.get().ppb_x, Game.get().ppb_y, null);

	}

	@Override
	public int priority() {
		return 0;
	}

}
