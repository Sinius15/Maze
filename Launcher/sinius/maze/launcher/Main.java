package sinius.maze.launcher;

import java.io.File;

public class Main {

	public static LoginScreen frame;
	
	//options:
	public static File dataPath = null;
	public static boolean autoUpadte = true;
	
	public static void main(String[] args) {
		frame = new LoginScreen();
		frame.setVisible(true);
	}
	
	public static String getArguments(){
		String builder = "";
		if(dataPath != null)
			builder = builder + " \"dataFolder:"+ dataPath.getAbsolutePath() +"\"";
		return builder;
	}
	
}
