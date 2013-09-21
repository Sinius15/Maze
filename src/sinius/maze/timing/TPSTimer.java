package sinius.maze.timing;

import java.util.Timer;
import java.util.TimerTask;

public class TPSTimer {

	private Timer timer = new Timer();
	private int updates = 0;
	private int tps = 0;
	
	public void Start(){
		timer.scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				tps = updates;
				updates = 0;
			}
		}, 1000, 1000);
	}
	
	public void Stop(){
		timer.cancel();
	}
	
	public int getTPS(){
		return tps;
	}
	
	public void registerTick(){
		updates++;
	}
	
}
