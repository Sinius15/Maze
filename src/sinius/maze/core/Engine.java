package sinius.maze.core;

import sinius.maze.Game;

public class Engine{

	boolean isRunning = true;
	Thread tickThread;
	Thread drawThread;
	Thread saveThread;
	
	public Engine(){
		tickThread = new Thread(tickThread(), "tickThread");
		tickThread.start();
		
		drawThread = new Thread(drawThread(), "drawThread");
		drawThread.start();
		
		saveThread = new Thread(saveThread(), "saveThread");
		saveThread.start();
	}
	
	public Runnable tickThread() {
		return new Runnable() { @Override public void run() {
			while(isRunning){ 
				Game.get().tps.registerTick();
				Game.get().doTick();
				try {
					Thread.sleep(10);   //aiming for 100tps
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
	}};}
	
	public Runnable drawThread() {
		return new Runnable() { @Override public void run() {
			while(isRunning){
				Game.get().fps.registerTick();
				Game.get().display.reDraw();
				try {
					Thread.sleep(17);	//aiming for 60fps
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
		isRunning = false;
	}
}
