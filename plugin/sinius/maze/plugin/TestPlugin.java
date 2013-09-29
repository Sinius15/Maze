package sinius.maze.plugin;

import sinius.maze.plugin.mob.KungfuPanda;

public class TestPlugin extends Plugin{
	
	@Override
	public void onEnable() {
		getPluginManager().addEntity(Exit.class);
		getPluginManager().addEntity(KungfuPanda.class);
		getPluginManager().addEditorObject(new TeleporterLink());
	}

	
}

