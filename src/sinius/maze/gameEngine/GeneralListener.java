package sinius.maze.gameEngine;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

import sinius.maze.Game;
import sinius.maze.core.SynchroniezedList;
import sinius.maze.state.StatsOverlay;
import sinius.maze.state.levelChoser.LevelChoserState;

public class GeneralListener implements ComponentListener, KeyListener, MouseListener, MouseMotionListener{

	public SynchroniezedList<Integer> pressedKeys = new SynchroniezedList<Integer>();
	public SynchroniezedList<Integer> pressedMouse = new SynchroniezedList<Integer>();

	@Override
	public void mouseDragged(MouseEvent event) {
		JFrame frame = Game.get().display.getFrame();
		float zoomX = (frame.getWidth()-frame.getInsets().left-frame.getInsets().right)/800f;
		float zoomY = (frame.getHeight()-frame.getInsets().top-frame.getInsets().bottom)/800f;
		
		int mouseX = (int) (event.getX()/zoomX);
		int mouseY = (int) (event.getY()/zoomY);
		
		if(mouseX == Game.get().mouseX && mouseY == Game.get().mouseY){
			return;
		}
		
		if(mouseX <800 && mouseX>=0 && mouseY <800 && mouseY>=0){
			Game.get().latestMouseX = Game.get().mouseX;
			Game.get().latestMouseY = Game.get().mouseY;
			Game.get().mouseX = mouseX;
			Game.get().mouseY = mouseY;
		}else{
			Game.get().latestMouseX = -1;
			Game.get().mouseX = -1;
			Game.get().mouseY = -1;
		}
	}

	@Override
	public void mouseMoved(MouseEvent event) {
		JFrame frame = Game.get().display.getFrame();
		float zoomX = (frame.getWidth()-frame.getInsets().left-frame.getInsets().right)/800f;
		float zoomY = (frame.getHeight()-frame.getInsets().top-frame.getInsets().bottom)/800f;
		
		int mouseX = (int) (event.getX()/zoomX);
		int mouseY = (int) (event.getY()/zoomY);
		
		if(mouseX == Game.get().mouseX && mouseY == Game.get().mouseY){
			return;
		}
		
		if(mouseX <800 && mouseX>=0 && mouseY <800 && mouseY>=0){
			Game.get().latestMouseX = Game.get().mouseX;
			Game.get().latestMouseY = Game.get().mouseY;
			Game.get().mouseX = mouseX;
			Game.get().mouseY = mouseY;
		}else{
			Game.get().latestMouseX = -1;
			Game.get().mouseX = -1;
			Game.get().mouseY = -1;
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
		Game.get().mouseX = -1;
		Game.get().mouseY = -1;
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
		if(!pressedKeys.contains((Integer)e.getKeyCode()))
			pressedKeys.add((Integer)e.getKeyCode());
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(pressedKeys.contains((Integer)e.getKeyCode()))
			pressedKeys.remove((Integer)e.getKeyCode());
		if(e.getKeyCode() == KeyEvent.VK_F3)
			StatsOverlay.show();
		
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
		if(Game.get().display.gameState.getName().equals("levelChoser")){
			((LevelChoserState) Game.get().display.gameState).refresh();
		}
	}


}
