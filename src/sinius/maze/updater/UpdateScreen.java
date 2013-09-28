package sinius.maze.updater;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import sinius.maze.Game;
import sinius.maze.lib.Folders;
import sinius.maze.lib.Layout;

public class UpdateScreen extends JFrame {
	JLabel status;
	private static final long serialVersionUID = 4701563347273403805L;
	private JPanel contentPane;
	JButton btnUpdate;
	private String newVersion = null;
	private File out;
	JButton btnGoToThe;
	
	public UpdateScreen(String v) {
		this.newVersion = v;
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				Game.get().display.getFrame().setVisible(true);
			}
		});
		
		setResizable(false);
		setTitle("Updater");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 282, 140);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUpdateAvalable = new JLabel("Update Avalable!");
		lblUpdateAvalable.setHorizontalAlignment(SwingConstants.CENTER);
		lblUpdateAvalable.setFont(Layout.MAIN_FONT);
		lblUpdateAvalable.setBounds(10, 11, 261, 27);
		contentPane.add(lblUpdateAvalable);
		
		btnUpdate = new JButton("Update!");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnUpdate.setVisible(false);
				
				try{
					status.setText("status: downloading...");
		            URL url = new URL("http://sinius15.com/maze/version/"+newVersion + ".jar");
		            URLConnection urlConn = url.openConnection();
		            BufferedInputStream is = new BufferedInputStream(urlConn.getInputStream());
		            out = new File(Folders.TEMP + "//Sinius Maze " + newVersion + ".jar");
		            BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(out));
		            byte[] b = new byte[8 *1024];
		            int read = 0;
		            while((read = is.read(b)) > -1){
		                bout.write(b,0, read);
		                	
		            }
		            bout.flush();
		            bout.close();
		            is.close();
		            status.setText("status: download ready");
		            btnGoToThe.setEnabled(true);
		        }
		        catch(IOException mfu){
		            mfu.printStackTrace();
		        }

			}
		});
		btnUpdate.setBounds(10, 66, 261, 40);
		contentPane.add(btnUpdate);
		
		status = new JLabel("status: ready to downlaod the file");
		status.setBounds(10, 38, 261, 16);
		contentPane.add(status);
		
		btnGoToThe = new JButton("Go to the folder");
		btnGoToThe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop.getDesktop().open(new File(out.getAbsolutePath().replaceAll(out.getName(),"")));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				System.exit(1);
			}
		});
		btnGoToThe.setEnabled(false);
		btnGoToThe.setBounds(10, 66, 261, 40);
		contentPane.add(btnGoToThe);
	}
}
