package sinius.maze;

import java.io.File;
import java.util.ArrayList;

import sinius.maze.gui.StartupScreen;
import sinius.maze.io.LevelLoader;

public class MainProgram {
	
	public static StartupScreen startupScreen;
	public static ArrayList<Level> levels;
	public static Game game;
	
	public static final String SAVEMAP = System.getenv("APPDATA") + "\\Sinius Maze";
	
	public static void main(String[] args) {
		
		File f = new File(System.getenv("APPDATA") + "\\Sinius Maze");
		f.mkdirs();
		
		
		
		
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
