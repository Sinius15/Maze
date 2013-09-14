package sinius.maze.state.playMode;

import java.awt.Color;
import java.awt.Graphics2D;

import sinius.maze.Entity;
import sinius.maze.Game;
import sinius.maze.core.SynchroniezedList.editAction;
import sinius.maze.state.GrapicsLayer;

public class Layer_Entitys implements GrapicsLayer{

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
			e.advancedRender(graphics);
			
		}});
	}
	
	@Override
	public int priority() {
		return 2;
	}

}
