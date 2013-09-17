package sinius.maze;

import java.util.ArrayList;

import sinius.maze.core.Engine;
import sinius.maze.io.MapStructureCreator;
import sinius.maze.state.menu.MenuState;

public class MainProgram {

	public static ArrayList<Level> levels;
	public static Game game;
	public static EntityManager entityManager = new EntityManager();
	public static EditorObjectManager editorObjManager = new EditorObjectManager();
	public static Engine engine;
	
	public static String SAVEMAP = System.getenv("APPDATA") + "\\Sinius Maze";
	public static String MAP_SAVES = SAVEMAP  + "\\saves";
	
	
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
		
		
		game = new Game(null, new MenuState());
		
	}
}
