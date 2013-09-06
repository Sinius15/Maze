package sinius.maze;

import java.awt.Color;
import java.awt.geom.Line2D;
import java.io.File;
import java.util.ArrayList;

public class Util {

	public static ArrayList<Block> getCrossedBlocks(int x1, int y1, int x2, int y2, Level level){
		
		ArrayList<Block> out = new ArrayList<Block>();
		
		Line2D line = new Line2D.Float(x1, y1, x2, y2);
		
		for(int x = 0; x < level.getWidth(); x++){
			for(int y = 0; y< level.getHeight(); y++){
				if(line.intersects(x, y, 1, 1)){
					out.add(level.getBlock(x, y));
				}
			}
		}
		
		return out;
	}
	
	public static ArrayList<File> getFileList(String path){
		ArrayList<File> files = new ArrayList<File>();
		File folder = new File(path);
		folder.getParentFile().mkdirs();
		System.out.println(folder.getAbsolutePath());
		File[] listOfFiles = folder.listFiles(); 
		System.out.println(listOfFiles.length);
		for (int i = 0; i < listOfFiles.length; i++){
			if (listOfFiles[i].isFile()) {
				files.add(listOfFiles[i]);
			}
		}
		return files;
	}
	
	public static Level createEmptyLevel(int size, String name, Color bgColor, Color bColor, boolean fillLevel){
		Level l = new Level(size, size, name);
		
		l.setBackgroundColor(bgColor);
		l.setStandardBlockColor(bColor);
		
		if(fillLevel){
			for(int w = 0; w < l.getWidth(); w++){
				for(int h = 0; h < l.getHeight(); h++){
					l.getBlock(w, h).setType(Block.WALL);
					l.getBlock(w, h).setColor(l.getStandardBlockColor());
				}
			}
		}

		
		return l;
	}
	
}
