package sinius.maze.state;

import sinius.maze.core.SynchroniezedList;
import sinius.maze.gameEngine.GObject;

public interface GameState {

	public String getName();
	public void tick();
	public SynchroniezedList<GObject> getGObjects();
	public SynchroniezedList<GrapicsLayer> getGraphicsLayers();
	public void mouseEvent(int button);
	public void keyEvent(int button);
	
}
