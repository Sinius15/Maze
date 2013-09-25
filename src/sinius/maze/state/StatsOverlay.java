package sinius.maze.state;

import java.awt.Color;
import java.awt.Graphics2D;

import sinius.maze.Game;
import sinius.maze.MainProgram;
import sinius.maze.Util;
import sinius.maze.state.solve.SolveState;

public class StatsOverlay implements GrapicsLayer{

	
	
	private static boolean show = false;
	
	@Override
	public String getName() {
		return "stats";
	}

	@Override
	public void Draw(Graphics2D g) {
		if(!show)
			return;
		g.setColor(Util.GRAY);
		g.fillRect(0, 0, 800, 150);	
		g.setColor(Color.white);
		g.setFont(Util.MAIN_FONT);
		
		g.drawString("FPS: " + Game.get().fps.getFPS(), 700, 22);
		g.drawString("TPS: " + Game.get().tps.getTPS(), 700, 44);
		g.drawString("State: "+ Game.get().display.gameState.getName(), 400, 22);
		if(Game.get().display.gameState.getName().equals("play")){
			g.drawString("Player:" , 10, 22);
			g.drawString("x: " + MainProgram.game.getPlayer().getX() , 15, 42);
			g.drawString("y: " + MainProgram.game.getPlayer().getY() , 15, 62);
			g.drawString("Entitys: " + Game.get().level.getEntitys().size(), 10, 90);
		}
		if(Game.get().display.gameState.getName().equals("solve")){
			g.drawString("Time buisy with solving: " + ((SolveState)Game.get().display.gameState).timeToSolve + "ms", 10, 22);
		}
		
	}

	@Override
	public int priority() {
		return 10;
	}

	@Override
	public boolean drawAfter() {
		return true;
	}

	public static void show() {
		show = !show;
	}
	
	public static boolean isShow(){
		return show;
	}

}
