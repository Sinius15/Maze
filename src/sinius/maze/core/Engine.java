package sinius.maze.core;

import sinius.maze.Game;


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
		System.out.println("Starting the gameloop");
	}
	
	public Runnable tickThread() {
		return new Runnable() { @Override public void run() {
			while(isRunning){
				Game.doTick();
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
				Game.fps.registerTick();
				Game.display.reDraw();
				try {
					Thread.sleep(17);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}		
			}
	}};}
	
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

	
	public void stopGame(){
		//TODO: save level
		
		isRunning = false;
	}
}
