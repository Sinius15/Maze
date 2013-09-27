package sinius.maze.lib;

import java.io.File;

public class Folders{
	
	public static File MAIN = null;
	
	public static File PLUGIN = null;
	
	public static File SAVES = null;
	
	public static File LEVELS = null;
	
	public static File RES = null;
	
	public static void init(){
		PLUGIN = new File(MAIN + "//plugins");
		
		SAVES = new File(MAIN + "//saves");
		
		LEVELS = new File(MAIN + "//levels");
		
		RES = new File(MAIN + "//resources"); 
	}
	
}
