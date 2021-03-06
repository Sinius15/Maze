package sinius.maze.state.solve;

import java.awt.Graphics2D;

import sinius.maze.Game;
import sinius.maze.lib.Layout;
import sinius.maze.state.GrapicsLayer;

public class Layer_Path implements GrapicsLayer{

	boolean[][] path;
	
	public Layer_Path(boolean[][] path){
		this.path = path;
	}
	
	@Override
	public String getName() {
		return "path";
	}

	@Override
	public void Draw(Graphics2D graphics) {
		graphics.setColor(Layout.OVERLAY_COLOR);
		for(int x = 0; x < path.length ; x++){
			for(int y = 0; y < path[0].length ; y++){
				if(path[x][y])
					graphics.fillRect(x*Game.get().ppb_x, y*Game.get().ppb_y, Game.get().ppb_x, Game.get().ppb_y);
			}
		}
		
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
