package sinius.maze.gui;

import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;

import sinius.maze.MainProgram;

public class EditorOptionScreen extends JFrame {

	private static final long serialVersionUID = 5527884862701605435L;

	private JPanel contentPane;
	ButtonGroup buttonGroup = new ButtonGroup();
	ColorChooserPanel panel1;
	private JList<String> list;

	public EditorOptionScreen() {
		setTitle("Option's");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 438, 296);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 422, 258);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Brush", null, panel, null);
		panel.setLayout(null);
		
		DefaultListModel<String> model = new DefaultListModel<String>();
		model.addElement("Wall Brush");
		model.addElement("Wall pencil");
		model.addElement("Spawn");
	    for(String s : MainProgram.entityManager.getEntityNames()){
	         model.addElement(s);
	    } 
	    for(String s : MainProgram.editorObjManager.getNames()){
	         model.addElement(s);
	    } 
		
		list = new JList<String>(model);
		list.setSelectedIndex(0);
		list.setBounds(12, 12, 393, 210);
		panel.add(list);
		
		
		panel1 = new ColorChooserPanel();
		panel1.tcc.setBounds(0, 0, 422, 227);
		tabbedPane.addTab("Color", null, panel1, null);
		panel1.setLayout(null);
		
		
		
	}
	
	public Color getColor(){
		return panel1.getColor();
	}
	
	public String getBrush(){
		return list.getSelectedValue();
	}
}
