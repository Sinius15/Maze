package sinius.maze.state.pause;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import sinius.maze.Game;
import sinius.maze.core.SynchroniezedList;
import sinius.maze.core.SynchroniezedList.editAction;
import sinius.maze.gameEngine.GButton;
import sinius.maze.gameEngine.GObject;
import sinius.maze.state.GameState;
import sinius.maze.state.GrapicsLayer;
import sinius.maze.state.menu.MenuState;
import sinius.maze.state.playMode.PlayState;

public class PausePlayState implements GameState{

	public PlayState playState;
	
	private SynchroniezedList<GObject> gObjects = new SynchroniezedList<GObject>();
	private SynchroniezedList<GrapicsLayer> gLayers = new SynchroniezedList<GrapicsLayer>();
	
	public PausePlayState(PlayState s){
		this.playState = s;
		playState.getTimer().Stop();
		playState.getGraphicsLayers().doForAll(new editAction<GrapicsLayer>() { @Override public void action(GrapicsLayer o) {
			if(!o.drawAfter())
				gLayers.add(o);
		}});
		gLayers.add(new Layer_Back());
		
		GButton resume = new GButton(50, 50, 700, 50);
		resume.setButtonColor(Color.green);
		resume.setTextColor(Color.black);
		resume.setText("Resume Game!");
		resume.setAction(new ActionListener() { @Override public void actionPerformed(ActionEvent e) {
			playState.getTimer().Start();
			Game.get().display.setGameState(playState);
		}});
		gObjects.add(resume);
		
		GButton quit = new GButton(50, 700, 700, 50);
		quit.setButtonColor(Color.red);
		quit.setTextColor(Color.black);
		quit.setText("Quit to menu");
		quit.setAction(new ActionListener() { @Override public void actionPerformed(ActionEvent e) {
			Game.get().display.setGameState(new MenuState());
		}});
		gObjects.add(quit);
	}
	
	@Override
	public String getName() {
		return "pause";
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
