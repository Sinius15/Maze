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

public class Game {
	
	public static Level level;
	public static EditorOptionScreen options;

	public static FPSTimer fps = new FPSTimer();
	public static Display display;
	
	public static Player player;
	
	public static boolean mouseDrag = false;
	public static int ppb_x, ppb_y, mouseX = -1, mouseY = -1, latestMouseX = -1, latestMouseY = -1;
	
	public static Font font = new Font("Zolano Serif BTN", Font.PLAIN, 25);
	
	public Game(Level l, GameState state){
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
	
	public static void quitGame(){
		try {
			LevelLoader.SaveLevel(level, MainProgram.SAVEMAP + "\\saves\\" + level.getName() + ".maze" );
			System.out.println("saving");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.exit(0);
	}

	public Player getPlayer() {
		return player;
	}

	public static void doTick() {
		display.onTick();
		display.gameState.tick();
	}
}
