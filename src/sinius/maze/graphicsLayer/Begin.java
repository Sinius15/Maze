package sinius.maze.graphicsLayer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import sinius.maze.Game;
import sinius.maze.MainProgram;
import sinius.maze.entitys.Player;
import sinius.maze.state.GrapicsLayer;

public class Begin implements GrapicsLayer{

	static Color gray = new Color(0f, 0f, 0f, 0.75f);
	static Font font = new Font("Zolano Serif BTN", Font.PLAIN, 18);
	
	@Override
	public String getName() {
		return "begin";
	}

	@Override
	public void Draw(Graphics2D graphics) {
		graphics.setColor(Color.black);
		graphics.fillRect(0, 0, 800, 800);
		graphics.setColor(Color.white);
		graphics.setFont(font);
		
		graphics.drawString("Click anywhere to start playing...", 370, 300);
	}

	@Override
	public void mouseClick(MouseEvent e) {
		Game.graManger.addLayer(new Maze());
		Game.graManger.addLayer(new Entitys());
		Game.graManger.addLayer(new Time());
		Game.graManger.removeLayer(this.getClass());	
		
		int x1 = (Game.level.getSpawn().getX() * Game.ppb_x) + Game.ppb_x/2;
		int y1 = (Game.level.getSpawn().getY() * Game.ppb_y) + Game.ppb_y/2;
		MainProgram.game.player = new Player();
		MainProgram.game.player.Create(x1, y1, "");
	}

	@Override
	public int priority() {
		return 0;
	}

}