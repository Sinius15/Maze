package sinius.maze.io;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;

import sinius.maze.Entity;
import sinius.maze.Game;
import sinius.maze.Level;
import sinius.maze.Util;
import sinius.maze.entitys.Spawn;

public class LevelLoader {

	private static YAMLFile saveFile = new YAMLFile();
	
	public static void SaveLevel(Level l, String place) throws Exception{
		saveFile.addInt("levelWidht", l.getWidth());
		saveFile.addInt("levelHeight", l.getHeight());
		saveFile.addString("levelName", l.getName());
		saveFile.addInt("backgroundColor.red", l.getBackgroundColor().getRed());
		saveFile.addInt("backgroundColor.green", l.getBackgroundColor().getGreen());
		saveFile.addInt("backgroundColor.blue", l.getBackgroundColor().getBlue());
		
		saveFile.addInt("standardBlockColor.red", l.getStandardBlockColor().getRed());
		saveFile.addInt("standardBlockColor.green", l.getStandardBlockColor().getGreen());
		saveFile.addInt("standardBlockColor.blue", l.getStandardBlockColor().getBlue());
		
		saveFile.addString("standardBlockColor", String.valueOf(l.getStandardBlockColor().getRed()));
		
		for(int w = 0; w < l.getWidth(); w++){
			for(int h = 0; h < l.getHeight(); h++){
				saveFile.addInt("blocks.x" + w + ".y" + h + ".type", l.getBlock(w, h).getType());
				saveFile.addInt("blocks.x" + w + ".y" + h + ".color", l.getBlock(w, h).getColor().getRGB());
			}
		}
		
		saveFile.addInt("entityAmount", l.getEntitys().size());
		int i = 0;
		for(Entity t : l.getEntitys()){
			saveFile.addInt("entitys." + i + ".x", t.getX());
			saveFile.addInt("entitys." + i + ".y", t.getY());
			//saveFile.addString("entitys." + i + ".type", t.getName());
			saveFile.addString("entitys." + i + ".class",t.getClass().getName());
			i++;
		}
		
		saveFile.addInt("spawn.x", l.getSpawn().getX());
		saveFile.addInt("spawn.y", l.getSpawn().getY());
		
		saveFile.Save(new File(place));
	}
	
	public static Level LoadLevel(File f){
		saveFile.Load(f);
		
		Level level = new Level(saveFile.getInt("levelWidht"), saveFile.getInt("levelHeight"), saveFile.getString("levelName"));
		System.out.println(saveFile.getString("levelName"));
		
		Game.ppb_x = 800 / level.getWidth();
		Game.ppb_y = 800 / level.getHeight();
		
		for(int w = 0; w < level.getWidth(); w++){
			for(int h = 0; h < level.getHeight(); h++){
				level.getBlock(w, h).setType(saveFile.getInt("blocks.x" + w + ".y" + h + ".type"));
				level.getBlock(w, h).setColor(new Color(saveFile.getInt("blocks.x" + w + ".y" + h + ".color")));
			}
		}
		
		for(int i = 0; i<saveFile.getInt("entityAmount"); i++){
			//String name = saveFile.getString("entitys." + i + ".type");
			try {
				Class<?> e = Class.forName(saveFile.getString("entitys." + i + ".class"));
				System.out.println(e.getName());
				if(!Entity.class.isAssignableFrom(e)){
					continue;
				}
				Entity entity = (Entity) e.newInstance();
				entity.Create(saveFile.getInt("entitys." + i + ".x"), saveFile.getInt("entitys." + i + ".y"));
				level.editEntitys("add", null, entity , null, 0, 0, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		int r, g, b;
		r = saveFile.getInt("backgroundColor.red");
		g = saveFile.getInt("backgroundColor.green");
		b = saveFile.getInt("backgroundColor.blue");
		level.setBackgroundColor(new Color(r,g,b));
		
		r = saveFile.getInt("standardBlockColor.red");
		g = saveFile.getInt("standardBlockColor.green");
		b = saveFile.getInt("standardBlockColor.blue");
		level.setStandardBlockColor(new Color(r,g,b));
		
		Spawn s = new Spawn();
		s.Create(saveFile.getInt("spawn.x"), saveFile.getInt("spawn.y"));
		level.setSpawn(s);
		
		return level;
	}
	
	public static ArrayList<Level> getLevelList(String path){
		ArrayList<Level> out = new ArrayList<Level>();
		
		for(File f : Util.getFileList(path)){
			out.add(LoadLevel(f));
		}
		
		return out;
	}
}
