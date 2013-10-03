package sinius.maze.launcher;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginScreen extends JFrame {

	private static final long serialVersionUID = -2739981413131156545L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	public LoginScreen() {
		setTitle("Sinius Maze - Launcher");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 301, 163);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmSaveAccount = new JMenuItem("Save Account");
		mnFile.add(mntmSaveAccount);
		
		JMenuItem mntmLoadAccount = new JMenuItem("Load Account");
		mnFile.add(mntmLoadAccount);
		
		JMenu mnOptions = new JMenu("Options");
		menuBar.add(mnOptions);
		
		JMenuItem mntmOptions = new JMenuItem("Options");
		mntmOptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OptionScreen s = new OptionScreen();
				s.setVisible(true);
				Main.frame.setEnabled(false);
			}
		});
		mnOptions.add(mntmOptions);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnPlay = new JButton("Play");
		btnPlay.setToolTipText("Start the game");
		btnPlay.setBounds(146, 76, 137, 26);
		contentPane.add(btnPlay);
		
		textField = new JTextField();
		textField.setToolTipText("Username");
		textField.setBounds(83, 12, 200, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(12, 14, 81, 16);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(12, 44, 81, 16);
		contentPane.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setToolTipText("Password");
		passwordField.setBounds(83, 44, 200, 20);
		contentPane.add(passwordField);
		
		JButton btnCreateAccoutn = new JButton("Create Account");
		btnCreateAccoutn.setBounds(12, 76, 122, 26);
		contentPane.add(btnCreateAccoutn);
	}
}
