package sinius.maze;

import java.awt.Graphics2D;

public interface EditorObject{

	void mousePress();
	void mouseRelease();
	void render(Graphics2D graphics);
	String getName();
	
}
