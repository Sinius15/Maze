package sinius.maze.state.playMode;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;

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
		
		final Dimension d = Game.get().display.camera.getBlockSize();
		
		Game.get().level.getEntitys().doForAll(new editAction<Entity>() { @Override public void action(Entity e) {
			
			if(e.onGrid()){
				Point p = Game.get().display.camera.getPointOnScreen(new Point(e.getX()*Game.get().ppb_x, e.getY()*Game.get().ppb_y));
				graphics.drawImage(e.getFont(), p.x, p.y, d.width, d.height, null);
			}else{
				Point p = Game.get().display.camera.getPointOnScreen(new Point(e.getX(), e.getY()));
				if(e.getSize() != null)
					graphics.drawImage(e.getFont(), p.x, p.y, (int) (e.getSize().width*Game.get().display.camera.getZoomX()), (int) (e.getSize().height*Game.get().display.camera.getZoomY()), null);
				else
					graphics.drawImage(e.getFont(), p.x, p.y, d.width, d.height, null);
			}
			graphics.setColor(Color.black);
			e.advancedRender(graphics);
			
		}});
	}
	
	@Override
	public int priority() {
		return 2;
	}

	@Override
	public boolean drawAfter() {
		return true;
	}

}
