package solver;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

public class AStarAlgorithm {

	private Square start, goal;
	private ArrayList<Square> closedSet;
	private ArrayList<Square> openSet;
	private Hashtable<String,Integer> gScore;
	private Hashtable<String,Integer> hScore;
	private Hashtable<String,Integer> fScore;
	private Hashtable<String,Square> cameFrom;
	private double runtime;
	private boolean eightDirections;
	
	public AStarAlgorithm( ArrayList<Square> openSet, Square start, Square goal, Grid parent, boolean eightDirections ) {
		this.eightDirections = eightDirections;
		this.runtime = 0.0;
		this.start = new Square( start );
		this.goal = new Square( goal );
		this.closedSet = new ArrayList<Square>();
		this.openSet = new ArrayList<Square>( openSet );
		this.gScore = new Hashtable<String,Integer>(); gScore.put( this.start.toString(), 0 );
		this.hScore = new Hashtable<String,Integer>(); hScore.put( this.start.toString(), heuristicEstimateOfDistance( this.start, this.goal ) );
		this.fScore = new Hashtable<String,Integer>(); fScore.put( this.start.toString(), hScore.get( this.start.toString() ) );
		this.cameFrom = new Hashtable<String,Square>();
		
		System.out.println("goal: " + goal.getX());
		System.out.println("start: "+ start.getX());
	}
	
	/** Returns the reconstructed path. */
	public ArrayList<Square> showPath() {
		return reconstructPath( cameFrom, goal );
	}
	
	/** Return the total runtime of the algorithm. */
	public double getRunTime() {
		return runtime;
	}
	
	/** Reconstruct the path by going through the already visited squares. */
	private ArrayList<Square> reconstructPath( Hashtable<String,Square> cameFrom2, Square currentNode ) {
		if ( cameFrom2.containsKey( currentNode.toString() ) ) {
			ArrayList<Square> p = reconstructPath( cameFrom2, cameFrom2.get( currentNode.toString() ) );
			p.add( currentNode );
			return p;
		}
		return new ArrayList<Square>();
	}

	/** Main algorithm of the program. Finds the shortest route based on the squares
	 * which are selected as passable and also the start and end points. */
	public void findPath() {
		int counter = 1;
		long t1 = System.currentTimeMillis();
		long t2;
		while ( openSet.size() > 0 ) {
			Square x = findSmallest( fScore );
			if ( x.equals( goal ) ) {
				t2 = System.currentTimeMillis();
				runtime = ( t2 - t1 ) / 1000.0;
			}
			openSet.remove( x );
			fScore.remove( x.toString() );
			closedSet.add( x );
			for ( Square y : neighbourNodes( x ) ) {
				System.out.println(y.toString());
				if ( closedSet.contains( y ) )
					continue;
				gScore.put( y.toString(), counter );
				counter++;
				int tentativeGScore = gScore.get( x.toString() ) + (int) distBetween( x, y );
				boolean tentativeIsBetter = false;
				if ( !openSet.contains( y ) ) {
					openSet.add( y );
					tentativeIsBetter = true;
				}
				else if ( tentativeGScore <= gScore.get( y.toString() ) )
					tentativeIsBetter = true;
				else
					tentativeIsBetter = false;
				if ( tentativeIsBetter ) {
					cameFrom.put( y.toString(), x );
					gScore.put( y.toString(), tentativeGScore );
					hScore.put( y.toString(), heuristicEstimateOfDistance( y, this.goal ) );
					fScore.put( y.toString(), gScore.get( y.toString() ) + hScore.get( y.toString() ) );
				}
			}
		}
	}
	
	/** Finds the distance between two squares. */
	private double distBetween( Square x, Square y ) {
		int distX1 = 0, distX2 = 0, distY1 = 0, distY2 = 0;
		int x1 = x.getX(), x2 = y.getX();
		int y1 = x.getY(), y2 = y.getY();

		while ( x1 != 0 ) {
            distX1++;
            x1 -= x.getWidth();
		}
        while ( x2 != 0 ) {
            distX2++;
            x2 -= x.getWidth();
        }
        while ( y1 != 0 ) {
            distY1++;
            y1 -= y.getHeight();
        }
        while ( y2 != 0 ) {
            distY2++;
            y2 -= y.getHeight();
        }
        return Math.sqrt( Math.pow( ( distX2 - distX1 ), 2 ) + Math.pow( ( distY2 - distY1 ), 2 ) );
	}

	/** Returns all neighboring nodes to x, depending on the number of possible directions. */
	private ArrayList<Square> neighbourNodes( Square x ) {
		ArrayList<Square> neighbors = new ArrayList<Square>();
		
		int tmp = x.getX() - x.getWidth();
		for ( Square l : openSet ) {
			if ( l.getX() == tmp && l.getY() == x.getY() )
				neighbors.add( l );
		}
		tmp = x.getX() + x.getWidth();
		for ( Square r : openSet ) {
			if ( r.getX() == tmp && r.getY() == x.getY() )
				neighbors.add( r );
		}
		tmp = x.getY() - x.getHeight();
		for ( Square u : openSet ) {
			if ( u.getX() == x.getX() && u.getY() == tmp )
				neighbors.add( u );
		}
		tmp = x.getY() + x.getHeight();
		for ( Square d : openSet ) {
			if ( d.getX() == x.getX() && d.getY() == tmp )
				neighbors.add( d );
		}	
		if ( eightDirections ) {
			for ( Square nw : openSet ) {
				if ( nw.getX() == x.getX() - x.getWidth() && nw.getY() == x.getY() - x.getHeight() )
					neighbors.add( nw );
			}
			for ( Square ne : openSet ) {
				if ( ne.getX() == x.getX() + x.getWidth() && ne.getY() == x.getY() - x.getHeight() )
					neighbors.add( ne );
			}
			for ( Square sw : openSet ) {
				if ( sw.getX() == x.getX() - x.getWidth() && sw.getY() == x.getY() + x.getHeight() )
					neighbors.add( sw );
			}
			for ( Square se : openSet ) {
				if ( se.getX() == x.getX() + x.getWidth() && se.getY() == x.getY() + x.getHeight() )
					neighbors.add( se );
			}
		}
		return neighbors;
	}

	/** Finds the key with the smallest value in 'nodes'. */
	private Square findSmallest( Hashtable<String,Integer> nodes ) {
		Object[] values = nodes.values().toArray();
		int smallest = 0;
		try {
			smallest = (Integer) values[ 0 ];
		} catch ( Exception e ) {
		}
		for ( Object o : values ) {
			if ( (Integer) o < smallest )
				smallest = (Integer) o;
		}
		Enumeration<String> e = nodes.keys();
		while ( e.hasMoreElements() ) {
			String el = e.nextElement();
			if ( nodes.get( el ) == smallest ) {
				String[] coords = el.split( " " );
				int x = Integer.parseInt( coords[ 0 ] );
				int y = Integer.parseInt( coords[ 1 ] );
				Square tmp = new Square( x, y, 21, 21, true, false, false );
				return tmp;
			}
		}
		return null;
	}

	/** Computes a heuristic estimate of the distance between s1 and s2, i.e the number
	 * of squares needed to go from s1 to s2. */
	private int heuristicEstimateOfDistance( Square s1, Square s2 ) {
		int result = 0;
		int x1 = s1.getX(), x2 = s2.getX();
		int y1 = s1.getY(), y2 = s2.getY();
		
		if ( x1 <= x2 ) {
			while ( x1 != x2 ) {
				result++;
				x1 += start.getWidth();
			}
		}
		else if ( x2 < x1 ) {
			while ( x2 != x1 ) {
				result++;
				x2 += start.getWidth();
			}
		}
		if ( y1 <= y2 ) {
			while ( y1 != y2 ) {
				result++;
				y1 += start.getHeight();
			}
		}
		else if ( y2 < y1 ) {
			while ( y2 != y1 ) {
				result++;
				y2 += start.getWidth();
			}
		}
		return result;
	}
}