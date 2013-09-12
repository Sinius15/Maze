package sinius.maze;

import sinius.maze.core.Engine;
import sinius.maze.entitys.Player;
import sinius.maze.gameEngine.Display;
import sinius.maze.gui.EditorOptionScreen;
import sinius.maze.io.LevelLoader;
import sinius.maze.state.GameState;
import sinius.maze.timing.FPSTimer;
import sinius.maze.timing.TimeTimer;

public class Game {
	
	public static Level level;
	public static EditorOptionScreen options;
	public static TimeTimer timer = new TimeTimer();
	public static FPSTimer fps = new FPSTimer();
	public static Display display;
	
	public static Player player;
	
	public static boolean mouseDrag = false;
	public static int ppb_x, ppb_y, mouseX = -1, mouseY = -1, latestMouseX = -1, latestMouseY = -1;
	
	public Game(Level l, GameState state){
		
		level = l;
		display = new Display(800, 800, "Sinius's Maze", state);
		ppb_x = 800 / l.getWidth();
		ppb_y = 800 / l.getHeight();
		
		MainProgram.engine = new Engine();
		timer.Start();
		fps.Start();
		
	}
	
		
	public void quitGame(){
		try {
			LevelLoader.SaveLevel(level, MainProgram.SAVEMAP + "\\saves\\" + level.getName() + ".maze" );
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.exit(0);
	}

	public Player getPlayer() {
		return player;
	}
		
}
