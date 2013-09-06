package sinius.maze;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import sinius.maze.io.MapStructureCreator;

@SuppressWarnings("rawtypes")
public class EntityManager {

	private ArrayList<Class<?>> entitys = new ArrayList<Class<?>>();
	
	@SuppressWarnings("resource")
	public void initEntitys(){
		try {
			List<File> files = getFiles(".jar", MapStructureCreator.entitys.toPath());
			for(File f: files){
					URL path = new URL("jar:" + f.toURI().toURL() + "!/entitys.txt");
					
					InputStream is = path.openStream();
					InputStreamReader isr = new InputStreamReader(is, "US-ASCII" );
					BufferedReader br = new BufferedReader(isr);
					String text;
					while((text = br.readLine()) != null){
						URL url = f.toURI().toURL();
						URL[] urls = new URL[]{url};
						ClassLoader clazzloader = new URLClassLoader(urls);
						Class<?> clazz = Class.forName(text, true, clazzloader);
						if(Entity.class.isAssignableFrom(clazz)){
							entitys.add(clazz);
						}
					}
					br.close();
					isr.close();
					is.close();
				
			
			}
		} catch (Exception e) {
			System.err.println("Something went wrong");
			e.printStackTrace();
		}
		String s = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
		if(!s.endsWith(".jar")){
			try{
				File entityTxt = new File(s + "/entitys.txt");
				BufferedReader br = new BufferedReader(new FileReader(entityTxt));  
				String line = null;  
				while ((line = br.readLine()) != null){
					System.out.println("i read from the entitys.txt: " + line);
					entitys.add(Class.forName(line));
				} 
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public synchronized ArrayList<String> getEntityNames(){
		ArrayList<String> out = new ArrayList<String>();
		
		for(Class c: entitys){
			Entity e;
			try {
				e = (Entity) c.newInstance();
				out.add(e.getName());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
		return out;
	}
	
	public synchronized Entity getEntityByName(String name){
		for(Class c: entitys){
			Entity e;
			try {
				e = (Entity) c.newInstance();
				if(e.getName().equals(name))
					return e;
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return null;
	}
	
	public synchronized Entity getEntityByClass(String className){
		
		for(Class c : entitys){
			if(c.getName().equals(className)){
				try {
					return (Entity) c.newInstance();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		try {
			Class<?> c = Class.forName(className);
			if(Entity.class.isAssignableFrom(c)){
				return (Entity) c.newInstance();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		return null;
	}
	
	private List<File> getFiles(String suffix, Path folder) throws IOException{
		List<File> files = new ArrayList<File>();
		
		DirectoryStream<Path> stream = Files.newDirectoryStream(folder);
		for (Path entry: stream) {
			File f = entry.toFile();
			if(f.isFile())
				if(f.getName().endsWith(suffix))
					files.add(f);
		}
		
//		File[] listOfFiles = folder.listFiles(); 
//		for(int i = 0; i < listOfFiles.length; i++){
//			if (listOfFiles[i].isFile()) {
//				String txt = listOfFiles[i].getName();
//				System.out.println(txt);
//				if(txt.toLowerCase().endsWith(suffix)){
//					files.add(listOfFiles[i]);
//				}
//			}
//		}
		return files;
	}
}
