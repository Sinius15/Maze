package sinius.maze.gameEngine;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import sinius.maze.core.SynchroniezedList.editAction;
import sinius.maze.state.GameState;
import sinius.maze.state.GrapicsLayer;

public class Display{

	private JFrame frame;
	private DrawPane pane;
	private Graphics2D graphics;
	private ArrayList<GObject> objects = new ArrayList<GObject>();
	private GameState gameState;
	
	public Display(int width, int height, String title){
		frame = new JFrame();
		pane = new DrawPane();
		frame.setResizable(false);
		pane.setPreferredSize(new Dimension(width, height));
		frame.setContentPane(pane);
		frame.setTitle(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.pack();
		frame.setVisible(true);
		
		
		
		graphics = (Graphics2D) frame.getContentPane().getGraphics();
	}
	
	public class DrawPane extends JPanel{
		private static final long serialVersionUID = -6825107813851526680L;
		public void paintComponent(Graphics g){
			for(GObject b : objects){
				b.Draw((Graphics2D) g);
			}
			gameState.getGraphicsLayers().doForAll(new editAction() { @Override public void action(Object o) {
					GrapicsLayer g = (GrapicsLayer) o;
					g.Draw((Graphics2D) g);
			}});
		}
	}
	
	public Graphics2D getGraphics(){
		return graphics;
	}
	
	public void reDraw(){
		frame.repaint();
	}
	
	public JFrame getFrame(){
		return frame;
	}
	
	public DrawPane getPanel(){
		return pane;
	}
	
	public synchronized void add(GObject object){
		objects.add(object);
	}
	
	private MouseListener getMouseListener(){
		return new MouseListener() {
			
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
				for(GObject b : objects){
					b.MouseClick(arg0);
				}
			}
		};
	}
	
	public void setGameState(GameState t){
		this.gameState = t;
		pane.addMouseListener(getMouseListener());
		if(t.getMouseListener() != null)
			pane.addMouseListener(t.getMouseListener());
		if(t.getKeyListener() != null)
			pane.addKeyListener(t.getKeyListener());
		
	}
}
