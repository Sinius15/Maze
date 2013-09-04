package sinius.maze.gui;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import sinius.maze.Game;
import sinius.maze.MainProgram;

public class GameScreen extends JFrame {

	private static final long serialVersionUID = -3616954140020650241L;
	private DrawPane p = new DrawPane();
	
	public GameScreen() {
		setResizable(false);
		setTitle("Sinius's Maze");
		p.setPreferredSize(new Dimension(800, 800));
		setContentPane(p);
		p.addComponentListener(Game.keys);
		this.addKeyListener(Game.keys);
		p.addMouseListener(Game.keys);
		p.addMouseMotionListener(Game.keys);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 800);
		setVisible(true); 
		pack();
		
	}

	class DrawPane extends JPanel{

		private static final long serialVersionUID = -6825107813851526680L;

		public void paintComponent(Graphics g){
			MainProgram.game.draw(g);
		}
	}
	
}
