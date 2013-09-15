package sinius.maze.state.menu;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import sinius.maze.Game;
import sinius.maze.core.SynchroniezedList;
import sinius.maze.gameEngine.GButton;
import sinius.maze.state.GameState;
import sinius.maze.state.levelChoser.LevelChoserState;

public class MenuState implements GameState{

	private SynchroniezedList gObjects = new SynchroniezedList();
	private SynchroniezedList gLayers = new SynchroniezedList();
	
	private GButton b_start;
	
	public MenuState(){
		gLayers.add(new BackGround());
		
		b_start = new GButton(100, 100, 600, 100);
		b_start.setButtonColor(Color.blue);
		b_start.setTextColor(Color.white);
		b_start.setText("Start the Game!");
		b_start.setAction(new ActionListener() { @Override public void actionPerformed(ActionEvent e) {
				
				Game.display.setGameState(new LevelChoserState());
				
		}});
		gObjects.add(b_start);
	}
	
	@Override
	public String getName() {
		return "Menu";
	}

	@Override
	public SynchroniezedList getGObjects() {
		return gObjects;
	}

	@Override
	public SynchroniezedList getGraphicsLayers() {
		return gLayers;
	}

	@Override
	public void tick() {
	}

	@Override
	public void mouseEvent(int button) {
		System.out.println("x: " + Game.mouseX + " y: " + Game.mouseY);
	}

	@Override
	public void keyEvent(int button) {
	}

}
