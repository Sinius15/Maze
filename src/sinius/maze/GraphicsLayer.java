package sinius.maze;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public interface GraphicsLayer {
	
	String getName();
	void Draw(Graphics2D graphics);
	void mouseClick(MouseEvent e);
	
	/**
	 *  priority 0 is the first one to be drawn, then 1, then 2, then 3, then 4 until 10.<br>
	 *  Overlay's should always be higher then 3<br><br>
	 *  priority  |   used by what<br>		
	 *  0 :          blocks of the maze  or things like begin screen.	<br>
	 *  1 :          spawn<br>
	 *  2 :          entitys  =>  player<br>
	 *  4 :          (editorObjects)<br>
	 *  5 :          timer<br>
	 *  6 :<br>
	 *  7 :<br>
	 *  8 :<br>
	 *  9 :          finish overlay<br>
	 *  10:          statsOverlay<br>
	 * @return your priority
	 */
	int priority();
	
}
