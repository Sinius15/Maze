package sinius.maze.state.levelChoser;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import sinius.maze.Game;
import sinius.maze.Level;
import sinius.maze.MainProgram;
import sinius.maze.Util;
import sinius.maze.core.SynchroniezedList;
import sinius.maze.gameEngine.GButton;
import sinius.maze.io.LevelLoader;
import sinius.maze.state.GameState;
import sinius.maze.state.editMode.EditState;
import sinius.maze.state.playMode.PlayState;

public class LevelChoserState implements GameState{
	
	private SynchroniezedList gObjects = new SynchroniezedList();
	SynchroniezedList gLayers = new SynchroniezedList();
	private GButton latestButton = new GButton(0, 0, 0, 0);
	private String selected = "";
	
	public LevelChoserState(){
		refresh();
		gLayers.add(new Layer_Background());
	}
	
	@Override
	public String getName() {
		return "levelChoser";
	}

	@Override
	public void tick() {
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
	public void mouseEvent(int button) {
	}

	@Override
	public void keyEvent(int button) {
	}
	
	private Level getLevel(){
		return LevelLoader.LoadLevel(new File(MainProgram.MAP_SAVES + "\\" + selected + ".maze"));
	}
	
	private synchronized void refresh(){
		gObjects = new SynchroniezedList();
		
		
		GButton play = new GButton(500, 680, 100, 50);
		play.setButtonColor(Color.orange);
		play.setTextColor(Color.black);
		play.setText("Play this level");
		play.setAction(new ActionListener() { @Override public void actionPerformed(ActionEvent e) {
			if(selected == "")
				return;
			Game.level = getLevel();
			Game.display.setGameState(new PlayState());
		}});
		gObjects.add(play);
		
		GButton edit = new GButton(300, 680, 100, 50);
		edit.setButtonColor(Color.orange);
		edit.setTextColor(Color.black);
		edit.setText("Edit");
		edit.setAction(new ActionListener() { @Override public void actionPerformed(ActionEvent e) {
				if(selected == "")
					return;
			Game.level = getLevel();
			Game.display.setGameState(new EditState());
		}});
		gObjects.add(edit);
		
		selected = "";
		int i = 0;
		for(File f : Util.getFileList(MainProgram.MAP_SAVES)){
			i = i + 50;
			final GButton b = new GButton(100, i, 600, 40);
			b.setText(f.getName().replace(".maze", ""));
			b.setAction(new ActionListener() { @Override public void actionPerformed(ActionEvent arg0) {
				if(b.getText().equals(selected))
					return;
				selected = b.getText();
				b.setButtonColor(Color.red);
				latestButton.setButtonColor(Color.blue);
				latestButton = b;
			}});
			b.setButtonColor(Color.blue);
			b.setTextColor(Color.white);
			gObjects.add(b);
		}
		
	}

}
