package sinius.maze.state.solve;

import java.util.ArrayList;

import sinius.maze.Block;
import sinius.maze.Entity;
import sinius.maze.Level;
import sinius.maze.core.SynchroniezedList;
import sinius.maze.core.SynchroniezedList.editAction;
import sinius.maze.gameEngine.GObject;
import sinius.maze.plugin.Exit;
import sinius.maze.state.GameState;
import sinius.maze.state.GrapicsLayer;
import sinius.maze.state.playMode.Layer_Maze;
import solver.Grid;
import solver.Square;

public class SolveState implements GameState{

	private SynchroniezedList<GObject> gObjects = new SynchroniezedList<GObject>();
	private SynchroniezedList<GrapicsLayer> gLayers = new SynchroniezedList<GrapicsLayer>();
	
	private Grid solver;
	
	int outX = -1;
	int outY = -1;
	
	public SolveState(Level l){
		
		int inX, inY;
		
		l.getEntitys().doForAll(new editAction<Entity>() { @Override public void action(Entity o) {
			if(o.getClass().equals(Exit.class)){
				outX = o.getX();
				outY = o.getY();
			}
		}});
		
		inX = l.getSpawn().getX();
		inY = l.getSpawn().getY();
		
		Square startSq = null, endSq = null;
		
		ArrayList<Square> grid = new ArrayList<Square>();
		for(int x = 0 ; x < l.getWidth(); x++){
			for(int y = 0 ; y<l.getHeight(); y++){
				boolean b = l.getBlock(x, y).getType() == Block.WALL;
				if(x == inX && y == inY){
					startSq = new Square(x, y, 10, 10, b, true, false);
					grid.add(startSq);
					System.out.println("added startSq");
				}
				else if(x == outX && y == outY){
					endSq = new Square(x, y, 10, 10, b, false, true);
					grid.add(endSq);
					System.out.println("added EndSq");
				}else{
					grid.add(new Square(x, y, 10, 10, b, false, false));
				}
				
			}
		}
		
		solver = new Grid(grid, startSq, endSq);
		ArrayList<Square> path = solver.findRoute();
		
		boolean[][] blocks = new boolean[l.getWidth()][l.getHeight()];
		for(int x = 0 ; x < l.getWidth(); x++){
			for(int y = 0 ; y<l.getHeight(); y++){
				boolean isPath = false;
				for(Square r : path){
					if(r.getX() == x && r.getY() == y){
						isPath = true;
					}
				}
				
				blocks[x][y] = isPath;
				
			}
		}
		
		gLayers.add(new Layer_Path(blocks));
		gLayers.add(new Layer_Maze());
	}
	
	@Override
	public String getName() {
		return "solve";
	}

	@Override
	public void tick() {
	}

	@Override
	public SynchroniezedList<GObject> getGObjects() {
		return gObjects;
	}

	@Override
	public SynchroniezedList<GrapicsLayer> getGraphicsLayers() {
		return gLayers;
	}

	@Override
	public void mouseEvent(int button) {
	}

	@Override
	public void keyEvent(int button) {
	}

}
