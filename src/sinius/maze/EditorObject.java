package sinius.maze;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public interface EditorObject{

	void mouseClick(MouseEvent e);
	void render(Graphics2D graphics);
	String getName();
	
}
