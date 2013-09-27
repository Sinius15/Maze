package sinius.maze.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import sinius.maze.Block;
import sinius.maze.Game;
import sinius.maze.Level;
import sinius.maze.Util;
import sinius.maze.io.LevelLoader;
import sinius.maze.lib.Folders;

public class LevelImporter extends JFrame {

	private static final long serialVersionUID = -8770237157237498654L;
	private JPanel contentPane;
	private JTextField textField_name;
	private JTextField textField_file;


	public LevelImporter() {
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				Game.get().display.getFrame().setVisible(true);
			}
		});
		
		setResizable(false);
		setTitle("Image to Maze");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 440, 190);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnChoseFile = new JButton("Chose file");
		btnChoseFile.setBounds(10, 11, 153, 41);
		contentPane.add(btnChoseFile);
		
		textField_name = new JTextField();
		textField_name.setBounds(173, 63, 251, 38);
		contentPane.add(textField_name);
		textField_name.setColumns(10);
		
		JLabel lblLevelName = new JLabel(" Level Name");
		lblLevelName.setFont(new Font("Arial", Font.PLAIN, 12));
		lblLevelName.setBounds(10, 63, 153, 38);
		contentPane.add(lblLevelName);
		
		textField_file = new JTextField();
		textField_file.setColumns(10);
		textField_file.setBounds(173, 11, 251, 38);
		contentPane.add(textField_file);
		
		JButton btnCreate = new JButton("Create");
		
		btnCreate.setBounds(10, 112, 414, 41);
		contentPane.add(btnCreate);
		
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File file = new File(textField_file.getText());
//				File file = new File("A://tester.png");
				if(!file.exists() || !file.getName().endsWith(".png")){
					System.out.println("returning!");
					return;
				}
					
				BufferedImage i;
				try {
					i = ImageIO.read(file);
				} catch (IOException e1) {
					e1.printStackTrace();
					return;
				}
				
				Raster r = i.getData();
				
				Level l = Util.createEmptyLevel(r.getWidth(), r.getHeight() , textField_name.getText(), Color.white, Color.black, false);
				
				for(int x = 0; x<r.getWidth();x++){
					for(int y = 0; y< r.getHeight(); y++){
						if((i.getRGB(x, y)>>24) != 0x00 ){
							l.getBlock(x, y).setType(Block.WALL);
							l.getBlock(x, y).setColor(new Color(i.getRGB(x, y)));
						}
					}
				}
				
				try {
					LevelLoader.SaveLevel(l, Folders.SAVES.getAbsolutePath() + "\\" + l.getName() + ".maze");
				} catch (Exception e1) {
					e1.printStackTrace();
					return;
				}
				disposer();
			}});
	}
	
	final void disposer(){
		this.dispose();
	}
}
