package sinius.maze.state.playMode;

import java.awt.Graphics2D;
import java.awt.Point;

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
		Point point = Game.get().display.camera.getPointOnScreen(new Point(p.getX(), p.getY()));
		try {
			
			graphics.drawImage(p.getFont(), point.x-40, point.y-40, 80, 80, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int priority() {
		return 3;
	}

	@Override
	public boolean drawAfter() {
		return true;
	}

}
