package sinius.maze.core;


public class Engine{

	boolean isRunning = true;
	Thread tickThread;
	Thread drawThread;
	Thread saveThread;
	SynchroniezedList list = new SynchroniezedList();
	
	public Engine(){
		list.add("lalala");
		tickThread = new Thread(tickThread(), "tickThread");
		tickThread.start();
		
		drawThread = new Thread(drawThread(), "drawThread");
		drawThread.start();
		
		saveThread = new Thread(saveThread(), "saveThread");
		saveThread.start();
		System.out.println("");
	}
	
	public Runnable tickThread() {
		return new Runnable() { @Override public void run() {
			while(isRunning){
				//TODO: tick
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
	}};}
	
	public Runnable drawThread() {
		return new Runnable() { @Override public void run() {
			while(isRunning){
				//TODO: add a draw functions
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}		
			}
	}};}
	
	int i = 0;
	
	public Runnable saveThread() {
		return new Runnable() { @Override public void run() {
			while(isRunning){
				//TODO: add a autosavefunction 
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
	}};}

}
