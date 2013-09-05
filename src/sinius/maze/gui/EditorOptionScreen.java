package sinius.maze.gui;

import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

public class EditorOptionScreen extends JFrame {

	private static final long serialVersionUID = 5527884862701605435L;

	private JPanel contentPane;
	ButtonGroup buttonGroup = new ButtonGroup();
	ColorChooserPanel panel1;
	final JRadioButton br_spawn , br_end, br_WallBrush, br_WallPencil, br_Teleporter;

	public EditorOptionScreen() {
		setTitle("Option's");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 581, 296);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 565, 258);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Brush", null, panel, null);
		panel.setLayout(null);
		
		br_spawn = new JRadioButton("Spawn Point");
		br_spawn.setBounds(8, 64, 121, 24);
		panel.add(br_spawn);
		buttonGroup.add(br_spawn);
		
		br_end = new JRadioButton("End Point");
		br_end.setBounds(203, 8, 121, 24);
		panel.add(br_end);
		buttonGroup.add(br_end);
		
		br_WallBrush = new JRadioButton("Wall");
		br_WallBrush.setSelected(true);
		br_WallBrush.setBounds(8, 8, 121, 24);
		panel.add(br_WallBrush);
		buttonGroup.add(br_WallBrush);
		
		br_WallPencil = new JRadioButton("Pencil");
		br_WallPencil.setBounds(8, 36, 121, 24);
		panel.add(br_WallPencil);
		buttonGroup.add(br_WallPencil);
		
		br_Teleporter = new JRadioButton("Teleporter");
		br_Teleporter.setBounds(203, 36, 121, 24);
		panel.add(br_Teleporter);
		buttonGroup.add(br_Teleporter);
		
		panel1 = new ColorChooserPanel();
		panel1.tcc.setBounds(0, 0, 560, 227);
		tabbedPane.addTab("Color", null, panel1, null);
		panel1.setLayout(null);
		
		
		
	}
	
	public Color getColor(){
		return panel1.getColor();
	}
	
	public String getBrush(){
		if(br_spawn.isSelected())
			return "spawn";
		if(br_end.isSelected())
			return "exit";
		if(br_WallBrush.isSelected())
			return "wall";
		if(br_WallPencil.isSelected())
			return "pencil";
		if(br_Teleporter.isSelected())
			return "teleporter";
		return "";
	}
}
