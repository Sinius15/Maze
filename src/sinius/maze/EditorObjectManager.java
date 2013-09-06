package sinius.maze;

import java.awt.Graphics2D;
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

public class EditorObjectManager {
	
	private ArrayList<EditorObject> editorObj = new ArrayList<EditorObject>();
	
	@SuppressWarnings("resource")
	public void initEditorObj(){
		try {
			List<File> files = getFiles(".jar", MapStructureCreator.entitys.toPath());
			for(File f: files){
					URL path = new URL("jar:" + f.toURI().toURL() + "!/editorObj.txt");
					
					InputStream is = path.openStream();
					InputStreamReader isr = new InputStreamReader(is, "US-ASCII" );
					BufferedReader br = new BufferedReader(isr);
					String text;
					while((text = br.readLine()) != null){
						URL url = f.toURI().toURL();
						URL[] urls = new URL[]{url};
						ClassLoader clazzloader = new URLClassLoader(urls);
						Class<?> clazz = Class.forName(text, true, clazzloader);
						if(EditorObject.class.isAssignableFrom(clazz)){
							editorObj.add((EditorObject) clazz.newInstance());
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
				File entityTxt = new File(s + "/editorObj.txt");
				BufferedReader br = new BufferedReader(new FileReader(entityTxt));  
				String line = null;  
				while ((line = br.readLine()) != null){
					System.out.println("i read from the editorObj.txt: " + line);
					editorObj.add((EditorObject)Class.forName(line).newInstance());
				} 
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public synchronized void draw(Graphics2D g){
		for(EditorObject e: editorObj){
			e.render(g);
		}
	}
	
	public synchronized ArrayList<String> getNames(){
		ArrayList<String> out = new ArrayList<String>();
		for(EditorObject e: editorObj){
				out.add(e.getName());
		}
		return out;
	}
	
	public synchronized EditorObject getByName(String name){
		for(EditorObject e: editorObj){
			if(e.getName().equals(name))
				return e;
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
		return files;
	}
}
