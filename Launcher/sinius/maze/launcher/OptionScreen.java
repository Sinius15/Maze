package sinius.maze.launcher;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JCheckBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
					btnNewButton.setEnabled(true);
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
		btnNewButton.setEnabled(false);
		btnNewButton.setBounds(411, 8, 21, 21);
		contentPane.add(btnNewButton);
	}
}
