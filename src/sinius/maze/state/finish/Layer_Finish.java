package sinius.maze.state.finish;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import sinius.maze.state.GrapicsLayer;

public class Layer_Finish implements GrapicsLayer{

	static Color gray = new Color(0f, 0f, 0f, 0.85f);
	static Font font = new Font("Zolano Serif BTN", Font.PLAIN, 25);
	
	String time;
	
	Layer_Finish(String time){
		this.time = time;
	}
	
	@Override
	public String getName() {
		return "finish";
	}

	@Override
	public void Draw(Graphics2D g) {
		g.setColor(gray);
		g.fillRect(0, 0, 800, 800);	
		g.setColor(Color.white);
		g.setFont(font);
		
		g.drawString("Congratulations!", 200, 200);
		g.drawString("You finished this maze.", 200, 230);
		
		g.drawString("Time: " + time, 200, 300);
		
	}

	@Override
	public int priority() {
		return 9;
	}

	@Override
	public boolean drawAfter() {
		return false;
	}


}
