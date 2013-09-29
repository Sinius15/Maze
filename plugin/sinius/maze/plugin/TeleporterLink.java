package sinius.maze.plugin;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import sinius.maze.EditorObject;
import sinius.maze.Entity;
import sinius.maze.Game;
import sinius.maze.core.SynchroniezedList.editAction;

public class TeleporterLink implements EditorObject{

	Teleporter to = new Teleporter(), from = new Teleporter();
	int fromX, fromY, toX, toY;
	boolean selected = false, neverClicked = true;
	
	@Override
	public void mouseClick(int button) {
		if(button == MouseEvent.BUTTON1){
			if(Game.get().level.isEntityOnBlock(Game.get().mouseX/Game.get().ppb_x, Game.get().mouseY/Game.get().ppb_y))
					return;
			if(!selected){
				fromX = Game.get().mouseX/Game.get().ppb_x;
				fromY = Game.get().mouseY/Game.get().ppb_y;
			}
			if(selected){
				toX = Game.get().mouseX/Game.get().ppb_x;
				toY = Game.get().mouseY/Game.get().ppb_y;
				if(toX == fromX && toY == fromY)
					return;
				to = new Teleporter();
				to.Create(toX, toY, fromX + "," + fromY + ",out");
				from = new Teleporter();
				from.Create(fromX, fromY, toX + "," + toY + ",in");
				Game.get().level.getEntitys().add(to);
				Game.get().level.getEntitys().add(from);
			}
			selected = !selected;
		}
		if(button == MouseEvent.BUTTON3){
			Game.get().level.getEntitys().doForAll(new editAction<Entity>() { @Override public void action(Entity e) {
				if(e.getX() == Game.get().mouseX/Game.get().ppb_x && e.getY() == Game.get().mouseY/Game.get().ppb_y && e.getClass().equals(Teleporter.class))
					Game.get().level.getEntitys().removeLater(e);
			}});
		}
		
	}

	@Override
	public void render(Graphics2D graphics) {
		graphics.setColor(Color.red);
		if(selected)
			graphics.drawLine(fromX*Game.get().ppb_x+Game.get().ppb_x/2, fromY*Game.get().ppb_y+Game.get().ppb_y/2, (Game.get().mouseX/Game.get().ppb_x)*Game.get().ppb_x+Game.get().ppb_x/2, (Game.get().mouseY/Game.get().ppb_y)*Game.get().ppb_y+Game.get().ppb_y/2);
		
	}

	@Override
	public String getName() {
		return "Teleporter";
	}

	
	
	
}
