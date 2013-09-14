package sinius.maze;

import java.awt.Graphics2D;

public interface EditorObject{

	void mouseClick(int button);
	void render(Graphics2D graphics);
	String getName();
	
}
