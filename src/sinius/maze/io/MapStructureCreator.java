package sinius.maze.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import sinius.maze.MainProgram;
import sinius.maze.Util;

public class MapStructureCreator {

	private static File main = new File(MainProgram.SAVEMAP);
	private static File res = new File(MainProgram.SAVEMAP + "\\res");
	private static File saves = new File(MainProgram.SAVEMAP + "\\saves");
	private static File levels = new File(MainProgram.SAVEMAP + "\\levels");
	
	
	public static void CreateFirstStartup() throws IOException{
		if(!main.exists())
			main.mkdirs();
		if(!res.exists())
			res.mkdirs();
		if(!saves.exists())
			saves.mkdirs();
		if(!levels.exists())
			levels.mkdirs();
		
		for(File f : Util.getFileList("res\\pics_Entity\\")){
			File to = new File(MainProgram.SAVEMAP + "\\res\\" + f.getName());
			if(!to.exists())
				Files.copy(f.toPath(), to.toPath());
		}
			
		for(File f : Util.getFileList("res\\levels\\")){
			File to = new File(MainProgram.SAVEMAP + "\\levels\\" + f.getName());
			if(!to.exists())
				Files.copy(f.toPath(), to.toPath());
		}	
			
			
		
	}
	
}
