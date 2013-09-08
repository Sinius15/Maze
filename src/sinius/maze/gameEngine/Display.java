package sinius.maze.gameEngine;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Display{

	private JFrame frame;
	private DrawPane pane;
	private Graphics2D graphics;
	private DrawAction drawing;
	private ArrayList<GObject> objects = new ArrayList<GObject>();
	
	public Display(int width, int height, String title){
		frame = new JFrame();
		pane = new DrawPane();
		pane.setPreferredSize(new Dimension(width, height));
		frame.setContentPane(pane);
		frame.setTitle(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);
		
		pane.addMouseListener(getMouseListener());
		
		graphics = (Graphics2D) frame.getContentPane().getGraphics();
	}
	
	private class DrawPane extends JPanel{
		private static final long serialVersionUID = -6825107813851526680L;
		public void paintComponent(Graphics g){
			for(GObject b : objects){
				b.Draw((Graphics2D) g);
			}
			if(drawing != null)
				drawing.Draw((Graphics2D) g);
		}
	}
	
	public interface DrawAction{
		void Draw(Graphics2D g);
	}
	
	public Graphics2D getGraphics(){
		return graphics;
	}
	
	public void setDrawAction(DrawAction g){
		this.drawing = g;
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
}
