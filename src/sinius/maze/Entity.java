package sinius.maze;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;

public interface Entity {

	void Create(int x, int y);
	String getName();
	int getX();
	int getY();
	Image getFont()  throws IOException;
	void onPlayerTouch();
	void onTick(Level l);
	Rectangle getCollisionBox();
}
