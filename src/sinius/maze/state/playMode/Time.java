package sinius.maze.state.playMode;

import java.awt.Color;
import java.awt.Graphics2D;

import sinius.maze.Game;
import sinius.maze.state.GrapicsLayer;

public class Time implements GrapicsLayer{

	@Override
	public String getName() {
		return null;
	}

	@Override
	public void Draw(Graphics2D graphics) {
		graphics.setColor(Color.black);
		graphics.drawString(Game.timer.getTime(), 100, 100);
	}

	@Override
	public int priority() {
		return 5;
	}

	
	
}
