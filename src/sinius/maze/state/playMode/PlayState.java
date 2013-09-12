package sinius.maze.state.playMode;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import sinius.maze.Entity;
import sinius.maze.Game;
import sinius.maze.core.SynchroniezedList;
import sinius.maze.core.SynchroniezedList.editAction;
import sinius.maze.state.GameState;

public class PlayState implements GameState{
	
	private PlayKeyListener keys = new PlayKeyListener();
	private PlayMouseListener mouse = new PlayMouseListener();
	
	private SynchroniezedList gObjects = new SynchroniezedList();
	private SynchroniezedList gLayers = new SynchroniezedList();

	public PlayState(){
		gLayers.add(new Layer_Maze());
		gLayers.add(new Layer_Entitys());
		gLayers.add(new Layer_Time());
	}
	
	@Override
	public String getName() {
		return "play";
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
	public MouseListener getMouseListener() {
		return mouse;
	}

	@Override
	public KeyListener getKeyListener() {
		return keys;
	}

	@Override
	public void tick() {
		Game.level.getEntitys().doForAll(new editAction() { @Override public void action(Object o) {
			Entity e = (Entity) o;
			e.onTick(Game.level);
			if(e.getCollisionBox().intersects(Game.player.getCollisionBox()))
				e.onPlayerTouch(Game.player);
		}});
	}

}
