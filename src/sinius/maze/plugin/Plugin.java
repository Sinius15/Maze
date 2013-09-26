package sinius.maze.plugin;

import sinius.maze.Game;

public abstract class Plugin{
	
	public void onEnable(){};
	
	public void onDisable(){};
	
	public PluginManager getPluginManager(){
		return Game.get().pluginManger;
	}
}
