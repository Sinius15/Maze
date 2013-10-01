package sinius.maze.state;

import java.awt.Color;
import java.awt.Graphics2D;

import sinius.maze.Game;
import sinius.maze.MainProgram;
import sinius.maze.lib.Folders;
import sinius.maze.lib.Layout;
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
		
		String state = Game.get().display.gameState.getName();
		
		g.setColor(Layout.OVERLAY_COLOR);
		g.fillRect(0, 0, 800, 150);	
		g.setColor(Color.white);
		g.setFont(Layout.MAIN_FONT);
		
		g.drawString("FPS: " + Game.get().fps.getFPS(), 700, 22);
		g.drawString("TPS: " + Game.get().tps.getTPS(), 700, 44);
		g.drawString("State: "+ Game.get().display.gameState.getName(), 400, 22);
		if(state.equals("play")){
			g.drawString("Player:" , 10, 22);
			g.drawString("x: " + MainProgram.game.getPlayer().getX() , 15, 42);
			g.drawString("y: " + MainProgram.game.getPlayer().getY() , 15, 62);
			
		}
		if(state.equals("Menu")){
			g.drawString("Dir: " + Folders.MAIN.getAbsolutePath() , 10, 82);
		}
		if(state.equals("play") || state.equals("editor"))
			g.drawString("Entitys: " + Game.get().level.getEntitys().size(), 10, 90);
		if(state.equals("solve")){
			g.drawString("Time busy with solving: " + ((SolveState)Game.get().display.gameState).timeToSolve + "ms", 10, 22);
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
