package sinius.maze.state.wait;

import java.awt.Color;

import sinius.maze.core.SynchroniezedList;
import sinius.maze.gameEngine.GObject;
import sinius.maze.gameEngine.GText;
import sinius.maze.lib.Layout;
import sinius.maze.state.GameState;
import sinius.maze.state.GrapicsLayer;
import sinius.maze.state.menu.BackGround;

public class WaitState implements GameState {

	private SynchroniezedList<GObject> gObjects = new SynchroniezedList<GObject>();
	private SynchroniezedList<GrapicsLayer> gLayers = new SynchroniezedList<GrapicsLayer>();
	
	public WaitState(String text1) {
		GText t = new GText(text1, 100,300);
		t.setFont(Layout.MAIN_FONT);
		t.setColor(Color.white);
		gObjects.add(t);
		
		gLayers.add(new BackGround());
	}
	
	@Override
	public String getName() {
		return "waitScreen";
	}

	@Override
	public void tick() {
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
	public void mouseEvent(int button) {
	}

	@Override
	public void keyEvent(int button) {
	}

}
