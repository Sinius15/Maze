package sinius.maze.gameEngine;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import sinius.maze.core.SynchroniezedList.editAction;
import sinius.maze.state.GameState;
import sinius.maze.state.GrapicsLayer;

public class Display{

	private JFrame frame;
	private DrawPane pane;
	private Graphics2D graphics;
	public GameState gameState;
	
	public Display(int width, int height, String title, GameState state){
		gameState = state;
		System.out.println("the state is: " + gameState.getName());
		frame = new JFrame();
		pane = new DrawPane();
		frame.setResizable(false);
		pane.setPreferredSize(new Dimension(width, height));
		frame.setContentPane(pane);
		frame.setTitle(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.pack();
		frame.setVisible(true);
		
		pane.addMouseListener(getMouseListener());
		if(gameState.getMouseListener() != null)
			pane.addMouseListener(gameState.getMouseListener());
		if(gameState.getKeyListener() != null)
			pane.addKeyListener(gameState.getKeyListener());
		graphics = (Graphics2D) frame.getContentPane().getGraphics();
	}
	
	public class DrawPane extends JPanel{
		private static final long serialVersionUID = -6825107813851526680L;
		public void paintComponent(Graphics g){
			if(gameState.getGObjects() != null)
				gameState.getGObjects().doForAll(new editAction() { @Override public void action(Object o) {
					GObject g = (GObject) o;
					g.Draw(graphics);
			}});
			if(gameState.getGraphicsLayers() != null)
				gameState.getGraphicsLayers().doForAll(new editAction() { @Override public void action(Object o) {
						GrapicsLayer g = (GrapicsLayer) o;
						g.Draw((Graphics2D) graphics);
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
			public void mouseClicked(final MouseEvent arg0) {
				if(gameState.getGObjects() != null)
					gameState.getGObjects().doForAll(new editAction() { @Override public void action(Object o) {
					GObject g = (GObject) o;
					g.MouseClick(arg0);
				}});
			}
		};
	}
	
	public void setGameState(GameState t){
		pane.removeMouseListener(gameState.getMouseListener());
		pane.removeKeyListener(gameState.getKeyListener());
		gameState = t;
		if(gameState.getMouseListener() != null)
			pane.addMouseListener(gameState.getMouseListener());
		if(gameState.getKeyListener() != null)
			pane.addKeyListener(gameState.getKeyListener());
	}
}
