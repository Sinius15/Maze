package sinius.maze;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.util.ArrayList;

import sinius.maze.core.Engine;
import sinius.maze.io.MapStructureCreator;
import sinius.maze.lib.Folders;
import sinius.maze.plugin.PluginManager;
import sinius.maze.state.menu.MenuState;

public class MainProgram {

	public static ArrayList<Level> levels;
	public static Game game;
	public static Engine engine;
	
	public static void main(String[] args) {

		if(args.length == 1)
			Folders.MAIN = new File(args[0]);
		else
			Folders.MAIN = new File(System.getenv("APPDATA") + "\\Sinius Maze");
		Folders.init();
		
		
		
		MapStructureCreator maper = new MapStructureCreator();
		try {
			maper.CreateFirstStartup();
		} catch (Exception e) {
			System.err.println("something critical went wrong when creating the files");
			e.printStackTrace();
			System.exit(1);
			
		}
		
		try {
			GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(Folders.RES.getAbsolutePath() + "//Blocked Off.ttf")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		PluginManager m = new PluginManager();
		m.loadPlugins();
		game = new Game(new MenuState(), m);
		
	}
}
