package sinius.maze.state.playMode;

import java.awt.Color;
import java.awt.event.KeyEvent;

import sinius.maze.Entity;
import sinius.maze.Game;
import sinius.maze.core.SynchroniezedList;
import sinius.maze.core.SynchroniezedList.editAction;
import sinius.maze.entitys.Player;
import sinius.maze.gameEngine.GObject;
import sinius.maze.gameEngine.GText;
import sinius.maze.state.GameState;
import sinius.maze.state.GrapicsLayer;
import sinius.maze.timing.TimeTimer;

public class PlayState implements GameState{
	
	private SynchroniezedList<GObject> gObjects = new SynchroniezedList<GObject>();
	private SynchroniezedList<GrapicsLayer> gLayers = new SynchroniezedList<GrapicsLayer>();
	private GText timeText;
	
	private TimeTimer timer;

	public PlayState(){
		gLayers.add(new Layer_Maze());
		gLayers.add(new Layer_Entitys());
		
		gLayers.add(new Layer_Player());
		Game.get().player = new Player();
		int x = Game.get().level.getSpawn().getX();
		int y = Game.get().level.getSpawn().getY();
		Game.get().player.Create((x*Game.get().ppb_x)+Game.get().ppb_x/2, (y*Game.get().ppb_y)+Game.get().ppb_y/2, "");
		
		timer = new TimeTimer();
		timer.Start();
		
		timeText = new GText(timer.getTime(), 5, 25);
		timeText.setFont(Game.get().font);
		timeText.setColor(Color.black);
		
		gObjects.add(timeText);
		
		Game.get().display.camera.setSize(10*Game.get().ppb_x, 10*Game.get().ppb_y);
		Game.get().display.camera.setLocation(Game.get().player.getX(), Game.get().player.getY());
	}
	
	@Override
	public String getName() {
		return "play";
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
		Game.get().level.getEntitys().doForAll(new editAction<Entity>() { @Override public void action(Entity e) {
			e.onTick(Game.get().level);
			if(e.getCollisionBox().intersects(Game.get().player.getCollisionBox()))
				e.onPlayerTouch(Game.get().player);
		}});
		timeText.setText(timer.getTime());
	}

	@Override
	public void mouseEvent(int button) {
	}

	@Override
	public void keyEvent(int button) {
		
		
		if(button == KeyEvent.VK_UP)
			Game.get().player.up(Game.get().level);
		if(button == KeyEvent.VK_DOWN)
			Game.get().player.down(Game.get().level);
		if(button == KeyEvent.VK_LEFT)
			Game.get().player.left(Game.get().level);
		if(button == KeyEvent.VK_RIGHT)
			Game.get().player.right(Game.get().level);
		Game.get().display.camera.setLocation(Game.get().player.getX(), Game.get().player.getY());
	}
	
	public TimeTimer getTimer(){
		return timer;
	}

}
