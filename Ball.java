import java.awt.*;

public class Ball extends Circle {
	
	public static final int NoHit = 0, HitTop = 1, HitBot = 2;
	public static final int HitLeft = 3, HitRight = 4;
	
	public Ball(int x, int y, int r, Color c) {
		super(x, y, r, c);
	}
	
	public int hit(int width, int height) {
		if(this.getCenterY() - this.getRadius() < 0) {
			this.setCenterY(30);
			return HitTop;
		} else if(this.getCenterY() + this.getRadius() > height) {
			this.setCenterY(height - 30);
			return HitBot;
		} else if(this.getCenterX() - this.getRadius() < 0) {
			this.setCenterX(30);
			return HitLeft;
		} else if(this.getCenterX() + this.getRadius() > width) {
			this.setCenterX(width - 30);
			return HitRight;
		}
		else {
			return NoHit;
		}
	}
	// pg 236
	public int velocity = 4;
	public int direction = 35;
	
	public void setVelocity(int v) {
		velocity = v;
	}
	public void setDirection(int degrees) {
		direction = degrees % 360;
	}
	public void turn(int degrees) {
		direction = (direction + degrees) % 360;
	}
	public void move() {
		move((int)(velocity * Math.cos(Math.toRadians(direction))),
				(int)(velocity * Math.sin(Math.toRadians(direction))));
	}
	
	public boolean isGoingUp() {
		if(direction > 180) {
			return true;
		} else {
			return false;
		}
	}
	public boolean isGoingLeft() {
		if(direction > 90 && direction < 270) {
			return true;
		} else {
			return false;
		}
	}
}