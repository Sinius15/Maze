package sinius.maze.state;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import sinius.maze.Game;
import sinius.maze.MainProgram;

public class StatsOverlay implements GrapicsLayer{

	static Color gray = new Color(0f, 0f, 0f, 0.75f);
	static Font font = new Font("Zolano Serif BTN", Font.PLAIN, 18);
	
	@Override
	public String getName() {
		return "stats";
	}

	@Override
	public void Draw(Graphics2D g) {
		g.setColor(gray);
		g.fillRect(0, 0, 800, 150);	
		g.setColor(Color.white);
		g.setFont(font);
		
		g.drawString("FPS: " + Game.get().fps.getFPS(), 700, 22);
		
		g.drawString("Player:" , 10, 22);
		g.drawString("x: " + MainProgram.game.getPlayer().getX() , 15, 42);
		g.drawString("y: " + MainProgram.game.getPlayer().getY() , 15, 62);
	}

	@Override
	public int priority() {
		return 10;
	}

	@Override
	public boolean drawAfter() {
		return true;
	}

}
