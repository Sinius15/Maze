package sinius.maze.io;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import sinius.maze.Game;

public class KeyHandler implements ComponentListener, KeyListener, MouseListener, MouseMotionListener{
	
	private ArrayList<Integer> pressedKeys = new ArrayList<Integer>();
	private ArrayList<Integer> pressedMouse = new ArrayList<Integer>();
	
	public int mousePosX = -1;
	public int mousePosY = -1;
	public int lastMousePosX = -1;
	public int lastMousePosY = -1;
	
	public boolean mouseMovedAfterDrag = false;
	
	public MouseEvent latestMouseEvent = null;
	
	public boolean isKeyPressed(int key){
		for(int i : pressedKeys){
			if(i == key){
				return true;
			}
		}
		return false;
	}
	
	public boolean isMousePressed(int mouse){
		for(int i : pressedMouse)
			if(i == mouse)
				return true;
		return false;
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		if(e.getX()/Game.ppb_x == mousePosX && e.getY()/Game.ppb_y == mousePosY){
			return;
		}
		mouseMovedAfterDrag = false;
		lastMousePosX = mousePosX;
		lastMousePosY = mousePosY;
		mousePosX = e.getX()/Game.ppb_x;
		mousePosY = e.getY()/Game.ppb_y;
		if(mousePosX >= Game.level.getWidth() || mousePosY >= Game.level.getHeight() || mousePosX < 0 || mousePosY < 0){
			mousePosX = -1;
			mousePosY = -1;
		}	
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if(e.getX()/Game.ppb_x == mousePosX && e.getY()/Game.ppb_y == mousePosY){
			return;
		}
		mouseMovedAfterDrag = true;
		mousePosX = e.getX()/Game.ppb_x;
		mousePosY = e.getY()/Game.ppb_y;
		if(mousePosX >= Game.level.getWidth() || mousePosY >= Game.level.getHeight() || mousePosX < 0 || mousePosY < 0){
			mousePosX = -1;
			mousePosY = -1;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		latestMouseEvent = e;
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {
		mousePosX = -1;
		mousePosY = -1;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(!pressedMouse.contains(e.getButton())){
			pressedMouse.add(e.getButton());
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(pressedMouse.contains(e.getButton())){
			pressedMouse.remove((Integer)e.getButton());
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(!pressedKeys.contains(e.getKeyCode())){
			pressedKeys.add(e.getKeyCode());
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(pressedKeys.contains(e.getKeyCode())){
			pressedKeys.remove((Integer)e.getKeyCode());
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void componentHidden(ComponentEvent arg0) {}

	@Override
	public void componentMoved(ComponentEvent arg0) {}

	@Override
	public void componentResized(ComponentEvent arg0) {}

	@Override
	public void componentShown(ComponentEvent arg0) {}

	
	
}
