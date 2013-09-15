package sinius.maze.state.finish;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import sinius.maze.state.GrapicsLayer;
import sinius.maze.timing.TimeTimer;

public class FinishLayer implements GrapicsLayer{

	static Color gray = new Color(0f, 0f, 0f, 0.85f);
	static Font font = new Font("Zolano Serif BTN", Font.PLAIN, 25);
	
	TimeTimer time;
	
	FinishLayer(TimeTimer time){
		this.time = time;
		time.Start();
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
		
		g.drawString("Press the X to exit!", 200, 260);
		g.drawString("Time: " + time.getTime(), 200, 300);
		
	}

	@Override
	public int priority() {
		return 9;
	}


}
