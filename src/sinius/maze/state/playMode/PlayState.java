package sinius.maze.state.playMode;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import sinius.maze.core.SynchroniezedList;
import sinius.maze.state.GameState;

public class PlayState implements GameState{
	
	private PlayKeyListener keys = new PlayKeyListener();
	private PlayMouseListener mouse = new PlayMouseListener();

	@Override
	public String getName() {
		return "play";
	}

	@Override
	public SynchroniezedList getGObjects() {
		return null;
	}

	@Override
	public SynchroniezedList getGraphicsLayers() {
		return null;
	}

	@Override
	public MouseListener getMouseListener() {
		return mouse;
	}

	@Override
	public KeyListener getKeyListener() {
		return keys;
	}

}
