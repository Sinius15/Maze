package sinius.maze.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import sinius.maze.Util;
import sinius.maze.lib.Folders;

public class MapStructureCreator {

	@SuppressWarnings("resource")
	public void CreateFirstStartup() throws Exception{
		
		String s = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
		
		if(!Folders.MAIN.exists())
			Folders.MAIN.mkdirs();
		if(!Folders.TEMP.exists())
			Folders.TEMP.mkdirs();
		
		if(!s.endsWith(".jar")){
			File toMap = null, toFile = null, fromMap = null, fromFile = null;
			ArrayList<String> dirs = new ArrayList<>();
			File dir = new File("res//");
			for(File f : dir.listFiles()){
				if(f.isDirectory()){
					dirs.add(f.getPath().substring(4, f.getPath().length()));
					
				}
			}
			
			
			for(String maps : dirs){
				toMap = new File(  Folders.MAIN + "//" + maps);
				fromMap = new File("res//" + maps);
				if(!toMap.exists())
					toMap.mkdirs();
				ArrayList<File> files = Util.getFileList(fromMap.getAbsolutePath());
				if(files != null){
					for(File f : files){
						toFile = new File(toMap.getAbsolutePath() + "//" + f.getName());
						fromFile = f;
						if(!toFile.exists()){
							Files.copy(fromFile.toPath(), toFile.toPath());
						}
					}
				}
				
			}
			
				
		}else{
			s = s.replaceAll("%20", " ");
			JarFile jar = new JarFile(s);
			Enumeration<JarEntry> entrys = jar.entries();
			while(entrys.hasMoreElements()){
				JarEntry entry = entrys.nextElement();
				String path = entry.getName();
				String[] split = path.split("/");
				
				System.out.println(path);
				
				
				if(!path.endsWith("/") && !path.endsWith(".class") && !path.endsWith(".MF")){
					
					String builder = "";
					for(int i = 0; i<(split.length-1); i++)
						builder = builder + split[i];
					
					File map = new File(Folders.MAIN + "//" + builder);
					if(!map.exists())
						map.mkdirs();
					File outFile = new File(map.getAbsolutePath() +"//"+ split[split.length-1]);
					
					if(!outFile.exists()){
						
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
						
			            System.out.println("I just copeyed this file. path in jar: " + path + "  path on disk: " + outFile.getPath());
			            
					}
				}
				
			}
			
		}
		
	}

}
