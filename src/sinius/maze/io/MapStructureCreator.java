package sinius.maze.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import sinius.maze.MainProgram;
import sinius.maze.Util;

public class MapStructureCreator {

	private static File main = new File(MainProgram.SAVEMAP);
	private static File res = new File(MainProgram.SAVEMAP + "\\res");
	private static File saves = new File(MainProgram.SAVEMAP + "\\saves");
	private static File levels = new File(MainProgram.SAVEMAP + "\\levels");
	public static File entitys = new File(MainProgram.SAVEMAP + "\\entitys");
	
	
	@SuppressWarnings("resource")
	public void CreateFirstStartup() throws Exception{
		String s = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
		
		if(!main.exists())
			main.mkdirs();
		if(!res.exists())
			res.mkdirs();
		if(!saves.exists())
			saves.mkdirs();
		if(!levels.exists())
			levels.mkdirs();
		if(!entitys.exists())
			entitys.mkdirs();
		
			
		if(!s.endsWith(".jar")){	
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
		}else{
			JarFile jar = new JarFile(s);
			Enumeration<JarEntry> entrys = jar.entries();
			while(entrys.hasMoreElements()){
				JarEntry entry = entrys.nextElement();
				String path = entry.getName();
				String[] split = path.split("/");
				
				
				if(path.contains("levels/") && !path.equals("levels/")){
					System.out.println("Now going to copy a file...    the path is " + path);
					
					File outFile = new File(MainProgram.SAVEMAP + "//levels//" + split[split.length-1]);
					FileOutputStream outStream = new FileOutputStream(outFile);
					InputStream inStream = getClass().getClassLoader().getResourceAsStream(path);
					
					int readBytes;
		            byte[] buffer = new byte[4096];
		            while ((readBytes = inStream.read(buffer)) > 0) {
		                outStream.write(buffer, 0, readBytes);
		            }
		            
		            inStream.close();
		            outStream.flush();
		            outStream.close();
				}
					
				if(path.contains("pics_Entity/") && !path.equals("pics_Entity/")){
					System.out.println("Now going to copy a file...    the path is " + path);
					
					File outFile = new File(MainProgram.SAVEMAP + "//res//" + split[split.length-1]);
					FileOutputStream outStream = new FileOutputStream(outFile);
					InputStream inStream = getClass().getClassLoader().getResourceAsStream(path);
					
					int readBytes;
		            byte[] buffer = new byte[4096];
		            while ((readBytes = inStream.read(buffer)) > 0) {
		                outStream.write(buffer, 0, readBytes);
		            }
		            
		            inStream.close();
		            outStream.flush();
		            outStream.close();
				}
				
			}
			
		}
		
	}

}
