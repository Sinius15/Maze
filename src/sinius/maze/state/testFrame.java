package sinius.maze.state;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;

@SuppressWarnings("serial")
public class testFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					testFrame frame = new testFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public testFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 823, 840);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLevel = new JLabel("Level");
		lblLevel.setBackground(Color.BLUE);
		lblLevel.setBounds(50, 50, 700, 40);
		contentPane.add(lblLevel);
		
		JLabel label = new JLabel("Level");
		label.setBackground(Color.BLUE);
		label.setForeground(Color.CYAN);
		label.setBounds(50, 100, 700, 40);
		contentPane.add(label);
		
		JButton button = new JButton("Create");
		button.setBounds(50, 700, 130, 55);
		contentPane.add(button);
		
		JButton button_1 = new JButton("Create");
		button_1.setBounds(200, 700, 130, 55);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("Create");
		button_2.setBounds(342, 700, 130, 55);
		contentPane.add(button_2);
		
		JButton button_3 = new JButton("Create");
		button_3.setBounds(484, 700, 130, 55);
		contentPane.add(button_3);
		
		JButton button_4 = new JButton("Create");
		button_4.setBounds(626, 700, 130, 55);
		contentPane.add(button_4);
	}
}
