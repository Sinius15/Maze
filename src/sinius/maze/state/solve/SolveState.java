package sinius.maze.state.solve;

import java.awt.Point;

import sinius.maze.Block;
import sinius.maze.Entity;
import sinius.maze.Level;
import sinius.maze.core.SynchroniezedList;
import sinius.maze.core.SynchroniezedList.editAction;
import sinius.maze.gameEngine.GObject;
import sinius.maze.plugin.Exit;
import sinius.maze.state.GameState;
import sinius.maze.state.GrapicsLayer;
import sinius.maze.state.playMode.Layer_Entitys;
import sinius.maze.state.playMode.Layer_Maze;
import solver.AStar;

public class SolveState implements GameState{

	private SynchroniezedList<GObject> gObjects = new SynchroniezedList<GObject>();
	private SynchroniezedList<GrapicsLayer> gLayers = new SynchroniezedList<GrapicsLayer>();
	
	Point in, out;
	
	public SolveState(Level l){
		gLayers.add(new Layer_Maze());
		
		l.getEntitys().doForAll(new editAction<Entity>() { @Override public void action(Entity o) {
			if(o.getClass().equals(Exit.class)){
				out = new Point(o.getX(), o.getY());
			}
		}});
		
		in = new Point(l.getSpawn().getX(), l.getSpawn().getY());
		
		boolean[][] input = new boolean[l.getWidth()][l.getHeight()];

		for(int x=0; x < l.getWidth(); x++){
			for(int y=0; y < l.getHeight(); y++){
				if(l.getBlock(x, y).getType() == Block.WALL)
					input[x][y] = true;
				else
					input[x][y] = false;
			}
		}
		
		AStar solver = new AStar(input, l.getWidth(), l.getHeight(), in, out);
		
		
		gLayers.add(new Layer_Path(solver.DumbSolve(), out));
		gLayers.add(new Layer_Entitys());
		
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
