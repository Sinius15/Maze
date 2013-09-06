package sinius.maze.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import sinius.maze.Game;
import sinius.maze.MainProgram;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

public class StartupScreen extends JFrame {

	private static final long serialVersionUID = -3086031329458294778L;
	private JPanel contentPane;
	private JTextField textField;
	private int levelNr;
	

	public StartupScreen() {
		setResizable(false);
		setTitle("Sinius's Maze   -  Level Choser");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 455, 321);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel LevelPic = new JPanel();
		LevelPic.setBackground(Color.LIGHT_GRAY);
		LevelPic.setBounds(61, 11, 310, 160);
		
		contentPane.add(LevelPic);
		
		
		final JToggleButton useEditor = new JToggleButton("Edit mode");
		useEditor.setBounds(171, 248, 91, 26);
		contentPane.add(useEditor);
		
		JButton PlayLevel = new JButton("Play This level");
		PlayLevel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainProgram.game = new Game(MainProgram.levels.get(levelNr), useEditor.isSelected());
				MainProgram.startupScreen.dispose();
				if(useEditor.isSelected()){
					Game.options = new EditorOptionScreen();
					Game.options.setVisible(true);
				}
			}
		});
		PlayLevel.setBounds(61, 204, 312, 32);
		contentPane.add(PlayLevel);
		
		JButton showPrevLevel = new JButton("<");
		showPrevLevel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				prevLevel();
			}
		});
		showPrevLevel.setBounds(12, 10, 41, 161);
		contentPane.add(showPrevLevel);
		
		JButton showNextLevel = new JButton(">");
		showNextLevel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextLevel();
			}
		});
		showNextLevel.setBounds(383, 10, 41, 161);
		contentPane.add(showNextLevel);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setEditable(false);
		textField.setBounds(61, 172, 312, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNew = new JButton("New");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LevelCreator c = new LevelCreator();
				c.setVisible(true);
				c.setAlwaysOnTop(true);
			}
		});
		btnNew.setBounds(274, 248, 98, 26);
		contentPane.add(btnNew);
		
		JButton btnKnopje = new JButton("knopje?");
		btnKnopje.setBounds(61, 248, 98, 26);
		contentPane.add(btnKnopje);

		
		nextLevel();
	}
	
	private void prevLevel(){
		if(MainProgram.levels.size() == 0){
			return;
		}
		levelNr--;
		if(levelNr < 0)  levelNr = MainProgram.levels.size()-1;
		textField.setText(MainProgram.levels.get(levelNr).getName());
	}
	
	private void nextLevel(){
		if(MainProgram.levels.size() == 0){
			return;
		}
		levelNr++;
		if(levelNr > MainProgram.levels.size()-1)  levelNr = 0;
		textField.setText(MainProgram.levels.get(levelNr).getName());
		
	}
}
