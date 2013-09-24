package sinius.maze.gameEngine;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

import sinius.maze.core.SynchroniezedList.editAction;
import sinius.maze.state.GameState;
import sinius.maze.state.GrapicsLayer;
import sinius.maze.state.StatsOverlay;

public class Display{

	private JFrame frame;
	private DrawPane pane;
	private GeneralListener listner = new GeneralListener();
	public GameState gameState;
	private BufferedImage img = new BufferedImage(800, 800, BufferedImage.TYPE_INT_ARGB);
	public Camera camera = new Camera();
	
	private int i;
	
	private StatsOverlay stats = new StatsOverlay();
	
	
	public Display(int width, int height, String title, GameState state){
		gameState = state;
		frame = new JFrame();
		
		pane = new DrawPane();
		frame.setResizable(false);
		pane.setPreferredSize(new Dimension(width, height));
		frame.setContentPane(pane);
		frame.setTitle(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();

		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		pane.addMouseListener(listner);
		pane.addMouseMotionListener(listner);
		frame.addKeyListener(listner);
		frame.addComponentListener(listner);
		
		pane.addMouseListener(getMouseListener());
	}
	
	public void onTick(){
		listner.pressedKeys.doForAll(new editAction<Integer>() { @Override public void action(Integer o) {
			gameState.keyEvent(o);
			
		}});
		
		listner.pressedMouse.doForAll(new editAction<Integer>() { @Override public void action(Integer o) {
				gameState.mouseEvent(o);
		}});
	}
	
	public class DrawPane extends JPanel{
		private static final long serialVersionUID = -6825107813851526680L;
		public void paintComponent(final Graphics graphics){
			graphics.drawImage(img, 0, 0, pane.getWidth(), pane.getHeight(), camera.getP1().x, camera.getP1().y, camera.getP2().x, camera.getP2().y, null);
			if(gameState.getGraphicsLayers() != null)
			gameState.getGraphicsLayers().doForAll(new editAction<GrapicsLayer>() { @Override public void action(GrapicsLayer l) {
				if(l.drawAfter())
					l.Draw((Graphics2D) graphics);
			}});
			if(gameState.getGObjects() != null)	
				gameState.getGObjects().doForAll(new editAction<GObject>() { @Override public void action(GObject g) {
					g.Draw((Graphics2D) graphics);
			}});
			stats.Draw((Graphics2D) graphics);
		}
	}
	
	public void reDraw(){
		if(gameState.getGraphicsLayers() != null)
			for(i = 0 ; i<11 ; i++){
				gameState.getGraphicsLayers().doForAll(new editAction<GrapicsLayer>() { @Override public void action(GrapicsLayer l) {
					if(!l.drawAfter())
						if(l.priority() == i)
							l.Draw(img.createGraphics());
				}});
			}
				
			
		
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
					gameState.getGObjects().doForAll(new editAction<GObject>() { @Override public void action(GObject o) {
						o.MouseClick(arg0);
					}});
			}
		};
	}
	
	public void setGameState(GameState t){
		gameState = t;
	}
}
