package sinius.maze.state.solve;

import java.awt.Graphics2D;
import java.awt.Point;

import sinius.maze.Game;
import sinius.maze.plugin.Exit;
import sinius.maze.state.GrapicsLayer;
import sinius.maze.state.StatsOverlay;

public class Layer_Path implements GrapicsLayer{

	boolean[][] path;
	Exit exit = new Exit();
	
	public Layer_Path(boolean[][] path, Point out){
		this.path = path;
		exit.Create(out.x, out.y, "");
	}
	
	@Override
	public String getName() {
		return "path";
	}

	@Override
	public void Draw(Graphics2D graphics) {
		graphics.setColor(StatsOverlay.gray);
		for(int x = 0; x < path.length ; x++){
			for(int y = 0; y < path[0].length ; y++){
				if(path[x][y])
					graphics.fillRect(x*Game.get().ppb_x, y*Game.get().ppb_y, Game.get().ppb_x, Game.get().ppb_y);
			}
		}
		graphics.drawImage(exit.getFont(), exit.getX()*Game.get().ppb_x, exit.getY()*Game.get().ppb_y, Game.get().ppb_x, Game.get().ppb_y, null);
		
	}

	@Override
	public int priority() {
		return 1;
	}

	@Override
	public boolean drawAfter() {
		return false;
	}

}
