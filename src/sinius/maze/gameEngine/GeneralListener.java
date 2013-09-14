package sinius.maze.gameEngine;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import sinius.maze.Game;
import sinius.maze.core.SynchroniezedList;

public class GeneralListener implements ComponentListener, KeyListener, MouseListener, MouseMotionListener{

	public SynchroniezedList pressedKeys = new SynchroniezedList();
	public SynchroniezedList pressedMouse = new SynchroniezedList();

	public boolean mouseMovedAfterDrag = false;

	public MouseEvent latestMouseEvent = null;

	@Override
	public void mouseDragged(MouseEvent event) {
		if(event.getX() == Game.mouseX && event.getY() == Game.mouseY){
			return;
		}
		
		if(event.getX() <800 && event.getX()>=0 && event.getY() <800 && event.getY()>=0){
			Game.latestMouseX = Game.mouseX;
			Game.latestMouseY = Game.mouseY;
			Game.mouseX = event.getX();
			Game.mouseY = event.getY();
			Game.mouseDrag = false;
		}else{
			Game.latestMouseX = -1;
			Game.mouseX = -1;
			Game.mouseY = -1;
		}
	}

	@Override
	public void mouseMoved(MouseEvent event) {
		if(event.getX() == Game.mouseX && event.getY() == Game.mouseY){
			return;
		}
		
		if(event.getX() <800 && event.getX()>=0 && event.getY() <800 && event.getY()>=0){
			Game.latestMouseX = Game.mouseX;
			Game.latestMouseY = Game.mouseY;
			Game.mouseX = event.getX();
			Game.mouseY = event.getY();
			Game.mouseDrag = true;
		}else{
			Game.latestMouseX = -1;
			Game.mouseX = -1;
			Game.mouseY = -1;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
		Game.mouseX = -1;
		Game.mouseY = -1;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(!pressedMouse.contains((Integer)e.getButton())){
			pressedMouse.add((Integer)e.getButton());
		}
			
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(pressedMouse.contains((Integer)e.getButton())){
			pressedMouse.remove((Integer)e.getButton());
		}
			
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(!pressedKeys.contains(e.getKeyCode()))
			pressedKeys.add(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(pressedKeys.contains(e.getKeyCode()))
			pressedKeys.remove((Integer)e.getKeyCode());
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void componentHidden(ComponentEvent arg0) {
	}

	@Override
	public void componentMoved(ComponentEvent arg0) {
	}

	@Override
	public void componentResized(ComponentEvent arg0) {
	}

	@Override
	public void componentShown(ComponentEvent arg0) {
	}

}
