package sinius.maze.state.playMode;

import java.awt.Graphics2D;

import sinius.maze.Game;
import sinius.maze.entitys.Player;
import sinius.maze.state.GrapicsLayer;

public class Layer_Player implements GrapicsLayer{

	@Override
	public String getName() {
		return "player";
	}

	@Override
	public void Draw(Graphics2D graphics) {
		Player p = Game.get().player;
		try {
			graphics.drawImage(p.getFont(), p.getX()-Game.get().ppb_x/2, p.getY()-Game.get().ppb_y/2, Game.get().ppb_x, Game.get().ppb_y, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int priority() {
		return 3;
	}

}
