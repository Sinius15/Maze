package sinius.maze.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import sinius.maze.MainProgram;
import sinius.maze.Util;
import sinius.maze.api.Level;
import sinius.maze.io.LevelLoader;

public class LevelCreator extends JFrame {

	private static final long serialVersionUID = -8923472525792263271L;
	private JPanel contentPane;
	private JTextField colorField;
	private JTextField nameField;
	private JTextField blockColorField;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public LevelCreator() {
		setResizable(false);
		setTitle("Create Maze");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 291, 252);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JComboBox sizeField = new JComboBox();
		sizeField.setFont(new Font("Dialog", Font.PLAIN, 12));
		sizeField.setModel(new DefaultComboBoxModel(new String[] {"Standard", "Small", "Large"}));
		sizeField.setBounds(178, 48, 95, 25);
		contentPane.add(sizeField);
		
		JButton btnCreate = new JButton("Create");
		
		btnCreate.setBounds(10, 191, 263, 26);
		contentPane.add(btnCreate);
		
		JLabel lblSize = new JLabel("Size");
		lblSize.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblSize.setBounds(10, 47, 57, 25);
		contentPane.add(lblSize);
		
		JLabel lblColor = new JLabel("Background Color");
		lblColor.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblColor.setBounds(10, 83, 136, 25);
		contentPane.add(lblColor);
		
		colorField = new JTextField();
		colorField.setEditable(false);
		colorField.setText("255255255");
		colorField.setToolTipText("");
		colorField.setBounds(178, 84, 95, 25);
		contentPane.add(colorField);
		colorField.setColumns(10);
		
		nameField = new JTextField();
		nameField.setBounds(178, 14, 95, 20);
		contentPane.add(nameField);
		nameField.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblName.setBounds(10, 11, 57, 25);
		contentPane.add(lblName);
		
		final JCheckBox fillBox = new JCheckBox("");
		fillBox.setBounds(178, 156, 29, 24);
		contentPane.add(fillBox);
		
		JLabel lblFillTheMaze = new JLabel("Fill the maze");
		lblFillTheMaze.setToolTipText("do you want me to fill the whole level with blocks in the standard block color?");
		lblFillTheMaze.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblFillTheMaze.setBounds(10, 155, 136, 25);
		contentPane.add(lblFillTheMaze);
		
		JLabel lblStandardBlockColor = new JLabel("Standard block color");
		lblStandardBlockColor.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblStandardBlockColor.setBounds(10, 119, 136, 25);
		contentPane.add(lblStandardBlockColor);
		
		blockColorField = new JTextField();
		blockColorField.setEditable(false);
		blockColorField.setToolTipText("");
		blockColorField.setText("000000000");
		blockColorField.setColumns(10);
		blockColorField.setBounds(178, 120, 95, 25);
		contentPane.add(blockColorField);
		
		
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(nameField.getText().equals("") || colorField.getText().length() != 9 || blockColorField.getText().length() != 9){
					return;
				}
				
				
				int size;		
				String name;
				Color bgColor;
				Color bColor;
				boolean fillLevel = fillBox.isSelected();
				
				
				if(sizeField.getSelectedItem().equals("Large")){
					size = Level.SIZE_LARGE;
				}else if(sizeField.getSelectedItem().equals("Small")){
					size = Level.SIZE_SMALL;
				}else{
					size = Level.SIZE_NORMAL;
				}
				
				name = nameField.getText();
				
				bgColor = new Color(Integer.parseInt(colorField.getText().substring(0, 3)), 
												Integer.parseInt(colorField.getText().substring(3, 6)), 
												Integer.parseInt(colorField.getText().substring(6, 9)));
				bColor = new Color(Integer.parseInt(blockColorField.getText().substring(0, 3)), 
												Integer.parseInt(blockColorField.getText().substring(3, 6)), 
												Integer.parseInt(blockColorField.getText().substring(6, 9)));

				
				try {
					LevelLoader.SaveLevel(Util.createEmptyLevel(size, name, bgColor, bColor, fillLevel), MainProgram.SAVEMAP + "\\saves\\" + nameField.getText() + ".maze");
				} catch (Exception e) {
					e.printStackTrace();
					return;
				}
				
				MainProgram.loadAllLevels();
				disposer();
			}
		});
	}
	
	final void disposer(){
		this.dispose();
	}
}
