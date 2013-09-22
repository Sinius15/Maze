package solver;

public class Square {

	private int x, y, width, height;
	private boolean isPassable;
	private boolean isStart, isEnd;
	
	public Square( int x, int y, int width, int height, boolean isPassable, boolean isStart, boolean isEnd ) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.isPassable = isPassable;
		this.isStart = isStart;
		this.isEnd = isEnd;
	}
	public Square( Square s ) {
		this.x = s.x;
		this.y = s.y;
		this.width = s.width;
		this.height = s.height;
		this.isPassable = s.isPassable;
		this.isStart = s.isStart;
		this.isEnd = s.isEnd;
	}

	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public boolean isPassable() {
		return isPassable;
	}
	public void setPassable( boolean isPassable ) {
		this.isPassable = isPassable;
	}
	public boolean isStart() {
		return isStart;
	}
	public void setStart( boolean isStart ) {
		this.isStart = isStart;
	}
	public boolean isEnd() {
		return isEnd;
	}
	public void setEnd( boolean isEnd ) {
		this.isEnd = isEnd;
	}
	
	public boolean equals( Object o ) {
		if ( o == null || o.getClass() != getClass() )
			return false;
		else {
			Square tmp = (Square) o;
			return ( x == tmp.x && y == tmp.y );
		}
	}
	
	public String toString() {
		return ( x + " " + y );
	}
}
