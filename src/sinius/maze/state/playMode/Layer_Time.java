package sinius.maze.state.playMode;

import java.awt.Color;
import java.awt.Graphics2D;

import sinius.maze.state.GrapicsLayer;
import sinius.maze.timing.TimeTimer;

public class Layer_Time implements GrapicsLayer{

	TimeTimer timer;
	
	public Layer_Time(TimeTimer t){
		this.timer = t;
	}
	
	@Override
	public String getName() {
		return null;
	}

	@Override
	public void Draw(Graphics2D graphics) {
		graphics.setColor(Color.black);
		graphics.drawString(timer.getTime(), 100, 100);
	}

	@Override
	public int priority() {
		return 5;
	}

	
	
}
