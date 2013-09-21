package sinius.maze.state.pause;

import java.awt.Graphics2D;

import sinius.maze.state.GrapicsLayer;
import sinius.maze.state.StatsOverlay;

public class Layer_Back implements GrapicsLayer{
	
	@Override
	public String getName() {
		return "pause back layer";
	}

	@Override
	public void Draw(Graphics2D graphics) {
		graphics.setColor(StatsOverlay.gray);
		graphics.fillRect(0, 0, 800, 800);
	}

	@Override
	public int priority() {
		return 9;
	}

	@Override
	public boolean drawAfter() {
		return false;
	}

}
