package sinius.maze.gameEngine;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class GButton implements GObject{

	private Color buttonColor, textColor;
	private Image img;
	private ActionListener action;
	private String text = "";
	private Font font;
	private Rectangle boundrys;

	public GButton(int x, int y, int width, int height){
		boundrys = new Rectangle(x, y, width, height);
	}
	
	@Override
	public void Draw(Graphics2D g) {
		if(buttonColor != null)
			g.setColor(buttonColor);
		g.fill(boundrys);
		if(img != null)
			g.drawImage(img, (int) boundrys.getX(), (int) boundrys.getY(), (int) boundrys.getWidth(), (int) boundrys.getHeight(), null);
		if(font != null)
			g.setFont(font);
		if(text != null){
			g.setColor(textColor);
			FontMetrics metrics = g.getFontMetrics(g.getFont());
			int adv = metrics.stringWidth(text);
			g.drawString(text, (int)(boundrys.getX() + (boundrys.getWidth()/2)-(adv/2)), (int)(boundrys.getY()+(boundrys.getHeight()/2)));
		}
			
	}


	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	@Override
	public void MouseClick(MouseEvent event) {
		if(action != null){
			if(boundrys.contains(event.getPoint()))
					action.actionPerformed(new ActionEvent(this, 0, null));
		}
			
	}

	@Override
	public void KeyClick(KeyEvent event) {
		
	}

	public void setSize(Rectangle r){
		this.boundrys = r;
	}
	
	public Rectangle getSize(){
		return this.boundrys;
	}
	
	public ActionListener getAction() {
		return action;
	}

	public void setAction(ActionListener action) {
		this.action = action;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public Color getButtonColor() {
		return buttonColor;
	}

	public void setButtonColor(Color buttonColor) {
		this.buttonColor = buttonColor;
	}

	public Color getTextColor() {
		return textColor;
	}

	public void setTextColor(Color textColor) {
		this.textColor = textColor;
	}
	
}
