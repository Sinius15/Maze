package sinius.maze.launcher;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JCheckBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JButton;

public class OptionScreen extends JFrame {

	private static final long serialVersionUID = 6252940110687701565L;
	private JPanel contentPane;
	private JTextField textField;
	private JButton btnNewButton;


	public OptionScreen() {
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				Main.frame.setEnabled(true);
				Main.frame.requestFocus(); 
			}
		});
		setTitle("Sinius Maze - Options");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JCheckBox chckbxUseDefaultData = new JCheckBox("Use Default Data Locatin");
		chckbxUseDefaultData.setSelected(true);
		chckbxUseDefaultData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxUseDefaultData.isSelected()){
					btnNewButton.setEnabled(false);
					textField.setEnabled(false);
				}else{
					btnNewButton.setEnabled(true);
					textField.setEnabled(true);
				}
					
			}
		});
		chckbxUseDefaultData.setBounds(6, 7, 184, 23);
		contentPane.add(chckbxUseDefaultData);
		
		textField = new JTextField();
		textField.setEnabled(false);
		textField.setBounds(198, 8, 201, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		btnNewButton = new JButton("...");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					JFileChooser filechooser = new JFileChooser();
					filechooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					filechooser.setAcceptAllFileFilterUsed(false);
					filechooser.setDialogType(JFileChooser.SAVE_DIALOG);
					int returnErrorNR = filechooser.showSaveDialog(null);
					if(returnErrorNR != 0)
						return;
					String path = filechooser.getSelectedFile().getAbsolutePath();
					textField.setText(path);
					
			
		}});
		btnNewButton.setEnabled(false);
		btnNewButton.setBounds(411, 8, 21, 21);
		contentPane.add(btnNewButton);
		
		final JCheckBox chckbxAutomaticUpdate = new JCheckBox("Automatic Update");
		chckbxAutomaticUpdate.setSelected(true);
		chckbxAutomaticUpdate.setBounds(6, 34, 184, 23);
		contentPane.add(chckbxAutomaticUpdate);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.dataPath = new File(textField.getText());
				if(chckbxUseDefaultData.isSelected())
					Main.dataPath = null;
				Main.autoUpadte = chckbxAutomaticUpdate.isSelected();
					
				
				
				Main.frame.setEnabled(true);
				Main.frame.optionScreen.setVisible(false);
			}
		});
		btnSave.setBounds(334, 234, 98, 26);
		contentPane.add(btnSave);
		
		
	}
}
