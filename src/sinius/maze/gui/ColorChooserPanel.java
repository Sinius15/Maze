package sinius.maze.gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ColorChooserPanel extends JPanel{

	private static final long serialVersionUID = 2175531522440397707L;
	public JColorChooser tcc;

    public ColorChooserPanel() {
        super(new BorderLayout());

        tcc = new JColorChooser();
        tcc.setColor(Color.white);
        tcc.setPreviewPanel(new JPanel());
        add(tcc, BorderLayout.PAGE_END);
       // tcc.setColor(Startup.game.level.getStandardBlockColor());
    }


    public static void createAndShowGUI() {
        JFrame frame = new JFrame("Color Picker");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JComponent newContentPane = new ColorChooserPanel();
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);
        frame.pack();
        frame.setVisible(true);
        
    }
    
    public Color getColor(){
    	return tcc.getColor();
    }
}
