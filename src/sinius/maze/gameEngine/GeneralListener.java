package sinius.maze.gameEngine;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import sinius.maze.Game;
import sinius.maze.MainProgram;

public class GeneralListener implements ComponentListener, KeyListener, MouseListener, MouseMotionListener{

	@Override
	public void mouseDragged(MouseEvent event) {
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
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
			MainProgram.engine.stopGame();
		}
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
