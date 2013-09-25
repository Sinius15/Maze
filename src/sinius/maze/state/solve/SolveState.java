package sinius.maze.state.solve;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import sinius.maze.Block;
import sinius.maze.Entity;
import sinius.maze.Game;
import sinius.maze.Level;
import sinius.maze.Util;
import sinius.maze.core.SynchroniezedList;
import sinius.maze.core.SynchroniezedList.editAction;
import sinius.maze.gameEngine.GButton;
import sinius.maze.gameEngine.GObject;
import sinius.maze.gameEngine.GText;
import sinius.maze.plugin.Exit;
import sinius.maze.state.GameState;
import sinius.maze.state.GrapicsLayer;
import sinius.maze.state.menu.MenuState;
import sinius.maze.state.playMode.Layer_Entitys;
import sinius.maze.state.playMode.Layer_Maze;
import solver.AStar;

public class SolveState implements GameState{

	private SynchroniezedList<GObject> gObjects = new SynchroniezedList<GObject>();
	private SynchroniezedList<GrapicsLayer> gLayers = new SynchroniezedList<GrapicsLayer>();
	
	Point in;
	
	public SolveState(Level l){
		
		gLayers.add(new Layer_Maze());
		
		final ArrayList<Point> exits = new ArrayList<>();
		
		l.getEntitys().doForAll(new editAction<Entity>() { @Override public void action(Entity o) {
			if(o.getClass().equals(Exit.class)){
				exits.add(new Point(o.getX(), o.getY()));
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
		
		boolean solveAble = false;
		
		for(Point e : exits){
			solver = new AStar(input, l.getWidth(), l.getHeight(), in, e);
			solver.DumbSolve();
		}
		
		
		GButton back = new GButton(0, 0, 50, 30);
		back.setButtonColor(Color.red);
		back.setTextColor(Color.black);
		back.setText("<=");
		back.setAction(new ActionListener() { @Override public void actionPerformed(ActionEvent e) {
			Game.get().display.setGameState(new MenuState());
		}});
		gObjects.add(back);
		
		
		gLayers.add(new Layer_Path(solver.DumbSolve()));
		gLayers.add(new Layer_Entitys());
		
		GText text = new GText("this level is solveable: " + solver.isSolvable, 60, 20);
		text.setColor(Color.black);
		text.setFont(Util.MAIN_FONT);
		gObjects.add(text);
		
		Game.get().display.setGameState(this);
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
