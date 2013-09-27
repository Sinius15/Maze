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
	
	public static ArrayList<Block> getBlocksAround(int x, int y, Level level){
		ArrayList<Block> out = new ArrayList<Block>();
		try{
			out.add(level.getBlocks()[x+1] [y+1]);
			out.add(level.getBlocks()[x+1] [y-1]);
			out.add(level.getBlocks()[x-1] [y+1]);
			out.add(level.getBlocks()[x-1] [y-1]);
			out.add(level.getBlocks()[x+1] [y]);
			out.add(level.getBlocks()[x]   [y+1]);
			out.add(level.getBlocks()[x-1] [y]);
			out.add(level.getBlocks()[x]   [y-1]);
		}catch(ArrayIndexOutOfBoundsException e){
		}
		
		
		return out;
	}
	
	public static ArrayList<File> getFileList(String path){
		ArrayList<File> files = new ArrayList<File>();
		File folder = new File(path);
		folder.getParentFile().mkdirs();
		File[] listOfFiles = folder.listFiles();
		if(listOfFiles == null)
			return null;
		for (int i = 0; i < listOfFiles.length; i++){
			if (listOfFiles[i].isFile()) {
				files.add(listOfFiles[i]);
			}
		}
		return files;
	}
	
	public static Level createEmptyLevel(int w1, int h1, String name, Color bgColor, Color blockColor, boolean fillLevel){
		Level l = new Level(w1, h1, name);
		
		l.setBackgroundColor(bgColor);
		l.setStandardBlockColor(blockColor);
		
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
