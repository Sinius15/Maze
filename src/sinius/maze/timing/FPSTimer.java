package sinius.maze.timing;

import java.util.Timer;
import java.util.TimerTask;

public class FPSTimer {

	private Timer timer = new Timer();
	private int updates = 0;
	private int fps = 0;
	
	public void Start(){
		timer.scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				fps = updates;
				updates = 0;
			}
		}, 1000, 1000);
	}
	
	public void Stop(){
		timer.cancel();
	}
	
	public int getFPS(){
		return fps;
	}
	
	public void registerTick(){
		updates++;
	}
	
}
