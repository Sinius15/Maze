package sinius.maze.state.editMode;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import sinius.maze.core.SynchroniezedList;
import sinius.maze.state.GameState;

public class EditState implements GameState{

	private SynchroniezedList gObjects = new SynchroniezedList();
	private SynchroniezedList gLayers = new SynchroniezedList();
	private KeyListener keys = new KeyListener() {
		
		@Override
		public void keyTyped(KeyEvent arg0) {
			
		}
		
		@Override
		public void keyReleased(KeyEvent arg0) {
		}
		
		@Override
		public void keyPressed(KeyEvent arg0) {
		}
	};
	private MouseListener mouse = new MouseListener() {
		
		@Override
		public void mouseReleased(MouseEvent arg0) {
			
		}
		
		@Override
		public void mousePressed(MouseEvent arg0) {
		}
		
		@Override
		public void mouseExited(MouseEvent arg0) {
		}
		
		@Override
		public void mouseEntered(MouseEvent arg0) {
		}
		
		@Override
		public void mouseClicked(MouseEvent arg0) {
		}
	};
	
	@Override
	public String getName() {
		return "Editor";
	}

	@Override
	public SynchroniezedList getGObjects() {
		return gObjects;
	}

	@Override
	public SynchroniezedList getGraphicsLayers() {
		return gLayers;
	}

	@Override
	public MouseListener getMouseListener() {
		return mouse;
	}

	@Override
	public KeyListener getKeyListener() {
		return keys;
	}

}
