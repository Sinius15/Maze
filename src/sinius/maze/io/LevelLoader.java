package sinius.maze.io;

import java.awt.Color;
import java.io.File;

import sinius.maze.Entity;
import sinius.maze.Game;
import sinius.maze.Level;
import sinius.maze.MainProgram;
import sinius.maze.core.SynchroniezedList.editAction;
import sinius.maze.entitys.Spawn;

public class LevelLoader {

	private static YAMLFile saveFile = new YAMLFile();
	
	static int i = 0;
	
	public static void SaveLevel(Level l, String place) throws Exception{
		saveFile = new YAMLFile();
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
		i = 0;
		
		l.getEntitys().doForAll(new editAction<Entity>() { @Override public void action(Entity t) {
			saveFile.addInt("entitys." + i + ".x", t.getX());
			saveFile.addInt("entitys." + i + ".y", t.getY());
			saveFile.addString("entitys." + i + ".class",t.getClass().getName());
			if(t.getSaveData() != null)
				saveFile.addString("entitys." + i + ".data", t.getSaveData());
			i++;
			System.out.println("saved a entity: " + t.getName());
		}});
		
		saveFile.addInt("spawn.x", l.getSpawn().getX());
		saveFile.addInt("spawn.y", l.getSpawn().getY());
		
		saveFile.Save(new File(place));
	}
	
	public static Level LoadLevel(File f){
		saveFile = new YAMLFile();
		saveFile.Load(f);
		
		Level level = new Level(saveFile.getInt("levelWidht"), saveFile.getInt("levelHeight"), saveFile.getString("levelName"));
		
		Game.get().ppb_x = 800 / level.getWidth();
		Game.get().ppb_y = 800 / level.getHeight();
		
		for(int w = 0; w < level.getWidth(); w++){
			for(int h = 0; h < level.getHeight(); h++){
				level.getBlock(w, h).setType(saveFile.getInt("blocks.x" + w + ".y" + h + ".type"));
				level.getBlock(w, h).setColor(new Color(saveFile.getInt("blocks.x" + w + ".y" + h + ".color")));
			}
		}
		
		for(int i = 0; i<saveFile.getInt("entityAmount"); i++){
			try {
				Entity entity = MainProgram.entityManager.getEntityByClass(saveFile.getString("entitys." + i + ".class"));
				if(saveFile.getString("entitys." + i + ".data") == null){
					entity.Create(saveFile.getInt("entitys." + i + ".x"), saveFile.getInt("entitys." + i + ".y"), "");
				}else{
					entity.Create(saveFile.getInt("entitys." + i + ".x"), saveFile.getInt("entitys." + i + ".y"), saveFile.getString("entitys." + i + ".data"));
				}
				level.getEntitys().add(entity);
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
		s.Create(saveFile.getInt("spawn.x"), saveFile.getInt("spawn.y"), "");
		level.setSpawn(s);
		
		return level;
	}
}
