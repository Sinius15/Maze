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
import sinius.maze.gameEngine.GObject;
import sinius.maze.gui.EditorOptionScreen;
import sinius.maze.plugin.Teleporter;
import sinius.maze.state.GameState;
import sinius.maze.state.GrapicsLayer;
import sinius.maze.state.pause.PauseEditState;
import sinius.maze.state.playMode.Layer_Entitys;
import sinius.maze.state.playMode.Layer_Maze;

public class EditState implements GameState{

	private SynchroniezedList<GObject> gObjects = new SynchroniezedList<GObject>();
	private SynchroniezedList<GrapicsLayer> gLayers = new SynchroniezedList<GrapicsLayer>();
	
	boolean temp;
	
	public EditState(){
		gLayers.add(new Layer_Maze());
		gLayers.add(new Layer_EditorOption());
		gLayers.add(new Layer_Entitys());
		Game.get().options = new EditorOptionScreen();
		Game.get().options.setVisible(true);
	}
	
	@Override
	public String getName() {
		return "editor";
	}

	@Override
	public SynchroniezedList<GObject> getGObjects() {
		return gObjects;
	}

	@Override
	public SynchroniezedList<GrapicsLayer> getGraphicsLayers() {
		return gLayers;
	}

	@Override
	public void tick() {
	}

	@Override
	public void mouseEvent(int button) {
		int mouseX = Game.get().mouseX;
		int mouseY = Game.get().mouseY;
		
		int blockX = mouseX/Game.get().ppb_x;
		int blockY = mouseY/Game.get().ppb_y;
		
		
		if(mouseX != -1){
			if(Game.get().options.getBrush().equals("Wall Brush")){
				if(button == MouseEvent.BUTTON1){
					for(Block b : Util.getBlocksAround(blockX, blockY, Game.get().level)){
						b.setType(Block.WALL);
						b.setColor(Game.get().options.getColor());
					}
					
				}
				if(button == MouseEvent.BUTTON3){
					for(Block b : Util.getBlocksAround(blockX, blockY, Game.get().level))
						b.setType(Block.AIR);
				}
				
			}else if(Game.get().options.getBrush().equals("Spawn")){
				if(button == MouseEvent.BUTTON1){
					Spawn s = new Spawn();
					s.Create(blockX, blockY, "");
					Game.get().level.setSpawn(s);
				}
					
			}else if(Game.get().options.getBrush().equals("Wall pencil")){
				Block b = Game.get().level.getBlock(blockX, blockY);
				if(button == MouseEvent.BUTTON1){
					b.setType(Block.WALL);
					b.setColor(Game.get().options.getColor());
				}
				if(button == MouseEvent.BUTTON3){
					b.setType(Block.AIR);
				}
			}else{
				final Entity x = MainProgram.entityManager.getEntityByName(Game.get().options.getBrush());
				if(x != null){
					if(x.onGrid()){
						x.Create(blockX, blockY, "");
					}else{
						x.Create(mouseX, mouseY, "");
					}
					
					if(button == MouseEvent.BUTTON1){
						if(x.onGrid())
							if(!Game.get().level.isEntityOnBlock(x.getX(), x.getY()))
								Game.get().level.getEntitys().add(x);
						if(!x.onGrid())
							if(!Game.get().level.isEntityOnCoord(x.getX(), x.getY()))
								Game.get().level.getEntitys().add(x);
						
					}else if(button == MouseEvent.BUTTON3){
						Game.get().level.getEntitys().doForAll(new editAction<Entity>() {@Override public void action(Entity e) {
							if(e.onGrid()){
								if(e.getX() == Game.get().mouseX/Game.get().ppb_x && e.getY() == Game.get().mouseY/Game.get().ppb_y && e.getClass().equals(x.getClass()))
									Game.get().level.getEntitys().removeLater(e);
							}else{
								if(e.getX()/Game.get().ppb_x == Game.get().mouseX/Game.get().ppb_x && e.getY()/Game.get().ppb_y == Game.get().mouseY/Game.get().ppb_y && e.getClass().equals(x.getClass()))
									Game.get().level.getEntitys().removeLater(e);
							}
								
								
						}});
					}
				}else{
					MainProgram.editorObjManager.getByName(Game.get().options.getBrush()).mouseClick(button);;
				}
				
				
			}
		}
		
		
	}

	@Override
	public void keyEvent(int key) {
		if(key == KeyEvent.VK_ESCAPE){
			Game.get().display.setGameState(new PauseEditState(this));
		}
	}

}
