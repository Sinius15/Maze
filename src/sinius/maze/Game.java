package sinius.maze;

import sinius.maze.core.Engine;
import sinius.maze.core.SynchroniezedList.editAction;
import sinius.maze.entitys.Player;
import sinius.maze.gameEngine.Display;
import sinius.maze.gui.EditorOptionScreen;
import sinius.maze.io.LevelLoader;
import sinius.maze.timing.FPSTimer;
import sinius.maze.timing.TimeTimer;

public class Game {
	
	public static Level level;
	public static EditorOptionScreen options;
	public static TimeTimer timer = new TimeTimer();
	public static FPSTimer fps = new FPSTimer();
	public static Display display;
	
	private static Thread gameLoop;
	public static boolean editMode;
	public static Player player;
	
	public static boolean mouseDrag = false;
	public static int ppb_x, ppb_y, mouseX = -1, mouseY = -1, latestMouseX = -1, latestMouseY = -1;
	
	public Game(Level l, boolean editMode){
		Game.editMode = editMode;
		level = l;
		display = new Display(800, 800, "Sinius's Maze");
		
		ppb_x = 800 / l.getWidth();
		ppb_y = 800 / l.getHeight();
		
		startGame();
		
	}
	
	public static void startGame(){
		
		MainProgram.engine = new Engine();
		
		gameLoop.start();
		timer.Start();
		fps.Start();
	}
	
	public static void doTick(){
		if(!editMode){
			level.getEntitys().doForAll(new editAction() { @Override public void action(Object o) {
					Entity e = (Entity) o;
					e.onTick(Game.level);
					if(e.getCollisionBox().intersects(player.getCollisionBox()))
						e.onPlayerTouch(player);
			}});

		}
		
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
