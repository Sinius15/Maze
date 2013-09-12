package sinius.maze.state.editMode;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import sinius.maze.Game;

public class EditKeyListener implements KeyListener{

	@Override
	public void keyPressed(KeyEvent event) {
		if(event.getKeyCode() == KeyEvent.VK_UP)
			Game.player.up(Game.level);
		if(event.getKeyCode() == KeyEvent.VK_DOWN)
			Game.player.down(Game.level);
		if(event.getKeyCode() == KeyEvent.VK_LEFT)
			Game.player.left(Game.level);
		if(event.getKeyCode() == KeyEvent.VK_RIGHT)
			Game.player.right(Game.level);
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

}
