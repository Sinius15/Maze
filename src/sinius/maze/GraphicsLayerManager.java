package sinius.maze;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GraphicsLayerManager {

	private ArrayList<GraphicsLayer> curLayers = new ArrayList<GraphicsLayer>();
	private GraphicsLayer temp;
	
	private synchronized void editCurLayers(String what, GraphicsLayer s, Graphics2D graphics, MouseEvent event){
		if(what.equals("add"))
			curLayers.add(s);
		else if(what.equals("remove")){
			for(GraphicsLayer g : curLayers){
				if(g.getClass().equals(s.getClass()))
					temp = g;
			}
			curLayers.remove(temp);
		}
		else if(what.equals("draw")){
			for(int i = 0; i <= 10; i++){
				for(GraphicsLayer g : curLayers){
					if(g.priority() == i){
						System.out.println("drawing " + g.priority());
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
	}
	
	public synchronized void addLayer(GraphicsLayer g){
		this.editCurLayers("add", g, null, null);
	}
	
	public synchronized void removeLayer(GraphicsLayer g){
		this.editCurLayers("remove", g, null, null);
	}
	
	public synchronized void draw(Graphics2D g){
		this.editCurLayers("draw", null, g, null);
	}
	
	public synchronized void mouseClick(MouseEvent e){
		this.editCurLayers("mouseClick", null, null, e);
	}
	
}
