package sinius.maze.state.editMode;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import sinius.maze.core.SynchroniezedList;
import sinius.maze.state.GameState;

public class EditState implements GameState{

	@Override
	public String getName() {
		return "Editor";
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
		return null;
	}

	@Override
	public KeyListener getKeyListener() {
		return null;
	}

}
