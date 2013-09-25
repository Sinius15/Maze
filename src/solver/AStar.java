package solver;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import sinius.maze.Block;
import sinius.maze.Game;

public class AStar {

	boolean[][] maze;
	int[][] pathSize;
	int widht, height;
	Point in, out;
	Set<Point> lastChecked, toCheck;
	public boolean isSolvable;
	public String status;
	
	public AStar(boolean[][] input, int w, int h, Point in, Point out){
		maze = input;
		widht = w;
		height = h;
		this.in = in;
		this.out = out;
		
		pathSize = new int[widht][height];
		for(int x=0; x < widht; x++){
			for(int y=0; y < height; y++){
				pathSize[x][y] = -1;
			}
		}
		
	}
	
	public boolean[][] DumbSolve(){
		boolean[][] output = new boolean[widht][height];
		
		
		lastChecked = new HashSet<Point>();
		toCheck = new HashSet<Point>();
		
		lastChecked.add(in);
		
		int lock = 0;
		boolean running = true;
		while(running){
			toCheck.clear();
			for(Point p : lastChecked){
				for(Point q : getPointsAround(p)){
					doStuff(q);
				}
			}
			
			for(Point p : toCheck){
				output[p.x][p.y] = true;
				pathSize[p.x][p.y] = 1;
				status = "now checking " + p.toString(); 
				if(p.x == out.x && p.y == out.y){
					running = false;
					status = "found!";
					isSolvable = true;
					break;
				}
				
			}
			
			lastChecked = new HashSet<Point>(toCheck);
			lock++;
			System.out.println(lock);
			System.out.println("lastChecked: " + lastChecked.size() );
			if(lock > 999){
				status = "not found";
				isSolvable = false;
				break;
			}
		}
		
		
		return output;
	}
	
	public boolean[][] SmarSolve(){
		lastChecked = new HashSet<Point>();
		toCheck = new HashSet<Point>();
		
		lastChecked.add(in);
		
		int lock = 0;
		boolean running = true;
		while(running){
			toCheck.clear();
			for(Point p : lastChecked){
				for(Point q : getPointsAround(p)){
					doStuff(q);
				}
			}
			
			for(Point p : toCheck){
				if(p.x == out.x && p.y == out.y){
					pathSize[p.x][p.y] = 0;
					running = false;
					break;
				}
					
				int dX = p.x - out.x;
				int dY = p.y - out.y;
				if(String.valueOf(dX).startsWith("-"))
					dX = dX *-1;
				if(String.valueOf(dY).startsWith("-"))
					dY = dY *-1;
				pathSize[p.x][p.y] = dX + dY;
				
			}
			
			lastChecked = new HashSet<Point>(toCheck);
			lock++;
			if(lock > 999){
				break;
			}
		}
		
		boolean[][] output = new boolean[widht][height];
		running = true;
		Point j = new Point(out);
		while(running){
			for(Point p : getPointsAround(j)){
				
				if(pathSize[p.x][p.y] != -1){
					output[p.x][p.y] = true;
					j = new Point(p.x, p.y);
					break;
				}else{
					output[p.x][p.y]  = false;
				}
				
			}
				
			if(j.equals(in)){
				break;
			}
		}
		return output;
	}
	
	private boolean ifOutOfMaze(Point p){
		if(p.x < 0 || p.y<0 || p.x >= widht || p.y >= height){
			return true;
		}
		return false;
	}
	
	private void doStuff(Point temp){
		if(!ifOutOfMaze(temp)){
			if(pathSize[temp.x][temp.y] == -1){
				if(Game.get().level.getBlock(temp.x, temp.y).getType() == Block.AIR)
					toCheck.add(temp);
			}
		}
	}
	
	private ArrayList<Point> getPointsAround(Point p){
		ArrayList<Point> o = new ArrayList<>();
		
		o.add(new Point(p.x+1, p.y));
		o.add(new Point(p.x, p.y+1));
		o.add(new Point(p.x-1, p.y));
		o.add(new Point(p.x, p.y-1));
		
		return o;
	}
	
	@SuppressWarnings("unused")
	private Point getLowestPathLength(ArrayList<Point> list){
		Point o = null;
		int lowest= 99999;
		for(Point p : list){
			if(pathSize[p.x][p.y] < lowest){
				lowest = pathSize[p.x][p.y];
				o = p;
			}
		}
		
		return o;
	}
	
}
