package sinius.maze.state.editMode;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import sinius.maze.Block;
import sinius.maze.Entity;
import sinius.maze.Game;
import sinius.maze.MainProgram;
import sinius.maze.Util;
import sinius.maze.core.SynchroniezedList;
import sinius.maze.core.SynchroniezedList.editAction;
import sinius.maze.entitys.Spawn;
import sinius.maze.gui.EditorOptionScreen;
import sinius.maze.state.GameState;
import sinius.maze.state.playMode.Layer_Entitys;
import sinius.maze.state.playMode.Layer_Maze;

public class EditState implements GameState{

	private SynchroniezedList gObjects = new SynchroniezedList();
	private SynchroniezedList gLayers = new SynchroniezedList();
	
	boolean temp;
	
	public EditState(){
		gLayers.add(new Layer_Maze());
		gLayers.add(new Layer_EditorOption());
		gLayers.add(new Layer_Entitys());
		Game.options = new EditorOptionScreen();
		Game.options.setVisible(true);
	}
	
	@Override
	public String getName() {
		return "editor";
	}

	@Override
	public SynchroniezedList getGObjects() {
		return gObjects;
	}

	@Override
	public SynchroniezedList getGraphicsLayers() {
		return gLayers;
	}

	@Override
	public void tick() {
	}

	@Override
	public void mouseEvent(int button) {
		
		int mouseX = Game.mouseX;
		int mouseY = Game.mouseY;
		
		int blockX = mouseX/Game.ppb_x;
		int blockY = mouseY/Game.ppb_y;
		
		
		if(mouseX != -1){
			if(Game.options.getBrush().equals("Wall Brush")){
				Block b = Game.level.getBlock(blockX, blockY);
				if(button == MouseEvent.BUTTON1){
					b.setType(Block.WALL);
					b.setColor(Game.options.getColor());
					if(Game.latestMouseX != -1 && !Game.mouseDrag){
						for(Block block : Util.getCrossedBlocks(mouseX, mouseY, Game.latestMouseX, Game.latestMouseY, Game.level)){
							block.setType(1);
							block.setColor(Game.options.getColor());
						}
					}
					
				}
				if(button == MouseEvent.BUTTON3){
					b.setType(Block.AIR);
					if(Game.latestMouseX != -1 && !Game.mouseDrag){
						for(Block block : Util.getCrossedBlocks(mouseX, mouseY, Game.latestMouseX, Game.latestMouseY, Game.level)){
								block.setType(0);
						}
					}
					
				}
				
			}else if(Game.options.getBrush().equals("Spawn")){
				if(button == MouseEvent.BUTTON1){
					Spawn s = new Spawn();
					s.Create(blockX, blockY, "");
					Game.level.setSpawn(s);
				}
					
			}else if(Game.options.getBrush().equals("Wall pencil")){
				Block b = Game.level.getBlock(blockX, blockY);
				if(button == MouseEvent.BUTTON1){
					b.setType(Block.WALL);
					b.setColor(Game.options.getColor());
				}
				if(button == MouseEvent.BUTTON3){
					b.setType(Block.AIR);
				}
			}else{
				final Entity x = MainProgram.entityManager.getEntityByName(Game.options.getBrush());
				if(x != null){
					if(x.onGrid()){
						x.Create(blockX, blockY, "");
					}else{
						x.Create(mouseX, mouseY, "");
					}
					
					if(button == MouseEvent.BUTTON1){
						if(x.onGrid())
							if(!Game.level.isEntityOnBlock(x.getX(), x.getY()))
								Game.level.getEntitys().add(x);
						if(!x.onGrid())
							if(!Game.level.isEntityOnCoord(x.getX(), x.getY()))
								Game.level.getEntitys().add(x);
						
					}else if(button == MouseEvent.BUTTON3){
						Game.level.getEntitys().doForAll(new editAction() {@Override public void action(Object o) {
							Entity e = (Entity) o;
							if(e.getX() == Game.mouseX/Game.ppb_x && e.getY() == Game.mouseY/Game.ppb_y && e.getClass().equals(x.getClass()))
								Game.level.getEntitys().removeLater(o);
						}});
					}
				}else{
					MainProgram.editorObjManager.getByName(Game.options.getBrush()).mouseClick(button);;
				}
				
				
			}
		}
	}

	@Override
	public void keyEvent(int key) {
		if(key == KeyEvent.VK_ESCAPE){
			MainProgram.engine.stopGame();
		}
	}

}
