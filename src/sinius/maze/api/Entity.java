package sinius.maze.api;

import java.awt.Image;
import java.awt.Rectangle;

public interface Entity {

	void Create(int x, int y, String saveData);
	String getName();
	int getX();
	int getY();
	Image getFont();
	void onPlayerTouch();
	void onTick(Level l);
	Rectangle getCollisionBox();
	String getSaveData();
}
