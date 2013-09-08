package sinius.maze.graphicsLayer;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import sinius.maze.Game;
import sinius.maze.GraphicsLayer;

public class Time implements GraphicsLayer{

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
	public void mouseClick(MouseEvent e) {
	}

	@Override
	public int priority() {
		return 5;
	}

	
	
}
