package sinius.maze.state.playMode;

import java.awt.event.KeyEvent;

import sinius.maze.Entity;
import sinius.maze.Game;
import sinius.maze.core.SynchroniezedList;
import sinius.maze.core.SynchroniezedList.editAction;
import sinius.maze.entitys.Player;
import sinius.maze.state.GameState;
import sinius.maze.timing.TimeTimer;

public class PlayState implements GameState{
	
	private SynchroniezedList gObjects = new SynchroniezedList();
	private SynchroniezedList gLayers = new SynchroniezedList();
	
	public TimeTimer timer = new TimeTimer();

	public PlayState(){
		gLayers.add(new Layer_Maze());
		gLayers.add(new Layer_Entitys());
		gLayers.add(new Layer_Time(timer));
		gLayers.add(new Layer_Player());
		Game.player = new Player();
		int x = Game.level.getSpawn().getX();
		int y = Game.level.getSpawn().getY();
		Game.player.Create((x*Game.ppb_x)+Game.ppb_x/2, (y*Game.ppb_y)+Game.ppb_y/2, "");
		timer.Start();
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
	public void tick() {
		Game.level.getEntitys().doForAll(new editAction() { @Override public void action(Object o) {
			Entity e = (Entity) o;
			e.onTick(Game.level);
			if(e.getCollisionBox().intersects(Game.player.getCollisionBox()))
				e.onPlayerTouch(Game.player);
		}});
	}

	@Override
	public void mouseEvent(int button) {
	}

	@Override
	public void keyEvent(int button) {
		
		if(button == KeyEvent.VK_UP)
			Game.player.up(Game.level);
		if(button == KeyEvent.VK_DOWN)
			Game.player.down(Game.level);
		if(button == KeyEvent.VK_LEFT)
			Game.player.left(Game.level);
		if(button == KeyEvent.VK_RIGHT)
			Game.player.right(Game.level);
	}

}
