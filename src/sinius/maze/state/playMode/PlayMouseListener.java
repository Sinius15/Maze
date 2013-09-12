package sinius.maze.state.playMode;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import sinius.maze.Block;
import sinius.maze.Entity;
import sinius.maze.Game;
import sinius.maze.MainProgram;
import sinius.maze.Util;
import sinius.maze.core.SynchroniezedList.editAction;
import sinius.maze.entitys.Spawn;

public class PlayMouseListener implements MouseListener{

	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent event) {
		
		int key = event.getButton();
		
		if(Game.mouseX != -1){
			if(Game.options.getBrush().equals("Wall Brush")){
				Block b = Game.level.getBlock(Game.mouseX, Game.mouseY);
				if(key == MouseEvent.BUTTON1){
					b.setType(Block.WALL);
					b.setColor(Game.options.getColor());
					if(Game.latestMouseX != -1 && !Game.mouseDrag){
						for(Block block : Util.getCrossedBlocks(Game.mouseX, Game.mouseY, Game.latestMouseX, Game.latestMouseY, Game.level)){
							block.setType(1);
							block.setColor(Game.options.getColor());
						}
					}
					
				}
				if(key == MouseEvent.BUTTON3){
					b.setType(Block.AIR);
					if(Game.latestMouseX != -1 && !Game.mouseDrag){
						for(Block block : Util.getCrossedBlocks(Game.mouseX, Game.mouseY, Game.latestMouseX, Game.latestMouseY, Game.level)){
								block.setType(0);
						}
					}
					
				}
				
			}else if(Game.options.getBrush().equals("Spawn")){
				if(key == MouseEvent.BUTTON1){
					Spawn s = new Spawn();
					s.Create(Game.mouseX, Game.mouseY, "");
					Game.level.setSpawn(s);
				}
					
			}else if(Game.options.getBrush().equals("Wall pencil")){
				Block b = Game.level.getBlock(Game.mouseX, Game.mouseY);
				if(key == MouseEvent.BUTTON1){
					b.setType(Block.WALL);
					b.setColor(Game.options.getColor());
				}
				if(key == MouseEvent.BUTTON3){
					b.setType(Block.AIR);
				}
			}else{
				final Entity x = MainProgram.entityManager.getEntityByName(Game.options.getBrush());
				if(x != null){
					x.Create(Game.mouseX, Game.mouseY, "");
					if(key == MouseEvent.BUTTON1){
						
						Game.level.getEntitys().doForAll(new editAction() {@Override public void action(Object o) {
								Entity e = (Entity) o;
								if(e.getX() == x.getX() && e.getY() == x.getY())
									Game.level.getEntitys().addLater(o);
						}});
						
					}else if(key == MouseEvent.BUTTON3){
						Game.level.getEntitys().doForAll(new editAction() {@Override public void action(Object o) {
							Entity e = (Entity) o;
							if(e.getX() == Game.mouseX/Game.ppb_x && e.getY() == Game.mouseY/Game.ppb_y && e.getClass().equals(x.getClass()))
								Game.level.getEntitys().removeLater(o);
						}});
					}
				}
				
				
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

}
