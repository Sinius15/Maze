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
import sinius.maze.gameEngine.GObject;
import sinius.maze.gui.LevelCreator;
import sinius.maze.io.LevelLoader;
import sinius.maze.state.GameState;
import sinius.maze.state.GrapicsLayer;
import sinius.maze.state.editMode.EditState;
import sinius.maze.state.menu.MenuState;
import sinius.maze.state.playMode.PlayState;
import sinius.maze.state.solve.SolveState;

public class LevelChoserState implements GameState{
	
	private SynchroniezedList<GObject> gObjects = new SynchroniezedList<GObject>();
	private SynchroniezedList<GrapicsLayer> gLayers = new SynchroniezedList<GrapicsLayer>();
	
	private LevelCreator levelCreator = new LevelCreator();
	
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
	
	private Level getLevel(){
		return LevelLoader.LoadLevel(new File(MainProgram.MAP_SAVES + "\\" + selected + ".maze"));
	}
	
	private final synchronized void refresh(){
		gObjects = new SynchroniezedList<GObject>();
		
		
		GButton play = new GButton(626,700,130,55);
		play.setButtonColor(Color.orange);
		play.setTextColor(Color.black);
		play.setText("Play this level");
		play.setAction(new ActionListener() { @Override public void actionPerformed(ActionEvent e) {
			if(selected == "")
				return;
			Game.get().level = getLevel();
			Game.get().display.setGameState(new PlayState());
		}});
		gObjects.add(play);
		
		GButton edit = new GButton(484,700,130,55);
		edit.setButtonColor(Color.orange);
		edit.setTextColor(Color.black);
		edit.setText("Edit");
		edit.setAction(new ActionListener() { @Override public void actionPerformed(ActionEvent e) {
				if(selected == "")
					return;
			Game.get().level = getLevel();
			Game.get().display.setGameState(new EditState());
		}});
		gObjects.add(edit);
		
		GButton refresh = new GButton(342, 700, 130, 55);
		refresh.setButtonColor(Color.orange);
		refresh.setTextColor(Color.black);
		refresh.setText("refresh list");
		refresh.setAction(new ActionListener() { @Override public void actionPerformed(ActionEvent e) {
			refresh();
		}});
		gObjects.add(refresh);
		
		GButton create = new GButton(200,700,130,55);
		create.setButtonColor(Color.orange);
		create.setTextColor(Color.black);
		create.setText("new level");
		create.setAction(new ActionListener() { @Override public void actionPerformed(ActionEvent e) {
			levelCreator = new LevelCreator();
			levelCreator.setVisible(true);
			Game.get().display.getFrame().setVisible(false);
		}});
		gObjects.add(create);
		
		GButton del = new GButton(50, 700, 130, 55);
		del.setButtonColor(Color.orange);
		del.setTextColor(Color.black);
		del.setText("delete level");
		del.setAction(new ActionListener() { @Override public void actionPerformed(ActionEvent e) {
			if(selected != null){
				File f =new File(MainProgram.MAP_SAVES + "\\" + selected + ".maze");
				f.delete();
				refresh();
			}
				
		}});
		gObjects.add(del);
		
		GButton back = new GButton(0, 0, 50, 30);
		back.setButtonColor(Color.red);
		back.setTextColor(Color.black);
		back.setText("<=");
		back.setAction(new ActionListener() { @Override public void actionPerformed(ActionEvent e) {
			Game.get().display.setGameState(new MenuState());
		}});
		gObjects.add(back);
		
		GButton solve = new GButton(450, 400, 80, 80);
		solve.setButtonColor(Color.green);
		solve.setTextColor(Color.black);
		solve.setText("Solve this level");
		solve.setAction(new ActionListener() {@Override public void actionPerformed(ActionEvent e) {
			if(selected == "")
				return;
			Game.get().level = getLevel();
			Game.get().display.setGameState(new SolveState(Game.get().level));
		}});
		gObjects.add(solve);
		
		selected = "";
		int i = 0;
		for(File f : Util.getFileList(MainProgram.MAP_SAVES)){
			i = i + 50;
			final GButton b = new GButton(50, i, 700, 40);
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
