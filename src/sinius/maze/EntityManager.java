package sinius.maze;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

import sinius.maze.io.MapStructureCreator;

@SuppressWarnings("rawtypes")
public class EntityManager {

	private ArrayList<Class<?>> avalableEntitys = new ArrayList<Class<?>>();
	
	public void init(){
		List<File> files = getFiles();
		for(File f: files){
			try {
				URL path = new URL("jar:" + f.toURI().toURL() + "!/entitys.txt");
				
				InputStream is = path.openStream();
				InputStreamReader isr = new InputStreamReader(is, "US-ASCII" );
				BufferedReader br = new BufferedReader(isr);
				String text;
				while((text = br.readLine()) != null){
					URL url = f.toURI().toURL();
					URL[] urls = new URL[]{url};
					ClassLoader clazzloader = new URLClassLoader(urls);
					System.out.println(text);
					Class<?> clazz = Class.forName(text, true, clazzloader);
					if(Entity.class.isAssignableFrom(clazz)){
						avalableEntitys.add(clazz);
						System.out.println("just added: " + clazz.getName());
					}
				}
				
			} catch (Exception e) {
				System.err.println("Something went wrong in this file: " + f.getAbsolutePath());
				e.printStackTrace();
			}
		}
	}
	
	public synchronized ArrayList<String> getEntityNames(){
		ArrayList<String> out = new ArrayList<String>();
		
		for(Class c: avalableEntitys){
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
		for(Class c: avalableEntitys){
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
		
		for(Class c : avalableEntitys){
			if(c.getName().equals(className)){
				try {
					System.out.println(c.getName());
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
	
	private List<File> getFiles(){
		List<File> files = new ArrayList<File>();
		
		String file;
		File folder = MapStructureCreator.entitys;
		File[] listOfFiles = folder.listFiles(); 
		for (int i = 0; i < listOfFiles.length; i++){
			if (listOfFiles[i].isFile()) {
				file = listOfFiles[i].getName();
				if (file.toLowerCase().endsWith(".jar")){
					files.add(listOfFiles[i]);
				}
			}
		}
		return files;
	}
}
