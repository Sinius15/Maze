package sinius.maze;

import java.io.IOException;
import java.util.ArrayList;

import sinius.maze.gui.StartupScreen;
import sinius.maze.io.LevelLoader;
import sinius.maze.io.MapStructureCreator;

public class MainProgram {
	
	public static StartupScreen startupScreen;
	public static ArrayList<Level> levels;
	public static Game game;
	
	public static String SAVEMAP = System.getenv("APPDATA") + "\\Sinius Maze";
	
	public static void main(String[] args) {
		if(args.length == 1)
			SAVEMAP = args[0];
		
		
		try {
			MapStructureCreator.CreateFirstStartup();
		} catch (IOException e) {
			System.out.println("something critical went wrong when creating the files");
			System.exit(1);
			e.printStackTrace();
		}
		
		
		loadAllLevels();
		reDrawStartupScreen();
		
	}
	
	public static void loadAllLevels(){
		
		levels = LevelLoader.getLevelList(System.getenv("APPDATA") + "\\Sinius Maze\\saves");
	}
	
	public static void reDrawStartupScreen(){
		startupScreen = new StartupScreen();
		startupScreen.setVisible(true);
	}
}
