package sinius.maze.state.finish;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import sinius.maze.Game;
import sinius.maze.core.SynchroniezedList;
import sinius.maze.gameEngine.GButton;
import sinius.maze.gameEngine.GObject;
import sinius.maze.state.GameState;
import sinius.maze.state.GrapicsLayer;
import sinius.maze.state.menu.MenuState;

public class FinishState implements GameState {

	private SynchroniezedList<GObject> gObjects = new SynchroniezedList<GObject>();
	private SynchroniezedList<GrapicsLayer> gLayers = new SynchroniezedList<GrapicsLayer>();
	
	public FinishState(String time){
		
		gLayers.add(new Layer_Finish(time));
		
		GButton b = new GButton(500, 500, 150, 110);
		b.setButtonColor(Color.red);
		b.setText("Back to main menu");
		b.setTextColor(Color.black);
		b.setAction(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Game.get().display.setGameState(new MenuState());
			}
		});
		gObjects.add(b);
		Game.get().display.camera.setSize(800, 800);
		Game.get().display.camera.setLocation(400, 400);
	}
	
	@Override
	public String getName() {
		return "finish";
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
	}

	@Override
	public void keyEvent(int button) {
	}

}
