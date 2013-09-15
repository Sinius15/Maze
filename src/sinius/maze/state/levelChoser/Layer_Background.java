package sinius.maze.state.levelChoser;

import java.awt.Color;
import java.awt.Graphics2D;

import sinius.maze.state.GrapicsLayer;

public class Layer_Background implements GrapicsLayer{
	
	@Override
	public String getName() {
		return "background";
	}

	@Override
	public void Draw(Graphics2D graphics) {
		graphics.setColor(Color.black);
		graphics.fillRect(0, 0, 800, 800);
	}

	@Override
	public int priority() {
		return 0;
	}

}
