package sinius.maze.state;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import sinius.maze.core.SynchroniezedList;

public interface GameState {

	public String getName();
	public SynchroniezedList getGObjects();
	public SynchroniezedList getGraphicsLayers();
	public MouseListener getMouseListener();
	public KeyListener getKeyListener();
	
}
