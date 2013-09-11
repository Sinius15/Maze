package sinius.maze.state.menu;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import sinius.maze.core.SynchroniezedList;
import sinius.maze.gameEngine.GButton;
import sinius.maze.state.GameState;

public class MenuState implements GameState{

	private SynchroniezedList gObjects = new SynchroniezedList();
	private SynchroniezedList gLayers = new SynchroniezedList();
	
	private GButton b_start;
	
	public MenuState(){
		gLayers.add(new BackGround());
		
		b_start = new GButton(100, 100, 100, 100);
		b_start.setButtonColor(Color.blue);
		b_start.setText("Start the Game!");
		b_start.setAction(new ActionListener() { @Override public void actionPerformed(ActionEvent e) {
				System.out.println("Now starting the game!!");
				//TODO: startGame
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
	public MouseListener getMouseListener() {
		return null;
	}

	@Override
	public KeyListener getKeyListener() {
		return null;
	}

}
