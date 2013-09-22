package sinius.maze.state.playMode;

import java.awt.Dimension;
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
		Dimension d = Game.get().display.camera.getBlockSize();
		try {
			graphics.drawImage(p.getFont(), point.x-(d.width/2), point.y-(d.height/2), d.width, d.height, null);
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
