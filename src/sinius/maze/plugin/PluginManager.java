package sinius.maze.plugin;

import java.awt.Graphics2D;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import sinius.maze.EditorObject;
import sinius.maze.Entity;
import sinius.maze.Util;
import sinius.maze.io.YAMLFile;
import sinius.maze.lib.Folders;

public class PluginManager {

	private ArrayList<Plugin> plugins = new ArrayList<>();
	private HashMap<Plugin, String> pluginNames = new HashMap<>();
	
	private ArrayList<Class<?>> entitys = new ArrayList<>();
	private ArrayList<EditorObject> editorObj = new ArrayList<>();
	
	public void loadPlugins(){
		try {
			List<File> files = Util.getFileList(Folders.PLUGIN.getAbsolutePath());
			for(File f: files){
				if(!f.getName().endsWith(".jar"))
					return;
				URL path = new URL("jar:" + f.toURI().toURL() + "!/plugin.yml");
				
				YAMLFile pluginFile = new YAMLFile();
				pluginFile.Load(path);
				
				if(pluginFile.getString("name") == null || pluginFile.getString("main") == null){
					continue;
				}
				Plugin p = (Plugin)Class.forName(pluginFile.getString("main")).newInstance();
				plugins.add(p);
				pluginNames.put(p, pluginFile.getString("name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void disablePlugins(){
		for(Plugin p : plugins){
			p.onDisable();
		}
	}
	public void addEditorObject(EditorObject object){
		editorObj.add(object);
	}
	public void addEntity(Class<?> entity){
		entitys.add(entity);
	}
	public void initPlugins() {
		for(Plugin p : plugins)
			p.onEnable();
	}
	
	//entity things
	
	public ArrayList<String> getEntityNames(){
		ArrayList<String> out = new ArrayList<String>();
		for(Class<?> c: entitys){
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
	
	public Entity getEntityByName(String name){
		for(Class<?> c: entitys){
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
	
	public Entity getEntityByClass(String className){
		for(Class<?> c : entitys){
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

	//editor object things
	
	public synchronized void drawEditorObjects(Graphics2D g){
		for(EditorObject e: editorObj){
			e.render(g);
		}
	}
	
	public synchronized ArrayList<String> getEditorObjectNames(){
		ArrayList<String> out = new ArrayList<String>();
		for(EditorObject e: editorObj){
				out.add(e.getName());
		}
		return out;
	}
	
	public synchronized EditorObject getEditorObjectByName(String name){
		for(EditorObject e: editorObj){
			if(e.getName().equals(name))
				return e;
		}
		return null;
	}
	
	
}
