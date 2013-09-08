package sinius.maze;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class GraphicsLayerManager {

	private Vector<GraphicsLayer> curLayers = new Vector<GraphicsLayer>();
	private Vector<GraphicsLayer> toAdd = new Vector<GraphicsLayer>();
	private Vector<GraphicsLayer> temp = new Vector<GraphicsLayer>();
	private Vector<Class<?>> toRemove = new Vector<Class<?>>();
	
	
	private synchronized void editCurLayers(String what, GraphicsLayer s, Graphics2D graphics, MouseEvent event){

		if(what.equals("add"))
			curLayers.add(s);
		else if(what.equals("draw")){
			for(int i = 0; i <= 10; i++){
				for(GraphicsLayer g : curLayers){
					if(g.priority() == i){
						g.Draw(graphics);
					}
				}
			}
				
		}
		else if(what.equals("mouseClick")){
			for(GraphicsLayer g : curLayers){
				g.mouseClick(event);
			}
			
		}
		else if(what.equals("onTick")){
			for(GraphicsLayer g : toAdd){
				curLayers.add(g);
			}
			toAdd.clear();
			for(Class<?> c : toRemove){
				for(GraphicsLayer g : curLayers){
					if(g.getClass().equals(c))
						temp.add(g);
				}
			}
			for(GraphicsLayer g : temp){
				curLayers.remove(g);
			}
			temp.clear();
			toRemove.clear();
		}
		
		
	}
	
	public synchronized void tick(){
		editCurLayers("onTick", null, null, null);
	}
	
	public synchronized void addLayer(GraphicsLayer i){
		toAdd.add(i);
	}
	
	public synchronized void removeLayer(Class<?> i){
		toRemove.add(i);
	}
	
	public synchronized void draw(Graphics2D g){
		this.editCurLayers("draw", null, g, null);
	}
	
	public synchronized void mouseClick(MouseEvent e){
		this.editCurLayers("mouseClick", null, null, e);
	}
	
}
