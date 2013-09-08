package sinius.maze.gameEngine;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public interface GObject {

	public void Draw(Graphics2D g);
	public void MouseClick(MouseEvent event);
	public void KeyClick(KeyEvent event);
	
}
