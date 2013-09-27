package sinius.maze.state.pause;

import java.awt.Graphics2D;

import sinius.maze.lib.Layout;
import sinius.maze.state.GrapicsLayer;

public class Layer_Back implements GrapicsLayer{
	
	@Override
	public String getName() {
		return "pause back layer";
	}

	@Override
	public void Draw(Graphics2D graphics) {
		graphics.setColor(Layout.OVERLAY_COLOR);
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
