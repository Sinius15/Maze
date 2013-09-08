package sinius.maze.graphicsLayer;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import sinius.maze.Game;
import sinius.maze.MainProgram;
import sinius.maze.GraphicsLayer;
import sinius.maze.entitys.Player;

public class Entitys implements GraphicsLayer{

	@Override
	public String getName() {
		return "entitys";
	}

	@Override
	public void Draw(Graphics2D graphics) {
		
		Game.level.editEntitys("draw", graphics, null, null, 0, 0, null);
		
		Player p = MainProgram.game.getPlayer();
		try {
			graphics.drawImage(p.getFont(), p.getX()-Game.ppb_x/2, p.getY()-Game.ppb_y/2, Game.ppb_x, Game.ppb_y, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void mouseClick(MouseEvent e) {
	}

	@Override
	public int priority() {
		return 2;
	}

}
