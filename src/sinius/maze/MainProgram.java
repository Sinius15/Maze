package sinius.maze;

import java.util.ArrayList;

import sinius.maze.gui.StartupScreen;
import sinius.maze.io.LevelLoader;
import sinius.maze.io.MapStructureCreator;

public class MainProgram {

	public static StartupScreen startupScreen;
	public static ArrayList<Level> levels;
	public static Game game;
	public static EntityManager entityManager = new EntityManager();
	public static EditorObjectManager editorObjManager = new EditorObjectManager();
	
	public static String SAVEMAP = System.getenv("APPDATA") + "\\Sinius Maze";
	
	
	
	public static void main(String[] args) {
		if(args.length == 1)
			SAVEMAP = args[0];
		
		MapStructureCreator maper = new MapStructureCreator();
		try {
			maper.CreateFirstStartup();
		} catch (Exception e) {
			System.out.println("something critical went wrong when creating the files");
			e.printStackTrace();
			System.exit(1);
			
		}
		
		entityManager.initEntitys();
		editorObjManager.initEditorObj();
		
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
