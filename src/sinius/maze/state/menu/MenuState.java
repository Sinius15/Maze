package sinius.maze.state.menu;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import sinius.maze.Game;
import sinius.maze.core.SynchroniezedList;
import sinius.maze.gameEngine.GButton;
import sinius.maze.gameEngine.GObject;
import sinius.maze.gameEngine.GText;
import sinius.maze.lib.General;
import sinius.maze.lib.Layout;
import sinius.maze.state.GameState;
import sinius.maze.state.GrapicsLayer;
import sinius.maze.state.levelChoser.LevelChoserState;

public class MenuState implements GameState{

	private SynchroniezedList<GObject> gObjects = new SynchroniezedList<GObject>();
	private SynchroniezedList<GrapicsLayer> gLayers = new SynchroniezedList<GrapicsLayer>();
	
	private GButton b_start, b_resetSize;
	private GText t_version;
	
	public MenuState(){
		gLayers.add(new BackGround());
		
		b_start = new GButton(100, 100, 600, 100);
		b_start.setButtonColor(Color.blue);
		b_start.setTextColor(Color.white);
		b_start.setText("Start the Game!");
		b_start.setAction(new ActionListener() { @Override public void actionPerformed(ActionEvent e) {
				
				Game.get().display.setGameState(new LevelChoserState());
				
		}});
		gObjects.add(b_start);
	
		b_resetSize = new GButton(500, 650, 200, 80);
		b_resetSize.setButtonColor(Color.blue);
		b_resetSize.setTextColor(Color.white);
		b_resetSize.setText("Reset screen size");
		b_resetSize.setAction(new ActionListener() {@Override
			public void actionPerformed(ActionEvent e) {
				Game.get().display.resetSize();
			}
		});
		gObjects.add(b_resetSize);
		
		t_version = new GText(General.VESRION.toString(), 710, 780);
		t_version.setColor(Color.white);
		t_version.setFont(Layout.MAIN_FONT);
		gObjects.add(t_version);
		
	}
	
	@Override
	public String getName() {
		return "Menu";
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
