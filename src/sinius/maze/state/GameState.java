package sinius.maze.state;

import sinius.maze.core.SynchroniezedList;

public interface GameState {

	public String getName();
	public void tick();
	public SynchroniezedList getGObjects();
	public SynchroniezedList getGraphicsLayers();
	public void mouseEvent(int button);
	public void keyEvent(int button);
	
}
