package sinius.maze;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import sinius.maze.gui.StartupScreen;
import sinius.maze.io.LevelLoader;
import sinius.maze.io.MapStructureCreator;

public class MainProgram {
	
	public static StartupScreen startupScreen;
	public static ArrayList<Level> levels;
	public static Game game;
	
	public static final String SAVEMAP = System.getenv("APPDATA") + "\\Sinius Maze";
	
	public static void main(String[] args) {
		
		File f = new File(System.getenv("APPDATA") + "\\Sinius Maze");
		f.mkdirs();
		
		try {
			MapStructureCreator.CreateFirstStartup();
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
