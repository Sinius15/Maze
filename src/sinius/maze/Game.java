package sinius.maze;

import java.awt.Font;

import sinius.maze.core.Engine;
import sinius.maze.entitys.Player;
import sinius.maze.gameEngine.Display;
import sinius.maze.gui.EditorOptionScreen;
import sinius.maze.io.LevelLoader;
import sinius.maze.state.GameState;
import sinius.maze.state.playMode.PlayState;
import sinius.maze.timing.FPSTimer;
import sinius.maze.timing.TPSTimer;

public class Game {
	
	public Level level;
	public EditorOptionScreen options;

	public FPSTimer fps = new FPSTimer();
	public TPSTimer tps = new TPSTimer();
	
	public Display display;
	
	public Player player;
	
	public boolean mouseDrag = false;
	public int ppb_x, ppb_y, mouseX = -1, mouseY = -1, latestMouseX = -1, latestMouseY = -1;
	
	public Font font = new Font("Zolano Serif BTN", Font.PLAIN, 25);
	
	private static Game theGame;
	
	public Game(Level l, GameState state){
		tps.Start();
		theGame = this;
		fps.Start();
		level = l;
		if(level != null){
			ppb_x = 800 / l.getWidth();
			ppb_y = 800 / l.getHeight();
		}
		
		if(state == null)
			display = new Display(800, 800, "Sinius's Maze", new PlayState());
		else
			display = new Display(800, 800, "Sinius's Maze", state);
		
		MainProgram.engine = new Engine();
		
		
		
	}
	
	public static Game get(){
		return theGame;
	}
	
	public void SaveGame(){
		try {
			LevelLoader.SaveLevel(level, MainProgram.SAVEMAP + "\\saves\\" + level.getName() + ".maze" );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Player getPlayer() {
		return player;
	}

	public void doTick() {
		display.onTick();
		display.gameState.tick();
	}
}
