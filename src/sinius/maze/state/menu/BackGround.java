package sinius.maze.state.menu;

import java.awt.Color;
import java.awt.Graphics2D;

import sinius.maze.state.GrapicsLayer;

public class BackGround implements GrapicsLayer{

	@Override
	public String getName() {
		return "Menu_Background";
	}

	@Override
	public void Draw(Graphics2D graphics) {
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, 800, 800);
	}

	@Override
	public int priority() {
		return 0;
	}

	
	
}
