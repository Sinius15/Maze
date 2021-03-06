package sinius.maze.state.editMode;

import java.awt.Graphics2D;

import sinius.maze.Game;
import sinius.maze.state.GrapicsLayer;

public class Layer_EditorOption implements GrapicsLayer{

	@Override
	public String getName() {
		return "editorOptionLayer";
	}

	@Override
	public void Draw(Graphics2D graphics) {
		Game.get().pluginManger.drawEditorObjects(graphics);
	}

	@Override
	public int priority() {
		return 4;
	}

	@Override
	public boolean drawAfter() {
		return false;
	}

}
