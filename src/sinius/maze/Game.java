package sinius.maze;

import sinius.maze.core.Engine;
import sinius.maze.entitys.Player;
import sinius.maze.gameEngine.Display;
import sinius.maze.gui.EditorOptionScreen;
import sinius.maze.io.LevelLoader;
import sinius.maze.lib.Folders;
import sinius.maze.plugin.PluginManager;
import sinius.maze.state.GameState;
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
	public PluginManager pluginManger;
	
	private static Game theGame;
	
	public Game(GameState state, PluginManager pm){
		this.pluginManger = pm;
		tps.Start();
		theGame = this;
		fps.Start();
		
		display = new Display(800, 800, "Sinius's Maze", state);
		
		pluginManger.initPlugins();
		
		MainProgram.engine = new Engine();
		
		
		
	}
	
	public static Game get(){
		return theGame;
	}
	
	public void SaveGame(){
		try {
			LevelLoader.SaveLevel(level, Folders.SAVES.getAbsolutePath() +"//"+ level.getName() + ".maze" );
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
