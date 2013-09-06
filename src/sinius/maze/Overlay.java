package sinius.maze;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public interface Overlay {
	
	String getName();
	void Draw(Graphics2D graphics);
	void mouseClick(MouseEvent e);
	
}
