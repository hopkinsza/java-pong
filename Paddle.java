import java.awt.*;

public class Paddle extends Rectangle {
	private static final long serialVersionUID = 1L;
	
	private Color color;
	
	public Paddle(int x, int y, int width, int height, Color color) {
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		this.color = color;
	}
	
	public void move(int move) {
		this.y += move;
	}
	
	public void fill(Graphics g) {
		Color oldColor = g.getColor();
		g.setColor(this.color);
		g.fillRect(x, y, width, height);
		g.setColor(oldColor);
	}
}