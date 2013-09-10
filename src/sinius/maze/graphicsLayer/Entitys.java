package sinius.maze.graphicsLayer;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import sinius.maze.Entity;
import sinius.maze.Game;
import sinius.maze.GraphicsLayer;
import sinius.maze.MainProgram;
import sinius.maze.core.SynchroniezedList.editAction;
import sinius.maze.entitys.Player;

public class Entitys implements GraphicsLayer{

	@Override
	public String getName() {
		return "entitys";
	}

	@Override
	public void Draw(final Graphics2D graphics) {
		Game.level.getEntitys().doForAll(new editAction() { @Override public void action(Object o) {
			
			Entity e = (Entity) o;
			
			if(e.onGrid())
				graphics.drawImage(e.getFont(), e.getX()*Game.ppb_x, e.getY()*Game.ppb_y, Game.ppb_x, Game.ppb_y, null);
			else{
				if(e.getSize() != null)
					graphics.drawImage(e.getFont(), e.getX(), e.getY(), e.getSize().width, e.getSize().height, null);
				else
					graphics.drawImage(e.getFont(), e.getX(), e.getY(), Game.ppb_x, Game.ppb_y, null);
			}
			graphics.setColor(Color.black);
			e.advancedRender(graphics, Game.editMode);
			
		}});
		
		
		if(!Game.editMode){
			Player p = MainProgram.game.getPlayer();
			try {
				graphics.drawImage(p.getFont(), p.getX()-Game.ppb_x/2, p.getY()-Game.ppb_y/2, Game.ppb_x, Game.ppb_y, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
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
