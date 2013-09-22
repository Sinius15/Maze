package solver;

import java.util.ArrayList;

public class Grid{
	
	private ArrayList<Square> grid, path;
	private Square startSq, endSq;
	private AStarAlgorithm alg;
	
	public Grid(ArrayList<Square> grid, Square startSq, Square endSq) {
		this.startSq = startSq;
		this.endSq = endSq;
		this.grid = grid;
		path = new ArrayList<Square>();
	}
	
	/** 
	 * Applies the A* algorithm to find the route based on the squares left as passable. 
	*/
	public ArrayList<Square> findRoute() {
		ArrayList<Square> passableGrid = new ArrayList<Square>();
		for ( Square s : grid ) {
			if ( s.isPassable() )
				passableGrid.add( s );
		}
		alg = new AStarAlgorithm( passableGrid, startSq, endSq, this, false);
		alg.findPath();
		path = alg.showPath();
		return path;
	}

}
