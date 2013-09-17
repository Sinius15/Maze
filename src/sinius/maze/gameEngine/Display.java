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

public class Display{

	private JFrame frame;
	private DrawPane pane;
	private GeneralListener listner = new GeneralListener();
	public GameState gameState;
	private BufferedImage img = new BufferedImage(800, 800, BufferedImage.TYPE_INT_ARGB);
	public Camera camera = new Camera();
	
	
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
		
		
		pane.addMouseListener(listner);
		pane.addMouseMotionListener(listner);
		frame.addKeyListener(listner);
		frame.addComponentListener(listner);
		
		pane.addMouseListener(getMouseListener());
	}
	
	public void onTick(){
		listner.pressedKeys.doForAll(new editAction() {
			@Override
			public void action(Object o) {
				gameState.keyEvent((int) o);
			}
		});
		
		listner.pressedMouse.doForAll(new editAction() {
			@Override
			public void action(Object o) {
				gameState.mouseEvent((int)o);
			}
		});
	}
	
	public class DrawPane extends JPanel{
		private static final long serialVersionUID = -6825107813851526680L;
		public void paintComponent(final Graphics graphics){
			graphics.drawImage(img, 0, 0, pane.getWidth(), pane.getHeight(), camera.getP1().x, camera.getP1().y, camera.getP2().x, camera.getP2().y, null);
			
			if(gameState.getGObjects() != null)
				gameState.getGObjects().doForAll(new editAction() { @Override public void action(Object o) {
					GObject g = (GObject) o;
					g.Draw((Graphics2D) graphics);
			}});
		}
	}
	
	public void reDraw(){
		if(gameState.getGraphicsLayers() != null)
			gameState.getGraphicsLayers().doForAll(new editAction() { @Override public void action(Object o) {
					GrapicsLayer l = (GrapicsLayer) o;
					l.Draw((Graphics2D) img.getGraphics());
		}});
		
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
		gameState = t;
	}
}
