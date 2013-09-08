package sinius.maze.graphicsLayer;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import sinius.maze.EditorObject;
import sinius.maze.Game;
import sinius.maze.GraphicsLayer;
import sinius.maze.MainProgram;

public class EditorOptionLayer implements GraphicsLayer{

	@Override
	public String getName() {
		return "editorOptionLayer";
	}

	@Override
	public void Draw(Graphics2D graphics) {
		MainProgram.editorObjManager.draw(graphics);
	}

	@Override
	public void mouseClick(MouseEvent e) {
		EditorObject o = MainProgram.editorObjManager.getByName(Game.options.getBrush());
		if(o != null)
			o.mouseClick(e);
	}

	@Override
	public int priority() {
		return 4;
	}

}
