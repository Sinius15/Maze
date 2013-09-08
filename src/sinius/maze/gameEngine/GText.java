package sinius.maze.gameEngine;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class GText implements GObject{

	private String text;
	private Point point;
	private Font font;
	private Color color;
	
	public GText(String text, int x, int y){
		this.setPoint(new Point(x, y));
		this.setText(text);
	}
	
	@Override
	public void Draw(Graphics2D g) {
		if(font != null)
			g.setFont(font);
		if(color != null)
			g.setColor(color);
		g.drawString(text, (int)point.getX(), (int)point.getY());
	}

	@Override
	public void MouseClick(MouseEvent event) {
	}

	@Override
	public void KeyClick(KeyEvent event) {
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	

}
