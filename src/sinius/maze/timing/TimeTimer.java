package sinius.maze.timing;

import java.util.Timer;
import java.util.TimerTask;

public class TimeTimer{

	private Timer timer = new Timer();
	private int min = 0;
	private int sec = 0;
	
	public void Start(){
		timer.scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				sec++;
				if(sec == 60){
					min++;
					sec= 0;
				}
			}
		}, 1000, 1000);
	}
	
	public void Stop(){
		timer.cancel();
	}
	
	public String getTime(){
		return twoDigitString(min) + ":" + twoDigitString(sec);
	}
	
	private static String twoDigitString(int number){
	    if (number == 0) 
	      return "00";
	    if (number / 10 == 0) 
	      return "0" + number;
	    return String.valueOf(number);
	  }
}
