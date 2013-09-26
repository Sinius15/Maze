package sinius.maze;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import sinius.maze.entitys.Player;

public interface Entity{
	
	void Create(int x, int y, String saveData);		//
	String getName();								//done
	int getX();										//
	int getY();										//		
	void onPlayerTouch(Player p);					//
	void onTick(Level l);							//
	void advancedRender(Graphics2D graphics);
	boolean onGrid();								//
	Dimension getSize();							//
	Image getFont();								//
	Rectangle getCollisionBox();					//
	String getSaveData();							//

}
