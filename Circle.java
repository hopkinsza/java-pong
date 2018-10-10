import java.awt.*;

public class Circle {
	// data
	private int centerX, centerY, radius;
	private Color color;
	
	// constructor and gets
	public Circle(int x, int y, int r, Color c) {
		this.centerX = x;
		this.centerY = y;
		this.radius = r;
		this.color = c;
	}
	public int getCenterX() {
		return this.centerX;
	}
	public void setCenterY(int y) {
		this.centerY = y;
	}
	public void setCenterX(int x) {
		this.centerX = x;
	}
	public int getCenterY() {
		return this.centerY;
	}
	public int getRadius() {
		return this.radius;
	}
	
	// draw and fill
	public void draw(Graphics g) {
		Color oldColor = g.getColor();
		g.setColor(this.color);
		// draws from top left
		g.drawOval(centerX - radius, centerY - radius, radius * 2, radius * 2);
		g.setColor(oldColor);
	}
	public void fill(Graphics g) {
		Color oldColor = g.getColor();
		g.setColor(this.color);
		// draws from top left
		g.fillOval(centerX - radius, centerY - radius, radius * 2, radius * 2);
		g.setColor(oldColor);
	}
	
	public boolean containsPoint(int x, int y) {
		// essentially finds distance from midpoint and checks if radius is greater
		int xSquared = (x - centerX) * (x - centerX);
		int ySquared = (y - centerX) * (y - centerX);
		int radiusSquared = radius * radius;
		return xSquared + ySquared - radiusSquared <= 0;
	}
	
	public void move(int xAmount, int yAmount) {
		centerX = centerX + xAmount;
		centerY = centerY + yAmount;
	}
	
}
